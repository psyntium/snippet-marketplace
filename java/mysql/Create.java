//jar@http://central.maven.org/maven2/mysql/mysql-connector-java/6.0.5/mysql-connector-java-6.0.5.jar  

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;  

public class Create { 	 	
  	  	 
    public String testparams = "{\"hostname\":\"us-cdbr-sl-dfw-01.cleardb.net\","
  	 		+ "\"username\":\"b319ac7e3853e4\","
  	 		+ "\"password\":\"db586ae5\","
  	 		+ "\"dbname\":\"ibmx_b20a18ec42f7a48\","
  	 		+ "\"tablename\":\"student_username\","
  	 		+ "\"studentFirstName\":\"John\","
  	 		+ "\"studentLastName\":\"Doe\","
  	 		+ "\"studentId\":\"12345\"}"; 	  	 
    
    public static void main(String[] args) { 	   
    	Create hello = new Create(); 	   
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
	 		  
		 			String query = "INSERT INTO " + args.getAsJsonPrimitive("tablename").getAsString() + " (firstname, lastname, student_id)"
		    			+ " values (?, ?, ?)";
		    	PreparedStatement stat = conn.prepareStatement(query);
		    	stat.setString(1, args.getAsJsonPrimitive("studentFirstName").getAsString());
		    	stat.setString(2, args.getAsJsonPrimitive("studentLastName").getAsString());
		    	stat.setString(3, args.getAsJsonPrimitive("studentId").getAsString());
	    	
	     		stat.execute();
	     		output.addProperty("status", "Success Created");
	     		
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