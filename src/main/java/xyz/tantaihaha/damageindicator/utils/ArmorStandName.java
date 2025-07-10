package xyz.tantaihaha.damageindicator.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import xyz.tantaihaha.damageindicator.config.ConfigManager;

public class ArmorStandName {

    public static @NotNull Component format(String input) {
        String formated = input
                .replace("{damage}", "<damage>")
                .replace("{healamount}", "<healamount>")
                .replace("{currenthealth}", "<currenthealth>")
                .replace("{maxhealth}", "<maxhealth>")
                .replace("{mobname}", "<mobname>");
        return MiniMessage.miniMessage().deserialize(formated);
    }

    public static @NotNull Component format(String input, TagResolver... tagResolvers) {
        String formated = input
                .replace("{damage}", "<damage>")
                .replace("{healamount}", "<healamount>")
                .replace("{currenthealth}", "<currenthealth>")
                .replace("{maxhealth}", "<maxhealth>")
                .replace("{mobname}", "<mobname>");
        return MiniMessage.miniMessage().deserialize(formated, tagResolvers);
    }

    public static Component damageFormat(Double finalDamage, Entity entity) {
        if (!(entity instanceof Damageable damageable)) throw new IllegalArgumentException("Entity must be an instance of Damageable");

        String mobName = entity.getName().isEmpty() ? entity.getType().name() : entity.getName();
        String finalDamageString = String.format("%.1f", finalDamage);
        double healthAfterDamage = damageable.getHealth() - finalDamage;
        String currentHealthString = String.format("%.1f", Math.max(0, healthAfterDamage));
        String maxHealthString = String.format("%.1f", damageable.getMaxHealth());

        return format(ConfigManager.getDamageIndicatorFormat(),
                Placeholder.parsed("mobname", mobName),
                Placeholder.parsed("damage", finalDamageString),
                Placeholder.parsed("currenthealth", currentHealthString),
                Placeholder.parsed("maxhealth", maxHealthString));
    }

    public static Component criticalDamageFormat(Double finalDamage, Entity entity) {
        if (!(entity instanceof Damageable damageable)) throw new IllegalArgumentException("Entity must be an instance of Damageable");

        String mobName = entity.getName().isEmpty() ? entity.getType().name() : entity.getName();
        String finalDamageString = String.format("%.1f", finalDamage);
        double healthAfterDamage = damageable.getHealth() - finalDamage;
        String currentHealthString = String.format("%.1f", Math.max(0, healthAfterDamage));
        String maxHealthString = String.format("%.1f", damageable.getMaxHealth());

        return format(ConfigManager.getCriticalDamageIndicatorFormat(),
                Placeholder.parsed("mobname", mobName),
                Placeholder.parsed("damage", finalDamageString),
                Placeholder.parsed("currenthealth", currentHealthString),
                Placeholder.parsed("maxhealth", maxHealthString));
    }

    public static Component healFormat(Double healAmount, Entity entity) {
        if (!(entity instanceof Damageable damageable)) throw new IllegalArgumentException("Entity must be an instance of Damageable");

        String mobName = entity.getName().isEmpty() ? entity.getType().name() : entity.getName();
        String healAmountString = String.format("%.1f", healAmount);
        String currentHealthString = String.format("%.1f", damageable.getHealth());
        String maxHealthString = String.format("%.1f", damageable.getMaxHealth());

        return format(ConfigManager.getHealthIndicatorFormat(),
                Placeholder.parsed("mobname", mobName),
                Placeholder.parsed("healamount", healAmountString),
                Placeholder.parsed("currenthealth", currentHealthString),
                Placeholder.parsed("maxhealth", maxHealthString));
    }

}