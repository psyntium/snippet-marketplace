## Cloudant - Read Document by ID

This code shows you how to use the Java API to read specified document from cloudant database. 

The code has three basic sections: 

1. Default values for the parameters. These are always used by the *public static void main(String[] args)* method. 
If the call to *public static JsonObject main(JsonObject args)* has no parameters or the wrong number of parameters, 
the default values are also used there. 

2. The *public static void main(String[] args)* method. This uses a *JsonParser* to convert the default parameters into a 
JSON object, then it calls... 

3. ...The *public static JsonObject main(JsonObject args)* method. This is the method OpenWhisk invokes once the code is 
deployed as an OpenWhisk action.

### Code Walkthrough
1. Lines 39-62 illustrate the Java API for the service to read the specified document from the cloudant database, and show the results.

2. The results show the document content, or failure messages.

### Testing Instructions
1. Before this, please create a cloudant database of your own, by following CreateDB sample in the catalog.

2. The code as is, automatically uses a built-in test environment. Please change the **dbname** to `person_MYNAME` (where MYNAME should be replaced by your name to make it unique) in **testparams**.

3. To use against your own Cloudant system, please change **dbname**, **username**, and **password** in **testparams**.

### Reference
* [Cloudant Documentation](https://docs.cloudant.com/)