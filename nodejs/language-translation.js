const testparams = {
  "textToTranslate": "That that is not confusing is amazing.",
  "username": "",
  "password": "",
  "url": "https://sandbox-watson-proxy.mybluemix.net/language-translator/api",
  "use_unauthenticated" : true
}

function main(params) {
  const LanguageTranslationV2 = require('watson-developer-cloud/language-translation/v2');
  
  var opts = {
    "url": params.url || "https://gateway.watsonplatform.net/language-translator/api",
    "use_unauthenticated": isTrue(params.use_unauthenticated)
  }
  
  if (params.username && params.password) {
    opts.username = params.username;
    opts.password = params.password;
  }
  
  var language_translator = new LanguageTranslationV2(opts);

  return new Promise(function (resolve, reject) {
    englishToFrench(params.textToTranslate, language_translator)
      .then((results) => frenchToSpanish(results.translations[0].translation, language_translator))
      .then((results) => spanishToEnglish(results.translations[0].translation, language_translator))
      .then((results) => resolve(results))
      .catch((err) => reject(err));
  });
}

function isTrue(value) {
  if (value == "true" || value == true)
    return true;
  return false;
}

function englishToFrench(textToTranslate, language_translator) {
  return new Promise(function (resolve, reject) {
    language_translator.translate({
        "text": textToTranslate,
        "source": "en", 
        "target": "fr"
      }, function(err, res) {
      if (err)
        reject(err);
      else
        resolve(res);
    });
  });
}

function frenchToSpanish(textToTranslate, language_translator) {
  return new Promise(function (resolve, reject) {
    language_translator.translate({
        "text": textToTranslate,
        "source": "fr", 
        "target": "es"
      }, function(err, res) {
      if (err)
        reject(err);
      else
        resolve(res);
    });
  });
}

function spanishToEnglish(textToTranslate, language_translator) {
  return new Promise(function (resolve, reject) {
    language_translator.translate({
        "text": textToTranslate,
        "source": "es", 
        "target": "en"
      }, function(err, res) {
      if (err)
        reject(err);
      else
        resolve(res);
    });
  });
}

if (require.main === module)
  main(testparams)
    .then((results) => console.log(results.translations[0].translation))
    .catch((error) => console.log(error.message));

exports.main = main;
