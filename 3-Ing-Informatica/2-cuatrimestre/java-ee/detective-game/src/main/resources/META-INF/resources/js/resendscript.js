function resendRequest() {
    document.getElementById("chatForm:reloadbut").click();
}

function handleAjax(data) {
    if (data.status === "success") {
        setTimeout(resendRequest, 1000);
    }
}
