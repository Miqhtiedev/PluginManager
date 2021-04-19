package me.miqhtie.pluginmanager.commands;

import me.miqhtie.pluginmanager.PluginManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

public class CommandRegister extends Command implements PluginIdentifiableCommand {
    private final CustomCommand command;
    private final PluginManager manager;

    public CommandRegister(CustomCommand command, PluginManager manager) {
        super(command.getName());
        this.command = command;
        this.manager = manager;
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase(s) || command.getAliases().contains(s.toLowerCase())) {
            command.execute(commandSender, strings, manager);
            return true;
        }
        return false;
    }

    @Override
    public Plugin getPlugin() {
        return manager.getPluginInstance();
    }
}
