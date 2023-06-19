from flask import Flask
from markupsafe import escape
from simpleicons.all import icons
import base64

app = Flask(__name__)


@app.route("/<name>/<color>")
def hello_world(name, color):
    icon = icons.get(name)
    iconToReturn = icon.__dict__["svg"].replace(
        "<svg", "<svg fill=\"#" + color + "\"")
    return base64.b64encode(iconToReturn.encode('utf-8'))
