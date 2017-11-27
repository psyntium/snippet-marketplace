## HTTP POST Request

This code shows you how to use the node.js to do a HTTP POST request.

The code has two basic sections: 

1. The *main()* method. This is the entry point to the code. It performs a POST request to a proxy website with parameters and return with updated result set.

2. A set of default parameters and the code that runs this module if it is invoked by itself (as opposed to being invoked 
by a *require()* statement).

See the [Node.js documentation](https://nodejs.org/api/modules.html#modules_accessing_the_main_module) for more information 
on the implications of the *require.main === module* statement.

### Code Walkthrough
1. This code takes a URL and parameters (name, serialno, author) to perform a POST request, and print out the updated result set.
The call to the POST request is wrapped in a JavaScript **Promise** to handle the asynchronous nature of the service.

### Testing Instructions

1. The default **http_url** along with other params **name**, **serialno**, and **author** are provided solely for illustration of these simple GET and POST request. Try change to another URL and necessary params to get the different content.

### Reference
* [NPM Request Module](https://www.npmjs.com/package/request)
    
