<%@ page contentType = "text/html; charset=utf-8" %>
<%@ include file = "../view/cus_color.jsp" %>

<% request.setCharacterEncoding("utf-8"); %>
<%-- 
<jsp:useBean id = "member" class = "fourleaf.logon.LogonDataBean">
	<jsp:setProperty name = "member" property = "*" />
</jsp:useBean> --%>

<html>
<body>

<%-- <%
	String id = (String)session.getAttribute("memId");
	member.setId(id);
	LogonDBBean manager = LogonDBBean.getInstance();
	manager.updateMember(member);
%> --%>

<link href = "style.css" rel = "stylesheet" type = "text/css">
<table width = "270" border = "0" cellspacing = "0"
	cellpadding = "5" align = "center">
<tr bgcolor = "<%= title_c %>">
	<td bgcolor = "<%= title_c %>">
		<font size = "+1"><b>회원정보가 수정되었습니다.</b></font>
	</td>
</tr>
<tr>
	<td bgcolor = "<%= value_c %>" align = "center">
		<form>
			<input type = "button" value = "메인으로"
				onclick = "window.location='cus_main.do'">
		</form>
		5초후에 메인으로 이동합니다.<meta http-equiv="Refresh" content = "5;url=cus_main.do">
	</td>
</tr>
</table>
</body>
</html>