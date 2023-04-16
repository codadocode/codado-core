package br.com.codadocode.codadocore.worldspawn;

import br.com.codadocode.codadocore.core.LocationExt;
import br.com.codadocode.codadocore.core.Vector3;
import org.bukkit.Location;

import java.util.Optional;

public class WorldSpawn {
    private WorldSpawnData worldSpawnData;

    public WorldSpawn(WorldSpawnData worldSpawnData)   {
        this.worldSpawnData = worldSpawnData;
    }

    public Optional<WorldSpawnData> saveSpawn(String worldName, Location location)   {
        if (!worldName.equals(this.worldSpawnData.getWorldName())) return Optional.empty();
        LocationExt locationExt = (LocationExt)location;
        this.worldSpawnData.setSpawnPosition(locationExt.toVector3());
        return Optional.of(new WorldSpawnData(worldName, locationExt.toVector3()));
    }
}
