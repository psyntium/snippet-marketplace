var testparams = {
    "dbname": "person",
    "username": "",
    "password": "",
    "name": "Joe Doe",
    "email": "joedoe@email.com",
    "comment": "This is just a test"
};
const request = require("request");
const Cloudant = require('cloudant');

function main(params) { 
    
    var dbConfig = {
        account : params.username || "", 
        password : params.password || "",
        dbname : params.dbname || ""
    };
    var cloudant = Cloudant(dbConfig);
    var db = cloudant.db.use(dbConfig.dbname);

    var doc = {
		"name"	: params.name,
		"email"	: params.email,
		"comment"	: params.comment
	}

    return new Promise(function (resolve, reject) {
       db.insert(doc, function(err, data) {
            if (err) {
                return reject({"err":err.reason});
            }

            return resolve({"result":data});
       });
    });
}

if (require.main === module) {
     main(testparams)
     .then(function(result) {
        console.log(JSON.stringify(result));
     })
     .catch(function (err) {
        console.error(err);
     });
}

exports.main = main;