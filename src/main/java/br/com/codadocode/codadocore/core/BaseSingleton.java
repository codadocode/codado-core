package br.com.codadocode.codadocore.core;

public class BaseSingleton  {
    private static BaseSingleton instance;

    protected BaseSingleton()   {
        setInstance(this);
    }

    private void setInstance(BaseSingleton baseSingleton)   {
        if (instance != null) return;
        instance = baseSingleton;
    }

    public static <T extends BaseSingleton> T getInstance()   {
        return (T)instance;
    }
}
