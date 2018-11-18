import javax.json.*;
import java.io.StringReader;

public class Director implements Jsonable {

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