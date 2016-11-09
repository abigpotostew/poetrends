package com.stew.util;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by stew.bracken on 11/6/16.
 */
public class SFileUtil {

    /**
     * Downloads specified URL to file destination
     * @param Url
     * @param dst
     * @return
     */
    public static boolean curl (final String Url, final File dst){
        Log.log(String.format("Downloading \"%s\" to file \"%s\"", Url, dst));
        try {
            URL website = new URL(Url);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(dst);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (Exception e){
            Log.log(e.getStackTrace().toString());
            return false;
        }
        return true;
    }
}
