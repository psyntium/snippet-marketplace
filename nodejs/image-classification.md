## Visual Recognition

This code shows you how to use the node.js API for the Watson Visual Recognition service. Given an image, Watson attempts to 
identify objects in that image.

The code has two basic sections: 

1. The *main()* method. This is the entry point to the code. It creates the *VisualRecognitionV3* object and then invokes 
the *classify()* method.

2. A set of default parameters and the code that runs this module if it is invoked by itself (as opposed to being invoked 
by a *require()* statement).

See the [Node.js documentation](https://nodejs.org/api/modules.html#modules_accessing_the_main_module) for more information 
on the implications of the *require.main === module* statement.

### Code Walkthrough
1. This code takes a URL of an image file as input and looks for objects in that image. The call to the Watson 
Visual Recognition service is wrapped in a JavaScript *Promise* to handle the asynchronous nature of the service. 

2. Notice that it is possible for a translation to return multiple results. If Watson returns only one result, that's the only 
one the code displays. 

### Testing Instructions

1. To see what else Watson can identify, try these other values for *imageurl* in **testparams**
* A cat: https://upload.wikimedia.org/wikipedia/commons/thumb/4/4d/Cat_November_2010-1a.jpg/449px-Cat_November_2010-1a.jpg
* A school bus: https://upload.wikimedia.org/wikipedia/commons/f/f3/Laidlaw_school_bus.jpg
* A pizza: https://upload.wikimedia.org/wikipedia/commons/thumb/d/d1/Pepperoni_pizza.jpg/800px-Pepperoni_pizza.jpg


### Reference 
* [Overview of the Watson Visual Recognition service](https://www.ibm.com/watson/developercloud/doc/visual-recognition/index.html)
* [Github repo for the NodeJS API for more information.](https://github.com/watson-developer-cloud/node-sdk)
    
