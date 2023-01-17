<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Home</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
</head>
<body>


<input type=text id=data1 name=data1>
<input type=text id=data2 name=data2>
<button id=asJson>asJson</button>


<script>

$("#asJson").on("click",function(){
	console.log($("#data1").val())
	console.log($("#data2").val())
	console.log("----------------------------------");
	$.ajax({
		url:"asJson",
		data:{data1:$("#data1").val(), data2:$("#data2").val()},
		dataType:'JSON',
		success:function(resp){

			console.log(resp.data1 + " : " + resp.data2);

			
			console.log("----------------------------------");
			console.log(resp);
		}
	})
})

</script>

</body>

</html>


<!-- 

<html>
<head>
<title>Home</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
</head>
<body>

<input type=text id=data1 name=data1>
<input type=text id=data2 name=data2>
<button id=asJson>asJson</button>

<script>
$("#asJson").on("click",function(){
	console.log("----------------------------------");
	$.ajax({
		url:"asJson",
		data:{data1:$("#data1").val(), data2:$("#data2").val()},
		success:function(resp){
			console.log(resp);
		}
	})
})

</script>
</body>
</html>

-->