package me.miqhtie.pluginmanager;

import me.miqhtie.pluginmanager.commands.CommandRegister;
import me.miqhtie.pluginmanager.commands.CustomCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class PluginManager {
    private JavaPlugin plugin;
    private String prefix;

    public PluginManager(JavaPlugin plugin) {
        this.plugin = plugin;

        // Default prefix
        prefix = ChatColor.translateAlternateColorCodes('&', String.format("&b[%s]&r", plugin.getName()));
    }

    public void registerCommand(CustomCommand command) {
        CommandMap map = null;
        Field field;

        try {
            field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            map = (CommandMap)field.get(Bukkit.getServer());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        if (map != null && map.getCommand(command.getName()) == null) {
            map.register(prefix, new CommandRegister(command, this));
        }
    }

    public void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(getPrefix() + " " + message);
    }

    public JavaPlugin getPluginInstance() {
        return plugin;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
