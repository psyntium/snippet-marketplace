var parameters = {
  "username" : "..",
  "password" : "...",
  "text" : "Is it sunny?",
  "classifier_id" : "..."
}

function main(params) {
  var NaturalLanguageClassifierV1 = require('watson-developer-cloud/natural-language-classifier/v1');
  var fs = require('fs');
  
  var natural_language_classifier = new NaturalLanguageClassifierV1({
    username : params.username,
    password : params.password
  });

  natural_language_classifier.classify({
    text: params.text,
    classifier_id: params.classifier_id
  }, function(err, response) {
        if (err) {
          console.log('error:', err);
          if (typeof callback !== 'undefined' && typeof callback=="function") return callback(err);
        } else {
          console.log(JSON.stringify(response, null, 2));
          if (typeof callback !== 'undefined' && typeof callback=="function") return callback(response);
        }
  });
}

if (require.main === module) console.log(JSON.stringify(main(parameters),null,2));

exports.main = main;