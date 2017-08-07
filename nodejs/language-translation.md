## Language Translator

This code shows you how to use the node.js API for the Watson Language Translator service. Given some text, a source language, 
and a target language, Watson translates that text and returns one or more translations to you.

The code has five basic sections: 

1. The *main()* method. This is the entry point to the code. It creates the *LanguageTranslationV2* object and then invokes 
the three translation functions.

2. The *englishToFrench()* function. This takes text and a *LanguageTranslationV2* object as its inputs and calls the 
Language Translator service to convert the input from English into French.

3. The *frenchToSpanish()* function. This converts French text into Spanish.

4. The *spanishToEnglish()* function converts Spanish to English. 

5. A set of default parameters and the code that runs this module if it is invoked by itself (as opposed to being invoked by a 
*require()* statement).

See the [Node.js documentation](https://nodejs.org/api/modules.html#modules_accessing_the_main_module) for more information 
on the implications of the *require.main === module* statement.

### Code Walkthrough
1. This code takes a string as input and translates the string from English to French, from French to Spanish, and finally 
from Spanish into English again.

2. The calls to the Watson Language Translator service are wrapped in JavaScript *Promise*s to handle the asynchronous 
nature of the service.

### Testing Instructions

1. Try changing the **textToTranslate** or **language codes** in *testparams* to see what happens.


### Reference 
* [Overview of the Watson Language Translator service](https://www.ibm.com/watson/developercloud/language-translator.html)
* [Github repo for the NodeJS API for more information.](https://github.com/watson-developer-cloud/node-sdk)
    
