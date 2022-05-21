package gay.charon.shooty;

import gay.charon.shooty.Commands.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;
import java.util.Random;

public final class Shooty extends JavaPlugin implements Listener {

    public static Shooty plugin;
    Random random = new Random();

    @Override
    public void onEnable() {
        System.out.println("[*] Starting Shooty ...");
        System.out.println("[!] Registering commands and events ...");
        plugin = this;
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        getCommand("asetborder").setExecutor(new SetBorder());
        getCommand("asetspawn").setExecutor(new SetSpawn());
        getCommand("aspawn").setExecutor(new Spawn());
        getCommand("ajoin").setExecutor(new Join());
        getCommand("abow").setExecutor(new GetBow());
        getCommand("aammo").setExecutor(new GetAmmo());
        System.out.println("[!] Registered commands and events");
        System.out.println("[!] Loading configs ...");
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        System.out.println("[!] Loaded all configs!");
        System.out.println("[!] Clearing all entities from world...");
        for (World w : Bukkit.getWorlds()) {
            for (Entity e : w.getEntities()) {
                if (!(e instanceof Player)) {
                    e.remove();
                }
            }
        }
        System.out.println("[!] Cleared all entities from world!");
        System.out.println("[!] Loading enemies in arenas...");
        for (World world : Bukkit.getWorlds()) {
            String worldName = world.getName();
            double minX = getConfig().getDouble(worldName + ".minX");
            double maxX = getConfig().getDouble(worldName + ".maxX");
            double minZ = getConfig().getDouble(worldName + ".minZ");
            double maxZ = getConfig().getDouble(worldName + ".maxZ");
            for(int i = 0; i<10; i++) {
                double x = minX + (maxX - minX) * random.nextDouble();
                double y = getConfig().getDouble(worldName + ".height");
                double z = minZ + (maxZ - minZ) * random.nextDouble();
                Location location = new Location(world, x, y, z);
                world.spawnEntity(location, EntityType.ZOMBIE);
            }
        }
        System.out.println("[!] Loaded entities in arenas!");
        System.out.println("[*] Started Shooty!");
    }

    @Override
    public void onDisable() {
        System.out.println("[*] Stopping Shooty ...");
        System.out.println("[*] Stopped Shooty!");
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e){
        Player player = e.getEntity().getKiller();
        LivingEntity entity = e.getEntity();
        if (entity.getType() == EntityType.ZOMBIE) {
            String worldName = player.getWorld().getName();
            World world = player.getWorld();
            double minX = getConfig().getDouble(worldName + ".minX");
            double maxX = getConfig().getDouble(worldName + ".maxX");
            double minZ = getConfig().getDouble(worldName + ".minZ");
            double maxZ = getConfig().getDouble(worldName + ".maxZ");
            double x = minX + (maxX - minX) * random.nextDouble();
            double y = getConfig().getDouble(worldName + ".height");
            double z = minZ + (maxZ - minZ) * random.nextDouble();
            Location location = new Location(world, x, y, z);
            world.spawnEntity(location, EntityType.ZOMBIE);
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player whoWasHit = (Player) e.getEntity();
            Player whoHit = (Player) e.getDamager();
            if(whoWasHit.getType() == EntityType.PLAYER)
                e.setCancelled(true);

            if(whoHit.getType() == EntityType.PLAYER)
                whoHit.sendMessage("§8[§cShooty§8] §fYou can't attack other players only enemies!");
        }
    }

    @EventHandler
    public void onPlayerClickSign(PlayerInteractEvent e){
        Player player = e.getPlayer();
        String playerName = player.getName();
        if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Sign sign = (Sign) e.getClickedBlock().getState();
            if (sign.getLine(0).equalsIgnoreCase("[JOIN ARENA]")) {
                String arena = sign.getLine(1);
                World arenaWorld = Bukkit.getWorld(arena);
                double x = Shooty.plugin.getConfig().getDouble(arena + ".Spawn.X");
                double y = Shooty.plugin.getConfig().getDouble(arena + ".Spawn.Y");
                double z = Shooty.plugin.getConfig().getDouble(arena + ".Spawn.Z");
                float yaw = (float) Shooty.plugin.getConfig().getDouble(arena + ".Spawn.Pitch");
                float pitch = (float) Shooty.plugin.getConfig().getDouble(arena + ".Spawn.Pitch");
                Location arenaLocation = new Location(arenaWorld, x, y, z, yaw, pitch);
                player.teleport(arenaLocation);
                player.sendMessage("§8[§cShooty§8] §fSuccessfully teleported to arena " + arena + "!");
                sign.update();
                System.out.println(playerName + " joined arena " + arena);
            }
        }

        if(e.getClickedBlock().getType() == null){
            System.out.println("");
        }
    }

    @EventHandler
    public void changeArrowDamage(EntityDamageByEntityEvent e){
        if(e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE){
            if(e.getDamager() instanceof Arrow){
                Arrow arrow = (Arrow) e.getDamager();
                e.setDamage(99999999);
            }
        }
    }

    @EventHandler
    public void onEntityCombust(EntityCombustEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Player) && !(entity instanceof Zombie))
            entity.remove();
    }

}