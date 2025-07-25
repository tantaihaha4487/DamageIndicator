package xyz.tantaihaha.damageindicator.listener;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import xyz.tantaihaha.damageindicator.config.ConfigManager;
import xyz.tantaihaha.damageindicator.manager.ArmorStandManager;
import xyz.tantaihaha.damageindicator.utils.ArmorStandName;

import java.util.List;

public class EntityListener implements Listener {

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event) {

        // ignore if player is damage entity
        if (event instanceof EntityDamageByEntityEvent e) if (e.getDamager() instanceof Player) return;
        if (ArmorStandManager.isIgnoreType(event.getEntity())) return;

        // Config ignores
        if (ConfigManager.getBlacklistEntities().contains(event.getEntityType())) return;
        if (ConfigManager.getBlacklistWorlds().contains(event.getEntity().getWorld())) return;

        Entity entity = event.getEntity();
        double finalDamage = event.getFinalDamage();
        Location location = entity.getLocation().add(ArmorStandManager.randomPosition(), entity.getHeight() + 0.03D, ArmorStandManager.randomPosition());
        Component component = ArmorStandName.damageFormat(finalDamage, entity);
        List<ArmorStand> armorStands = ArmorStandManager.spawnArmorStand(location, component);

        ArmorStandManager.removeArmorStandAfter(armorStands, ConfigManager.getIndicatorLifetime());
    }

    @EventHandler
    public void onEntityHealEvent(EntityRegainHealthEvent event) {
        if (ArmorStandManager.isIgnoreType(event.getEntity())) return;

        Entity entity = event.getEntity();
        double healAmount = event.getAmount();
        Location location = entity.getLocation().add(ArmorStandManager.randomPosition(), entity.getHeight() + 0.03D, ArmorStandManager.randomPosition());
        Component component = ArmorStandName.healFormat(healAmount, entity);
        List<ArmorStand> armorStands = ArmorStandManager.spawnArmorStand(location, component);
        ArmorStandManager.removeArmorStandAfter(armorStands, ConfigManager.getIndicatorLifetime());
    }

}