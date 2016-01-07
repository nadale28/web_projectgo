<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
	#myform {
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
		text-align:center;
		color: #222;
		margin-top:20px;
		border-bottom: 2px solid #222;
		width:1020px;
	}
	#textarea{
		display:table;
		margin-top:20px;
		margin-bottom:20px;
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
<script>
function sub(){
 var x = modify.region1.value;
 $.ajax({   
  type: "POST",  
  url: "region2_search.do",   
  data: "region1="+x,   //&a=xxx 식으로 뒤에 더 붙이면 됨
  success: result    //function result 를 의미함
   }
 );
}
function result(msg){
 //sub()가 실행되면 결과 값을 가지고 와서 action 을 취해주는 callback 함수
 var sel  =  document.modify.region2;
 $("#sp1").html(msg); //innerHTML 을 이런 방식으로 사용함
 //id 는 $("#id")   name 의 경우 $("name") 으로 접근함
}
</script>
<script type="text/javascript">
function go_list(){
	location.href="list.do";
}
function go_update(){
	alert("D1");
	document.modify.action="update.do";
	document.modify.submit();
	alert("D2");
}
function go_delete(){
	alert("삭제되었습니다.");
	document.modify.action="delete.do";
	document.modify.submit();
}
</script>

</head>
<body>
<jsp:include page="toptop.jsp"></jsp:include>
<h1 id = "header">Modify Information</h1>
<form id="myform" name="modify" method="post">


<div id="main">Main Info</div>
<input type="hidden" name="num" value="${notice_bor_detail.num }"/>
<img id="img" src="http://placehold.it/350x130">
	<table>
	<tr>
		<td class="menu">제목</td><td><input type="text" name="title" value="${ notice_bor_detail.title}"/></td>
	</tr>
	<tr>
		<td class="menu">작성자</td><td><input type="text" name="author" value="${ notice_bor_detail.author}"/></td>
	</tr>
	<tr>
		<td class="menu">봉사지역</td>
			<td>
	<select name="region1" onchange="sub();">
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
   <option value="">선택하세요</option>
  </select>
 </span>
			</td>
	</tr>
	<tr>
		<td class="menu">모집시작날짜</td><td><input type="date" name="start_Day" value="${ notice_bor_detail.start_Day}"/></td>
	</tr>
	<tr>
		<td class="menu">모집종료날짜</td><td><input type="date" name="end_Day" value="${ notice_bor_detail.end_Day}"/></td>
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
<p id="sub">Detail Info</p>
<textarea id="textarea" name="content" rows="5" cols="120">${notice_bor_detail.content }</textarea>
<div align="center">
<c:if test="${notice_bor_detail.id==memId }">
<span class = "hover effect3" onClick="go_update();">수정</span>
<span class = "hover effect3" onClick="go_delete(${notice_bor_detail.num});">삭제</span>
</c:if>
</div>
</form>

</body>
</html>
