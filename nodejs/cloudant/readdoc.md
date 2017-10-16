## Cloudant - Read Document by ID

This code shows you how to use the node.js API to read specified document from cloudant database. 

The code has two basic sections:

1. The main() method. This is the entry point to the code. It creates the Cloudant object and then invokes the db.get() to read a specified document content from the Cloudant database.

2. A set of default parameters and the code that runs this module if it is invoked by itself (as opposed to being invoked by a require() statement).

See the Node.js documentation for more information on the implications of the require.main === module statement.

### Code Walkthrough
1. This code takes a database name and document id as input, and attempts to read a document content from a specified database. The call to get document is wrapped in a JavaScript Promise to handle the asynchronous nature of the service.

### Testing Instructions
1. Before this, please create a cloudant database of your own, by following CreateDB sample in the catalog.

2. Change the **dbname** to `person_MYNAME` (where MYNAME should be replaced by your name) and **docid** in **testparams** to see what happens.

### Reference
* [Cloudant Documentation](https://docs.cloudant.com/)