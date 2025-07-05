package xyz.tantaihaha.damageindicator.config;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;

public class ConfigManager {

    private static boolean isEnablePlugin;
    private static int indicatorLifetime; // ticks
    private static ArrayList<World> ignoreWorlds = new ArrayList<>();
    private static ArrayList<EntityType> ignoreEntities = new ArrayList<>();
    private static String healthIndicatorFormat;
    private static String damageIndicatorFormat;

    protected static void init(YamlConfiguration config) {
        // Load plugin enable configuration
        isEnablePlugin = config.getBoolean("enable", true);

        // Load indicator lifetime configuration
        indicatorLifetime = config.getInt("indicator.lifetime", 12);
        if (indicatorLifetime < 1) {
            Bukkit.getLogger().warning("Indicator lifetime must be at least 1 tick. Setting to default value of 12 ticks.");
            indicatorLifetime = 12;
        }

        // Load ignored entities
        ignoreEntities.clear();
        for (String entityName : config.getStringList("ignore.entities")) {
            EntityType entityType = EntityType.fromName(entityName);
            if (entityType != null) {
                ignoreEntities.add(entityType);
            } else {
                Bukkit.getLogger().warning("Entity type '" + entityName + "' specified in config.yml is not valid.");
            }
        }

        // Load ignored worlds
        ignoreWorlds.clear();
        for (String worldName : config.getStringList("ignore.worlds")) {
            World world = Bukkit.getWorld(worldName);
            if (world != null) {
                ignoreWorlds.add(world);
            }
        }

        // Load health indicator format
        healthIndicatorFormat = config.getString("indicator.health-indicator-format", "<aqua>+{healamount}</aqua> <green>[{currenthealth}/{maxhealth}❤]</green>");
        if (healthIndicatorFormat.isEmpty()) {
            Bukkit.getLogger().warning("Health indicator format is not set or empty. Using default format.");
            healthIndicatorFormat = "<aqua>+{healamount}</aqua> <green>[{currenthealth}/{maxhealth}❤]</green>";
        }

        // Load damage indicator format
        damageIndicatorFormat = config.getString("indicator.damage-indicator-format", "<red>-{damage}</red> <green>[{currenthealth}/{maxhealth}❤]</green>");
        if (damageIndicatorFormat.isEmpty()) {
            Bukkit.getLogger().warning("Damage indicator format is not set or empty. Using default format.");
            damageIndicatorFormat = "<red>-{damage}</red> <green>[{currenthealth}/{maxhealth}❤]</green>";
        }

    }

    public static boolean isPluginEnabled() {
        return isEnablePlugin;
    }

    public static int getIndicatorLifetime() {
        return indicatorLifetime;
    }

    public static String getHealthIndicatorFormat() {
        return healthIndicatorFormat;
    }

    public static String getDamageIndicatorFormat() {
        return damageIndicatorFormat;
    }

    public static ArrayList<World> getIgnoreWorlds() {
        return ignoreWorlds;
    }

    public static ArrayList<EntityType> getIgnoreEntities() {
        return ignoreEntities;
    }
}
