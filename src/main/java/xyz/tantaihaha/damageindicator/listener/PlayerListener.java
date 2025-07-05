package xyz.tantaihaha.damageindicator.listener;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;
import xyz.tantaihaha.damageindicator.manager.ArmorStandManager;
import xyz.tantaihaha.damageindicator.utils.ArmorStandName;

public class PlayerListener implements Listener {
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        // if damaged by player
        if (!(event.getDamager() instanceof Player player)) return;
        if (ArmorStandManager.isIgnoreType(event.getEntity())) return;

        Entity entity = event.getEntity();

        boolean isCritical = !player.isOnGround()
                && !player.isInsideVehicle()
                && !player.isSprinting()
                && player.getFallDistance() > 0.0F
                && !player.hasPotionEffect(PotionEffectType.BLINDNESS)
                && player.getLocation().getBlock().getType() != Material.LADDER
                && player.getLocation().getBlock().getType() != Material.VINE
                && event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK;

        double finalDamage = event.getFinalDamage();
        Location location = entity.getLocation().add(ArmorStandManager.randomPosition(), entity.getHeight() + 0.03D, ArmorStandManager.randomPosition());
        String displayText = LegacyComponentSerializer.legacySection().serialize(ArmorStandName.damageFormat(finalDamage, entity));

        if (isCritical) {
            displayText = LegacyComponentSerializer.legacySection().serialize(ArmorStandName.criticalDamageFormat(finalDamage, entity));
        }

        ArmorStand armorStand = ArmorStandManager.spawnArmorStand(location, displayText);
        ArmorStandManager.removeArmorStandAfter(armorStand, 12);
    }
}