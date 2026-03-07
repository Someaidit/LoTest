package com.lotest.sodiumpaper;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OptimizationTask implements Runnable {

    private final SodiumPaperPlugin plugin;
    private final OptimizationStats stats = new OptimizationStats();
    private int taskId = -1;

    public OptimizationTask(SodiumPaperPlugin plugin) {
        this.plugin = plugin;
    }

    public OptimizationStats getStats() {
        return stats;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void cancel() {
        if (taskId != -1) {
            plugin.getServer().getScheduler().cancelTask(taskId);
        }
    }

    @Override
    public void run() {
        long started = System.currentTimeMillis();
        stats.reset();

        for (World world : plugin.getServer().getWorlds()) {
            if (plugin.getConfig().getBoolean("optimizations.skip-empty-worlds", true) && world.getPlayers().isEmpty()) {
                stats.setWorldsSkipped(stats.getWorldsSkipped() + 1);
                continue;
            }

            stats.setWorldsScanned(stats.getWorldsScanned() + 1);
            applyWorldCaps(world);

            if (plugin.isFeatureEnabled("dynamic-ai")) {
                processDynamicAi(world);
            }
            if (plugin.isFeatureEnabled("item-merge")) {
                processItemMerge(world);
            }
            if (plugin.isFeatureEnabled("cleanup-arrows")) {
                cleanupArrows(world);
            }
            if (plugin.isFeatureEnabled("cleanup-exp-orbs")) {
                cleanupExpOrbs(world);
            }
            if (plugin.isFeatureEnabled("gamerule-randomTickSpeed") || plugin.isFeatureEnabled("gamerule-maxEntityCramming")) {
                applyGameRules(world);
            }
        }

        stats.setCycleTimeMs(System.currentTimeMillis() - started);
    }

    private void applyWorldCaps(World world) {
        if (plugin.isFeatureEnabled("limit-animals")) {
            world.setAnimalSpawnLimit(plugin.getConfig().getInt("limits.animals", 12));
        }
        if (plugin.isFeatureEnabled("limit-monsters")) {
            world.setMonsterSpawnLimit(plugin.getConfig().getInt("limits.monsters", 45));
        }
        if (plugin.isFeatureEnabled("spawn-animal-interval")) {
            world.setTicksPerAnimalSpawns(plugin.getConfig().getInt("spawn-intervals.animals", 600));
        }
        if (plugin.isFeatureEnabled("spawn-monster-interval")) {
            world.setTicksPerMonsterSpawns(plugin.getConfig().getInt("spawn-intervals.monsters", 2));
        }
        if (plugin.isFeatureEnabled("spawn-keep-spawn-loaded")) {
            world.setKeepSpawnInMemory(plugin.getConfig().getBoolean("world.keep-spawn-loaded", false));
        }
        if (plugin.isFeatureEnabled("world-autosave-interval")) {
            world.setAutoSave(plugin.getConfig().getBoolean("world.autosave", true));
        }
    }

    private void applyGameRules(World world) {
        if (plugin.isFeatureEnabled("gamerule-randomTickSpeed")) {
            world.setGameRuleValue("randomTickSpeed", String.valueOf(plugin.getConfig().getInt("gamerules.random-tick-speed", 2)));
            stats.setGamerulesApplied(stats.getGamerulesApplied() + 1);
        }
        if (plugin.isFeatureEnabled("gamerule-maxEntityCramming")) {
            world.setGameRuleValue("maxEntityCramming", String.valueOf(plugin.getConfig().getInt("gamerules.max-entity-cramming", 18)));
            stats.setGamerulesApplied(stats.getGamerulesApplied() + 1);
        }
    }

    private void processDynamicAi(World world) {
        double disableDistance = plugin.getConfig().getDouble("optimizations.dynamic-ai.disable-distance", 56.0);
        double reenableDistance = plugin.getConfig().getDouble("optimizations.dynamic-ai.reenable-distance", 40.0);
        double disableDistanceSq = disableDistance * disableDistance;
        double reenableDistanceSq = reenableDistance * reenableDistance;

        Collection<? extends Player> players = world.getPlayers();
        if (players.isEmpty()) {
            return;
        }

        int disabledCount = 0;
        int processed = 0;
        int maxPerCycle = plugin.getConfig().getInt("optimizations.entity-scan-batch-limit", 2500);

        for (LivingEntity living : world.getLivingEntities()) {
            if (processed++ >= maxPerCycle) {
                break;
            }
            if (!(living instanceof Monster)) {
                continue;
            }
            if (plugin.isFeatureEnabled("safety-never-touch-named-mobs") && living.getCustomName() != null) {
                continue;
            }

            double closestSq = closestPlayerDistanceSquared(living.getLocation(), players);
            if (closestSq > disableDistanceSq && living.hasAI()) {
                living.setAI(false);
            } else if (closestSq < reenableDistanceSq && !living.hasAI()) {
                living.setAI(true);
            }

            if (!living.hasAI()) {
                disabledCount++;
            }
        }

        stats.setEntitiesWithoutAi(stats.getEntitiesWithoutAi() + disabledCount);
    }

    private void processItemMerge(World world) {
        double radius = plugin.getConfig().getDouble("optimizations.item-merge.radius", 2.0);
        int minAmount = Math.max(1, plugin.getConfig().getInt("optimizations.item-merge.min-amount", 1));
        int merged = 0;

        List<Item> items = new ArrayList<Item>(world.getEntitiesByClass(Item.class));
        for (int i = 0; i < items.size(); i++) {
            Item base = items.get(i);
            if (!base.isValid() || base.isDead()) {
                continue;
            }

            ItemStack baseStack = base.getItemStack();
            if (baseStack == null || baseStack.getAmount() < minAmount) {
                continue;
            }

            for (Entity nearby : base.getNearbyEntities(radius, radius, radius)) {
                if (!(nearby instanceof Item)) {
                    continue;
                }

                Item other = (Item) nearby;
                if (other.equals(base) || !other.isValid() || other.isDead()) {
                    continue;
                }

                ItemStack otherStack = other.getItemStack();
                if (!canMerge(baseStack, otherStack)) {
                    continue;
                }

                int maxStack = baseStack.getMaxStackSize();
                int free = maxStack - baseStack.getAmount();
                if (free <= 0) {
                    break;
                }

                int transfer = Math.min(free, otherStack.getAmount());
                baseStack.setAmount(baseStack.getAmount() + transfer);
                otherStack.setAmount(otherStack.getAmount() - transfer);
                merged += transfer;

                base.setItemStack(baseStack);

                if (otherStack.getAmount() <= 0) {
                    other.remove();
                } else {
                    other.setItemStack(otherStack);
                }
            }
        }

        stats.setItemsMerged(stats.getItemsMerged() + merged);
    }

    private void cleanupArrows(World world) {
        int maxTicks = plugin.getConfig().getInt("cleanup.arrows-max-age-ticks", 1200);
        int removed = 0;
        int maxPerCycle = plugin.getConfig().getInt("optimizations.projectile-scan-batch-limit", 1000);
        int processed = 0;

        for (Arrow arrow : world.getEntitiesByClass(Arrow.class)) {
            if (processed++ >= maxPerCycle) {
                break;
            }
            if (arrow.getTicksLived() > maxTicks) {
                arrow.remove();
                removed++;
            }
        }

        stats.setCleanedEntities(stats.getCleanedEntities() + removed);
    }

    private void cleanupExpOrbs(World world) {
        int maxTicks = plugin.getConfig().getInt("cleanup.exp-orb-max-age-ticks", 600);
        int removed = 0;

        for (ExperienceOrb orb : world.getEntitiesByClass(ExperienceOrb.class)) {
            if (orb.getTicksLived() > maxTicks) {
                orb.remove();
                removed++;
            }
        }

        stats.setCleanedEntities(stats.getCleanedEntities() + removed);
    }

    private boolean canMerge(ItemStack a, ItemStack b) {
        if (a == null || b == null) {
            return false;
        }
        return a.isSimilar(b) && a.getAmount() < a.getMaxStackSize();
    }

    private double closestPlayerDistanceSquared(Location location, Collection<? extends Player> players) {
        double min = Double.MAX_VALUE;
        for (Player player : players) {
            double current = player.getLocation().distanceSquared(location);
            if (current < min) {
                min = current;
            }
        }
        return min;
    }
}
