package me.miqhtie.pluginmanager.commands;

import org.bukkit.command.CommandSender;

@FunctionalInterface
public interface CustomCommandExecutor {
    void run(CommandSender sender, String[] args);
}
