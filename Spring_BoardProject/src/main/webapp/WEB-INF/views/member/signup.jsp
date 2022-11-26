<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
        crossorigin="anonymous"></script>

    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
	
    <style>
        @media (min-width: 768px) { .container { width: 700px; }}

        #center {
            text-align: center;
        }

        #center>button {
            margin-top: 10px;
        }

        .container {
            margin-top: 50px;
            padding: 50px;
        }

        h2 {
            margin-bottom: 40px;
            text-align: center;
            font-weight: 700;
        }

        button {
            margin-right: 10px;
        }

        #pwresult {
            font-size: 14px;
            line-height: 38px;
            color: red;
        }
    </style>
</head>

<body>
    <div class="container border bg-light">
        <h2>Sign Up</h4>
        
            <form id="frm" action="/member/sign" method="post">
            
            <!-- 값 중에 uri가 있어서 무조건 메서드 post 처리를 해야한다. -->
            
                <div class="row mb-3 gy-2">
                    <label for="id" class="col-sm-2 col-form-label">ID</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="id" name="id">
                    </div>
                    <div class="col-sm-5">
                        <!--  <button type="button" class="btn btn-primary" id="idcheck">Check</button>  -->
                        <span id="result"></span>
                    </div>
                </div>
                <div class="row mb-3 gy-2">
                    <label for="password" class="col-sm-2 col-form-label">Password</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="password">
                    </div>
                    <div class="col-sm-5"></div>
                </div>
                <div class="row mb-3 gy-2">
                    <label for="passwordRE" class="col-sm-2 col-form-label">PwCheck</label>
                    <div class="col-sm-5">
                        <input type="password" class="form-control" id="passwordRE" name="pw">
                    </div>
                    <div class="col-sm-5" id="pwresult"></div>
                </div>
                <div class="row mb-3 gy-2">
                    <label for="name" class="col-sm-2 col-form-label">Name</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="name" name="name">
                    </div>
                    <div class="col-sm-5"></div>
                </div>
                <div class="row mb-3 gy-2">
                    <label for="phone" class="col-sm-2 col-form-label">Phone</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="phone" name="phone">
                    </div>
                    <div class="col-sm-5"></div>
                </div>
                <div class="row mb-3 gy-2">
                    <label for="email" class="col-sm-2 col-form-label">Email</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="email" name="email">
                    </div>
                    <div class="col-sm-5"></div>
                </div>
                <div class="row mb-3 gy-2">
                    <label for="zipcode" class="col-sm-2 col-form-label">Zipcode</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="zipcode" name="zipcode">
                    </div>
                    <div class="col-sm-3 gy-2">
                        <button type="button" class="btn btn-primary" onclick="execDaumPostcode()">Search</button>
                    </div>
                </div>
                <div class="row mb-3 gy-2">
                    <label for="address1" class="col-sm-2 col-form-label">Address1</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="address1" name="address1">
                    </div>
                </div>
                <div class="row mb-3 gy-2">
                    <label for="address2" class="col-sm-2 col-form-label">Address2</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="address2" name="address2">
                    </div>
                </div>

                <div id="center">
                    <button type="submit" class="btn btn-primary">Join</button>
                    <button type="button" class="btn btn-primary" id="rewrite">Retype</button>
                    <button type="button" class="btn btn-primary" onclick="history.back()">Back</button>
                </div>
            </form>
    </div>


    <script>
    
    	$("#id").on("input",function(){
    		idcheck = false; // 아이디를 수정하게되면 false로 되도록
    		// let 으로 변수명 선언을 하게 되면 중괄호 안에 생성된 지역변수가 되어버리기때문에 중괄호 밖에서 사용이 불가능하다.
    		// window.idcheck = false;	// public static 이나 마찬가지.
    		// input : 키 값 입력된거 확인은 불가능하지만(이땐 keypress), 반응성이 좋다.
    	  	// focusout : 다른 곳을 클릭하는 순간
    	})
    
  
         $("#id").on("blur", function(){
        	let id = $("#id").val();
        	
            if(id==""){ // 아이디가 이미 존재하므로 사용할 수 없는 경우
            	$("#result").html("아이디를 입력하세요.");
            	$("#id").focus();
            	return;
            }
        
        	$.ajax({
            	url:"/member/idCheck",
            	data:{"id":id}
            }).done(function(resp){
            	console.log(resp);
            	if(resp=="true"){ // 아이디가 이미 존재하므로 사용할 수 없는 경우
            		$("#result").html("이미 사용 중인 ID 입니다.");
            		idcheck = false;
            	}else{ // 아이디가 존재하지않으므로 사용할 수 있는 경우
            		$("#result").html("사용 가능한 ID 입니다.");
            		idcheck = true;
            	}
            });
     
         })
    	
    
    
    
    
    
    
        let inputId = document.getElementById("id");
        let inputPassword = document.getElementById("password");
        let inputPasswordRE = document.getElementById("passwordRE");
        let inputName = document.getElementById("name");
        let inputPhone = document.getElementById("phone");
        let inputEmail = document.getElementById("email");

        let frm = document.getElementById("frm");
        let reset = document.getElementById("rewrite");
        let idCheck = document.getElementById("idcheck");
        let pwResult = document.getElementById("pwresult");





        function pwCheck() {

            function earlyLockColor(element) {
                element.style.color = '#666666';
            }
            function beRightColor(element) {
                element.style.color = '#03c75a';
            }
            function beWrongColor(element) {
                element.style.color = 'red';
            }
            let pwCheck1 = inputPassword.value;
            let pwCheck2 = inputPasswordRE.value;

            let passwordRegex = /[A-Za-z0-9!@#$%]{10,20}/;

            if (pwCheck1 !== "" || pwCheck2 !== "") {

                if (passwordRegex.test(inputPassword.value) && (pwCheck1 == pwCheck2)) {
                    beRightColor(pwResult);
                    pwResult.innerHTML = "패스워드가 일치합니다.";
                } else if (pwCheck1 == "" || pwCheck2 == "") {
                    earlyLockColor(pwResult);
                    pwResult.innerHTML = "패스워드를 입력해주세요.";
                } else if (pwCheck1 != pwCheck2) {
                    beWrongColor(pwResult);
                    pwResult.innerHTML = "패스워드가 일치하지 않습니다.";
                } else {
                    beWrongColor(pwResult);
                    pwResult.innerHTML = "10~20자,영문대소문자,숫자,!@#$%만 허용됩니다.";
                }
            }
        }
        inputPassword.onkeyup = pwCheck;
        inputPasswordRE.onkeyup = pwCheck;


   
        
       

/*

        idCheck.onclick = function () {
            
            let id = document.getElementById("id").value;
            
            $.ajax({
            	url:"/duplCheck.mem",
            	data:{"id":id}
            }).done(function(resp){
            	console.log(resp);	
            	if(resp=="true"){ // 아이디가 이미 존재하므로 사용할 수 없는 경우
            		$("#result").html("이미 사용 중인 ID 입니다.");
            	}else{ // 아이디가 존재하지않으므로 사용할 수 있는 경우
            		$("#result").html("사용 가능한 ID 입니다.");
            	}
            });


        	
           // let str = inputId.value;
           // let idRegex = /^[a-z\d_]{8,14}$/;

           // if (!idRegex.test(str)) {
           //     Swal.fire({
           //         title: '사용할 수 없는 아이디 입니다.',
           //         html: '<h6>1. 소문자, 숫자, _만 사용 가능합니다.</h6><h6>2. 아이디는 8~14글자까지 사용 가능합니다.</h6>',
           //         icon: 'error',
           //         confirmButtonText: '확인'
           //     }).then(function () {
           //         inputPhone.focus();
           //     })
           //     inputPhone.value = "";
           // } 
            
            
            //    let id = document.getElementById("id").value;
            //    window.open("/duplCheck.mem?id=" + id,"","width=400,height=300");
                
               //절대 경로로 만들어야 servlet 할 때 절대경로로 가능
                
               // Swal.fire({
               //     icon: 'success',
               //     title: '사용할 수 있는 아이디 입니다.',
               //     showConfirmButton: false,
               //     timer: 1500
               // })
               
        }

        
*/
        
        
        let inputZipcode = document.getElementById("zipcode");
        let inputAddress1 = document.getElementById("address1");
        let inputAddress2 = document.getElementById("address2");

        reset.onclick = function () {
            inputId.value = "";
            inputPassword.value = "";
            inputPasswordRE.value = "";
            inputName.value = "";
            inputPhone.value = "";
            inputEmail.value = "";
            inputZipcode.value = "";
            inputAddress1.value = "";
            inputAddress2.value = "";
            pwResult.innerHTML = "";
        }


        function execDaumPostcode() {
            new daum.Postcode({
                oncomplete: function (data) {
                    var roadAddr = data.roadAddress; // 도로명 주소 변수
                    var extraRoadAddr = ''; // 참고 항목 변수

                    if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                        extraRoadAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if (data.buildingName !== '' && data.apartment === 'Y') {
                        extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if (extraRoadAddr !== '') {
                        extraRoadAddr = ' (' + extraRoadAddr + ')';
                    }

                    document.getElementById("zipcode").value = data.zonecode;
                    document.getElementById("address1").value = roadAddr;
                }
            }).open();
        }


        

        
    	$("#signupFrm").on("submit",function(){
    		
    		// 1. 아이디 중복확인이 제대로 되었는가?
    		if(!idcheck){
    			alert("아이디 중복 체크를 먼저 수행해주세요.");
    			return false;
    		}
    				
    		// 2. 패스워드가 일치하는가?
    		if(!($("#password").val() == $("#passwordRE").val())){
    			alert("입력한 두 패스워드가 일치하지 않습니다.");
    			
    		}		
    		
    				
			// 3. 각종 필드 유효성 검사들이 정상인가?
			
			alert("유효성 검사 모두 통과");
    		return false;
    	})
        
        
        
        frm.onsubmit = function () {

            let idRegex = /^[a-z\d_]{8,14}$/;
            let passwordRegex = /[A-Za-z0-9!@#$%]{10,20}/;
            let phoneRegex = /^0[\d]{2}[- ]?\d{3,4}[- ]?\d{4}$/;
            let nameRegex = /[가-힣]{1,5}/;
            let emailRegex = /[@\.a-zA-Z\d]+@[a-z]+\.[a-z]{2,3}/;

            
            
            if (inputId.value == "") {
                Swal.fire({
                    title: '아이디 미입력!',
                    text: '빈칸의 내용을 입력해주세요.',
                    icon: 'error',
                    confirmButtonText: '확인'
                })
                return false;

            } else if(!idcheck){
            	 Swal.fire({
                     title: '중복 아이디입니다.',
                     html: '아이디를 변경해주세요',
                     icon: 'error',
                     confirmButtonText: '확인'
                 })
                 return false;
            	 
    		} else if (!idRegex.test(inputId.value)) {
                Swal.fire({
                    title: '사용할 수 없는 아이디 입니다.',
                    html: '<h6>1. 소문자, 숫자, _만 사용 가능합니다.</h6><h6>2. 아이디는 8~14글자까지 사용 가능합니다.</h6>',
                    icon: 'error',
                    confirmButtonText: '확인'
                })
                return false;

            } else if (inputPassword.value == "") {
                Swal.fire({
                    title: '비밀번호 미입력!',
                    text: '빈칸의 내용을 입력해주세요.',
                    icon: 'error',
                    confirmButtonText: '확인'
                })
                return false;

            } else if (!passwordRegex.test(inputPassword.value)) {
                Swal.fire({
                    title: '사용할 수 없는 비밀번호 입니다.',
                    html: '<h6>비밀번호는 10-20자 길이로 가능하며</h6><h6>대소문자,숫자,!,@,#,$,% 문자만 사용가능합니다.</h6>',
                    icon: 'error',
                    confirmButtonText: '확인'
                })
                return false;

            } else if (inputPasswordRE.value == "") {
                Swal.fire({
                    title: '비밀번호 확인 미입력!',
                    text: '빈칸의 내용을 입력해주세요.',
                    icon: 'error',
                    confirmButtonText: '확인'
                })
                return false;

            } else if (!inputPassword.value == inputPasswordRE.value) {
                Swal.fire({
                    title: 'Error!',
                    text: '비밀번호와 동일하게 입력되지 않았습니다.',
                    icon: 'error',
                    confirmButtonText: '확인'
                })
                return false;

            } else if (inputName.value == "") {
                Swal.fire({
                    title: '이름 미입력!',
                    text: '빈칸의 내용을 입력해주세요.',
                    icon: 'error',
                    confirmButtonText: '확인'
                })
                return false;

            } else if (!nameRegex.test(inputName.value)) {
                Swal.fire({
                    title: '사용할 수 없는 이름 입니다.',
                    html: '<h6>이름은 한글만 입력되며</h6><h6>1-5글자까지 입력 가능합니다.</h6>',
                    icon: 'error',
                    confirmButtonText: '확인'
                })
                return false;

            } else if (inputPhone.value == "") {
                Swal.fire({
                    title: '전화번호 미입력!',
                    text: '빈칸의 내용을 입력해주세요.',
                    icon: 'error',
                    confirmButtonText: '확인'
                })
                return false;

            } else if (!phoneRegex.test(inputPhone.value)) {
                Swal.fire({
                    title: '사용할 수 없는 전화번호 입니다.',
                    html: '<h6>일반 전화 또는 휴대폰 번호 입력이 가능합니다.</h6>',
                    icon: 'error',
                    confirmButtonText: '확인'
                }).then(function () {
                    inputPhone.focus();
                })
                return false;

            } else if (inputEmail.value == "") {
                Swal.fire({
                    title: '이메일 미입력!',
                    text: '빈칸의 내용을 입력해주세요.',
                    icon: 'error',
                    confirmButtonText: '확인'
                })
                return false;

            } else if (!emailRegex.test(inputEmail.value)) {
                Swal.fire({
                    title: '사용할 수 없는 이메일 주소 입니다.',
                    html: '<h6>이메일 양식에 맞춰 입력 가능합니다.</h6><h6>(example@example.com)</h6>',
                    icon: 'error',
                    confirmButtonText: '확인'
                }).then(function () {
                    inputPhone.focus();
                })
                return false; // submit 중지

            } else {
            	
             //   Swal.fire({
             //      icon: 'success',
             //    	 title: '회원 가입이 완료 되었습니다.',
             //      showConfirmButton: false,
             //      timer: 1500	
             //  })
                
            }
        }

    </script>
</body>

</html>