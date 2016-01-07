<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script>
function sub(){
 var x = insert.region1.value;
 $.ajax({   
  type: "POST",  
  url: "region2_search.do",   
  data: "region1="+x,   //&a=xxx 식으로 뒤에 더 붙이면 됨
  success: result    //function result 를 의미함
   }
 );
}
function go_insert(){

	document.insert.action="insert.do";
	document.insert.submit();
} 
function result(msg){
 //sub()가 실행되면 결과 값을 가지고 와서 action 을 취해주는 callback 함수
 $("#sp1").html(msg); //innerHTML 을 이런 방식으로 사용함
 //id 는 $("#id")   name 의 경우 $("name") 으로 접근함
}
</script>
<link href='https://fonts.googleapis.com/css?family=Josefin+Sans' rel='stylesheet' type='text/css'>
<style>
	*{
	margin:0;
	}

	#header {
		font-family: 'Josefin Sans', sans-serif;
		text-align:center;
		border-bottom: 2px solid black;
		margin-top:120px;
		margin-bottom:70px;
	}
	#mainform {
		display:table;
		margin-left:auto;
		margin-right:auto;
	}
	#main {
		font-family: 'Josefin Sans', sans-serif;
		font-size:26px;
		text-align:center;
		color: #222;
		border-bottom: 2px solid #222;
		width:1020px;
	}
	#sub {
		font-family: 'Josefin Sans', sans-serif;
		font-size:26px;
		margin-top:20px;
		text-align:center;
		color: #222;
		border-bottom: 2px solid #222;
		width:1020px;
	}
	#textarea{
		display:table;
		margin-top:20px;
		margin-left:auto;
		margin-right:auto;
	}
	#img{
		float:left;
		margin-top:15px;
		margin-left:14%;
		margin-right:20px;
	}
	table{
		width:400px;
	}
	
	table .menu {
	color: #222;
	border-bottom: 1px solid #666;
	}
	.hover {
		max-width:400px; 
		height:80px; 
		line-height:80px; 
		margin:20px auto; 
		background-color:#999;  
		text-align:center; transition:all 0.8s, color 0.3s 0.3s;  
		color:#504f4f; cursor: pointer;  
		padding:10px 15px;
		border-radius:5px;
	}
	.hover:hover{
		color:#fff;
	}

  	.effect3:hover{
		box-shadow:0 150px 0 0 rgba(0,0,0,0.75) inset;
		}
</style>
</head>
<body>
<jsp:include page="toptop.jsp"/>
<h1 id = "header">Write Information</h1>
<form id = "mainform" name="insert" method="post">
<div id="main">Main Info</div>
	<img id="img" src="http://placehold.it/350x130">
	<table>
	<tr>
		<td class="menu">제목</td><td><input type="text" name="title"/></td>
	</tr>
	<tr>
		<td class="menu">작성자</td><td><input type="text" name="author"/></td>
	</tr>
	<tr>
		<td class="menu">봉사지역</td>
			<td><select name="region1" onchange="sub();">
	<option value="">선택해주세요</option>
	<option value="01">서울특별시</option>
	<option value="02">부산광역시</option>
	<option value="03">광주광역시</option>
	<option value="04">울산광역시</option>
	<option value="05">대구광역시</option>
	<option value="06">인천광역시</option>
	<option value="07">대전광역시</option>
	<option value="08">경기도</option>
	<option value="09">충청북도</option>
	<option value="10">충청남도</option>
	<option value="11">전라북도</option>
	<option value="12">전라남도</option>
	<option value="13">경상북도</option>
	<option value="14">경상남도</option>
	<option value="15">강원도</option>
	<option value="16">제주도</option>
</select>
 <span id="sp1">
  <select name="region2">
   <option value="">선택해주세요</option>
  </select>
 </span>
	</td>
	</tr>
	<tr>
		<td class="menu">모집시작날짜</td><td><input type="date" name="start_Day"/></td>
	</tr>
	<tr>
		<td class="menu">모집종료날짜</td><td><input type="date" name="end_Day"/></td>
	</tr>
	<tr>
		<td class="menu">모집인원</td>
		<td> 
			<select name="rec_People">
			<c:forEach var="i" begin="1" end="50" step="1">
				<option value=${i }>${i}</option>
				</c:forEach>
			</select>
		</td>
	</tr>

	
	</table>
<div id="sub">Detail Info</div>
<textarea id="textarea" name="content" rows="5" cols="120"></textarea>
<div align="center">
<span class = "hover effect3" onClick="go_insert();">저장</span>
</div>
</form>

</body>
</html>
