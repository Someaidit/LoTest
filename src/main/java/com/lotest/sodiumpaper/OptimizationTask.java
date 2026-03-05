package com.lotest.sodiumpaper;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
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

    public OptimizationTask(SodiumPaperPlugin plugin) {
        this.plugin = plugin;
    }

    public OptimizationStats getStats() {
        return stats;
    }

    @Override
    public void run() {
        stats.reset();

        boolean aiEnabled = plugin.getConfig().getBoolean("optimizations.dynamic-ai.enabled", true);
        boolean itemMergeEnabled = plugin.getConfig().getBoolean("optimizations.item-merge.enabled", true);

        for (World world : plugin.getServer().getWorlds()) {
            if (aiEnabled) {
                processDynamicAi(world);
            }
            if (itemMergeEnabled) {
                processItemMerge(world);
            }
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

        for (LivingEntity living : world.getLivingEntities()) {
            if (!(living instanceof Monster)) {
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
        int merged = 0;

        List<Item> items = new ArrayList<Item>(world.getEntitiesByClass(Item.class));
        for (int i = 0; i < items.size(); i++) {
            Item base = items.get(i);
            if (!base.isValid() || base.isDead()) {
                continue;
            }

            ItemStack baseStack = base.getItemStack();
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
