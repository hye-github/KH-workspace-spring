<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Movie</title>

<style>

* {
 box-sizing:;
}

.box {
margin: auto;
align: center;
}

.row {
width: 700px;
height: 45px;
align: center;

border: 1px black solid;
}

.row > div {
float: left;
align: center;
}

.title {
width:20%;
height: 100%;
}

.input {
width:80%;
height: 100%;
}

input {
width:100%;
height: 100%;
margin: 0;
padding: 0;
}

</style>

</head>

<body>

	<form action="/netflix/insert">	
	
	<div class="box">
		<div class="row">
			<div class="title">title</div><div class="input">
			<input type="text" name="title" placeholder="input title"></div>
		</div>
		<div class="row">
			<div class="title">genre</div><div class="input">
			<input type="text" name="genre" placeholder="input genre"></div>
		</div>
		<div class="row">
			<button>입력완료</button> <a herf="/"><button type=button>Back</button></a>
		</div>
		
	</div>
	
	</form>

</body>
</html>