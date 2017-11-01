//jar@http://repo1.maven.org/maven2/com/cloudant/cloudant-client/2.5.0/cloudant-client-2.5.0.jar;http://repo1.maven.org/maven2/com/cloudant/cloudant-http/2.5.0/cloudant-http-2.5.0.jar  

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;
import com.cloudant.client.org.lightcouch.NoDocumentException;
import com.cloudant.client.org.lightcouch.PreconditionFailedException;
import com.cloudant.http.Http;
import com.cloudant.http.HttpConnection;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;  

public class CompleteCrud { 	 	
  	  	 
    public String testparams = "{\"dbname\":\"person_test\"," //Replace MYNAME with your name
  	 		+ "\"doc_name\":\"Joe Doe\","
  	 		+ "\"doc_email\":\"joedoe@email.com\","
  	 		+ "\"doc_comment\":\"This is just a test\","
  	 		+ "\"username\":\"\","
  	 		+ "\"password\":\"\"," 	 		
    		+ "\"proxy\":\"http://cloudant-proxy.mybluemix.net/\"}"; 	  	 
    
    public static void main(String[] args) { 	   
    	CompleteCrud hello = new CompleteCrud(); 	   
	    System.out.println(main(new JsonParser().parse(hello.testparams).getAsJsonObject()).getAsJsonPrimitive("result").getAsString());     
    } 	  	 
    
    public static JsonObject main(JsonObject args) { 	   
        
    	JsonObject output = new JsonObject();
		  
	   	String username = args.getAsJsonPrimitive("username").getAsString();
	   	String password = args.getAsJsonPrimitive("password").getAsString();
	   	String dbname = args.getAsJsonPrimitive("dbname").getAsString();
        String url = args.getAsJsonPrimitive("proxy").getAsString().isEmpty()?("https://" + username + ".cloudant.com"):args.getAsJsonPrimitive("proxy").getAsString();
				String result = "";
	
		try {
				CloudantClient client = ClientBuilder.url(new URL(url))
					.username(username)
					.password(password)
					.build();;
				
				
				result += "Creating Cloudant database \'"+dbname+"\' \n";
				client.createDB(args.getAsJsonPrimitive("dbname").getAsString()); 
				result += "Result: success created database \n\n";
				
				result += "Creating document \n";
				JsonObject studentJson = new JsonObject();
				studentJson.addProperty("name", args.getAsJsonPrimitive("doc_name").getAsString());
				studentJson.addProperty("email", args.getAsJsonPrimitive("doc_email").getAsString());
				studentJson.addProperty("comment", args.getAsJsonPrimitive("doc_comment").getAsString());
				Database db = client.database(dbname, false);
				Response dbResponse = db.save(studentJson);
				String docId = dbResponse.getId();
				result += "Result: "+dbResponse.toString()+" \n\n";
				
				result += "Listing all docs \n";
				HttpConnection httpResponse = client.executeRequest(
						Http.GET(new URL(url + db.info().getDbName() + "/_all_docs")));
				result += "Result: "+httpResponse.responseAsString()+" \n\n";
				
				result += "Reading document content \n";
				db.find(docId);
				InputStream is = db.find(docId);
				int i;
				char c;
				String doc = "";
				while((i=is.read())!=-1){
					c=(char)i;
					doc += c;
				}
				result += "Result: "+doc+" \n\n";
				
				result += "Updating document \n";
				JsonObject docJson = new JsonParser().parse(doc).getAsJsonObject();
				docJson.addProperty("name", "updated_"+docJson.getAsJsonPrimitive("name").getAsString());
				docJson.addProperty("comment", "updated_"+docJson.getAsJsonPrimitive("comment").getAsString());
				dbResponse = db.update(docJson);
				result += "Result: "+dbResponse.toString()+" \n\n";
				
				result += "(After update) Reading document content \n";
				db.find(docId);
				is = db.find(docId);
				doc = "";
				while((i=is.read())!=-1){
					c=(char)i;
					doc += c;
				}
				is.close();
				result += "Result: "+doc+" \n\n";
					
				result += "Deleting doc \n";
				docJson = new JsonParser().parse(doc).getAsJsonObject();	
				dbResponse = db.remove(docJson);
				result += "Result: "+dbResponse.toString()+" \n\n";
				
				result += "Deleting database \n";
				client.deleteDB(dbname);
				result += "Result: success deleted database. \n";
				
				output.addProperty("result", result);
			
		} catch(NoDocumentException ex) {
			output.addProperty("result", result+"\n"+ex.getReason());
		} catch(PreconditionFailedException ex) {
			output.addProperty("result", result+"\n"+ex.getReason());
		} catch (MalformedURLException ex) {
			output.addProperty("result", result+"\n"+ex.getMessage());
		} catch (JsonSyntaxException ex) {
			output.addProperty("result", result+"\n"+ex.getMessage());
		} catch (IOException ex) {
			output.addProperty("result", result+"\n"+ex.getMessage());
		} 
		
		return output;	 
    } 	
}