import com.mongodb.client.result.DeleteResult;
import com.stew.db.DBLoad;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class MongoConnectTest{
    DBLoad dbl;

    @Before
    public void setup(){

        dbl = new DBLoad();
        dbl.loadDb("test");

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);

        try {
            dbl.getDbTestOnly().getCollection("restaurants").insertOne(
                    new Document("address",
                            new Document()
                                    .append("street", "2 Avenue")
                                    .append("zipcode", "10075")
                                    .append("building", "1480")
                                    .append("coord", asList(-73.9557413, 40.7720266)))
                            .append("borough", "Manhattan")
                            .append("cuisine", "Italian")
                            .append("grades", asList(
                                    new Document()
                                            .append("date", format.parse("2014-10-01T00:00:00Z"))
                                            .append("grade", "A")
                                            .append("score", 11),
                                    new Document()
                                            .append("date", format.parse("2014-01-16T00:00:00Z"))
                                            .append("grade", "B")
                                            .append("score", 17)))
                            .append("name", "Vella")
                            .append("restaurant_id", "41704620"));
        } catch(ParseException e){
            System.out.println(e.getStackTrace());
        }
    }

    @Test
    public void iterateAllTest() {

//        db.getCollection("restaurants").find().forEach((Block<Document>) document -> {
//            document.
//        });
    }

    @After
    public void tearDown(){
        DeleteResult dres = dbl.getDbTestOnly().getCollection("restaurants").deleteOne(new Document("restaurant_id", "41704620"));
        assertEquals(1, dres.getDeletedCount());
        System.out.print("Successfully dropped lines "+dres.getDeletedCount());
    }
}