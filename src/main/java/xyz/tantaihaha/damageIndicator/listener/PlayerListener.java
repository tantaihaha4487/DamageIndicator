package xyz.tantaihaha.damageIndicator.listener;

import org.bukkit.ChatColor;
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
import xyz.tantaihaha.damageIndicator.manager.ArmorStandManager;
import xyz.tantaihaha.damageIndicator.utils.ArmorStandName;

public class PlayerListener implements Listener {
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        // if damaged by player
        if (!(event.getDamager() instanceof Player player)) return;
        if(ArmorStandManager.isIgnoreType(event.getEntity())) return;

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
        Location location = entity.getLocation().add(0.1, entity.getHeight() + 0.03D, 0.1);
        String displayText = ArmorStandName.damageFormat(finalDamage, entity);

        if (isCritical) {
            displayText = ChatColor.GOLD.toString() + ChatColor.BOLD + "✧ " + displayText;
        }

        ArmorStand armorStand = ArmorStandManager.spawnArmorStand(location, displayText);
        ArmorStandManager.removeArmorStandAfter(armorStand, 12);
    }
}
