package xyz.tantaihaha.damageindicator.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import xyz.tantaihaha.damageindicator.config.ConfigManager;

public class ArmorStandName {

    /*
    *  TODO: Add support for custom placeholders in the critical hit format
    * */
    private static String applyPlaceholders(String format, double damage, double healAmount, double currentHealth, double maxHealth) {
        return format
                .replace("{damage}", String.format("%.1f", damage))
                .replace("{healAmount}", String.format("%.1f", healAmount))
                .replace("{currentHealth}", String.format("%.1f", currentHealth))
                .replace("{maxHealth}", String.format("%.1f", maxHealth));
    }

    private static String applyColorTags(String input) {
        // Simple implementation for <color>...</color> tags
        String[][] colors = {
            {"<red>", ChatColor.RED.toString()},
            {"</red>", ChatColor.RESET.toString()},
            {"<green>", ChatColor.GREEN.toString()},
            {"</green>", ChatColor.RESET.toString()},
            {"<aqua>", ChatColor.AQUA.toString()},
            {"</aqua>", ChatColor.RESET.toString()}
        };
        for (String[] color : colors) {
            input = input.replace(color[0], color[1]);
        }
        return input;
    }

    public static String damageFormat(Double finalDamage, Entity entity) {
        if (!(entity instanceof Damageable damageable)) {
            return applyColorTags(applyPlaceholders("<red>{damage}</red>", finalDamage, 0, 0, 0));
        }
        double currentHealth = Math.max(0, damageable.getHealth());
        double maxHealth = damageable.getMaxHealth();
        String format = ConfigManager.getDamageIndicatorFormat();
        if (finalDamage > currentHealth) {
            // Overkill, show 0 health
            format = format.replace("{currentHealth}", "0");
        }
        return applyColorTags(applyPlaceholders(format, finalDamage, 0, currentHealth, maxHealth));
    }

    public static String healFormat(Double healAmount, Entity entity) {
        if (!(entity instanceof Damageable damageable)) {
            return applyColorTags(applyPlaceholders("<aqua>{healAmount}</aqua>", 0, healAmount, 0, 0));
        }
        double currentHealth = damageable.getHealth();
        double maxHealth = damageable.getMaxHealth();
        String format = ConfigManager.getHealthIndicatorFormat();
        return applyColorTags(applyPlaceholders(format, 0, healAmount, currentHealth, maxHealth));
    }
}
