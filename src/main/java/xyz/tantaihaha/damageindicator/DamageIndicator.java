package xyz.tantaihaha.damageindicator;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.tantaihaha.damageindicator.listener.EntityListener;
import xyz.tantaihaha.damageindicator.listener.PlayerListener;
import xyz.tantaihaha.damageindicator.manager.ArmorStandManager;

public final class DamageIndicator extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getLogger().info("DamageIndicator plugin is starting up!");
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
