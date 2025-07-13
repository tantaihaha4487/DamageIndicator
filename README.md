# DamageIndicator

DamageIndicator is a Paper plugin that displays damage dealt to entities a. This provides a visual representation of combat feedback, enhancing the player experience on your survival server.

## Features

- **Floating Damage Text**: Clearly see how much damage you deal to mobs and other players.
- **Configurable**: Customize various aspects of the damage indicators, including:
  - Enabling/disabling the plugin.
  - Ignoring specific entity types.
  - Ignoring specific worlds.
  - **Configurable indicator lifetime**: Set how long damage indicators remain visible.
  - **Formatting**: Control the color and style of the displayed damage text.
- **Lightweight**: Designed to be efficient and not impact server performance significantly.

## Damage
![](https://github.com/tantaihaha4487/assets/blob/main/DamageIndicator/dmg.gif?raw=true)

## Critical Damage
![](https://github.com/tantaihaha4487/assets/blob/main/DamageIndicator/crit.gif?raw=true)

## Heal
![](https://github.com/tantaihaha4487/assets/blob/main/DamageIndicator/heal.gif?raw=true)

## Installation

1.  Download the latest version of the plugin from [https://github.com/tantaihaha4487/DamageIndicator/releases/latest].
2.  Place the `DamageIndicator-2.x.x.jar` file into your server's `plugins/` folder.
3.  Restart or reload your server.
4.  (Optional) Configure the `config.yml` file located in `plugins/DamageIndicator/` to suit your server's needs.

## Configuration

The plugin generates a `config.yml` file in the `plugins/DamageIndicator/` directory upon first run. Here are some key configuration options:

```yaml
# config.yml example

# Enable or disable the plugin
plugin-enabled: true

# List of entity types to ignore (e.g., ARMOR_STAND, VILLAGER)
ignored-entities:
  - ARMOR_STAND

# List of worlds where damage indicators should not appear
ignored-worlds:
  - world_nether
  - world_the_end

  #
  # You can see documentation for color codes below:
  # https://docs.advntr.dev/minimessage/format.html
  #

# Format for displaying health indicators (supports MiniMessage format)
# Example: "<aqua>+{healamount}</aqua> <green>[{currenthealth}/{maxhealth}❤]</green>"
# Placeholders: {healamount}, {currenthealth}, {maxhealth}
health-indicator-format: "<aqua>+{healamount}</aqua> <green>[{currenthealth}/{maxhealth}❤]</green>"

# Format for displaying damage indicators (supports MiniMessage format)
# Example: "<red>-{damage}</red> <green>[{currenthealth}/{maxhealth}❤]</green>"
# Placeholders: {damage}, {currenthealth}, {maxhealth}
damage-indicator-format: "<red>-{damage}</red> <green>[{currenthealth}/{maxhealth}❤]</green>"

# Format for displaying critical damage indicators (supports MiniMessage format)
# Example: "<gold><b>✧</b></gold> <red>-{damage}</red> <green>[{currenthealth}/{maxhealth}❤]</green>"
# Placeholders: {damage}, {currenthealth}, {maxhealth}
critical-damage-indicator-format: "<gold><b>✧</b></gold> <red>-{damage}</red> <green>[{currenthealth}/{maxhealth}❤]</green>"

# The lifetime of the indicators in ticks before they disappear (must be greater than 1)
lifetime: 12
```

## Commands

| Command           | Description                               | Permissions (Assumed) |
|-------------------|-------------------------------------------|-----------------------|
| `/damageindicator` | Base command for plugin information/reload. | `damageindicator.use` |

## Support & Contributing

If you encounter any issues, have suggestions, or would like to contribute, please visit the [GitHub repository](https://github.com/tantaihaha4487/DamageIndicator/) or open an issue there.

**Authors**: TantaiHaha, Thanachot
**Version**: 2.0.1
**Version**: 1.21.x
