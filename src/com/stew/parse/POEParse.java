package com.stew.parse;

import com.stew.record.Stash;
import com.stew.record.StashBuilder;
import com.stew.util.Log;
import com.stew.util.SFileUtil;
import com.stew.util.SUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by stew.bracken on 11/6/16.
 * processes a full public stash api result
 */
public class POEParse {
    final String changeId;
    final ParseDestination dataLoc;
    String nextChangeId;

    /**
     * @param changeId changeId for this parsed data
     * @param dst
     */
    private POEParse(final String changeId, final ParseDestination dst){
        this.changeId = changeId;
        this.dataLoc = dst;
    }



    /**
     * Downloads stash data from POE.com and saves to a file.
     * @param changeId next change id to query poe api, ok null
     * @return new POEParse instance with link to stash data location on OS
     */
    public static POEParse init(String changeId){
        String url = "http://www.pathofexile.com/api/public-stash-tabs";
        if ( !SUtil.NullEmpty(changeId) ){
            url += "?id=" + changeId;
        }
        System.currentTimeMillis();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH.mm.ss", Locale.ENGLISH);
        String dtm = format.format(Calendar.getInstance().getTime());

        final String dataFileName = "poe"+dtm+".json";
        ParseDestination pd = new ParseDestination(dataFileName);
        //download and save to file
        SFileUtil.curl(url, pd.fileFromLoc().toFile());

        return new POEParse(changeId, pd);
    }

    /**
     * new instance that will point to a test poe stash file
     * @param fileLoc
     * @return new instance, with not yet parsed data.
     */
    public static POEParse testInit(String fileLoc){
        ParseDestination pdt = new ParseDestination(fileLoc);
        pdt.fullFile(true);
        return new POEParse(null,pdt);
    }

    public List<Stash> parse(){
        assert(dataLoc!=null);
        //Arrays.asList
        // ArrayList.stream()
        //Stream<String>
        StringBuilder sb = new StringBuilder();
        try {
            Files.lines(dataLoc.fileFromLoc(), StandardCharsets.UTF_8).forEach(f -> sb.append(f));
        }catch(IOException e){
            Log.log("ERROR: Parsing json file failed! - "+dataLoc.fileFromLoc());
        }
        JSONObject obj = new JSONObject(sb.toString());
        nextChangeId = obj.getString("next_change_id");
        JSONArray stashes = obj.getJSONArray("stashes");
        //System.out.println(stashes.getClass()+" - " + stashes.toList().getClass());
        return toList(stashes);
    }

    ArrayList<Stash> toList (JSONArray ar){
        ArrayList<Stash> out = new ArrayList();
        for(Object o:ar){
            if(o instanceof JSONObject )out.add(
                    new StashBuilder().fromJson((JSONObject)o).build()
            );
        }
        return out;
    }
}
