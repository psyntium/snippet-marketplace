## Cloudant - Create Database

This code shows you how to use the Java API to run compelte CRUD (Create Database, Create Document, List Documents, Read Document, Update Document, Delete Document, Delete Database) flows in Cloudant. 

The code has three basic sections: 

1. Default values for the parameters. These are always used by the *public static void main(String[] args)* method. 
If the call to *public static JsonObject main(JsonObject args)* has no parameters or the wrong number of parameters, 
the default values are also used there. 

2. The *public static void main(String[] args)* method. This uses a *JsonParser* to convert the default parameters into a 
JSON object, then it calls... 

3. ...The *public static JsonObject main(JsonObject args)* method. This is the method OpenWhisk invokes once the code is 
deployed as an OpenWhisk action.

### Code Walkthrough
1. Lines 46-49 illustrate the Java API for the service to create a Cloudant client.

2. Lines 52-54 to create cloudant database.

3. Lines 56-64 to create document.

4. Lines 66-69 to list all documents.

5. Lines 71-81 and 90-99 to read document.

6. Lines 83-88 to update document. 

7. Lines 101-104 to delete document.

8. Lines 106-108 to delete database.

9. The results show the status of success/failure of the CRUD flow.

### Testing Instructions
1. The code as is, automatically uses a built-in test environment. Please change the **dbname** to `person_MYNAME` (where MYNAME should be replaced by your name to make it unique) in **testparams**.

2. To use against your own Cloudant system, please change **dbname**, **username**, and **password** in **testparams**.

### Reference
* [Cloudant Documentation](https://docs.cloudant.com/)