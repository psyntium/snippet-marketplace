jar@http://repo1.maven.org/maven2/com/cloudant/cloudant-client/2.5.0/cloudant-client-2.5.0.jar;http://repo1.maven.org/maven2/com/cloudant/cloudant-http/2.5.0/cloudant-http-2.5.0.jar  

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;
import com.cloudant.client.org.lightcouch.PreconditionFailedException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CreateDoc {
	
	private final String htmlGistUrl = "https://gist.githubusercontent.com/snippet-java/fd3aa1c2ab893bf8e1bcd90073ceab99/raw";
	 
	 public String parameters = "{\"dbname\":\"person\","
	 		+ "\"username\":\"\","
	 		+ "\"password\":\"\","
	 		+ "\"name\":\"Joe Doe\","
	 		+ "\"email\":\"joedoe@email.com\","
	 		+ "\"comment\":\"This is a test\"}";
	 
	 public static void main(String[] args) {
	   CreateDoc hello = new CreateDoc();
	   System.out.println(hello.process(hello.parameters));
	 }
	 
	 public static JsonObject main(JsonObject args) throws IllegalArgumentException, IllegalAccessException {
	   JsonObject response = new JsonObject();
	   CreateDoc hello = new CreateDoc();
	   if(args.getAsJsonPrimitive("__ow_method").getAsString().equalsIgnoreCase("get"))
	     response.addProperty("html", hello.generateHTMLForm().toString());
	   else 
	     response.addProperty("output", hello.process(args.toString()).toString());
	        
	   return response;
	 }
	 
	 private JsonObject process(String jsonString) {

	   JsonParser parser = new JsonParser(); 
	   JsonObject mybean = parser.parse(jsonString).getAsJsonObject();
		  
	   JsonObject output = new JsonObject();
		  
  	 String username = mybean.getAsJsonPrimitive("username").getAsString();
  	 String password = mybean.getAsJsonPrimitive("password").getAsString();
  	 String dbname = mybean.getAsJsonPrimitive("dbname").getAsString();

		 try {
			 CloudantClient client = ClientBuilder.url(new URL("https://" + username + ".cloudant.com"))
	        .username(username)
	        .password(password)
	        .build();;
	        
			 	//Create a dummy json document
	    	JsonObject studentJson = new JsonObject();
	    	studentJson.addProperty("name", mybean.getAsJsonPrimitive("name").getAsString());
	    	studentJson.addProperty("email", mybean.getAsJsonPrimitive("email").getAsString());
	    	studentJson.addProperty("comment", mybean.getAsJsonPrimitive("comment").getAsString());

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	  
	  return output;
	 }
	 
	 private StringBuilder generateHTMLForm() {
	   HttpClient client = HttpClientBuilder.create().build();
	  
	   HttpGet get = new HttpGet(htmlGistUrl);
	  
	   HttpResponse resp;
	   StringBuilder result = new StringBuilder();
	   try {
		   resp = client.execute(get);
		   BufferedReader rd = new BufferedReader(
		           new InputStreamReader(resp.getEntity().getContent()));
	
		   String line = "";
		   while ((line = rd.readLine()) != null) {
	    
		     //replace with dynamic fields for html form
		     if(line.indexOf("{{inputs}}") >= 0) {
		    	 ArrayList<String> htmlCode = generateDynamicHTMLCode(new JsonParser().parse(parameters).getAsJsonObject());
		    	 for (String htmlLine : htmlCode) {
		    		 result.append(htmlLine+"\n");
		    	 }
		     }
		     else
		    	 result.append(line+"\n");
		   	 }
	  } catch (ClientProtocolException e) {
	    System.err.println(e.getMessage());
	  } catch (IOException e) {
	    System.err.println(e.getMessage());
	  } catch (IllegalArgumentException e) {
	    e.printStackTrace();
	  } catch (IllegalAccessException e) {
	    e.printStackTrace();
	  }
	  
	  return result;
  }
	 
	 private ArrayList<String> generateDynamicHTMLCode(JsonObject jsonObj)
	   throws IllegalArgumentException, IllegalAccessException {
	   
		 ArrayList<String> htmlCode = new ArrayList<String>();
	  
	   for (Map.Entry<String,JsonElement> entry : jsonObj.entrySet()) {
	   
		   String textFieldHTML = "";
		   textFieldHTML = String.format("%s: <input type=\"text\" name=\"%s\" value=\"%s\" ><br><br>",
		     entry.getKey(), entry.getKey(), entry.getValue().getAsString());
		   htmlCode.add(textFieldHTML);
	   }
	  
	   return htmlCode;
	 }
}