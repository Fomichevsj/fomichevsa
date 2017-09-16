import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.junit.Test;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Bucket {
    @Test
    public void step() {
        HashMap<CurrencyPair,Double> o = null;

        FileInputStream fileIn = null;
        try {
            fileIn = new FileInputStream("/home/sergey/currency.ser");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(fileIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            o =   (HashMap<CurrencyPair,Double>) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(o.size() > 0) {
            System.out.println("size of map = " + o.size());
            for (Map.Entry entry : o.entrySet()) {
                CurrencyPair myPair = (CurrencyPair) entry.getKey();
                System.out.println("additional key_field: " + myPair.getRate());
                System.out.println("Key: " + myPair.getFirstCurrency() + " | " + myPair.getSecondCurrency() + " Value: "
                        + entry.getValue());
            }
        } else {
            System.out.println("You haven't make a requests");
        }

    }
}
