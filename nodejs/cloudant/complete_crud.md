## Cloudant - Complete CRUD 

This code shows you how to use the node.js API to run compelte CRUD (Create Database, Create Document, List Documents, Read Document, Update Document, Delete Document, Delete Database) flows in Cloudant. 

The code has two basic sections:

1. The main() method. This is the entry point to the code. It creates the Cloudant client and then invokes the cloudant.db.create() to create cloudant database, db.insert() to create new document, db.list() to list all available documents, db.get() to read a document, db.insert() again but to update document this time, db.destroy() to delete document, and cloudant.db.destroy() to delete the Cloudant database. 

2. A set of default parameters and the code that runs this module if it is invoked by itself (as opposed to being invoked by a require() statement).

See the Node.js documentation for more information on the implications of the require.main === module statement.

### Code Walkthrough
1. This code takes Cloudant dbname, username, password, doc_name, doc_email, and doc_comment as input, and execute full flows of CRUD. At the end of executions, execution logs of each flow will be printed.

2. Note that both Cloudant "create new" and "update" executions are using same method db.insert(data). Difference is "update" need to specify 'id' and 'rev', which are not necessary for "create new".

### Testing Instructions
1. The code as is, automatically uses a built-in test environment. Please change the **dbname** to `person_MYNAME` (where MYNAME should be replaced by your name to make it unique) in **testparams**. 

3. To use against your own Cloudant system, please change **dbname**, **username**, and **password** in **testparams**.

### Reference
* [Cloudant Documentation](https://docs.cloudant.com/)