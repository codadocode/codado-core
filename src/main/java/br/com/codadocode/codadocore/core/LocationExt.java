package br.com.codadocode.codadocore.core;

import org.bukkit.Location;
import org.bukkit.World;

public class LocationExt extends Location {
    public LocationExt(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public Vector3 toVector3()   {
        return new Vector3(this.getBlockX(), this.getBlockY(), this.getBlockZ());
    }
}
