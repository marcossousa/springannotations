function showMsgInfo(flag) {
	var divMsgInfo = document.getElementById("MSG_AGUARDE");
	if (flag) {
		divMsgInfo.style.visibility = "";
	} else {
		divMsgInfo.style.visibility = "hidden";
	}
}