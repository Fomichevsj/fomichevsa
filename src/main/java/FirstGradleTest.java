import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FirstGradleTest {
    private String request;
    private static Map<CurrencyPair,Double> currencyPairMap;// HasMap for localMemory
    private static boolean isWorking = true;// is we going to work or quit
    private static boolean sendRequest = true;// is we need send request or fetch from local memory
    private static MakeSerialize makeSerialize;// implements print info and serialize
    private static HttpURLConnection con;// connection
    private static BufferedReader in;

    public static void main(String[] args) throws Exception {
        FirstGradleTest http = new FirstGradleTest();
        currencyPairMap = new HashMap<>();// HasMap for localMemory
        makeSerialize = new MakeSerialize();
        ApiResponse apiResponse;// Response from the server
        Scanner inScanner = new Scanner(System.in);
        String from = new String();// from currency
        String to = new String();// to currency
        HelpWelcome.hi();
        try {
            DesserializeMap.read();
        } catch (FileNotFoundException e1) {
            System.out.println("File not found. no load from file");
        }
        while(isWorking) {
            System.out.println("Enter from currency");
            from = inScanner.nextLine().toUpperCase();
            if(from.equals("EXIT")) {
                System.out.println("thank you, bye");
                isWorking = false;
                makeSerialize.printIfno(currencyPairMap);// print info
                makeSerialize.serialize(currencyPairMap);// save on disk
                return;
            } else if(from.equals("HELP")) {
                HelpInfo.help();
                continue;
            }
            System.out.println("Enter to currency");
            to = inScanner.nextLine().toUpperCase();
            if(to.toUpperCase().equals("EXIT")) {
                System.out.println("thank you, bye");
                isWorking = false;
                makeSerialize.printIfno(currencyPairMap);// print info
                makeSerialize.serialize(currencyPairMap);// save on disk
                return;
            } else if(to.equals("HELP")) {
                HelpInfo.help();
                continue;
            }
            http.setRequestString(from, to);// create the string for request. set request field
            if (sendRequest) {// should we send request, or we have already have info from local memory and print it
                try {
                    apiResponse = http.sendGet();// send request to server and get apiResponse object
                    apiResponse.print();// print response
                } catch (Exception e) {
                    System.out.println("Something has gone wrong. press enter to continue or print exit for EXIT");
                    String localUserInput = new Scanner(System.in).nextLine().toUpperCase();
                    if (localUserInput.
                            equals("EXIT")) {
                        makeSerialize.printIfno(currencyPairMap);// print info
                        makeSerialize.serialize(currencyPairMap);// save on disk
                        System.out.println("bye");
                        in.close();
                        con.disconnect();
                        return;
                    } else if(localUserInput.equals("HELP")) {
                        HelpInfo.help();
                        continue;
                    }
                }
            } else {
                sendRequest = true;// set sendRequest field back in true position
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
            makeSerialize.printIfno(currencyPairMap);
            return null;
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
    private void setRequestString(String from, String to) throws Exception{
        CurrencyPair findCurrencyPair = new CurrencyPair(from,to);
        // if we found key in our local memory. we don't need to send request
        if(currencyPairMap.containsKey(findCurrencyPair)) {
            System.out.println("Info from log");
            double rate = currencyPairMap.get(findCurrencyPair);
            System.out.println(findCurrencyPair.getFirstCurrency() + " => " +
            findCurrencyPair.getSecondCurrency() + " : " + rate);
            sendRequest = false;
        }
        this.request = "http://api.fixer.io/latest?base=" + from + "&symbols=" + to;// set the request field
    }
}
