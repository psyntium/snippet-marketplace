## Redis - Set Hashed field value of Key

This code shows you how to use the node.js API to sets field in the hash stored at key to value in Redis database. 

The code has two basic sections:

1. The main() method. This is the entry point to the code. It creates the Redis client and then invokes the client.hset() method to set the hashed field value of the key.

2. A set of default parameters and the code that runs this module if it is invoked by itself (as opposed to being invoked by a require() statement).

See the Node.js documentation for more information on the implications of the require.main === module statement.

### Code Walkthrough
1. This code takes Redis hostname, password, port, key, field, and value as input, and set the hashed field value of key from the Redis database. The call to hset is wrapped in a JavaScript Promise to handle the asynchronous nature of the service.

### Testing Instructions
1. The code as is, automatically uses a built-in test environment. To use against your own redis system, change the **hostname**, **password**, **port**, **key**, **field**, and **value** in **testparams**.

### Reference
* [Redis Documentation](https://redis.io/)
* [Redis Command](https://redis.io/commands/)