import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.json.*;
import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MovieTest extends TestCase {

	Movie movie;
	String json;

	@Before
	public void setUp() throws Exception {
		movie = Movie.fromFile("BladeRunner.json");
	}

	@Test
	public void testMovieTitle() {
		System.out.println(movie.title);
		assertEquals(movie.title, "Blade Runner");
	}

	@Test
	public void testMovieDirector() {
		assertEquals(movie.director.name, "Ridley Scott");
	}

	@Test
	public void testIMDBVoters() {
		assertEquals(movie.ratings[0].votes, 602079);
	}

	@Test
	public void testRottenTomatoVoters() {
		assertEquals(movie.ratings[1].votes, -1);
	}

	@Test
	public void testJson() {
		try {
			InputStream fis = new FileInputStream("BladeRunner.json");
			JsonReader reader = Json.createReader(fis);
			String json = reader.readObject().toString();
			assertEquals(movie.toJsonString(), json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
