function main(params) {
  return new Promise(function (resolve, reject) {
    var res = {};

    const VisualRecognitionV3 =
      require('watson-developer-cloud/visual-recognition/v3');

    const visual_recognition = new VisualRecognitionV3({
      api_key: params.api_key,
      version_date: '2016-05-20'
    });

    visual_recognition.classify({'url': params.url}, function(err, res) {
      if (err)
        reject(err);
      else
        resolve(res);
    });
  });
}

const defaultParameters = {
  'api_key': 'adea4e1a51525d502c2502c2114610b959cf9dbe',
  'url': 'https://raw.githubusercontent.com/watson-developer-cloud/doc-tutorial-downloads/master/visual-recognition/fruitbowl.jpg'
}

if (require.main === module)
  main(defaultParameters)
    .then((results) => console.log(JSON.stringify(results, null, 2)))
    .catch((error) => console.log(error.message));
