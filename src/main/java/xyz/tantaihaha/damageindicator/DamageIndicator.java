package xyz.tantaihaha.damageindicator;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.tantaihaha.damageindicator.command.damageindicatorCommand;
import xyz.tantaihaha.damageindicator.config.ConfigFile;
import xyz.tantaihaha.damageindicator.config.ConfigManager;
import xyz.tantaihaha.damageindicator.listener.EntityListener;
import xyz.tantaihaha.damageindicator.listener.PlayerListener;
import xyz.tantaihaha.damageindicator.manager.ArmorStandManager;

public final class DamageIndicator extends JavaPlugin {

    @Override
    public void onEnable() {
        // Load configuration
        ConfigFile.load();

        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register("damageindicator", new damageindicatorCommand());
        });

        if (!ConfigManager.isPluginEnabled()) {
            getServer().getLogger().info("DamageIndicator plugin is disabled by config.yml");
            getServer().getLogger().info("Please enable it in config.yml and reload or restart server to use this plugin.");
            return;
        }

        getServer().getLogger().info("DamageIndicator plugin is starting up!");

        // Register events
        getServer().getPluginManager().registerEvents(new EntityListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

    }

    @Override
    public void onDisable() {
        ArmorStandManager.removeAllArmorStands();
    }

    public static DamageIndicator getInstance() {
        return getPlugin(DamageIndicator.class);
    }
}
