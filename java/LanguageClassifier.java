import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.ibm.watson.developer_cloud.natural_language_classifier.v1.NaturalLanguageClassifier;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.Classification;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.ClassifiedClass;

public class LanguageClassifier {

  private static String testparams =
    "{\"textToClassify\" : \"Will it rain tomorrow?\","  +
    " \"contextId\"      : \"359f41x201-nlc-180573\"," +
    " \"username\"       : \"\"," +
    " \"password\"       : \"\"," +
    " \"endpoint\"        : \"https://sandbox-watson-proxy.mybluemix.net/natural-language-classifier/api\"," +
    " \"authentication\"  : \"true\"}";

  public static void main(String[] args) {
    JsonParser parser = new JsonParser();
    JsonObject jsonArgs = parser.parse(testparams).getAsJsonObject();
    main(jsonArgs);
  }

  public static JsonObject main(JsonObject args) {
    JsonParser parser = new JsonParser();
    boolean noArgs = (args == null);
    boolean badArgs = (args.entrySet().size() != 7);
    if (noArgs || badArgs)
      args = parser.parse(testparams).getAsJsonObject();

    NaturalLanguageClassifier service = new NaturalLanguageClassifier();

    service.setUsernameAndPassword
        (args.get("username").getAsString(),
         args.get("password").getAsString());
         
 if (args.get("endpoint")!=null) 
    	service.setEndPoint(args.get("endpoint").getAsString());
 	
 	if (args.get("authentication")!=null) service.setSkipAuthentication((args.get("authentication").getAsString()=="true")?true:false);

    Classification result = service.classify
        (args.get("contextId").getAsString(),
         args.get("textToClassify").getAsString()).
        execute();

    System.out.println("The most likely classification is " +
                       result.getTopClass() + "\n");
    System.out.println("The complete list is:");
    List<ClassifiedClass> classifications = result.getClasses();
    for (ClassifiedClass nextClassification : classifications) {
      System.out.println("  Classification: " + nextClassification.getName() +
                         " (confidence: " +
                         (int)(nextClassification.getConfidence() * 100) +
                         "%)");
    }

    JsonObject returnObject = parser.parse(result.toString()).getAsJsonObject();

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
