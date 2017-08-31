// npm zipcodes

const testparams = {
  "zipcode": "90210"
}

function main(params) {
  return new Promise(function (resolve, reject) {
    const zipcodes = require('zipcodes');

    try {
      resolve(zipcodes.lookup(params.zipcode))
    } catch (error) {
      reject({ error });
    }
  });
}

if (require.main === module)
  main(testparams)
    .then((results) => console.log(JSON.stringify(results, null, 2)))
    .catch((error) => console.log(error.message));

exports.main = main;
