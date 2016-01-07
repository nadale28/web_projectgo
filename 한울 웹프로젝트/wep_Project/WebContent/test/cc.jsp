<%@ page contentType = "text/html; charset=utf-8" %>

<html>
<head><title>회원가입</title></head>
<link href = "cus_style.css" rel = "stylesheet" type = "text/css">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
  <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
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
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
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
	$(document).ready(function(){

	$('.id_01').keyup(function(){
			var id = $('.id_01').val();
			var id_if = /^[a-z0-9]{6,12}$/;  
			

			 if(id_if.test(id)){
				$('.condition-id').html('중복 확인이 필요합니다');
				$('.condition-id').css('color','red');
			}
			else{
				$('.condition-id').html('6~12자의 영문 소문자, 숫자만 사용 가능합니다.');
				$('.condition-id').css('color','red');
			}
		});

		$('.pw_01').keyup(function(){
			var id = $('.pw_01').val();
			var id_if = /^[a-z0-9]{6,12}$/;  
			

			 if(id_if.test(id)){
				$('.condition-pw').html('6~15자의 영문 대소문자, 숫자, 특수문자 2개 이상을 사용해주세요. 연속된 숫자 문자(4개 이상)는 제한합니다.');
				$('.condition-pw').css('color','red');
			}
			else{
				$('.condition-pw').html('6~15자의 영문 대소문자, 숫자, 특수문자 2개 이상을 사용해주세요. 연속된 숫자 문자(4개 이상)는 제한합니다.');
				$('.condition-pw').css('color','red');
			}
		});

	});
  </script>
  
  <script language = "javascript">

	function checkIt()
	{
		var userinput = eval("document.userinput");
		
		if(!userinput.id.value)
		{
			alert("아이디를 입력하세요");
			return false;
		}
		
		else if(!userinput.passwd.value)
		{
			alert("비밀번호를 입력하세요");
			return false;
		}
		
		else if(userinput.passwd.value != userinput.passwd2.value)
		{
			alert("주민등록번호를 입력하세요");
			return false;
		}
		else{alert("회원가입을 축하 드립니다.");
		location.href="cus_insert.do";
		document.userinput.submit();}
	}
	
	// 아이디 중복 여부를 판단
	function openConfirmid(userinput)
	{
		// 아이디를 입력했는지 검사
		if(userinput.id.value == "")
		{
			alert("아이디를 입력하세요");
			return;
		}
		
		// url과 사용자 입력 아이디를 조합합니다.
		url = "cus_confirmId.do?id=" + userinput.id.value;
		
		// 새로운 윈도우를 엽니다.
		open(url, "confirm", 
			"toolbar = no, location = no, status = no," + 
			"menubar = no, scrollbars = no, resizable = no," +
			"width = 300, height = 200");
	}


	
</script>


 <style>
.h4 >h4{margin:10px 0px}
.comfirm_01{border:1px solid orangered;}

 </style>

 <body >
<form method = "post" action = "cus_insert.do" name = "userinput">
<table width = "520px"  style="margin:auto"" >
	
<tr>
	<td colspan = "2" height = "39" 
		bgcolor = "#fff">
	<font size = "+1" color="black"><h2>회원가입</h2></font></td>
</tr>
<tr height="90px"  >
	<td colspan = "2" >
		<input style="width:75%; height:38px; line-height:38px;"  value="" placeholder="사용자 ID" type = "text" name = "id" maxlength = "12" class="id_01">
		<input style="width:20%; height:38px; line-height:38px; background:#fff;	 color:orangered" type = "button" name = "confirm_id" value = "중복확인"
			onclick = "openConfirmid(this.form)"   class="comfirm_01 ">
		<div class="possible-id possible-box"></div>		
		<div class="condition-id condition-box" style="font-size:13px"></div>
	</td>
</tr>
<tr>
	<td colspan="2">
		<input style="width:96%;  height:38px; line-height:38px;" value="" placeholder="비밀번호" type = "password" name = "passwd"  maxlength = "12"   class="pw_01">
		<input style="width:96%; height:38px; line-height:38px;" value="" placeholder="비밀번호 확인" type = "password" name = "passwd2" maxlength = "12"  class="pw_01">
		<div class="possible-pw possible-box"></div>		
		<div class="condition-pw condition-box" style="font-size:13px"></div>
	</td>
