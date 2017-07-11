function main(params) {
  return new Promise(function (resolve, reject) {
    var res = {};

    const ToneAnalyzerV3 =
      require('watson-developer-cloud/tone-analyzer/v3');

    const tone_analyzer = new ToneAnalyzerV3({
      username: params.username,
      password: params.password,
      version_date: '2016-05-20'
    });

    tone_analyzer.tone({'text': params.textToAnalyze}, function(err, res) {
      if (err)
        reject(err);
      else
        resolve(res);
    });
  });
}

const defaultParameters = {
  'textToAnalyze': 'I know the times are difficult! Our sales have been \
      disappointing for the past three quarters for our data analytics \
      product suite. We have a competitive data analytics product \
      suite in the industry. But we need to do our job selling it! \
      We need to acknowledge and fix our sales challenges. \
      We can\'t blame the economy for our lack of execution! \
      We are missing critical sales opportunities. \
      Our product is in no way inferior to the competitor products. \
      Our clients are hungry for analytical tools to improve their \
      business outcomes. Economy has nothing to do with it.',
  'username':      '470d0294-a580-4072-8b4e-beb063ee971a',
  'password':      '40BK1H3VUnUL'
}

if (require.main === module)
  main(defaultParameters)
    .then((results) => console.log(JSON.stringify(results, null, 2)))
    .catch((error) => console.log(error.message));
