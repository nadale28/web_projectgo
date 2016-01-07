<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	$(function() {
		var select = "<option>:: 선택 ::</option>"; 
		$("#product").change(function() {			
			if($("#product").val() == "") { // select의 value가 ""이면, "선택" 메뉴만 보여줌.
				$("#sub").find("option").remove().end().append(select);
			} else {
				comboChange($(this).val());
			}
		});

		function comboChange() {
			$.ajax({
				type:"post",
				url:"test1.do",
				datatype: "json",
				data: "region1="+$("#productForm").serialize(),
				success: function(data) {
					if(data.SUB_LIST.length > 0) {
						$("#sub").find("option").remove().end().append(select);
						$.each(data.SUB_LIST, function(key, value) {
							$("#sub").append("<option>" + value + "</option>"); 
						});
					} else {
						$("#sub").find("option").remove().end().append("<option>-- No sub --</option>");
						return;
					}
				},
				error: function(x, o, e) {
					var msg = "페이지 호출 중 에러 발생 \n" + x.status + " : " + o + " : " + e; 
					alert(msg);
				}				
			});
		}	
	});
	
</script>
</head>
<body>
	<form id="productForm">
		<select name="product" id="product">
			<option value="">:: 선택 ::</option>
			<c:forEach var="value" items="01,02,03">
				<option>${value}</option>
			</c:forEach>		
		</select>
	
		<select name="sub" id="sub">
			<option>:: 선택 ::</option>
		</select>
	</form>
</body>
</html>