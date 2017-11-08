## Visual Recognition

This code shows you how to use the python SDK for the Watson Visual Recognition service. Given an image, Watson attempts to 
identify objects in that image.

The code has two basic sections: 

1. The *main()* function. This is the entry point to the code. It creates the *VisualRecognitionV3* object and then invokes 
the *detect_faces()* method.

2. A set of default parameters and the code that runs this module if it is invoked by itself (as opposed to being invoked 
by a *require()* statement).

### Code Walkthrough
1. This code takes a URL of an image file as input, looks for faces in that image, and attempts to recognize each of those faces. 

### Testing Instructions

1. To identify other people, try these other values for **image_url** in *testparams* :
* Donald Trump: https://www.whitehouse.gov/sites/whitehouse.gov/files/images/45/PE%20Color.jpg
* George W. Bush: https://www.whitehouse.gov/sites/whitehouse.gov/files/images/first-family/43_george_w_bush.jpg
* Bill Clinton: https://www.whitehouse.gov/sites/whitehouse.gov/files/images/first-family/42_bill_clinton.jpg
* Melania Trump: https://www.whitehouse.gov/sites/whitehouse.gov/files/images/flotus.png


### Reference 
* [Overview of the Watson Visual Recognition service](https://www.ibm.com/watson/developercloud/doc/visual-recognition/index.html)
* [Github repo for the Python SDK for more information.](https://github.com/watson-developer-cloud/python-sdk)
    
