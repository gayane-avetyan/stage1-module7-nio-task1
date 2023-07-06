package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FileReader {

    private static final Logger logger = Logger.getLogger(FileReader.class.getName());

    public Profile getDataFromFile(File file) {

        Profile profile = new Profile();

        try(BufferedReader reader = Files.newBufferedReader(file.toPath(),StandardCharsets.UTF_8)){

            String line = null;
            while ((line = reader.readLine()) != null){

                String[] split = line.split(":");
                String key = split[0].trim();
                String value = split[1].trim();

                switch (key){
                    case "Name":
                        profile.setName(value);
                        break;
                    case "Age":
                        profile.setAge(Integer.parseInt(value));
                        break;
                    case "Email":
                        profile.setEmail(value);
                        break;
                    case "Phone":
                        profile.setPhone(Long.parseLong(value));
                        break;
                    default:
                        break;
                }
            }
        }
        catch (IOException ex){
            logger.log(Level.WARNING, ex.getMessage());
        }

        return profile;
    }
}
