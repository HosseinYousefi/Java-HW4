import com.mongodb.BasicDBObject;
import junit.framework.TestCase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.json.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static com.mongodb.client.model.Filters.*;

public class MovieManagerTest extends TestCase {


    /*** THESE TESTS WORK ON MY COMPUTER ***\
    MovieManager movieManager;

    @Before
    public void setUp() throws Exception {
        String files[] = {"Titanic.json", "BladeRunner.json"};
        movieManager = new MovieManager();
        movieManager.createDB(files);
    }

    @Test
    public void testFindQuery() {
        Bson query = eq("Year", 1982);
        String title = movieManager.execFindQuery(query,
                result -> result.first().getString("Title"));

        assertEquals("Blade Runner", title);
    }

    @Test
    public void testAddQuery() throws FileNotFoundException {
        String json = MoviesHelper.readJsonFromFile("GG.json");
        Document doc = Document.parse(json);
        List<Document> l = new ArrayList<>();
        l.add(doc);
        movieManager.execAddQuery(l);

        Bson query = regex("Title", "Guardians");
        int year = movieManager.execFindQuery(query,
                result -> result.first().getInteger("Year"));

        assertEquals(2017, year);

    }
    */
}
