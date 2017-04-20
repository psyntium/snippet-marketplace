var parameters = {
	"username" : "........",  // SET YOUR USERNAME
	"password" : ".......", // SET YOUR PASSWORD
	"workspace_id" : "....." // SET YOUR WORKSPACE
}


function main(params) {

	var Conversation = require('watson-developer-cloud/conversation/v1');

	var conversation = new Conversation({  
	  username: params.username, 
	  password: params.password, 
	  version: 'v1',
	  version_date: '2016-07-01'
	});

	conversation.message({
		input: { text: "" },
		workspace_id: params.workspace_id
	}, (err, response) => {
		if (err) {
			console.log('error:', err);
			if (typeof callback !== 'undefined' && typeof callback=="function") return callback(err);
		} else {
			console.log(JSON.stringify(response, null, 2));
			if (typeof callback !== 'undefined' && typeof callback=="function") return callback(response);

			conversation.message({
				input: { text: 'Hi Watson?' },  // SET YOUR CONVERSATION
				context : response.context,
				workspace_id: params.workspace_id
			}, (err, response) => {
				if (err) {
					console.log('error:', err);
					if (typeof callback !== 'undefined' && typeof callback=="function") return callback(err);
				} else {
					console.log(JSON.stringify(response, null, 2));
					if (typeof callback !== 'undefined' && typeof callback=="function") return callback(response);

					conversation.message({
						input: { text: 'Turn on the wiper' },  // SET YOUR CONVERSATION
						context : response.context,
						workspace_id: params.workspace_id
					}, (err, response) => {
						if (err) {
							console.log('error:', err);
							if (typeof callback !== 'undefined' && typeof callback=="function") return callback(err);
						} else {
							console.log(JSON.stringify(response, null, 2));
							if (typeof callback !== 'undefined' && typeof callback=="function") return callback(response);	
						}
					});
				}
			});
		}
	});


}

if (require.main === module) console.log(JSON.stringify(main(parameters),null,2));

exports.main = main;