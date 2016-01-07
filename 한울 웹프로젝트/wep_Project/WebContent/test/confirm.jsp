<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>데이터 베이스 연동</title>
<style type="text/css">
 form{
  width:500px;
  margin:10px auto;
 }
 ul{
  padding:0;
  margin:0;
  list-style:none;
 }
 ul li{
  padding:0;
  margin:0 0 10px 0;
 }
 label{
  width:100px;
  float:left;
 }
</style>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var count = 0;
		alert("Dd");
		$('#confirmId').on('click', function(){
			alert("Dd");
			var uid = $('#id').val();
			if(uid==''){
				alert('아이디를 입력하세요!');
				$('#id').focus();
				return;
			}
			
			$.getJSON('confirmId.jsp',{id:uid},function(data){
				if(data.id==uid){//아이디 중복
					count=0;
					$('#id_signed').html('이미 등록된 아이디').css('color', 'red');
					$('#id').val('').focus();
				}else{
					count=1; //중복 확인 작업을 수행하면 count를 1로 변경
					$('#id_signed').html('사용 가능한 아이디').css('color', 'black');
				}
			});
		});
		
		//submit이벤트 처리
		$('#insert_form').submit(function(){
			//기본 이벤트 제거
			event.preventDefault();
			
			if(count!=1){
				alert('아이디 중복 체크 필수!');
				$('#id').focus();
				return;
			}
			alert('전송!');			
			
		});
		
		
	});
</script>
</head>
<body>
<!-- ajax에서는 id를 중요시함 -->
 <form id ="insert_form" method="post">
  <fieldset>
   <legend>데이터 추가</legend>
   <ul>
    <li>
     <label for="id">아이디</label>
     <input type="text" name="id" id="id">
     <input type="button" id="confirmId" value="아이디 중복체크">
     <span id="id_signed"></span>
    </li>
    <li>
     <input type="submit" value="추가">
    </li>
   </ul>
  </fieldset>
  
 </form>
</body>
</html>