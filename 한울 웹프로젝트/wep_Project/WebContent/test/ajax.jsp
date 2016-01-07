<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

<script>
$(document).ready(function() {
     jQuery.ajax({
           type:"GET",
           url:"ajax.jsp",
           datd:{"name": "lioncat", "age": 14, "nickname": "qwerty"},
           dataType:"JSON", // 옵션이므로 JSON으로 받을게 아니면 안써도 됨
           success : function(data) {
                 // 통신이 성공적으로 이루어졌을 때 이 함수를 타게 된다.
                 // TODO
        	   alert(data.name);
        	   alert(data.age);
        	   alert(data.nickname);
           },
           complete : function(data) {
                 // 통신이 실패했어도 완료가 되었을 때 이 함수를 타게 된다.
                 // TODO
           },
           error : function(xhr, status, error) {
                 alert("에러발생");
           }
     });
});
</script>
</head>
<body>

</body>
</html>