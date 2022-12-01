<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- jstl 포맷라이브러리 추가 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Board</title>

<script src="https://code.jquery.com/jquery-3.6.1.js"></script>

<script
	src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
	
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.min.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />


<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
	crossorigin="anonymous"></script>



<style>
@import
	url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap')
	;

:root { -
	-animate-duration: 2s; -
	-animate-delay: 0.9s;
}
</style>
</head>

<body>

	<div class="container" style="text-algn: center; margin-top: 50px;">

		<table class="table hover" id="myTable" style="width: 100%">

			<thead>
				<tr>
					<th>no.</th>
					<th>title</th>
					<th>writer</th>
					<th>view</th>
					<th>date</th>
				</tr>
			</thead>

			<tbody>
				<!-- choose 랑 when 사이에 아무것도 넣지않기. 넣으면 에러남. -->
				<c:choose>
					<c:when test="${not empty list}">
						<c:forEach var="i" items="${list}">

							<tr>
								<td>${i.seq}</td>
								<td id="title"><a href="/board/detail?seq=${i.seq}">${i.title}
										<span class="gray">[${i.commentNum}]</span>
								</a> <!-- 날짜 계산 --> <jsp:useBean id="now" class="java.util.Date" />
									<fmt:parseNumber value="${now.time / (1000*60*60*24)}"
										integerOnly="true" var="nowfmtTime" scope="request" /> <fmt:parseNumber
										value="${i.write_date.time / (1000*60*60*24)}"
										integerOnly="true" var="dbDtParse" scope="request" /> <c:if
										test="${(dbDtParse - nowfmtTime)==0}">
										<span
											class="badge bg-danger animate__animated animate__flash animate__infinite">NEW</span>
									</c:if></td>
								<td>${i.writer}</td>
								<td>${i.view_count}</td>
								<td>${i.formedDate}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div>
							<h2>출력할 내용이 없습니다.</h2>
						</div>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>

		<div class="align">
			<button class="btn btn-secondary" id="toWrite">Write</button>
		</div>


	</div>

	<script>
		$(document).ready(function() {
			$('#myTable').DataTable(
					{ order: [[0, 'desc']],lengthMenu: [
			            [10, 25, 50, -1],
			            [10, 25, 50, 'All'],
			        ],}	
			);
		});

		$("#toWrite").on("click", function() {
			location.href = "/board/toWrite";
		})
	</script>
</body>

</html>