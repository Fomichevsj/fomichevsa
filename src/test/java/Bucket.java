import com.google.gson.JsonElement;
import org.junit.Test;


import java.util.Map;
import java.util.Set;

public class Bucket {
    @Test
    public void step() {
        System.out.println("test");
        JsonElement json = null ;
        Set<Map.Entry<String, JsonElement>> entries = json.getAsJsonObject().entrySet();
    }
}