</tr>

<tr height="90px">
	<td colspan="2">
		<input style="width:96%;  height:38px; line-height:38px;"  placeholder="이름" type = "text" name = "name"  maxlength = "10">
	</td>
</tr>

<tr>
<td style="color:#bdbdbd;" colspan="2" >주민등록번호</td>
</tr>
<tr>
	
	<td colspan="2">
		<input type = "text" name = "jumin1" size = "7" maxlength = "6" style="width:46%; height:30px; line-height:38px;"> - 
		<input type = "text" name = "jumin2" size = "7" maxlength = "7" style="width:46%; height:30px; line-height:38px;">
		<br><br>
	</td>
</tr>
<tr >
	<td style="color:#bdbdbd;" colspan="2">전화번호</td>
</tr>

<tr>
   
   <td colspan="2" style="padding:0px; ">
      <select name = "phone1" style="width:20%; height:30px; line-height:38px; ">
          <option value="010">010</option>
          <option value="016">016</option>
          <option value="011">011</option>
          <option value="019">019</option>
      </select> - 
      <input type = "text" name = "phone2"  maxlength = "4" style="width:33%; height:30px; line-height:38px; margin:1px;"> -
      <input type = "text" name = "phone3"  maxlength = "4" style="width:33%; height:30px; line-height:38px; margin:1px;">
      <br>
      <br>
   </td>
</tr>
<tr>

<tr height="60px" style="margin-bottom:50px">
	<td colspan="2">
		<input style="width:45%;  height:35px; line-height:38px;" placeholder="이메일 아이디" type = "text" name = "email"  maxlength = "30"> @
		
		<select  type="text" style="width:45%;  height:38px; line-height:38px;"  maxlength = "30" placeholder="이메일"   >
				<option value="">직접입력 </option>	
				  <option value="naver.com" >naver.com</option>
				  <option value="hanmail.net" >hanmail.net</option>
				  <option value="daum.net" >daum.net</option>
				  <option value="nate.com" >nate.com</option>
				  <option value="yahoo.co.kr" >yahoo.co.kr</option>
				  <option value="paran.com" >paran.com</option>
				  <option value="empal.com" >empal.com</option>
				  <option value="gmail.com" >gmail.com</option>
				  <option value="hotmail.com" >hotmail.com</option>
				  <option value="netian.com" >netian.com</option>
				  <option value="dreamwiz.com" >dreamwiz.com</option>
				  <option value="hanmir.com" >hanmir.com</option>
				  <option value="lycos.co.kr" >lycos.co.kr</option>
				  <option value="korea.com" >korea.com</option>
				  <option value="chollian.net" >chollian.net</option>
				  <option value="orgio.net" >orgio.net</option>
				  <option value="hanafos.com" >hanafos.com</option>
				  <option value="kornet.net" >kornet.net</option>
				</select>

		
		<br><br>
	</td>
</tr>
	<!-- 우편번호 -->
	<tr height="90px">

	<td colspan="2">
<input style="width:40%; height:45px; line-height:45px;" type="text" name="zipcode"id="sample6_postcode" placeholder="우편번호">
<input style="width:20%; height:45px; line-height:45px;" type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
<input style="width:40%; height:45px; line-height:45px;" type="text" name="address" id="sample6_address" placeholder="주소">
<input style="width:55%; height:45px; line-height:45px;" type="text" name="detail_address" id="sample6_address2" placeholder="상세주소">
	
	</td>
</tr>


<tr  >
	<td class="h4">
		   <h4><input type="checkbox" >만 14세 이상입니다  </br> </h4> 
		 <h4><input type="checkbox">이용약관 동의</br> </h4>
		<h4><input type="checkbox" >개인정보 수집 및 이용 동의 </br></h4>
		<br><br>
	</td>
</tr>

	



	
<tr height="10px"  >
	<td  style="text-align:center" >
		<input style="width:35%; background: orangered; height:55px; font-size:20px; color:white; font-weight:bold" type = "button" name = "confirm" value = "등  록" onClick="checkIt()" 
		>
	</td>
</tr>
</table>
</form>
</body>
</html>