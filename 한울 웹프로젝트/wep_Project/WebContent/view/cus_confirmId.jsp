<%@ page contentType = "text/html; charset=utf-8" %>
<%@ include file = "../view/cus_color.jsp" %>

<html>
<head><title>ID 중복확인</title></head>



<body bgcolor = "<%= bodyback_c %>">
<%	String id= request.getParameter("id");
	if( (Integer)session.getAttribute("check") == 1 )
	{
%>
<table width = "270" border = "0" cellspacing = "0" cellpadding = "5">
<tr bgcolor = "<%= title_c %>">
	<td height = "39"><%= id %>이미 사용중인 아이디입니다.</td>
</tr>
</table>
<form name = "checkForm" method = "post" action = "cus_confirmId.do">
<table width = "270" border = "0" cellspacing = "0" cellpadding = "5">
<tr>
	<td bgcolor = "<%= value_c %>" align = "center">
		다른 아이디를 사용하세요.<p>
		<input type = "text" size = "10" maxlength = "12" name = "id">
		<input type = "submit" value = "ID중복확인">
	</td>
</tr>
</table>
</form>
<%
	}
	else
	{
%>
<table width = "270" border = "0" cellspacing = "0" cellpadding = "5">
<tr bgcolor = "<%= title_c %>">
	<td align = "center"><p>
		입력하신 <%= id %>는 사용하실 수 있는 ID입니다.
		<input type = "button" value = "닫기" onclick = "setid()">
	</td>
</tr>
</table>
<%
	}
%>
</body>
</html>
<script language = "javascript">
 
	function setid()
	{
		opener.document.userinput.id.value = "<%= id %>";
		self.close();
	}

</script>