import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.Language;
import com.ibm.watson.developer_cloud.language_translator.v2.model.Translation;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;

public class LanguageTranslation {

  private static String testparams =
    "{\"textToTranslate\" : \"That that is not confusing is amazing.\"," +
    " \"username\"        : \"\"," +
    " \"password\"        : \"\"," +
    " \"endpoint\"        : \"https://sandbox-watson-proxy.mybluemix.net/language-translator/api\"," +
    " \"authentication\"  : \"true\"}";

  public static void main(String[] args) {
    JsonParser parser = new JsonParser();
    JsonObject jsonArgs = parser.parse(testparams).getAsJsonObject();
    main(jsonArgs);
  }

  public static JsonObject main(JsonObject args) {
    JsonParser parser = new JsonParser();
    boolean noArgs = (args == null);
    boolean badArgs = (args.entrySet().size() != 6);
    if (noArgs || badArgs)
      args = parser.parse(testparams).getAsJsonObject();

    LanguageTranslator service = new LanguageTranslator();

    // The username and password aren't necessary if you're running in
    // the developerWorks sandbox. Otherwise you've got to fill them in.
    String username = args.get("username").getAsString();
    String password = args.get("password").getAsString();
    service.setUsernameAndPassword(username, password);
    
    if (args.get("endpoint")!=null) 
    	service.setEndPoint(args.get("endpoint").getAsString());
 	
 	if (args.get("authentication")!=null) service.setSkipAuthentication((args.get("authentication").getAsString()=="true")?true:false);


    String textToTranslate = args.get("textToTranslate").getAsString();

    TranslationResult firstResult = service.translate(
      textToTranslate, Language.ENGLISH, Language.FRENCH).execute();

    TranslationResult secondResult = service.translate(
      firstResult.getFirstTranslation(),
      Language.FRENCH, Language.SPANISH).execute();

    TranslationResult thirdResult = service.translate(
      secondResult.getFirstTranslation(),
      Language.SPANISH, Language.ENGLISH).execute();

    List<Translation> translations = thirdResult.getTranslations();
    if (translations.size() > 1) {
      System.out.println("There are multiple translations:");
      for (Translation nextTranslation : translations) {
        System.out.println("- " +
          nextTranslation.getTranslation());
      }
    }
    else {
      System.out.println("The default translation: ");
      System.out.println("- " + thirdResult.getFirstTranslation());
    }

    JsonObject returnObject = parser.parse(thirdResult.toString())
                                    .getAsJsonObject();

    if (noArgs || badArgs)
      returnObject.
      	addProperty("Note", "Either no arguments or the wrong number " +
                    "of arguments were passed in to this service, so " +
                    "default values were used. To POST data to this " +
                    "service, set the HTTP Content-Type header to " +
                    "'application/json' and pass a JSON object " +
                    "containing the fields 'textToTranslate', 'username', " +
                    "and 'password'.");

    return returnObject;
  }
}
