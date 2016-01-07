<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
 <script>

$(document).ready(function(){
	$('.rbtn').mousedown(function(){
		if($('.id_box').val()==''){
			alert('id를 입력해주세요');
		}
		else if($('.content_box').val()==''){
			alert('내용을 입력해주세요');
		}
	});
});

function replay(){
 var x = reply.id.value;
 var y = reply.content.value;
 $.ajax({   
  type: "POST",  
  url: "repAjax.do",   
  data: "id="+x+"&content="+y,   //&a=xxx 식으로 뒤에 더 붙이면 됨
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
</script> -->

<style>
	.reply2{ margin:0 auto; height:57%}
	
	.reply_container{width:560px; margin:0 auto;}
	.reply_box{width:100%; background:#f5f5f5;padding:5px; border:1px solid gray; margin-top:8px;}	
	.container_box{margin:0 auto;overflow:hidden;height:80px;}
	.id_box{margin-bottom:5px;height:20%;}
	.content_box{width:82%;height:80%;float:left;padding:0;box-sizing:border-box;}
	.rbtn{width:17%; height:80%;float:left;box-sizing:border-box;margin-left:1%;
		height:80%;font-weight:bold;}
	
	.reply_list{width:560px; background:white;padding:5px; margin:5px auto;}
	.user_info{overflow:hidden;margin-top:12px;}
	.id_list{float:left; margin-right:10px; font-size:8px; color:#696969}
	.date_list{float:left; font-size:7px; color:#696969}
	.content_list{margin:6px 0 0 0; padding:0; font-size:10px;color:#696969}
	
	#reply{height:43px; vertical-align:middle;border-bottom:1px solid gray;}
	
	.page {margin:0 auto; text-align:center; margin-top:10px;}
	
	p{margin:0;padding:0;}
</style>

</head>
<body>
	
	<div class="reply2">
	
		<div class="reply_container">
		    
			<div class="reply_box">
				<form  method="post" name="reply">
					<!-- 제목<input type="text" name="title" /> -->

					<div class="container_box">
						<input type="text" name="id" class="id_box" placeholder="아이디"/><br>
						<textarea name="content" rows="4" cols="80" class="content_box"></textarea>
						<input type="button"  onClick="replay();" value="댓글등록" class="rbtn"/>
					</div>			
					<!-- 내용<input type="text" name="content" />
					아이디<input type="text" name="id" />
					<input type="submit" value="고"/> -->
				</form>
			</div>
			<div id="sp1">
			<div class="reply_list" >
				<!-- <table border=1> -->
					<c:forEach items="${requestScope.board}" var="board">
						<!-- <tr> -->
					<div id="reply2">
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
			</div>
		</div>
		
	 </div>
</body>
</html>