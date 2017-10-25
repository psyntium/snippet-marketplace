## Redis - Delete Key

This code shows you how to use the Java API to delete key from Redis database. 

The code has three basic sections: 

1. Default values for the parameters. These are always used by the *public static void main(String[] args)* method. 
If the call to *public static JsonObject main(JsonObject args)* has no parameters or the wrong number of parameters, the default values are also used there. 

2. The *public static void main(String[] args)* method. This uses a *JsonParser* to convert the default parameters into a JSON object, then it calls... 

3. ...The *public static JsonObject main(JsonObject args)* method. This is the method OpenWhisk invokes once the code is deployed as an OpenWhisk action.

### Code Walkthrough
1. Lines 24-30 illustrate the Java API to create Redis client, and line 32-33 to delete the key and to show the result.

2. The results show the status of success/failure deleting the key.

### Testing Instructions
1. The code as is, automatically uses a built-in test environment. To use against your own redis system, change the **hostname**, **password**, **port**, and **key** in **testparams**.

### Reference
* [Redis Documentation](https://redis.io/)
* [Redis Command](https://redis.io/commands/)