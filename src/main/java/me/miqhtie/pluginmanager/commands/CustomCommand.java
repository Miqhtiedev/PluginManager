package me.miqhtie.pluginmanager.commands;

import me.miqhtie.pluginmanager.PluginManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.bukkit.ChatColor.*;

public class CustomCommand {
    private String name;
    private CustomCommandExecutor executor;
    private String permission;
    private boolean playerOnly;
    private String description;
    private Set<String> aliases;
    private String usage;
    private int minArgs = -1;
    private int maxArgs = -1;

    public CustomCommand(String name) {
        this.name = name;
        this.aliases = new HashSet<>();
    }

    public void execute(CommandSender sender, String[] args, PluginManager pluginManager) {
        if (permission != null && !sender.hasPermission(permission)) {
            pluginManager.sendMessage(sender, RED + "Missing permissions!");
            return;
        }

        if (playerOnly && !(sender instanceof Player)) {
            pluginManager.sendMessage(sender, RED + "Only players can run this command!");
            return;
        }

        if (minArgs != -1 && args.length < minArgs) {
            pluginManager.sendMessage(sender, RED + "Invalid arguments.\nMinimum: " + minArgs);
            if (usage != null) {
                pluginManager.sendMessage(sender, RED + "Usage: " + usage);
            }
            return;
        }

        if (maxArgs != -1 && args.length > maxArgs) {
            pluginManager.sendMessage(sender, RED + "Invalid arguments.\nMaximum: " + maxArgs);
            if (usage != null) {
                pluginManager.sendMessage(sender, RED + "Usage: " + usage);
            }
            return;
        }

        executor.run(sender, args);
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public boolean isPlayerOnly() {
        return playerOnly;
    }

    public void setPlayerOnly(boolean playerOnly) {
        this.playerOnly = playerOnly;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getAliases() {
        return aliases;
    }

    public void setAliases(Set<String> aliases) {
        this.aliases = aliases;
    }

    public void setAliases(String... aliases) {
        this.aliases = Stream.of(aliases).map(String::toLowerCase).collect(Collectors.toSet());;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public int getMinArgs() {
        return minArgs;
    }

    public void setMinArgs(int minArgs) {
        this.minArgs = minArgs;
    }

    public int getMaxArgs() {
        return maxArgs;
    }

    public void setMaxArgs(int maxArgs) {
        this.maxArgs = maxArgs;
    }

    public CustomCommandExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(CustomCommandExecutor executor) {
        this.executor = executor;
    }

    public String getName() {
        return name;
    }
}
