package br.com.codadocode.codadocore.core;

import org.bukkit.Location;

public class ConvertUtility {
    public static Vector3 LocationToVector3(Location location)   {
        return new Vector3(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
}
