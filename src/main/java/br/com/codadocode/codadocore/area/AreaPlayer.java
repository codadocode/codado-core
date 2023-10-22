package br.com.codadocode.codadocore.area;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AreaPlayer {
    private Player player;
    private AreaData actualRegion;

    public AreaPlayer(Player player)   {
        this.player = player;
    }

    public boolean setActualRegion(AreaData areaData)   {
        this.actualRegion = areaData;
        return true;
    }

    public AreaData getActualRegion()   {
        return this.actualRegion;
    }

    public Player getPlayer()   {
        return this.player;
    }
}
