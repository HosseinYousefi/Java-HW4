import javax.json.*;
import java.io.*;

public class Movie implements Jsonable{

    public class Writer implements Jsonable{

        String name;
        String type;

        @Override
        public JsonObject toJsonObject() {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("Name", name);
            objectBuilder.add("Type", type);
            return objectBuilder.build();
        }

        @Override
        public String toJsonString() {
            return toJsonObject().toString();
        }

        @Override
        public void fromJson(String json) {
            JsonReader reader = Json.createReader(new StringReader(json));
            JsonObject jObject = reader.readObject();
            this.name = jObject.getString("Name");
            this.type = jObject.getString("Type");
        }
    }

    public class Director implements Jsonable{

        String name;

        @Override
        public JsonObject toJsonObject() {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("Name", name);
            return objectBuilder.build();
        }

        @Override
        public String toJsonString() {
            return toJsonObject().toString();
        }

        @Override
        public void fromJson(String json) {
            JsonReader reader = Json.createReader(new StringReader(json));
            JsonObject jObject = reader.readObject();
            this.name = jObject.getString("Name");
        }
    }

    public class Actor implements Jsonable {

        String name;
        String as;

        @Override
        public JsonObject toJsonObject() {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("Name", name);
            objectBuilder.add("As", as);
            return objectBuilder.build();
        }

        @Override
        public String toJsonString() {
            return toJsonObject().toString();
        }

        @Override
        public void fromJson(String json) {
            JsonReader reader = Json.createReader(new StringReader(json));
            JsonObject jObject = reader.readObject();
            this.name = jObject.getString("Name");
            this.as = jObject.getString("As");
        }
    }

    public static class Rating implements Jsonable {

        String source;
        String value;
        int votes = -1;

        @Override
        public JsonObject toJsonObject() {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("Source", source);
            objectBuilder.add("Value", value);
            if (votes != -1)
                objectBuilder.add("Votes", votes);
            return objectBuilder.build();
        }

        @Override
        public String toJsonString() {
            return toJsonObject().toString();
        }

        @Override
        public void fromJson(String json) {
            JsonReader reader = Json.createReader(new StringReader(json));
            JsonObject jObject = reader.readObject();
            this.source = jObject.getString("Source");
            this.value = jObject.getString("Value");
            try {
                this.votes = jObject.getInt("Votes");
            } catch (Exception e) {
                // f it!
            }
        }
    }

    String title;
    int year;
    String released;
    int runtime;
    String[] genres;
    Director director;
    Writer[] writers;
    Actor[] actors;
    String plot;
    String[] languages;
    String[] countries;
    String awards;
    String poster;
    Rating[] ratings;

    static public JsonArray makeJsonArrayFrom(String[] arr) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (String str: arr)
            arrayBuilder.add(str);
        return arrayBuilder.build();
    }

    static public<T extends Jsonable> JsonArray makeJsonArrayFrom(T[] arr) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (T str: arr)
            arrayBuilder.add(str.toJsonObject());
        return arrayBuilder.build();
    }

    static public JsonArray makeJsonArrayFromStringArray(String[] arr) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (String str: arr)
            arrayBuilder.add(str);
        return arrayBuilder.build();
    }

    public String[] toStringArr(JsonArray json) {
        String[] arr = new String[json.size()];
        for (int i = 0; i < json.size(); ++i)
            arr[i] = json.getString(i);
        return arr;
    }

    public static Movie fromFile(String path) throws Exception {
        Movie movie = new Movie();
        InputStream fis = new FileInputStream(path);
        JsonReader reader = Json.createReader(fis);
        String json = reader.readObject().toString();
        movie.fromJson(json);
        return movie;
    }

    @Override
    public JsonObject toJsonObject() {
        return Json.createObjectBuilder()
                .add("Title", title)
                .add("Year", year)
                .add("Released", released)
                .add("Runtime", runtime)
                .add("Genres", makeJsonArrayFromStringArray(genres)) // TODO: fix
                .add("Director", director.toJsonObject())
                .add("Writers", makeJsonArrayFrom(writers))
                .add("Actors", makeJsonArrayFrom(actors))
                .add("Plot", plot)
                .add("Languages", makeJsonArrayFrom(languages))
                .add("Countries", makeJsonArrayFrom(countries))
                .add("Awards", awards)
                .add("Poster", poster)
                .add("Ratings", makeJsonArrayFrom(ratings))
                .build();
    }

    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }

    @Override
    public void fromJson(String json) {
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject jObject = reader.readObject();
        this.title = jObject.getString("Title");
        this.year = jObject.getInt("Year");
        this.released = jObject.getString("Released");
        this.runtime = jObject.getInt("Runtime");
        this.genres = toStringArr(jObject.getJsonArray("Genres"));
        this.director = new Director();
        this.director.fromJson(jObject.getJsonObject("Director").toString());

        JsonArray writersArr = jObject.getJsonArray("Writers");
        this.writers = new Writer[writersArr.size()];
        for (int i = 0; i < writersArr.size(); ++i) {
            this.writers[i] = new Writer();
            this.writers[i].fromJson(writersArr.getJsonObject(i).toString());
        }
        JsonArray actorsArr = jObject.getJsonArray("Actors");
        this.actors = new Actor[actorsArr.size()];
        for (int i = 0; i < actorsArr.size(); ++i) {
            this.actors[i] = new Actor();
            this.actors[i].fromJson(actorsArr.getJsonObject(i).toString());
        }

        this.plot = jObject.getString("Plot");
        this.languages = toStringArr(jObject.getJsonArray("Languages"));
        this.countries = toStringArr(jObject.getJsonArray("Countries"));
        this.awards = jObject.getString("Awards");
        this.poster = jObject.getString("Poster");

        JsonArray ratingsArr = jObject.getJsonArray("Ratings");
        this.ratings = new Rating[ratingsArr.size()];
        for (int i = 0; i < ratingsArr.size(); ++i) {
            this.ratings[i] = new Rating();
            this.ratings[i].fromJson(ratingsArr.getJsonObject(i).toString());
        }
    }

    public static void main(String... args) {

    }
}
