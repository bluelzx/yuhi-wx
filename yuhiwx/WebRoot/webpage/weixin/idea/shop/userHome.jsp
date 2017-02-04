<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
pageContext.setAttribute("basePath",basePath); 
//${pageScope.basePath}
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- saved from url=(0052)http://communityshop.kuaizhan.com/auth/me?embed=true -->
<html lang="zh-cn" style="font-size: 84px;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no, email=no">

    <style type="text/css">
        @font-face {
          font-family: 'icomoon';
          src: url("http://passport.kuaizhan.com/styles/fonts/icomoon.eot?-u490bm");
          src: url("http://passport.kuaizhan.com/styles/fonts/icomoon.eot?#iefix-u490bm") format("embedded-opentype"), url("http://passport.kuaizhan.com/styles/fonts/icomoon.woff?-u490bm") format("woff"), url("http://passport.kuaizhan.com/styles/fonts/icomoon.ttf?-u490bm") format("truetype"), url("http://passport.kuaizhan.com/styles/fonts/icomoon.svg?-u490bm#icomoon") format("svg");
          font-weight: normal;
          font-style: normal;
        }
        [class^="icon-"], [class*=" icon-"] {
          font-family: "icomoon";
          speak: none;
          font-style: normal;
          font-weight: 400;
          font-variant: normal;
          text-transform: none;
          font-size: 14px;
          line-height: 24px;
          -webkit-font-smoothing: antialiased;
        }
    </style>
    <script src="./个人中心_files/kzcollector.min.js"></script><script>
        (function(i,s,o,g,r,a,m) {
            i['KZAnalyticsObject'] = r; i[r] = i[r] || function() {(i[r].q = i[r].q || []).push(arguments);};
            a = s.createElement(o); m = s.getElementsByTagName(o)[0]; a.sync = 1; a.src = g;
            m.parentNode.insertBefore(a, m);
        })(window, document, "script", "//pv.kuaizhan.com/kzcollector.min.js?version=0.3", "kaq");
    </script>

    <script>
        var html = document.querySelector("html");
        var width = Math.min(document.documentElement.clientWidth, 640);
        html.style.fontSize = parseInt(width / 750 * 100 / 2) * 2 + 'px';

        window.addEventListener('resize', function () {
            var width = Math.min(document.documentElement.clientWidth, 640);
            html.style.fontSize = parseInt(width / 750 * 100 / 2) * 2 + 'px';
        });
    </script>

    <script type="text/javascript">
        var KZPP = {
            "callback": decodeURIComponent(""),
            "site_id": "2190910596"
        }
    </script>

    <!--动态加载不打包-->

    <title>个人中心</title>
    <meta name="format-detection" content="telephone=no, email=no">

    <link rel="stylesheet" href="${pageScope.basePath}plug-in/shop/css/main-57dc5c923d.css">

    <script src="./个人中心_files/lib-f5ca830961.js"></script>

    <!--动态加载不打包-->
            <link rel="stylesheet" type="text/css" href="${pageScope.basePath}plug-in/shop/css/site-2190910596.css">
</head>
<body>
    <div class="main mod-passport">
<nav class="nav-content nav-normal" style="background-color: rgb(238, 111, 94);">
    <ul>
        <li class="nav-left">
            <a href="javascript:;" onclick="javascript :history.back(-1);"
				 class="nav-cell-back go-back">
                <div class="arrow">
                    <div class="arrow-inner" style="border-right-color: rgb(238, 111, 94);"></div>
                </div>
            </a>
        </li>

            <li class="nav-title">个人中心</li>

        <li class="nav-right">
            <a class="go-home" href="shopMainController.do?getGoodsListforpage">首页</a>
        </li>

    </ul>
</nav>
        <div class="header">
            <ul class="table" data-user-id="V1-NvGerpHph5rzR">
                <li class="table-cell table-head nav-normal">
                    <span class="table-cell-avatar js-go-modify">
                            <img src="${user.headimgurl}">
                    </span>
                    <div class="table-cell-brief js-go-modify">
                        <span class="table-cell-nick">${user.nickname}</span>
                        <span class="table-cell-abstract">
                        
                        <c:if test="${user.sex==1}">
        男
                        </c:if>
                        <c:if test="${user.sex==0}">
                        女
                        </c:if>
                        
                        </span>
                        <span class="table-cell-modify">
                        </span>
                    </div>
                </li>

                <li class="table-cell js-go-score score">
                    <span class="table-cell-tag">
                    </span>
                    <div class="table-cell-content-container" type="accessory">
                        <span class="table-cell-title">所属区域</span>
                        <span class="table-cell-content user-score js-user-score">${user.partid}</span>
                        <a class="table-cell-accessory">
                            <div class="arrow">
                                <div class="arrow-inner"></div>
                            </div>
                        </a>
                    </div>
                </li>

                <li class="table-cell table-cell-phone phone table-cell-split">
                    <span class="table-cell-tag">
                    </span>
                    <div class="table-cell-content-container">
                            <span class="table-cell-title">手机</span>
                            <c:if test="${user.phone==null}">
                            <span class="table-cell-content table-cell-content-mobile js-go-mobile" style="color: #aaaaaa">未绑定</span>
                            </c:if>
                              <c:if test="${user.phone!=null}">
                            <span class="table-cell-content table-cell-content-mobile js-go-mobile" style="color: #aaaaaa">${user.phone}</span>
                            </c:if>
                            <a class="table-cell-accessory">
                                <div class="arrow">
                                    <div class="arrow-inner"></div>
                                </div>
                            </a>
                    </div>
                </li>
	 <a  href="shopMainController.do?goshopCar">
                <li class="table-cell js-go-order order">
                    <span class="table-cell-tag">
                    </span>
					
                    <div class="table-cell-content-container" type="accessory">
                        <span class="table-cell-title">我的购物车</span>
                        <a class="table-cell-accessory" >
                            <div class="arrow">
                                <div class="arrow-inner"></div>
                            </div>
                        </a>
                    </div>
                </li>
