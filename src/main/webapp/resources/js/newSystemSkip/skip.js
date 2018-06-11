$(function autoSkip() {
	var url = $("#newPath").val();
	delayURL(url);
})

function delayURL(url) {
	var delay = $("#time").text();
	if (delay > 0) {
		delay--;
		$("#time").text(delay);
	} else {
		window.top.location.href = url;
	}
	t = setTimeout("delayURL('" + url + "')", 1000);
}
