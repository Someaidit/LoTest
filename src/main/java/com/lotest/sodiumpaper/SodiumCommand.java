package com.lotest.sodiumpaper;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

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
            OptimizationTask task = plugin.getOptimizationTask();
            OptimizationStats stats = task != null ? task.getStats() : new OptimizationStats();
            sender.sendMessage(ChatColor.AQUA + "[SodiumPaper] Server-side optimization status");
            sender.sendMessage(ChatColor.GRAY + "Mobs with AI disabled: " + ChatColor.WHITE + stats.getEntitiesWithoutAi());
            sender.sendMessage(ChatColor.GRAY + "Items merged (last cycle): " + ChatColor.WHITE + stats.getItemsMerged());
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

        sender.sendMessage(ChatColor.YELLOW + "Usage: /" + label + " [status|reload]");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("status", "reload");
        }
        return Collections.emptyList();
    }
}
