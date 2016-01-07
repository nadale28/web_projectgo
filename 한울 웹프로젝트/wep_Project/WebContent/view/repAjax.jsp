<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="reply_list">
				<!-- <table border=1> -->
					<c:forEach items="${requestScope.board}" var="board">
						<!-- <tr> -->
					<div id="sp1">
						<div class="user_info">
							<p class="id_list">${board.id}</p><p class="date_list">${board.date}</p>
						</div>
							<p class="content_list">${board.content}</p>
					</div>	
						<!-- </tr> -->
					</c:forEach>
				<!-- </table> -->
				<div class="page">
					<jsp:include page="replypage.jsp"></jsp:include>
				</div>
			</div>
