<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<style>
	*{margin:0px; padding:5px;  }
	.wrap{background:#ffffff; width:780px; height:580px; border: 0.5px solid #000000;font-size:13px; padding:30px 40px 40px 40px; margin:0 auto;} 
	.line{border-bottom:1px solid #000}
</style>
<title>Insert title here</title>
</head>
<body>
    <div>
        <form action="sendMail.jsp" method="post">
            <table>
                <tr><th colspan="2">JSP 메일 보내기</th></tr>
                <tr><td>from</td><td><input type="text" name="from" /></td></tr>
                <tr><td>to</td><td><input type="text" name="to" /></td></tr>
                <tr><td>subject</td><td><input type="text" name="subject" /></td></tr>
                <tr><td>content</td><td><textarea name="content" style="width:170px; height:200px;"></textarea></td></tr>
                <tr><td colspan="2" style="text-align:right;"><input type="submit" value="Transmission"/></td></tr>
            </table>
        </form>
    </div>
    




	<div class="wrap">

		<div id="container">
		
			<div id="content" class="conWrap">
				<div class="headline" style="margin-bottom:30px">
					<h1>아이디 / 비밀번호 찾기</h1>
				</div>


				<div class="id" >

					<form method = "post" name = "searchId2">
						
							<h3 style="margin-bottom:10px; border-bottom:1px solid orangered">아이디 찾기</h3>
								<table class="board4" >
						
									<tbody><tr>
										<td colspan="2">
											
										</td>
									</tr>
									<tr class="line">
										<td >이름</td>
										<td><input type="text" name="name" maxlength="50" id="user_nm"  style="ime-mode:active; width:300px;" ></td>
									</tr>
									<tr class="_number1">
										<td>주민등록 번호</td>
										<td>
											<input type = "text" name = "jumin1" size = "7" maxlength = "6" style="width:130px;" > - 
											<input type = "text" name = "jumin2" size = "7" maxlength = "7" style="width:140px;">
										</td>

									</tbody>
								</table>
								<input type = "button" value = "메인으로.." onclick = 
											"javascript:window.location = 'wep_main.jsp'">
										<input type = "button" value = "찾기" onClick="searchId(this.form);">
					</form>

				</div>





				<div class="pw">
					<form method = "post" name="searchPw2">
					<h3 style="margin-bottom:10px; border-bottom:1px solid orangered">비밀번호 찾기</h3>
					<div >
						<table >

							<tbody>
							<tr>
								<td colspan="2">

								</td>
							</tr>
							<tr>
								<td>아이디</td>
								<td><input type="text" name="id" maxlength="50" value="" style="ime-mode:inactive; width:300px"></td>
							</tr>
							<tr>
								<td>주민등록 번호</td>
								<td>	
									<input type = "text" name = "jumin1" size = "7" maxlength = "6" style="width:130px;"> - 
									<input type = "text" name = "jumin2" size = "7" maxlength = "7" style="width:140px;">
								</td>
							</tr>
							
							</tbody>
						</table>
										<input type = "button" value = "메인으로.." onclick = 
											"javascript:window.location = 'wep_main.jsp'">
										<input type = "button" value = "찾기" onClick="searchPw(this.form);">
					</form>
				</div>

	
		</div>

	</div>
</body>
</html>