import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

public class DesserializeMap {
    public static void read() throws FileNotFoundException, IOException, ClassNotFoundException{

        HashMap<CurrencyPair,Double> o = null;
        FileInputStream fileIn = null;
        fileIn = new FileInputStream("~/currency.ser");
        ObjectInputStream in = null;
        in = new ObjectInputStream(fileIn);
        o =   (HashMap<CurrencyPair,Double>) in.readObject();
        in.close();
        fileIn.close();
    }
}
