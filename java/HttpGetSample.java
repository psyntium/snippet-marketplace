import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HttpGetSample {

  private static String testparams =
    "{\"http_url\" : \"https://openwhisk.ng.bluemix.net/api/v1/web/ecodadmi%40us.ibm.com_cloudsandbox/default/simple-get-post.json\"}";

  public static void main(String[] args) {
    System.out.println(main(new JsonParser().parse(HttpGetSample.testparams).getAsJsonObject()));
  }

  public static JsonObject main(JsonObject args) {
    
    JsonObject returnObject = new JsonObject();

    HttpClient client = HttpClientBuilder.create().build();
		
		HttpGet get = new HttpGet(args.getAsJsonPrimitive("http_url").getAsString());
		
		HttpResponse resp;
		String result = "";
		try {
			resp = client.execute(get);
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