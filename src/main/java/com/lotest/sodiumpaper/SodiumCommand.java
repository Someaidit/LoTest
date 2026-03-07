package com.lotest.sodiumpaper;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SodiumCommand implements CommandExecutor, TabCompleter {

    private final SodiumPaperPlugin plugin;

    public SodiumCommand(SodiumPaperPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || "status".equalsIgnoreCase(args[0])) {
            showStatus(sender);
            return true;
        }

        if ("features".equalsIgnoreCase(args[0])) {
            int page = 1;
            String filter = "all";
            if (args.length >= 2) {
                try {
                    page = Math.max(1, Integer.parseInt(args[1]));
                } catch (NumberFormatException ignored) {
                    filter = args[1].toLowerCase();
                }
            }
            if (args.length >= 3) {
                filter = args[2].toLowerCase();
            }
            showFeatures(sender, page, filter);
            return true;
        }

        if ("reload".equalsIgnoreCase(args[0])) {
            if (!sender.hasPermission("sodiumpaper.reload")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission.");
                return true;
            }

            plugin.reloadPlugin();
            sender.sendMessage(ChatColor.GREEN + "SodiumPaper config reloaded.");
            return true;
        }

        sender.sendMessage(ChatColor.YELLOW + "Usage: /" + label + " [status|features [page] [all|enabled|disabled]|reload]");
        return true;
    }

    private void showStatus(CommandSender sender) {
        OptimizationTask task = plugin.getOptimizationTask();
        OptimizationStats stats = task != null ? task.getStats() : new OptimizationStats();

        sender.sendMessage(ChatColor.AQUA + "[SodiumPaper] Optimization status");
        sender.sendMessage(ChatColor.GRAY + "Enabled features: " + ChatColor.WHITE + plugin.getEnabledFeatureCount() + ChatColor.GRAY + "/100");
        sender.sendMessage(ChatColor.GRAY + "Mobs with AI disabled: " + ChatColor.WHITE + stats.getEntitiesWithoutAi());
        sender.sendMessage(ChatColor.GRAY + "Items merged (last cycle): " + ChatColor.WHITE + stats.getItemsMerged());
        sender.sendMessage(ChatColor.GRAY + "Entities cleaned (last cycle): " + ChatColor.WHITE + stats.getCleanedEntities());
        sender.sendMessage(ChatColor.GRAY + "Gamerules applied (last cycle): " + ChatColor.WHITE + stats.getGamerulesApplied());
        sender.sendMessage(ChatColor.GRAY + "Worlds scanned/skipped: " + ChatColor.WHITE + stats.getWorldsScanned() + "/" + stats.getWorldsSkipped());
        sender.sendMessage(ChatColor.GRAY + "Cycle time: " + ChatColor.WHITE + stats.getCycleTimeMs() + "ms");
    }

    private void showFeatures(CommandSender sender, int page, String filter) {
        List<FeatureDefinition> filtered = new ArrayList<FeatureDefinition>();
        for (FeatureDefinition feature : FeatureCatalog.FEATURES) {
            boolean enabled = plugin.isFeatureEnabled(feature.getKey());
            if ("enabled".equals(filter) && !enabled) {
                continue;
            }
            if ("disabled".equals(filter) && enabled) {
                continue;
            }
            filtered.add(feature);
        }

        int perPage = 10;
        int totalPages = Math.max(1, (int) Math.ceil(filtered.size() / (double) perPage));
        int clampedPage = Math.min(page, totalPages);
        int start = (clampedPage - 1) * perPage;
        int end = Math.min(filtered.size(), start + perPage);

        sender.sendMessage(ChatColor.AQUA + "[SodiumPaper] Features (" + filter + ") page " + clampedPage + "/" + totalPages);
        if (filtered.isEmpty()) {
            sender.sendMessage(ChatColor.YELLOW + "No features matched this filter.");
            return;
        }

        for (int i = start; i < end; i++) {
            FeatureDefinition feature = filtered.get(i);
            boolean enabled = plugin.isFeatureEnabled(feature.getKey());
            ChatColor stateColor = enabled ? ChatColor.GREEN : ChatColor.RED;
            sender.sendMessage(stateColor + (enabled ? "[ON] " : "[OFF] ") + ChatColor.WHITE + feature.getKey() + ChatColor.GRAY + " - " + feature.getDescription());
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("status", "features", "reload");
        }
        if (args.length == 3 && "features".equalsIgnoreCase(args[0])) {
            return Arrays.asList("all", "enabled", "disabled");
        }
        return Collections.emptyList();
    }
}
