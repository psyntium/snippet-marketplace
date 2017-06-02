//1. goto - https://jazzbot.mybluemix.net/
//2. issue the command below :   Replace < > with your space
//    bot register bot https://openwhisk.ng.bluemix.net/api/v1/web/devworkssandbox_dev/<yourspace> 
//3. deploy the web app to a specific name
//   in this case we will call the app name ***hello***
//4. issue the command below:
//    bot hello john doe
//5. repeat steps 3 to 4 many times

function main(params) {
    return {body:'How are you doing ' + JSON.stringify(params.text)};
}