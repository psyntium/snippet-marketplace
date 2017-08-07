var testparams = {
  "name": "John Doe",
  "email": "jdoe@xyz.com",
  "zip": "27517"
}

function main(params) {
  console.log(params); 
  
  var out = {};
  for (var i in testparams) {
      out[i] = params[i];
  }
  return { out };
}

if (require.main === module)
  console.log(JSON.stringify(main(testparams), null, 2));

exports.main = main;
