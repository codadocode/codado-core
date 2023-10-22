package br.com.codadocode.codadocore.area;

import br.com.codadocode.codadocore.core.CodadoLog;
import br.com.codadocode.codadocore.core.ConvertUtility;
import org.bukkit.entity.Player;

import java.util.*;

public class AreaData {
    private CodadoLog log;
    private String areaName;
    private Player owner;
    private List<Player> members;
    private List<Player> insidePlayers;
    private Map<AREA_FLAG, Boolean> flags;
    private AreaSize areaSize;

    public AreaData(String areaName, AreaSize areaSize, Player owner)   {
        this.areaName = areaName;
        this.areaSize = areaSize;
        this.owner = owner;
        this.log = new CodadoLog(this.areaName);
        this.flags = AreaFlagBuilder.buildDefaultFlags();
        this.members = new ArrayList<>();
        this.insidePlayers = new ArrayList<>();
    }

    public Optional<AreaData> checkPlayerInside(Player player)   {
        boolean isInside = this.areaSize.isInside(ConvertUtility.locationToVector3(player.getLocation()));

        if (isInside)   {
            addPlayerInside(player);
            return Optional.of(this);
        }

        return Optional.empty();
    }

    private boolean addPlayerInside(Player player)   {
        if (this.insidePlayers.contains(player)) return false;

        this.insidePlayers.add(player);
        return true;
    }

    private boolean removePlayerInside(Player player)   {
        if (!this.insidePlayers.contains(player)) return false;

        this.insidePlayers.remove(player);
        return true;
    }

    public boolean addMember(Player sender, Player playerToAdd)   {
        if (this.members.contains(playerToAdd) || !sender.equals(this.owner)) return false;

        this.members.add(playerToAdd);
        return true;
    }

    public boolean removeMember(Player sender, Player playerToRemove)   {
        if (!this.members.contains(playerToRemove) || !sender.equals(this.owner)) return false;

        this.members.remove(playerToRemove);
        return true;
    }

    public String getAreaName()   {
        return this.areaName;
    }

    public boolean isOwner(Player player)   {
        if (!this.owner.equals(player)) return false;

        return true;
    }

    public boolean isMember(Player player)   {
        if (!this.members.equals(player)) return false;

        return true;
    }
}
