package br.com.codadocode.codadocore.worldspawn;

import br.com.codadocode.codadocore.core.Vector3;

public class WorldSpawnData {
    private String worldName;
    private Vector3 spawnPosition;
    private String spawnName;

    public WorldSpawnData(String worldName, Vector3 spawnPosition, String spawnName)   {
        this.spawnPosition = spawnPosition;
        this.worldName = worldName;
        this.spawnName = spawnName;
    }

    public WorldSpawnData(WorldSpawnData worldSpawnData)   {
        this.spawnPosition = worldSpawnData.getSpawnPosition();
        this.worldName = worldSpawnData.getWorldName();
        this.spawnName = worldSpawnData.spawnName;
    }

    public Vector3 getSpawnPosition()   {
        return this.spawnPosition;
    }

    public String getWorldName()   {
        return this.worldName;
    }

    public String getSpawnName()   {
        return this.spawnName;
    }

    public void setSpawnPosition(Vector3 spawnPosition)   {
        this.spawnPosition = spawnPosition;
    }

    public void setWorldName(String worldName)   {
        this.worldName = worldName;
    }

    public void setSpawnName(String spawnName)   {
        this.spawnName = spawnName;
    }
}
