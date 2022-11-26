<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Input Form</title>
</head>
<body>

	<form action="/message/insert">
		<table border=1 align=center>
			<tr>
				<th>Input
			</tr>
			<tr>
				<td>
					<input type=text name="writer" placeholder="Input writer">
				</td>
			</tr>
			<tr>	
				<td>
					<input type=text name="message" placeholder="Input message">
				</td>
			</tr>
			<tr>
				<td align=center><button>Submit</button>
					<a herf="/"><button type=button>Back</button></a>
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>