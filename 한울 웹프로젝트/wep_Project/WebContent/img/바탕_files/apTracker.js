var adpickFirstConversionTime=5;
var adpickLastConversionTime=30;
var adpickTrackingTerm=5;
var adpickProgress=0;

function AdpickTimeTracking(){
	if(typeof(adpickCertkey)!="undefined"){
		adpickActivationTimer=setInterval(adpickProgressCheck,1000);
	}else{
		var adpick_current_param = location.href.split('?')[1];
		if(adpick_current_param != undefined){
			var getParam = adpick_getQueryParams(adpick_current_param);
			if(getParam.adpick_certkey != undefined){
				adpickCertkey=getParam.adpick_certkey;
				adpickActivationTimer=setInterval(adpickProgressCheck,1000);
			}
		}
	}
}

function adpickProgressCheck(){
    var adpickConversionEvent="";
    adpickProgress=adpickProgress+1;
    if(adpickProgress==adpickFirstConversionTime) adpickConversionEvent="registered";
    else if(adpickProgress%adpickTrackingTerm==0) adpickConversionEvent="viewing";
    
	if(adpickConversionEvent) {
		adpick_addImage('certkey='+adpickCertkey+'&event='+adpickConversionEvent+'&dtime='+adpickProgress);
        if(adpickProgress==adpickLastConversionTime) clearInterval(adpickActivationTimer);
    }
}

function adpick_addImage(param){
    var img = document.createElement('img');
    img.width = '0';
    img.height = '0';
    img.id = 'adpick_blank';
    img.style.display = 'none';
    img.src = 'https://www.adpick.co.kr/apis/cpt.php?'+param+'&r='+(new Date()).getTime();
    if(typeof(document.getElementsByTagName('body')[0])=='undefined') document.getElementsByTagName('html')[0].appendChild(img);
	else document.getElementsByTagName('body')[0].appendChild(img);
};

function adpick_getCookie(cName) {
    cName = cName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cName);
    var cValue = '';
    if(start != -1){
       start += cName.length;
       var end = cookieData.indexOf(';', start);
       if(end == -1)end = cookieData.length;
       cValue = cookieData.substring(start, end);
    }
    return unescape(cValue);
};

function adpick_getQueryParams(qs) {
    qs = qs.split("+").join(" ");
    var params = {}, tokens,
        re = /[?&]?([^=]+)=([^&]*)/g;
    while (tokens = re.exec(qs)) {
        params[decodeURIComponent(tokens[1])]
            = decodeURIComponent(tokens[2]);
    }
    return params;
};

function adpick_setCookie(cName, cValue, cDay, cDomain){
    var expire = new Date();
    expire.setDate(expire.getDate() + cDay);
    cookies = cName + '=' + escape(cValue) + '; path=/ ';
    if(typeof cDay != 'undefined'){
        cookies += ';expires=' + expire.toGMTString() + ';';
        if(cDomain){
            cookies += 'domain='+cDomain+';path=/';
        }
        //console.log(cookies);
    }
    document.cookie = cookies;
};

AdpickTimeTracking();
