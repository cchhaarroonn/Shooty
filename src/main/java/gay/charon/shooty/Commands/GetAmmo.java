package gay.charon.shooty.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetAmmo implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("shooty.ammo")){
                player.getInventory().addItem(new ItemStack(Material.ARROW, 128));
                player.sendMessage("§8[§cShooty§8] §fSince you got your ammo you can start attacking enemies");
            } else {
                player.sendMessage("§8[§cShooty§8] §fYou don't have permission to run this command!");
            }
        } else {
            System.out.println("[!] You have to run this command as player ...");
        }

        return false;
    }
}
