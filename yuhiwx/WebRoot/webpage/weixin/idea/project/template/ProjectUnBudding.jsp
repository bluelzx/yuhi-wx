<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<!-- saved from url=(0034)http://crmwx.vanke.com:88/Customer -->
<html lang="zh-cn"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户未绑定</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <meta content="email=no" name="format-detection">
    <meta name="msapplication-tap-highlight" content="no">
	<style>
	.msg .msg-cry-icon {
    background-image: url("<%=basePath%>/userfiles/images/cry_icon.png");
    background-repeat: no-repeat;
    display: block;
    height: 225px;
    width: 300px;
	margin:0 auto;
    background-size: 300px 225px;
	}
	.img-swipe {
    position: relative;
    overflow: hidden;
	}
	.pa-lr {
    padding: 0 10px!important;
}
a, a:visited {
    text-decoration: none;
    -webkit-touch-callout: none;
    -webkit-tap-highlight-color: rgba(0,0,0,0);
}
.btn {
	float: left;
    margin-right: 5px;
    width: 48%;
    background-color: #1A9BFC;
    color: #FFFFFF;
    padding: 15px 0;
    display: block;
    text-align: center;
    -webkit-border-radius: 6px;
    border-radius: 6px;
}
	body {
    background: #EBEBEB;
    font-family: "Microsoft YaHei","Helvetica";
    -webkit-user-select: none;
    -webkit-tap-highlight-color: rgba(0,0,0,0);
    overflow: hidden;
    min-height: 100%;
}
.msg {
    text-align: center;
}
	</style>
</head>
<body>
    <div class="page">
        <div class="page-content">
            <div class="img-swipe" id="err_page" style="visibility: visible;">
                <div class="img-list" style="width: 100%">
                    <div class="img" data-index="0" style="width: 100%; left: 0px; transition-duration: 0ms; transform: translate(0px, 0px) translateZ(0px);">
                        <div class="msg pa-lr">
                            <span class="msg-icon msg-cry-icon"></span>
                            <strong class="msg-title">您还没有绑定</strong>
                            <p class="msg-desc">绑定后才能享受更多服务哟!</p>
                        </div>
                        <div class="pa-lr">
                        <a href="" class="btn f-18 ma-b">业主绑定</a>
                        <a href="" class="btn f-18 ma-b">工程人员绑定</a>
                        </div>
                    </div>
                  </div> 
            </div>
        </div>
    </div>
</body>
</html>