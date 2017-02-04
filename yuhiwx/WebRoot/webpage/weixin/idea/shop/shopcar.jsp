<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("basePath", basePath);
	//${pageScope.basePath}
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0118)http://www.17sucai.com/preview/216556/2016-05-21/%E6%89%8B%E6%9C%BA%E5%88%86%E9%94%80%E5%95%86%E5%9F%8E/shop_cart.html -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<meta
		content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"
		name="viewport">
		<meta content="no-cache,must-revalidate" http-equiv="Cache-Control">
			<meta content="no-cache" http-equiv="pragma">
				<meta content="0" http-equiv="expires">
					<meta content="telephone=no, address=no" name="format-detection">
						<meta name="apple-mobile-web-app-capable" content="yes">
							<meta name="apple-mobile-web-app-status-bar-style"
								content="black-translucent">
								<link href="${pageScope.basePath}plug-in/shop/css/ectouch.css"
									rel="stylesheet" type="text/css">
									<link
										href="${pageScope.basePath}plug-in/shop/css/shopcarstyle.css"
										rel="stylesheet" type="text/css">
										<link
											href="${pageScope.basePath}plug-in/shop/css/font-awesome.min.css"
											rel="stylesheet" type="text/css">
											<link
												href="${pageScope.basePath}plug-in/shop/css/iconfont.css"
												rel="stylesheet" type="text/css">
												<link
													href="${pageScope.basePath}plug-in/layer/need/layer.css"
													rel="stylesheet">
												<script
													src="${pageScope.basePath}plug-in/shop/js/TouchSlide.1.1.source.js"
													type="text/javascript"></script>
<script src="${pageScope.basePath}plug-in/shop/js/jquery-1.8.2.min.js"
	type="text/javascript"></script>
<script src="${pageScope.basePath}plug-in/shop/js/common_js.js"
	type="text/javascript"></script>
<script src="${pageScope.basePath}plug-in/shop/js/jquery.reveal.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageScope.basePath}plug-in/layer/layer.js">
	
</script>
<title>购物车</title>
</head>

<body>
	<div class="Layout_style">
		<header id="header">
		<div class="header_l header_return">
			<a href="javascript:;" onclick="javascript :history.back(-1);"
				class="return_cz"><img style="height: 30px;margin-top: 10px;" src="${pageScope.basePath}plug-in/shop/picture/icon_back.png">
			</a>
		</div>
		<h1>购物车</h1>
		<div class="header_r">
			<a class="iconfont icon-icon4"
				href="http://www.17sucai.com/preview/216556/2016-05-21/%E6%89%8B%E6%9C%BA%E5%88%86%E9%94%80%E5%95%86%E5%9F%8E/user.html">
			</a>
		</div>
		</header>
		<!--购物车样式-->
		<div class="shop_cart">
			<div class="schedule">
				<ul>
					<li class="on"><em>1</em>查看购物车</li>
					<li><em>2</em>确认订单</li>
					<li><em>3</em>成功提交订单</li>
				</ul>
			</div>
			<div class="cart_list">
				<div class="cart_filter clearfix">
					<a href="javascript:ovid(0)" class="hover">全部商品</a><a
						href="javascript:ovid(0)">预售商品</a>
				</div>
				<ul>
				
				<c:forEach items="${shopcarlist}" var="goods">
					<li class="itemlist">
						<div class="shop_Order">
							最后一次修改时间：${goods.get('create_date')}
							<div class="delete">
								<a href="javascript:ovid(0)" class="fa fa-trash-o "></a><a
									href="javascript:ovid(0)" class="fa fa-heart-o "></a>
							</div>
						</div>
						<div class="s_c_content clearfix">
							<div class="checkbox">
								<label><input name="form-field-checkbox" type="checkbox"
									class="ace"><span class="lbl"></span>
								</label>
							</div>
							<div class="img_link">
								<a
									href="http://www.17sucai.com/preview/216556/2016-05-21/%E6%89%8B%E6%9C%BA%E5%88%86%E9%94%80%E5%95%86%E5%9F%8E/shop_cart.html#"><img
									src="${pageScope.basePath}${goods.get('title_img')}" width="100%">
								</a>
							</div>
							<div class="cart_info">
								<a
									href="http://www.17sucai.com/preview/216556/2016-05-21/%E6%89%8B%E6%9C%BA%E5%88%86%E9%94%80%E5%95%86%E5%9F%8E/shop_cart.html#">
									${goods.get("title")}</a>
								<div class="Standard">
									<span>${goods.get("simple_descs")}</span>
								</div>
								<div class="price">￥${goods.get("price")}</div>
							</div>
						</div>
						<div class="Quantity_s">
							<span class="txt">
								<button type="button" class="decrease"
									onclick="changenum(0)">-</button> <input class="num"
								type="text" min="1" max="1000" name="goods_number[1789]"
								id="goods_number_1789" value="${goods.get('ws_goods_count')}" size="4"
								onkeyup="changenum(1)">
									<button type="button" class="increase"
										onclick="changenum(1789,1)">+</button>
							</span>
						</div>
					</li>
					</c:forEach>
					
					
				</ul>
			</div>
		</div>
		<footer class="toolbar">
		<p>
			<label><input name="form-field-checkbox" id="CheckedAll"
				type="checkbox" class="ace"><span class="lbl"></span>
			</label>合计: <em class="price" id="goods_subtotal">￥<span>59.00</span>
			</em>
		</p>
		<a href="javascript:;" onclick="javascript :history.back(-1);"
			class="carry_btn">继续购物</a> <a href="shopMainController.do?AddshopOrder" class="Billing"
			id="send">结算</a> </footer>
	</div>


	<script type="text/javascript">
		function changenum(isadd){
		var goodscount=$("#goods_number_1789").val();
		if(isadd){
		goodscount=parseInt(goodscount)+1;
		}else{
		goodscount=parseInt(goodscount)-1;
		}
		$("#goods_number_1789").val(goodscount);
		}
	
		//全选
		$("#CheckedAll").click(
				function() {
					if (this.checked) { //如果当前点击的多选框被选中
						$('input[type=checkbox][name=form-field-checkbox]')
								.attr("checked", true);
					} else {
						$('input[type=checkbox][name=form-field-checkbox]')
								.attr("checked", false);
					}
				});
		$('input[type=checkbox][name=form-field-checkbox]').click(
				function() {
					var flag = true;
					$('input[type=checkbox][name=form-field-checkbox]').each(
							function() {
								if (!this.checked) {
									flag = false;
								}
							});

					if (flag) {
						$('#CheckedAll').attr('checked', true);
					} else {
						$('#CheckedAll').attr('checked', false);
					}
				});
		//输出值
		$("#send")
				.click(
						function() {
							if ($(
									"input[type='checkbox'][name='form-field-checkbox']:checked")
									.attr("checked")) {
								var str = "你是否要选中的商品!\r\n";
								//$('input[type=checkbox][name=form-field-checkbox]:checked').each(function () {
								//   str += $(this).val() + "\r\n";
								// })
								layer
										.open({
											title : [ '提示框',
													'background-color:#8DCE16; color:#fff;' ],
											content : (str),
											btn : [ '确认', '取消' ],
											//shadeClose: false,
											yes : function() {
												layer.open({
													content : '提交成功',
													time : 1
												});
												location.href = "提交订单.html";
											}
										});
							} else {

								layer
										.open({
											title : [ '提示框',
													'background-color:#8DCE16; color:#fff;' ],
											content : ('你未选中任何商品，请选择后在操作'),
											btn : [ '确认' ],
										});
								//  alert(str);
							}
						});
	</script>
	<audio controls="controls" style="display: none;"></audio>
