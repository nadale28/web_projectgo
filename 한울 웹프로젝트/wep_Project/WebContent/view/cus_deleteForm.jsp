<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ include file = "../view/cus_color.jsp" %>

<html>
<head><title>ȸ�� Ż��</title></head>
<link href = "style.css" rel = "stylesheet" type = "text/css">
<script language = "javascript">
<!-- 
	function begin()
	{
		document.myform.passwd.focus();
	}

	function checkIt()
	{
		if(!document.myform.passwd.value)
		{
			alert("��й�ȣ�� �Է����� �����̽��ϴ�.");
			document.myform.passwd.focus();
			return false;
		}
	}
-->
</script>

<body onload = "begin()" bgcolor = "<%= bodyback_c %>">
<form name = "myform" action = "deletePro.jsp" method = "post"
	onSubmit = "return checkIt()">
<table cellspacing = "1" cellpadding = "1" width = "260"
	border = "1" align = "center">
<tr height = "30">
	<td colspan = "2" align = "middle" bgcolor = "<%= title_c %>">
		<font size = "+1"><b>ȸ�� Ż��</b></font>
	</td>
</tr>
<tr>
	<td width = "110" bgcolor = "<%= value_c %>" align = "center">
		��й�ȣ
	</td>
	<td width = "150" align = "center">
		<input type = "password" name = "passwd" size = "15" maxlength = "12">
	</td>
</tr>
<tr height = "30">
	<td colspan = "2" align = "middle" bgcolor = "<%= value_c %>">
		<input type = "submit" value = "ȸ��Ż��">
		<input type = "button" value = "��  ��"
			onclick = "javascript:window.location='cus_main.jsp'">
	</td>
</tr>
</table>
</form>
</body>
</html>