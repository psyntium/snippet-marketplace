//jar@http://central.maven.org/maven2/com/sendgrid/sendgrid-java/4.1.2/sendgrid-java-4.1.2.jar;http://central.maven.org/maven2/com/sendgrid/java-http-client/4.1.0/java-http-client-4.1.0.jar;http://central.maven.org/maven2/org/apache/httpcomponents/httpcore/4.4.4/httpcore-4.4.4.jar;http://central.maven.org/maven2/org/mockito/mockito-core/1.10.19/mockito-core-1.10.19.jar;http://central.maven.org/maven2/org/hamcrest/hamcrest-core/1.1/hamcrest-core-1.1.jar;http://central.maven.org/maven2/org/objenesis/objenesis/2.1/objenesis-2.1.jar;http://central.maven.org/maven2/commons-logging/commons-logging/1.2/commons-logging-1.2.jar;http://central.maven.org/maven2/commons-codec/commons-codec/1.9/commons-codec-1.9.jar;http://central.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.5.3/jackson-core-2.5.3.jar;http://central.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.5.3/jackson-databind-2.5.3.jar;http://central.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.5.3/jackson-annotations-2.5.3.jar

import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

public class SendGridSample {
    public String testparams = "{\"fromEmail\":\"test@example.com\"," 
        + "\"toEmail\":\"snippet.java@gmail.com\"," 	 		
        + "\"subject\":\"Email sent from Sandbox\"," 	 		
        + "\"content\":\"This is email sending from sandbox using sendgrid\"," 	 		
        + "\"apikey\":\"\"}"; 	  	 
    
    public static void main(String[] args) { 	   
    		SendGridSample hello = new SendGridSample(); 	   
	      System.out.println(main(new JsonParser().parse(hello.testparams).getAsJsonObject()));     
	  } 	  	 
  
  public static JsonObject main(JsonObject args) { 	   
      
      JsonObject output = new JsonObject(); 
      
      Email fromEmail = new Email(args.getAsJsonPrimitive("fromEmail").getAsString());
      Email toEmail = new Email(args.getAsJsonPrimitive("toEmail").getAsString());
      String subject = args.getAsJsonPrimitive("subject").getAsString();
      Content content = new Content("text/plain", args.getAsJsonPrimitive("content").getAsString());
      Mail mail = new Mail(fromEmail, subject, toEmail, content);

      String apikey = args.getAsJsonPrimitive("apikey").getAsString().isEmpty()?"SANDBOX_SENDGRID_APIKEY":args.getAsJsonPrimitive("apikey").getAsString();
      SendGrid sg = new SendGrid(apikey);
      Request request = new Request();
      try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      output.addProperty("Status", response.getStatusCode());
	      output.addProperty("Body", response.getBody());
	      if(response.getStatusCode() >= 200 && response.getStatusCode() < 400)
	      	output.addProperty("Message", "Email sent");
      } catch (IOException ex) {
      		output.addProperty("err", ex.getMessage());
      }
      
      return output;
	}
}