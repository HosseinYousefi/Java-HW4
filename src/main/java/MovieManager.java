import com.mongodb.BasicDBObject;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.json.Json;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import static com.mongodb.client.model.Filters.*;


public class MovieManager {

    MongoClient mongoClient;
    MongoDatabase movieDatabase;

    MongoCollection collection;
    final String DATABASE_NAME = "MoviesDatabase";
    final String COLLECTION_NAME = "movies";

    public MovieManager(){
        mongoClient = new MongoClient();
        movieDatabase = mongoClient.getDatabase(DATABASE_NAME);
        collection = movieDatabase.getCollection(COLLECTION_NAME);
    }

    public <T> T execFindQuery(Bson query, ResultHandler<T> handler) {
        FindIterable<Document> result = collection.find(query);
        T res = handler.handle(result);
        mongoClient.close();
        return res;
    }

    public void execAddQuery(List l) {
        collection.insertMany(l);
    }

    public MongoIterable<Document> createDB(String[] files) throws Exception {
        collection.drop();

        for (String file: files) {
            String json = MoviesHelper.readJsonFromFile(file);
            Document doc = Document.parse(json);
            collection.insertOne(doc);
        }

        MongoIterable<Document> moviesCollection = collection.find();

        return moviesCollection;
    }

}