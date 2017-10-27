## Redis - Complete CRUD 

This code shows you how to use the node.js API to run compelte CRUD (Set Multiple Keys, Get Key, Delete Key) flow in Redis database. 

The code has two basic sections:

1. The main() method. This is the entry point to the code. It creates the Redis client and then invokes the client.mset() method to set multiple keys, client.get() to get the key, client.keys() to list all existing keys, client.del() to delete the key, and finally client.flushdb() to delete all existing keys.

2. A set of default parameters and the code that runs this module if it is invoked by itself (as opposed to being invoked by a require() statement).

See the Node.js documentation for more information on the implications of the require.main === module statement.

### Code Walkthrough
1. This code takes Redis hostname, password, port, keys and values as input, and execute full flows of CRUD. At the end of executions, execution logs of each flow will be printed.

### Testing Instructions
1. The code as is, automatically uses a built-in test environment. To use against your own redis system, change the **hostname**, **password**, **port**, **key1**, **value1**, and **key2** and **value2** in **testparams**.

### Reference
* [Redis Documentation](https://redis.io/)
* [Redis Command](https://redis.io/commands/)