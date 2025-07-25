package xyz.tantaihaha.damageindicator.config;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;

public class ConfigManager {

    private static boolean isEnablePlugin;
    private static int indicatorLifetime; // ticks
    private static final ArrayList<World> blacklistWorlds = new ArrayList<>();
    private static final ArrayList<EntityType> blacklistEntities = new ArrayList<>();
    private static String healthIndicatorFormat;
    private static String damageIndicatorFormat;
    private static String criticalDamageIndicatorFormat;

    private static final String INDICATOR_LIFETIME = "indicator.lifetime";
    private static final String BLACKLIST_ENTITIES = "blacklist.entities";
    private static final String BLACKLIST_WORLDS = "blacklist.worlds";
    private static final String HEALTH_INDICATOR_FORMAT = "indicator.health-indicator-format";
    private static final String DAMAGE_INDICATOR_FORMAT = "indicator.damage-indicator-format";
    private static final String CRITICAL_DAMAGE_INDICATOR_FORMAT = "indicator.critical-damage-indicator-format";



    protected static void init(YamlConfiguration config) {
        // Load plugin enable configuration
        isEnablePlugin = config.getBoolean("enable", true);

        // Load indicator lifetime configuration
        indicatorLifetime = config.getInt(INDICATOR_LIFETIME, 12);
        if (indicatorLifetime < 1) {
            Bukkit.getLogger().warning("Indicator lifetime must be at least 1 tick. Setting to default value of 12 ticks.");
            indicatorLifetime = 12;
        }

        // Load blacklist
        // Blacklist entities
        blacklistEntities.clear();
        for (String entityName : config.getStringList(BLACKLIST_ENTITIES)) {
            EntityType entityType = EntityType.fromName(entityName);
            if (entityType != null) {
                blacklistEntities.add(entityType);
            } else {
                Bukkit.getLogger().warning("Entity type" + entityName + "specified in config.yml is not valid.");
            }
        }

        // Load blacklist
        // Blacklist worlds
        blacklistWorlds.clear();
        for (String worldName : config.getStringList(BLACKLIST_WORLDS)) {
            World world = Bukkit.getWorld(worldName);
            if (world != null) {
                blacklistWorlds.add(world);
            }
        }

        // Load health indicator format
        healthIndicatorFormat = config.getString(HEALTH_INDICATOR_FORMAT, "<aqua>+{healamount}</aqua> <green>[{currenthealth}/{maxhealth}❤]</green>");
        if (healthIndicatorFormat.isEmpty()) {
            Bukkit.getLogger().warning("Health indicator format is not set or empty. Using default format.");
            healthIndicatorFormat = "<aqua>+{healamount}</aqua> <green>[{currenthealth}/{maxhealth}❤]</green>";
        }

        // Load damage indicator format
        damageIndicatorFormat = config.getString(DAMAGE_INDICATOR_FORMAT, "<red>-{damage}</red> <green>[{currenthealth}/{maxhealth}❤]</green>");
        if (damageIndicatorFormat.isEmpty()) {
            Bukkit.getLogger().warning("Damage indicator format is not set or empty. Using default format.");
            damageIndicatorFormat = "<red>-{damage}</red> <green>[{currenthealth}/{maxhealth}❤]</green>";
        }

        //Load critical damage indicator format
        criticalDamageIndicatorFormat = config.getString(CRITICAL_DAMAGE_INDICATOR_FORMAT, "<gold><b>✧</b></gold> <red>-{damage}</red> <green>[{currenthealth}/{maxhealth}❤]</green>");
        if (criticalDamageIndicatorFormat.isEmpty()) {
            Bukkit.getLogger().warning("Critical damage indicator format is not set or empty. Using default format.");
            criticalDamageIndicatorFormat = "<<gold><b>✧</b></gold> <red>-{damage}</red> <green>[{currenthealth}/{maxhealth}❤]</green>";
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

    public static String getCriticalDamageIndicatorFormat() {
        return criticalDamageIndicatorFormat;
    }

    public static ArrayList<World> getBlacklistWorlds() {
        return blacklistWorlds;
    }

    public static ArrayList<EntityType> getBlacklistEntities() {
        return blacklistEntities;
    }
}
