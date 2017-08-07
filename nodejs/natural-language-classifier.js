const testparams = {
  "textToClassify": "Will it rain tomorrow?",
  "classifier_id": "359f41x201-nlc-180573",
  "username": "",
  "password": "",
  "url": "https://sandbox-watson-proxy.mybluemix.net/natural-language-classifier/api",
  "use_unauthenticated": true
}

function main(params) {
  return new Promise(function (resolve, reject) {
    const NaturalLanguageClassifierV1 = require("watson-developer-cloud/natural-language-classifier/v1");

    var natural_language_classifier = new NaturalLanguageClassifierV1({
      "username": params.username || "",
      "password": params.password || "",
      "version": "v1",
      "url": params.url || "https://gateway.watsonplatform.net/natural-language-classifier/api",
      "use_unauthenticated": isTrue(params.use_unauthenticated)
    });

    natural_language_classifier.classify({
      "text": params.textToClassify,
      "classifier_id": params.classifier_id
    }, function (err, res) {
      if (err)
        reject(err);
      else
        resolve(res);
    });
  });
}

function isTrue(value) {
  if (value == "true" || value == true)
    return true;
  return false;
}

if (require.main === module)
  main(testparams)
    .then((results) => console.log(JSON.stringify(results, null, 2)))
    .catch((error) => console.log(error.message));

exports.main = main;
