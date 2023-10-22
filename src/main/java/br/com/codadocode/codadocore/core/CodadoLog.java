package br.com.codadocode.codadocore.core;

import org.bukkit.Bukkit;

import java.util.logging.Level;

public class CodadoLog {
    private String logName;

    public CodadoLog(String logName)   {
        this.logName = logName;
    }

    public void showInfo(String msg)   {
        Bukkit.getLogger().info(this.logName + ": " + msg);
    }

    public void showWarning(String warningMsg)   {
        Bukkit.getLogger().warning(this.logName + ": " + warningMsg);
    }

    public void showSevere(String severeMsg)   {
        Bukkit.getLogger().severe(this.logName + ": " + severeMsg);
    }

    public void show(Level logLevel, String msg)   {
        Bukkit.getLogger().log(logLevel, this.logName + ": " + msg);
    }
}
