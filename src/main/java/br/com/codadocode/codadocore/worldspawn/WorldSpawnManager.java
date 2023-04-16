package br.com.codadocode.codadocore.worldspawn;

import br.com.codadocode.codadocore.core.BaseSingleton;
import br.com.codadocode.codadocore.core.DataFolder;
import br.com.codadocode.codadocore.core.JsonManager;
import br.com.codadocode.codadocore.core.LocationExt;
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
    private HashMap<World, WorldSpawn> worldSpawnMap;
    private DataFolder worldSpawnManagerDataFolder;
    private JsonManager jsonManager;

    public WorldSpawnManager(File dataFolder, String subDataFolder)   {
        super();
        this.worldSpawnMap = new HashMap<>();
        this.worldSpawnManagerDataFolder = new DataFolder(dataFolder, subDataFolder);
        this.jsonManager = new JsonManager(this.worldSpawnManagerDataFolder);
    }

    public void saveSpawn(@NotNull World world, @NotNull Location spawnLocation)   {
        LocationExt location = (LocationExt) spawnLocation;
        WorldSpawnData worldSpawnData = new WorldSpawnData(world.getName(), location.toVector3());
        WorldSpawn worldSpawn = new WorldSpawn(worldSpawnData);
        this.worldSpawnMap.put(world, worldSpawn);
        this.jsonManager.saveToFile(world.getName(), worldSpawnData, worldSpawnData.getClass());
    }

    public void loadAllSpawnData()   {
        Optional<List<Object>> optionalWorldSpawnDataList = this.jsonManager.loadAllFiles(WorldSpawnData.class);
        if (optionalWorldSpawnDataList.isPresent())   {
            List<Object> worldSpawnDataList = optionalWorldSpawnDataList.get();
            for (int i = 0; i < worldSpawnDataList.size(); i++)   {
                WorldSpawnData actualWorldSpawnData = (WorldSpawnData)worldSpawnDataList.get(i);
                World findedWorld = Bukkit.getServer().getWorld(actualWorldSpawnData.getWorldName());
                if (findedWorld != null)   {
                    WorldSpawn worldSpawn = new WorldSpawn(actualWorldSpawnData);
                    this.worldSpawnMap.put(findedWorld, worldSpawn);
                }
            }
        }
    }
}
