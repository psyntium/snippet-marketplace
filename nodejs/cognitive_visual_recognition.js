var parameters = {
    "url": "https://www.whitehouse.gov/sites/whitehouse.gov/files/images/first-family/44_barack_obama%5B1%5D.jpg",
    "api_key": ""
}

function main(params) {
   
    return new Promise(function (resolve, reject) {
        var newParams = {};
        for (var i in parameters) {
            newParams[i] = params[i]
        }
        faces(newParams, function (result) {
            return resolve(result);
        });
    });
}

function faces(params, cb) {
    const watson = require('watson-developer-cloud');

    var visual_recognition = watson.visual_recognition({
                api_key: params.api_key,
                version: 'v3',
                version_date: '2016-05-19'
            });

    visual_recognition.detectFaces(params, function (err, response) {
        if (err)
            return cb({ err });

        return cb(response);
    });
}

if (require.main === module) {
    faces(parameters, function (result) {
        console.log(JSON.stringify(result, null, 2));
    });
}

exports.main = main;