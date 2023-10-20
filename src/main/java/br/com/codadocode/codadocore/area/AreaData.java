package br.com.codadocode.codadocore.area;

import br.com.codadocode.codadocore.core.ConvertUtility;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AreaData {
    private AreaSize areaSize;
    private AreaID mainArea;
    private List<AreaID> subAreas;
    private Map<AREA_FLAG, Boolean> areaFlags;
    private String areaName;
    private String enterMessage;
    private String exitMessage;
    private AreaID areaID;
    private UUID ownerID;
    private List<UUID> members;
    private List<UUID> playersInsideArea;

    public AreaData(String areaName, AreaSize areaSize, UUID ownerID)   {
        this.areaName = areaName;
        this.areaSize = areaSize;
        this.areaID = new AreaID(UUID.randomUUID());
        this.ownerID = ownerID;
        this.members = new ArrayList<>();
        this.playersInsideArea = new ArrayList<>();
    }

    public AreaData(String areaName, AreaSize areaSize, AreaID mainAreaID, UUID ownerID)   {
        this(areaName, areaSize, ownerID);
        this.mainArea = mainAreaID;
    }

    public AreaID getAreaID()   {
        return this.areaID;
    }

    public boolean addSubArea(AreaData subAreaData)   {
        if (this.subAreas.contains(subAreaData.areaID)) return false;

        this.subAreas.add(subAreaData.areaID);
        return true;
    }

    public boolean removeSubArea(AreaData subAreaData)   {
        if (!this.subAreas.contains(subAreaData.areaID)) return false;

        this.subAreas.remove(subAreaData.areaID);
        return true;
    }

    public boolean checkPlayerInside(Player player)   {
        boolean isPlayerInside = this.areaSize.isInside(ConvertUtility.LocationToVector3(player.getLocation()));
        if (isPlayerInside)   {
            addPlayerInside(player);
            return true;
        }else   {
            removePlayerInside(player);
            return false;
        }
    }

    private boolean addPlayerInside(Player player)   {
        if (this.playersInsideArea.contains(player.getUniqueId())) return false;

        this.playersInsideArea.add(player.getUniqueId());
        return true;
    }

    private boolean removePlayerInside(Player player)   {
        if (!this.playersInsideArea.contains(player.getUniqueId())) return false;

        this.playersInsideArea.remove(player.getUniqueId());
        return true;
    }
}
