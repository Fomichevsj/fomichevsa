import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class FirstGradleTest {
    private String request;

    public static void main(String[] args) throws Exception {

        FirstGradleTest http = new FirstGradleTest();

        System.out.println("Start running program");
        http.setRequestString();
        http.sendGet();
    }

    // HTTP GET request
    private void sendGet() throws Exception {



        URL obj = new URL(request);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        //con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("User-Agent","");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + request);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print result
        System.out.println("response to String " + response.toString());
    }

    // Set the string that would be in GET request
    private void setRequestString() {
        Scanner in = new Scanner(System.in);
        String from = new String();
        System.out.println("Enter from currency\n");
        from = in.nextLine();
        String to = new String();
        System.out.println("Enter to currency\n");
        to = in.nextLine();
        this.request = "http://api.fixer.io/latest?base=" + from + "&symbols=" + to;
        System.out.println("request string: " + this.request);
    }
}
