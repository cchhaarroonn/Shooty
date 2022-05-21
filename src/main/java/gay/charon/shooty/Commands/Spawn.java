package gay.charon.shooty.Commands;

import gay.charon.shooty.Shooty;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

    FileConfiguration config = Shooty.plugin.getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("shooty.spawn")) {
                String arena = player.getWorld().getName();
                World arenaWorld = Bukkit.getWorld(arena);
                double x = config.getDouble(arena + ".Spawn.X");
                double y = config.getDouble(arena + ".Spawn.Y");
                double z = config.getDouble(arena + ".Spawn.Z");
                float yaw = (float) config.getDouble(arena + ".Spawn.Pitch");
                float pitch = (float) config.getDouble(arena + ".Spawn.Pitch");
                Location arenaLocation = new Location(arenaWorld, x, y, z, yaw, pitch);
                player.teleport(arenaLocation);
                player.sendMessage("§8[§cShooty§8] §fSuccessfully teleported to spawn");
            }else {
                player.sendMessage("§8[§cShooty§8] §fYou don't have permission to run this command!");
            }
        } else {
            System.out.println("[!] You have to run this command as player ...");
        }


        return false;
    }
}
