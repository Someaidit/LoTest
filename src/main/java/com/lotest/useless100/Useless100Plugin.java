package com.lotest.useless100;

import org.bukkit.plugin.java.JavaPlugin;

public class Useless100Plugin extends JavaPlugin {

    private FeatureRegistry featureRegistry;

    @Override
    public void onEnable() {
        this.featureRegistry = new FeatureRegistry();
        UselessCommand command = new UselessCommand(featureRegistry);
        getCommand("useless").setExecutor(command);
        getCommand("useless").setTabCompleter(command);
        getLogger().info("Useless100 enabled with " + featureRegistry.size() + " useless features.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Useless100 disabled.");
    }
}
