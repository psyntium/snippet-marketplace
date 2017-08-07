## Visual Recognition

This code shows you how to use the node.js API for the Watson Visual Recognition service. Given an image, Watson looks for 
faces in that image and attempts to recognize each of those faces.

The code has two basic sections: 

1. The *main()* method. This is the entry point to the code. It creates the *VisualRecognitionV3* object and then invokes 
the *detectFaces()* method.

2. A set of default parameters and the code that runs this module if it is invoked by itself (as opposed to being invoked 
by a *require()* statement).

### Code Walkthrough
1. Lines 55-66 illustrate the Java API for the service. The Watson SDK wraps the JSON returned by the service as a set of objects 
that are easy to work with in Java. 

2. Notice that it is possible for a translation to return multiple results. If Watson returns only one result, that's the only 
one the code displays. 

### Testing Instructions

1. This code takes a URL of an image file as input, looks for faces in that image, and attempts to recognize each of those faces. 
The call to the Watson Visual Recognition service is wrapped in a JavaScript **Promise** to handle the asynchronous nature 
of the service.

2. To identify other people, try these other values for **imageurl** in *testparams* :
* Donald Trump: https://www.whitehouse.gov/sites/whitehouse.gov/files/images/45/PE%20Color.jpg
* George W. Bush: https://www.whitehouse.gov/sites/whitehouse.gov/files/images/first-family/43_george_w_bush.jpg
* Bill Clinton: https://www.whitehouse.gov/sites/whitehouse.gov/files/images/first-family/42_bill_clinton.jpg
* Melania Trump: https://www.whitehouse.gov/sites/whitehouse.gov/files/images/flotus.png

### Reference
* [Node.js documentation](https://nodejs.org/api/modules.html#modules_accessing_the_main_module)

  *for more information on the implications of the **require.main === module** statement.*
  
* [Overview of the Watson Visual Recognition service](https://www.ibm.com/watson/developercloud/doc/visual-recognition/index.html)
* [Github repo for the NodeJS API for more information.](https://github.com/watson-developer-cloud/node-sdk)
    
