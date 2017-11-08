import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HttpPostSample {

  private static String testparams =
    "{\"http_url\" : \"https://openwhisk.ng.bluemix.net/api/v1/web/ecodadmi%40us.ibm.com_cloudsandbox/default/simple-get-post.json\"," +
    " \"name\"       : \"How to program in Go\"," +
    " \"serialno\"       : \"1-2345243\"," +
    " \"author\"       : \"Michael Lock\"}";

  public static void main(String[] args) {
    System.out.println(main(new JsonParser().parse(HttpPostSample.testparams).getAsJsonObject()));
  }

  public static JsonObject main(JsonObject args) {
    
    JsonObject returnObject = new JsonObject();

    HttpClient client = HttpClientBuilder.create().build();
		
    HttpPost post = new HttpPost(args.getAsJsonPrimitive("http_url").getAsString());
		
    List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
    urlParameters.add(new BasicNameValuePair("name", args.getAsJsonPrimitive("name").getAsString()));
    urlParameters.add(new BasicNameValuePair("serialno", args.getAsJsonPrimitive("serialno").getAsString()));
    urlParameters.add(new BasicNameValuePair("author", args.getAsJsonPrimitive("author").getAsString()));

    
		HttpResponse resp;
		String result = "";
		try {
	    post.setEntity(new UrlEncodedFormEntity(urlParameters));
	    
			resp = client.execute(post);
			BufferedReader rd = new BufferedReader(
			        new InputStreamReader(resp.getEntity().getContent()));

			String line = "";
			while ((line = rd.readLine()) != null) {
				result += line;
			}
		} catch (ClientProtocolException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		returnObject.addProperty("result", result);
		
    return returnObject;
  }
}