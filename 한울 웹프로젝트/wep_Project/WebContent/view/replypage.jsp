<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%

String name=(String)session.getAttribute("name");
%>
<script>

function go_page(page,block){
	 $.ajax({   
	  type: "POST",  
	  url: "repAjax.do",   
	  data: "curPage="+page+"&curBlock="+block,   //&a=xxx 식으로 뒤에 더 붙이면 됨
	  success: result, 
	  error : function(xhr, status, error) {
		     alert("에러발생");
		}//function result 를 의미함
	   }
	 
	 );
	}

	function result(msg){
	 //sub()가 실행되면 결과 값을 가지고 와서 action 을 취해주는 callback 함수
	 $("#sp1").html(msg); //innerHTML 을 이런 방식으로 사용함
	 //id 는 $("#id")   name 의 경우 $("name") 으로 접근함
	}
	

</script>
<style>
#curpage_ok{font-family: tahoma; 
font-size: 8pt; color: red; 
padding: 0 3px 0 3px;}

#curpage_not{font-family: tahoma; 
font-size: 8pt; color: #777777; 
padding: 0 3px 0 3px;}

</style>
</head>
<body>
<c:set var="pageBlock" value="${pageInfo.pageBlock}"/>
<c:set var="totBlock" value="${pageInfo.totalBlock }"/>
<c:set var="totPage" value="${pageInfo.totalPage }"/>
<c:set var="curBlock" value="${pageInfo.curBlock }"/>
<c:set var="curPage" value="${pageInfo.curPage }"/>
<c:set var="beginPage" value="${pageInfo.beginPage }"/>
<c:set var="endPage" value="${pageInfo.endPage }"/>

	<!--First:첫block 첫page 
		Prev:이전block 첫Page  -->
	<%-- <c:if test="${curBlock>1}"> --%>
	<%-- <input type="button" value="첫페이지"
				onclick="${curBlock>1 ? 'go_page(1,1);' :''}"/> --%>
	<img src = 'img/doublearrow.png' width = "20px" height = "20px"
				onclick="${curBlock>1 ? 'go_page(1,1);' :''}"/>
	<c:set var="prev" 
		value="go_page(${beginPage-pageBlock},${curBlock-1});"/>
	<img src = 'img/backarrow.png' width = "20px" height = "20px"
			onclick="${curBlock>1 ? prev :''}"/></td>		
	<%-- </c:if> --%>
	<c:if test="${curBlock==1}">
	<td width="25px"></td><td width="25px"></td>
	</c:if>
	<!-- 페이지번호 -->
	<c:forEach var="i" begin="${beginPage}" end="${endPage}" step="1">
	<c:if test="${i<=totPage}">
		<c:if test="${i==curPage}">
		
			&nbsp;&nbsp;
			<span id="curpage_ok">
			${i}
			</span>&nbsp;&nbsp;
		
		</c:if>
		<c:if test="${i!=curPage}">
		
		
			&nbsp;&nbsp;
			
			<span id="curpage_not" onclick="go_page(${i},${curBlock});">
	              	${i}
			</span>
			&nbsp;&nbsp;
		
		
		</c:if>
	</c:if>
	</c:forEach>

	<!--Next:다음block 첫page 
		Last:마지막block 마지막Page  -->
	<%-- <c:if test="${curBlock<totBlock}"> --%>
	<c:set var="next" 
		value="go_page(${endPage+1},${curBlock+1});"/>
	<c:set var="last"
		value="go_page(${totPage},${totBlock});" />
	<img src = "img/arrow.png" width = "20px" height = "20px"
			onclick="${curBlock<totBlock ? next : ''}"/></td>		
	<img src = "img/forwdoublearrow.png" width = "20px" height = "20px"
			onclick="${curBlock<totBlock ? last : ''}"/></td>
	<%-- </c:if>	 --%>	

</body>
</html>










