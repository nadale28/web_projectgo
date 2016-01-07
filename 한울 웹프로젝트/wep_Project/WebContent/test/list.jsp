<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function go_write(){
	location.href="write.do";
}
function add_reg_people(){
	alert("신청되었습니다.");
}
function stop_reg_people(){
	alert("마감되었습니다.");
}
/* function go_detail(rcv){
	location.href="detail.bo?num="+rcv;
}
function go_search(){
	document.search.action="search.bo";
	document.search.submit();
} */
</script>
<title>Insert title here</title>
<style>
* {
	margin: 0;
}

.board_wrap {
	width: 960px;
	margin: 0 auto;
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
}

#board_main tr {
	text-align: center;
}

#board_nav_num {
	text-align: center;
	margin: 22px 0 22px 0;
}

#board_search_bar {
	float: right; margin-right:30px;
}
</style>
</head>
<body>
	<!-- 번호,지역, 제목, 작성자, 모집인원, 일시, 마감여부,신청 -->
	<div class="board_wrap">
		<img id="board_img_top" src="html_img/board_img_top.jpg" /> <img
			id="board_img_middle" src="html_img/board_img_middle.gif" />
		<table id="board_main">
			<tr>
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
			<c:set var="sysdate" value="<%=new java.util.Date() %>" />
			
		<c:forEach items="${requestScope.notice_bor_list }" var="board">
			<tr>
				<td>${board.no }</td>
				<td>${board.city1} ${board.city2}</td>
				<td>${board.title }</td>
				<td>${board.start_Day }→${board.end_Day }</td>
				<td>${board.reg_People }/${board.rec_People }</td>
				<td>${board.author }</td>
				<td>
				<c:set var="reg_People" value="${board.reg_People }"/>
				<c:set var="rec_People" value="${board.rec_People }"/>
				<%-- <c:set var="end_Day" value="${board.end_Day }"/>
				<c:if test="${end_Day== sysdate||reg_People>=rec_People}"> --%>
				마감됨
				<%-- </c:if> --%>
				</td>
				<td>
				<c:choose>
				<c:when test="${reg_People<=rec_People-1}">
				<a href="add.do?add_num=${board.num }">
				<input type="button" value="신청" onClick="add_reg_people();" /></a>
				</c:when>
				<c:otherwise>
				<input type="button" value="신청" onClick="stop_reg_people();" />
				</c:otherwise>
				</c:choose>
				</td>
			</tr>
			</c:forEach>
			
<!-- 			<tr>
				<td></td>
				<td>여수</td>
				<td>김귀귀선거위 봉사활동</td>
				<td><input type="date"></td>
				<td>00/50</td>
				<td>김기기</td>
				<td>모집중</td>
				<td><input type="button" value="신청" /></td>
			</tr> -->
		

		</table>
<img src="imgs/btn_write.gif" onclick="go_write();"/>





		<div id="board_nav_num">
			&nbsp;<b
				style="font-family: tahoma; font-size: 8pt; color: #1471ae; padding: 0 3px 0 3px;">1</b>
			&nbsp;<a href=''><span
				style="font-family: tahoma; font-size: 8pt; color: #777777; padding: 0 3px 0 3px;">2</span></a>
			&nbsp;<a href=''><span
				style="font-family: tahoma; font-size: 8pt; color: #777777; padding: 0 3px 0 3px;">3</span></a>
			&nbsp;<a href=''><span
				style="font-family: tahoma; font-size: 8pt; color: #777777; padding: 0 3px 0 3px;">4</span></a>
			&nbsp;<a href=''><span
				style="font-family: tahoma; font-size: 8pt; color: #777777; padding: 0 3px 0 3px;">5</span></a>
			&nbsp;<a href=''><span
				style="font-family: tahoma; font-size: 8pt; color: #777777; padding: 0 3px 0 3px;">6</span></a>
			&nbsp;<a href=''><span
				style="font-family: tahoma; font-size: 8pt; color: #777777; padding: 0 3px 0 3px;">7</span></a>
			&nbsp;<a href=''><span
				style="font-family: tahoma; font-size: 8pt; color: #777777; padding: 0 3px 0 3px;">8</span></a>
			&nbsp;<a href=''><span
				style="font-family: tahoma; font-size: 8pt; color: #777777; padding: 0 3px 0 3px;">9</span></a>
			&nbsp;<a href=''><span
				style="font-family: tahoma; font-size: 8pt; color: #777777; padding: 0 3px 0 3px;">10</span></a>
			&nbsp;<a href=''><img src='../skin/board/basic/img/page_end.gif'
				border='0' align='middle' alt='맨끝'></a>
		</div>
		<div id="board_search_bar">
			<select>
				<option>제목</option>
				<option>내용</option>
				<option>지역</option>
				<option>일시</option>

			</select> <input type="text" name="board_search" /> <input type="submit"
				value="찾기" />
		</div>
	</div>



</body>
</html>