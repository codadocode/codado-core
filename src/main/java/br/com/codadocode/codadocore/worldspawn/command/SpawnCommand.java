package br.com.codadocode.codadocore.worldspawn.command;

import br.com.codadocode.codadocore.core.Vector3;
import br.com.codadocode.codadocore.worldspawn.WorldSpawnData;
import br.com.codadocode.codadocore.worldspawn.WorldSpawnManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player)   {
            Player player = (Player) commandSender;
            String spawnName = "default";
            if (strings.length > 0)   {
                spawnName = strings[0];
            }
            WorldSpawnManager worldSpawnManager = (WorldSpawnManager)WorldSpawnManager.getInstance();
            Optional<WorldSpawnData> findedWorldData = worldSpawnManager.findWorldSpawnData(spawnName);
            if (findedWorldData.isPresent())   {
                WorldSpawnData worldSpawnData = findedWorldData.get();
                Vector3 spawnPosition = worldSpawnData.getSpawnPosition();
                String worldName = worldSpawnData.getWorldName();
                World teleportWorld = Bukkit.getServer().getWorld(worldName);
                if (teleportWorld == null)   {
                    Bukkit.getServer().getLogger().info("World'" + worldName + "' not found.");
                }else   {
                    Location teleportLocation = new Location(teleportWorld, spawnPosition.getX(), spawnPosition.getY(), spawnPosition.getZ());
                    player.teleport(teleportLocation);
                    player.sendMessage("Uau! Você foi teleportado!");
                    Bukkit.getServer().getLogger().info(player.getName() + " has been teleported to '" + worldSpawnData.getSpawnName() + "' spawn.");
                }
            }
        }
        return true;
    }
}
