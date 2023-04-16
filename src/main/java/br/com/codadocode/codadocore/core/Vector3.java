package br.com.codadocode.codadocore.core;

public class Vector3 {
    private int x;
    private int y;
    private int z;

    public Vector3()   {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector3(Vector3 value)   {
        this.x = value.getX();
        this.y = value.getY();
        this.z = value.getZ();
    }

    public Vector3(int x, int y, int z)   {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX()   {
        return this.x;
    }

    public int getY()   {
        return this.y;
    }

    public int getZ()   {
        return this.z;
    }
}
