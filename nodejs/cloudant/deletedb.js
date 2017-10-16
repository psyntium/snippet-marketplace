var testparams = {
    "dbname": "person_MYNAME", //Replace MYNAME with your name
    "username": "",
    "password": "",
    "proxy": "http://cloudant-proxy.mybluemix.net/"
};
const request = require("request");
const Cloudant = require('cloudant');

function main(params) {
    var dbConfig = {
        account : params.username || "", 
        password : params.password || "",
        dbname : params.dbname || ""
    };
    var cloudant = Cloudant(params.proxy || dbConfig);

    return new Promise(function(resolve, reject) {
        cloudant.db.destroy(dbConfig.dbname, function(err, data) {
            if (err) 
                return reject({"err": err.reason});

            return resolve( {"result":"Database deleted"});
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