</body>
<style type="text/css">
#yddContainer {
	display: block;
	font-family: Microsoft YaHei;
	position: relative;
	width: 100%;
	height: 100%;
	top: -4px;
	left: -4px;
	font-size: 12px;
	border: 1px solid
}

#yddTop {
	display: block;
	height: 22px
}

#yddTopBorderlr {
	display: block;
	position: static;
	height: 17px;
	padding: 2px 28px;
	line-height: 17px;
	font-size: 12px;
	color: #5079bb;
	font-weight: bold;
	border-style: none solid;
	border-width: 1px
}

#yddTopBorderlr .ydd-sp {
	position: absolute;
	top: 2px;
	height: 0;
	overflow: hidden
}

.ydd-icon {
	left: 5px;
	width: 17px;
	padding: 0px 0px 0px 0px;
	padding-top: 17px;
	background-position: -16px -44px
}

.ydd-close {
	right: 5px;
	width: 16px;
	padding-top: 16px;
	background-position: left -44px
}

#yddKeyTitle {
	float: left;
	text-decoration: none
}

#yddMiddle {
	display: block;
	margin-bottom: 10px
}

.ydd-tabs {
	display: block;
	margin: 5px 0;
	padding: 0 5px;
	height: 18px;
	border-bottom: 1px solid
}

.ydd-tab {
	display: block;
	float: left;
	height: 18px;
	margin: 0 5px -1px 0;
	padding: 0 4px;
	line-height: 18px;
	border: 1px solid;
	border-bottom: none
}

.ydd-trans-container {
	display: block;
	line-height: 160%
}

.ydd-trans-container a {
	text-decoration: none;
}

#yddBottom {
	position: absolute;
	bottom: 0;
	left: 0;
	width: 100%;
	height: 22px;
	line-height: 22px;
	overflow: hidden;
	background-position: left -22px
}

.ydd-padding010 {
	padding: 0 10px
}

#yddWrapper {
	color: #252525;
	z-index: 10001;
	background:
		url(chrome-extension://eopjamdnofihpioajgfdikhhbobonhbb/ab20.png);
}

#yddContainer {
	background: #fff;
	border-color: #4b7598
}

#yddTopBorderlr {
	border-color: #f0f8fc
}

#yddWrapper .ydd-sp {
	background-image:
		url(chrome-extension://eopjamdnofihpioajgfdikhhbobonhbb/ydd-sprite.png)
}

#yddWrapper a,#yddWrapper a:hover,#yddWrapper a:visited {
	color: #50799b
}

#yddWrapper .ydd-tabs {
	color: #959595
}

.ydd-tabs,.ydd-tab {
	background: #fff;
	border-color: #d5e7f3
}

#yddBottom {
	color: #363636
}

#yddWrapper {
	min-width: 250px;
	max-width: 400px;
}
</style>
</html>