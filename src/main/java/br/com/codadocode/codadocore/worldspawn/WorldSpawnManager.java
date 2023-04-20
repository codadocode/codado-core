package br.com.codadocode.codadocore.worldspawn;

import br.com.codadocode.codadocore.core.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class WorldSpawnManager extends BaseSingleton {
    private HashMap<String, WorldSpawn> worldSpawnMap;
    private DataFolder worldSpawnManagerDataFolder;
    private JsonManager jsonManager;

    public WorldSpawnManager(File dataFolder, String subDataFolder)   {
        super();
        this.worldSpawnMap = new HashMap<>();
        this.worldSpawnManagerDataFolder = new DataFolder(dataFolder, subDataFolder);
        this.jsonManager = new JsonManager(this.worldSpawnManagerDataFolder);
    }

    public void saveSpawn(@NotNull World world, @NotNull Location spawnLocation, @NotNull String spawnName)   {
        Vector3 position = ConvertUtility.LocationToVector3(spawnLocation);
        WorldSpawnData worldSpawnData = new WorldSpawnData(world.getName(), position, spawnName);
        WorldSpawn worldSpawn = new WorldSpawn(worldSpawnData);
        this.worldSpawnMap.put(spawnName, worldSpawn);
        this.jsonManager.saveToFile(world.getName(), worldSpawnData, worldSpawnData.getClass());
        if (spawnName.equals("default")) world.setSpawnLocation(position.getX(), position.getY(), position.getZ());
    }

    public void loadAllSpawnData()   {
        Optional<List<Object>> optionalWorldSpawnDataList = this.jsonManager.loadAllFiles(WorldSpawnData.class);
        if (optionalWorldSpawnDataList.isPresent())   {
            List<Object> worldSpawnDataList = optionalWorldSpawnDataList.get();
            for (int i = 0; i < worldSpawnDataList.size(); i++)   {
                WorldSpawnData actualWorldSpawnData = (WorldSpawnData)worldSpawnDataList.get(i);
                World findedWorld = Bukkit.getServer().getWorld(actualWorldSpawnData.getWorldName());
                String spawnName = actualWorldSpawnData.getSpawnName();
                if (findedWorld != null)   {
                    WorldSpawn worldSpawn = new WorldSpawn(actualWorldSpawnData);
                    this.worldSpawnMap.put(spawnName, worldSpawn);
                    if (spawnName.equals("default"))   {
                        Vector3 spawnPosition = actualWorldSpawnData.getSpawnPosition();
                        findedWorld.setSpawnLocation(spawnPosition.getX(), spawnPosition.getY(), spawnPosition.getZ());
                    }
                }
            }
        }
        if (this.worldSpawnMap.size() > 0)   {
            Bukkit.getServer().getLogger().info("All world spawn data has been loaded!");
        }else   {
            Bukkit.getServer().getLogger().info("World spawn data empty!");
        }
    }

    public Optional<WorldSpawnData> findWorldSpawnData(String spawnName)   {
        WorldSpawn findedWorldSpawn = this.worldSpawnMap.get(spawnName);
        if (findedWorldSpawn == null) return Optional.empty();

        WorldSpawnData findedWorldSpawnData = findedWorldSpawn.getWorldSpawnData();
        return Optional.of(findedWorldSpawnData);
    }
}