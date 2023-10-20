package br.com.codadocode.codadocore.core;

public class  BaseSingleton {
    private static BaseSingleton instance;

    protected BaseSingleton()   {
        instance = this;
    }

    public static BaseSingleton getInstance()   {
        if (instance == null)   {
            instance = new BaseSingleton();
        }

        return instance;
    }

    public <T> T cast()   {
        return (T)instance;
    }
}
