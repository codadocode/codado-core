package br.com.codadocode.codadocore.worldspawn;

import br.com.codadocode.codadocore.core.Vector3;

public class WorldSpawnData {
    private String worldName;
    private Vector3 spawnPosition;

    public WorldSpawnData(String worldName, Vector3 spawnPosition)   {
        this.spawnPosition = spawnPosition;
        this.worldName = worldName;
    }

    public Vector3 getSpawnPosition()   {
        return this.spawnPosition;
    }

    public String getWorldName()   {
        return this.worldName;
    }

    public void setSpawnPosition(Vector3 spawnPosition)   {
        this.spawnPosition = spawnPosition;
    }

    public void setWorldName(String worldName)   {
        this.worldName = worldName;
    }
}
