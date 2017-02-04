<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name='apple-touch-fullscreen' content='yes'>
<meta name="full-screen" content="yes">
<meta name="format-detection" content="telephone=no" />
<title>区域绑定</title>
<link href="plug-in/shop/css/common_2.css" rel="stylesheet" type="text/css">
<script src="plug-in/shop/js/jquery.js"></script>
<script src="plug-in/shop/js/json_parse.js"></script>
<script src="plug-in/shop/js/common.js"></script>

</head>
<body>
	<div class="body_frame" style="
		padding-top:44px;
		">
		
		
			<link href="plug-in/shop/css/mall_only_2.css" rel="stylesheet" type="text/css">
		
		
		<header>
			<div class="head" id="header" style="">
                <div class="LR"><a id="btnGoBack" href="javascript:pageBack()" class="back"><img src="plug-in/shop/picture/icon_back.png"></a></div>
                <div class="center">区域绑定</div>
                <div class="LR">
                	<a href="/mall/weiindex/weiShopIndex.action" class="home" style="">
                		<img src="plug-in/shop/picture/iconmenu_home_active.png">
                	</a>
                </div>
            </div>
			
		</header>
		<!-- include -->
		




<link href="plug-in/shop/css/define.css" rel="stylesheet" type="text/css">
<script src="plug-in/shop/js/define.js"></script>
<script src="plug-in/shop/js/security.js"></script>

        
<form id="weiDoQueryOrderLogin" name="weiDoQueryOrderLogin"  action="syvscribeusersController.do?userbinding" method="POST">
	<input type="hidden" name="returnURL" value="" id="weiDoQueryOrderLogin_returnURL"/>
	<div class="select_instalment borTD">
		<div class="login_logo">
			<img alt="" src="plug-in/shop/picture/0408_logo.jpg">
		</div>
		<div class="title">
			<span class="fl w70" style="text-indent: 2em;">区&nbsp;&nbsp;&nbsp;域:&nbsp;&nbsp;&nbsp;</span>
			<select class="l_input" name="part" style="margin-top: 10px;">
			<c:forEach items="${partlist}" var="part">
			<!-- id: ${part[0]} -->
			<option value="${part[1]}" >${part[1]}</option>
			</c:forEach>
			</select>
		</div>
		<div class="title">
			<span class="fl w70">手&nbsp;&nbsp;&nbsp;机&nbsp;&nbsp;&nbsp;号:&nbsp;&nbsp;&nbsp;</span>
			<input id="mobileNo" name="mobileNo" type="text" class="l_input" placeholder="请输入手机号" value=""
				maxlength="11">
		<input id="openid" type="hidden" name="openid" value="${openid}" >
		</div>
		<div id="cvv2" class="title uhide"> 
			<span class="fl w70">CVV2</span>
			<input id="cvv2_input" type="text" class="l_input" placeholder="CVV2见卡片背面后三位" maxlength="3" value="">
			<input type="hidden" name="cvv2Cipher" value="" id="cvv2Cipher"/><!-- 隐藏域，用于保存CVV2的密文 -->
		</div>	
		<div id="message" class="title uhide" style="height: auto;line-height: 15px;min-height: 20px;padding-top: 10px;padding-bottom: 10px;">
			<span id="sendms_show" class="red" style="font-size: 13px;"></span>
		</div>
		<div class="login_btn" style="border-bottom: 0px;padding-bottom: 0px;">
			<a onclick="javascript:document.getElementById('loginBtn').click();">确认</a>
		</div>
		
		
			<div style="border-bottom: 1px solid #C7C7C7;padding:5px 0px 10px 10px;">
				<span style="font-size: 11px;color: #FF3333;">请直接选择区域和手机号(可选)绑定，无需注册。</span>
			</div>
		
	</div>
	<input type="submit" style="display: none;" id="loginBtn" />
</form>



<!--loginEnd-->

		 <!--手机数码End-->
        <!--menuStart-->
       
        <!--menuEnd-->
       <!--  <div style="height: 20px;width: 100%;"></div> -->
        <!-- 版权 -->
		<footer class="copyright">元海智云– 版权所有 沪ICP备11039870号-2</footer>
	</div>
	
	
	 <div id="popup_fail" style="display: none">
        <!--maskStart-->    
            <div class="mask"></div>
        <!--maskEnd-->        
            <div class="popup">
                <div class="title">
                    <div class="LR"></div>
                    <div class="center">提示</div>                
                    <div class="LR"><a title="关闭" class="close" onClick="closeFail()"></a></div>                
                </div>
                <div class="oper_tips">
                    <img align="absmiddle" src="plug-in/shop/picture/icon_alert.png"><span id="failMessage">对不起，操作失败！</span>
                </div>
            </div>
        </div>
        
      <div id="popup_succ" style="display: none">
      <!--maskStart-->    
          <div class="mask"></div>
      <!--maskEnd-->        
          <div class="popup">
              <div class="title">
                  <div class="LR"></div>
                  <div class="center">提示</div>                
                  <div class="LR"><a title="关闭" class="close" onClick="closeSuccess()"></a></div>                
              </div>
              <div class="oper_tips">
                  <img align="absmiddle" src="plug-in/shop/picture/icon_complete.png"><span id="succMessage">恭喜您，操作成功！</span>
              </div>
          </div>
      </div>

	<div id="popup_confirm" style="display: none;">
		<!--maskStart-->
		<div class="mask"></div>
		<!--maskEnd-->
		<div class="popup">
			<div class="title">
				<div class="LR"></div>
				<div class="center">提示</div>
				<div class="LR">
					<a title="关闭" class="close" onClick="closePopupConfirm()"></a>
				</div>
			</div>
			<div class="oper_tips">
				<img align="absmiddle" src="plug-in/shop/picture/icon_alert.png">
				<span id="confirmMessage">提示信息</span>
			</div>
			<div class="box_btns">
				<a id="btn_cancel" onClick="closePopupConfirm()" class="blur">取&nbsp;&nbsp;消</a>
				<a href="plug-in/shop/picture/4b9ea9e62cca4271aca1f9f0ddb9f43c.gif;" id="btn_sub">确&nbsp;&nbsp;定</a>
			</div>
		</div>
	</div>
	<div id="popup_waiting" style="display: none">
      <!--maskStart-->    
          <div class="mask"></div>
      <!--maskEnd-->        
          <div class="popup" align="center">
			<img align="absmiddle" src="plug-in/shop/picture/bx_loader.gif">
          </div>
      </div>
	
</body>
</html>
<script type="text/javascript">
	function showSuccess(message) {
		$("#popup_succ").css("display", "block");
		$("#popup_succ #succMessage").html(message);
	};
	function closeSuccess() {
		$("#popup_succ").css("display", "none");
	};
	function showFail(message) {
		$("#popup_fail").css("display", "block");
		$("#popup_fail #failMessage").html(message);
	};
	function closeFail() {
		$("#popup_fail").css("display", "none");
	};

	/**
	 * alertConfirm弹出
	 * @param {} message
	 * @param {} bindEvent 回调函数
	 */
	function showConfirmMessage(message,bindEvent){
		$("#popup_confirm").css("display", "block");
		$("#popup_confirm #confirmMessage").html(message);
		$("#btn_sub").removeAttr("href");
		$("#btn_sub").attr("href",bindEvent);
	}
	/**
	 * 关闭alertConfirm弹出
	 */
	function closePopupConfirm(){
		$("#popup_confirm").css("display","none");
	}
</script>
