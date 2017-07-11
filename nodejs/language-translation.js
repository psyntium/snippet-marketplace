function main(params) {
  const LanguageTranslationV2 =
    require('watson-developer-cloud/language-translation/v2');

  const language_translator = new LanguageTranslationV2({
    username: params.username,
    password: params.password,
    url: params.url
  });

  return new Promise(function (resolve, reject) {
    englishToFrench(params.textToTranslate, language_translator)
      .then((results) => frenchToSpanish(results.translations[0].translation,
                                         language_translator))
      .then((results) => spanishToEnglish(results.translations[0].translation,
                                          language_translator))
      .then((results) => resolve(results))
      .catch((err) => reject(err));
  });
}

function englishToFrench(textToTranslate, language_translator) {
  return new Promise(function (resolve, reject) {
    language_translator.
      translate({'text': textToTranslate,
                 'source': 'en', 'target': 'fr'},
                function(err, res) {
      if (err)
        reject(err);
      else
        resolve(res);
    });
  });
}

function frenchToSpanish(textToTranslate, language_translator) {
  return new Promise(function (resolve, reject) {
    language_translator.
      translate({'text': textToTranslate,
                 'source': 'fr', 'target': 'es'},
                function(err, res) {
      if (err)
        reject(err);
      else
        resolve(res);
    });
  });
}

function spanishToEnglish(textToTranslate, language_translator) {
  return new Promise(function (resolve, reject) {
    language_translator.
      translate({'text': textToTranslate,
                 'source': 'es', 'target': 'en'},
                function(err, res) {
      if (err)
        reject(err);
      else
        resolve(res);
    });
  });
}

const defaultParameters = {
  'textToTranslate' : 'That that is not confusing is amazing.',
  'username'        : '344ba80c-fa8f-42db-b3ec-63e039b515d7',
  'password'        : 'djhlrKh2st3T',
  'url'             : 'https://gateway.watsonplatform.net/language-translator/api'
}

if (require.main === module)
  main(defaultParameters)
    .then((results) => console.log(results.translations[0].translation))
    .catch((error) => console.log(error.message));
