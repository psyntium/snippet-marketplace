## Cloudant - Create Document

This code shows you how to use the node.js API to create a document in cloudant database. 

The code has two basic sections:

1. The main() method. This is the entry point to the code. It creates the Cloudant object and then invokes the db.insert() method to save the document.

2. A set of default parameters and the code that runs this module if it is invoked by itself (as opposed to being invoked by a require() statement).

See the Node.js documentation for more information on the implications of the require.main === module statement.

### Code Walkthrough
1. This code takes a database name, name, email, and comment as input, and attempts to construct a json object and finally saved into the Cloudant database. The call to create document is wrapped in a JavaScript Promise to handle the asynchronous nature of the service.

### Testing Instructions
1. Before this, please create a cloudant database of your own, by following CreateDB sample in the catalog.

2. Change the **dbname** to `person_MYNAME` (where MYNAME should be replaced by your name), **name**, **email**, and **comment** in **testparams** to see what happens.

### Reference
* [Cloudant Documentation](https://docs.cloudant.com/)