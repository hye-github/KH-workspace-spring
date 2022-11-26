<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- jstl 포맷라이브러리 추가 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>My page</title>
            <script src="https://code.jquery.com/jquery-3.6.1.js"></script>
            <!-- CSS only -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
                crossorigin="anonymous">
            <!-- JavaScript Bundle with Popper -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
                crossorigin="anonymous"></script>

            <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
            <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

            <style>
                @media (min-width: 768px) {
                    .container {
                        width: 700px;
                    }
                }

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

            </style>
        </head>

        <body>
            <div class="container border bg-light">
                <h2>My page</h4>

                            <div class="row mb-3 gy-2">
                                <label for="id" class="col-sm-4 col-form-label">ID</label>
                                <div class="col-sm-8 col-form-label">${list.id}</div>
                            </div>
                            <div class="row mb-3 gy-2">
                                <label for="name" class="col-sm-4 col-form-label">Name</label>
                                <div class="col-sm-8 col-form-label">${list.name}</div>
                            </div>
                            <div class="row mb-3 gy-2">
                                <label for="phone" class="col-sm-4 col-form-label">Phone</label>
                                <div class="col-sm-8 col-form-label">${list.phone}</div>
                            </div>
                            <div class="row mb-3 gy-2">
                                <label for="email" class="col-sm-4 col-form-label">Email</label>
                                <div class="col-sm-8 col-form-label">${list.email}</div>
                            </div>
                            <div class="row mb-3 gy-2">
                                <label for="zipcode" class="col-sm-4 col-form-label">Zipcode</label>
                                <div class="col-sm-8 col-form-label">${list.zipcode}</div>
                            </div>
                            <div class="row mb-3 gy-2">
                                <label for="address1" class="col-sm-4 col-form-label">Address1</label>
                                <div class="col-sm-8 col-form-label">${list.address1}</div>
                            </div>
                            <div class="row mb-3 gy-2">
                                <label for="address2" class="col-sm-4 col-form-label">Address2</label>
                                <div class="col-sm-8 col-form-label">${list.address2}</div>
                            </div>
                            <div class="row mb-3 gy-2">
                                <label for="address2" class="col-sm-4 col-form-label">Signup Date</label>
                                <div class="col-sm-8 col-form-label"><fmt:formatDate pattern="yyyy년 MM월 dd일 가입" value="${list.signup_date}"/></div>
                            </div>
                   
                        <div id="center">
                            <button type="button" class="btn btn-primary" id="Modify">Modify</button>
                            <button type="button" class="btn btn-primary" onclick="history.back(); return false;">Back</button>
                        </div>
                        
            </div>

<script>

$("#Modify").on("click",function(){
	location.href="/member/modifyPage";
})

</script>


        </body>

        </html>