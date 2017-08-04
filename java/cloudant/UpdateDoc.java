jar@http://repo1.maven.org/maven2/com/cloudant/cloudant-client/2.5.0/cloudant-client-2.5.0.jar;http://repo1.maven.org/maven2/com/cloudant/cloudant-http/2.5.0/cloudant-http-2.5.0.jar  

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.org.lightcouch.PreconditionFailedException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;  

public class UpdateDoc { 	 	
  	  	 
    public String testparams = "{\"dbname\":\"person\","
  	 		+ "\"docid\":\"\","
  	 		+ "\"name\":\"Foo Bar\","
  	 		+ "\"comment\":\"I updated the doc\","
  	 		+ "\"username\":\"\","
  	 		+ "\"password\":\"\"}"; 	  	 
    
    public static void main(String[] args) { 	   
    		UpdateDoc hello = new UpdateDoc(); 	   
	      System.out.println(main(new JsonParser().parse(hello.testparams).getAsJsonObject()));     
    } 	  	 
    
    public static JsonObject main(JsonObject args) { 	   
        
    	 JsonObject output = new JsonObject();
		  
	   	 String username = args.getAsJsonPrimitive("username").getAsString();
	   	 String password = args.getAsJsonPrimitive("password").getAsString();
	   	 String dbname = args.getAsJsonPrimitive("dbname").getAsString();
	
	 		 try {
	 			 CloudantClient client = ClientBuilder.url(new URL("https://" + username + ".cloudant.com"))
	 	        .username(username)
	 	        .password(password)
	 	        .build();;
	 	        
	         String docId = args.getAsJsonPrimitive("docid").getAsString();
	     		String name = args.getAsJsonPrimitive("name").getAsString();
	     		String comment = args.getAsJsonPrimitive("comment").getAsString();
	
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
	 	    				while((i=is.read())!=-1) {
	   		          c=(char)i;
	   		          doc += c;
	   		        }
	 	    				JsonObject docJson = new JsonParser().parse(doc).getAsJsonObject();
	     				
	 	    				if(!(name == null || name.isEmpty()))
	 	    					docJson.addProperty("name", name);
	 	    				if(!(comment == null || comment.isEmpty()))
	 	    					docJson.addProperty("comment", comment);
	     				
	 	    				db.update(docJson);
	     				
	     				  output.addProperty("result", "Success update document");
	 			
	 					 } catch(PreconditionFailedException ex) {
	 						 output.addProperty("err", ex.getReason());
	 					 } catch (MalformedURLException e) {
	 						 e.printStackTrace();
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