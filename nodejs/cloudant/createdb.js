var testparams = {
    "dbname": "person",
    "username": "",
    "password": ""
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

    return new Promise(function(resolve, reject) {
        cloudant.db.create(dbConfig.dbname, function(err, data) {
            if (err) 
                return reject({"err": err.reason});

            return resolve( {"result":"Success created database"});
        });  
    })
}

if (require.main === module) {
    main(testparams).then(function(result) {
        console.log(JSON.stringify(result));
    })
    .catch(function (err) {
        console.error(err);
    });
}

exports.main = main;