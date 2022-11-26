<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>

<!-- 	<form action="/hello">
		<button>요청</button>
	</form>
	
	<hr>
	
	<form action="/insert">
		<input type=text name="writer"><br>
		<input type=text name="message"><br>
		<button>제출</button>
	</form>
	
	<form action="/delete">
		<input type=text name="seq"><br>
		<button>삭제</button>
	</form>
-->	
	
	<table border=1 align=center>
		<tr>
			<th colspan=2>Index
		</tr>
		<tr>
			<td><a href="/message/toInput">toInput</a></td>
			<td><a href="/message/getList">toOutput</a></td>
			<!-- Spring 에서는 jsp로 갈지, controller 로 갈지 정할 수 없음.  -->
		</tr>
	</table>
	
	
	
	
</body>
</html>