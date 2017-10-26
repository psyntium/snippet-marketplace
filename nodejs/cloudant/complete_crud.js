var testparams = {
    "dbname": "person_MYNAME", //Replace MYNAME with your name to make it unique
    "doc_name": "Joe Doe",
    "doc_email": "joedoe@email.com",
    "doc_comment": "This is just a test",
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
    var db = cloudant.db.use(dbConfig.dbname);

    var result = '';
    return new Promise(function(resolve, reject) {

        result += 'Creating Cloudant database \''+dbConfig.dbname+'\'\n';
        cloudant.db.create(dbConfig.dbname, function(err, data) {
            if (err) return reject({result, "err": err.reason});
            result += 'Result: '+JSON.stringify(data)+'\n\n';

            result += 'Creating document \n';
            var doc = {
                "name"	: params.doc_name,
                "email"	: params.doc_email,
                "comment"	: params.doc_comment
            }
            db.insert(doc, function(err, data) {
                if (err) return reject({result, "err":err.reason});
                result += 'Result: '+JSON.stringify(data)+'\n\n';
                var doc_id = data.id;

                result += 'Listing all docs \n';
                db.list(function(err, data) {
                    if (err) return reject({result, "err":err.reason});
                    result += 'Result: '+JSON.stringify(data)+'\n\n';

                    result += 'Reading document content \n';
                    db.get(doc_id, function(err, data) {
                        if (err) return reject ({result, "err":err.reason});
                        result += 'Result: '+JSON.stringify(data)+'\n\n';

                        result += 'Updating document \n';
                        data.name = 'updated-'+data.name;
                        data.comment = 'updated-'+data.comment;
                        
                        db.insert(data, function(err, data) {
                            if (err) return reject ({result, "err": err.reason});
                            result += 'Result: '+JSON.stringify(data)+'\n\n';

                            result += '(After update) Reading document content \n';
                            db.get(doc_id, function(err, data) {
                                if (err) return reject ({result, "err":err.reason});
                                result += 'Result: '+JSON.stringify(data)+'\n\n';
                                
                                result += 'Deleting doc \n';
                                db.destroy(data._id, data._rev, function(err, data) {
                                    if (err) return reject({result, "err": err.reason});
                                    result += 'Result: '+JSON.stringify(data)+'\n\n';
                        
                                    result += 'Deleting database \n';
                                    cloudant.db.destroy(dbConfig.dbname, function(err, data) {
                                        if (err) return reject({result, "err": err.reason});
                                        result += 'Result: '+JSON.stringify(data)+'\n\n';

                                        return resolve({result});
                                    });  
                                });
                            })
                        });
                    });
                });
            });
        });  
    })
}

if (require.main === module) {
    main(testparams).then(function(result) {
        console.log(result.result);
    })
    .catch(function (err) {
        console.error(err.result+'\n Error: '+err.err);
    });
}

exports.main = main;