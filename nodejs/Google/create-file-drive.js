var request = require('request');
var google = require('googleapis');
var googleAuth = require('google-auth-library');

var testparams = {
  "drive_oauth_client_secret": "",
  "drive_oauth_client_id": "",
  "drive_oauth_redirect_url": ["urn:ietf:wg:oauth:2.0:oob"],
  "refresh_token": ""
};

function main(params) {

  return new Promise(function(resolve, reject){
      var content = {
        "client_secret": params.drive_oauth_client_secret || "GDRIVE_CLIENT_SECRET",
        "client_id": params.drive_oauth_client_id || "GDRIVE_CLIENT_ID",
        "redirect_uris": params.drive_oauth_redirect_url
      }
      authorize(content, uploadFile);
      /**
       * Create an OAuth2 client with the given credentials, and then execute the
       * given callback function.
       *
       * @param {Object} credentials The authorization client credentials.
       * @param {function} callback The callback to call with the authorized client.
       */
      function authorize(credentials, callback) {
        var clientSecret = credentials.client_secret;
        var clientId = credentials.client_id;
        var redirectUrl = credentials.redirect_uris[0];
        var auth = new googleAuth();
        var oauth2Client = new auth.OAuth2(clientId, clientSecret, redirectUrl);

        oauth2Client.credentials = {"refresh_token": params.refresh_token || "GDRIVE_REFRESH_TOKEN"};
        callback(oauth2Client);
      }

      function uploadFile(auth) {
        var service = google.drive('v3');
        service.files.create({
          auth: auth,
          resource: {
            name: 'Test2',
            mimeType: 'text/plain'
          },
          media: {
            mimeType: 'text/plain',
            body: 'Uploaded another text from Sandbox'
          }
        }, function(err, response) {
          if (err)
            return reject({err});

          return resolve({response});
        });
      }
  })
}

if (require.main === module)
  main(testparams)
    .then((results) => console.log(results))
    .catch((error) => console.log(error));

exports.main = main;