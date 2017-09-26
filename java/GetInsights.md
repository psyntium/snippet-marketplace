## Language Translation

This code shows you how to use the Java API for the Watson Personality Insights service. Given some text, Watson analyzes
the openness, conscientiousness, extraversion, agreeableness, emotional range, and needs of the speaker.

The code has three basic sections: 

1. Default values for the parameters. These are always used by the *public static void main(String[] args)* method. 
If the call to *public static JsonObject main(JsonObject args)* has no parameters or the wrong number of parameters, 
the default values are also used there. 

2. The *public static void main(String[] args)* method. This uses a *JsonParser* to convert the default parameters into a 
JSON object, then it calls... 

3. ...The *public static JsonObject main(JsonObject args)* method. This is the method OpenWhisk invokes once the code is 
deployed as an OpenWhisk action.

### Code Walkthrough
1. Lines 65-92 illustrate the Java API for the service. The Watson SDK wraps the JSON returned by the service as a set of objects 
that are easy to work with in Java. 

2. The results include a comment from Watson about the text along with the  personality data Watson inferred from it.

### Testing Instructions

Try changing the text in **textparams** to see what happens.

### Reference
* [Overview of the Watson Personality Insight service](https://www.ibm.com/watson/developercloud/personality-insights.html)
* [Github repo for the Java API for more information.](https://github.com/watson-developer-cloud/java-sdk/tree/develop/personality-insights)
    
