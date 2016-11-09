package com.stew.parse;

import com.stew.ConfigProperty;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by stew.bracken on 11/6/16.
 */
public class ParseDestination {

    private String fileName;
    private String parseDirCache;
    private Path pathCache;

    boolean fullFileProvided;

    public ParseDestination(final String fileName){
        this.fileName = fileName;
    }

    public void fullFile(boolean useFullFile){
        fullFileProvided = useFullFile;
    }

    public final Path fileFromLoc(){
        if(pathCache==null){
            pathCache = Paths.get(fullPath());
        }
        return pathCache;
    }

    public String fullPath(){
        if(fullFileProvided){
            return this.fileName;
        }
        return parseDir() + this.fileName;
    }

    private String parseDir(){
        if (parseDirCache==null){
            parseDirCache = ConfigProperty.get().getParseDir();
        }
        return parseDirCache;
    }


}
