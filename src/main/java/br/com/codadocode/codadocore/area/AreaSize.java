package br.com.codadocode.codadocore.area;

import br.com.codadocode.codadocore.core.Vector3;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AreaSize {
    private Vector3 firstPosition;
    private Vector3 secondPosition;

    public AreaSize(Vector3 firstPosition, Vector3 secondPosition)   {
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
    }

    public Vector3 getFirstPosition()   {
        return this.firstPosition;
    }

    public Vector3 getSecondPosition()   {
        return this.secondPosition;
    }

    public String getSizeInfo()   {
        return "FirstPoint: " + this.firstPosition.toString() + ", SecondPoint: " + this.secondPosition.toString();
    }

    public boolean isInside(Vector3 position)   {
        List<Double> xPosList = Arrays.asList(this.firstPosition.getX(), this.secondPosition.getX());
        List<Double> yPosList = Arrays.asList(this.firstPosition.getY(), this.secondPosition.getY());
        List<Double> zPosList = Arrays.asList(this.firstPosition.getZ(), this.secondPosition.getZ());
        xPosList.sort(Double::compare);
        yPosList.sort(Double::compare);
        zPosList.sort(Double::compare);

        boolean insideX = (position.getX() >= xPosList.get(0) && position.getX() <= xPosList.get(1));
        boolean insideY = (position.getY() >= yPosList.get(0) && position.getY() <= yPosList.get(1));
        boolean insideZ = (position.getZ() >= zPosList.get(0) && position.getZ() <= zPosList.get(1));

        if (insideX && insideY && insideZ) return true;
        else return false;
    }
}
