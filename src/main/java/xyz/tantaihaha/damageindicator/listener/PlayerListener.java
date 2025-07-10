package xyz.tantaihaha.damageindicator.listener;

import java.util.List;
import java.util.Objects;

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
import xyz.tantaihaha.damageindicator.config.ConfigLogger;
import xyz.tantaihaha.damageindicator.config.ConfigManager;
import xyz.tantaihaha.damageindicator.manager.ArmorStandManager;
import xyz.tantaihaha.damageindicator.utils.ArmorStandName;

public class PlayerListener implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        // if damaged by player
        if (!(event.getDamager() instanceof Player player)) return;
        if (ArmorStandManager.isIgnoreType(event.getEntity())) return;

        // Config ignores
        if (ConfigManager.getIgnoreEntities().contains(event.getEntityType())) return;
        if (ConfigManager.getIgnoreWorlds().contains(event.getEntity().getWorld())) return;

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
        List<ArmorStand> armorStands;

        if (isCritical) {
            armorStands = ArmorStandManager.spawnArmorStand(location, ArmorStandName.criticalDamageFormat(finalDamage, entity));
        } else {
            armorStands = ArmorStandManager.spawnArmorStand(location, ArmorStandName.damageFormat(finalDamage, entity));
        }
        ArmorStandManager.removeArmorStandAfter(armorStands, ConfigManager.getIndicatorLifetime());
    }
}