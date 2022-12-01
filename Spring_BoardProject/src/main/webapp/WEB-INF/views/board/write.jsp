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
        
        .input-group{margin-bottom:5px;}
        
    </style>
</head>

<body>

    <div class="container">

<form id="frm" method="post" enctype="multipart/form-data" onsubmit="sendit(); return false;">
<!-- 파일첨부는 무조건 post, enctype="multipart/form-data" 무조건 이거 포함 -->

<!-- enctype="multipart/form-data" 이거 쓰면서 request.getParameter("titlepost"); 사용 불가능하다. 현재는 cos.jar 사용하기로함. -->
		<input type="hidden" value="${loginID}" name="writer">
		
        <div class="board alert alert-secondary" role="alert">
            <h5>Write Page</h5>
            <hr>
            <div class="row titleline">
                <div class="col-12">
                    <input type="text" placeholder="Please enter a post title" name="title" id="title">
                </div>
            </div>
            <hr>
            
            <div class="row">
            
                <div class="col-12">
                <textarea placeholder="Please enter a post contents" name="contents" id="contents"></textarea>
                </div>
                
                
                <div class="input-group">
				<input type="file" class="form-control" id="files" name="files" multiple>
				<!-- 
				<input type="file" class="form-control" id="file" name="file">
				 -->
				<!-- cos.jar는 name 값이 같으면 올릴 수 없다. multiple 옵션에서는 name 하나로 올리는거라 불가능 -->
                <!-- Attachments 파일 업로드 기능 
                <button class="btn btn-outline-secondary" type="button" id="inputGroupFileAddon04">Files Add</button>
				-->
                <!-- <input type="file" class="form-control" id="inputGroupFile02" name="file" multiple>  똑같은 name으로 올라가게 된다. -->
                </div>
            </div>
            
            <div class="row text-center">
                <div class="btncenter">
                    <button class="btn btn-secondary" id="toBoard" type="button">List</button>
                    <button class="btn btn-primary" id="toWrite">Write</button>
                </div>
            </div>
        </div>

</form>

    </div>

	<script>
		$("#toBoard").on("click",function(){
			history.back();
		})
		
		let count=1;
		$("#inputGroupFileAddon04").on("click",function(){
			let fileDiv = $("<div class='input-group'>");
			let inputFile = $("<input type='file' class='form-control' aria-describedby='inputGroupFileAddon04' aria-label='Upload'>");
			
			inputFile.attr("name","file"+count++);
			
			let delBtn = $("<button class='btn btn-outline-secondary' type='button'> X </button>");
			
			delBtn.on("click",function(){
				$(this).parent().remove();
			})
			
			fileDiv.append(inputFile);
			fileDiv.append(delBtn);
			
			$("#inputGroupFileAddon04").parent().after(fileDiv);
			
		})
		

		function sendit() {
			
			console.log($("#title").val());
			console.log($("#contents").val());
			var $fileUpload = $("input[type='file']");
			
			if($("#title").val()=="" || $("#contents").val()==""){
				alert("제목이나 내용입력이나 해라");
				return false;
			} else if (parseInt($fileUpload.get(0).files.length)>2) {
	            alert("파일 두개만 올려라");
	            $("input[type='file']").val("");
	            return false;
			} else {
				$("#frm").attr("action", "/file/multiUpload")
				$("#frm").submit();
			}
		}
		
	</script>



</body>

</html>