<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chat</title>

<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap"
	rel="stylesheet">
</head>

<style>
.message-area {
	width: 400px;
	height: 500px;
	border: 1px solid #B1B2FF;
	overflow-y: auto;
}

.input-area {
	width: 400px;
	border: 1px solid #B1B2FF;
	margin: 0;
	padding: 0;
}

.msg-box {
	max-width: 250px;
	word-wrap: break-word;
	overflow: auto;
	border: 1px dotted #B1B2FF;
	border-radius: 5px;
	margin: 5px;
	padding: 5px;
	display: inline-block;
}
</style>

<script>
	function updateScroll() {
		var element = document.getElementsByClassName("message-area")[0];
		element.scrollTop = element.scrollHeight;
	}

	$(function() {

		let ws = new WebSocket("ws://192.168.150.39/chat"); // WebSocket 인스턴스 생성

		ws.onmessage = function(e) {
			let text = e.data;
			let outer = $("<div>");
			let line = $("<div>");
			line.addClass("msg-box")
			line.append(text);

			outer.append(line);

			$(".message-area").append(outer);
			updateScroll();
		}

		$(".input-area").on("keydown", function(e) {
			if (e.keyCode == 13) {
				let text = $(".input-area").text();
				$(".input-area").text("");
				ws.send(text);
				return false;
			}
		});
	})
</script>

<body>

	<div style="margin: 0 auto">
		<div class="message-area"></div>
		<div class="input-area" contenteditable="true"></div>
	</div>

</body>

</html>