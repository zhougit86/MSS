/*
 * ouzm1 20180831
 * ie处理
*/

//检查IE浏览器的版本
var ieVersion = IEVersion();
if(ieVersion <= 9 && ieVersion > 0){
    //alert("IE浏览器版本IE9以下");
    window.onload = function() {
        loadCSS();
        var ieversionBoxString = '<!-- IE版本过低提醒弹框start --><div class="bi-eva-msg " id="ieversion-msg"><div class="pop-msg-section ie-up-bgcolor" style="background-color: #1768B8; width: 440px;height: 520px;position: relative;margin: 0 auto;left: 0;top: 0; margin-top: 2.5%;"><div class="pop-msg-evaluation ie-up-bgcolor"style="background-color: #1768B8; height: 45px;"></div><div class="pop-msg-content" style="padding: 0;margin: 0;height: 475px;position: absolute;left:0;"><div class="pop-div-up-img"><div></div></div><div class="pop-div-msg-up-title">请升级您的浏览器</div><div id="iefixUpUser" class="iefixUpUser"></div><div class="pop-div-msg-up-content"><span>尊敬的用户，您好!</span><span>为了您能获得更安全的网络环境和更好的用户体验</span><span>我们强烈建议您升级操作系统或浏览器</span></div><div class="pop-div-up-btn-bottom"><div class="iefix-icon-chrome-hover"><a href="/upload/ChromeStandalone.exe" target="_blank"><i class="iefix-icon-chrome"></i><span>Chrome浏览器下载</span></a></div><div class="marginLeft20"><a href="/upload/Firefox-full-latest.exe" target="_blank"><i class="iefix-icon-ff"></i><span>Firefox浏览器下载</span></a></div><span class="infoZCXP" style="display:block">支持XP/Win7/Win8/Win10系统</span><!-- <div class="iefix-icon-qqHover" style="display:none;"><a href="http://browser.qq.com/" target="_blank"><i class="iefix-icon-qq"></i><span>QQ浏览器</span></a></div> --></div></div></div></div><!-- IE版本过低提醒弹框end -->';
        document.getElementsByTagName('body')[0].innerHTML = ieversionBoxString
        function loadCSS () {
            var head = document.getElementsByTagName('head')[0],
            cssURL = './static/css/ie8.css',
            linkTag = document.createElement('link');
            linkTag.setAttribute('rel','stylesheet');
            linkTag.href = cssURL;
            linkTag.setAttribute('type','text/css');
            head.appendChild(linkTag);
        }
    }
}

function IEVersion () {
    //取得浏览器的userAgent字符串
    var userAgent = navigator.userAgent;
    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器
    var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器
    var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
    var isFF = userAgent.indexOf("Firefox") > -1; //判断是否Firefox浏览器
    var isSafari = userAgent.indexOf("Safari") > -1 && userAgent.indexOf("Chrome") == -1; //判断是否Safari浏览器
    var isChrome = userAgent.indexOf("Chrome") > -1 && userAgent.indexOf("Safari") > -1; //判断Chrome浏览器
    var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器
  
    if(isIE) {
      var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
      reIE.test(userAgent);
      var fIEVersion = parseFloat(RegExp["$1"]);
      if(fIEVersion == 7) {
        return 7;
      } else if(fIEVersion == 8) {
        return 8;
      } else if(fIEVersion == 9) {
        return 9;
      } else if(fIEVersion == 10) {
        return 10;
      } else {
        return 6;//IE版本<=7
      }
    } else if(isEdge) {
      return 'edge';//edge
    } else if (isFF) {
      return "FF";
    } else if (isOpera) {
      return "Opera";
    } else if (isSafari) {
      return "Safari";
    } else if (isChrome) {
      return "Chrome";
    } else if(isIE11) {
      return "IE"; //IE11
    }else{
      return -1;//不是ie浏览器
    }
}
