package xyz.tantaihaha.damageindicator.config;

import xyz.tantaihaha.damageindicator.DamageIndicator;

import java.util.List;

public class ConfigLogger {

    public static void log(String message) {
        DamageIndicator.getInstance().getLogger().info("\n" + message);
    }

    public static void logIgnoredEntity() {
        if (ConfigManager.getIgnoreEntities() == null || ConfigManager.getIgnoreEntities().isEmpty()) return;


        log("| Ignored entities |" + "\n" +
                listLogValue(ConfigManager.getIgnoreEntities()));
    }

    public static void logIgnoredWorld() {
        if (ConfigManager.getIgnoreWorlds() == null || ConfigManager.getIgnoreWorlds().isEmpty()) return;

        log("| Ignored worlds |" + "\n" +
                listLogValue(ConfigManager.getIgnoreWorlds()));
    }

    public static String listLogValue(List<?> list) {
        return String.join("\n",
                list.stream()
                        .map(Object::toString)
                        .map(String::toUpperCase)
                        .map(name -> "╰ " + name)
                        .toList()
        );
    }
}
