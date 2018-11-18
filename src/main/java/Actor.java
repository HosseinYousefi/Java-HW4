import javax.json.*;
import java.io.StringReader;

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