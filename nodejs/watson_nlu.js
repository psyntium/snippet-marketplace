var parameters = {
    "username": "........", // SET YOUR USERNAME
    "password": ".......", // SET YOUR PASSWORD
}
var NaturalLanguageUnderstandingV1 = require('watson-developer-cloud/natural-language-understanding/v1.js');
// ISSUE WITH LIB

function main(params) {

    var nlu = new NaturalLanguageUnderstandingV1({
        username: params.username,
        password: params.password,
        version_date: NaturalLanguageUnderstandingV1.VERSION_DATE_2016_01_23
    });

    nlu.analyze({
        'url': 'www.cnn.com', // Buffer or String
        'features': {
            'concepts': {},
            'keywords': {},
        }
    }, function(err, response) {
        if (err)
            console.log('error:', err);
        else
            console.log(JSON.stringify(response, null, 2));
    });

}

if (require.main === module) console.log(JSON.stringify(main(parameters), null, 2));

exports.main = main;