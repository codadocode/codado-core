package br.com.codadocode.codadocore.area;

import java.util.Optional;

public class AreaInfo {
    private Optional<AreaData> mainArea;
    private Optional<AreaData> subArea;

    public AreaInfo(Optional<AreaData> mainArea, Optional<AreaData> subArea)   {
        this.mainArea = mainArea;
        this.subArea = subArea;
    }

    public AreaInfo()   {
        this.mainArea = Optional.empty();
        this.subArea = Optional.empty();
    }

    public Optional<AreaData> getMainArea()   {
        return this.mainArea;
    }

    public Optional<AreaData> getSubArea()   {
        return this.subArea;
    }
}
