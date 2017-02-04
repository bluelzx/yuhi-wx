<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'MyJsp.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="plug-in/jquery/jquery-1.8.0.min.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
	//alert(location.href.split('#')[0]);
	$(document).ready(function() {
		initPage();
	});
	function initPage() {

		$.post("test.do?test", {
			"url" : window.location.href
		}, function(data, status) {
			data = eval("(" + data + ")");
			wx.config({
				debug : true,
				appId : "wx2c73741d132b2564",
				timestamp : parseInt(data[0]),
				nonceStr : data[1],
				signature : data[3],
				jsApiList : [
				// 所有要调用的 API 都要加到这个列表中
				'checkJsApi','chooseWXPay', 'scanQRCode','chooseImage'
				,'openLocation','getLocation' ]
			});
			wx.ready(function() {
				// 在这里调用 API
				// wx.hideOptionMenu();/***隐藏分享菜单****/ 
				alert("ok");
			});
		});
	}
	function saoyisao(){
			wx.scanQRCode({
					needResult : 0, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
					scanType : [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有
					success : function(res) {
						var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
					}
				});
	}
	function chooseimage(){
		wx.chooseImage({
		    count: 1, // 默认9
		    sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
		    sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
		    success: function (res) {
		        var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
		        $("#image").attr("src",localIds);
		    }
		});
	}
	function getplace(){
		wx.getLocation({
		    type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
		    success: function (res) {
		        var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
		        var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
		        var speed = res.speed; // 速度，以米/每秒计
		        var accuracy = res.accuracy; // 位置精度
		        alert(latitude);
		        alert(longitude);
		        alert(speed);
		        alert(accuracy);
		    }
		});
	}
</script>
</head>

<body>
	This is my JSP page.
	<button onclick="saoyisao()">扫一扫</button>
	<br> ${signature}
	<br /> ${nonceStr}
	<br /> ${timestamp}
	<button onclick="chooseimage()">图片</button>
	<img id="image">
	<button onclick="getplace()">位置</button>
	
</body>
</html>
