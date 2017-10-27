var testparams = {
    "hostname": "", 
    "password": "",
    "port": ""
};

var redis = require('redis');

function main(params) {
    var redisconn = {
        hostname : params.hostname || "SANDBOX_REDIS_HOST", 
        password : params.password || "SANDBOX_REDIS_PASSWORD",
        port : params.port || "SANDBOX_REDIS_PORT"
    };

    var client = redis.createClient({host: redisconn.hostname, password: redisconn.password, port:redisconn.port});

    return new Promise(function(resolve, reject) {
        client.flushdb(function(err, data) {
            client.end(true);
			if (err) {
				return reject({err});
			}
	
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