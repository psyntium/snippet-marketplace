const testparams = {
  "nlu_source": "http://www.techradar.com/reviews/wearables/apple-watch-2-1323213/review",
  "target": ["fitness"],
  "username": "",
  "password": "",
  "url": "https://sandbox-watson-proxy.mybluemix.net/natural-language-understanding/api",
  "use_unauthenticated": true
}

function main(params) {
  return new Promise(function (resolve, reject) {
    const NaturalLanguageUnderstandingV1 = require("watson-developer-cloud/natural-language-understanding/v1.js");

    var natural_language_understanding = new NaturalLanguageUnderstandingV1({
      "username": params.username || "",
      "password": params.password || "",
      "version_date" : NaturalLanguageUnderstandingV1.VERSION_DATE_2017_02_27,
      "url": params.url || "https://gateway.watsonplatform.net/natural-language-understanding/api",
      "use_unauthenticated": isTrue(params.use_unauthenticated)
    });

    var parameters = {
        url : params.nlu_source, 
        features : {
            sentiment : {
                targets: params.target
            } 
        }
    };

    natural_language_understanding.analyze(parameters, function (err, res) {
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