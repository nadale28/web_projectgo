<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href='https://fonts.googleapis.com/css?family=Josefin+Sans' rel='stylesheet' type='text/css'>
<style>
	*{
		margin:0;
	}
	#mainform{
		width:920px;
		margin-left:auto;
		margin-right:auto;
	}
	#header {
		font-family: 'Josefin Sans', sans-serif;
		text-align:center;
		border-bottom: 2px solid black;
		}
	#board_main {
		margin: 0 auto;
		font-size: 12px;
		background: #fff;
		margin: 45px;
		border-collapse: collapse;
		text-align: center;
		margin-left: auto;
		margin-right: auto;
	}
	#menu {
		color: #222;
		padding: 10px 8px;
		border-top:2px solid #222;
		border-bottom: 2px solid #222;
		height:40px;
		}
		#board_nav_num{
		text-align:center;
		}
		#center{
		width:250px;
		margin:0 auto;
		}
		

</style>
<script>
function go_main(){
	location.href="admin_info";
}
function go_manager(){
	location.href="member_management";
}
function go_admin_reply_delete(){
	location.href="admin_reply_list";
}
</script>
</head>
<body>
<form id = "mainform">
<h1 id = "header">Member info</h1>
<table id = "board_main" border="1">
<tr id="menu">
<th>no.</th>
<th>회원ID</th>
<th>이름</th>
<th>이메일</th>
<th>휴대폰</th>
<th>가입일</th>
</tr>
<c:forEach items="${requestScope.memmanger }" var="member">
<tr>
<td style="width:10%;">${member.no }</td>
<td style="width:17%;">${member.id }</td>
<td style="width:15%;">${member.name }</td>
<td style="width:24%;">${member.email }${member.email2 }</td>
<td style="width:17%;">${member.phone1 }-${member.phone2}-${member.phone3 }</td>
<td style="width:17%;">${member.reg_date }</td>
</tr>
</c:forEach>
</table>
</form>

 <div id="board_nav_num">
<jsp:include page="page.jsp"/>
		
		</div>
		
		<div id="center">
		<br><br><br>
		<input id="membermanage" type="button" value="메인으로" onClick="go_main();">
<input id="membermanage" type="button" value="회원관리" onClick="go_manager();">
<input id="membermanage" type="button" value="댓글관리" onClick="go_admin_reply_delete();">
		</div>

</body>
</html>