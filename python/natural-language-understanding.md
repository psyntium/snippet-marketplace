## Natural Language Understanding (NLU)

This code shows you how to use the Python SDK to extract concepts, entities, keywords, categories, emotion, sentiment, metadata, relations and semantic roles from a BBC article. 

The code has two basic sections:

1. The *main()* method. This is the entry point to the code. It creates the NLU service instance and then invokes the analyze() method to extract concepts, entities, keywords, categories, emotion, sentiment, metadata, relations and semantic roles from an article.

2. A set of default parameters and the code that runs this module if it is invoked by itself (as opposed to being invoked by a *require()* statement).

See the Python documentation for more information

### Code Walkthrough
1. This code takes url as input, and analyze to extract concepts, entities, keywords, categories, emotion, sentiment, metadata, relations and semantic roles.

2. Line 14 creates the NLU service instance

3. Lines 15-27 is where the NLU instance analyze and extract concepts, entities, keywords, categories, emotion, sentiment, metadata, relations and semantic roles.

### Testing Instructions
1. Change the **url** in **testparams** and see the difference.


### Reference
* [NLU Documentation](https://console.bluemix.net/docs/services/natural-language-understanding/getting-started.html#getting-started-tutorial)

* [NLU API] (https://www.ibm.com/watson/developercloud/natural-language-understanding/api/v1/)