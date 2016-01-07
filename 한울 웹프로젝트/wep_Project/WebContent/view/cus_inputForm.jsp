<%@ page contentType="text/html; charset=utf-8"%>

<html>
<head>
<title>회원가입</title>
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
<script>
	$(document).ready(
			function() {
				var count = 0;
				$('#confirmId').on(
						'click',
						function() {
							
							var uid = $('#id').val();
							if (uid == '') {
								alert('아이디를 입력하세요!');
								$('#id').focus();
								return;
							}

							$.getJSON('view/confirmId2.jsp', {
								id : uid
							}, function(data) {

								if (data.id == uid) {//아이디 중복
									count = 0;
									$('.condition-id').html('이미 등록된 아이디').css(
											'color', 'red');
									$('#id').val('').focus();
								} else {
									count = 1; //중복 확인 작업을 수행하면 count를 1로 변경
									$('.condition-id').html('사용 가능한 아이디').css(
											'color', 'black');
								}
							});
						});

				//submit이벤트 처리
				$('#insert_form').submit(function() {
					//기본 이벤트 제거
					event.preventDefault();

					if (count != 1) {
						alert('아이디 중복 체크 필수!');
						$('#id').focus();
						return;
					}

				});

			});
</script>

<script>
	$(document)
			.ready(
					function() {

						$('.id_01').keyup(function() {
							var id = $('.id_01').val();
							var id_if = /^[a-z0-9]{4,12}$/;

							if (id_if.test(id)) {
								$('.condition-id').html('중복 확인이 필요합니다');
								$('.condition-id').css('color', 'red');
								$('#confirmId').attr('disabled', false);
							} else {
								$('.condition-id').html('아이디는 4자 이상이여야 합니다');
								$('.condition-id').css('color', 'red');
								$('#confirmId').attr('disabled', true);
							}
						});

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
																'6~15자의 영문 대소문자, 숫자, 특수문자 2개 이상을 사용해주세요. 연속된 숫자 문자(4개 이상)는 제한합니다.');
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
<script>
	$(document).ready(
			function() {
				var c = 0;
				var userinput = eval("document.userinput");
				$('.submit').click(

						function() {
							var id = $('.pw_01').val();
							var id2 = $('.pw_02').val();
							if (!userinput.id.value) {
								alert("아이디를 입력하세요");
								return false;
							}

							else if (!userinput.passwd.value) {
								alert("비밀번호를 입력하세요");
								return false;
							} else if (id != id2) {
								alert("비밀번호가 일치하지 않습니다.");
								return false;
							} else if (!userinput.name.value) {
								alert("이름입력하세요");
								return false;
							} else if (!userinput.phone1.value
									|| !userinput.phone2.value
									|| !userinput.phone3.value) {
								alert("폰번호입력하세요");
								return false;
							}else if (!userinput.email.value) {
								alert("이메일을 입력하세요");
								return false;
							}
							else if (!userinput.zipcode.value
									||!userinput.address.value
									|| !userinput.detail_address.value) {
								alert("주소를 입력하세요");
								return false;
							}
							

							else if ($('.service').is(':checked')
									&& $('.privacy').is(':checked')) {
								document.userinput.action = "cus_insert.do";
								document.userinput.submit();
							} else {
								alert("이용약관과 개인정보 수집 및 이용에 대한 안내 모두 동의해주세요.")
								return false;
							}

						});

			});
</script>



<style>
.h4>h4 {
	margin: 10px 0px
}

.comfirm_01 {
	border: 1px solid orangered;
}
</style>

<!-- ajax에서는 id를 중요시함 -->

<form method="post" id="insert_form" name="userinput">

	<table width="520px" style="margin: auto"" >

		<tr>
			<td colspan="2" height="39" bgcolor="#fff"><font size="+1"
				color="black"><h2>회원가입</h2></font></td>
		</tr>
		<tr>
	<td >회원 종류</td>
	<td ><input type="radio" name="user_type" value="p" checked /> 개인 <input type="radio" name="user_type" value="c" /> 단체  </td>
</tr>
		<tr height="90px">
			<td colspan="2"><input
				style="width: 75%; height: 38px; " value=""
				placeholder="사용자 ID" type="text" name="id" id="id" maxlength="12"
				class="id_01"> <input
				style="width: 20%; height: 38px; ; background: #fff; color: orangered"
				type="button" id="confirmId" value="중복체크" class="comfirm_01 ">
				<div class="possible-id possible-box"></div>
				<div class="condition-id condition-box" style="font-size: 13px"></div>
			</td>
		</tr>
		<tr>
			<td colspan="2"><input
				style="width: 96%; height: 38px; ;" value=""
				placeholder="비밀번호" type="password" name="passwd" maxlength="12"
				class="pw_01"> <input
				style="width: 96%; height: 38px;" value=""
				placeholder="비밀번호 확인" type="password" name="passwd2" maxlength="12"
				class="pw_02">
				<div class="possible-pw possible-box"></div>
				<div class="condition-pw condition-box" style="font-size: 13px"></div>
			</td>
		</tr>

		<tr height="90px">
			<td colspan="2"><input
				style="width: 96%; height: 38px; "
				placeholder="이름" type="text" name="name" maxlength="10"></td>
		</tr>

		<!-- <tr>
<td style="color:#bdbdbd;" colspan="2" >주민등록번호</td>
</tr>
<tr>
	
	<td colspan="2">
		<input type = "text" name = "jumin1" size = "7" maxlength = "6" style="width:46%; height:30px; line-height:38px;"> - 
		<input type = "text" name = "jumin2" size = "7" maxlength = "7" style="width:46%; height:30px; line-height:38px;">
		<br><br>
	</td>
</tr> -->
		<tr>
			<td style="color: #bdbdbd;" colspan="2">전화번호</td>
		</tr>

		<tr>

			<td colspan="2" style="padding: 0px;"><select name="phone1"
				style="width: 20%; height: 30px; ;">
					<option value="010">010</option>
					<option value="016">016</option>
					<option value="011">011</option>
					<option value="019">019</option>
			</select> - <input type="text" name="phone2" maxlength="4"
				style="width: 33%; height: 30px; ; margin: 1px;">
				- <input type="text" name="phone3" maxlength="4"
				style="width: 33%; height: 30px; ; margin: 1px;">
				<br> <br></td>
		</tr>
		<tr>
		<tr height="60px" style="margin-bottom: 50px">
			<td colspan="2"><input
				style="width: 45%; height: 35px; "
				placeholder="이메일 아이디" type="text" name="email" maxlength="30">
				@ <select type="text"
				style="width: 45%; height: 38px; " maxlength="30"
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
			</select> <br> <br></td>
		</tr>
		<!-- 우편번호 -->
		<tr height="90px">

			<td colspan="2"><input
				style="width: 40%; height: 45px; line-height: 45px;" type="text"
				name="zipcode" id="sample6_postcode" placeholder="우편번호"> <input
				style="width: 20%; height: 45px; line-height: 45px;" type="button"
				onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
				<input style="width: 40%; height: 45px; line-height: 45px;"
				type="text" name="address" id="sample6_address" placeholder="주소">
				<input style="width: 55%; height: 45px; line-height: 45px;"
				type="text" name="detail_address" id="sample6_address2"
				placeholder="상세주소"></td>
		</tr>


		<tr>
			<td class="h4">
				<h4>
					<input type="checkbox" class="service">이용약관 동의</br>
				</h4>
				<h4>
					<input type="checkbox" class="privacy">개인정보 수집 및 이용 동의 </br>
				</h4> <br> <br>
			</td>
		</tr>






		<tr height="10px">
			<td  colspan="2" style="text-align: center"><input
				style="width: 35%; background: orangered; height: 55px; font-size: 20px; color: white; font-weight: bold"
				class="submit" type="submit" name="confirm" value="등  록")
		>
			</td>
		</tr>
	</table>
</form>
</body>
</html>