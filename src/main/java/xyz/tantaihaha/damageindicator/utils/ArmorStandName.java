package xyz.tantaihaha.damageindicator.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import xyz.tantaihaha.damageindicator.config.ConfigManager;

public class ArmorStandName {

    public static @NotNull Component format(String input) {
        return MiniMessage.miniMessage().deserialize(input);
    }

    public static @NotNull Component format(String input, TagResolver... tagResolvers) {
        return MiniMessage.miniMessage().deserialize(input, tagResolvers);
    }

    public static Component damageFormat(Double finalDamage, Entity entity) {
        if (!(entity instanceof Damageable damageable)) throw new IllegalArgumentException("Entity must be an instance of Damageable");
        String finalDamageString = String.format("%.1f", finalDamage);
        double healthAfterDamage = damageable.getHealth() - finalDamage;
        String currentHealthString = String.format("%.1f", Math.max(0, healthAfterDamage));
        String maxHealthString = String.format("%.1f", damageable.getMaxHealth());

        // Damage is overkill
         if(finalDamage >= damageable.getHealth()) {
             return format(ConfigManager.getDamageIndicatorFormat(),
                     Placeholder.parsed("damage", finalDamageString),
                     Placeholder.parsed("currenthealth", "0"),
                     Placeholder.parsed("maxhealth", maxHealthString));
         }

        return format(ConfigManager.getDamageIndicatorFormat(),
                Placeholder.parsed("damage", finalDamageString),
                Placeholder.parsed("currenthealth", currentHealthString),
                Placeholder.parsed("maxhealth", maxHealthString));

    }

    public static Component criticalDamageFormat(Double finalDamage, Entity entity) {
        return format("<gold><b>✧</b></gold> ").append(damageFormat(finalDamage, entity));
    }

    public static Component healFormat(Double healAmount, Entity entity) {
        if (!(entity instanceof Damageable damageable)) throw new IllegalArgumentException("Entity must be an instance of Damageable");

        String healAmountString = String.format("%.1f", healAmount);
        String currentHealthString = String.format("%.1f", damageable.getHealth());
        String maxHealthString = String.format("%.1f", damageable.getMaxHealth());
        return format(ConfigManager.getHealthIndicatorFormat(),
                Placeholder.parsed("healamount", healAmountString),
                Placeholder.parsed("currenthealth", currentHealthString),
                Placeholder.parsed("maxhealth", maxHealthString));
    }
}