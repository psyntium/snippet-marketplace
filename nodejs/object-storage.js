// npm bluemix-objectstorage

const testparams = {
  "projectId": "<PROJECT_ID>",
  "userId": "<USER_ID>",
  "password": "<PASSWORD>",
  "auth_url": "https://identity.open.softlayer.com"
}

function main(params) {
  return new Promise(function (resolve, reject) {
    const ObjectStorage = require('bluemix-objectstorage').ObjectStorage;
    
    var objStorage = new ObjectStorage({
      "projectId": params.projectId || "",
      "userId": params.userId || "",
      "password": params.password || "",
      "region": ObjectStorage.Region.DALLAS,
      "auth_url": params.auth_url || ""
    });

    objStorage.listContainers()
      .then(function (result) {
        resolve(result)
      })
      .catch(function (err) {
        reject(err)
      });
  });
}

if (require.main === module)
  main(testparams)
    .then((results) => console.log(JSON.stringify(results, null, 2)))
    .catch((error) => console.log(error.message));

exports.main = main;
