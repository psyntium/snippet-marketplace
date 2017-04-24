var parameters = {
  "password": "", // SET YOUR PASSWORD
  "username": "", // SET YOUR USERNAME
  "text" : "Call me Ishmael. Some years ago-never mind how long "
};


function mainnew(params) {
  var PersonalityInsightsV3 = require('watson-developer-cloud/personality-insights/v3');
  var personality_insights = new PersonalityInsightsV3({
    username: params.username, 
    password: params.password,
    use_unauthenticated: true, 
    version_date: '2016-10-19'   
  });
  
personality_insights.profile({
  text: params.text,
  consumption_preferences: true,
  },
  function (err, response) {
    if (err)
      console.log('error:', err);
    else
      console.log(JSON.stringify(response, null, 2));
});
}


if (require.main === module) console.log(JSON.stringify(main(parameters),null,2));

exports.main = main;