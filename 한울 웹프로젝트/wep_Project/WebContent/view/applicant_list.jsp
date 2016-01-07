<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>

<style>
.name{  width:100px; height:10px; line-height:40px; }
.phone{ width:300px; height:10px;}
td{line-height:30px; border-bottom:1px solid #666; }
.tr{border-top:2px solid #222; border-bottom:2px solid #222;}
</style>

<body>
<h1 align="center"> 신청자 목록</h1>
<table row=1   style="margin:0 auto; border-collapse:collapse; margin-top:50px"   cellpadding="5">
<tr class="tr" >
      <td class="name"  align=center >이름</td>
      <td class="phone"  align=center>연락처</td>
     </tr>
   <c:forEach items="${requestScope.apl_list}" var="applicant">
     
      <tr >
         <td align=center>${applicant.name}</td>
         <td align=center>${applicant.phone1}-${applicant.phone2}-${applicant.phone3}</td>
      </tr>

   </c:forEach>
</table>   

</body>
</html>