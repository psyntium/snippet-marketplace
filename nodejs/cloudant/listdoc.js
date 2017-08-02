var parameters = {
    "dbname": "person",
    "username": "9c30f6dc-3ead-4ccc-8928-4eef9dabdfe6-bluemix",
    "password": "88dac7d3fb8877e7a6959a2a6cd873389a70df30e3213c4c55b5f28dd37dd617"
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
	
	db.list(function(err, data) {
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