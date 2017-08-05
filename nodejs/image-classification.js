const testparams = {
  "api_key": "",
  "imageurl": "https://raw.githubusercontent.com/watson-developer-cloud/doc-tutorial-downloads/master/visual-recognition/fruitbowl.jpg",
  "url" : "https://sandbox-watson-proxy.mybluemix.net/visual-recognition/api",
  "use_unauthenticated" : true
}

function main(params) {
  return new Promise(function (resolve, reject) {
    const VisualRecognitionV3 = require('watson-developer-cloud/visual-recognition/v3');
    
    var opts = {
      "version_date": "2016-05-20",
      "url" : params.url || "https://gateway-a.watsonplatform.net/visual-recognition/api",
      'use_unauthenticated': isTrue(params.use_unauthenticated)
    }
    
    if (params.api_key)
      opts.api_key = params.api_key;
    
    var visual_recognition = new VisualRecognitionV3(opts);

    visual_recognition.classify({"url": params.imageurl}, function(err, res) {
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
