package com.stew;

import com.stew.util.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by stew.bracken on 11/6/16.
 */
public final class ConfigProperty {

    static ConfigProperty loadedConfig;

    private String parsedir;

    private ConfigProperty() { } //singleton

    private static void Log(final String s){
        Log.log(s);
    }

    public String getParseDir(){
        return parsedir;//System.getProperty("user.dir");
    }

    private void init() {
        final Properties prop = new Properties();
        final String configDir = System.getProperty("user.dir") + "/src/config.properties";
        FileInputStream inputStream = null;
        try {
            Log("reading config.properties from dir:\n" + configDir);
            inputStream = new FileInputStream(configDir);
            // load a properties file
            prop.load(inputStream);
            // get the property value and print it out
            this.parsedir = prop.getProperty("parsedir");

            Log(String.format("Properties read from config.properties file: {"
                    + "\n\t%s: %s\n\t}", "parsedir", this.parsedir ));
        }
        catch (IOException ex) {
            System.out.println("Error reading from config.properties:\n" + ex.getMessage());
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                    Log("Closed inputstream for config.properties:\n");
                }
                catch (IOException e) {
                    Log("Exception closing stream config.properties:\n" + e.getMessage());
                }
            }
            else {
                Log("No inputstream for config.properties:\n");
            }
        }
    }

    public static ConfigProperty get(){
        if (loadedConfig==null){
            loadedConfig = new ConfigProperty();
            loadedConfig.init();
        }
        return loadedConfig;
    }
}
