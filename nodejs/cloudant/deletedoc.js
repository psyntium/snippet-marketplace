var parameters = {
    "dbname": "person",
    "docid": "",
    "username": "",
    "password": ""
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
	var id = params.docid || "";

    if(id != "") {
        db.get(id, function(err, data) {
			if (err) {
				return cb({"err": err.reason});
			}

			db.destroy(id, data._rev, function(err, data) {
				if (err) 
					return cb({"err": err.reason});
	
				return cb({"result": data});
			});
		});
    }
    else {
        return cb({"err":"Please specify an id or _id to read"});
    }
}

if (require.main === module) {
    submain(parameters, function (result) {
        console.log(JSON.stringify(result, null, 2));
    });
}

exports.main = main;