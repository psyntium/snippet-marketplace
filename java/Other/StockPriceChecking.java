//jar@http://central.maven.org/maven2/com/twilio/sdk/twilio/7.15.6/twilio-7.15.6.jar;http://central.maven.org/maven2/com/google/guava/guava/18.0/guava-18.0.jar;http://central.maven.org/maven2/joda-time/joda-time/2.5/joda-time-2.5.jar;http://central.maven.org/maven2/io/jsonwebtoken/jjwt/0.4/jjwt-0.4.jar;http://central.maven.org/maven2/org/slf4j/slf4j-api/1.7.6/slf4j-api-1.7.6.jar;http://central.maven.org/maven2/org/apache/httpcomponents/httpclient/4.5.2/httpclient-4.5.2.jar;http://central.maven.org/maven2/commons-logging/commons-logging/1.2/commons-logging-1.2.jar;http://central.maven.org/maven2/commons-codec/commons-codec/1.9/commons-codec-1.9.jar;http://central.maven.org/maven2/org/apache/httpcomponents/httpcore/4.4.4/httpcore-4.4.4.jar;http://central.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.8.7/jackson-core-2.8.7.jar;http://central.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.8.7/jackson-annotations-2.8.7.jar;http://central.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.8.7/jackson-databind-2.8.7.jar;http://central.maven.org/maven2/javax/xml/bind/jaxb-api/2.2/jaxb-api-2.2.jar;http://central.maven.org/maven2/javax/xml/stream/stax-api/1.0-2/stax-api-1.0-2.jar;http://central.maven.org/maven2/javax/activation/activation/1.1/activation-1.1.jar

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class StockPriceChecking {
    public String testparams = "{\"symbol\":\"IBM\"}"; 	  	 
    
    public static void main(String[] args) { 	   
    	StockPriceChecking hello = new StockPriceChecking(); 	   
	      System.out.println(main(new JsonParser().parse(hello.testparams).getAsJsonObject()));     
	  } 	  	 
  
  public static JsonObject main(JsonObject args) { 	   
      
      JsonObject output = new JsonObject(); 
      

  		try {
	  		String url = "https://query1.finance.yahoo.com/v7/finance/quote?symbols="+ args.getAsJsonPrimitive("symbol").getAsString();
	  		HttpClient client = HttpClientBuilder.create().build();
	  		HttpGet get = new HttpGet(url);
	  		HttpResponse resp = client.execute(get);
	  		
	  		BufferedReader rd = new BufferedReader(
	  		        new InputStreamReader(resp.getEntity().getContent()));
	
	  		String result = new String();
	  		String line = "";
	  		while ((line = rd.readLine()) != null) {
	  			result += line;
	  		}
	  		JsonParser parser = new JsonParser();
	  		JsonObject stock = parser.parse(result).getAsJsonObject();
	  		System.out.println(stock);
	  		JsonObject stockInfo = stock.getAsJsonObject("quoteResponse").getAsJsonArray("result").get(0).getAsJsonObject();
	  		System.out.println(stockInfo);
	  		output.addProperty("name", stockInfo.get("shortName").getAsString());
	  		output.addProperty("symbol", stockInfo.get("symbol").getAsString());
	  		output.addProperty("price", stockInfo.get("regularMarketPrice").getAsDouble());
	  		output.addProperty("change", stockInfo.get("regularMarketChange").getAsDouble());
	  		output.addProperty("high", stockInfo.get("regularMarketDayHigh").getAsDouble());
	  		output.addProperty("low", stockInfo.get("regularMarketDayLow").getAsDouble());
	  		output.addProperty("volume", stockInfo.get("regularMarketVolume").getAsInt());
	  		
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