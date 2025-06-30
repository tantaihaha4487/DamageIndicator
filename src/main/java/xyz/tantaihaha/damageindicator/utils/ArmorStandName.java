package xyz.tantaihaha.damageindicator.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;

public class ArmorStandName {

    public static String damageFormat(Double finalDamage, Entity entity) {
        if (!(entity instanceof Damageable damageable)) {
            return ChatColor.RED + String.format("%.1f", finalDamage);
        }
        String finalDamageString = String.format("%.1f", finalDamage);
        String currentHealthString = String.format("%.1f", damageable.getHealth());
        String MaxHealthString = String.format("%.1f", damageable.getMaxHealth());

        // Damage is overkill
        if (finalDamage > damageable.getHealth()) return ChatColor.RED + finalDamageString + ChatColor.GREEN + " [0/" + MaxHealthString + "❤]";

        return ChatColor.RED + "-" + finalDamageString + ChatColor.GREEN + " [" + currentHealthString + "/" + MaxHealthString + "❤]";
    }

    public static String healFormat(Double healAmount, Entity entity) {
        if (!(entity instanceof Damageable damageable)) {
            return ChatColor.AQUA + String.format("%.1f", healAmount);
        }
        String healAmountString = String.format("%.1f", healAmount);
        String currentHealthString = String.format("%.1f", damageable.getHealth());
        String MaxHealthString = String.format("%.1f", damageable.getMaxHealth());

        return ChatColor.AQUA + "+" + healAmountString + ChatColor.GREEN + " [" + currentHealthString + "/" + MaxHealthString + "❤]";
    }
}
