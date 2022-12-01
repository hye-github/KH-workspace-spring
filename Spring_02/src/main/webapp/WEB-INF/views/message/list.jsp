<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Message List</title>
</head>
<body>


	<table>
		<tr>
			<th colspan=3>Message List (${count})
		</tr>
		<tr>
			<th>Seq
			<th>Writer
			<th>Message
		</tr>

	<c:forEach var="i" items="${list}">
		<tr>
			<td>${i.seq}</td>
			<td>${i.writer}</td>
			<td>${i.message}</td>
		</tr>
	</c:forEach>	
		<tr>
			<td><a href="/"><button type=button>Back</button></a></td>
		</tr>
	</table>


</body>
</html>