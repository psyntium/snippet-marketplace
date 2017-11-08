var request = require("request");

var testparams = {
  "http_url": "https://openwhisk.ng.bluemix.net/api/v1/web/ecodadmi%40us.ibm.com_cloudsandbox/default/simple-get-post.json",
  "name": "How to program in Go 2",
  "serialno": "1-2345243",
  "author": "Michael Lock2"
}

function main(params) {
  return new Promise(function (resolve, reject) {
        var options = { 
            method: 'POST',
            url: params.http_url,
            headers: {'content-type': 'application/x-www-form-urlencoded'},
            form: { name: params.name, serialno: params.serialno, author: params.author} 
        };
        request(options, function (error, response, body) {
            if (error) return reject({err: error});
            return resolve({result: body});
        });
  })
}

if (require.main === module)
  main(testparams)
    .then((results) => console.log(JSON.stringify(results, null, 2)))
    .catch((error) => console.log(error.message));

exports.main = main;

