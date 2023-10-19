package br.com.codadocode.codadocore.area;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AreaDataJson {
    private String areaName;
    private String enterMessage;
    private String exitMessage;
    private String areaID;
    private String mainArea;
    private List<String> subAreas;
    private AreaSize areaSize;
    private Map<AREA_FLAG, Boolean> areaFlags;

    public AreaDataJson(AreaData areaData)   {

    }
}
