package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan
        String studentNr = "014467642";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats2017.herokuapp.com/students/" + studentNr + "/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();
        
        //System.out.println("json-muotoinen data:");
        //System.out.println(bodyText);
        
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        System.out.println("opiskelijanumero: " + studentNr + "\n");
        for (Submission submission : subs) {
            System.out.println(submission);
        }
        System.out.println("\nyhteensä: " + totalAmountDone(subs) + " tehtävää " + totalHours(subs) + " tuntia");
    }
    
    public static int totalAmountDone(Submission[] subs) {
        int done = 0;
        for (int i = 0; i < subs.length; i++) {
            done += subs[i].getAmountDone();
        }
        return done;
    }
    
    public static int totalHours(Submission[] subs) {
        int hours = 0;
        for (int i = 0; i < subs.length; i++) {
            hours += subs[i].getHours();
        }
        return hours;
    }
}
