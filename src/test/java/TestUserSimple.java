import com.google.gson.Gson;
import org.junit.Test;

/**
 * Created by safomichev on 15.09.2017.
 */
public class TestUserSimple {
    @Test
    public void step() {
        System.out.println("Start testing simple user");
        serializeUserSimple();
        deserializeUserSimple();
    }

    private void serializeUserSimple() {
        UserSimple userSimple = new UserSimple(
                "Sergey",
                "fomichevsj@gmail.com",
                22,
                true);
        Gson gson = new Gson();
        String json = gson.toJson(userSimple);
        System.out.println(json);
    }

    private void deserializeUserSimple() {
        String userJson = "{\"name\":\"Sergey\",\"email\":\"fomichevsj@gmail.com\",\"age\":22,\"isDeveloper\":true}";
        Gson gson = new Gson();
        UserSimple userSimple = gson.fromJson(userJson,UserSimple.class);
        System.out.println("hi");
    }
}
