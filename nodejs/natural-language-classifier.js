function main(params) {
  return new Promise(function (resolve, reject) {
    var res = {};

    const NaturalLanguageClassifierV1 =
      require('watson-developer-cloud/natural-language-classifier/v1');

    var url = params.url || 'https://gateway.watsonplatform.net/natural-language-classifier/api' ;
    var use_unauthenticated =  params.use_unauthenticated || false ;

    const natural_language_classifier = new NaturalLanguageClassifierV1({
      'username': params.username,
      'password': params.password,
      'version': 'v1',
      'url' : url,
      'use_unauthenticated': use_unauthenticated
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
  'classifier_id'  : '359f41x201-nlc-180573',
  'username'       : '',
  'password'       : '',
  'url' : 'https://sandbox-watson-proxy.mybluemix.net/natural-language-classifier/api',
  'use_unauthenticated' : true  
}

if (require.main === module)
  main(defaultParameters)
    .then((results) => console.log(JSON.stringify(results, null, 2)))
    .catch((error) => console.log(error.message));
