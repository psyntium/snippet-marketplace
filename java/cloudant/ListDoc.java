jar@http://repo1.maven.org/maven2/com/cloudant/cloudant-client/2.5.0/cloudant-client-2.5.0.jar;http://repo1.maven.org/maven2/com/cloudant/cloudant-http/2.5.0/cloudant-http-2.5.0.jar  

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.org.lightcouch.NoDocumentException;
import com.cloudant.client.org.lightcouch.PreconditionFailedException;
import com.cloudant.http.Http;
import com.cloudant.http.HttpConnection;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;  

public class ListDoc { 	 	
  	  	 
    public String testparams = "{\"dbname\":\"person_MYNAME\"," //Replace MYNAME with your name
  	 		+ "\"username\":\"\","
  	 		+ "\"password\":\"\"," 	 		
    		+ "\"proxy\":\"http://cloudant-proxy.mybluemix.net/\"}"; 	  	 
    
    public static void main(String[] args) { 	   
	    ListDoc hello = new ListDoc(); 	   
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
		
			try {
				Database db = client.database(dbname, false);
				HttpConnection httpResponse = client.executeRequest(
					Http.GET(new URL(url + db.info().getDbName() + "/_all_docs")));
	
				output = new JsonParser().parse(httpResponse.responseAsString()).getAsJsonObject();
			
			} catch(NoDocumentException ex) {
				output.addProperty("err", "No Database/Document found");
			} catch (JsonSyntaxException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch(PreconditionFailedException ex) {
			output.addProperty("err", ex.getReason());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} 
	 	  
	 	return output;	 
    } 	
}