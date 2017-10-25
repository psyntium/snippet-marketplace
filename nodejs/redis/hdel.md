## Redis - Delete Hashed field of Key

This code shows you how to use the node.js API to removes the specified fields from the hash stored at key from Redis database. 

The code has two basic sections:

1. The main() method. This is the entry point to the code. It creates the Redis client and then invokes the client.hdel() method to delete the hashed field of the key.

2. A set of default parameters and the code that runs this module if it is invoked by itself (as opposed to being invoked by a require() statement).

See the Node.js documentation for more information on the implications of the require.main === module statement.

### Code Walkthrough
1. This code takes Redis hostname, password, port, key, and field as input, and delete the hashed field of key from the Redis database. The call to hdelete is wrapped in a JavaScript Promise to handle the asynchronous nature of the service.

### Testing Instructions
1. The code as is, automatically uses a built-in test environment. To use against your own redis system, change the **hostname**, **password**, **port**, **key**, and **field** in **testparams**.

### Reference
* [Redis Documentation](https://redis.io/)
* [Redis Command](https://redis.io/commands/)