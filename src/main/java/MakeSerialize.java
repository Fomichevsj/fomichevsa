import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

public class MakeSerialize {
    public void printIfno(Map<CurrencyPair,Double> currencyPairMap) {
        if(currencyPairMap.size() > 0) {
            System.out.println("size of map = " + currencyPairMap.size());
            for (Map.Entry entry : currencyPairMap.entrySet()) {
                CurrencyPair myPair = (CurrencyPair) entry.getKey();
                System.out.println("additional key_field: " + myPair.getRate());
                System.out.println("Key: " + myPair.getFirstCurrency() + " | " + myPair.getSecondCurrency() + " Value: "
                        + entry.getValue());
            }
        } else {
            System.out.println("You haven't make a requests");
        }
    }

    public void serialize(Map<CurrencyPair,Double> e) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("/home/sergey/currency.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(e);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in ~/currency.ser");
        }catch(IOException i) {
            System.out.println("Can't write into document");
        }
    }

/*    public Map<CurrencyPair,Double> deserialize() {

    }*/

}