package gay.charon.shooty.Commands;

import gay.charon.shooty.Shooty;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor {

    FileConfiguration config = Shooty.plugin.getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("shooty.setspawn")) {
                String name = player.getWorld().getName();
                double x = player.getLocation().getX();
                double y = player.getLocation().getY();
                double z = player.getLocation().getZ();
                float yaw = player.getLocation().getYaw();
                float pitch = player.getLocation().getPitch();
                config.set(name + ".Spawn.X", x);
                config.set(name + ".Spawn.Y", y);
                config.set(name + ".Spawn.Z", z);
                config.set(name + ".Spawn.Yaw", yaw);
                config.set(name + ".Spawn.Pitch", pitch);
                Shooty.plugin.saveConfig();
                player.sendMessage("§8[§cShooty§8] §fSuccessfully set spawn location to your current location");
            }else {
                player.sendMessage("§8[§cShooty§8] §fYou don't have permission to run this command!");
            }
        } else {
            System.out.println("[!] You have to run this command as player ...");
        }

        return false;
    }
}
