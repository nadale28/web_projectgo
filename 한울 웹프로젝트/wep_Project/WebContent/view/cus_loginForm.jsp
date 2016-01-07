<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ include file = "../view/cus_color.jsp" %>

<html>
<head><title>로그인</title>
<link href = "cus_style.css" rel = "stylesheet" type = "text/css">
<script language = "javascript">

	function begin()
	{
		document.myform.id.focus();
	}

	function checkIt()
	{
		if(!document.myform.id.value)
		{
			alert("아이디를 입력하세요.");
			document.myform.id.focus();
			return false;
		}

		if(!document.myform.passwd.value)
		{
			alert("비밀번호를 입력하세요.");
			document.myform.passwd.focus();
			return false;
		}
	}

</script>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script>
$(document).ready(
		function() {
			$('#login_fail').on(
					'click',
					function() {
						var uid = $('#id').val();
						var upasswd=$('#passwd').val();
						

						$.getJSON('view/cus_login_fail.jsp', {
							id : uid,
							passwd: upasswd
						}, function(data) {

							if (data.id == "") {//아이디 중복
							
								alert("아이디 또는 비번이 틀립니다.");
								location.href="cus_loginform.do";
							} else {
								location.href="cus_login.do?id="+uid+"&passwd="+upasswd;
							}
						});
					});
		});

</script>

        <style>
      @import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
*{
	margin:0;
	}
body{
	margin: 0;
	padding: 0;
	background: #fff;

	color: #fff;
	font-family: 'Nanum Gothic', sans-serif;
	font-size: 12px;
}

.body{
	position: absolute;
	top: -20px;
	left: -20px;
	right: -40px;
	bottom: -40px;
	width: auto;
	height: auto;
	background-image: url(img/174.jpg);
	background-size: cover;
	-webkit-filter: blur(5px);
	z-index: 0;
}

.grad{
	position: absolute;
	top: -20px;
	left: -20px;
	right: -40px;
	bottom: -40px;
	width: auto;
	height: auto;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(0,0,0,0)), color-stop(100%,rgba(0,0,0,0.65))); /* Chrome,Safari4+ */
	z-index: 1;
	opacity: 0.7;
}
.header{
	position: absolute;
	top: calc(54% - 35px);
	left: calc(50% - 255px);
	z-index: 2;
}

.header div{
	float: left;
	color: #fff;
	font-family: 'Nanum Gothic', sans-serif;
	font-size: 35px;
	font-weight: 200;
}

.header div span{
	color: #222 !important;
}

.login{
	position: absolute;
	top: calc(55% - 75px);
	left: calc(50% - 50px);
	height: 150px;
	width: 350px;
	padding: 10px;
	z-index: 2;
}

.login input[type=text]{
	width: 250px;
	height: 30px;
	background: transparent;
	border: 1px solid rgba(255,255,255,0.6);
	border-radius: 2px;
	color: #fff;
	font-family: 'Nanum Gothic', sans-serif;
	font-size: 16px;
	font-weight: 400;
	padding: 4px;
}

.login input[type=password]{
	width: 250px;
	height: 30px;
	background: transparent;
	border: 1px solid rgba(255,255,255,0.6);
	border-radius: 2px;
	color: #fff;
	font-family: 'Nanum Gothic', sans-serif;
	font-size: 16px;
	font-weight: 400;
	padding: 4px;
	margin-top: 10px;
}

.login input[type=button]{
	width: 80px;
	height: 35px;
	background: #fff;
	border: 1px solid #fff;
	cursor: pointer;
	border-radius: 2px;
	color: #a18d6c;
	font-family: 'Nanum Gothic', sans-serif;
	font-size: 16px;
	font-weight: 400;
	padding: 6px;
	margin-top: 10px;
}

.login input[type=button]:hover{
	opacity: 0.8;
}

.login input[type=button]:active{
	opacity: 0.6;
}

.login input[type=text]:focus{
	outline: none;
	border: 1px solid rgba(255,255,255,0.9);
}

.login input[type=password]:focus{
	outline: none;
	border: 1px solid rgba(255,255,255,0.9);
}

.login input[type=button]:focus{
	outline: none;
}

::-webkit-input-placeholder{
   color: rgba(255,255,255,0.6);
}

::-moz-input-placeholder{
   color: rgba(255,255,255,0.6);
}
.hover {
		background:transparent;  
		text-align:center; transition:all 0.8s, color 0.3s 0.3s;  
		color:#333; cursor: pointer;  
		padding:10px 15px;
		border: 1px solid #333;
		border-radius:5px;
	}
.hover:hover{
		color:#fff;
	}

.effect3:hover{
		box-shadow:0 150px 0 0 rgba(0,0,0,0.75) inset;
	}

    </style>

    
        <script src="js/prefixfree.min.js"></script>

    
  </head>

  <body>
<form name = "myform" action = "cus_login.do" method = "post"
	onSubmit = "return checkIt()">
    <div class="body"></div>
		<div class="grad"></div>
		<div class="header">
			<div>Login<span> Page</span></div>
		</div>
		<br>
		<div class="login">
				<input type="text" placeholder="아이디" name="id" id="id"><br>
				<input type="password" placeholder="비밀번호" name="passwd" id="passwd"><br>
				<input type ="text" value = "아이디/비밀번호 찾기" onclick = "javascript:window.location='cus_searchIdForm.do'" style="border:0px; cursor: pointer;">
				
				<input type="button" id="login_fail" class="hover effect3" value="로그인" style="position:absolute; 
				top: calc(43% - 35px); right: calc(60% - 255px);">
		</div>
		</form>
		<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    
    
    
    
  </body>
</html>