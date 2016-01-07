<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>

<script type="text/javascript">
function reply_delete(rdate){
	location.href='admin_reply_delete?rdate='+rdate ;
}
function go_main(){
	location.href="admin_info";
}
function go_manager(){
	location.href="member_management";
}
function go_admin_reply_delete(){
	location.href="admin_reply_delete";
}
</script>
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


function sub(){
 var x = reply.id.value;
 var y = reply.content.value;
 $.ajax({   
  type: "POST",  
  url: "reply.do",   
  data: "id="+x+"&content="+y,   //&a=xxx 식으로 뒤에 더 붙이면 됨
  success: result    //function result 를 의미함
   }
 );
}

function result(msg){
 //sub()가 실행되면 결과 값을 가지고 와서 action 을 취해주는 callback 함수
 var sel  =  document.insert.region2;
 $("#sp1").html(msg); //innerHTML 을 이런 방식으로 사용함
 //id 는 $("#id")   name 의 경우 $("name") 으로 접근함
}

</script>



<style>
	.reply{width:960px; margin:0 auto;}
	
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
	
	.reply_list_detail {height:43px; vertical-align:middle;border-bottom:1px solid gray;}
	
	.page {margin:0 auto; text-align:center; margin-top:10px;}
	
	p{margin:0;padding:0;}
	#membermanage{
	margin-left:20px;
		float:right;
	}
</style>

</head>
<body>
	
	<div class="reply">
	
		<div class="reply_container">
		    <p>관리자 댓글 관리</p>
			<!-- <div class="reply_box">
				<form action="reply.do" method="post" name="reply">
					제목<input type="text" name="title" />
					
					<div class="container_box">
						<input type="text" name="id" class="id_box" placeholder="아이디"/><br>
						<textarea name="content" rows="4" cols="80" class="content_box"></textarea>
						<input type="submit" value="댓글등록" class="rbtn"/>
					</div>			
					내용<input type="text" name="content" />
					아이디<input type="text" name="id" />
					<input type="submit" value="고"/>
				</form>
			</div> -->
			<div class="reply_list">
				<!-- <table border=1> -->
					<c:forEach items="${requestScope.board}" var="board">
						<!-- <tr> -->
					<div class="reply_list_detail">
						<div class="user_info">
							<p class="id_list">${board.id}</p><p class="date_list">${board.rdate}</p>
						</div>
							<p style="float:left" class="content_list">${board.content}</p>
							<a style="float:right" href="admin_reply_delete?rdate=${board.rdate}" }>삭제</a>
					</div>	
						<!-- </tr> -->
					</c:forEach>
				<!-- </table> -->
				<div class="page">
					<jsp:include page="rpage.jsp"></jsp:include>
				</div>
			</div>
		</div>
		<input id="membermanage" type="button" value="메인으로" onClick="go_main();">
<input id="membermanage" type="button" value="회원관리" onClick="go_manager();">
<input id="membermanage" type="button" value="댓글관리" onClick="go_admin_reply_delete();">
	 </div>
</body>
</html>