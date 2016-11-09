import com.stew.record.Stash;
import com.stew.record.StashBuilder;
import org.junit.Test;

/**
 * Created by stew.bracken on 11/7/16.
 */
public class StashTest {

    @Test
    public void stashBuilderTest(){
        StashBuilder builder = new StashBuilder();
        builder.name("name");
        builder.accountName("account name");
        builder.id("id");
        builder.type("type");
        Stash s = builder.build();


    }
}
