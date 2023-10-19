package br.com.codadocode.codadocore.area;

import br.com.codadocode.codadocore.core.BaseSingleton;

import java.util.*;

public class AreaManager extends BaseSingleton {
    private Map<AreaID, AreaData> areas;

    public AreaManager()   {
        this.areas = new HashMap<AreaID, AreaData>();
    }

    public boolean createArea(String areaName, AreaSize areaSize, UUID ownerID)   {
        AreaData area = new AreaData(areaName, areaSize, ownerID);
        this.areas.put(area.getAreaID(), area);
        return true;
    }

    public boolean createSubArea(String areaName, AreaSize areaSize, UUID ownerID, AreaID mainAreaID)   {
        AreaData area = new AreaData(areaName, areaSize, mainAreaID, ownerID);
        this.areas.put(area.getAreaID(), area);
        return true;
    }

    public boolean removeArea(AreaData area)   {
        if (!this.areas.containsKey(area.getAreaID())) return false;

        this.areas.remove(area);
        return true;
    }

    public boolean removeArea(AreaID areaID)   {
        if (!this.areas.containsKey(areaID)) return false;

        AreaData findedArea = this.areas.get(areaID);
        return removeArea(findedArea);
    }

    private Optional<AreaData> findArea(AreaData area)   {
        if (!this.areas.containsKey(area.getAreaID())) return Optional.empty();

        return Optional.of(this.areas.get(area.getAreaID()));
    }
}
