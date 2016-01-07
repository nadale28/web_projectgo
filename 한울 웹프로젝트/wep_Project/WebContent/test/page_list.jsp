<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.mousewheel.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
function go_write(){
	location.href="write.do";
}
function add_reg_people(){
	/* location.href="add.do?add_num="+num; */
	alert("신청되었습니다.");
}
function stop_reg_people(){
	alert("마감되었습니다.");
}
  function go_detail(rcv){
	location.href="detail.do?num="+rcv;
}  
  function go_search(){

		document.search.action="search.do";
		document.search.submit();
	} 
	function go_list(){

		location.href="list.do";
	} 
	function not_write(){
		alert("로그인이 필요합니다.");
	}
</script>
 
<link href='https://fonts.googleapis.com/css?family=Josefin+Sans' rel='stylesheet' type='text/css'>
<title>Insert title here</title>
<style>
	* {
		margin: 0;
	}
	a {
	text-decoration:none;
	}
	.board_wrap {
		width: 960px;
		margin: 0 auto;
	}
	#header {
		font-family: 'Josefin Sans', sans-serif;
		text-align:center;
		border-bottom: 2px solid black;
		margin-top:120px;
		margin-bottom:140px;
	}
	
	#board_img_top {
		width: 920px;
		height: 200px;
	}

	#board_img_middle {
		width: 920px;
		height: 100px;
	
	}

	#board_main {
		width: 920px;
		margin: 0 auto;
		font-size: 12px;
		background: #fff;
		margin: 45px;
		border-collapse: collapse;
		text-align: left;
	}
	.menu {
		color: #222;
		padding: 10px 8px;
		border-top:2px solid #222;
		border-bottom: 2px solid #222;
		height:40px;
	}
	#board_main tr {
		text-align: center;
	}

	#board_nav_num {
		text-align: center;
		margin: 22px 0 22px 0;
	}

	#board_search_bar {
		float: right;
		margin-right:20px;
	}
	#page{
		margin:0 auto;
	}
	#add{
		color:#fff;
		background:orange;
		padding:5px;
	}
	#stop{
		color:#fff;
		background:#aaa;
		padding:5px;
	}
	#cancle{
	    color:#fff;
		background:#222;
		padding:5px;
	}
	
	#btn_write{
		float:right;
		margin-left:5px;
		margin-right:5px;
	}
	#searchimg{
		cursor:pointer;
	}
	.txtstyle1{
		border-bottom:1px solid #666;
		height: 40px;
	}
	.txtstyle1:hover {
		color: #666;
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
	<!-- 번호,지역, 제목, 작성자, 모집인원, 일시, 마감여부,신청 -->
<jsp:include page="toptop.jsp"/>
	<h1 id = "header">Notice Board</h1>
	<div class="board_wrap">

		<table id="board_main">
			<tr class = "menu">
				<td>번호</td>
				<td>지역</td>
				<td>제목</td>
				<td>일시</td>
				<td>모집인원</td>
				<td>작성자</td>
				<td>마감여부</td>
				<td>바로신청</td>
			</tr>			
			<%@page import="java.util.*, com.model.*" %>
		<c:forEach items="${requestScope.notice_bor_list }" var="board">
			<tr class = "txtstyle1">
				<td onclick="go_detail(${board.num});">${board.no }</td>
				<td onclick="go_detail(${board.num});">${board.city1} ${board.city2}</td>
				<td onclick="go_detail(${board.num});">${board.title }</td>
				<td onclick="go_detail(${board.num});">${board.start_Day }→${board.end_Day }</td>
				<td onclick="go_detail(${board.num});">${board.reg_People }/${board.rec_People }</td>
				<td onclick="go_detail(${board.num});">${board.author }</td>
				<td onclick="go_detail(${board.num});">${board.deadline }
				<c:set var="recruit" value="모집중"/>
				</td>
				<td>
			     <c:set var="is" value="0"></c:set> 
				<c:forEach items="${requestScope.regId }" var="regId1">
					<c:if test="${regId1.num==board.num }">
					<c:set var="is" value="1"></c:set>
					</c:if>
				</c:forEach> 
				<c:if test="${is==1 }">
				<a href="add.do?add_num=${board.num }&chk=1"><span id="cancle" onClick="back_reg_people();" >취소</span></a></c:if>
				<c:if test="${is==0 }">
				<c:choose>
				<c:when test="${memId!=null&&board.deadline==recruit}">
				<a href="add.do?add_num=${board.num }&chk=0"> 
				<span id="add" onClick="add_reg_people();" >신청</span> </a> 
				</c:when>
				<c:when test="${memId==null&&board.deadline==recruit}">
				<a href=""> 
				<span id="add" onClick="not_write();" >신청</span> </a> 
				</c:when>
				<c:when test="${board.deadline!=recruit}">
				<span id="stop" onClick="stop_reg_people();" >마감</span>
				</c:when>
				</c:choose></c:if>
				</td>
			</tr>
			</c:forEach>

		</table>
		<%if(session.getAttribute("memId")==null) {%>
		<div id="btn_write" >
		<span class="hover effect3" onclick="not_write();">글쓰기</span>
		</div>
		<%}else{ %>
		<div id="btn_write" >
		<span class="hover effect3" onclick="go_write();">글쓰기</span>
		</div>
		
		<%} %>
		<div id="btn_write" >
		<span class="hover effect3" onclick="go_list();">${reg_id }목록</span>
		</div>

		
		<div id="board_nav_num">
		<jsp:include page="page.jsp"/>
		</div>
		
		<div id="board_search_bar">
		<form name="search" method="post" >
			<select id="search_name" name="search_name" style="float:left; height:21px; margin-top:9px;">
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="author">작성자</option>
				<option value="city1||city2">지역</option>		
			</select> 
			
			<input type="text" name="search_value" style="display:block;  margin-top:9px"/> 
			<img src="view/img/search.png" id="searchimg" 
			onClick="go_search();" width="40px" height="40px" />
			</form>
			
		</div>
		<br/>
	<br/>
	<br/>
	</div>
	
</body>
</html>

