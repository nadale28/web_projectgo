<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<select name="region2">
<option value="">선택해주세요</option>
<c:forEach items="${requestScope.select_region }" var="board">
<option value="${board.region2 }">${board.city1 }</option>  
</c:forEach>
</select>
