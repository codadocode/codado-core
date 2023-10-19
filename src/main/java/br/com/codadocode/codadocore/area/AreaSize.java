package br.com.codadocode.codadocore.area;

import br.com.codadocode.codadocore.core.Vector3;

public class AreaSize {
    private int horizontalSize;
    private int verticalSize;
    private Vector3 center;

    public AreaSize(int horizontalSize, int verticalSize, Vector3 center)   {
        this.horizontalSize = horizontalSize;
        this.verticalSize = verticalSize;
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

    public boolean isInside(Vector3 position)   {
        int leftPos = this.center.getX() - this.horizontalSize;
        int rightPos = this.center.getX() + this.horizontalSize;
        int frontPos = this.center.getZ() + this.horizontalSize;
        int backPos = this.center.getZ() - this.horizontalSize;

        int topPos = this.center.getY() + this.verticalSize;
        int bottomPos = this.center.getY() - this.verticalSize;

        if ((position.getX() >= leftPos) && (position.getX() <= rightPos) && (position.getZ() <= frontPos) && (position.getZ() <= backPos) && (position.getY() <= topPos) && (position.getY() >= bottomPos)) return true;
        else return false;
    }
}
