## HTTP POST Request

This code shows you how to use the Java API to do a HTTP POST request.

The code has three basic sections: 

1. Default values for the parameters. These are always used by the *public static void main(String[] args)* method. 
If the call to *public static JsonObject main(JsonObject args)* has no parameters or the wrong number of parameters, 
the default values are also used there. 

2. The *public static void main(String[] args)* method. This uses a *JsonParser* to convert the default parameters into a 
JSON object, then it calls... 

3. ...The *public static JsonObject main(JsonObject args)* method. This is the method OpenWhisk invokes once the code is 
deployed as an OpenWhisk action.

### Code Walkthrough
1. Lines 35-37 illustrate on how to construct a Apache HTTP client.

2. Lines 39-42 supply the params value.

3. Lines 45-64 shows the way to perform the POST request and print out the updated result.

### Testing Instructions

1. The default **http_url** along with other params **name**, **serialno**, and **author** are provided solely for illustration of these simple GET and POST request. Try change to another URL and necessary params to get the different content.

    
