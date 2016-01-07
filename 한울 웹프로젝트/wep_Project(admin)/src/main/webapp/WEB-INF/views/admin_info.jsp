<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
<title>관리자모드 - 관리자 관리</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$(".delete").click(function(){
		var id = $(this).attr("id");
		location.href="admin_delete?id="+id;
	});
});

function go_reg(){
	alert("등록되었습니다.");
	
}
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
<link href='https://fonts.googleapis.com/css?family=Josefin+Sans' rel='stylesheet' type='text/css'>
<style>
	*{
	margin:0;
	}
	#header {
		font-family: 'Josefin Sans', sans-serif;
		text-align:center;
		border-bottom: 2px solid black;
		}
	#mainform{
		width:920px;
		margin-left:auto;
		margin-right:auto;
	}
	#board_main {
		width: 920px;
		margin: 0 auto;
		font-size: 12px;
		background: #fff;
		margin: 45px;
		border-collapse: collapse;
		text-align:center;
		margin-left:auto;
		margin-right:auto;
	}
	#menu {
		color: #222;
		padding: 10px 8px;
		border-top:2px solid #222;
		border-bottom: 2px solid #222;
		height:40px;
	}
	#membermanage{
	margin-left:20px;
		float:right;
	}
</style>
</head>
<body>
<form id = "mainform" action="insert" method="post">
<h1 id = "header">Manager Management</h1>
<table id="board_main" border="1">
<tr id="menu">
<th>no.</th>
<th>아이디</th>
<th>이름</th>
<th>새패스워드</th>
<th>새패스워드확인</th>
<th>변경/수정</th>
</tr>
<c:forEach items="${requestScope.select_admin }" var="adminlist">
<tr>
<td>${adminlist.no }</td>
<td>${adminlist.id }</td>
<td>${adminlist.name }</td>
<td>새패스워드</td>
<td>새패스워드확인</td>
<td><a>변경</a>/<a>수정</a><input id="${adminlist.id }" class="delete" type="button" value="삭제"/></td>
</tr>
</c:forEach>
</table>
<br>
<table id="board_main" border="1">
<tr id="menu">
<th>아이디</th>
<th>이름</th>
<th>새패스워드</th>
<th>새패스워드확인</th>
<th>등록</th>
</tr>
<tr>
<td><input type="text" name="id"></td>
<td><input type="text" name="name"></td>
<td><input type="password" name="password"></td>
<td><input type="password" name="password_confirm"></td>
<td><input type="submit" value="등록" onClick="go_reg();"></td>
</tr>
</table>
<input id="membermanage" type="button" value="메인으로" onClick="go_main();">
<input id="membermanage" type="button" value="회원관리" onClick="go_manager();">
<input id="membermanage" type="button" value="댓글관리" onClick="go_admin_reply_delete();">
</form>
</body>
</html>