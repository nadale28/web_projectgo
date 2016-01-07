<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ include file = "../view/cus_color.jsp" %>

<html>
<head><title>메인입니다.</title></head>
<link href = "style.css" rel = "stylesheet" type = "text/css">

<%
String chk = request.getParameter("chk")==null ? "0" : request.getParameter("chk");

	try
	{
		if(session.getAttribute("log_fail_id")!=null ||session.getAttribute("log_fail_pw")!=null||session.getAttribute("memId")==null)
		{
%>
			<script language = "javascript">
			window.onload = function(){ 
				<%if( chk.equals("1")){%>
			
				if(<%=session.getAttribute("log_fail_id")%>==-1){
					alert("아이디를 찾을수 없습니다.");
				}
				if(<%=session.getAttribute("log_fail_pw")%>==0){
					alert("비밀번호를 찾을 수 없습니다.");
				}
	<%
			
	session.removeAttribute("log_fail_id");
	session.removeAttribute("log_fail_pw");
				}
		%>		
			}
			function focusIt()
			{
				document.inform.id.focus();
			}
			
			function checkIt()
			{
				inputForm = eval("document.inform");
				
				if(!inputForm.id.value)
				{
					alert("아이디를 입력하세요.");
					inputForm.id.focus();
					return false;
				}
				
				if(!inputForm.passwd.value)
				{
					alert("비밀번호를 입력하세요.");
					inputForm.passwd.focus();
					return false;
				}
			}
			
			</script>
<%
	// ID저장
	Cookie [] idc = request.getCookies();
	
	String id = "";
	
	if(idc != null)
	{
		for(Cookie idcs : idc)
		{
			if(idcs.getName().equals("id"))
				id = idcs.getValue();
		}
	}
%>

<body onLoad = "focusIt();" bgcolor = "<%= bodyback_c %>">
<table width = "500" cellpadding = "0" cellspacing = "0" align = "center" border = "1">
<tr>
	<td width = "300" bgcolor = "<%=bodyback_c %>" height = "20">&nbsp;</td>
	
	<form name = "inform" method = "post" action = "cus_login.do" onSubmit = "return checkIt();">
	<td bgcolor = "<%= title_c %>" width = "30" align = "right">ID</td>
	<td width = "150" bgcolor = "<%= value_c %>">
		<input type = "text" name = "id" size = "13" maxlength = "10" value = "<%= id %>">
		<input type = "checkbox" name = "saveid">ID저장
	</td>
</tr>
<tr>
	<td rowspan = "2" bgcolor = "<%= bodyback_c %>" width = "300">메인입니다.</td>
	<td bgcolor = "<%= title_c %>" width = "30" align = "right">PW</td>
	<td width = "150" bgcolor = "<%= value_c %>">
		<input type = "password" name = "passwd" size = "13" maxlength = "10">
		<input type = "submit" name = "Submit" value = "로그인">
	</td>
</tr>
<tr>
	<td colspan = "3" bgcolor = "<%= title_c %>" align = "center">
		<input type = "button" value = "회원가입" onclick =
			"javascript:window.location='cus_inputForm.do'">
		<input type = "button" value = "ID찾기" size = "3" onclick =
			"javascript:window.location='cus_searchIdForm.do'">
		<input type = "button" value = "PW찾기" onclick =
			"javascript:window.location='cus_searchPwForm.do'">
	</td>
</tr>
</form>
</table>
<%
		}
		else 
		{
%>
<table width = "500" cellpadding = "0" cellspacing = "0" align = "center" border = "1">
<tr>
	<td width = "300" bgcolor = "<%= bodyback_c %>" height = "20">Hi</td>
	<td rowspan = "3" bgcolor = "<%= value_c %>" align = "center">
		<%= session.getAttribute("memId") %>님이<br>
		방문하였습니다.
		<form method = "post" action = "cus_logout.do">
		<input type = "submit" value = "로그아웃">
		<input type = "button" value = "회원정보변경" onclick =
			"javascript:window.location='cus_modify.do?id=<%= session.getAttribute("memId") %>'">
		</form>
	</td>
</tr>
<tr>
	<td rowspan = "2" bgcolor = "<%= bodyback_c %>" width = "300">메인입니다.</td>
</tr>
</table>
<br>
<%
		}

	}catch(NullPointerException e){}
%>
</body>
</html>
