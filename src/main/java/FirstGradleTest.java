import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FirstGradleTest {
    private String request;
    private static Map<CurrencyPair,Double> currencyPairMap;
    private static boolean isWorking = true;
    private static boolean sendRequest = true;
    private static HttpURLConnection con;
    private static BufferedReader in;

    public static void main(String[] args) throws Exception {
        FirstGradleTest http = new FirstGradleTest();
        currencyPairMap = new HashMap<>();
        ApiResponse apiResponse;

        while(isWorking) {
            http.setRequestString();
            if (sendRequest) {
                try {
                    apiResponse = http.sendGet();
                    apiResponse.print();
                } catch (Exception e) {
                    System.out.println("Something has gone wrong. press enter to continue or print exit for EXIT");
                    if (new Scanner(System.in).nextLine().toLowerCase().
                            equals("exit")) {
                        //TODO вставить сюда вывод конечной информации
                        System.out.println("bye");
                        in.close();
                        con.disconnect();
                        return;
                    }
                }
            } else {
                sendRequest = true;
                System.out.println("please, continue");
            }
        }
    }

    /**
     * HTTP GET request
     * @throws Exception
     */
    private ApiResponse sendGet() throws Exception {
        if(!isWorking) {
            System.out.println("size of map = " + currencyPairMap.size());
            for (Map.Entry entry : currencyPairMap.entrySet()) {
                CurrencyPair myPair = (CurrencyPair) entry.getKey();
                System.out.println("additional key_field: " + myPair.getRate());
                System.out.println("Key: "  + myPair.getFirstCurrency() + " | " + myPair.getSecondCurrency() +  " Value: "
                        + entry.getValue());
            }
            return null;
        }
        else {
            System.out.println("Please, go on");
        }
        URL obj = new URL(request);
        con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        //con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("User-Agent","");

        int responseCode = con.getResponseCode();

        in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        WaitIndicator waitIndicator;
        waitIndicator = new WaitIndicator();
        Thread thread = new Thread(waitIndicator);
        //thread.setPriority(Thread.MIN_PRIORITY);
        //thread.run();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        //print result
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(RateObject.class,new RatesDeserializer())
                .create();
        ApiResponse apiResponse;
        apiResponse = gson.fromJson(response.toString(), ApiResponse.class);
        currencyPairMap.put(apiResponse.getCurrencyPair(),apiResponse.getRate());
        return apiResponse;
        //waitIndicator.setStop();
        //System.out.println("response to String " + response.toString());
    }

    /**
     *  Set the string that would be in GET request
     * @throws Exception
     */
    private void setRequestString() throws Exception{
        Scanner in = new Scanner(System.in);
        String from = new String();
        System.out.println("Enter from currency");
        from = in.nextLine().toUpperCase();
        if(from.equals("EXIT")) {
            System.out.println("thank you, bye");
            isWorking = false;
            return;
        }
        String to = new String();
        System.out.println("Enter to currency");
        to = in.nextLine().toUpperCase();
        if(to.toUpperCase().equals("EXIT")) {
            System.out.println("thank you, bye");
            isWorking = false;
            return;
        }
        CurrencyPair findCurrencyPair = new CurrencyPair(from,to);
        if(currencyPairMap.containsKey(findCurrencyPair)) {
            System.out.println("Info from log");
            double rate = currencyPairMap.get(findCurrencyPair);
            System.out.println(findCurrencyPair.getFirstCurrency() + " => " +
            findCurrencyPair.getSecondCurrency() + " : " + rate);
            sendRequest = false;
        }
        this.request = "http://api.fixer.io/latest?base=" + from + "&symbols=" + to;
    }
}
