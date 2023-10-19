package br.com.codadocode.codadocore.area;

import java.util.UUID;

public class AreaID {
    private UUID id;

    public AreaID(UUID id)   {
        this.id = id;
    }

    public String getID()   {
        return this.id.toString();
    }
}
