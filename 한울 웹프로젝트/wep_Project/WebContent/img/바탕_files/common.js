
/*	function OnlyNum(){
		var key = event.keyCode;
		if(!(key==8||key==9||key==13||key==46||key==144||(key>=48&&key<=57)||key==110||key==190)){
			event.returnValue = false;
		}
	}*/
	function OnlyNum(){
		var txt  = window.event.keyCode
		if((txt >= 48 && txt <=57) || (txt >= 96 && txt <=105) || txt == 8 || txt == 9 || txt == 37 || txt == 39 || txt == 46 || txt == 13){
			window.event.returnValue = true
		}else{
			window.event.returnValue = false
		}
	}


	function isNumerics(objControl) {
		var checkStr = document.getElementById(objControl).value;
		//var checkOK = "0123456789-";
		var checkOK = "0123456789";
		var isnumeric = false;

		for (i = 0;  i < checkStr.length;  i++) {
			ch = checkStr.charAt(i);

			isnumeric = false;

			for (j = 0;  j < checkOK.length;  j++) {
				if (ch == checkOK.charAt(j)) {
					isnumeric = true;
				}
			}

			if ( isnumeric == false ){
				alert("숫자만 입력해 주세요.");
				document.getElementById(objControl).value = "";
				return false;
			}
		}
		return true;
	}


	function AllTrim(a) {
		for (; a.indexOf(" ") != -1 ;) {
			a = a.replace(" ","")
		}
		return a;
	}


	function chkdate(dayStr) {
		//dayStr -> "0000-00-00"
		var valid = false;
		if(dayStr.search(/\d{4}-(0[1-9]|1[0-2])-([0-3][0-9])/)==0) { 
			var arrDay = dayStr.split("-");
			var year = parseInt(arrDay[0]);
			var month = parseInt(arrDay[1].replace(/^0(\d)/g,"$1"));
			var day = parseInt(arrDay[2].replace(/^0(\d)/g,"$1"));
			var d = new Date(year,month-1,day);
			if(d.getMonth() == month-1 && d.getDate() == day ) valid = true ;
		}
		return valid;
	}


	var xmlHttp;

	function chkAccount(mode, val) {
		var f = document.wbForm;
		if ( mode == "valid" ) {
			if(f.a_bankcompany.value == "") {
				alert("은행을 선택하세요.");
				f.a_bankcompany.focus();
				return false;
			}
			if(f.a_banknumber.value.length < 2) {
				alert("계좌번호를 입력하세요.");
				f.a_banknumber.focus();
				return false;
			}
			if(f.a_bankname.value.length < 2) {
				alert("예금주명을 입력하세요.");
				f.a_bankname.focus();
				return false;
			}
			code = getCode(f.a_bankcompany.value);
			url = "/images/chkBankNumber.php?bankcode="+getCode(f.a_bankcompany.value)+"&banknumber="+f.a_banknumber.value;
			alert("계좌검증이 진행됩니다.\n\n잠시만 기다려주세요.");
		}
//		f.a_verify_url.value = url;
		
		xmlHttp = GetXMLHttp();
		xmlHttp.open('POST', url, true);
		xmlHttp.onreadystatechange = callback2;
		xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xmlHttp.setRequestHeader('Content-length', '0');
		xmlHttp.send('');
	}

	function callback2() {
		if(xmlHttp.readyState == 4) {
			if(xmlHttp.status == 200) {
				var data = xmlHttp.responseText;
			//	alert(data);
				var info = decodeURIComponent(data);
			//	alert(info);

				var f = document.wbForm;
				if( info.indexOf("오류") > 0) {
					alert("유효하지 않은 계좌번호입니다.");
					f.a_banknumber.focus();
					f.a_verify.value = 0;
				} else if( info.indexOf("ERR") > 0) {
					alert("시스템 오류입니다.\n\n(현재 조회가 불가능합니다.)");
					f.a_verify.value = 0;
				} else if (info.indexOf("시간") > 0) {
					alert("불편을 드려 죄송합니다.\n\nPM11:50~AM00:30까지\n자동이체 정보검증이 되지 않습니다.");
					f.a_verify.value = 0;
				} else {
					if (info == f.a_bankname.value) {
						alert("계좌검증이 완료되었습니다.");
						f.a_verify.value = 1;
					} else {
						alert("조회된 예금주와 입력된 예금주가 다릅니다.");
						f.a_bankname.focus();
						f.a_verify.value = 0;
					}
				}
			} else {
				alert("에러 발생 : " + xmlHttp.statusText);
			}
		}
	}

	function GetXMLHttp() {
		if(window.XMLHttpRequest) return new XMLHttpRequest();
		var versions = [ "MSXML2.XMLHttp.5.0", "MSXML2.XMLHttp.4.0", "MSXML2.XMLHttp.3.0", "MSXML2.XMLHttp", "Microsoft.XMLHttp" ];
		for(var i = 0; i < versions.length; i++) {
			try {
				var oXMLHttp = new ActiveXObject(versions[i]);
				return oXMLHttp;
			} catch(e){}
		}		
		throw new Error("No XMLhttp");
	}



	function getCode(val) {
		var code;
		switch (val) {
			case "한국은행" :
				code = "001";
				break;
			case "산업은행" :
				code = "002";
			case "기업은행" :
				code = "003";
				break;
			case "국민은행" :
				code = "004";
				break;
			case "외환은행" :
				code = "005";
				break;
			case "수협" :
				code = "007";
				break;
			case "농협중앙회" :
				code = "011";
				break;
			case "단위농협" :
				code = "012";
				break;
			case "우리은행" :
				code = "020";
				break;
			case "스탠다드차타드은행" :
				code = "023";
				break;
			case "씨티은행" :
				code = "027";
				break;
			case "대구은행" :
				code = "031";
				break;
			case "부산은행" :
				code = "032";
				break;
			case "광주은행" :
				code = "034";
				break;
			case "제주은행" :
				code = "035";
				break;
			case "전북은행" :
				code = "037";
				break;
			case "경남은행" :
				code = "039";
				break;
			case "새마을금고" :
				code = "045";
				break;
			case "신협" :
				code = "048";
				break;
			case "상호저축은행" :
				code = "050";
				break;
			case "HSBC은행" :
				code = "054";
				break;
			case "우체국" :
				code = "071";
				break;
			case "하나은행" :
				code = "081";
				break;
			case "신한은행" :
				code = "088";
				break;
			case "동양증권" :
				code = "209";
				break;
			case "현대증권" :
				code = "218";
				break;
			case "미래에셋증권" :
				code = "230";
				break;
			case "KDB대우증권" :
				code = "238";
				break;
			case "삼성증권" :
				code = "240";
				break;
			case "한국투자증권" :
				code = "243";
				break;
			case "우리투자증권" :
				code = "247";
				break;
			case "하이투자증권" :
				code = "262";
				break;
			case "HMC투자증권" :
				code = "263";
				break;
			case "SK증권" :
				code = "266";
				break;
			case "대신증권" :
				code = "267";
				break;
			case "한화투자증권" :
				code = "269";
				break;
			case "하나대투증권" :
				code = "270";
				break;
			case "신한금융투자" :
				code = "278";
				break;
			case "동부증권" :
				code = "279";
				break;
			case "유진투자증권" :
				code = "280";
				break;
			case "메리츠종금증권" :
				code = "287";
				break;
			case "농협증권" :
				code = "289";
				break;
			case "부국증권" :
				code = "290";
				break;
			case "신영증권" :
				code = "291";
				break;

			default :
				code = "";
		}
		return code;
	}
