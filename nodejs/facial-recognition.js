function main(params) {
  return new Promise(function (resolve, reject) {
    var res = {};

    const VisualRecognitionV3 =
      require('watson-developer-cloud/visual-recognition/v3');

    const visual_recognition = new VisualRecognitionV3({
      api_key: params.api_key,
      version_date: '2016-05-20'
    });

    visual_recognition.detectFaces({'url': params.url}, function(err, res) {
      if (err)
        reject(err);
      else
        resolve(res);
    });
  });
}

const defaultParameters = {
  'api_key': 'cf8ff9af4fd5323e190b6df6b730ab4919464c73',
  'url': 'https://www.whitehouse.gov/sites/whitehouse.gov/files/images/' +
         'first-family/44_barack_obama%5B1%5D.jpg'
}

if (require.main === module)
  main(defaultParameters)
    .then((results) => console.log(JSON.stringify(results, null, 2)))
    .catch((error) => console.log(error.message));
