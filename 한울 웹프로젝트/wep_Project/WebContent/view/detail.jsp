<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href='https://fonts.googleapis.com/css?family=Josefin+Sans' rel='stylesheet' type='text/css'>
<style>
	*{
	margin:0;
	}
	body{overflow:auto;}
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
		width:80%;
	}
	#main {
		font-family: 'Josefin Sans', sans-serif;
		font-size:26px;
		text-align:center;
		color: #222;
		border-bottom: 2px solid #222;
		width:100%;
	}
	#sub {
		font-family: 'Josefin Sans', sans-serif;
		font-size:26px;
		text-align:center;
		color: #222;
		margin-top:20px;
		border-bottom: 2px solid #222;
		width:100%;
	}
	#textarea{
		display:table;
		margin-left:auto;
		margin-right:auto;
	}
	#img{
		float:left;
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
	#findnum{
	text-align:right;
	font-size: 12px;
	color: #585858
	}
	#content{
	
	margin-top: 20px;
	margin-bottom: 20px;
	border: 2px Outset #666;
	height:140px;
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
<script type="text/javascript">
function go_list(){
	location.href="list.do";
}
function go_modify(rcv){
	location.href="modify.do?num="+rcv;
	document.modify.submit();
}
function go_delete(rcv){
	alert("삭제되었습니다.");
	location.href="delete.do?num="+rcv;
	
	
}

function go_add(rcv){
	alert("신청되었습니다.");
	location.href="add.do?add_num="+rcv;
	
}
function not_write(){
	
	alert("로그인이 필요합니다.");
}

</script>
</head>
<body>
<jsp:include page="toptop.jsp"></jsp:include>
<h1 id = "header">Notice Board Info</h1>
<form id="mainform" method="post" name="detail">
<div id="main">${notice_bor_detail.title }</div>
<div id="findnum" align="right">조회수:${requestScope.notice_bor_detail.readCnt}</div>
	<img id="img" src="img/board.jpg" style="width:350px; height:130px;'">
	<table>
	<tr>
		<td class="menu">작성자</td><td>${notice_bor_detail.author }</td>
	</tr>
	<tr>
		<td class="menu">봉사지역</td>
			<td>
			${notice_bor_detail.city1 } ${notice_bor_detail.city2 }
			</td>
	</tr>
	<tr>
		<td class="menu">모집날짜</td><td>${notice_bor_detail.start_Day }/${notice_bor_detail.end_Day }</td>
	</tr>
	<tr>
		<td class="menu">모집인원</td>
		<td>
			${notice_bor_detail.reg_People }/${notice_bor_detail.rec_People }
		</td>
	</tr>
	<tr>
		<td class="menu">모집여부</td><td>${notice_bor_detail.deadline }</td>
	</tr>
	
	</table>
	<p id="sub">Detail Info</p>
	
	<div id="content">${notice_bor_detail.content }</div>
	<div align="center">
	<span class = "hover effect3" onClick="go_list();">목록</span>
<c:if test="${notice_bor_detail.id==memId }">
	<span class = "hover effect3" onClick="go_modify(${notice_bor_detail.num});">수정</span>
	<span class = "hover effect3" onClick="go_delete(${notice_bor_detail.num});">삭제</span>
</c:if>
<c:set var="recruit" value="모집중"/>
	<c:set var="is" value="0"></c:set> 
				<c:forEach items="${requestScope.regId }" var="regId1">
					<c:if test="${regId1.num==notice_bor_detail.num }">
					<c:set var="is" value="1"></c:set>
					</c:if>
				</c:forEach> 
				<c:if test="${is==1 }">
				<a href="add.do?add_num=${notice_bor_detail.num }&chk=1">
				<span class = "hover effect3" onClick="back_reg_people();" >취소</span></a></c:if>
				<c:if test="${is==0 }">
				<c:choose>
				<c:when test="${memId!=null&&notice_bor_detail.deadline==recruit}">
				<a href="add.do?add_num=${notice_bor_detail.num }&chk=0"> 
				<span class = "hover effect3" onClick="add_reg_people();" >신청</span> </a> 
				</c:when>
				<c:when test="${memId==null&&notice_bor_detail.deadline==recruit}">
				<a href=""> 
				<span class = "hover effect3" onClick="not_write();" >신청</span> </a> 
				</c:when>
				<c:when test="${notice_bor_detail.deadline!=recruit}">
				<span class = "hover effect3" onClick="stop_reg_people();" >마감</span>
				</c:when>
				</c:choose></c:if>
	</div>
	</form>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
