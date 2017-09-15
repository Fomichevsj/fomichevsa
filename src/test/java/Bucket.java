import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.junit.Test;


import java.util.Map;
import java.util.Set;

public class Bucket {
    @Test
    public void step() {
        System.out.println("test");
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(RateObject.class,new RatesDeserializer())
                .create();

    }
}
