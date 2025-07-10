package xyz.tantaihaha.damageindicator.manager;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitScheduler;
import xyz.tantaihaha.damageindicator.DamageIndicator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArmorStandManager {
    public static List<ArmorStand> armorStandList = new ArrayList<>();

    public static List<ArmorStand> spawnArmorStand(Location location, Component name) {
        // split the name into lines if it contains newlines for bypass custom name length limit
        String legacyName = LegacyComponentSerializer.legacySection().serialize(name);
        String[] lines = legacyName.split("\n");
        List<ArmorStand> stands = new ArrayList<>();
        Location currentLocation = location.clone();

        for (String line : lines) {
            if (line.isEmpty()) {
                currentLocation.add(0, -0.25, 0);
                continue;
            }

            ArmorStand armorStand = currentLocation.getWorld().spawn(currentLocation, ArmorStand.class, stand -> {
                stand.customName(LegacyComponentSerializer.legacySection().deserialize(line));
                stand.setDisabledSlots(EquipmentSlot.values());
                stand.setInvisible(true);
                stand.setMarker(true);
                stand.setCanPickupItems(false);
                stand.setGravity(false);
                stand.setSilent(true);
                stand.setBasePlate(false);
                stand.setInvulnerable(true);
                stand.setCustomNameVisible(true);
            });
            armorStandList.add(armorStand);
            stands.add(armorStand);
            currentLocation.add(0, -0.25, 0);
        }
        return stands;
    }

    public static void removeArmorStandAfter(ArmorStand armorStand, int delay) {
        removeArmorStandAfter(Collections.singletonList(armorStand), delay);
    }

    public static void removeArmorStandAfter(List<ArmorStand> armorStands, int delay) {
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskLater(DamageIndicator.getInstance(), () -> {
            for (ArmorStand armorStand : armorStands) {
                if (armorStand.isValid()) {
                    armorStandList.remove(armorStand);
                    armorStand.remove();
                }
            }
        }, delay);
    }


    public static void removeAllArmorStands() {
        for (ArmorStand armorStand : armorStandList) {
            if (armorStand.isValid()) {
                armorStand.remove();
            }
        }
        armorStandList.clear();
    }

    /**
     * Generates a random position offset for armor stands within a range of -0.999 to 0.999
     * to prevent overlapping of damage indicators.
     *
     * @return A random double value between -0.999 and 0.999.
     */
    public static double randomPosition() {
        return Math.random() * 1.998 - 0.999;
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