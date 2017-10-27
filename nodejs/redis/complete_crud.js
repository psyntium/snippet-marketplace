var testparams = {
    "hostname": "", 
    "password": "",
    "port": "",
    "key1": 'keyA',
    "value1": "valueA",
    "key2": 'keyB',
    "value2": "valueB"
};

var redis = require('redis');

function main(params) {
    var redisconn = {
        hostname : params.hostname || "SANDBOX_REDIS_HOST", 
        password : params.password || "SANDBOX_REDIS_PASSWORD",
        port : params.port || "SANDBOX_REDIS_PORT"
    };

    var client = redis.createClient({host: redisconn.hostname, password: redisconn.password, port:redisconn.port});
    var result = '';
    return new Promise(function(resolve, reject) {
        result += 'Setting multiple keys\n';
        client.mset([params.key1, params.value1, params.key2, params.value2], function(err, data) {
			if (err) return reject({result, err});
            result += 'Result: '+data+'\n\n';

            result += 'Getting value of key \''+params.key1+'\'\n';
            client.get(params.key3, function(err, data) {
                if (err) return reject({result, err});
                result += 'Result: '+data+'\n\n';

                result += 'Listing all key(s) \n';
                client.keys('*', function(err, data) {
                    if (err) return reject({result, err});
                    result += 'Result: Size '+data.length+' ('+data+')\n\n';

                    result += 'Deleting key \''+params.key1+'\'\n';
                    client.del(params.key1, function(err, data) {
                        if (err) return reject({result, err});                    
                        result += 'Result: '+data+'\n\n';

                        result += '(After Delete) Listing all key(s) \n';
                        client.keys('*', function(err, data) {
                            if (err) return reject({result, err});
                            result += 'Result: Size '+data.length+' ('+data+')\n\n';

                            result += 'Flushing Database \n';
                            client.flushdb(function(err, data) {
                                if (err) return reject({result, err});
                                result += 'Result: '+data+'\n\n'; 
                        
                                result += '(After Flush DB) Listing all key(s) \n';
                                client.keys('*', function(err, data) {
                                    if (err) return reject({result, err});
                                    result += 'Result: Size '+data.length+' ('+data+')\n\n';

                                    client.end(true);
                                    return resolve({result});
                                })
                            });
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