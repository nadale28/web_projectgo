<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
  <script type="text/javascript" src="js/jquery.mousewheel.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="css/ih_style.css" rel="stylesheet" />
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
	$('.header_nav_btn').hover(function(){
		//alert('hey');
		$('.donation_box').animate({
			left:0 
		},'slow');
	
	});

	$('.donation_box').mouseleave(function(){
		
		$('.donation_box').animate({
			left:-1000
		},'slow');
	});
});
</script>

<script>
	$(document).ready(function(){
	
		$('.t_don').click(function(){
			//alert('d');
			$('.wrap').css({
				'display' : 'block',
				'z-index' : '9999999',
				'box-shadow' : 'rgba(0,0,0,0.5) 0 0 0 9999px'
			});
			//$('.pages').css('background','black');
		});

		$('.m_don').click(function(){
			//alert('d');
			$('.container1').css({
				'display' : 'block',
				'z-index' : '99999991',
				'box-shadow' : 'rgba(0,0,0,0.5) 0 0 0 9999px'
			});
			//$('.pages').css('background','black');
		});


		$('#sponsor_paykind1').click(function(){
		// alert('a');
		 $('.payinfo1').css('display','block');
			$('.payinfo2').css('display','none');
		});
		$('#sponsor_paykind2').click(function(){
		 //alert('a');
		 $('.payinfo1').css('display','none');
			$('.payinfo2').css('display','block');
		});
	


	$('#tax_yes').click(function(){

		$('.tax_yes_1').css('display','block');
	});

	$('#tax_no').click(function(){
	
			$('.tax_yes_1').css('display','none');

		});

		



	$('#button').click(function(){

		$('.container1').css('display','block');
		});
	
	$('#pop').click(function(){
		$('.container1').css('display','none');
		});

	$('#tpop').click(function(){
		$('.wrap').css('display','none');
		});



    $('.junggi').click(function(){
			
			if($('#m_name').val()==''){
				alert("이름을 입력해주세요");
		
			}else if($('#mail1').val()==' ' &&  $('#mail2').val()==' '){
				alert("이메일을 입력해주세요");

			}else{ 
				alert('감사합니다.');
			}
	});

	$('.cancel').click(function(){
			$(location).attr('href','templog.html');
	});

	$('.container1').draggable();
});
  </script>
