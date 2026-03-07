package com.lotest.sodiumpaper;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class SodiumPaperPlugin extends JavaPlugin {

    private OptimizationTask optimizationTask;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        initializeFeatureConfig();
        startOptimizationTask();

        SodiumCommand command = new SodiumCommand(this);
        if (getCommand("sodiumpaper") != null) {
            getCommand("sodiumpaper").setExecutor(command);
            getCommand("sodiumpaper").setTabCompleter(command);
        }

        getLogger().info("SodiumPaper enabled with " + FeatureCatalog.FEATURES.size() + " features.");
    }

    @Override
    public void onDisable() {
        if (optimizationTask != null) {
            optimizationTask.cancel();
        }
        getLogger().info("SodiumPaper disabled.");
    }

    public void reloadPlugin() {
        reloadConfig();
        initializeFeatureConfig();
        if (optimizationTask != null) {
            optimizationTask.cancel();
        }
        startOptimizationTask();
    }

    public OptimizationTask getOptimizationTask() {
        return optimizationTask;
    }

    public boolean isFeatureEnabled(String key) {
        return getConfig().getBoolean("features." + key + ".enabled", false);
    }

    public String getFeatureDescription(String key) {
        return getConfig().getString("features." + key + ".description", "No description");
    }

    public int getEnabledFeatureCount() {
        int enabled = 0;
        for (FeatureDefinition definition : FeatureCatalog.FEATURES) {
            if (isFeatureEnabled(definition.getKey())) {
                enabled++;
            }
        }
        return enabled;
    }

    private void startOptimizationTask() {
        int periodTicks = Math.max(10, getConfig().getInt("optimizations.tick-period", 20));
        optimizationTask = new OptimizationTask(this);
        int taskId = Bukkit.getScheduler().runTaskTimer(this, optimizationTask, periodTicks, periodTicks).getTaskId();
        optimizationTask.setTaskId(taskId);
    }

    private void initializeFeatureConfig() {
        FileConfiguration config = getConfig();
        Set<String> known = new HashSet<String>();
        for (FeatureDefinition definition : FeatureCatalog.FEATURES) {
            known.add(definition.getKey());
            String base = "features." + definition.getKey();
            if (!config.isSet(base + ".enabled")) {
                config.set(base + ".enabled", definition.isDefaultEnabled());
            }
            if (!config.isSet(base + ".description")) {
                config.set(base + ".description", definition.getDescription());
            }
        }

        if (config.isConfigurationSection("features")) {
            for (String key : config.getConfigurationSection("features").getKeys(false)) {
                if (!known.contains(key)) {
                    getLogger().warning("Unknown feature key in config: " + key);
                }
            }
        }
        saveConfig();
    }
}
