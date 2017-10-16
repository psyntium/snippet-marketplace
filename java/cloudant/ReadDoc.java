jar@http://repo1.maven.org/maven2/com/cloudant/cloudant-client/2.5.0/cloudant-client-2.5.0.jar;http://repo1.maven.org/maven2/com/cloudant/cloudant-http/2.5.0/cloudant-http-2.5.0.jar  

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.org.lightcouch.NoDocumentException;
import com.cloudant.client.org.lightcouch.PreconditionFailedException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;  

public class ReadDoc { 	 	
  	  	 
    public String testparams = "{\"dbname\":\"person_MYNAME\"," //Replace MYNAME with your name
  	 		+ "\"docid\":\"\","
  	 		+ "\"username\":\"\","
  	 		+ "\"password\":\"\"," 	 		
    		+ "\"proxy\":\"http://cloudant-proxy.mybluemix.net/\"}"; 	  	 
    
    public static void main(String[] args) { 	   
    	ReadDoc hello = new ReadDoc(); 	   
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
				.build();
		
			String docId = args.getAsJsonPrimitive("docid").getAsString();

			if(docId == null || docId.isEmpty()) {
				output.addProperty("err", "Please specify valid Doc ID");
			}
			else {
				try {
					Database db = client.database(dbname, false);
			
					db.find(docId);
					InputStream is = db.find(docId);
					int i;
					char c;
					String doc = "";
					while((i=is.read())!=-1){
						c=(char)i;
						doc += c;
					}
					output = new JsonParser().parse(doc).getAsJsonObject();
					
				} catch(NoDocumentException ex) {
					output.addProperty("err", "No Database/Document found");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		} catch(PreconditionFailedException ex) {
			output.addProperty("err", ex.getReason());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} 
	 	  
	 	return output;	 
    } 	
}