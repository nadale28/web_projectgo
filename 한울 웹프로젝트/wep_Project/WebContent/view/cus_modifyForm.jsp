<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="../view/cus_color.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title>회원정보수정</title>
</head>
<link href="cus_style.css" rel="stylesheet" type="text/css">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script>
	function sample6_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 각 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var fullAddr = ''; // 최종 주소 변수
						var extraAddr = ''; // 조합형 주소 변수

						// 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
						if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
							fullAddr = data.roadAddress;

						} else { // 사용자가 지번 주소를 선택했을 경우(J)
							fullAddr = data.jibunAddress;
						}

						// 사용자가 선택한 주소가 도로명 타입일때 조합한다.
						if (data.userSelectedType === 'R') {
							//법정동명이 있을 경우 추가한다.
							if (data.bname !== '') {
								extraAddr += data.bname;
							}
							// 건물명이 있을 경우 추가한다.
							if (data.buildingName !== '') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
							fullAddr += (extraAddr !== '' ? ' (' + extraAddr
									+ ')' : '');
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
						document.getElementById('sample6_address').value = fullAddr;

						// 커서를 상세주소 필드로 이동한다.
						document.getElementById('sample6_address2').focus();
					}
				}).open();
	}
</script>
<script language="javascript">
	function checkIt() {
		var userinput = eval("document.userinput");

		if (!userinput.passwd.value) {
			alert("비밀번호를 입력하세요.");
			return false;
		}


		if (!userinput.username.value) {
			alert("사용자 이름을 입력하세요.");
			return false;
		}

		if (!userinput.jumin1.value || !userinput.jumin2.value) {
			alert("주민등록번호를 입력하세요.");
			return false;
		}
	}

	
</script>
<script>
	$(document)
			.ready(
					function() {


						$('.pw_01, .pw_02')
								.keyup(
										function() {
											var id = $('.pw_01').val();
											var id_if = /^[a-z0-9]{6,15}$/;
											var id2 = $('.pw_02').val();
											if (id_if.test(id)) {

												$('.condition-pw').html(
														'적합한 비밀번호 입니다. 비밀번호를 확인해주세요.');
												$('.condition-pw').css('color',
														'blue');

											} else {
												$('.condition-pw')
														.html(
																'6~15자의 영문 대소문자, 숫자를 사용해주세요. 연속된 숫자 문자(4개 이상)는 제한합니다.');
												$('.condition-pw').css('color',
														'red');
											}

											if (id_if.test(id2)&&id == id2) {
												$('.condition-pw').html(
														'비밀번호가 일치합니다.');
												$('.condition-pw').css('color',
														'blue');
											} else if (id_if.test(id2)&&id != id2) {
												$('.condition-pw').html(
														'비밀번호가 일치하지 않습니다.');
												$('.condition-pw').css('color',
														'red');
											}
										});

					});
</script>

<%
	String id = (String) session.getAttribute("memId");
	try {
%>

<body bgcolor="#fafafa">
	<form method="post" action="cus_modifyPro.do" name="userinput"
		onSubmit="return checkIt()">
		<table bgcolor="#fff"
			style="border-left: 1px solid #eaeaea; border-right: 1px solid #eaeaea; border-top: 3px solid black; border-bottom: 1px solid #eaeaea;"
			border="0" width="900" height="45" cellspacing="8" cellpadding="3"
			align="center">
			<tr>
				<td><h1>회원정보 수정</h1></td>
			</tr>
			<tr
				style="border-top: 1px solid #eaeaea; border-bottom: 1px solid #eaeaea;">
				<td
					style="padding: 10px 0 10px 12px; font-family: '돋움', dotum, sans-serif; font-size: 12; background: #f4f4f6;">&nbsp;&nbsp;사용자
					ID</td>
				<td>${memberinfo.id}</td>
			</tr>
			<tr>
				<td 
					style="padding: 10px 0 10px 12px; font-family: '돋움', dotum, sans-serif; font-size: 12; background: #f4f4f6;">&nbsp;&nbsp;새
					비밀번호</td>
				<td><input class="pw_01"  style="width:167px; height:120%" 
			type = "password" name = "passwd" size = "10"
			maxlength = "10" value="${memberinfo.passwd}"> <span class='condition-pw'
					style="font-size: 9; color: #bdbdbd;"></span></td>
			</tr>
			<tr>
				<td 
					style="padding: 10px 0 10px 12px; font-family: '돋움', dotum, sans-serif; font-size: 12; background: #f4f4f6;">&nbsp;&nbsp;새
					비밀번호 확인</td>
				<td><input class="pw_02"  style="width: 167px;" type="password"
					name="passwd2" size="10" maxlength="10"
					value="${memberinfo.passwd}"> </td>
			</tr>
			<tr>
				<td
					style="padding: 10px 0 10px 12px; font-family: '돋움', dotum, sans-serif; font-size: 12; background: #f4f4f6;">&nbsp;&nbsp;연락처
				
				<td><select name="phone1">
						<option value="010">010</option>
						<option value="016">016</option>
						<option value="011">011</option>
						<option value="019">019</option>
				</select> - <input style="width: 83px;" type="text" name="phone2"
					maxlength="4" value="${memberinfo.phone2 }"> - <input
					style="width: 83px;" type="text" name="phone3" maxlength="4"
					value="${memberinfo.phone3 }"> <br> <br></td>
			</tr>
			<tr>
				<td
					style="padding: 10px 0 10px 12px; font-family: '돋움', dotum, sans-serif; font-size: 12; background: #f4f4f6;">&nbsp;&nbsp;사용자
					이름</td>
				<td > <input
					style="width: 83px;" type="text" name="name" maxlength="4"
					value="${memberinfo.name}"></td>
			</tr>

			<tr>
				<td
					style=" padding: 10px 0 10px 12px; font-family: '돋움',dotum,sans-serif; font-size:12; background:#f4f4f6;">&nbsp;&nbsp;E-Mail</td>
				<td><input type = "text" name = "email" size = "20" maxlength = "30" height:120%
					value="${memberinfo.email}"> @ <select 
					type = "text" name = "email" 
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
				</select></td>
			</tr>
			<tr>
				<td
					style="padding: 10px 0 10px 12px; font-size: 12; font-family: '돋음', dotum, sans-serif; background: #f4f4f6;">&nbsp;&nbsp;주소</td>
				<td><input style="width: 83px;" type="text" name="zipcode"
					id="sample6_postcode" placeholder="우편번호"
					value="${memberinfo.zipcode }"> <input type="button"
					onclick="sample6_execDaumPostcode()" value="우편번호 찾기"> <br>
					<input style="width: 133px;" type="text" name="address"
					id="sample6_address" placeholder="주소"
					value="${memberinfo.address }"> <input
					style="width: 133px;" type="text" name="detail_address"
					id="sample6_address2" placeholder="상세주소"
					value="${memberinfo.detail_address }"></td>
			</tr>

			<tr>
				<td colspan="2" align="center" bgcolor="<%=value_c%>"><input
					style="width: 160px; height: 53px; font-size: 12; font-family: '돋움', dotum, sans-serif;"
					type="submit" name="modify" value="개인정보수정"> <input
					style="width: 160px; height: 53px; font-size: 12; font-family: '돋움', dotum, sans-serif;"
					type="button" value="취  소"
					onclick="javascript:window.location='main.do'"></td>
			</tr>
		</table>
	</form>

</body>
<%
	} catch (Exception e) {
	}
%>
</html>