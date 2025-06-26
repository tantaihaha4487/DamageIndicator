package xyz.tantaihaha.damageIndicator;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.tantaihaha.damageIndicator.listener.EntityListener;
import xyz.tantaihaha.damageIndicator.listener.PlayerListener;
import xyz.tantaihaha.damageIndicator.manager.ArmorStandManager;

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
