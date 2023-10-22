package br.com.codadocode.codadocore.area;

import br.com.codadocode.codadocore.core.Vector3;

public class AreaSize {
    private int horizontalSize;
    private int verticalSize;
    private Vector3 center;

    public AreaSize(int horizontalSize, int verticalSize, Vector3 center)   {
        this.horizontalSize = horizontalSize;
        this.verticalSize = verticalSize;
        this.center = center;
    }

    public int getHorizontalSize()   {
        return this.horizontalSize;
    }

    public int getVerticalSize()   {
        return this.verticalSize;
    }

    public Vector3 getCenter()   {
        return this.center;
    }

    public String getSizeInfo()   {
        return "Centro:" + this.center + ", TamanhoHorizontal:" + this.horizontalSize + ", TamanhoVertical:" + this.verticalSize;
    }

    public boolean isInside(Vector3 position)   {
        boolean insideX = position.getX() < this.center.getX() + this.horizontalSize && position.getX() > this.center.getX() - this.horizontalSize;
        boolean insideZ = position.getZ() < this.center.getZ() + this.horizontalSize && position.getZ() > this.center.getZ() - this.horizontalSize;
        boolean insideY = position.getY() < this.center.getY() + this.verticalSize && position.getY() > this.center.getY() - this.verticalSize;

        if (insideX && insideY && insideZ) return true;
        else return false;
    }
}
