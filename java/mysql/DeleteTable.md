## MySQL - Delete Table

This code shows you how to use the Java API to delete a table from MySQL database. 

The code has three basic sections: 

1. Default values for the parameters. These are always used by the *public static void main(String[] args)* method. 
If the call to *public static JsonObject main(JsonObject args)* has no parameters or the wrong number of parameters, the default values are also used there. 

2. The *public static void main(String[] args)* method. This uses a *JsonParser* to convert the default parameters into a JSON object, then it calls... 

3. ...The *public static JsonObject main(JsonObject args)* method. This is the method OpenWhisk invokes once the code is deployed as an OpenWhisk action.

### Code Walkthrough
1. Lines 33-36 illustrate the Java API to create connection to MySQL database.

2. Lines 38-40 prepares the query and necessary params, and finally execute the query.

3. The result is returned in line 41, which show the status of success/failure deleting the table.

### Testing Instructions
1. The code as is, automatically uses a built-in test environment. To use against your own redis system, change the **hostname**, **username**, **password**, and **dbname** in **testparams**.

### Reference
* [MySQL Official Page](https://www.mysql.com/)
* [MySQL Java Documentation](https://dev.mysql.com/doc/connector-j/5.1/en/)