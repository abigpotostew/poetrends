import com.stew.parse.POEParse;
import com.stew.record.Stash;
import org.junit.After;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

/**
 * Created by stew.bracken on 11/6/16.
 */
public class POEParseTest {

    @Test
    public void downloadTest(){
        POEParse parse = POEParse.init(null);
//        File[] fs = new File("/Users/stew.bracken/Documents/Code/poetrends/data/").listFiles(new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                return name.matches("poe(.*).json");
//            }
//        });
//        testFile=fs[0];

        //assertTrue(new File("/Users/stew.bracken/Documents/Code/poetrends/poe").canRead)
    }

    @Test
    public void jsonParseTest(){
        POEParse ps = POEParse.testInit("test/resources/sample_poe.json");

        Stream<Stash> stream = ps.parse().stream();
        List<String> accounts = Arrays.asList("KronicTide", "mortimermcmire", "apexmateria1");
        List<Stash> res = stream.filter(obj -> obj != null )
                .filter(json -> accounts.contains(json.accountName() ))
                .collect(Collectors.toList());
        List<String> res_backwards = accounts.stream()
                                    .filter(acc -> Stash.contains(acc, res))
                                    .collect(Collectors.toList());
        System.out.println(res_backwards.size());
        assertTrue(res_backwards.size() == 3);
    }

    @After
    public void teardown(){
//        if(deleteTestFile){
//            try {
//                Files.deleteIfExists(testFile.toPath());
//            }catch(IOException e){
//                Log.log("Can't delete test file : "+testFile.toPath().toString());
//            }
//        }
    }
}
