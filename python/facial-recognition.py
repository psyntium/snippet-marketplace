# pip watson-developer-cloud
import json
from watson_developer_cloud import VisualRecognitionV3

testparams = {
    "image_url": "https://www.whitehouse.gov/sites/whitehouse.gov/files/images/first-family/44_barack_obama%5B1%5D.jpg",
    "api_key": "SANDBOX_VR_APIKEY",
    "url": "SANDBOX_VR_URL",
}

def main(params):
    visual_recognition = VisualRecognitionV3('2016-05-20', api_key=params["api_key"], url=params["url"])
    return visual_recognition.detect_faces(images_url=params["image_url"])

if vars().get("__name__", "") == "__main__":
    print(json.dumps(main(testparams)))
