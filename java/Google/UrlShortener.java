//jar@http://central.maven.org/maven2/org/apache/httpcomponents/httpclient/4.5/httpclient-4.5.jar;http://central.maven.org/maven2/commons-logging/commons-logging/1.2/commons-logging-1.2.jar;http://central.maven.org/maven2/commons-codec/commons-codec/1.9/commons-codec-1.9.jar;http://central.maven.org/maven2/org/apache/httpcomponents/httpcore/4.4.1/httpcore-4.4.1.jar

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UrlShortener {
    public String testparams = "{\"url\":\"https://developer.ibm.com/sandbox_test/index.html\","
    		+ "\"apikey\":\"\"}"; 	  	 
    
    public static void main(String[] args) { 	   
    	UrlShortener hello = new UrlShortener(); 	   
	      System.out.println(main(new JsonParser().parse(hello.testparams).getAsJsonObject()));     
	  } 	  	 
  
  public static JsonObject main(JsonObject args) { 	   
      
      JsonObject output = new JsonObject(); 

  		try {
	  		String url = "https://www.googleapis.com/urlshortener/v1/url?key="+args.getAsJsonPrimitive("apikey").getAsString();
	  		HttpClient client = HttpClientBuilder.create().build();
	  		HttpPost post = new HttpPost(url);
				post.setHeader("Content-Type", "application/json");
	  		
	  		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
	  		urlParameters.add(new BasicNameValuePair("longUrl", args.getAsJsonPrimitive("url").getAsString()));
				post.setEntity(new UrlEncodedFormEntity(urlParameters));
			
				StringEntity requestEntity = new StringEntity(
				    "{\"longUrl\":\""+args.getAsJsonPrimitive("url").getAsString()+"\"}",
				    ContentType.APPLICATION_JSON);
				post.setEntity(requestEntity);
	  		HttpResponse resp = client.execute(post);
	  		
	  		BufferedReader rd = new BufferedReader(
	  		        new InputStreamReader(resp.getEntity().getContent()));
	
	  		String result = new String();
	  		String line = "";
	  		while ((line = rd.readLine()) != null) {
	  			result += line;
	  		}
	  		JsonObject json = new JsonParser().parse(result).getAsJsonObject();
	  		output.addProperty("result", json.getAsJsonPrimitive("id").getAsString());
	  		
  		} catch (UnsupportedEncodingException e) {
				output.addProperty("err", e.getMessage());
			} catch (ClientProtocolException e) {
				output.addProperty("err", e.getMessage());
			} catch (IOException e) {
				output.addProperty("err", e.getMessage());
			}
      return output;
	}
}