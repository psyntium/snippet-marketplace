//jar@http://central.maven.org/maven2/redis/clients/jedis/2.9.0/jedis-2.9.0.jar;http://central.maven.org/maven2/org/apache/commons/commons-pool2/2.4.2/commons-pool2-2.4.2.jar  

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import redis.clients.jedis.Jedis;  

public class Delete { 	 	
  	  	 
    public String testparams = "{\"hostname\":\"\","
  	 		+ "\"password\":\"\","
  	 		+ "\"port\":\"0\","
  	 		+ "\"key\":\"key1\"}"; 	  	 
    
    public static void main(String[] args) { 	   
    	Delete hello = new Delete(); 	   
	    System.out.println(main(new JsonParser().parse(hello.testparams).getAsJsonObject()));     
    } 	  	 
    
    public static JsonObject main(JsonObject args) { 	   
        
    	 JsonObject output = new JsonObject();
		  
    	 JsonObject redisConn = new JsonObject();
    	 redisConn.addProperty("hostname", args.getAsJsonPrimitive("hostname").getAsString().isEmpty()?"SANDBOX_REDIS_HOST":args.getAsJsonPrimitive("hostname").getAsString());
    	 redisConn.addProperty("password", args.getAsJsonPrimitive("password").getAsString().isEmpty()?"SANDBOX_REDIS_PASSWORD":args.getAsJsonPrimitive("password").getAsString());
    	 redisConn.addProperty("port", args.getAsJsonPrimitive("port").getAsString().equals("0")?"SANDBOX_REDIS_PORT":args.getAsJsonPrimitive("port").getAsString());
	 
         Jedis client = new Jedis(redisConn.getAsJsonPrimitive("hostname").getAsString(), redisConn.getAsJsonPrimitive("port").getAsInt());
   		 client.auth(redisConn.getAsJsonPrimitive("password").getAsString());
       
   		 String result = client.del(args.getAsJsonPrimitive("key").getAsString()).toString();
   		 output.addProperty("data", result);
	 		 
   		 client.close();
	 	  
	 	 return output;	 
    } 	
}