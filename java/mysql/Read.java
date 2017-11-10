//jar@http://central.maven.org/maven2/mysql/mysql-connector-java/6.0.5/mysql-connector-java-6.0.5.jar;http://central.maven.org/maven2/commons-dbutils/commons-dbutils/1.6/commons-dbutils-1.6.jar  

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;  

public class Read { 	 	
  	  	 
    public String testparams = "{\"hostname\":\"\","
  	 		+ "\"username\":\"\","
  	 		+ "\"password\":\"\","
  	 		+ "\"dbname\":\"\","
  	 		+ "\"tablename\":\"student_username\","
  	 		+ "\"studentid\":\"12345\"}"; 	  	 
    
    public static void main(String[] args) { 	   
    	Read hello = new Read(); 	   
	    System.out.println(main(new JsonParser().parse(hello.testparams).getAsJsonObject()));     
    } 	  	 
    
    public static JsonObject main(JsonObject args) { 	   
        
    	 JsonObject output = new JsonObject();
    	 
    	 String hostname = args.getAsJsonPrimitive("hostname").getAsString().isEmpty()?"SANDBOX_MYSQL_HOST":args.getAsJsonPrimitive("hostname").getAsString();
    	 String dbname = args.getAsJsonPrimitive("dbname").getAsString().isEmpty()?"SANDBOX_MYSQL_DATABASE":args.getAsJsonPrimitive("dbname").getAsString();
    	 String username = args.getAsJsonPrimitive("username").getAsString().isEmpty()?"SANDBOX_MYSQL_USER":args.getAsJsonPrimitive("username").getAsString();
    	 String password = args.getAsJsonPrimitive("password").getAsString().isEmpty()?"SANDBOX_MYSQL_PASSWORD":args.getAsJsonPrimitive("password").getAsString();
    	 
    	 String jdbcUrl = "jdbc:mysql://" + hostname + "/" + dbname + "?user=" + username + "&password=" + password + "&useLegacyDatetimeCode=false&serverTimezone=America/Chicago";
    	 try {
				  Class.forName("com.mysql.cj.jdbc.Driver");
	 			  Connection conn = DriverManager.getConnection(jdbcUrl);
	 		  
	 			  String query = "SELECT * FROM "+args.getAsJsonPrimitive("tablename").getAsString()
	 			  		+" WHERE student_id = " +args.getAsJsonPrimitive("studentid");
	 			  QueryRunner queryRunner = new QueryRunner();
		    	java.util.List<Map<String, Object>>listOfMaps = queryRunner.query(conn, query, new MapListHandler());
		    	String result = new Gson().toJson(listOfMaps);
		    	JsonArray resultSet = new JsonParser().parse(result).getAsJsonArray();
		    	output.add("data", resultSet);
	     		
	    		stat.close();
	    		conn.close();
			} catch (ClassNotFoundException e) {
				output.addProperty("err", e.getMessage());
			} catch (SQLException e) {
				output.addProperty("err", e.getMessage());
			}
	 		 
	 	  
   		 return output;	 
    } 	
}