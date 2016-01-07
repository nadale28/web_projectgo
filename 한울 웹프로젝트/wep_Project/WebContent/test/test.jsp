<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script>
function sub(){
 var x = f.sel1.value;
 $.ajax({   
  type: "POST",  
  url: "test1.do",   
  data: "region1="+x,   //&a=xxx 식으로 뒤에 더 붙이면 됨
  success: result    //function result 를 의미함
   }
 );
}
function result(msg){
 //sub()가 실행되면 결과 값을 가지고 와서 action 을 취해주는 callback 함수
 var sel  =  document.f.sel2;
 $("#sp1").html(msg); //innerHTML 을 이런 방식으로 사용함
 //id 는 $("#id")   name 의 경우 $("name") 으로 접근함
}
</script>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
<form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post">
  <input type="hidden" name="cmd" value="_s-xclick">
  <input type="hidden" name="hosted_button_id" value="6RNT8A4HBBJRE">
  <input type="image" 
    src="https://www.sandbox.paypal.com/en_US/i/btn/btn_buynowCC_LG.gif" 
    border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
  <img alt="" border="0" src="https://www.sandbox.paypal.com/en_US/i/scr/pixel.gif" 
    width="1" height="1">
</form>
<form name="f">
 <select name="sel1" onchange="sub();">
  <option value="">선택하세요</option>
  <option value="01">서울</option>
  <option value="02">경기</option>
 </select>
 <span id="sp1">
  <select name="sel2">
   <option value="">선택하세요</option>
  </select>
 </span>
</form>
</body>
</html>

<script>
function change_go(form){
var value = form.region.options[form.region.selectedIndex].value;
var text = form.region.options[form.region.selectedIndex].text;

location.href="region.do?region1="+ form.region.options[form.region.selectedIndex].value;
alert("text -->"+text+"\nvalue-->"+value);
}

/* function change_go(form){
	location.href=="region_1.do?region1="+ form.region.options[form.region.selectedIndex].value;
 	var text = form.gu_1.options[form.gu_1.selectedIndex].text;

	alert("text -->"+text+"\nvalue-->"+value); 
	}*/


</script>