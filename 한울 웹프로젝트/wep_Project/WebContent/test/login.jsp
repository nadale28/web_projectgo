<%@ page contentType = "text/html; charset=utf-8" %>


<html>
<head><title>회원정보수정</title></head>
<link href = "cus_style.css" rel = "stylesheet" type = "text/css">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
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
<script language = "javascript">
 
	function checkIt()
	{
		var userinput = eval("document.userinput");

		if(!userinput.passwd.value)
		{
			alert("비밀번호를 입력하세요.");
			return false;
		}

		if(userinput.passwd.value != userinput.passwd2.value)
		{
			alert("비밀번호를 동일하게 입력하세요.");
			return false;
		}

		if(!userinput.username.value)
		{
			alert("사용자 이름을 입력하세요.");
			return false;
		}

		if(!userinput.jumin1.value || !userinput.jumin2.value)
		{
			alert("주민등록번호를 입력하세요.");
			return false;
		}
	}


//아이디 중복 여부를 판단
function openConfirm()
{//수정시 알림창 띄어주기 
	// url과 사용자 입력 아이디를 조합합니다.
	url = "cus_modifyPro.do";
	
	// 새로운 윈도우를 엽니다.
	open(url, "post", 
		"toolbar = no, location = no, status = no," + 
		"menubar = no, scrollbars = no, resizable = no," +
		"width = 300, height = 200");
}
</script>

<%
	String id = (String)session.getAttribute("memId");
	try
	{
%>
<style>

.bottom{margin-top:100px}
</style>


<body bgcolor = "#fafafa">
<form method = "post" action = "cus_modifyPro.do" name = "userinput" 
	onSubmit = "return checkIt()">
<table bgcolor="#fff"
	style="border-left:1px solid #eaeaea;
	border-right:1px solid #eaeaea;
	border-top:1px solid #eaeaea;
	border-bottom:1px solid #eaeaea;"
	border="0"
	width = "600" height="75"  cellspacing = "8" 
	cellpadding = "10" align = "center"  >
<tr ><td colspan="2"><h1   style="text-align:center">회원정보 수정</h1></td></tr>
<tr style=" border-top:1px solid #eaeaea;
	border-bottom:1px solid #eaeaea;" >
	<td style=" padding: 10px 0 10px 12px; font-family: '돋움',dotum,sans-serif; font-size:12; background:#f4f4f6;">&nbsp;&nbsp;사용자 ID</td>
	<td >${memberinfo.id}</td>
</tr>
<tr>
	<td  style=" padding: 10px 0 10px 12px; font-family: '돋움',dotum,sans-serif; font-size:12; background:#f4f4f6;">&nbsp;&nbsp;새 비밀번호</td>
	<td>
		<input 
			style="width:167px; height:120%" 
			type = "password" name = "passwd" size = "10"
			maxlength = "10" value = "${memberinfo.passwd}">
		<span style="font-size:9; color:#bdbdbd;">아무거나 입력하세요 </span>
		</td>
</tr>
<tr>
	<td style="padding: 10px 0 10px 12px; font-family: '돋움',dotum,sans-serif; font-size:12; background:#f4f4f6;" >&nbsp;&nbsp;새 비밀번호 확인</td>
	<td >
		<input
			style="width:167px; height:120%" 
			type = "password" name = "passwd2" size = "10"
			maxlength = "10" value = "${memberinfo.passwd}">
		<span style="font-size:9; color:#bdbdbd;">설정하신 비밀번호를 한번 더 입력해주세요.</span>
	</td>
</tr>
<tr>
	<td style="  padding: 10px 0 10px 12px; font-family: '돋움',dotum,sans-serif; font-size:12; background:#f4f4f6;" >&nbsp;&nbsp;연락처
   <td >
      <select name = "phone1" style="width:83px; height:22">
          <option value="010">010</option>
          <option value="016">016</option>
          <option value="011">011</option>
          <option value="019">019</option>
      </select> - 
      <input 
      	style="width:83px; height:22"
      	type = "text" name = "phone2"  maxlength = "4" size = "10"> -
      <input 
      	style="width:83px; height:22"
      	type = "text" name = "phone3"  maxlength = "4" size = "10">
      <br>
      <br>
   </td>
</tr>
<tr>
	<td style="padding: 10px 0 10px 12px; font-family: '돋움',dotum,sans-serif; font-size:12; background:#f4f4f6;">&nbsp;&nbsp;사용자 이름</td>
	<td >
		${memberinfo.name}
	</td>
</tr>
<tr>
	<td style="padding: 10px 0 10px 12px; font-family: '돋움',dotum,sans-serif; font-size:12; background:#f4f4f6;">&nbsp;&nbsp;주민등록번호</td>
	<td >
		${memberinfo.jumin1} - ${memberinfo.jumin2}
	</td>
</tr>
<tr>
	<td style=" padding: 10px 0 10px 12px; font-family: '돋움',dotum,sans-serif; font-size:12; background:#f4f4f6;">&nbsp;&nbsp;E-Mail</td>
	<td >${memberinfo.email}
		 	<c:if test="${memberinfo.email == null}" >
		
		<input type = "text" name = "email" size = "20" maxlength = "30" height:120%> @ 
		</c:if>
			
			<c:if test =" ${memberinfo.email != null}">
		<input type = "text" name = "email" size = "20" maxlength = "30" height:120%
		
			value = "${memberinfo.email}" >
		</c:if>
		
	</td>
</tr>
<tr>
	<td style=" padding: 10px 0 10px 12px; font-size:12; font-family: '돋음',dotum,sans-serif; background:#f4f4f6;">&nbsp;&nbsp;주소</td>
	<td >
<input 
	style="width:133px;"
	type="text" name="zipcode"id="sample6_postcode" placeholder="우편번호"
	value="${memberinfo.zipcode }">
<input 
	type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"> <br>
<input 
	style="width:133px;"
	type="text" name="address" id="sample6_address" placeholder="주소" 
	value="${memberinfo.address }">
<input
	style="width:133px;" 
	type="text" name="detail_address" id="sample6_address2" placeholder="상세주소" 
	value="${memberinfo.detail_address }">
	
	</td>
</tr>

<tr class="bottom"  style="height:140px">
	<td colspan = "2" align = "center"  >
		<input 
			style="width:160px; height:53px; font-size:12; font-family: '돋움',dotum,sans-serif;"
			type = "submit" name = "modify" value = "개인정보수정" onclick = "javascript:window.location='cus_main.do'">
		<input
			style="width:160px; height:53px; font-size:12; font-family: '돋움',dotum,sans-serif;" 
			type = "button" value = "취  소"
			onclick = "javascript:window.location='cus_main.do'">
	</td>
</tr>
</table>
</form>
</body>
<%
	}catch(Exception e) {}
%>
</html>