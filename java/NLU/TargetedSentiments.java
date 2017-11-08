import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.SentimentOptions;

public class TargetedSentiments {

  private static String testparams =
    "{\"nlu_source\" 					: \"http://www.techradar.com/reviews/wearables/apple-watch-2-1323213/review\","  +
    " \"targets\"       : [\"fitness\"]," +
    " \"username\"       : \"\"," +
    " \"password\"       : \"\"," +
    " \"endpoint\"        : \"https://sandbox-watson-proxy.mybluemix.net/natural-language-understanding/api\"," +
    " \"authentication\"  : \"true\"}";

  public static void main(String[] args) {
    System.out.println(main(new JsonParser().parse(TargetedSentiments.testparams).getAsJsonObject()));
  }

  public static JsonObject main(JsonObject args) {
    JsonParser parser = new JsonParser();
    boolean noArgs = (args == null);
    boolean badArgs = (args.get("nlu_source").getAsString().isEmpty() || args.get("targets").getAsJsonArray().size() <= 0 || args.get("username").getAsString().isEmpty() || args.get("password").getAsString().isEmpty());
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
    List<String> targets = new ArrayList<String>();
    for(JsonElement target: args.get("targets").getAsJsonArray()) {
    	targets.add(target.getAsString());
    }
    SentimentOptions sentiment = new SentimentOptions.Builder().targets(targets).build();
    Features features = new Features.Builder().sentiment(sentiment).build();
    AnalyzeOptions parameters = new AnalyzeOptions.Builder().url(args.get("nlu_source").getAsString()).features(features).build();
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