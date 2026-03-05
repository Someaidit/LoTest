package com.lotest.sodiumpaper;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SodiumPaperPlugin extends JavaPlugin {

    private OptimizationTask optimizationTask;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        startOptimizationTask();

        SodiumCommand command = new SodiumCommand(this);
        getCommand("sodiumpaper").setExecutor(command);
        getCommand("sodiumpaper").setTabCompleter(command);

        getLogger().info("SodiumPaper enabled. Running server-side optimization routines.");
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
        if (optimizationTask != null) {
            optimizationTask.cancel();
        }
        startOptimizationTask();
    }

    public OptimizationTask getOptimizationTask() {
        return optimizationTask;
    }

    private void startOptimizationTask() {
        int periodTicks = Math.max(10, getConfig().getInt("optimizations.tick-period", 20));
        optimizationTask = new OptimizationTask(this);
        Bukkit.getScheduler().runTaskTimer(this, optimizationTask, periodTicks, periodTicks);
    }
}
