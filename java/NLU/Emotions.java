import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;

public class Emotions {

  private static String testparams =
    "{\"text\" : \"This card is way too slow for my taste. It's probably great shooting JPEG but if your shooting Raw you may want to go with something else\","  +
    " \"username\"       : \"\"," +
    " \"password\"       : \"\"," +
    " \"endpoint\"        : \"https://sandbox-watson-proxy.mybluemix.net/natural-language-understanding/api\"," +
    " \"authentication\"  : \"true\"}";

  public static void main(String[] args) {
    System.out.println(main(new JsonParser().parse(Emotions.testparams).getAsJsonObject()));
  }

  public static JsonObject main(JsonObject args) {
    JsonParser parser = new JsonParser();
    boolean noArgs = (args == null);
    boolean badArgs = (args.get("text").getAsString().isEmpty() || args.get("username").getAsString().isEmpty() || args.get("password").getAsString().isEmpty());
    if (noArgs || badArgs)
      args = parser.parse(testparams).getAsJsonObject();

    NaturalLanguageUnderstanding service=new NaturalLanguageUnderstanding(
    		NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
    		args.get("username").getAsString(),
    		args.get("password").getAsString());
    
    
    if (args.get("endpoint")!=null) 
    	service.setEndPoint(args.get("endpoint").getAsString());
	
    if (args.get("authentication")!=null) 
    	service.setSkipAuthentication((args.get("authentication").getAsString()=="true")?true:false);
    
    EmotionOptions emotions = new EmotionOptions.Builder().build();
    Features features = new Features.Builder().emotion(emotions).build();
    AnalyzeOptions parameters = new AnalyzeOptions.Builder().text(args.get("text").getAsString()).features(features).build();
    AnalysisResults response = service.analyze(parameters).execute();
    
    JsonObject returnObject = parser.parse(response.toString()).getAsJsonObject();

    if (noArgs || badArgs)
      returnObject.
      	addProperty("Note", "Either no arguments or the wrong number " +
                    "of arguments were passed in to this service, so " +
                    "default values were used. To POST data to this " +
                    "service, set the HTTP Content-Type header to " +
                    "'application/json' and pass a JSON object " +
                    "containing the fields 'textToClassify', 'contextId', "+
                    "'username', and 'password'.");

    return returnObject;
  }
}