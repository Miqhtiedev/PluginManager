package me.miqhtie.pluginmanager.commands;

public class CommandBuilder {
    private final CustomCommand command;

    public CommandBuilder(String name) {
        this.command = new CustomCommand(name);
    }

    public CommandBuilder setRequiredPermission(String permission) {
        command.setPermission(permission);
        return this;
    }

    public CommandBuilder playerOnly() {
        command.setPlayerOnly(true);
        return this;
    }

    public CommandBuilder setAliases(String... aliases) {
        command.setAliases(aliases);
        return this;
    }

    public CommandBuilder setUsage(String usage) {
        command.setUsage(usage);
        return this;
    }

    public CommandBuilder setDescription(String description) {
        command.setDescription(description);
        return this;
    }

    public CommandBuilder setMinArgs(int minArgs) {
        command.setMinArgs(minArgs);
        return this;
    }

    public CommandBuilder setMaxArgs(int maxArgs) {
        command.setMaxArgs(maxArgs);
        return this;
    }

    public CommandBuilder setExecutor(CustomCommandExecutor executor) {
        command.setExecutor(executor);
        return this;
    }

    public CustomCommand build() {
        return command;
    }
}
