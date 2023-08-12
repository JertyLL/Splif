package org.example;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class splif extends JavaPlugin implements Listener {

    private World world;
    private Location startLocation;
    private int gameHeight;

    @Override
    public void onEnable() {
        // Установите здесь вашу стартовую локацию и высоту игры
        world = Bukkit.getWorld("world");
        startLocation = new Location(world, 55, 100, 238);
        gameHeight = 10;

        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();

        if (location.getY() <= startLocation.getY() - gameHeight) {
            // Игрок упал ниже уровня игры, удаляем блок под ним
            Block block = player.getLocation().getBlock();
            block.setType(Material.AIR);
        }
    }

    public void startGame() {
        // Создаем платформу для игры
        for (int x = -5; x <= 5; x++) {
            for (int z = -5; z <= 5; z++) {
                Block block = startLocation.clone().add(54, 99, 238).getBlock();
                block.setType(Material.SNOW_BLOCK);
            }
        }
    }
}