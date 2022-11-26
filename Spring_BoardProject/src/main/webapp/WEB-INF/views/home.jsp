<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Index</title>
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<style>
@media ( min-width : 768px) {
	.container {
		width: 500px;
		margin-top: 200px;
	}
}

.container {
	margin-top: 200px;
}

h1 {
	font-weight: 900;
}

.center {
	text-align: center;
	margin-top: 30px;
	margin-bottom: 30px;
}

.title {
	margin-top: 30px;
}

.btn>button {
	width: 30%;
	margin-bottom: 10px;
}

#gridCheck1{
	position: relative;
	top: 1px;
}
.blank{
	margin-top: 20px;
}

</style>
</head>

<body>

	<c:choose>
		<c:when test="${loginID != null}">

			<div class="container border bg-light">
				<form id="frm" action="/member/delete" method="post">
					<div class="mb-3 center">
						<h3>${loginID}님 안녕하세요.</h3>
					</div>
					<div class="row mb-3 title">
						<div class="btn">
							<button type="button" class="btn btn-primary" id="toBoard">자유게시판</button>
							<button type="button" class="btn btn-primary" id="mypage">마이페이지</button><br>
							<button type="button" class="btn btn-primary" id="logout">로그아웃</button>
							<button class="btn btn-primary" id="memberout">회원탈퇴</button>
						</div>
					</div>
				</form>
			</div>

			<script>
				$("#toBoard").on("click",function(){
					location.href="/board/list";
				})
			
				$("#logout").on("click",function(){
					location.href="/member/logout";
				})
				
				$("#mypage").on("click",function(){
					location.href="/member/mypage";
				})
	
				$("#memberout").on("click", function () {
					
                Swal.fire({
                    title: '회원 탈퇴를 진행하시겠습니까?',
                    showConfirmButton: false,
                    showDenyButton: true,
                    showCancelButton: true,
                    denyButtonText: '회원 탈퇴',
                }).then((result) => {
                    if (result.isDenied) {
                        Swal.fire('회원 탈퇴가 완료 되었습니다.', '', 'success')
                            .then((result) => {
                                console.log("회원 탈퇴 진행 중");
                               // if (result.isConfirmed) {
                                	$("#frm").submit();
                                	console.log("회원 탈퇴 완료");
                                	
                              //  }
                            })
                        
                    }
                })
            })
						
				
			</script>
		</c:when>
		<c:otherwise>
			<div class="container border bg-light">
			
				<form class="px-4 py-3" action="/member/login" method="post">
					<div class="mb-3 center">
						<h1>Login Box</h1>
					</div>
					<div class="row mb-3 title">
						<label for="inputId" class="col-sm-2 col-form-label">Id</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="id"
								name="id">
						</div>
					</div>
					<div class="row mb-3">
						<label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="pw"
								name="pw">
						</div>
					</div>
					<div class="center">
						<button class="btn btn-primary" type="submit">Sign in</button>
						<a href="/member/toSignup"><button type="button"
								class="btn btn-primary">Sign up</button></a>
						<div class="row mb-3">
							<div class="blank">
								<input class="form-check-input" type="checkbox" id="gridCheck1">
								<label class="form-check-label" for="gridCheck1"> Remember me</label>
							</div>
						</div>
					</div>
				</form>
				
			</div>
		</c:otherwise>
	</c:choose>

</body>

</html>