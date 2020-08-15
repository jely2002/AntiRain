# AntiRain
A spigot plugin to stop raining on your server. And also disable the weather command for players.
This plugin is compatible with all versions up to **1.16.2**

[SpigotMC page](https://www.spigotmc.org/resources/antirain.27224/)

### Commands
- */antirain reload*
  - Reloads the config
  - Permission: ar.admin
- */antirain version*
  - Shows the version of the plugin
  - Permission: ar.admin

### Permissions
- *ar.admin*
  - Allows the player to use the reload & version commands
- *ar.override*
  - Allows the player to still use weather commands when those are disabled

### Config
```yaml
#Add world names below to add them to the black-list (List of worlds where the plugin is disabled).
disabled-worlds:
  - One world
  - Another world
  - The last world

#Set this to true to disable the /weather command
disable-weather-commands: false

#Set the message that is displayed when a player is denied the /weather command
disable-weather-message: '&4 This server has disabled the weather command'

# Config version, please do not touch this
config-version: 1
```
