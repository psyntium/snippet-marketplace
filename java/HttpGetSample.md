## HTTP GET Request

This code shows you how to use the Java API to do a HTTP GET request.

The code has three basic sections: 

1. Default values for the parameters. These are always used by the *public static void main(String[] args)* method. 
If the call to *public static JsonObject main(JsonObject args)* has no parameters or the wrong number of parameters, 
the default values are also used there. 

2. The *public static void main(String[] args)* method. This uses a *JsonParser* to convert the default parameters into a 
JSON object, then it calls... 

3. ...The *public static JsonObject main(JsonObject args)* method. This is the method OpenWhisk invokes once the code is 
deployed as an OpenWhisk action.

### Code Walkthrough
1. Lines 27-29 illustrate on how to construct a Apache HTTP client.

2. Lines 31-48 shows the way to perform the GET request and print out the result returned.

### Testing Instructions

1. The default **http_url** is provided solely for illustration of these simple GET and POST request. Try change to another URL 
to get the different content.

    
