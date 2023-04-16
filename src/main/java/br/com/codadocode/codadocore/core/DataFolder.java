package br.com.codadocode.codadocore.core;

import java.io.File;

public class DataFolder {
    private File subFolderData;

    public DataFolder(File dataFolder, String subFolderName)   {
        this.subFolderData = new File(dataFolder.getAbsolutePath() + "/" + subFolderName);
        if (!this.subFolderData.exists()) this.subFolderData.mkdirs();
    }

    public File getSubFolderData()   {
        return this.subFolderData;
    }
}
