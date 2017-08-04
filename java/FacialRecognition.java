import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.Face;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ImageFace;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualRecognitionOptions;

public class FacialRecognition {

  // Image URL is Barack Obama
  private static String data =
      "{ \"apiKey\": \"\", " 
      +" \"imageUrl\": \"https://www.whitehouse.gov/sites/whitehouse.gov/files/images/first-family/44_barack_obama%5B1%5D.jpg\"," 
      +" \"endpoint\": \"https://sandbox-watson-proxy.mybluemix.net/visual-recognition/api\"," 
      +" \"authentication\"  : \"false\"}";
    
    
    
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

    VisualRecognition service =
      new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
    service.setApiKey(args.get("apiKey").getAsString());
    String imageUrl = args.get("imageUrl").getAsString();

  if (args.get("endpoint")!=null) 
     service.setEndPoint(args.get("endpoint").getAsString());
  
  if (args.get("authentication")!=null) service.setSkipAuthentication((args.get("authentication").getAsString()=="true")?true:false);
  
    VisualRecognitionOptions voptions =
      new VisualRecognitionOptions.Builder().url(imageUrl).build();
    DetectedFaces result = service.detectFaces(voptions).execute();

    List<ImageFace> images = result.getImages();
    for (ImageFace nextImage : images) {
      List<Face> faces = nextImage.getFaces();
      for (Face nextFace : faces) {
        if (nextFace.getIdentity() != null) {
          System.out.println("This image contains a picture of "
                             + nextFace.getIdentity().getName() + ".");
        }
        else
          System.out.println("This image contains one or more faces, " +
                             "but none of them were recognized.");
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