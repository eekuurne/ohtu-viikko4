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

        String courseUrl = "https://ohtustats2017.herokuapp.com/courses/1.json";
        String submissionsUrl = "http://ohtustats2017.herokuapp.com/students/" + studentNr + "/submissions";
        
        String courseText = Request.Get(courseUrl).execute().returnContent().asString();
        String submissionsText = Request.Get(submissionsUrl).execute().returnContent().asString();
        
        //System.out.println("json-muotoinen data:");
        //System.out.println(bodyText);
        //System.out.println(courseText);
        
        Gson mapper = new Gson();
        Course course = mapper.fromJson(courseText, Course.class);
        Submission[] subs = mapper.fromJson(submissionsText, Submission[].class);

        System.out.println(course + "\n");
        System.out.println("opiskelijanumero: " + studentNr + "\n");
        for (Submission submission : subs) {
            System.out.println(submission.toStringWithMaxExercises(course));
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
