## Language Translation

This code shows you how to use the Java API for the Watson language translator service. Given some text, a source language, 
and a target language, Watson translates that text and returns one or more translations to you.

The code has three basic sections: 

1. Default values for the parameters. These are always used by the *public static void main(String[] args)* method. 
If the call to *public static JsonObject main(JsonObject args)* has no parameters or the wrong number of parameters, 
the default values are also used there. 

2. The *public static void main(String[] args)* method. This uses a *JsonParser* to convert the default parameters into a 
JSON object, then it calls... 

3. ...The *public static JsonObject main(JsonObject args)* method. This is the method OpenWhisk invokes once the code is 
deployed as an OpenWhisk action.

### Code Walkthrough
1. Lines 47-56 illustrate the Java API for the service. The Watson SDK wraps the JSON returned by the service as a set of objects 
that are easy to work with in Java. 

2. Notice that it is possible for a translation to return multiple results. If Watson returns only one result, that's the only 
one the code displays. 

### Testing Instructions

Try changing the text or the languages to see what happens.

### Reference
* [Overview of the Language Translator service](https://www.ibm.com/watson/developercloud/language-translator.html)
* [Github repo for the Java API for more information.](https://github.com/watson-developer-cloud/java-sdk/tree/develop/language-translator)
    
