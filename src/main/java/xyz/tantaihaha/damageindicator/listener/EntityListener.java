package xyz.tantaihaha.damageindicator.listener;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import xyz.tantaihaha.damageindicator.manager.ArmorStandManager;
import xyz.tantaihaha.damageindicator.utils.ArmorStandName;

public class EntityListener implements Listener {

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event) {

        // ignore if player is damage entity
        if (event instanceof EntityDamageByEntityEvent e) if(e.getDamager() instanceof Player) return;
        if(ArmorStandManager.isIgnoreType(event.getEntity())) return;

        Entity entity = event.getEntity();
        double finalDamage = event.getFinalDamage();
        Location location = entity.getLocation().add(0.100, entity.getHeight()+ 0.03D, 0.100);
        ArmorStand armorStand = ArmorStandManager.spawnArmorStand(location, ArmorStandName.damageFormat(finalDamage, entity));

        ArmorStandManager.removeArmorStandAfter(armorStand, 12); // Remove armor stand after 10 ticks (0.5 seconds)
    }

    @EventHandler
    public void onEntityHealEvent(EntityRegainHealthEvent event) {
        if(ArmorStandManager.isIgnoreType(event.getEntity())) return;

        Entity entity = event.getEntity();
        double healAmount = event.getAmount();
        Location location = entity.getLocation().add(0.100, entity.getHeight() + 0.03D, 0.100);
        ArmorStand armorStand = ArmorStandManager.spawnArmorStand(location, ArmorStandName.healFormat(healAmount, entity));

        ArmorStandManager.removeArmorStandAfter(armorStand, 12);
    }

}
