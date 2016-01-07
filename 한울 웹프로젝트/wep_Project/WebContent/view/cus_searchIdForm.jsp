<%@ page contentType = "text/html; charset=utf-8" %>
<%@ include file = "../view/cus_color.jsp" %>

<html lang="ko">
 <head>
  <meta charset="UTF-8">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <title>Document</title>
 </head>
 <script type="text/javascript">
 function searchId(searchId2)
	{
		// url과 사용자 입력 아이디를 조합합니다.
		
		location.href="cus_searchId.do?name="+searchId2.name.value
		+"&email="+searchId2.email.value
		+"&email2="+searchId2.email2.value;
	}
 
 function searchPw(searchPw2)
	{
		// url과 사용자 입력 아이디를 조합합니다.
		location.href= "cus_searchPw.do?id="+searchPw2.id.value
				+"&email="+searchPw2.email.value
				+"&email2="+searchPw2.email2.value;
	}
 </script> 

<style>
	*{margin:0px; padding:5px;  }
	.wrap{background:#ffffff; width:780px; height:580px; border: 0.5px solid #000000;font-size:13px; padding:30px 40px 40px 40px; margin:0 auto;} 
	.line{border-bottom:1px solid #000}
</style>

 <body>

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
										<td>이메일</td>
										<td colspan="2"><input
				style="width: 45%; height: 35px; "
				placeholder="이메일 아이디" type="text" name="email" maxlength="30">
				@ <select type="text"
				style="width: 45%; height: 35px; " maxlength="30"
				placeholder="이메일" name="email2">
					<option value="@naver.com">naver.com</option>
					<option value="@hanmail.net">hanmail.net</option>
					<option value="@daum.net">daum.net</option>
					<option value="@nate.com">nate.com</option>
					<option value="@yahoo.co.kr">yahoo.co.kr</option>
					<option value="@paran.com">paran.com</option>
					<option value="@empal.com">empal.com</option>
					<option value="@gmail.com">gmail.com</option>
					<option value="@hotmail.com">hotmail.com</option>
					<option value="@netian.com">netian.com</option>
					<option value="@dreamwiz.com">dreamwiz.com</option>
					<option value="@hanmir.com">hanmir.com</option>
					<option value="@lycos.co.kr">lycos.co.kr</option>
					<option value="@korea.com">korea.com</option>
					<option value="@chollian.net">chollian.net</option>
					<option value="@orgio.net">orgio.net</option>
					<option value="@hanafos.com">hanafos.com</option>
					<option value="@kornet.net">kornet.net</option>
			</select> </td>

									</tbody>
								</table>
								<input type = "button" value = "메인으로.." onclick = 
											"javascript:window.location = 'main.do'">
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
								<td>이메일</td>
								
										<td colspan="2"><input
				style="width: 45%; height: 35px; "
				placeholder="이메일 아이디" type="text" name="email" maxlength="30">
				@ <select type="text"
				style="width: 45%; height: 35px; " maxlength="30"
				placeholder="이메일" name="email2">
					<option value="@naver.com">naver.com</option>
					<option value="@hanmail.net">hanmail.net</option>
					<option value="@daum.net">daum.net</option>
					<option value="@nate.com">nate.com</option>
					<option value="@yahoo.co.kr">yahoo.co.kr</option>
					<option value="@paran.com">paran.com</option>
					<option value="@empal.com">empal.com</option>
					<option value="@gmail.com">gmail.com</option>
					<option value="@hotmail.com">hotmail.com</option>
					<option value="@netian.com">netian.com</option>
					<option value="@dreamwiz.com">dreamwiz.com</option>
					<option value="@hanmir.com">hanmir.com</option>
					<option value="@lycos.co.kr">lycos.co.kr</option>
					<option value="@korea.com">korea.com</option>
					<option value="@chollian.net">chollian.net</option>
					<option value="@orgio.net">orgio.net</option>
					<option value="@hanafos.com">hanafos.com</option>
					<option value="@kornet.net">kornet.net</option>
			</select> </td>
							</tr>
							
							</tbody>
						</table>
										<input type = "button" value = "메인으로.." onclick = 
											"javascript:window.location = 'main.do'">
										<input type = "button" value = "찾기" onClick="searchPw(this.form);">
					</form>
				</div>

	
		</div>

	</div>
</div>
 </body>
</html>
