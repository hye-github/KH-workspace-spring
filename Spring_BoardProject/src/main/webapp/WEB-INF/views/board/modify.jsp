<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Board Write Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
        crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.6.1.js"></script>
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
            font-size: 15px;
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
        .btncenter{text-align: center;}
        
        input{width: 100%;}
        textarea{width: 100%; height: 500px;}
    </style>
</head>

<body>

    <div class="container">

	<form id="frm" action="/checkmodify.board" method="post">
	<div class="d-none"><input name="seq" value="${list.seq}"></div>
	        <div class="board alert alert-secondary" role="alert">
	            <h5>Modify Page</h5>
	            <hr>
	            <div class="row titleline">
	                <div class="col-12">
	                    <input type="text" name="titlepost" value="${list.title}">
	                </div>
	            </div>
	            <hr>
	            <div class="row">
	                <div class="col-12">
	                <textarea name="contentspost">${list.contents}</textarea>
	                </div>
	            </div>
	            <hr>
	            <div class="row text-center">
	                <div class="btncenter">
	                    <button type="button" class="btn btn-secondary" id="toBoard">List</button>
	                    <button class="btn btn-primary">Write</button>
	                    <button type="button" class="btn btn-secondary" onclick="history.back()">Back</button>
	                </div>
	            </div>
	        </div>
	
	</form>

    </div>

	<script>
		$("#toBoard").on("click",function(){
			location.href="/list.board?cpage=1";
		})
		
	</script>



</body>

</html>