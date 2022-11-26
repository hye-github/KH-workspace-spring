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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />

<link
	href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.min.css">

<script	src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>

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

	<div class="container" style="text-algn:center">
<h5>Free Board</h5>
			<hr class="d-none d-md-block">

		<table class="cell-border compact stripe display" id="myTable">
			
			<thead>
				<tr class="row text-center titleline">
					<th class="col-md-1 no d-none d-md-block">no.</th>
					<th class="col-12 col-md-6 title d-none d-md-block">title</th>
					<th class="col-md-2 writer d-none d-md-block">writer</th>
					<th class="col-md-1 hits d-none d-md-block">view</th>
					<th class="col-md-2 date d-none d-md-block">date</th>
				</tr>
			</thead>

			<tbody>
				<!-- choose 랑 when 사이에 아무것도 넣지않기. 넣으면 에러남. -->
				<c:choose>
					<c:when test="${not empty list}">
						<c:forEach var="i" items="${list}">
							
							<tr class="row text-center">
								<td class="col-12 col-md-1 no d-none d-md-block">${i.seq}</td>
								<td class="col-12 col-md-6 title text-truncate" id="title">
									<a href="/detail.board?seq=${i.seq}">${i.title} <span
										class="gray">[${i.commentNum}]</span></a> <!-- 날짜 계산 --> <jsp:useBean
										id="now" class="java.util.Date" /> <fmt:parseNumber
										value="${now.time / (1000*60*60*24)}" integerOnly="true"
										var="nowfmtTime" scope="request" /> <fmt:parseNumber
										value="${i.write_date.time / (1000*60*60*24)}"
										integerOnly="true" var="dbDtParse" scope="request" /> <c:if
										test="${(dbDtParse - nowfmtTime)==0}">
										<span
											class="badge bg-danger animate__animated animate__flash animate__infinite">NEW</span>
									</c:if>
								</td>
								<td class="col-5 col-md-2 writer"><i
									class="bi bi-pen d-block d-md-none"> ${i.writer}</i><span
									class="d-none d-md-block"> ${i.writer}</span></td>
								<td class="col-2 col-md-1 hits"><i
									class="bi bi-eye d-block d-md-none"> ${i.view_count}</i><span
									class="d-none d-md-block"> ${i.view_count}</span></td>
								<td class="col-5 col-md-2 date"><i
									class="bi bi-clock-history d-block d-md-none">
										${i.formedDate}</i><span class="d-none d-md-block">
										${i.formedDate}</span></td>
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
			$('#myTable').DataTable();
		});

		$("#toWrite").on("click", function() {
			location.href = "/board/toWrite";
		})
	</script>
</body>

</html>