<style>
	.top{position:relative;  width:100%; height:80px; background:brown; color:white; }
	.top_logo {position:absolute; top:0; bottom:0; left:5%; width:20%; height:100%; margin:auto 0;}
	.top_logo > p {height:100%; margin:0; line-height:80px}
	.top_nav {position:absolute; top:0; bottom:0; right:5%;   width:20%; height:100%; margin:auto 0;}
	.top_nav > p {float:left; margin-right:5%; height:100%; margin-top:0; margin-bottom:0; line-height:80px}
	.top_nav > .lp {float:left; margin-right:0; height:100%; margin-top:0; margin-bottom:0; line-height:80px}
	.top_img {width:100%; height:20%; position:absolute; top:10%;}
	.top_img > img {width:100%; }
	

	.header {width:100%; position:absolute;  top:0; height:80px; left:0; right:0; color:white; background:#593220;}
	.header > * {vertical-align:center; line-height:80px;}
	.header > .header_nav_btn {float:left; margin-left:5%;}
	.header > .header_campaign {float:left; margin-left:2%}
	.header > .header_nav {float:right; margin-right:5%; }
	.header > .header_nav > span > * {color:white;}

	.donation_box{position:fixed; left:-500px; height:100%; width:23%; overflow:hidden; -webkit-transform: translateZ(0);
		 background:brown; top:0;}
	.donation_box > .link_wrap{margin-top:75%; }
	.donation_box > .link_wrap > a {font-size:30px; color:white;line-height:45px; text-align:left;
	margin-left:20% }

	.nav_btn {position:fixed; top:45%; left:2%; font-size:50px; color:white; font-weight:bold;}

	.donation_box > span{width:24.4%; display:inline-block; text-align:left; vertical-align:middle;
	  height:100%; line-height:auto; border-left:1px solid white;}
	.lspan {border-right:1px solid white;}
	.donation_box > span > .d_btn{ vertical-align:middle; color:white;
	font-size:18px; font-weight:bold; line-height:50px; }


	.footer{width:100%; height:60px; position:absolute; margin:0 auto; margin-top:40px; bottom:0; left:0; right:0; }
	.footer > * {margin-left:15%;}
	.fmenu{position:absolute; top:0; left:0; padding:0;margin-top:0;margin-bottom:0; }
	.fm_list{float:left; list-style:none; padding-right:13px;}

	.faddress{position:absolute; top:30px; left:0; font-size:13px;}
	.faddress > p {padding-top:0;padding-bottom:0;margin:0;height:16px;line-height:16px;}
	.faddr_f{padding-right:10px; float:left;}
	.faddr_p{padding-right:10px; float:left;}
	.faddr_l{float:left;}
</style>
</head>
<body>
<div class="header" >
			<p class="header_nav_btn">메뉴</p>
			<h4 class="header_campaign">캠페인</h4>
			<div class="header_nav">
				<%
			if(session.getAttribute("memId")==null){
				
			
			%>
				<span><a href="cus_loginform.do">로그인</a></span>&nbsp;&nbsp;
				<%} else{%>
				<span><a href="cus_logout.do">로그아웃</a></span>&nbsp;&nbsp;
				<span><a href="cus_modify.do?id=<%= session.getAttribute("memId") %>">마이페이지</a></span>&nbsp;&nbsp;
				<% } %>
				<span><a href="cus_inputForm.do">회원가입</a></span>
			</div>
	  </div>

	<div class="donation_box">
			<div class="link_wrap">
				<a class="m_don">정기후원하기</a>	<br>
				<a class="t_don">일시후원하기</a>	<br>
				<a href="list.do">게시판</a> <br>
				<a href="main.do">메인으로</a>
			</div>
		</div>	

		<div class="footer">
			 <ul class="fmenu">
				 <li class="fm_list"><a href="">개인정보취급방침</a></li>
				 <li class="fm_list"><a href="">아동보호정책</a></li>
				 <li class="fm_list"><a href="">이용약관</a></li>
				 <li class="fm_list"><a href="">이메일 무단 수집거부</a></li>
			 </ul>
			 <div class="faddress">
				 <p class="faddr_f">광주광역시 남구 주월동</p><p class="faddr_p">집주인 김인환</p>
				 <p class="faddr_p">사업자번호 000-11-222222</p><p class="faddr_p">대표전화 010-111-1234</p>
				 <p class="faddr_l">상담시간 (월~금)09:00~18:00 (토)09:00~12:00</p>
			 </div>
	</div>

<div class="wrap">
	<div>
		<img style="height: 25px; width: 25px;" src="img/popup_btn_close.png" class="tpop"; id="tpop"> 
	</div>
			  <h2 class="title">후원하기</h2>
			  <div>
						<span  class="num">
						<img style="height: 18px; width: 18px;" src="img/1_02.gif">
						<img style="height: 15px; width: 15px;" src="img/right.gif">
						<img style="height: 18px; width: 18px;" src="img/2.gif">
						</span>
				</div>
				<div class="container">
					<div class="wrap_01">
            			<h3>후원신청 내역</h3>
						<div class="form">
							<table class="tb_info-box" cellpadding="0" width="50%">
								<tbody style="vartical-align:0">	
									<tr>
									  <td>프로젝트명</td>
									  <td>시리아 난민아동 돕기</td>
								  </tr>
								  <tr>
									  <td>후원방식</td>
									  <td>일시후원</td>
								  </tr>
								</tbody>
							</table>

							<table class="tb_info-box_01" cellpadding="0" width="50%" >
								<tbody>
								  <tr>
									   <td>후원내역</td>
									    <td>영아키트</td>
								  </tr>
								  <tr>
									 <td>후원금액</td>
									 <td>100,000원</td>
								  </tr>
								</tbody>
							</table>
						</div>
					</div>
				

                   
                   
					<div class ="wrap_02">     
						<h3>후원자 정보&emsp;<input type="checkbox" name="nomem_gb" id="nomem_gb" value="Y" onclick="noname_Check(this.checked)" /><label for="nomem_gb"><span style="font:normal 12px '돋움';color:Black;">무기명 후원</span></label></h3>
                    
						<p id="noname_txt" style="line-height:18px;display:none;">
							* 무기명 후원 시, 후원자 정보가 없어 추후 <span class="b u">후원내역 확인 및 소득공제가 불가능</span>합니다.<br />
							* 응원메시지는 익명으로 등록됩니다.<br />
						</p>
                        <table class="tb_info-box form-tb"  cellpadding="0" width="50%">
 
                            <tbody>
                                <tr>
                                    <td>구분</td>
                                    <td class="pt3 pb12">
                          	            <span style="width:50px;"><input type="radio" name="membertype" id="membertype_1" value="1" onclick="alert_Show();" checked /><label for="membertype_1">개인</label></span>
                                        <span style="width:50px;"><input type="radio" name="membertype" id="membertype_2" value="3" onclick="alert_Show();" /><label for="membertype_2">단체</label></span>
                          	            <input type="radio" name="membertype" id="membertype_3" value="4" onclick="alert_Show();" /><label for="membertype_3">기업/교회</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>휴대전화</td>
                                    <td >
                                        <select name="hp1" id="hp1" style="width:60px;" onchange="sponsor.WVMemberCheck();" >
                          	                <option value="010">010</option>
                                            <option value="011">011</option>
                                            <option value="016">016</option>
                                            <option value="017">017</option>
                                            <option value="018">018</option>
                                            <option value="019">019</option>
                                        </select>
                                        <em class="fc898989">-</em>
                                        <input type="text" name="hp2" id="hp2" style="width:50px;" maxlength="4" value="" onkeyup="this.value=getOnlyNumeric(this.value);" onblur="this.value=getOnlyNumeric(this.value);sponsor.WVMemberCheck();" />
                                        <em class="fc898989">-</em>
                                        <input type="text" name="hp3" id="hp3" style="width:50px;" maxlength="4" value="" onkeyup="this.value=getOnlyNumeric(this.value);" onblur="this.value=getOnlyNumeric(this.value);sponsor.WVMemberCheck();" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="tb_info-box form-tb"  cellpadding="0" width="50%">

                            <tbody>
                                <tr>
                                    <td>이름</td>
                                    <td><input type="text" name="user_nm" id="user_nm" style="width:193px;" value="" onblur="sponsor.WVMemberCheck();" /></td>
                                </tr>
                                <tr>
                                    <td>주민등록번호</td>
                                    <td>
                                        <input class="jumin1" type="text" id="jumin1" name="jumin1" maxlength="6" style="ime-mode:disabled;width:80px;"  />
										 -
										 <input class="jumin2" type="text" id="jumin2" name="jumin2" maxlength="7" style="ime-mode:disabled;width:80px;"   />
									</td>
                                </tr>
                            </tbody>
                        </table> 
					</div>
					
                 
                        
                   
                
					
					<div class="wrap_03">
						<div class="form" id="donatorinfo3">
                        
                        <table class="tb_info-box form-tb_01" cellpadding="0" width="50%">

                            <tbody>
                                <tr>
                                    <td>성별</td>
                                    <td class="pt3 pb12">
                          	            <span style="width:77px;"><input type="radio" name="sex" id="sex_1" value="1" checked /><label for="sex_1">남자</label></span>
                          	            <input type="radio" name="sex" id="sex_2" value="2" /><label for="sex_2">여자</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="pb13" style="line-height:17px">국세청 연말정산<br />간소화 서비스</td>
                                    <td class="pt10">
                          	            <span style="width:80px;"><input type="radio" name="receipt_yn" id="receipt_yn_1" value="Y" onclick="receiptinfo_Show(this.value)" /><label for="receipt_yn_1">등록함</label></span>                            
                          	            <input type="radio" name="receipt_yn" id="receipt_yn_2" value="N" onclick="receiptinfo_Show(this.value)" checked /><label for="receipt_yn_2">등록 안 함</label>
                                    </td>
                                </tr>
                                <tr id="receiptinfo" style="display:none;">
                                    <td>주민등록번호</td>
                                    <td>
                                        <input type="text" name="jumin_no1" id="jumin_no1" style="width:43px;" maxlength="6" onkeyup="this.value=getOnlyNumeric(this.value);" onblur="this.value=getOnlyNumeric(this.value);" />
                                        <em class="fc898989">-</em>
                                        <input type="password" name="jumin_no2" id="jumin_no2" style="width:49px;" maxlength="7" onkeyup="this.value=getOnlyNumeric(this.value);" onblur="this.value=getOnlyNumeric(this.value);" /><a href="javascript:sponsor.certJumin();" id="name_chk" class="ml6"><img src="/images/OA/bt_self-certification.gif" alt="본인인증" /></a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="tb_info-box form-tb_01" cellpadding="0" width="50%">
                            <tbody>	
                                <tr>
                                    <td width="87px">이메일       </td>
                                    <td><input type="text" name="email" id="email" style="width:193px;" value="" /></td>
                                </tr>

                            </tbody>
                        </table>
                        
						</div>
                </div>  
				 

            	     <h3>후원금 결제 정보</h3>
                    
                   
                        <table class="tb_info-box form-tb_01" cellpadding="0" width="100%" >
                           
	
                            <tbody>
                                <tr>
                                    <th class="pt3">결제방법</th>
                                    <td class="pb12">
                          	            <span style="width:150px;">
										<input type="radio" name="paytype_module" id="paytype_module_1" value="1" checked />
										<label for="paytype_module_1"><img  src="img/kakao.png" class="ml10" style="height:25px;vertical-align:middle;" /></label></span>
                          	            
										<input type="radio" name="paytype_module" id="paytype_module_2" value="2" />
                                        <select name="paytype_cd" id="paytype_cd" class="ml5" onchange="if (this.value != '') { document.getElementById('paytype_module_2').checked=true; }">
                          	                <option value="">선택</option>
                                            <option value="2011">카드결제</option>
                                            <option value="2012">실시간 계좌이체</option>
                                            <option value="2013">핸드폰결제</option>
                                            <option value="2014">무통장입금</option>
                                        </select>
                                    </td>
                                </tr>

                            </tbody>
                        </table>
                   
						
					<div class="ml5_mr10">
						<input type="checkbox" name="agree_cb" id="agree_cb"  /><label for="agree_cb">
						<a href="">이용약관</a>과 
						<a href="">개인정보 취급방침</a>에 동의합니다.</label>
					</div>
					
					<div class="img">
						<img style="height: 40px; width: 120px;" src="img/결제.gif">
					</div>

                
    		    <!--//input_form-->

        </div>
   </div>
   














<div class="container1">
 
	<div>
		<img style="height: 25px; width: 25px;" src="img/popup_btn_close.png" class="pop" ; id="pop"> 
	</div>

	  <form action="payment.do" method="post" class="body">
		<div class="huwan">
					<h2>후원 종류</h2>
					<div class="dona">
						<label> <input type="radio" name="support"  value="1"  checked="checked"   />긴급구호사업, 매월 1만원</label>
						<label>	<input type="radio" name="support" value="2" />긴급구호사업, 매월 2만원</label>
						<label>	<input type="radio" name="support" value="3" />긴급구호사업, 매월 3만원</label>
					</div>
		</div>
			<div class="box">
			   한국월드비전은 16개국가에서 18개의 긴급구호사업(2015년 기준)을 진행 중에 있으며, 월 2만원이면 시리아의 한 아이에게, 월 1만원이면 남수단의 한 가정(5인 기준)에 식량 제공이 가능합니다.
			</div>  

	
		<div class="huwan">
			<h2>후원 신청</h2>
		</div>
	<div class="wrap1">	 
		<div class="left">

			<div class = "d_1">	<div class="d_2"><span class="ff6600">* </span> 이름</div>
				<input class="d_3" type="text" name="name" id="m_name"  maxlength="20" tabindex="101" />  
			</div>
			
			<div class="d_1" ><div class="d_2"><span class="ff6600">*</span> 주민번호</div>
				<div class="d_3">	
				 <input type="text" name="jumin1" style="width:40%;"> -
				  <input type="text" name="jumin2" style="width:40%;">
				 </div>
			</div>

			<div class="phone d_1" ><div class="d_2"><span class="ff6600">*</span> 휴대폰</div>
				<div class="d_3">
				<select class="front_num" name="phone1" id="m_mobile1" tabindex="131" style="width:54px;">
					  <option value="010">010</option>
					  <option value="011">011</option>
					  <option value="016">016</option>
					  <option value="017">017</option>
					  <option value="018">018</option>
					  <option value="019">019</option>
				</select>			 -
					 <input class="mid_num" type="text" id="m_mobile2" name="phone2" maxlength="4" style="ime-mode:disabled;width:53px;" tabindex="132" />
					 -
					 <input class="end_num" type="text" id="m_mobile3" name="phone3" maxlength="4" style="ime-mode:disabled;width:53px;"  tabindex="133" />
				</div>
			</div> 	
			
			<div class="mail d_1"><div class="d_2"><span class="ff6600">*</span> 이메일</div>
				<div class="d_3">
				<span>
				 <input class="front_emil" type="text" name="email1" maxlength="30" id="mail1" style="ime-mode:disabled; width:70px;" tabindex="141" />
				 @
				 <input class ="mid_email" type="text" name="email2" maxlength="30" id="mail2" style="width:90px;ime-mode:disabled; width:70px;" tabindex="142"/>
				<select class="form_select3" name="email_choice" id="email_choice" title="도메인 선택"style="width:100px">
				  <option value="" >직접입력</option>
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
				</span>
				</div>
			</div>
		   
			<div class="addr d_1"><div class="d_2"><span class="ff6600"tabindex="151">*</span> 주소</div>
				<div class="d_3" >
<td colspan="2">
<input type="text" name="zipcode"id="sample6_postcode" placeholder="우편번호">
<input  type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
<input style="width:40%;" type="text" name="address" id="sample6_address" placeholder="주소">
<input  style="width:50%;" type="text" name="detail_address" id="sample6_address2" placeholder="상세주소">
	
	</td>
				 </div>
			</div>

		</div> 	
		<!-- left -->


		<div class="right">
				<div class="pay_01">
					<span class="ff6600">*</span> 결제방법
					<input type="radio" name="accountkind" id="sponsor_paykind1" value="신용카드" tabindex="211" checked="checked" />
					<label>신용카드</label>
					<input type="radio" name="accountkind" id="sponsor_paykind2" value="자동이체" tabindex="212"/>
					<label>자동이체</label>
				</div>
			
		   <!-- 결제정보 - 신용카드 -->
				<div class="payinfo1"   >
					<div >카드명
						  <select name="a_cardcompany"  tabindex="221">
						   <option value="">선택</option>
						   <option value="BC카드" >BC카드 </option>
						   <option value="국민카드" >국민카드</option>
						   <option value="삼성카드" >삼성카드</option>
						   <option value="외환카드" >외환카드</option>
						   <option value="롯데카드" >롯데카드</option>
						   <option value="신한카드" >신한카드</option>
						   <option value="현대카드" >현대카드</option>
						   <option value="씨티카드" >씨티카드</option>
						   <option value="하나SK카드" >하나SK카드</option>
						   <option value="NH카드" >NH카드</option>
						  </select>
						  <span class="txt_sb" style="font-size:1px">카드승인일 매월 15일, 미승인시 25일 추가승인</span> 
					</div>

					<div>카드번호
						 <span>
						  <input class="form_input1" type="text" id="a_cardnumber_1" name="a_cardnumber_1" style="ime-mode:disabled; width:60px;" maxlength="4" tabindex="231" />
						  -
						  <input class="form_input1" type="password" id="a_cardnumber_2" name="a_cardnumber_2" style="ime-mode:disabled; width:60px;" maxlength="4"  tabindex="232" />
						  -
						  <input class="form_input1" type="password" id="a_cardnumber_3" name="a_cardnumber_3" style="ime-mode:disabled; width:60px;" maxlength="4"  tabindex="233" />
						  -
						  <input class="form_input1" type="text" id="a_cardnumber_4" name="a_cardnumber_4" style="ime-mode:disabled; width:60px;" maxlength="4" tabindex="234" />
						 </span>
					</div>   
					
					<div>유효기간
						<span>
							  <select class="form_select2" name="a_cardyear" tabindex="241">
							   <option value='2015'>2015</option><option value='2016' selected>2016</option><option value='2017'>2017</option><option value='2018'>2018</option><option value='2019'>2019</option><option value='2020'>2020</option><option value='2021'>2021</option><option value='2022'>2022</option><option value='2023'>2023</option><option value='2024'>2024</option><option value='2025'>2025</option>
							  </select>
						  <label class="txt2">년</label>
							  <select class="form_select2" name="a_cardmonth" tabindex="242">
							   <option value='01' selected>01</option><option value='02'>02</option><option value='03'>03</option><option value='04'>04</option><option value='05'>05</option><option value='06'>06</option><option value='07'>07</option><option value='08'>08</option><option value='09'>09</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option>
							  </select>
						  <label class="txt2">월</label>
						</span>
					</div>

					<div>카드소유주
						<span>
						  <input class="form_input2" type="text" name="a_cardname" id="a_cardname" maxlength="20" tabindex="251" />
						  <label class="txt2">
						   <input type="checkbox" name="copybtncard" value="Y" id="copybtncard" onclick="copyname();" tabindex="252" />
						   후원자와 동일</label>
						</span>
					</div>
				</div>
				<!-- 카드 -->

			<!-- 자동이체 -->
				<div class="payinfo2" style="display:none;" >
					<div>은행명
						 <select class="form_select3" name="a_bankcompany" id="a_bankcompany" tabindex="301">
						   <option value="">선택</option>
						   <option value="산업은행" >산업은행</option>
						   <option value="기업은행" >기업은행</option>
						   <option value="국민은행" >국민은행</option>
						   <option value="외환은행" >외환은행</option>
						   <option value="수협" >수협</option>
						   <option value="농협중앙회" >농협중앙회</option>
						   <option value="단위농협" >단위농협</option>
						   <option value="우리은행" >우리은행</option>
						   <option value="스탠다드차타드은행" >스탠다드차타드은행</option>
						   <option value="씨티은행" >씨티은행</option>
						   <option value="대구은행" >대구은행</option>
						   <option value="부산은행" >부산은행</option>
						   <option value="광주은행" >광주은행</option>
						   <option value="제주은행" >제주은행</option>
						   <option value="전북은행" >전북은행</option>
						   <option value="경남은행" >경남은행</option>
						   <option value="새마을금고" >새마을금고</option>
						   <option value="신협" >신협</option>
						   <option value="우체국" >우체국</option>
						   <option value="하나은행" >하나은행</option>
						   <option value="신한은행" >신한은행</option>
						   <option value="동양증권" >동양증권</option>
						   <option value="현대증권" >현대증권</option>
						   <option value="미래에셋증권" >미래에셋증권</option>
						   <option value="KDB대우증권" >KDB대우증권</option>
						   <option value="삼성증권" >삼성증권</option>
						   <option value="한국투자증권" >한국투자증권</option>
						   <option value="우리투자증권" >우리투자증권</option>
						   <option value="하이투자증권" >하이투자증권</option>
						   <option value="HMC투자증권" >HMC투자증권</option>
						   <option value="SK증권" >SK증권</option>
						   <option value="대신증권" >대신증권</option>
						   <option value="한화투자증권" >한화투자증권</option>
						   <option value="하나대투증권" >하나대투증권</option>
						   <option value="신한금융투자" >신한금융투자</option>
						   <option value="동부증권" >동부증권</option>
						   <option value="유진투자증권" >유진투자증권</option>
						   <option value="메리츠종금증권" >메리츠종금증권</option>
						   <option value="농협증권" >농협증권</option>
						   <option value="부국증권" >부국증권</option>
						   <option value="신영증권" >신영증권</option>
						  </select>
							 <input type="radio"  value="10" checked="checked" name="date" id="10" tabindex="305"/>
							  <label>매월 10일</label>
							 <input type="radio"  value="25" tabindex="306" name="date" id="25"/>
							  <label>매월 25일</label>
					</div>

					<div>계좌번호
						<span>
						  <input type="text" name="a_banknumber" id="a_banknumber" style="ime-mode:disabled;" maxlength="30" tabindex="310" />
						  
						  <span><a href="">계좌번호 인증</a></span>
						</span>
					</div>

					<div>예금주
						<span>
						  <input type="text" name = "card_own" id="a_bankname" maxlength="20" tabindex="315"/>
						  
						  <label class="txt2">
						   <input type="checkbox" value="Y" id="copybtnbank" tabindex="316"/>
						   후원자와 동일</label>
						</span>
					</div>
					
					<div> 예금주 생년월일
						 <span>
						  <select name="a_birth_1" id="a_birth_1" tabindex="321">
						   <option value="1900" >1900</option><option value="1901" >1901</option><option value="1902" >1902</option><option value="1903" >1903</option><option value="1904" >1904</option><option value="1905" >1905</option><option value="1906" >1906</option><option value="1907" >1907</option><option value="1908" >1908</option><option value="1909" >1909</option><option value="1910" >1910</option><option value="1911" >1911</option><option value="1912" >1912</option><option value="1913" >1913</option><option value="1914" >1914</option><option value="1915" >1915</option><option value="1916" >1916</option><option value="1917" >1917</option><option value="1918" >1918</option><option value="1919" >1919</option><option value="1920" >1920</option><option value="1921" >1921</option><option value="1922" >1922</option><option value="1923" >1923</option><option value="1924" >1924</option><option value="1925" >1925</option><option value="1926" >1926</option><option value="1927" >1927</option><option value="1928" >1928</option><option value="1929" >1929</option><option value="1930" >1930</option><option value="1931" >1931</option><option value="1932" >1932</option><option value="1933" >1933</option><option value="1934" >1934</option><option value="1935" >1935</option><option value="1936" >1936</option><option value="1937" >1937</option><option value="1938" >1938</option><option value="1939" >1939</option><option value="1940" >1940</option><option value="1941" >1941</option><option value="1942" >1942</option><option value="1943" >1943</option><option value="1944" >1944</option><option value="1945" >1945</option><option value="1946" >1946</option><option value="1947" >1947</option><option value="1948" >1948</option><option value="1949" >1949</option><option value="1950" >1950</option><option value="1951" >1951</option><option value="1952" >1952</option><option value="1953" >1953</option><option value="1954" >1954</option><option value="1955" >1955</option><option value="1956" >1956</option><option value="1957" >1957</option><option value="1958" >1958</option><option value="1959" >1959</option><option value="1960" >1960</option><option value="1961" >1961</option><option value="1962" >1962</option><option value="1963" >1963</option><option value="1964" >1964</option><option value="1965" >1965</option><option value="1966" >1966</option><option value="1967" >1967</option><option value="1968" >1968</option><option value="1969" >1969</option><option value="1970" >1970</option><option value="1971" >1971</option><option value="1972" >1972</option><option value="1973" >1973</option><option value="1974" >1974</option><option value="1975" >1975</option><option value="1976" >1976</option><option value="1977" >1977</option><option value="1978" >1978</option><option value="1979" >1979</option><option value="1980" >1980</option><option value="1981" >1981</option><option value="1982" >1982</option><option value="1983" >1983</option><option value="1984" >1984</option><option value="1985" >1985</option><option value="1986" >1986</option><option value="1987" >1987</option><option value="1988" >1988</option><option value="1989" >1989</option><option value="1990" >1990</option><option value="1991" >1991</option><option value="1992" >1992</option><option value="1993" >1993</option><option value="1994" >1994</option><option value="1995"  selected>1995</option><option value="1996" >1996</option><option value="1997" >1997</option><option value="1998" >1998</option><option value="1999" >1999</option><option value="2000" >2000</option><option value="2001" >2001</option>
						  </select>
						  <label>년</label>
						  <select name="a_birth_2" id="a_birth_2"tabindex="322">
						   <option value="01"  selected>1</option><option value="02" >2</option><option value="03" >3</option><option value="04" >4</option><option value="05" >5</option><option value="06" >6</option><option value="07" >7</option><option value="08" >8</option><option value="09" >9</option><option value="10" >10</option><option value="11" >11</option><option value="12" >12</option>
						  </select>
						  <label>월</label>
						  <select name="a_birth_3" id="a_birth_3"tabindex="333">
						   <option value="01"  selected>1</option><option value="02" >2</option><option value="03" >3</option><option value="04" >4</option><option value="05" >5</option><option value="06" >6</option><option value="07" >7</option><option value="08" >8</option><option value="09" >9</option><option value="10" >10</option><option value="11" >11</option><option value="12" >12</option><option value="13" >13</option><option value="14" >14</option><option value="15" >15</option><option value="16" >16</option><option value="17" >17</option><option value="18" >18</option><option value="19" >19</option><option value="20" >20</option><option value="21" >21</option><option value="22" >22</option><option value="23" >23</option><option value="24" >24</option><option value="25" >25</option><option value="26" >26</option><option value="27" >27</option><option value="28" >28</option><option value="29" >29</option><option value="30" >30</option><option value="31" >31</option>
						  </select>
						  <label>일</label>
						 </span>
					</div>
				</div>
				<!-- 자동이체 -->
		</div>
		<!-- right -->
	</div>
	<!-- wrap -->

		<div  class="agree"> 
				<input type="checkbox" name="agree1"value="Y" />
				 <label>개인정보수집 동의</label> 
		</div>

		<div class="box">
			 국세청 연말정산 간소화 서비스에서 기부금 영수증을 발급하시겠습니까?
			 <input type="radio" name="guk" value="Y" id="tax_yes" tabindex="401" />
			 <label class="txt2 inline mg_r20"> 예</label>
			 <input type="radio" name="guk" value="N" id="tax_no" tabindex="402" checked="checked" />
			 <label class="txt2 inline">아니오</label>
			
			<div class="tax_yes_1"   style="display:none;" >
				 <p>후원자님의 주민등록번호(타인 주민등록번호 불가)를 입력하시면 국세청 연말정산 간소화 서비스에 등록됩니다.</p>
				 <p> 주민등록번호 
				  <input class="front_jumin" type="text" name="front_jumin" id="front_jumin" maxlength="6" style="ime-mode:disabled;" tabindex="411"/>
				  -
				  <input class="end_jumin" type="password" name="end_jumin2" id="end_jumin2" maxlength="7" style="ime-mode:disabled;" tabindex="412"/>
				  
				  <input type="checkbox" name="agree2" tabindex="421" id="agree2" value="Y" />
				  <label>개인정보수집 동의</label>
			</div>
		</div>   


	<input type="submit" class="junggi"  value="" style="height: 50px; width: 170px;">	


	</form>

</div>

</body>
</html>