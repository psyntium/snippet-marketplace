## HTTP GET Request

This code shows you how to use the node.js to do a HTTP GET request.

The code has two basic sections: 

1. The *main()* method. This is the entry point to the code. It performs a GET request to a proxy website and return with result.

2. A set of default parameters and the code that runs this module if it is invoked by itself (as opposed to being invoked 
by a *require()* statement).

See the [Node.js documentation](https://nodejs.org/api/modules.html#modules_accessing_the_main_module) for more information 
on the implications of the *require.main === module* statement.

### Code Walkthrough
1. This code takes a URL to perform a GET request, and print out the result returned.
The call to the GET request is wrapped in a JavaScript **Promise** to handle the asynchronous nature of the service.

### Testing Instructions

1. The default **http_url** is provided solely for illustration of these simple GET and POST request. Try change to another URL 
to get the different content.

### Reference
* [NPM Request Module](https://www.npmjs.com/package/request)
    
