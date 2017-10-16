## Cloudant - Create Database

This code shows you how to use the Java API to create a cloudant database. 

The code has three basic sections: 

1. Default values for the parameters. These are always used by the *public static void main(String[] args)* method. 
If the call to *public static JsonObject main(JsonObject args)* has no parameters or the wrong number of parameters, 
the default values are also used there. 

2. The *public static void main(String[] args)* method. This uses a *JsonParser* to convert the default parameters into a 
JSON object, then it calls... 

3. ...The *public static JsonObject main(JsonObject args)* method. This is the method OpenWhisk invokes once the code is 
deployed as an OpenWhisk action.

### Code Walkthrough
1. Lines 31-36 illustrate the Java API for the service to create a new cloudant database. 

2. The results show the status of success/failure creating cloudant database.

### Testing Instructions
1. This should be the **FIRST** step before proceeding with other cloudant samples.

2. Change the **dbname** to `person_MYNAME` (where MYNAME should be replaced by your name to make it unique) in **testparams** to see what happens.

### Reference
* [Cloudant Documentation](https://docs.cloudant.com/)