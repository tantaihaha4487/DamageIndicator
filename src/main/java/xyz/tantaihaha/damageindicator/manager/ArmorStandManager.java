package xyz.tantaihaha.damageindicator.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitScheduler;
import xyz.tantaihaha.damageindicator.DamageIndicator;

import java.util.ArrayList;
import java.util.List;

public class ArmorStandManager {
    public static List<ArmorStand> armorStandList = new ArrayList<>();

    public static ArmorStand spawnArmorStand(Location location, String name) {

        return location.getWorld().spawn(location, ArmorStand.class, armorStand -> {
            armorStand.setCustomName(name);
            armorStand.setDisabledSlots(EquipmentSlot.values());
            armorStand.setInvisible(true);
            armorStand.setMarker(true);
            armorStand.setCanPickupItems(false);
            armorStand.setGravity(false);
            armorStand.setSilent(true);
            armorStand.setBasePlate(false);
            armorStand.setInvulnerable(true);
            armorStand.setCustomNameVisible(true);
            armorStandList.add(armorStand);
        });
    }

    public static void removeArmorStandAfter(ArmorStand armorStand, int delay) {
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskLater(DamageIndicator.getInstance(), () -> {
            if(!armorStand.isValid()) return;
            armorStandList.remove(armorStand);
            armorStand.remove();

        }, delay);
    }

    public static void removeAllArmorStands() {
        for (ArmorStand armorStand : armorStandList) {
            if (!armorStand.isValid()) return;
            armorStand.remove();
        }
        armorStandList.clear();
    }

    public static boolean isIgnoreType(Entity entity) {
        EntityType type = entity.getType();
        return switch (type) {
            // Non-Damageable and special entities to ignore
            case ITEM_DISPLAY, BLOCK_DISPLAY, TEXT_DISPLAY, INTERACTION, PAINTING, LEASH_KNOT, ITEM, ITEM_FRAME,
                 GLOW_ITEM_FRAME, COMMAND_BLOCK_MINECART, TNT_MINECART, SPAWNER_MINECART, HOPPER_MINECART,
                 FURNACE_MINECART, CHEST_MINECART, MINECART, ARMOR_STAND, PLAYER, FIREWORK_ROCKET, FIREBALL,
                 SMALL_FIREBALL, DRAGON_FIREBALL, SPLASH_POTION, LINGERING_POTION, EGG, ENDER_PEARL, END_CRYSTAL,
                 EYE_OF_ENDER, AREA_EFFECT_CLOUD, SPECTRAL_ARROW, ARROW, FALLING_BLOCK, SHULKER_BULLET, FISHING_BOBBER,
                 LIGHTNING_BOLT, TRIDENT, SNOWBALL, WITHER_SKULL, TNT, OAK_CHEST_BOAT, SPRUCE_CHEST_BOAT,
                 BIRCH_CHEST_BOAT, JUNGLE_CHEST_BOAT, ACACIA_CHEST_BOAT, DARK_OAK_CHEST_BOAT, MANGROVE_CHEST_BOAT,
                 BAMBOO_CHEST_RAFT, MARKER, EVOKER_FANGS, EXPERIENCE_BOTTLE, EXPERIENCE_ORB, UNKNOWN -> true;
            default -> false;
        };
    }
}