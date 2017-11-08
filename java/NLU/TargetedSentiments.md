## NLU - Identify Sentiments with Target text

This code shows you how to use the Java API to identify sentiments from article of given url as well as target text.

The code has three basic sections: 

1. Default values for the parameters. These are always used by the *public static void main(String[] args)* method. 
If the call to *public static JsonObject main(JsonObject args)* has no parameters or the wrong number of parameters, 
the default values are also used there. 

2. The *public static void main(String[] args)* method. This uses a *JsonParser* to convert the default parameters into a 
JSON object, then it calls... 

3. ...The *public static JsonObject main(JsonObject args)* method. This is the method OpenWhisk invokes once the code is 
deployed as an OpenWhisk action.

### Code Walkthrough
1. Lines 34-37 illustrate the Java API to create the NLU service instance.

2. Line 45-53 shows how NLU service process the given text and return result.

3. The results show sentiments identified in given url compared to target text.

### Testing Instructions
1. Change the **text** and **targets** in **testparams** and see the difference.


### Reference
* [NLU Documentation](https://console.bluemix.net/docs/services/natural-language-understanding/getting-started.html#getting-started-tutorial)

* [NLU API] (https://www.ibm.com/watson/developercloud/natural-language-understanding/api/v1/)