function startRequest() {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "/async-servlet-chat-1.0-SNAPSHOT/asyncchat", true);
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            const msgs = xmlHttp.responseXML.getElementsByTagName("line");
            if (msgs.length > 0) {
                document.getElementById("message").innerText = msgs[0].textContent;
            }
            startRequest();
        }
    };
    xmlHttp.send(null);
}

function sendInput(value) {
    console.log("sending data")
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/async-servlet-chat-1.0-SNAPSHOT/send", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("text=" + encodeURIComponent(value));
}
