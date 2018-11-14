import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.json.*;
import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MoviesHelperTest extends TestCase {

    Movie[] movies;

    @Before
    public void setUp() throws Exception {
		movies = new Movie[1];
		String[] files = {"Titanic.json"};//, "GG.json", "Titanic.json"};
		for (int i = 0; i < 1; ++i) {
            System.out.println(i);
			movies[i] = Movie.fromFile(files[i]);
		}
		System.out.println("Finished!");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void teststh() {
        ;
    }
}
