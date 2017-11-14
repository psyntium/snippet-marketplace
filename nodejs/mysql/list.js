var testparams = {
    "hostname": "", 
    "username": "",
    "password": "",
    "dbname": "",
    "tablename": "student_username"
};

var mysql = require('mysql');

function main(params) {

    var dbConfig = {
            host     : params.hostname || "SANDBOX_MYSQL_HOST",
            user     : params.username || "SANDBOX_MYSQL_USER",
            password : params.password || "SANDBOX_MYSQL_PASSWORD",
            database : params.dbname || "SANDBOX_MYSQL_DATABASE",
            connectionLimit : 5,
    };
    var pool  = mysql.createPool(dbConfig);

    var query = "SELECT * FROM " + params.tablename;

    return new Promise(function(resolve, reject) {
        pool.query(query, function (err, data, fields) {
            pool.end();
            if (err) return reject({err});
            
            return resolve({data});
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