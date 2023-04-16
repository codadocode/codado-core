package br.com.codadocode.codadocore.core;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
        this.gson = new Gson();
    }

    public boolean saveToFile(String fileName, Object sourceObj, Type typeOfSource)   {
        String jsonObject = this.gson.toJson(sourceObj, typeOfSource);
        try   {
            FileWriter fileWriter = new FileWriter(this.dataFolder.getSubFolderData().getAbsolutePath() + "/" + fileName + this.fileExtension);
            fileWriter.write(jsonObject);
            fileWriter.close();
            return true;
        }catch(Exception e)   {
            e.printStackTrace();
        }
        return false;
    }

    private Optional<Object> loadFile(File file, Type typeOfSource)   {
        try   {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            if (reader == null)   return Optional.empty();
            Object object = this.gson.toJson(reader, typeOfSource);
            return Optional.of(typeOfSource.getClass().cast(object));

        }catch (Exception e)   {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<List<Object>> loadAllFiles(Type typeOfSource)   {
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
