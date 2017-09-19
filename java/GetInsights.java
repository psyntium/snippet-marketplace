import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.ibm.watson.developer_cloud.personality_insights.v3.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Trait;

public class GetInsights {

  private static String textToAnalyze =
    "Call me Ishmael. Some years ago-never mind how long precisely-having "
    + "little or no money in my purse, and nothing particular to interest "
    + "me on shore, I thought I would sail about a little and see the "
    + "watery part of the world. It is a way I have of driving off the "
    + "spleen and regulating the circulation. Whenever I find myself "
    + "growing grim about the mouth; whenever it is a damp, drizzly "
    + "November in my soul; whenever I find myself involuntarily pausing "
    + "before coffin warehouses, and bringing up the rear of every "
    + "funeral I meet; and especially whenever my hypos get such an upper "
    + "hand of me, that it requires a strong moral principle to prevent "
    + "me from deliberately stepping into the street, and methodically "
    + "knocking people's hats off-then, I account it high time to get to "
    + "sea as soon as I can. This is my substitute for pistol and ball. "
    + "With a philosophical flourish Cato throws himself upon his sword; "
    + "I quietly take to the ship. There is nothing surprising in this. "
    + "If they but knew it, almost all men in their degree, some time or "
    + "other, cherish very nearly the same feelings towards the ocean "
    + "with me. There now is your insular city of the Manhattoes, belted "
    + "round by wharves as Indian isles by coral reefs-commerce surrounds "
    + "it with her surf. Right and left, the streets take you waterward.";

  private static String testparams =
    "{\"textToAnalyze\" : \"" + textToAnalyze + "\"," +
    " \"username\"       : \"\"," +
    " \"password\"       : \"\"," +
    " \"endpoint\"        : \"https://sandbox-watson-proxy.mybluemix.net/personality-insights/api\"," +
    " \"authentication\"  : \"true\"}";

  public static void main(String[] args) {
    JsonParser parser = new JsonParser();
    JsonObject jsonArgs = parser.parse(testparams).getAsJsonObject();
    main(jsonArgs);
  }

  public static JsonObject main(JsonObject args) {
    JsonParser parser = new JsonParser();
    boolean noArgs = (args == null);
    boolean badArgs = (args.get("textToAnalyze").getAsString().isEmpty() || args.get("username").getAsString().isEmpty() || args.get("password").getAsString().isEmpty());
    if (noArgs || badArgs)
      args = parser.parse(testparams).getAsJsonObject();

    PersonalityInsights service = new
      PersonalityInsights(PersonalityInsights.VERSION_DATE_2016_10_19);
    service.setUsernameAndPassword(args.get("username").getAsString(),
                                   args.get("password").getAsString());

 	if (args.get("endpoint")!=null) 
    	service.setEndPoint(args.get("endpoint").getAsString());
 	
 	if (args.get("authentication")!=null) service.setSkipAuthentication((args.get("authentication").getAsString()=="true")?true:false);


    Profile result =
        service.getProfile(args.get("textToAnalyze").getAsString()).
        execute();

    System.out.println("Watson's analysis\n\nComment: " +
                       result.getWordCountMessage() + "\n");

    System.out.println("Personality traits:");
    List<Trait> traits = result.getPersonality();
    for (Trait nextTrait : traits) {
      System.out.println("- " + nextTrait.getName() + " - (" +
                         (int) (nextTrait.getPercentile() * 100) +
                         " percentile)");
    }
    System.out.println("\nEmotional needs:");
    List<Trait> needs = result.getNeeds();
    for (Trait nextNeed : needs) {
      System.out.println("- " + nextNeed.getName() + " - (" +
                         (int) (nextNeed.getPercentile() * 100) +
                         " percentile)");
    }
    System.out.println("\nPersonal values:");
    List<Trait> values = result.getValues();
    for (Trait nextValue : values) {
      System.out.println("- " + nextValue.getName() + " - (" +
                         (int) (nextValue.getPercentile() * 100) +
                         " percentile)");
    }

    JsonObject returnObject = parser.parse(result.toString()).getAsJsonObject();

    if (noArgs || badArgs)
      returnObject.
      	addProperty("Note", "Either no arguments or the wrong number " +
                    "of arguments were passed in to this service, so " +
                    "default values were used. To POST data to this " +
                    "service, set the HTTP Content-Type header to " +
                    "'application/json' and pass a JSON object " +
                    "containing the fields 'textToAnalyze', 'username', "+
                    " and 'password'.");

    return returnObject;
  }
}
