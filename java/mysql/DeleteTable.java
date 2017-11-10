//jar@http://central.maven.org/maven2/mysql/mysql-connector-java/6.0.5/mysql-connector-java-6.0.5.jar  

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;  

public class DeleteTable { 	 	
  	  	 
    public String testparams = "{\"hostname\":\"\","
  	 		+ "\"username\":\"\","
  	 		+ "\"password\":\"\","
  	 		+ "\"dbname\":\"\","
  	 		+ "\"tablename\":\"student_username\"}"; 	  	 
    
    public static void main(String[] args) { 	   
    	DeleteTable hello = new DeleteTable(); 	   
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
	 		  
	 			  String query = "DROP TABLE "+args.getAsJsonPrimitive("tablename").getAsString();
		    	PreparedStatement stat = conn.prepareStatement(query);
	    		stat.execute();
	    		output.addProperty("data", "Deleted table " + args.getAsJsonPrimitive("tablename").getAsString());
	    		
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