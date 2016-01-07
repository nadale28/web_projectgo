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
<link href="css/ih_style2.css" rel="stylesheet" />
<style>
	@import url(http://fonts.googleapis.com/earlyaccess/nanumpenscript.css);
	@import url(http://fonts.googleapis.com/earlyaccess/jejumyeongjo.css);
	@import url(http://fonts.googleapis.com/earlyaccess/jejugothic.css);
	@import url(http://fonts.googleapis.com/earlyaccess/nanumbrushscript.css);
	@import url(http://fonts.googleapis.com/earlyaccess/hanna.css);
	@import url(http://fonts.googleapis.com/earlyaccess/jejuhallasan.css);
	@import url(http://fonts.googleapis.com/earlyaccess/nanumgothiccoding.css);
</style>
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
	function sponser(){
		alert("결제되었습니다.");
		document.sponser.action="sponser.do";
		document.sponser.submit();
	}
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
	

	/* .header {width:100%; position:absolute;  top:0; height:80px; left:0; right:0; color:white; background:#593220;}
	.header > * {vertical-align:center; line-height:80px;}
	.header > .header_nav_btn {float:left; margin-left:5%;}
	.header > .header_campaign {float:left; margin-left:2%}
	.header > .header_nav {float:right; margin-right:5%; }
	.header > .header_nav > span > * {color:white;} */
	
	
	.header {width:90%; position:fixed;  top:4%; left:0; right:0; color:black; font-size:25px; margin:0 auto}
	.header > .header_nav_btn {float:left; margin-left:5%; border-bottom:3px solid black; height:30px; 
	font-family: 'Hanna', sans-serif;}
	.header > .header_campaign {float:left; margin-left:2%; border-bottom:3px solid black; height:30px; 
	font-family: 'Hanna', sans-serif; font-weight:normal;}
	.header > .header_nav {float:right; margin-right:5%; overflow:hidden; width:50%;}
	.header > .header_nav > p {height:20px; border-bottom:3px solid black; float:right; margin-left:5%; height:30px; font-family: 'Hanna', sans-serif;}
	.header > .header_nav > p > * {color:black; }
	

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


	.footer{width:100%; height:60px; position:relative; margin:0 auto; margin-top:40px; bottom:0; left:0; right:0; }
	.footer > * {margin-left:15%;}
	.fmenu{position:absolute; top:0; left:0; padding:0;margin-top:0;margin-bottom:0; }
	.fm_list{float:left; list-style:none; padding-right:13px;}

	.faddress{position:absolute; top:30px; left:0; font-size:13px;}
	.faddress > p {padding-top:0;padding-bottom:0;margin:0;height:16px;line-height:16px;}
	.faddr_f{padding-right:10px; float:left;}
	.faddr_p{padding-right:10px; float:left;}
	.faddr_l{float:left;}
	.danation{float:right;}
	
			 
</style>
<script type="text/javascript"	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		// donation price box
		$("#support_donationprice").change(function() {
			if ($(this).val() == "custom") {
				$('#support_donationprice_custom').fadeIn("slow");
				$(this).css("width", "76px");
				$('#once_countbox').css("width", "179px");
				$('#support_donationprice_custom').focus();
			} else {
				$(this).css("width", "120px");
				$('#support_donationprice_custom').fadeOut("slow", function() {
					$('#once_countbox').css("width", "126px");
				});
			}
		});
	});
	$(document).ready(function() {
		// donation price box
		$("#support_donationprice1").change(function() {
			if ($(this).val() == "custom") {
				$('#support_donationprice_custom1').fadeIn("slow");
				$(this).css("width", "76px");
				$('#once_countbox1').css("width", "179px");
				$('#support_donationprice_custom1').focus();
			} else {
				$(this).css("width", "120px");
				$('#support_donationprice_custom1').fadeOut("slow", function() {
					$('#once_countbox1').css("width", "126px");
				});
			}
		});
	});
	function close_support_popup(preserve) {

		$('.layer_popup_bg').hide();
		$('#pu_support').fadeOut();

		if (preserve != "1") {
			setTimeout(
					function() {

						if (g_sup_donationtarget == "1") {
							document.getElementById("support_child_id").value = g_sup_childid;
							document.getElementById("support_child_name").innerHTML = g_sup_givenname;
							document.getElementById("support_child_age").innerHTML = g_sup_age;
							document.getElementById("support_child_gender").innerHTML = g_sup_genderdesc;
							document
									.getElementById("support_child_filepath")
									.setAttribute(
											"style",
											"background:url('"
													+ g_sup_filepath
													+ "') no-repeat center 0%;background-size:cover;height:117px;width:117px;overflow:hidden;");
							document.getElementById("support_child_country").innerHTML = g_sup_country;
							document.getElementById("support_child_adp").innerHTML = g_sup_adp;
						} else if (g_sup_donationtarget == "2") {
							document.getElementById("support_donationprice").value = "5,000";
							//document.getElementById("support_donationprice").innerHTML = ((g_sup_donationtype == "1") ? "월 " : "") + make_price_format(g_sup_unitprice) + "원";
						}

						document.getElementById("co_write_sponsor").value = "";
						document.getElementById("pu_support_cb").checked = false;

					}, 300);
		}
	}
	
	
</script>
</head>
<body>
<div class="header" >
			<p class="header_nav_btn">메뉴</p>
			<h4 class="header_campaign">캠페인</h4>
			<div class="header_nav">
				<%
			if(session.getAttribute("memId")==null){
				
			
			%>
				<p><a href="cus_loginform.do">로그인</a></p>&nbsp;&nbsp;
				<p><a href="cus_searchIdForm.do">ID/PW찾기</a></p>&nbsp;&nbsp;
				<%} else{%>
				<p><a href="cus_logout.do">로그아웃</a></p>&nbsp;&nbsp;
				<p><a href="cus_modify.do?id=<%= session.getAttribute("memId") %>">마이페이지</a></p>&nbsp;&nbsp;
				<% } %>
				<p><a href="cus_inputForm.do">회원가입</a></p>
			</div>
	  </div>

	<div class="donation_box">
			<div class="link_wrap">
				<a class="m_don">무기명후원</a>	<br>
				<a class="t_don">일시후원</a>	<br>
				<a href="list.do">봉사신청</a> <br>
				<a href="main.do">메인으로</a>
			</div>
		</div>	

		

<div class="wrap">
	<div>
		<img style="height: 20px; width: 25px;" src="img/popup_btn_close.png" class="tpop"; id="tpop"> 
	</div>
	<form method="post" name="sponser" action="sponser.do">
			  <h2 class="title">후원하기</h2>
				<div class="container">
					<div class="wrap_01">
            			<h3>후원신청 내역</h3>
						<div class="form">
							<table class="tb_info-box" cellpadding="0" width="50%">
								<tbody style="vartical-align:0">	
									<tr>
									  <td>프로젝트명</td>
									  <td>사랑의 연탄</td>
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
									    <td>연탄</td>
								  </tr>
								  <tr>
									 <td>후원금액</td>
									 <td>
									 <span id="once_countbox1" class="count-box"> 
										<select id="support_donationprice1" name="amount">
												<option value="">선택</option>
												<option value="1,000">1,000</option>
												<option value="2,000">2,000</option>
												<option value="3,000">3,000</option>
												<option value="5,000">5,000</option>
												<option value="10,000">10,000</option>
												<option value="20,000">20,000</option>
												<option value="30,000">30,000</option>
												<option value="50,000">50,000</option>
												<option value="100,000">100,000</option>
												<option value="custom">직접입력</option>
										</select> <label for="support_donationprice"></label> <input
											type="text" id="support_donationprice_custom1"
											name="amount" value="" maxlength="12"
											onkeydown="keep_price_format(this)"
											onkeyup="keep_price_format(this);"
											onblur="keep_price_format(this);"
											onmousedown="keep_price_format(this);" style="display: none;" />
											<!--<span class="arrow"><a href="javascript:change_donationprice('up')">증가</a><a href="javascript:change_donationprice('down')">감소</a></span>-->
									</span>
									 </td>
								  </tr>
								</tbody>
							</table>
						</div>
					</div>
				

                   <br>
                   
					<div class ="wrap_02">     
						<h3>후원자 정보</h3>
                    
						
                        <table class="tb_info-box form-tb"  cellpadding="0" width="50%">
 
                            <tbody>
                             
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
                                <tr>
                                    <td>성별</td>
                                    <td class="pt3 pb12">
                          	            <span style="width:77px;"><input type="radio" name="sex" id="sex_1" value="1" checked /><label for="sex_1">남자</label></span>
                          	            <input type="radio" name="sex" id="sex_2" value="2" /><label for="sex_2">여자</label>
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
<div class="ml5_mr10">
						<input type="checkbox" name="agree_cb" id="agree_cb"  /><label for="agree_cb">
						<a href="">이용약관</a>과 
						<a href="">개인정보 취급방침</a>에 동의합니다.</label>
					</div>
					
					
					<div class="img" >
						<input type="submit" style="height: 40px; width: 120px;" value="결제" >
					</div>

                
    		    

        </div>
        </form><!--//input_form-->
   </div>
   














<div class="container1">
  <form method="post" action="https://www.sandbox.paypal.com/cgi-bin/webscr">
	<div>
		<img style="height: 20px; width: 20px;" src="img/popup_btn_close.png" class="pop" ; id="pop"> 
	</div>

 <div class = "title">
			<h4 class="title_01">후원하기
		</div>
	<div class="layer_popup size03 pu_support_wrap" id="pu_support">
		
		<div class="pu_support">
			<div id="pu_support_goods" name="pu_support_goods">
				<p>후원 대상 정보</p>
		
			</div>
			
			<div class="reco-child" style="margin-bottom: 10px;">
				<table class="tb_reco-child"  width="430" >
					<caption></caption>
					<colgroup>
						<col width="0px;" />
					</colgroup>
					<tbody>
					<br>
						<tr>
							<td class="ph"><img style="height: 130px; width: 150px;"
								src="img/logo.png" alt="" id="support_itempicture"
								name="support_itempicture"/></td>
							<td class="info">
								<ul
									style="display: table; margin-left: 0px; line-height: 28px;">
									<li class="sponsor"><div><font size="2">프로젝트 : 연탄 배달 봉사&nbsp;</font></div>
										<div id="support_projectname" name="support_projectname"></div></li>
									<li class="sponsor"><div><font size="2">후원대상 : 어려운 사람들&nbsp;</font></div>
										<div id="support_iteminfo" name="support_iteminfo"></div></li>
									<li class="sponsor price">
									<div id="support_donation_object_info"><font size="2">후원금액 : </font>&nbsp;
										<div id="support_donationprice_prefix" name="support_donationprice_prefix">
										<span id="once_countbox" class="count-box"> 
										<select id="support_donationprice" name="amount">
												<option value="">선택</option>
												<option value="1,000">1,000</option>
												<option value="2,000">2,000</option>
												<option value="3,000">3,000</option>
												<option value="5,000">5,000</option>
												<option value="10,000">10,000</option>
												<option value="20,000">20,000</option>
												<option value="30,000">30,000</option>
												<option value="50,000">50,000</option>
												<option value="100,000">100,000</option>
												<option value="custom">직접입력</option>
										</select> <label for="support_donationprice"></label> <input
											type="text" id="support_donationprice_custom"
											name="amount" value="" maxlength="12"
											onkeydown="keep_price_format(this)"
											onkeyup="keep_price_format(this);"
											onblur="keep_price_format(this);"
											onmousedown="keep_price_format(this);" style="display: none;" />
											<!--<span class="arrow"><a href="javascript:change_donationprice('up')">증가</a><a href="javascript:change_donationprice('down')">감소</a></span>-->
									</span>
									<span id="support_donationprice_tail" style="vertical-align: top;">원</span>
									</div> 
									</div>
									</li>
									
								</ul>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<font class="font" size="2", color="gray"><p id="noname_txt" style="line-height:18px;">
							* 무기명 후원 시, 후원자 정보가 없어 추후 <span class="b u">후원내역 확인 및 소득공제가 불가능</span>합니다.<br />
							* 응원메시지는 익명으로 등록됩니다.<br />
						</p>  </font>
	


			<div id="pu_support_designated_item"
				name="pu_support_designated_item" style="display: none">
				<p class="tr mt6"></p>
			</div>
		</div>
		<div class="bot_btn" id="pu_support_btn_nologin"
			name="pu_support_btn_nologin">
			<!-- style="display:none" -->

				
		</div>
		   <div id="order_container">
       
          <!--   구매요청 : --> <input type="hidden" name="cmd" value="_xclick" size="50" />
           <!--  상점계정 : --> <input type="hidden" name="business" value="slugalove@naver.com" size="50" />
         <!--    상품이름 : --> <input type="hidden" name="item_name" value="사랑의 연탄" size="50" />
            <!-- 결제후 이동되는 페이지 : --> <input type="hidden" name="return" value="accept.do" size="50" />
          <!--   IPN메세지 받을 페이지 : --> <input type="hidden" name="notify_url" value="accept.do" size="50" />
            <!-- 결제 취소 페이지 :  --><input type="hidden" name="cancel_return" value="cancel.do" size="50" />
            <!-- 인코딩 : --> <input type="hidden" name="charset" value="UTF-8" size="50" />
            <input type="hidden" name="currency_type" value="USD" size="50" />
            <input type="submit" class="danation" value="후원하기" size="50" />
            </div>
        </form>
    
	</div>

</div>





</body>
</html>