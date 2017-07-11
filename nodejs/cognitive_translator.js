var parameters = {
  "username" : "", 
  "password" : "", 
  "text" : "Boy kicked a ball",
  "fromLanguage" : "en",
  "toLanguage" : "es"
}

function main(params) {
  var LanguageTranslatorV2 = require('watson-developer-cloud/language-translator/v2');
  
  var language_translator = new LanguageTranslatorV2({
    username: params.username, 
    password: params.password, 
    url : "https://sandbox-watson-proxy.mybluemix.net/language-translator/api",
    use_unauthenticated: true
  });

  language_translator.translate({
    text: params.text,
    source: params.fromLanguage,
    target: params.toLanguage
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

if (require.main === module) console.log(JSON.stringify(main(parameters), null, 2));

exports.main = main;