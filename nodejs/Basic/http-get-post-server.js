var testparams = {};
var library = [];

function main(params) {
  return new Promise(function (resolve, reject) {
    if(library.length <= 0)
        library = [
            {"name": "Java for dummy", "serialno": "1-abc123", "author": "John Doe"},
            {"name": "Node JS Beginner Guide", "serialno": "2-xyz456", "author": "Foo Bar"},
            {"name": "Learn Python in Minutes", "serialno": "3-111999", "author": "Cobra King"}
        ];

    if (params.__ow_method == "post") {
        if(!params.name || !params.serialno || !params.author)
            return reject({"err": "Please provide valid name, serialno, and author"});
        else {
            var libItem = {
                name: params.name, 
                serialno: params.serialno, 
                author: params.author
            };
            library.push(libItem);
        }
    }
    return resolve({"payload": library});
  })
}

if (require.main === module)
  main(testparams)
    .then((results) => console.log(results))
    .catch((error) => console.log(error));

exports.main = main;
