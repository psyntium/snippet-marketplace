jar@http://repo1.maven.org/maven2/org/apache/airavata/levenshtein-distance-service/0.2-incubating/levenshtein-distance-service-0.2-incubating.jar
import org.apache.airavata.samples.LevenshteinDistanceService;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class a_simple_dynamic_app {

	 public String testparams = "{\"String1\":\"This is a sentence. It is made of words\","
	   + "\"String2\":\"This sentence is similar. It has almost the same words\"}";
	 
	 public static void main(String[] args) {
		 a_simple_dynamic_app hello = new a_simple_dynamic_app();
	        System.out.println(main(new JsonParser().parse(hello.testparams).getAsJsonObject()));
	 }
	 
	 public static JsonObject main(JsonObject args) {
	   JsonObject response = new JsonObject();
	  
		 int diff = new LevenshteinDistanceService().computeDistance(
				 args.getAsJsonPrimitive("String1").getAsString(), 
				 args.getAsJsonPrimitive("String2").getAsString());
	   
	   response.addProperty("output", "number of differences: " + diff);
	        
	   return response;
	 }
 
}
