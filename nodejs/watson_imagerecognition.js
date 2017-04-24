var parameters = {
  "api_key" : "", //SET YOUR API KEY
  "url" : "https://www.whitehouse.gov/sites/whitehouse.gov/files/images/first-family/44_barack_obama%5B1%5D.jpg" 
};

function main(params) {
  var VisualRecognitionV3 = require('watson-developer-cloud/visual-recognition/v3');
  var fs = require('fs');
  var http = require('http');
  
  var visual_recognition = new VisualRecognitionV3({
    api_key: params.api_key,  
    use_unauthenticated: true,        
    version_date: '2016-05-20'
  });

  visual_recognition.detectFaces(parameters, function (err, response) {
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