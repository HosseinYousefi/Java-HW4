import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class MoviesHelperTest extends TestCase {

    Movie[] movies;

    @Before
    public void setUp() throws Exception {
		movies = new Movie[3];
		String[] files = {"BladeRunner.json", "GG.json", "Titanic.json"};
		for (int i = 0; i < 3; ++i)
			movies[i] = Movie.fromFile(files[i]);
    }

    @Test
    public void testSortByYear() {
        List<String> sorted = MoviesHelper.sortByYear(movies).stream()
                .map(m -> m.title)
                .collect(Collectors.toList());
        assertEquals(sorted.get(0), "Blade Runner");
        assertEquals(sorted.get(1), "Titanic");
        assertEquals(sorted.get(2),
        "Guardians of the Galaxy Vol. 2");
    }

    @Test
    public void testSortByRunTime() {
        List<String> sorted = MoviesHelper.sortByRuntime(movies).stream()
                .map(m -> m.title)
                .collect(Collectors.toList());
        assertEquals(sorted.get(0), "Blade Runner");
        assertEquals(sorted.get(1),
                "Guardians of the Galaxy Vol. 2");
        assertEquals(sorted.get(2), "Titanic");
    }

    @Test
    public void testSortByIMDB() {
        try {
            List<String> sorted = MoviesHelper.sortByRating(movies, "Internet Movie Database").stream()
                    .map(m -> m.title)
                    .collect(Collectors.toList());
            assertEquals(sorted.get(0),
                    "Guardians of the Galaxy Vol. 2");
            assertEquals(sorted.get(1), "Titanic");
            assertEquals(sorted.get(2), "Blade Runner");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testSortByMetacritic() {
        try {
            List<String> sorted = MoviesHelper.sortByRating(movies, "Metacritic").stream()
                    .map(m -> m.title)
                    .collect(Collectors.toList());
            assertEquals(sorted.get(0),
                    "Guardians of the Galaxy Vol. 2");
            assertEquals(sorted.get(1), "Titanic");
            assertEquals(sorted.get(2), "Blade Runner");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testFilterByDirector() {
        List<String> filtered = MoviesHelper.filterByDirector(movies, "James Cameron").stream()
                .map(m -> m.title)
                .collect(Collectors.toList());
        assertEquals(filtered.get(0), "Titanic");
        assertEquals(filtered.size(), 1);
    }

    @Test
    public void testFilterByActor() {
        List<String> filtered = MoviesHelper.filterByActor(movies, "Leonardo DiCaprio").stream()
                .map(m -> m.title)
                .collect(Collectors.toList());
        assertEquals(filtered.get(0), "Titanic");
        assertEquals(filtered.size(), 1);
    }

    @Test
    public void testFilterByGenre() {
        List<String> filtered = MoviesHelper.filterByGenre(movies, "Sci-Fi").stream()
                .map(m -> m.title)
                .collect(Collectors.toList());
        assertEquals(filtered.size(), 2);
        assertFalse(filtered.contains("Titanic"));
    }
}
