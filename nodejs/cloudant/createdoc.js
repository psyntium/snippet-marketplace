var parameters = {
    "dbname": "person",
    "username": "",
    "password": "",
    "name": "Joe Doe",
    "email": "joedoe@email.com",
    "comment": "This is just a test"
}
const request = require("request");
const Cloudant = require('cloudant');
const templateUrl = "https://gist.githubusercontent.com/snippet-java/fd3aa1c2ab893bf8e1bcd90073ceab99/raw";

function main(params) {
    if (params.__ow_method == "get") {
        var inputs = "";
        for (i in parameters) {
            inputs += "<input name='" + i + "' placeholder='" + i + "' value='" + parameters[i] + "' style='width:100%; padding:10px'><br/>"
        }

        return new Promise(function (resolve, reject) {
            request(templateUrl, function(err, res, body) {
                return resolve({html:body.replace("{{inputs}}", inputs)});
            })
        });
    }

    return new Promise(function (resolve, reject) {
        var newParams = {};
        for (var i in parameters) {
            newParams[i] = params[i]
        }
        submain(newParams, function (result) {
            return resolve(result);
        });
    });
}

function submain(params, cb) {

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
	
	db.insert(doc, function(err, data) {
		if (err) {
			return cb({"err":err.reason});
		}

		return cb({"result":data});
	});

    
}

if (require.main === module) {
    submain(parameters, function (result) {
        console.log(JSON.stringify(result, null, 2));
    });
}

exports.main = main;