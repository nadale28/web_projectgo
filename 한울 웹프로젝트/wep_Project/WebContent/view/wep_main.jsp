<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang=ko>
 <head>
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <title>Document</title>

  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script type="text/javascript"></script>
  <script src="js/jquery.bxslider.min.js"></script>
  <link href="lib/jquery.bxslider.css" rel="stylesheet" />
  <link href="css/ih_style.css" rel="stylesheet" />

  <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <script type="text/javascript" src="js/jquery.mousewheel.min.js"></script>
	
	<style>
	@import url(http://fonts.googleapis.com/earlyaccess/nanumpenscript.css);
	@import url(http://fonts.googleapis.com/earlyaccess/jejumyeongjo.css);
	@import url(http://fonts.googleapis.com/earlyaccess/jejugothic.css);
	@import url(http://fonts.googleapis.com/earlyaccess/nanumbrushscript.css);
	@import url(http://fonts.googleapis.com/earlyaccess/hanna.css);
	@import url(http://fonts.googleapis.com/earlyaccess/jejuhallasan.css);
	@import url(http://fonts.googleapis.com/earlyaccess/nanumgothiccoding.css);
	</style>
	<style>
	.reply2{ margin:0 auto; height:55%;}
	
	.reply_container{width:560px; margin:0 auto;}
	.reply_box{width:100%; background:#f5f5f5;padding:5px; border:1px solid gray; margin-top:8px;}	
	.container_box{margin:0 auto;overflow:hidden;height:80px;}
	.id_box{margin-bottom:5px;height:20%;}
	.content_box{width:82%;height:80%;float:left;padding:0;box-sizing:border-box;}
	.rbtn{width:17%; height:80%;float:left;box-sizing:border-box;margin-left:1%;
		height:80%;font-weight:bold;}
	
	.reply_list{width:560px; background:white;padding:5px; margin:5px auto;}
	.user_info{overflow:hidden;margin-top:12px;}
	.id_list{float:left; margin-right:10px; font-size:8px; color:#696969}
	.date_list{float:left; font-size:7px; color:#696969}
	.content_list{margin:6px 0 0 0; padding:0; font-size:10px;color:#696969}
	
	#reply{height:43px; vertical-align:middle;border-bottom:1px solid gray;}
	
	.page {margin:0 auto; text-align:center; margin-top:10px;}
	
	p{margin:0;padding:0;}
	.danation{float:right;}
</style>


  <script>
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
	$(document).ready(function(){
		 $('.bxslider').bxSlider({
			mode: 'fade',
			caption: true,
			controls: true,
			hideControlOnEnd: true,
			auto: true
		 });
		
		 //var w_width = screen.width;
		 //var w_height = screen.height;
		 var w_width = $(window).width();
		 var w_height = $(window).height();

		 $('.first_page').css('width',w_width);
		 $('.first_page').css('height',w_height);

		 $('.second_page').css('width',w_width);
		 $('.second_page').css('height',w_height);
		 $('.second_page').css('top',w_height);

		 $('.third_page').css('width',w_width);
		 $('.third_page').css('height',w_height);
		 $('.third_page').css('top',w_height*2);

		 $('.forth_page').css('width',w_width);
		 $('.forth_page').css('height',w_height);
		 $('.forth_page').css('top',w_height*3);
		var start = 0;
		var end = 0;
		var up = 0;
		var down = 0;
		var finish = false;

		
		function nav_brown(){
			$('.header').css('color','brown');
			$('.header_nav_btn').css('border-bottom','3px solid brown');
			$('.header_campaign').css('border-bottom','3px solid brown');
			$('.header_nav > p > *').css('color','brown');
			$('.header_nav > p').css('border-bottom','3px solid brown');
		}

		function nav_white(){
			$('.header').css('color','white');
			$('.header_nav_btn').css('border-bottom','3px solid white');
			$('.header_campaign').css('border-bottom','3px solid white')
			$('.header_nav > p > *').css('color','white');
			$('.header_nav > p').css('border-bottom','3px solid white');
		}
		
		function nav_btn_brown(){
			$('.nav_btn').css('color','brown');
		}

		function nav_btn_white(){
			$('.nav_btn').css('color','white');
		}
		
		function nav_dark_brown(){
			$('.header').css('color','#5d4037');
			$('.header_nav_btn').css('border-bottom','3px solid #5d4037');
			$('.header_campaign').css('border-bottom','3px solid #5d4037');
			$('.header_nav > p > *').css('color','#5d4037');
			$('.header_nav > p').css('border-bottom','3px solid #5d4037');
		}
		
		//$('.start_logo').css('line-height',w_height+'px');
		//현재페이지 구하기
		function cur_page(){
			var page;
			if($('.first_page').position().top==0){
				page = 0;
				return page; 
			}else if($('.first_page').position().top==-w_height){
				page = 1;
				return page; 
			}else if($('.first_page').position().top==-w_height*2){
				page = 2;
				return page; 
			}else if($('.first_page').position().top==-w_height*3){
				page = 3;
				return page; 
			}
		}
		/* function story_select_ani(select){
			if(select==0){
				$('.first_stroy').animate({
					right:5
					},500);
			}else{
				$('.first_story').animate({
					right:0
				},500);
			}
		} */
		var ani_pro=0;
		//네비게이션 숨김
		//function story_nav_open(){
			/* $('.story_nav').animate({right:-200},'slow');
			setTimeout(function(){
				$('.story_nav').animate({right:2},'slow');},900); */
				//callback();
				//$('.story_nav').stop().effect('fade','',500,callback);
				/* setTimeout(function(){
					$('.story_nav').effect('fade','',500,'');
				},900); */
				//callback();
				//$('.story_nav').effect('fold','',100,'');
				/* setTimeout(function(){
					$('.story_nav').effect('fold','',500,'');
					//ani_pro = 0;
				},5000); */
				/* if(ani_pro==0){
					ani_pro = 1;
					$('.story_nav').show('fold','',500,'');
					setTimeout(function(){
						$('.story_nav').effect('fold','',500,'');
						ani_pro = 0;
					},5000);
				}else if(ani_pro==1){
					callback();
					ani_pro = 0;
				} */
		//}
		/* function callback() {
		      setTimeout(function() {
		        $( ".story_nav" ).removeAttr( "style" ).hide().fadeIn();
		      }, 1000 );
		    }; */
		    
		function story_select_color(cls){
		    $(cls).mouseover(function(){
		    	$(this).css('color','white');
		    	$(this).css('border','1px solid white');
		    });
		    $(cls).mouseleave(function(){
		    	$(this).css('color','black');
		    	$(this).css('border','1px solid gray');
		    });
		  }
		    
		 function story_no_select(cls){
			 $(cls).mouseleave(function(){
				 $(this).css('color','white');
				 $(this).css('border','1px solid white');
			 });
		 }
		 	
		    $('.first_page').hide();
		    
		 function start_ani(){
			$('.start_page').css({
				'width':w_width,
				'height':w_height,
			});
			$(window).bind('mousewheel DOMMouseScroll', function(event) 
					{
							event.preventDefault(); 			
					});
			setTimeout(function(){wheel();},8000);
			/* setTimeout(function(){
				$('.start_logo').animate({
					color : "#fff",
					//top : 0
				},3000);
			},5000); */
			//$('.first_page').css('display','block');
			//$('.first_page').hide('fade','',1000,'');
			$('.top_logo > h1').hide();
			$('.top_logo > h2').hide();
			setTimeout(function(){
				$('.top_logo > h1').show('fade','',2000,'');
			},1000);
			
			setTimeout(function(){
				$('.top_logo > h2').show('fade','',2000,'');
			},'2000');
			//$('.top_logo > h1').show('fade','',5000,'');
			 setTimeout(function(){
				$('.start_page').hide('fade','',1000,'');
				$('.first_page').show('fade','',2000,'');
			},5000); 
		 }
		 start_ani();
		 var second_state=0;
		 $('.middle_txt').hide();
		 $('iframe').hide();
		 $('.middle').hide();
		 story_nav();
		 function second_ani(){
			 setTimeout(function(){
				 var curpage;
				 if(second_state==0){
					 
					 curpage = cur_page();
					 if(curpage==1){
					 	second_state=1;
					 	
					 		$('.middle').show('fade','',1000,'')
						 	
					 		setTimeout(function(){
					 			$('.middle_txt').show('fade','',2000,'');
					 		},0);
						 	setTimeout(function(){
						 		$('iframe').show('fade','',2000,'');
						 	},2000);
					 }
				 }
				 
			 },1000);
		 }
		 
		 var third_state=0;
		 $('.con01').hide();
		 $('.con02').hide();
		 $('.con03').hide();
		 function third_ani(){
			 setTimeout(function(){
				 var curpage;
				 if(third_state==0){
					 
					 curpage = cur_page();
					 if(curpage==2){
					 	third_state=1;
					 	
					 	$('.con01').show('scale','percent:80',0,'');
					 	setTimeout(function(){
					 		$('.con02').show('scale','percent:80',1000,'');
					 	},1000);
					 	setTimeout(function(){
					 		$('.con03').show('scale','percent:80',1500,'');
					 	},2000);
					 }
				 }
			 },1000);
		 }
		 
		 var forth_state=0;
		 $('.bottom_txt').hide();
		 $('.reply2').hide();
		 $('.footer').hide();
		 function forth_ani(){
			 setTimeout(function(){
				 var curpage;
				 if(forth_state==0){
					 
					 curpage = cur_page();
					 if(curpage==3){
						 forth_state=1;
						 
					 	$('.bottom_txt').show('fade','',0,'');
					 	setTimeout(function(){
					 		$('.reply2').show('fold','',2000,'');
					 	},1000);
					 	setTimeout(function(){
					 		$('.footer').show('fade','',1000,'');
					 	},3000);
					 }
				 }
			 },1000);
		 }
		 
		 function story_nav(){
			var curpage;
			setTimeout(function(){
				
			curpage = cur_page();
			
			if(curpage==0){
				//story_select_ani(0);
				$('.first_story').css({
					'font-size':'20px',
					'color':'white',
					'border':'1px solid white'
				});
				$('.second_story').css({
					'font-size':'20px',
					'color':'black',
					'border' : '1px solid gray'
				});
				$('.third_story').css({
					'font-size':'20px',
					'color':'black',
					'border' : '1px solid gray'
				});
				$('.forth_story').css({
					'font-size':'20px',
					'color':'black',
					'border' : '1px solid gray'
				});
				$('.cur_story').text('first story');
				story_select_color('.second_story');
				story_select_color('.third_story');
				story_select_color('.forth_story');
				story_no_select('.first_story');
			}else if(curpage==1){
				$('.first_story').css({
					'font-size':'20px',
					'color':'black',
					'border' : '1px solid gray'
				});
				$('.second_story').css({
					'font-size':'20px',
					'color':'white',
					'border':'1px solid white'
				});
				$('.third_story').css({
					'font-size':'20px',
					'color':'black',
					'border' : '1px solid gray'
				});
				$('.forth_story').css({
					'font-size':'20px',
					'color':'black	',
					'border' : '1px solid gray'
				});
				$('.cur_story').text('second story');
				story_select_color('.first_story');
				story_select_color('.third_story');
				story_select_color('.forth_story');
			}else if(curpage==2){
				$('.first_story').css({
					'font-size':'20px',
					'color':'black',
					'border' : '1px solid gray'
				});
				$('.second_story').css({
					'font-size':'20px',
					'color':'black',
					'border' : '1px solid gray'
				});
				$('.third_story').css({
					'font-size':'20px',
					'color':'white',
					'border':'1px solid white'
				});
				$('.forth_story').css({
					'font-size':'20px',
					'color':'black',
					'border' : '1px solid gray'
				});
				$('.cur_story').text('third story');
				story_select_color('.first_story');
				story_select_color('.second_story');
				story_select_color('.forth_story');
			}else{
				$('.first_story').css({
					'font-size':'20px',
					'color':'black',
					'border' : '1px solid gray'
				});
				$('.second_story').css({
					'font-size':'20px',
					'color':'black',
					'border' : '1px solid gray'
				});
				$('.third_story').css({
					'font-size':'20px',
					'color':'black',
					'border' : '1px solid gray'
				});
				$('.forth_story').css({
					'font-size':'20px',
					'color':'white',
					'border':'1px solid white'
				});
				$('.cur_story').text('forth story');
				story_select_color('.first_story');
				story_select_color('.second_story');
				story_select_color('.third_story');
			}},1000);
		} 
		
		//$('*').hover(function(){
		//$('.below_a').toggle('slide','',500);
		//});
		
		/* setInterval(function(){
			$('.below_a').animate({
				bottom:0
			},500);
			$('.below_a').animate({
				bottom:15
			},500);
		},1000) */
		$('.story_nav_container').fadeOut();
		$('.story_arrow').mouseenter(function(){
			//$('.story_arrow').removeAttr('hover');
			$('.story_arrow').css('display','none');
			//$('.story_nav_container').css('display','block');
			$('.story_nav_container').show('clip','',1000,'');
			setTimeout(function(){
				callback();
			},3000);
			setTimeout(function(){
				
				$('.story_arrow').show('fade','',1000,'');
			},4000);
		});
		function callback() {
		      setTimeout(function() {
		        $( ".story_nav_container:visible" ).removeAttr( "style" ).fadeOut();
		      }, 1000 );
		}
		/* $('.story_nav_container').mouseleave(function(){
			$('.story_nav_container').effect('clip','',1000,'');
			setTimeout(function(){$('.story_arrow').css('display','block');},1000);
			
			
		}); */
		function scroll_ani(page_num){
				$('.nav_scroll').animate({
					top: (2 + page_num*24)+'%'
				},'slow');
			}

		function wheel(){
		 $(window).bind('mousewheel',function(event){		
		
			var f_position = $('.first_page').position().top;
			var s_position = $('.second_page').position().top;
			var t_position = $('.third_page').position().top;
			var forth_position = $('.forth_page').position().top;
			

			function wheel_ani(){
				story_nav();
				second_ani();
				third_ani();
				forth_ani();
				//story_nav_hidden();
				//var curpage=cur_page().delay(1000);
				//alert(curpage);
				$('.first_page').stop().animate({
					top:f_position
				},'slow');
				$('.second_page').stop().animate({
					top:s_position
				},'slow');
				$('.third_page').stop().animate({
					top:t_position
				},'slow');
				$('.forth_page').stop().animate({
					top:forth_position
				},'slow');						
			}
			var page_num = 0;
			

				if (event.deltaY == 1 && !finish) { //휠 올릴떄
					finish=true;
							if(f_position!=0){//첫페이지가 아닐때
								f_position += w_height;
								s_position += w_height;
								t_position += w_height;
								forth_position += w_height;
								if(s_position==0){//두번째 페이지로 올라올때
									setTimeout(function(){nav_brown()},700);
									setTimeout(function(){nav_btn_white()},700);
									wheel_ani();
									
									scroll_ani('1');
									
								}
								if(t_position==0){//세번째페이지로 올라올떄
									setTimeout(function(){nav_dark_brown()},700);
									setTimeout(function(){nav_btn_brown()},700);
								wheel_ani();
								end=0;
								scroll_ani('2');
								}
							}	
							if(f_position==0){//첫페이지로 올라올때
									if(start==0){//첫페이지 일때 처음실행
										setTimeout(function(){nav_white()},700);
										wheel_ani();
										start=1;//첫페이지에 도달했을때 한번만 실행
										scroll_ani('0');
										//story_nav();
									}
							}
							
					finish=false;	
				}
			

				if (event.deltaY == -1 && !finish) {//휠 내릴때
				
					finish=true;
							if(forth_position!=0){//끝페이지가 아닐때 
								f_position -= w_height; 
								s_position -= w_height;
								t_position -= w_height;
								forth_position -= w_height;
								
								if(s_position==0){ //두번째페이지로 내려올때
									setTimeout(function(){nav_brown()},700);
									wheel_ani();
									start=0;
									scroll_ani('1');
								}
								if(t_position==0){//세번째 페이지로 내려올때
									setTimeout(function(){nav_dark_brown()},700);
									setTimeout(function(){nav_btn_brown()},700);
									nav_brown();
									wheel_ani();
									scroll_ani('2');
								}
							}
							
							if(forth_position==0){//네번째 페이지로 내려올때
								if(end==0){
									wheel_ani();
									end=1;//끝페이지에 도달했을때 한번만 실행	
									scroll_ani('3');
								}
							}
					finish=false;	
				}
		 });
		}
			 
		

		
		
		function page_down(page_num){
				story_nav();
				var curpage = cur_page();
				//alert(curpage);
				$('.first_page').stop().animate({
					top:w_height*(-page_num)
				},'slow');
				$('.second_page').stop().animate({
					top:w_height*(-(page_num-1))
				},'slow');
				$('.third_page').stop().animate({
					top:w_height*(-(page_num-2))
				},'slow');
				$('.forth_page').stop().animate({
					top:w_height*(-(page_num-3))
				},'slow');
		}

		function page_up(page_num){
			story_nav();
				var curpage = cur_page();
				$('.first_page').stop().animate({
					top:w_height*(-page_num)
				},'slow');
				$('.second_page').stop().animate({
					top:w_height*(-(page_num-1))
				},'slow');
				$('.third_page').stop().animate({
					top:w_height*(-(page_num-2))
				},'slow');
				$('.forth_page').stop().animate({
					top:w_height*(-(page_num-3))
				},'slow');
		}


		$('.first_story').click(function(){
			var curpage = cur_page();
			end=0; start=1;
			scroll_ani('0');
			nav_white();
			
			if(curpage>0){
				page_up(0);
			}else if(curpage<0){
				page_down(0);
			}	
		});

		$('.second_story').click(function(){
			var curpage = cur_page();
			end=0; start=0;
			scroll_ani('1');
			nav_brown();
			second_ani();
			//alert(page);
			if(curpage>1){
				page_up(1);
			}else if(curpage<1){			
				page_down(1);
			}	
		});

		$('.third_story').click(function(){
			var curpage = cur_page();
			end=0; start=0;
			scroll_ani('2');
			nav_dark_brown();
			third_ani();
			//alert(page);
			if(curpage>2){
				page_up(2);
			}else if(curpage<2){
				page_down(2);
			}	
		});

		$('.forth_story').click(function(){
			var curpage = cur_page();
			end=1; start=0;
			scroll_ani('3');
			nav_dark_brown();
			forth_ani();
			//alert(page);
			if(curpage>3){
				page_up(3);
			}else if(curpage<3){
				//alert('curpage<3');
				page_down(3);
			}	
		});

				

		
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

		

		//ctrl+wheel 확대/축소 막기
		$(window).keydown(function(event){
			if((event.keyCode == 107 && event.ctrlKey == true) || (event.keyCode == 109 && event.ctrlKey == true))
			{
				event.preventDefault(); 
			}

			$(window).bind('mousewheel DOMMouseScroll', function(event) 
			{
				if(event.ctrlKey == true)
				{
					event.preventDefault(); 
				}
			});
		});	
		
		
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
  <script>

$(document).ready(function(){
	$('.rbtn').mousedown(function(){
		if($('.id_box').val()==''){
			alert('id를 입력해주세요');
		}
		else if($('.content_box').val()==''){
			alert('내용을 입력해주세요');
		}
	});
});

function replay(){
	
 var x = reply.id.value;
 var y = reply.content.value;
 $.ajax({   
  type: "POST",  
  url: "repAjax.do",   
  data: "id="+x+"&content="+y,   //&a=xxx 식으로 뒤에 더 붙이면 됨
  success: result, 
  error : function(xhr, status, error) {
	     alert("에러발생");
	}//function result 를 의미함
   }
 
 );
}

function result(msg){
 //sub()가 실행되면 결과 값을 가지고 와서 action 을 취해주는 callback 함수
 $("#sp2").html(msg); //innerHTML 을 이런 방식으로 사용함
 //id 는 $("#id")   name 의 경우 $("name") 으로 접근함
}


</script>
 </head>
 <body>
	
	 <div class="start_page" style="background:rgba(214, 139, 22, 0.74);">
		<!-- <div class="start_logo" style="position:absolute; width:100%; height:100%; text-align:center; top:20%;
		 margin:auto;  font-size:40px; color:brown; overflow:hidden;"><h1>로고가 들어갈 자리</h1> </div> -->
		 
		 
		 <div class="top_logo">
			<h1>연탄 나눔 캠페인</h1>
			<h2>이웃들에게 따뜻한 겨울을 선물해주세요</h2>
		</div>
	</div> 
	<div class="first_page pages">
	
	    <div class="header" >
			<p class="header_nav_btn">메뉴</p>
			<h4 class="header_campaign">캠페인</h4>
			<div class="header_nav">
				<%
			if(session.getAttribute("memId")==null){
				
			
			%>
				<p><a href="cus_loginform.do">로그인</a></p>
				<p><a href="cus_searchIdForm.do">ID/PW찾기</a></p>
				<%} else{%>
				<p><a href="cus_logout.do">로그아웃</a></p>&nbsp;
				<p><a href="cus_modify.do?id=<%= session.getAttribute("memId") %>">마이페이지</a></p>
				<% } %>
				<p><a href="cus_inputForm.do">회원가입</a></p>
			</div>
	    </div>					
		
		<video preload="auto" autoplay="true" loop="loop" >
			<source src="view/ih_main_vod2.mp4">
		</video>
		
		<div class="top_logo">
			<h1>연탄 나눔 캠페인</h1>
			<h2>이웃들에게 따뜻한 겨울을 선물해주세요</h2>
		</div>
		<!--
		<div class="nav_btn">
			<p>메뉴</p>
		</div>-->
		<div class="donation_box">
			<div class="link_wrap">
				<a class="m_don" >무기명후원</a>	<br>
				<a class="t_don">일시후원</a>	<br>
				<a href="list.do">봉사신청</a> <br>
				<a href="main.do">메인으로</a> <br>
				<!-- <a href="">Q&A</a> -->
			</div>
		</div>	

		<div class="nav_scroll">
		</div>
			
			<div class="story_nav">
				<div class="story_nav_container">
					<p class="first_story">first story</p>
					<p class="second_story">second story</p>
					<p class="third_story">third story</p>
					<p class="forth_story">forth story</p>
				</div>
				<div class="story_arrow">
					<img src="img/ih_up_arrow.png"/>
					
					<p class="cur_story">first story</p>
					
					<img src="img/ih_down_arrow.png"/>
				</div>
			</div>
		
			
			<!-- <img class="below_a" src="img/ih_below_arrow.png" /> -->
		
    </div>

  <div class="second_page pages">
  
	<div class="middle">
		<div class="middle_txt_con">
			<div class="middle_txt">
				<h2 style="font-size:50px; margin:0; margin-top:20px; margin-bottom:20px">연탄 천사가 되어주세요</h2>
				<p>연탄은 타고 나면 하얀 재만 남지만 따뜻한 사랑은 영원히 남습니다.<br></p>
			</div>
		</div>
			<div class="middle_vod_con">
				<div class="middle_vod">
					<iframe width="640" height="360" src="https://www.youtube.com/embed/Lo0uvxuASKI" frameborder="0" allowfullscreen></iframe>			
				</div>
			</div>
		
		
	</div>
  </div>

  <div class="third_page pages">
		<div class="middle_top"></div>
		<div class="middle_con">
			<div class="middle_pic">
				
				<div class="con01 con">
					 <!-- <img src="img/ih_main_yun15.png"/>  -->
					 <img class="l_img1" src="img/ih_yun.jpg"/>
					<p class="sub_title01">
					"세상에서 가장 아름다운 얼굴을 보고, 만나는 곳"		
					</p>
					<p class="sub_text01">
					연탄 한장 속에는 </br>
					수 많은 사람들의 사랑과 정성,</br>
					기도와 아름다운 마음이 담겨져 있습니다.
						
						
					</p>
				</div>
				<div class="con02 con">
					<img class="r_img" src="img/ih_yun3.jpg"/>
					<p class="sub_title02">
						 "연탄처럼 잊혀져 가는 어려운 이웃들"		
					</p>
					<p class="sub_text02">
						 80년대 중반까지만 해도 연탄은 서민들이 추운 겨울을 날 수 있도록 도와주는 소중한 존재였습니다.<br> 
						 그러나 아직까지도 기초생활수급자, 차상위 계층 등에게는 연탄이 추운 겨울을 나는 데 없어서는 안 될 존재입니다. <br>
						 
					</p>
				</div>
				<div class="con03 con">
					<img class="l_img2" src="img/ih_yun2.jpg"/>
					<p class="sub_title03">
						 "특별한 봉사활동"		
					</p>
					<p class="sub_text03">
						집에서 방콕하고 있는 것도 좋지만, <br>추위에 떨고 있는 어려운 이웃들을 도우면서 따뜻한 온기를 함께 나눠보는 것은 어떨까요?<br>
						 겨울에만 할 수 있는 특별한 봉사활동! 
					</p>
				</div>
			</div>
		</div>
  </div>

  <div class="forth_page pages">
		<div class="middle_top"></div>
		<div class="bottom">
			<div class="bottom_txt">
				<h2>따뜻한 댓글을 남겨주세요.</h2>
				<h4>우리들의 따뜻한 댓글이, 어려운 이웃들에게 큰 힘을 줄 수 있습니다.<br>
						함께 만들어요! 따뜻한 겨울.
				</h4>
				
			</div>
			<jsp:include page="ih_reply.jsp"></jsp:include>
			<div class="footer">
			 <ul class="fmenu">
				 <li class="fm_list"><a href="">개인정보취급방침</a></li>
				 <li class="fm_list"><a href="">아동보호정책</a></li>
				 <li class="fm_list"><a href="">이용약관</a></li>
				 <li class="fm_list"><a href="">이메일 무단 수집거부</a></li>
			 </ul>
			 <div class="faddress">
				 <p class="faddr_f">광주광역시 남구 주월동</p><p class="faddr_p">사업주 김인환</p>
				 <p class="faddr_p">사업자번호 000-11-222222</p><p class="faddr_p">대표전화 010-111-1234</p>
				 <p class="faddr_l">상담시간 (월~금)09:00~18:00 (토)09:00~12:00</p>
			 </div>
		</div>
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

