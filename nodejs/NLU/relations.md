## NLU - Identify Relations

This code shows you how to use the node.js API to analyze emotion from given text.

The code has two basic sections:

1. The main() method. This is the entry point to the code. It creates the NLU service instance and then invokes the analyze() method to identify relations implied in given text.

2. A set of default parameters and the code that runs this module if it is invoked by itself (as opposed to being invoked by a require() statement).

See the Node.js documentation for more information on the implications of the require.main === module statement.

### Code Walkthrough
1. This code takes text as input, and analyze to identify relations. The call is wrapped in a JavaScript Promise to handle the asynchronous nature of the service.

2. Lines 13-19 creates the NLU service instance

3. Lines 21-26 constructs parameters object, which will be fed into NLU instance to analyze and identify relations per lines 28-33.

### Testing Instructions
1. Change the **text** in **testparams** and see the difference.


### Reference
* [NLU Documentation](https://console.bluemix.net/docs/services/natural-language-understanding/getting-started.html#getting-started-tutorial)

* [NLU API] (https://www.ibm.com/watson/developercloud/natural-language-understanding/api/v1/)