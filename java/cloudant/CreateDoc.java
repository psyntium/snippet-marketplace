jar@http://repo1.maven.org/maven2/com/cloudant/cloudant-client/2.5.0/cloudant-client-2.5.0.jar;http://repo1.maven.org/maven2/com/cloudant/cloudant-http/2.5.0/cloudant-http-2.5.0.jar  

import java.net.MalformedURLException;
import java.net.URL;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;
import com.cloudant.client.org.lightcouch.PreconditionFailedException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;  

public class CreateDoc { 	 	
  	  	 
    public String testparams = "{\"dbname\":\"person_MYNAME\"," //Replace MYNAME with your name
  	 		+ "\"username\":\"\","
  	 		+ "\"password\":\"\","
  	 		+ "\"name\":\"Joe Doe\","
  	 		+ "\"email\":\"joedoe@email.com\","
  	 		+ "\"comment\":\"This is a test\"," 	 		
    		+ "\"proxy\":\"http://cloudant-proxy.mybluemix.net/\"}"; 	  	 
    
    public static void main(String[] args) { 	   
    	CreateDoc hello = new CreateDoc(); 	   
        System.out.println(main(new JsonParser().parse(hello.testparams).getAsJsonObject()));     
    } 	  	 
    
    public static JsonObject main(JsonObject args) { 	   
        
    	JsonObject output = new JsonObject();
		  
	   	String username = args.getAsJsonPrimitive("username").getAsString();
	   	String password = args.getAsJsonPrimitive("password").getAsString();
	   	String dbname = args.getAsJsonPrimitive("dbname").getAsString();
        String url = args.getAsJsonPrimitive("proxy").getAsString().isEmpty()?("https://" + username + ".cloudant.com"):args.getAsJsonPrimitive("proxy").getAsString();
	
		try {
			CloudantClient client = ClientBuilder.url(new URL(url))
				.username(username)
				.password(password)
				.build();;
		
			//Create a dummy json document
			JsonObject studentJson = new JsonObject();
			studentJson.addProperty("name", args.getAsJsonPrimitive("name").getAsString());
			studentJson.addProperty("email", args.getAsJsonPrimitive("email").getAsString());
			studentJson.addProperty("comment", args.getAsJsonPrimitive("comment").getAsString());

			Database db = client.database(dbname, false);
			Response dbResponse = db.save(studentJson);
			
			//for success insertion
			if(dbResponse.getStatusCode() < 400) {
				output.add("doc", studentJson);	
			
				//dbResponse json data
				JsonObject dbResponseJson = new JsonObject();
				dbResponseJson.addProperty("status", dbResponse.getStatusCode() + " - " + dbResponse.getReason());
				dbResponseJson.addProperty("id", dbResponse.getId());
				dbResponseJson.addProperty("rev", dbResponse.getRev());
			
				output.add("data", dbResponseJson);
			}
			else {
				output.addProperty("err", dbResponse.getStatusCode() + " - " + dbResponse.getReason());
			}
			
		} catch(PreconditionFailedException ex) {
			output.addProperty("err", ex.getReason());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} 
	
		return output;	 
    } 	
}