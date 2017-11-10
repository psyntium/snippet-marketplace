## Personality Insights

This code shows you how to use the node.js API for the Watson Personality Insights service. Given some text, Watson analyzes the openness, conscientiousness, extraversion, agreeableness, emotional range, and needs of the speaker.

The code has two basic sections:

1. The *main()* method. This is the entry point to the code. It creates the *PersonalityInsightsV3* object and then invokes the *profile* method.

2. A set of default parameters and the code that runs this module if it is invoked by itself (as opposed to being invoked by a *require()* statement).

See the Python documentation for more information


### Code Walkthrough
1. This code takes a string as input and analyzes the personality of the speaker.


### Testing Instructions
1. Try changing the text in *testparams* to see what happens.


### Reference
* [Personality Insights Documentation](https://console.bluemix.net/docs/services/personality-insights/getting-started.html#getting-started-tutorial)

* [Personality Insights API](https://www.ibm.com/watson/developercloud/personality-insights/api/v3/)