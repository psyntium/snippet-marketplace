## MySQL - Create Data

This code shows you how to use the node.js API to create a record of data in MySQL database. 

The code has two basic sections:

1. The main() method. This is the entry point to the code. It creates the MySQL connection pool, set the query and then invokes the pool.query() method to create the data in specified table.

2. A set of default parameters and the code that runs this module if it is invoked by itself (as opposed to being invoked by a require() statement).

See the Node.js documentation for more information on the implications of the require.main === module statement.

### Code Walkthrough
1. This code takes MySQL hostname, username, password, dbname, tablename, and some other data info (including firstname, lastname, and id) as input, and create a record of data in the MySQL database. 

2. The call to set key is wrapped in a JavaScript Promise to handle the asynchronous nature of the service.

### Testing Instructions
1. The code as is, automatically uses a built-in test environment. To use against your own MySQL system, change the **hostname**, **username**, **password**, and **dbname** in **testparams**.

### Reference
* [MySQL Page](https://www.mysql.com/)
* [MySQL NPM Documentation](https://www.npmjs.com/package/mysql)