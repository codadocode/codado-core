package br.com.codadocode.codadocore.area;

import br.com.codadocode.codadocore.core.CodadoLog;
import br.com.codadocode.codadocore.core.ConvertUtility;
import br.com.codadocode.codadocore.core.Vector3;
import org.bukkit.entity.Player;

import javax.xml.crypto.Data;
import java.util.*;

public class AreaData {
    private CodadoLog log;
    private String areaName;
    private String owner;
    private List<String> members;
    private List<String> insidePlayers;
    private Map<String ,AreaData> subAreas;
    private Map<AREA_FLAG, Boolean> flags;
    private AreaSize areaSize;

    public AreaData(String areaName, AreaSize areaSize, Player owner)   {
        this.areaName = areaName;
        this.areaSize = areaSize;
        this.owner = owner.getName();
        this.log = new CodadoLog("Area-" + this.areaName);
        this.flags = AreaFlagBuilder.buildDefaultFlags();
        this.members = new ArrayList<>();
        this.insidePlayers = new ArrayList<>();
        this.subAreas = new HashMap<>();
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

        this.insidePlayers.add(player.getName());
        player.sendMessage("Você entrou na região '" + this.getAreaName() + "', Dono: '" + this.getOwner() + "'.");
        return true;
    }

    private boolean removePlayerInside(Player player)   {
        if (!this.insidePlayers.contains(player)) return false;

        this.insidePlayers.remove(player);
        return true;
    }

    public boolean addMember(Player sender, Player playerToAdd)   {
        if (this.members.contains(playerToAdd) || !sender.equals(this.owner)) return false;

        this.members.add(playerToAdd.getName());

        try   {
            AreaManager.getInstance().getJsonManager().saveToFile(this.areaName, this);
        }catch(Exception e)   {

        }

        return true;
    }

    public boolean removeMember(Player sender, Player playerToRemove)   {
        if (!this.members.contains(playerToRemove) || !sender.equals(this.owner)) return false;

        this.members.remove(playerToRemove);

        try   {
            AreaManager.getInstance().getJsonManager().saveToFile(this.areaName, this);
        }catch(Exception e)   {

        }

        return true;
    }

    public String getAreaName()   {
        return this.areaName;
    }

    public boolean isOwner(Player player)   {
        if (!this.owner.equals(player.getName())) return false;

        return true;
    }

    public boolean isMember(Player player)   {
        if (!this.members.equals(player.getName())) return false;

        return true;
    }

    public AreaSize getAreaSize()   {
        return this.areaSize;
    }

    public boolean getFlagValue(AREA_FLAG areaFlag)   {
        return this.flags.get(areaFlag);
    }

    public boolean setFlagValue(String flagName, boolean flagValue)   {
        if (!this.flags.containsKey(AREA_FLAG.valueOf(flagName))) return false;

        this.flags.replace(AREA_FLAG.valueOf(flagName), flagValue);

        try   {
            AreaManager.getInstance().getJsonManager().saveToFile(this.areaName, this);
        }catch(Exception e)   {

        }

        return true;
    }

    public void setOwner(Player player)   {
        this.owner = player.getName();

        try   {
            AreaManager.getInstance().getJsonManager().saveToFile(this.areaName, this);
        }catch(Exception e)   {

        }

        this.log.showInfo("Player '" + player.getName() + "' foi definido como dono da região");
    }

    public Optional<AreaData> isInside(Vector3 position)   {
        boolean positionInside = getAreaSize().isInside(position);

        if (!positionInside) return Optional.empty();

        return Optional.of(this);
    }

    public boolean createSubArea(Player sender, AreaData areaData)   {
        if (this.subAreas.containsKey(areaData.getAreaName())) return false;

        this.subAreas.put(areaData.getAreaName(), areaData);

        return true;
    }

    public String getOwner()   {
        return this.owner;
    }
}
