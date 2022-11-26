<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Output Form</title>
</head>
<body>
	<form action="/message/delete">
		<table border=1 align=center>
			<tr>
				<th>Output
			</tr>
			<tr>	
				<td>
					<input type=text name="seq" placeholder="Delete to seq">
				</td>
			</tr>
			<tr>
				<td align=center><button>삭제</button>
					<a href="/"><button type=button>Back</button></a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>