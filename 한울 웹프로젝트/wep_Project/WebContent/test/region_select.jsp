<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<TITLE> New Document </TITLE>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script>
function sub(){
 var x = test_select.region1.value;
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
 var sel  =  document.test_select.region2;
 $("#sp1").html(msg); //innerHTML 을 이런 방식으로 사용함
 //id 는 $("#id")   name 의 경우 $("name") 으로 접근함
}
</script>

</HEAD>
<BODY>
<form name="test_select" method="post">

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
</form>
</BODY>
</HTML>