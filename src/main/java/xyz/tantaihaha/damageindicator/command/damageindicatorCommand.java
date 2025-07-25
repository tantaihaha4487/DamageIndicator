package xyz.tantaihaha.damageindicator.command;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NullMarked;
import xyz.tantaihaha.damageindicator.config.ConfigFile;
import xyz.tantaihaha.damageindicator.config.ConfigLogger;
import xyz.tantaihaha.damageindicator.config.ConfigManager;

import java.util.Collection;
import java.util.List;

@NullMarked
public class damageindicatorCommand implements BasicCommand {

    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, String[] args) {
        if (!commandSourceStack.getSender().hasPermission("damageindicator.use")) {
            commandSourceStack.getSender().sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return;
        }

        if (args.length == 0) {
            commandSourceStack.getSender().sendMessage(ChatColor.GREEN + "DamageIndicator plugin is " + (ConfigManager.isPluginEnabled() ? "enabled" : "disabled") + ".");
            return;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            try {
                ConfigFile.load();
            } catch (Exception e) {
                commandSourceStack.getSender().sendMessage(ChatColor.RED + "Failed to reload configuration: " + e.getMessage());
                Bukkit.getLogger().severe("Error reloading DamageIndicator plugin configuration: " + e.getMessage());
                return;
            }
            commandSourceStack.getSender().sendMessage(ChatColor.GREEN + "DamageIndicator plugin configuration reloaded.");
            Bukkit.getLogger().info("DamageIndicator plugin configuration reloaded by command.");
            ConfigLogger.logIgnoredEntity();
            ConfigLogger.logIgnoredWorld();
        } else {
            commandSourceStack.getSender().sendMessage(ChatColor.RED + "Unknown command.");
        }

    }

    @Override
    public Collection<String> suggest(CommandSourceStack commandSourceStack, String[] args) {
        if (args.length == 0) {
            return List.of("reload");

        }
        return Bukkit.getOnlinePlayers().stream()
                .map(Player::getName)
                .filter(name -> name.toLowerCase().startsWith(args[args.length - 1].toLowerCase()))
                .toList();
    }
}