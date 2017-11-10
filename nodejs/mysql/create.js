var testparams = {
    "hostname": "", 
    "username": "",
    "password": "",
    "dbname": "",
    "tablename": "student_username",
    "student_first_name": "John",
    "student_last_name": "Doe",
    "student_id": "12345"
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

    var query = "INSERT INTO " + params.tablename + " SET ?";
    var values = {
		"firstname"  : params.student_first_name,
		"lastname"   : params.student_last_name,
		"student_id" : params.student_id
	}

    return new Promise(function(resolve, reject) {
        pool.query(query, values, function (err, data, fields) {
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