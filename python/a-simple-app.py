import json

parameters = {
    "name":"John Doe",
    "email": "jdoe@xyz.com",
    "zip": "27517"
}

def main(params):
    out = {}
    for key in params:
        out[key] = params[key]        
    return {"out":out}

if vars().get("__name__","") == "__main__":
    print(json.dumps(main(parameters)))
