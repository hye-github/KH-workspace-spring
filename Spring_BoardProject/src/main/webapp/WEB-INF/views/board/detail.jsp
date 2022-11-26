<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!-- jstl 포맷라이브러리 추가 -->
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
			<!DOCTYPE html>
			<html lang="en">

			<head>
				<meta charset="UTF-8">
				<meta http-equiv="X-UA-Compatible" content="IE=edge">
				<meta name="viewport" content="width=device-width, initial-scale=1.0">
				<title>${dto.seq}. ${dto.title}</title>
				<link rel="stylesheet"
					href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
				<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
					integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
					crossorigin="anonymous">
				<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
					integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
					crossorigin="anonymous"></script>
				<script src="https://code.jquery.com/jquery-3.6.1.js"></script>

				<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
				<script src="https://rawgit.com/jackmoore/autosize/master/dist/autosize.min.js"></script>
				<!--  https://www.jacklmoore.com/autosize/ -->
				<style>
					@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');

					* {
						box-sizing: border-box;
						font-family: 'Noto Sans KR', sans-serif;
					}

					#title {
						text-align: left;
					}

					#title:hover {
						font-weight: 700;
						cursor: pointer;
					}

					.writer:hover {
						color: #666666;
						cursor: pointer;
					}

					.alert {
						background: #fff;
						vertical-align: middle;
					}


					.titleline>div {
						font-size: 17px;
						font-weight: 900;
					}

					div {
						font-size: 14px;
						font-weight: 500;
					}

					i {
						margin-top: 15px;
					}

					.bg-secondary {
						--badge-color: #999999;
					}

					.btn-bd-secondary {
						--bs-btn-font-weight: 500;
						--bs-btn-font-size: 12px;
						--bs-btn-padding-x: 10px;
						--bs-btn-padding-y: 3px;
						--bs-btn-border-color: #333333;
						--bs-btn-color: #333333;
						--bs-btn-bg: white;
						--bs-btn-hover-bg: #999999;
						--bs-btn-hover-color: white;
					}

					.pagination {
						--bs-pagination-color: #333333;
						--bs-pagination-active-bg: #999999;
						--bs-pagination-active-border-color: #999999;
						--bs-pagination-hover-color: #333333;
						--bs-pagination-font-size: 14px;
					}

					.cate {
						margin-top: 20px;
					}

					.board {
						margin-top: 10px;
					}


					.nav {
						--bs-nav-link-color: #666666;
						--bs-nav-link-hover-color: #222222;
						--bs-nav-link-font-size: 14px;
					}

					.nav-pills {
						--bs-nav-pills-link-active-bg: #999999;
					}

					h5 {
						text-align: center;
						font-weight: 900;
					}

					.btncenter {
						text-align: center;
					}

					.subline {
						margin-top: 10px;
					}

					.right {
						text-align: right;
					}

					.space {
						margin-right: 20px;
					}

					i {
						margin-right: 5px;
						margin-left: 5px;
					}

					#contents {
						border: none;
						resize: none;
						width: 100%;
						height: 500px;
						background-color: transparent;
					}

					#comments {
						width: 100%;
						height: 100%;
					}

					#btncomment {
						width: 100%;
						height: 100%;
					}

					textarea:focus {
						outline: none;
					}
					
					.titleline{
						padding-left: 5px;}
					
					textarea {
						height: 100%;
						padding-left: 10px;
						
						padding-right: 20px;
					}

					.commentsview {
						border: none;
						resize: none;
						width: 100%;
						background-color: transparent;
						padding-left: 30px;
					}

					.commentsview:focus {
						outline: none;
					}


					.minibtn {
						padding: 0px 5px 0px 5px;
						font-size: 8px;
						position: relative;
						top: -1px;
						margin-left: 1px;
						margin-right: 1px;
					}

					.commentline {
						margin-bottom: 5px;
					}

					#commentwriter {
						font-weight: 600;
					}

					#commentdate {
						color: #888888;
						padding-left: 10px;
						padding-right: 5px;
					}

					.btnbottom {
						margin-bottom: 5px;
					}

					.comments {
						padding-top: 5px;
					}
					
					.card {
						margin-bottom: 30px;
					}
					
					.new{position: relative;
					top: -3px;}
					
					a {color: #0099a4; 
					text-decoration:none;
					margin-left: 7px;}
					
					a:hover {color: #ffd400; }
					
				</style>
			</head>

			<body>

				<div class="container">

					<form action="/modify.board" method="post" id="frm">

						<div class="d-none" id="seq">
							<input name="seq" value="${dto.seq}">${dto.seq}
							<input name="title" value="${dto.title}">
							<input name="contents" value="${dto.contents}">
						</div>

						<div class="board alert alert-secondary" role="alert">
							<div class="row titleline">
								<div class="col-12">
									${dto.title}

									<!-- 날짜 계산 -->
									<jsp:useBean id="now" class="java.util.Date" />
									<fmt:parseNumber value="${now.time / (1000*60*60*24)}" integerOnly="true"
										var="nowfmtTime" scope="request" />
									<fmt:parseNumber value="${dto.write_date.time / (1000*60*60*24)}" integerOnly="true"
										var="dbDtParse" scope="request" />
									<c:if test="${(dbDtParse - nowfmtTime)==0}">
										<span class="badge bg-danger new">NEW</span>
									</c:if>

								</div>
							</div>


							<div class="row subline">
								<div class="col-6 col-md-4"><i class="bi bi-pen"> ${dto.writer}</i> </div>
								<div class="col-6 col-md-8 right">
									<span class="d-block d-md-none"><i class="bi bi-clock-history space">
											${dto.detailSubFormedDate}</i><i class="bi bi-eye">
											${dto.view_count}</i></span>
									<span class="d-none d-md-block"><i class="bi bi-clock-history space">
											${dto.detailFormedDate}</i><i class="bi bi-eye">
											${dto.view_count}</i></span>
								</div>
							</div>

							<hr>
							
							<div class="row">
								<div class="col-12">
									<textarea id="contents" name="contentspost" disabled>${dto.contents}</textarea>
								</div>
							</div>
							
							
						<c:if test="${not empty filelist}">
							<div class="card col-12">
							  <div class="card-header">
							    Download Files List
							  </div>
							  <ul class="list-group list-group-flush">
							  
							  <c:forEach var="i" items="${filelist}">
							  		<c:set var="num" value="${num+1}"/>
									<li class="list-group-item"><i class="bi bi-link-45deg"></i> ${num}. <a href="/download.file?sysname=${i.sysName }&oriname=${i.oriName }"> ${i.oriName }</a>
									
									<!-- <a href="/files/${i.sysName }"> ${i.oriName }</a> -->
									</li>  <!-- 다운로드 흉내 중 -->
							  </c:forEach>
							   
							  </ul>
							</div>
						</c:if>



							<c:choose>
								<c:when test="${not empty list}">
									<c:forEach var="i" items="${list}">
										<!--  <form method="post" class="commfrm"> 폼 중첩 안된다! 
										폼 태그 안에 폼 태그가 중첩이 안되어서 첫번째 댓글에 대한 수정 삭제 기능이 동작하지않았다.. 고통...
										
											<div class="d-none" class="seq">
												<input name="commseq" value="${i.seq}">
											</div>
										-->
										
											<hr>
											
											<div class="row commentedit">
												<div class="col-12 commentline">
													<span id="commentbtn">
														<span id="commentwriter"><i class="bi bi-chat-square-quote"></i> ${i.writer}</span>
														<span id="commentdate"> ${i.formedDate}</span>

														<c:choose>
															<c:when test="${loginID == i.writer}">
																<button type="button"
																	class="minibtn btn btn-outline-primary toCommentEdit" seq=${i.seq }>E</button>
																<button type="button"
																	class="minibtn btn btn-outline-danger toCommentDelete" seq=${i.seq }>X</button>
															</c:when>
														</c:choose>
													</span>
												</div>
												<div class="col-12 autosizediv">
													<textarea class="commentsview" name="commentsviewpost" disabled>${i.contents}</textarea>
												</div>
												
												
											</div>

										<!--  </form> -->

									</c:forEach>
								</c:when>
							</c:choose>

							<c:choose>
								<c:when test="${loginID != null}">
									<hr>
									<div class="row comments">
										<div class="col-8 col-md-9 col-lg-10">
											<textarea id="comments" name="commentspost"></textarea>
										</div>
										<div class="col-4 col-md-3 col-lg-2 btnbottom">
											<button type="button" id="btncomment"
												class="btn btn-outline-primary">comment</button>
										</div>
									</div>
								</c:when>
							</c:choose>

							<hr>
							<div class="row text-center">
								<div class="btncenter">

									<c:choose>
										<c:when test="${loginID == dto.writer}">
											<button class="btn btn-primary">Edit</button>
											<button type="button" class="btn btn-danger" id="toDelete">Delete</button>
										</c:when>
									</c:choose>

									<button type="button" class="btn btn-secondary" id="toBoard">List</button>
								</div>
							</div>
						</div>

					</form>

				</div>

				<script>
					autosize($("textArea"));
					
					
					/*
					
					<button type=button class="deleteComment" seq=${i.seq }>삭제</button>
					
					
					$(".deleteComment").on('click',function(){
						
						let target = $(this).attr("seq"); 
						location.href="/delete.cmts?seq="+target;
					})
					
					속성 부여하고 seq 찾기
					
					*/
					
					
					
					
					


					// id 중복으로 만들면 안됨!!!

					$(document).on("click", ".toCommentDelete", function () {

						Swal.fire({
							title: '코멘트를 삭제 하시겠습니까?',
							showConfirmButton: false,
							showDenyButton: true,
							showCancelButton: true,
							denyButtonText: '삭제',
						}).then((result) => {
							if (result.isDenied) {
								Swal.fire('코멘트 삭제가 완료 되었습니다.', '', 'success')
									.then((result) => {
										let target = $(this).attr("seq"); 
										console.log("seq : "+target);
										console.log("dto seq : "+${dto.seq});
										$(this).closest('form').attr("action", "/delete.comment?parent_seq=${dto.seq}&seq=" + target);
										/* $(this).closest('form').attr("action", "/delete.comment?parent_seq=${dto.seq}&seq=" + $(this).closest("form").find("input[name=commseq]").val()); */
										$(this).closest('form').submit();
									})

							}
						})

					})


					$(document).on("click", ".toCommentEdit", function () {
						/* form 은 찾아지니까 폼으로 찾고, 그 다음에 find로 클래스 찾는게 빠르다. */
						
						$(this).hide();
						$(this).closest(".commentedit").find(".toCommentDelete").hide();
						
						/* $(".toCommentEdit").hide();
						$(".toCommentDelete").hide(); */
						
						/* $(this).closest("form").find(".toCommentEdit").hide(); -> 이렇게 하면 전체 수정된다... */
						/* $(this).closest("form").find(".commentsview").attr("disabled", "false"); */
						
						$(this).closest(".commentedit").find(".commentsview").attr("disabled", false);
						$(this).closest(".commentedit").find(".commentsview").css("outline", "auto");
						
						
						let modifyOK = $("<button>");

						modifyOK.addClass("minibtn");
						modifyOK.addClass("btn");
						modifyOK.addClass("btn-outline-primary");

						let target = $(this).attr("seq"); 
						console.log("seq : "+target);
						console.log("dto seq : "+${dto.seq});
						
						modifyOK.html("Edit OK");
						modifyOK.on("click", function () {
							$(this).closest("form").attr("action", "/edit.comment?parent_seq=${dto.seq}&seq=" + target);
							/* $(this).closest('.commentline').attr("action", "/edit.comment?parent_seq=${dto.seq}&seq=" + $(this).closest('form').find("input[name=commseq]").val()); */
							$(this).closest("form").submit();
						})

						let modifyCancel = $("<button>");
						modifyCancel.addClass("minibtn");
						modifyCancel.addClass("btn");
						modifyCancel.addClass("btn-outline-danger");

						modifyCancel.attr("type", "button");
						modifyCancel.html("cancle");
						modifyCancel.on("click", function () { location.reload(); });

						$(this).closest(".commentedit").find(".toCommentDelete").before(modifyOK);
						$(this).closest(".commentedit").find(".toCommentDelete").before(modifyCancel);

					})




					$("#btncomment").on("click", function () {
						$("#frm").attr("action", "/checkwrite.comment?parent_seq=${ dto.seq }")
						$("#frm").submit();
					})


					$("#toBoard").on("click", function () {
						location.href = "/list.board?cpage=1";
					})


					$("#toDelete").on("click", function () {

						Swal.fire({
							title: '게시글을 삭제 하시겠습니까?',
							showConfirmButton: false,
							showDenyButton: true,
							showCancelButton: true,
							denyButtonText: '삭제',
						}).then((result) => {
							if (result.isDenied) {
								Swal.fire('게시글 삭제가 완료 되었습니다.', '', 'success')
									.then((result) => {
										$("#frm").attr("action", "/delete.board?seq=" + ${ dto.seq })
										$("#frm").submit();
									})

							}
						})
					})

				</script>



			</body>

			</html>