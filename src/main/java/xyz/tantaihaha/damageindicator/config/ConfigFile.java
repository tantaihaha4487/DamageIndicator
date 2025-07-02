package xyz.tantaihaha.damageindicator.config;

import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.tantaihaha.damageindicator.DamageIndicator;

import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static xyz.tantaihaha.damageindicator.config.ConfigManager.init;

public class ConfigFile {

    protected final static String CONFIG_FILE_NAME = "config.yml";
    private static YamlConfiguration config;

    public ConfigFile() {
        // Default constructor
    }

    public static void load() {
        File configFile = new File(DamageIndicator.getInstance().getDataFolder(), CONFIG_FILE_NAME);

        // Check if the config file exists, if not, copy default from the plugin resources
        if (!configFile.exists()) DamageIndicator.getInstance().saveResource(CONFIG_FILE_NAME, false);

        config = new YamlConfiguration();
        config.options().parseComments(true);

        try {
            config.load(configFile);
            init(config);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static YamlConfiguration getConfig() {
        return config;
    }


}
