import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ImageClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class ClassifyImage {

  // A bowl of fruit
  private static String data =
    "{ \"imageUrl\" : \"https://raw.githubusercontent.com/watson-developer-cloud/doc-tutorial-downloads/master/visual-recognition/fruitbowl.jpg\"," +
    " \"apiKey\"   : \"adea4e1a51525d502c2502c2114610b959cf9dbe\"}";

  public static void main(String[] args) {
    JsonParser parser = new JsonParser();
    JsonObject jsonArgs = parser.parse(data).getAsJsonObject();
    main(jsonArgs);
  }

  public static JsonObject main(JsonObject args) {
    JsonParser parser = new JsonParser();
    boolean noArgs = (args == null);
    boolean badArgs = (args.entrySet().size() != 5);
    if (noArgs || badArgs)
      args = parser.parse(data).getAsJsonObject();

    VisualRecognition service = new
      VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);

    // The API key isn't necessary if you're running in the developerWorks
    // sandbox. Otherwise you have to fill it in.
    String apiKey = args.get("apiKey").getAsString();
    service.setApiKey(apiKey);

    String imageUrl = args.get("imageUrl").getAsString();
    ClassifyImagesOptions options = new
      ClassifyImagesOptions.Builder().url(imageUrl).build();
    VisualClassification result = service.classify(options).execute();

    System.out.println("Things that Watson found in this image:\n");
    List<ImageClassification> images = result.getImages();
    for (ImageClassification nextImage : images) {
      List<VisualClassifier> classifiers = nextImage.getClassifiers();
      for (VisualClassifier nextClassifier : classifiers) {
        List<VisualClassifier.VisualClass> classes =
          nextClassifier.getClasses();
        for (VisualClassifier.VisualClass nextClass : classes) {
          System.out.println("- " + nextClass.getName() + " (certainty: "
                             + (int)(nextClass.getScore() * 100) + "%)");
        }
      }
    }

    JsonObject returnObject = parser.parse(result.toString()).getAsJsonObject();

    if (noArgs || badArgs)
      returnObject.
      	addProperty("Note", "Either no arguments or the wrong number " +
                    "of arguments were passed in to this service, so " +
                    "default values were used. To POST data to this " +
                    "service, set the HTTP Content-Type header to " +
                    "'application/json' and pass a JSON object " +
                    "containing the fields 'apiKey' and 'imageUrl'.");

    return returnObject;
  }
}
