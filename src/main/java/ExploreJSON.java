import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Map;
import java.util.Set;

public class ExploreJSON {
    public static void main(String[] args) {
        String jsonString = "{\"base\":\"USD\",\"date\":\"2017-09-15\",\"rates\":{\"RUB\":57.53}}";
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(jsonString);
        JsonObject obj = element.getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();//will return members of your object
        for (Map.Entry<String, JsonElement> entry: entries) {
            if(entry.getValue().isJsonObject()) {
                obj= entry.getValue().getAsJsonObject();
                Set<Map.Entry<String, JsonElement>> secondEntries = obj.entrySet();//will return members of your object
                for(Map.Entry<String, JsonElement> secondEntry: secondEntries) {
                    System.out.println("key " + secondEntry.getKey() + " : " + secondEntry.getValue());
                }
            }
        }
    }
}
