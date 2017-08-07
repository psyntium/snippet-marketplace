## Language Classifier

This code shows you how to use the Java API for the Watson natural language classification service. Given some text and a 
context, Watson analyzes the text and returns a list of categories relevant to that text.

The code has three basic sections: 

1. Default values for the parameters. These are always used by the *public static void main(String[] args)* method. 
If the call to *public static JsonObject main(JsonObject args)* has no parameters or the wrong number of parameters, 
the default values are also used there. 

2. The *public static void main(String[] args)* method. This uses a *JsonParser* to convert the default parameters into a 
JSON object, then it calls... 

3. ...The *public static JsonObject main(JsonObject args)* method. This is the method OpenWhisk invokes once the code is 
deployed as an OpenWhisk action.

### Code Walkthrough
1. Lines 55-66 illustrate the Java API for the service. The Watson SDK wraps the JSON returned by the service as a set of objects 
that are easy to work with in Java. 

2. The results include the most likely classification (accessed via the *topClass()* method) and a list of all classifications 
that might be relevant.

### Testing Instructions

See what else Watson can do. Change the text in **testparams** to see what happens.

### Reference
* [Overview of the Watson Natural Language Classifier](https://www.ibm.com/watson/developercloud/nl-classifier.html)
* [Github repo for the Java API for more information.](https://github.com/watson-developer-cloud/java-sdk/tree/develop/natural-language-classifier)
    
