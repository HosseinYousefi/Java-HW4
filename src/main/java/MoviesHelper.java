import java.util.Comparator;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MoviesHelper {

	// SORTS
	public static List<Movie> sortBy(Movie[] movies, Comparator<Movie> comp) {
		List<Movie> result = Arrays.asList(movies);
		result.sort(comp);
		return result;
	}

	public static List<Movie> sortByYear(Movie[] movies) {
		return sortBy(movies, (a, b) -> a.year - b.year);
	}

	public static List<Movie> sortByRuntime(Movie[] movies) {
		return sortBy(movies, (a, b) -> a.runtime - b.runtime);
	}

	public static List<Movie> sortByRating(Movie[] movies, String source) throws Exception {
		boolean test = Arrays.asList(movies).stream().allMatch(m ->
				Arrays.asList(m.ratings)
						.stream()
						.anyMatch(r -> r.source == source));
		if (!test)
			throw new Exception("Incorrect Source: " + source);
		return sortBy(movies, (a, b) -> {
			List<Movie.Rating> arate = Arrays.asList(a.ratings);
			List<Movie.Rating> brate = Arrays.asList(b.ratings);
			arate = arate.stream().filter(r -> r.source == source).collect(Collectors.toList());
			brate = brate.stream().filter(r -> r.source == source).collect(Collectors.toList());
			return arate.get(0).value.compareTo(brate.get(0).value);
		});
	}

	// FILTERS
	public static List<Movie> filterBy(Movie[] movies, Predicate<Movie> pred) {
		List<Movie> result = Arrays.asList(movies);
		return result.stream().filter(pred).collect(Collectors.toList());
	}

	public static List<Movie> filterByDirector(Movie[] movies, String directorName) {
		return filterBy(movies, m -> m.director.name == directorName);
	}

	public static List<Movie> filterByActor(Movie[] movies, String actorName) {
		return filterBy(movies, m -> Arrays.asList(m.actors).stream()
				.anyMatch(a -> a.name == actorName));
	}

	public static List<Movie> filterByGenre(Movie[] movies, String genre) {
		return filterBy(movies, m -> Arrays.asList(m.genres).contains(genre));
	}
}
