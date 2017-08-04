var testparams = {
    "dbname": "person",
    "docid": "",
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
    var db = cloudant.db.use(dbConfig.dbname);
    var id = params.docid || "";

    return new Promise(function (resolve, reject) {
        if(id != "") {
            db.get(id, function(err, data) {
                if (err) {
                    return reject({"err": err.reason});
                }

                db.destroy(id, data._rev, function(err, data) {
                    if (err) 
                        return reject({"err": err.reason});
        
                    return resolve({"result": data});
                });
            });
        }
        else {
            return reject ({"err":"Please specify an id or _id to read"});
        }
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