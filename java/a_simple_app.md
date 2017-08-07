## Overview
OpenWhisk provides a distributed compute service to execute application logic in response to events.  
There are several key architectural concepts:

1. **Triggers:** 
A class of events emitted by event sources.

2. **Actions:** 
Encapsulate the actual code to be executed which support multiple language bindings including NodeJS, Swift and 
arbitrary binary programs encapsulated in Docker Containers.  
Actions invoke any part of an open ecosystem including existing Bluemix services for analytics, data, cognitive, 
or any other 3rd party service.

3. **Rules:** 
An association between a trigger and an action.

4. **Packages:** 
Describe external services in a uniform manner.

Combined these allow developers to compose solutions using modern abstraction and chaining which can be created, accessed, 
updated and deleted via the CLI. For convenience, special SDKs like our iOS SDK can be used for specific environments.

