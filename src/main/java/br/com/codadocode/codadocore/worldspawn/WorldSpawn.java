package br.com.codadocode.codadocore.worldspawn;

import br.com.codadocode.codadocore.core.ConvertUtility;
import br.com.codadocode.codadocore.core.Vector3;
import org.bukkit.Location;

import java.util.Optional;

public class WorldSpawn {
    private WorldSpawnData worldSpawnData;

    public WorldSpawn(WorldSpawnData worldSpawnData)   {
        this.worldSpawnData = worldSpawnData;
    }

    public Optional<WorldSpawnData> saveSpawn(String worldName, Location location, String spawnName)   {
        if (!worldName.equals(this.worldSpawnData.getWorldName())) return Optional.empty();
        Vector3 position = ConvertUtility.LocationToVector3(location);
        this.worldSpawnData.setSpawnPosition(position);
        return Optional.of(new WorldSpawnData(worldName, position, spawnName));
    }

    public WorldSpawnData getWorldSpawnData()   {
        return new WorldSpawnData(this.worldSpawnData);
    }
}
