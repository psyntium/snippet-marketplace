An app that demonstrates how to load NPM packages at runtime. By default, the IBM Cloud Functions environment provides a limited number of packages. This sample extends the packages available by loading the `zipcodes` package to display the details of the city associated with a U.S. zip code. 

To load an NPM package, start your code with this syntax: 

```javascript
// npm zipcodes
```

To use the package, simply use a `require` statement as you normally would: 

```javascript
  const zipcodes = require('zipcodes');
```

To load multiple packages, separate the package names with spaces:

```javascript
// npm zipcodes flying-car
...
  const flyingCar = require('flying-car');
  const zipcodes = require('zipcodes');
```