</a> 
<a  href="shopMainController.do?goshopOrder">
                <li class="table-cell js-go-crm crm">
                    <span class="table-cell-tag">
                    </span>
                    <div class="table-cell-content-container" type="accessory">
                        <span class="table-cell-title">未处理订单</span>
                        <a class="table-cell-accessory">
                            <div class="arrow">
                                <div class="arrow-inner"></div>
                            </div>
                        </a>
                    </div>
                </li>
</a>
<a  href="shopMainController.do?goshopOrder">
                <li class="table-cell js-go-club club">
                    <span class="table-cell-tag">
                    </span>
                    <div class="table-cell-content-container" type="accessory">
                        <span class="table-cell-title">历史订单</span>
                        <a class="table-cell-accessory">
                            <div class="arrow">
                                <div class="arrow-inner"></div>
                            </div>
                        </a>
                    </div>
                </li>
</a>


            </ul>
        </div>

        <div class="button-container">
         <a href="shopMainController.do?getGoodsListforpage">   <button class="btn btn-primary nav-normal js-logout">返回首页</button></a>
        </div>
    </div>

    <script src="./个人中心_files/main-72371f7a9f.js"></script>

    <div class="blank"></div>



<audio controls="controls" style="display: none;"></audio><div style="position: static; width: 0px; height: 0px; border: none; padding: 0px; margin: 0px;"><div id="trans-tooltip"><div id="tip-left-top" style="background: url(chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-left-top.png);"></div><div id="tip-top" style="background: url(chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-top.png) repeat-x;"></div><div id="tip-right-top" style="background: url(chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-right-top.png);"></div><div id="tip-right" style="background: url(chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-right.png) repeat-y;"></div><div id="tip-right-bottom" style="background: url(chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-right-bottom.png);"></div><div id="tip-bottom" style="background: url(chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-bottom.png) repeat-x;"></div><div id="tip-left-bottom" style="background: url(chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-left-bottom.png);"></div><div id="tip-left" style="background: url(chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-left.png);"></div><div id="trans-content"></div></div><div id="tip-arrow-bottom" style="background: url(chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-arrow-bottom.png);"></div><div id="tip-arrow-top" style="background: url(chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-arrow-top.png);"></div></div></body><style type="text/css">#yddContainer{display:block;font-family:Microsoft YaHei;position:relative;width:100%;height:100%;top:-4px;left:-4px;font-size:12px;border:1px solid}#yddTop{display:block;height:22px}#yddTopBorderlr{display:block;position:static;height:17px;padding:2px 28px;line-height:17px;font-size:12px;color:#5079bb;font-weight:bold;border-style:none solid;border-width:1px}#yddTopBorderlr .ydd-sp{position:absolute;top:2px;height:0;overflow:hidden}.ydd-icon{left:5px;width:17px;padding:0px 0px 0px 0px;padding-top:17px;background-position:-16px -44px}.ydd-close{right:5px;width:16px;padding-top:16px;background-position:left -44px}#yddKeyTitle{float:left;text-decoration:none}#yddMiddle{display:block;margin-bottom:10px}.ydd-tabs{display:block;margin:5px 0;padding:0 5px;height:18px;border-bottom:1px solid}.ydd-tab{display:block;float:left;height:18px;margin:0 5px -1px 0;padding:0 4px;line-height:18px;border:1px solid;border-bottom:none}.ydd-trans-container{display:block;line-height:160%}.ydd-trans-container a{text-decoration:none;}#yddBottom{position:absolute;bottom:0;left:0;width:100%;height:22px;line-height:22px;overflow:hidden;background-position:left -22px}.ydd-padding010{padding:0 10px}#yddWrapper{color:#252525;z-index:10001;background:url(chrome-extension://eopjamdnofihpioajgfdikhhbobonhbb/ab20.png);}#yddContainer{background:#fff;border-color:#4b7598}#yddTopBorderlr{border-color:#f0f8fc}#yddWrapper .ydd-sp{background-image:url(chrome-extension://eopjamdnofihpioajgfdikhhbobonhbb/ydd-sprite.png)}#yddWrapper a,#yddWrapper a:hover,#yddWrapper a:visited{color:#50799b}#yddWrapper .ydd-tabs{color:#959595}.ydd-tabs,.ydd-tab{background:#fff;border-color:#d5e7f3}#yddBottom{color:#363636}#yddWrapper{min-width:250px;max-width:400px;}</style></html>