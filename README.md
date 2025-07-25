# DamageIndicator
<p align="center">
  <a href="https://github.com/tantaihaha4487/Damageindicator/actions/workflows/build-plugin.yml">
    <img src="https://github.com/tantaihaha4487/Damageindicator/actions/workflows/build-plugin.yml/badge.svg" alt="Build Status">
  </a>
  <a href="https://github.com/tantaihaha4487/Damageindicator/releases/latest">
    <img src="https://img.shields.io/github/v/release/tantaihaha4487/Damageindicator?style=flat-square" alt="Latest Release">
  </a>
</p>

DamageIndicator is a Paper plugin that displays damage dealt to entities. This provides a visual representation of combat feedback, enhancing the player experience on your survival server.

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

#
# Enable or disable the plugin.
#
enable: true

indicator:
  # Format for displaying in indicators

  #
  # You can generate your own format below:
  # https://www.birdflop.com/resources/rgb/
  # select Color format to MiniMessage
  #
  health-indicator-format: "<aqua>+{healamount}</aqua> <green>[{currenthealth}/{maxhealth}❤]</green>"
  damage-indicator-format: "<red>-{damage}</red> <green>[{currenthealth}/{maxhealth}❤]</green>"
  critical-damage-indicator-format: "<gold><b>✧</b></gold> <red>-{damage}</red> <green>[{currenthealth}/{maxhealth}❤]</green>"


  # The lifetime of the indicators in ticks before they disappear
  lifetime: 12

# List of entities and worlds to ignore
blacklist:
  entities:
    - "item_frame"
    - "item"
  worlds:
    - "world_example"
```

## Commands

| Command            | Description                                 | Permissions (Assumed) |
|--------------------|---------------------------------------------|-----------------------|
| `/damageindicator` | Base command for plugin information/reload. | `damageindicator.use` |

## Support & Contributing

If you encounter any issues, have suggestions, or would like to contribute, please visit the [GitHub repository](https://github.com/tantaihaha4487/DamageIndicator/) or open an issue there.

**Authors**: TantaiHaha, Thanachot
**Version**: 2.0.2
**Version**: 1.21.x
