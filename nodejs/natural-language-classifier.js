function main(params) {
  return new Promise(function (resolve, reject) {
    var res = {};

    const NaturalLanguageClassifierV1 =
      require('watson-developer-cloud/natural-language-classifier/v1');

    const natural_language_classifier = new NaturalLanguageClassifierV1({
      username: params.username,
      password: params.password,
      version: 'v1'
    });

    natural_language_classifier.
      classify({'text': params.textToClassify,
                'classifier_id': params.classifier_id},
               function(err, res) {
      if (err)
        reject(err);
      else
        resolve(res);
    });
  });
}

const defaultParameters = {
  'textToClassify' : 'Will it rain tomorrow?',
  'classifier_id'  : 'ff18c7x157-nlc-2810',
  'username'       : '1e2a85d3-f9f3-4d77-9b44-d0d56c93a028',
  'password'       : 'EjhIkxAUWWVQ'
}

if (require.main === module)
  main(defaultParameters)
    .then((results) => console.log(JSON.stringify(results, null, 2)))
    .catch((error) => console.log(error.message));
