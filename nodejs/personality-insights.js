const testparams = {
  "textToAnalyze":
      "Call me Ishmael. Some years ago-never mind how long precisely-having \
      little or no money in my purse, and nothing particular to interest \
      me on shore, I thought I would sail about a little and see the \
      watery part of the world. It is a way I have of driving off the \
      spleen and regulating the circulation. Whenever I find myself \
      growing grim about the mouth; whenever it is a damp, drizzly \
      November in my soul; whenever I find myself involuntarily pausing \
      before coffin warehouses, and bringing up the rear of every \
      funeral I meet; and especially whenever my hypos get such an upper \
      hand of me, that it requires a strong moral principle to prevent \
      me from deliberately stepping into the street, and methodically \
      knocking people\"s hats off-then, I account it high time to get to \
      sea as soon as I can. This is my substitute for pistol and ball. \
      With a philosophical flourish Cato throws himself upon his sword; \
      I quietly take to the ship. There is nothing surprising in this. \
      If they but knew it, almost all men in their degree, some time or \
      other, cherish very nearly the same feelings towards the ocean \
      with me. There now is your insular city of the Manhattoes, belted \
      round by wharves as Indian isles by coral reefs-commerce surrounds \
      it with her surf. Right and left, the streets take you waterward.",
  "username": "",
  "password": "",
  "url" : "https://sandbox-watson-proxy.mybluemix.net/personality-insights/api",
  "use_unauthenticated" : true
}

function main(params) {
  return new Promise(function (resolve, reject) {
    const PersonalityInsightsV3 = require("watson-developer-cloud/personality-insights/v3");

    var personality_insights = new PersonalityInsightsV3({
      "username": params.username || "",
      "password": params.password || "",
      "version_date": "2016-05-20",
      "url" : params.url || "https://gateway.watsonplatform.net/personality-insights/api",
      "use_unauthenticated": isTrue(params.use_unauthenticated)
    });

    personality_insights.profile({
      "text": params.textToAnalyze
    }, function(err, res) {
      if (err)
        reject(err);
      else
        resolve(res);
    });
  });
}

function isTrue(value) {
  if (value == "true" || value == true)
    return true;
  return false;
}

if (require.main === module)
  main(testparams)
    .then((results) => console.log(JSON.stringify(results, null, 2)))
    .catch((error) => console.log(error.message));

exports.main = main;
