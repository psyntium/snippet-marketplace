const testparams = {
  "textToAnalyze": "I know the times are difficult! Our sales have been \
      disappointing for the past three quarters for our data analytics \
      product suite. We have a competitive data analytics product \
      suite in the industry. But we need to do our job selling it! \
      We need to acknowledge and fix our sales challenges. \
      We can\"t blame the economy for our lack of execution! \
      We are missing critical sales opportunities. \
      Our product is in no way inferior to the competitor products. \
      Our clients are hungry for analytical tools to improve their \
      business outcomes. Economy has nothing to do with it.",
  "username": "",
  "password": "",
  "url": "https://sandbox-watson-proxy.mybluemix.net/tone-analyzer/api",
  "use_unauthenticated": true
}

function main(params) {
  return new Promise(function (resolve, reject) {
    const ToneAnalyzerV3 = require("watson-developer-cloud/tone-analyzer/v3");

    var opts = {
      "version_date": "2016-05-20",
      "url" : params.url || "https://gateway.watsonplatform.net/tone-analyzer/api",
      "use_unauthenticated": isTrue(params.use_unauthenticated)
    }

    if (params.username && params.password) {
      opts.username = params.username;
      opts.password = params.password;
    }

    var tone_analyzer = new ToneAnalyzerV3(opts);

    tone_analyzer.tone({
      "text": params.textToAnalyze
    }, function(err, res) {
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
