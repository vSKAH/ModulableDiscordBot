package fr.skah.mdb.utils;

/*
 *  * @Created on jeudi/avril/2021 - 07:53
 *  * @Project ModulesDiscordBot
 *  * @Author Jimmy
 */

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonManager {

    public <T> T load(File file, Class<T> tClass) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(file, tClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(File file, Object object) {

        try {
            if (!file.exists()) file.createNewFile();
            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(file, object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}