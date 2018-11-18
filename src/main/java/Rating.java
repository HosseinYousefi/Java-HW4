import javax.json.*;
import java.io.StringReader;

public class Rating implements Jsonable {

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