import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.stew.db.DBLoad;
import com.stew.parse.POEParse;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by stew.bracken on 11/9/16.
 */
public class PoeDBTest {
    DBLoad dbl;
    final String DATABASE_NAME = "poetest";
    final String COLLECTION_NAME = "stashetest";

    @Before
    public void setup() {
        dbl = new DBLoad(); //loads test
    }

    @Test
    public void testDBLoad(){

        dbl.loadDb(DATABASE_NAME);

        //load test data
        POEParse ps = POEParse.testInit("test/resources/sample_poe.json");
        dbl.initialDbLoad(COLLECTION_NAME,ps.parse()); //load json data into new collection
        FindIterable<Document> res = dbl.find();
        for (Document stashDoc : res){

            stashDoc.get("items");
            //new StashBuilder().fromBsonDoc(stashDoc);
        }
        res.forEach( new Block<Document>() {
                         @Override
                         public void apply(final Document d) {
                             System.out.println(d.get("items").getClass());
                         }
                     } );
    }

    @After
    public void tearDown(){
        dbl.getDbTestOnly().getCollection(COLLECTION_NAME).drop();
        assertEquals(null, dbl.getDbTestOnly().getCollection(COLLECTION_NAME));
        //System.out.print("Successfully dropped lines "+dres.getDeletedCount());
    }
}