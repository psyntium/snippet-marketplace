## HTTP GET Request

This code shows you how to use the node.js to setup a server to handle simple GET and POST request.

The code has two basic sections: 

1. The *main()* method. This is the entry point to the code. It is a running server to handle GET/POST request.

2. A set of default parameters and the code that runs this module if it is invoked by itself (as opposed to being invoked 
by a *require()* statement).

See the [Node.js documentation](https://nodejs.org/api/modules.html#modules_accessing_the_main_module) for more information 
on the implications of the *require.main === module* statement.

### Code Walkthrough
1. Line 6-11 constructs the default dataset and store in memory. 

2. Line 13-24 to handle POST request to add in new data into the dataset, and return the result of latest dataset.

3. Line 25 to handle GET request to return dataset.

### Testing Instructions

1. Since this is a server-side code, it need to be deployed as a running instance to work. 

2. To test along with http-get-client and http-post-client samples, after deploy this sample, take note of the generated URL and paste it to http_url in the client samples.

### Reference
* [NPM Request Module](https://www.npmjs.com/package/request)
    
