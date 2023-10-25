package br.com.codadocode.codadocore.core;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JsonManager {
    private DataFolder dataFolder;
    private String fileExtension = ".json";

    Gson gson;

    public JsonManager(DataFolder dataFolder)   {
        this.dataFolder = dataFolder;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public boolean saveToFile(String fileName, Object sourceObj) throws IOException {
        File fileObject = new File(this.dataFolder.getSubFolderData(), fileName + fileExtension);
        Writer writer = new FileWriter(fileObject);
        this.gson.toJson(sourceObj, writer);
        writer.close();

        return true;
    }

    private Optional<Object> loadFile(File file, Type typeOfSource) throws FileNotFoundException {
        Reader reader = new FileReader(file);
        Object genericObject = gson.fromJson(reader,  typeOfSource);
        return Optional.of(genericObject);
    }

    public Optional<List<Object>> loadAllFiles(Type typeOfSource) throws FileNotFoundException {
        File[] folderFiles = this.dataFolder.getSubFolderData().listFiles();
        if (folderFiles.length == 0) return Optional.empty();
        List<Object> loadedObjectList = new ArrayList<>();
        for (int i = 0; i < folderFiles.length; i++)   {
            File actualFile = folderFiles[i];
            Optional optionalFile = loadFile(actualFile, typeOfSource);
            if (optionalFile.isPresent())   {
                loadedObjectList.add(optionalFile.get());
            }
        }
        if (loadedObjectList.size() == 0)   return Optional.empty();
        else return Optional.of(loadedObjectList);
    }
}
