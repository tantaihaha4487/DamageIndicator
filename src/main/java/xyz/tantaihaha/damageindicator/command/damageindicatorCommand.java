package xyz.tantaihaha.damageindicator.command;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NullMarked;
import xyz.tantaihaha.damageindicator.config.ConfigFile;
import xyz.tantaihaha.damageindicator.config.ConfigManager;
import xyz.tantaihaha.damageindicator.utils.ArmorStandName;

import java.util.Collection;
import java.util.List;

import static xyz.tantaihaha.damageindicator.config.ConfigManager.getDamageIndicatorFormat;
import static xyz.tantaihaha.damageindicator.config.ConfigManager.getIgnoreWorlds;

@NullMarked
public class damageindicatorCommand implements BasicCommand {

    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, String[] args) {
        if (args.length == 0) {
            commandSourceStack.getSender().sendMessage(ChatColor.GREEN + "DamageIndicator plugin is " + (ConfigManager.isPluginEnabled() ? "enabled" : "disabled") + ".");
            return;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            ConfigFile.load();
            commandSourceStack.getSender().sendMessage(ChatColor.GREEN + "DamageIndicator plugin configuration reloaded.");
            Bukkit.getLogger().info("DamageIndicator plugin configuration reloaded by command.");
        } else if (args[0].equalsIgnoreCase("listworld")) {
            StringBuilder worlds = new StringBuilder(ChatColor.GREEN + "Disabled worlds: ");
            if (getIgnoreWorlds().isEmpty()) {
                worlds.append(ChatColor.RED + "None");
            } else {
                ConfigManager.getIgnoreWorlds().forEach(world -> worlds.append(ChatColor.YELLOW).append(world.getName()).append(ChatColor.GREEN).append(", "));
                worlds.setLength(worlds.length() - 2); // Remove the last comma and space
            }
            commandSourceStack.getSender().sendMessage(worlds.toString());
        } else if (args[0].equalsIgnoreCase("listignore")) {
            StringBuilder ignoredEntities = new StringBuilder(ChatColor.GREEN + "Ignored entities: ");
            if (ConfigManager.getIgnoreEntities().isEmpty()) {
                ignoredEntities.append(ChatColor.RED + "None");
            } else {
                ConfigManager.getIgnoreEntities().forEach(entity -> ignoredEntities.append(ChatColor.YELLOW).append(entity.name()).append(ChatColor.GREEN).append(", "));
                ignoredEntities.setLength(ignoredEntities.length() - 2); // Remove the last comma and space
            }
            commandSourceStack.getSender().sendMessage(ignoredEntities.toString());
        } else if (args[0].equalsIgnoreCase("test")) {
        commandSourceStack.getSender().sendMessage(ArmorStandName.format("<aqua>Test damage indicator</aqua> <green>[{currenthealth}/{maxhealth}❤]</green>"));
        } else if (args[0].equalsIgnoreCase("testheal")) {
            commandSourceStack.getSender().sendMessage(ArmorStandName.format(ConfigManager.getHealthIndicatorFormat()));
        } else {
            commandSourceStack.getSender().sendMessage(ChatColor.RED + "Unknown command.");
        }

    }

    @Override
    public Collection<String> suggest(CommandSourceStack commandSourceStack, String[] args) {
        if (args.length == 0) {
            return List.of("reload", "listworld", "listignore", "test");

        }

        return Bukkit.getOnlinePlayers().stream()
                .map(Player::getName)
                .filter(name -> name.toLowerCase().startsWith(args[args.length - 1].toLowerCase()))
                .toList();
    }
}