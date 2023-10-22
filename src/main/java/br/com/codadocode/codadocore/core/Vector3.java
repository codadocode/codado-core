package br.com.codadocode.codadocore.core;

public class Vector3 {
    private double x;
    private double y;
    private double z;

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

    public Vector3(double x, double y, double z)   {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX()   {
        return this.x;
    }

    public double getY()   {
        return this.y;
    }

    public double getZ()   {
        return this.z;
    }
}
