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
	
	<hr>
	
	<a href="/message/selectBySeq?seq=7">7번글 검색 ( Select By Value )</a>
	
	<hr>
	
	<fieldset>
		<legend>Search</legend>
		<form action="/message/selectByCon">
			<select name="condition">
				<option value="writer">Writer</option>
				<option value="message">Message</option>
			</select>
			<input type="text" name="keyword" placeholder="검색 할 단어 입력">
			<button>검색</button>
		</form>
	</fieldset>	
	
	<hr>
	
	<fieldset>
		<legend>Search2</legend>
		<form action="/message/selectByMultiCon">
			<input type="text" name="writer" placeholder="검색 할 작성자 입력">
			<input type="text" name="message" placeholder="검색 할 내용 입력">
			<button>검색</button>
		</form>
	</fieldset>
	
</body>
</html>