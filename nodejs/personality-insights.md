## Personality Insights

This code shows you how to use the node.js API for the Watson Personality Insights service. Given some text, Watson analyzes 
the openness, conscientiousness, extraversion, agreeableness, emotional range, and needs of the speaker.

The code has two basic sections: 

1. The *main()* method. This is the entry point to the code. It creates the *PersonalityInsightsV3* object and then invokes 
the *profile* method.

2. A set of default parameters and the code that runs this module if it is invoked by itself (as opposed to being invoked by a 
*require()* statement).

See the [Node.js documentation](https://nodejs.org/api/modules.html#modules_accessing_the_main_module) for more information 
on the implications of the *require.main === module* statement.

### Code Walkthrough
1. This code takes a string as input and analyzes the personality of the speaker.

2. The calls to the Watson Personality Insights service are wrapped in JavaScript *Promise*s to handle the asynchronous 
nature of the service.

### Testing Instructions

1. Try changing the text in *testparams* to see what happens.


### Reference 
* [Overview of the Watson Personality Insights service](https://www.ibm.com/watson/developercloud/personality-insights.html)
* [Github repo for the NodeJS API for more information.](https://github.com/watson-developer-cloud/node-sdk)
    
