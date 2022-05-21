package gay.charon.shooty.Commands;

import gay.charon.shooty.Shooty;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetBorder implements CommandExecutor {

    FileConfiguration config = Shooty.plugin.getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("shooty.setborder")) {
                String name = player.getWorld().getName();
                if (args[0].equalsIgnoreCase("minx")) {
                    double minx = player.getLocation().getX();
                    config.set(name + ".minX", minx);
                    Shooty.plugin.saveConfig();
                    player.sendMessage("§8[§cShooty§8] §fSuccessfully set minX for enemy spawn!");
                } else if (args[0].equalsIgnoreCase("maxx")) {
                    double maxx = player.getLocation().getX();
                    config.set(name + ".maxX", maxx);
                    Shooty.plugin.saveConfig();
                    player.sendMessage("§8[§cShooty§8] §fSuccessfully set maxX for enemy spawn!");
                } else if (args[0].equalsIgnoreCase("minz")) {
                    double minz = player.getLocation().getZ();
                    config.set(name + ".minZ", minz);
                    Shooty.plugin.saveConfig();
                    player.sendMessage("§8[§cShooty§8] §fSuccessfully set minZ for enemy spawn!");
                } else if (args[0].equalsIgnoreCase("maxz")) {
                    double maxz = player.getLocation().getZ();
                    config.set(name + ".maxZ", maxz);
                    Shooty.plugin.saveConfig();
                    player.sendMessage("§8[§cShooty§8] §fSuccessfully set maxZ for enemy spawn!");
                } else if (args[0].equalsIgnoreCase("height")) {
                    double height = player.getLocation().getY();
                    config.set(name + ".height", height);
                    Shooty.plugin.saveConfig();
                    player.sendMessage("§8[§cShooty§8] §fSuccessfully set height for enemy spawn!");
                }
            }else {
                player.sendMessage("§8[§cShooty§8] §fYou don't have permission to run this command!");
            }
        } else {
            System.out.println("[!] You have to run this command as player ...");
        }

        return false;
    }
}
