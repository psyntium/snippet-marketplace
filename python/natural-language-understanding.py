# pip watson-developer-cloud
import json
from watson_developer_cloud import NaturalLanguageUnderstandingV1
import watson_developer_cloud.natural_language_understanding.features.v1 as Features

testparams = {
    "username": "SANDBOX_NLU_USERNAME",
    "password": "SANDBOX_NLU_PASSWORD",
    "version": "2017-02-27",
    "url": "http://www.espn.com/tennis/story/_/id/18436908/australian-open-2017-tournament-news-schedule-live-scores-tv-coverage"
}

def main(params):
    natural_language_understanding = NaturalLanguageUnderstandingV1(username=params["username"],password=params["password"],version=params["version"])
    response = natural_language_understanding.analyze(url=params["url"],
        features=[
            Features.Concepts(limit=1),
            Features.Entities(limit=1),
            Features.Keywords(limit=1),
            Features.Categories(),
            Features.Emotion(),
            Features.Sentiment(),
            Features.MetaData(),
            Features.Relations(),
            Features.SemanticRoles(limit=1)
        ]
    )
    return response

if vars().get("__name__", "") == "__main__":
    print(json.dumps(main(testparams)))