﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
pageContext.setAttribute("basePath",basePath); 
//${pageScope.basePath}
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





<title>元海微商城</title>

<link href="${pageScope.basePath}plug-in/shop/css/common_1.css" rel="stylesheet" type="text/css">
<script src="${pageScope.basePath}plug-in/shop/js/jquery.js"></script>
<script src="${pageScope.basePath}plug-in/shop/js/json_parse.js"></script>
<script src="${pageScope.basePath}plug-in/shop/js/common.js"></script>

<script type="text/javascript">
	//js全局项目路径
	var applicationContextPath = '/mall';
	var imagePath='http://img.leagcard.com/cms/online';
	var installmentFlag = '1';// 是否支持分期
	function pageBack() {
		var a = window.location.href;
		if (/#top/.test(a)) {
			window.history.back(-2);
			window.location.load(window.location.href);
		} else {
			window.history.back(-1);
			window.location.load(window.location.href);
		}
	}

	function chiosePage(titleId) {
		$("#headerTitle a").removeClass("active");
		$("#" + titleId).addClass("active");
		//移动到选中菜单
		$("#headerTitle a").each(function(i,obj){
			if(obj.id == titleId){
				$(obj).addClass("cur");
			} else {
				$(obj).removeClass("cur");
			}
		});
	}
	/*搜索*/
	function entersearch(keyword, productType){
		/* if(keyword == null || keyword.trim() == ''){
			alert("请输入搜索条件!");
			return false;
		} */
		var event = window.event || arguments.callee.caller.arguments[0];
        if (event.keyCode == 13 || event.button == 0)
        {
        	var url = '/mall/weiproduct/weiProductList.action?keyword='+keyword + '&productType=' + productType + '&productCtg=1106';
        	/* if($("#pointCondition").val() != '' && $("#pointCondition").val() > 0){
        		url += '&pointCondition=' + $("#pointCondition").val();
        	} */
        	window.location.href = url;
        }
	}
	
	/*综合搜索*/
	function entersearchProduct(keyword){
		/* if(keyword == null || keyword.trim() == ''){
			alert("请输入搜索条件!");
			return false;
		} */
		var event = window.event || arguments.callee.caller.arguments[0];
	    if (event.keyCode == 13 || event.button == 0)
	    {
	    	var url = '/mall/weiproduct/weiSearchProductList.action?productCtg=1106&classType=&keyword='+keyword;
	    	/* if($("#pointCondition").val() != '' && $("#pointCondition").val() > 0){
	    		url += '&pointCondition=' + $("#pointCondition").val();
	    	} */
	    	window.location.href = url;
	    }
	}
	/*综合搜索*/
	function insearchProduct(classType,keyword){
		/* if(keyword == null || keyword.trim() == ''){
			alert("请输入搜索条件!");
			return false;
		} */
		var event = window.event || arguments.callee.caller.arguments[0];
	    if (event.keyCode == 13 || event.button == 0)
	    {
	    	var url = '/mall/weiproduct/weiSearchProductList.action?productCtg=1106&classType='+classType+'&keyword='+keyword;
	    	/* if($("#pointCondition").val() != '' && $("#pointCondition").val() > 0){
	    		url += '&pointCondition=' + $("#pointCondition").val();
	    	} */
	    	window.location.href = url;
	    }
	}
	/*综合搜索菜单筛选*/
	function searchProductCtg(classType, productCtg) {
		window.location.href = applicationContextPath + '/weiproduct/weiSearchProductList.action?productCtg=' + productCtg + '&classType=' + classType + '&keyword=';
	}
	
	/*综合搜索积分范围筛选*/
	function searchPointRange(classType, pointRange) {
		window.location.href = '/mall/weiproduct/weiSearchProductList.action?classType=' + classType + '&keyword=&pointRange=' + pointRange + '&orderType=price&orderSortBy=desc';
	}
	
	function changeHeaderTtile(title){
		try {
			$("#header .center").html(title);
		} catch (e) {
			
		}
	}
	
	/*菜单筛选*/
	function selectproductCtg(productType, productCtg) {
		window.location.href = '/mall/weiproduct/weiProductList.action?productType=' + productType + '&productCtg=' + productCtg;
	}
	
	/*积分范围筛选*/
	function selectPointRange(productType, pointRange) {
		window.location.href = '/mall/weiproduct/weiProductList.action?productType=' + productType + '&pointRange=' + pointRange + '&orderType=price&orderSortBy=desc';
	}
</script>

</head>
<body>
	<div class="body_frame" style="
		padding-top:44px;
		">
		
		
			<link href="${pageScope.basePath}plug-in/shop/css/mall_only_1.css" rel="stylesheet" type="text/css">
		
		
		<header>
			<div class="head" id="header" style="">
                <div class="LR"><a id="btnGoBack" href="javascript:pageBack()" class="back"><img src="${pageScope.basePath}plug-in/shop/picture/icon_back.png"></a></div>
                <div class="center"></div>
                <div class="LR">
                	<a href="/mall/weiindex/weiShopIndex.action" class="home" style="">
                		<img src="${pageScope.basePath}plug-in/shop/picture/iconmenu_home_active.png">
                	</a>
                </div>
            </div>
			
		</header>
		<!-- include -->
		





<script src="${pageScope.basePath}plug-in/shop/js/jquery.bxslider.min.js"></script>
<link href="${pageScope.basePath}plug-in/shop/css/jquery.bxslider_1.css" type="text/css" rel="stylesheet">
<script type="text/javascript">
var productCategory = '1'; //E-电子礼券，1-普通商品
var productStock = '866'; //库存
var inputBuyAmount = parseInt($.trim($('#buyAmount').val()));
var productBelongs='1';
var  freeTaxLimit = '0';
$(document).ready(function(){
	$(".btm_menu").css("display","none");
	$(".copyright").css("display","none");
	changeHeaderTtile('商品详情');
	$('.slider1').bxSlider({ //slider1是需要展示的横向流布局的DIV
		/*slideWidth: 200,*/   //每个横向流布局图片的团度
		minSlides: 1,		//每隔横向流布局最少要有几个图片
		maxSlides: 1,      //每个横向流布局最多展示几个图片
		slideMargin: 3,		//每个很想流布局之间的宽度
		auto: true
		});
});
</script>
<form id="productDetailForm" method="POST" action="/mall/weiorder/weiConfirmProduct.action">
	<input type="hidden" name="productId" value="${goods.id }" />
	<input type="hidden" name="wsGoodsPrice" value="${goods.price }" />
		
			<input type="hidden" name="productItem.buyType" value="0" id="buyType"/>
	
	<input type="hidden" id="fea" name="productItem.fea"/>
<div class="details_frame">
   	<a href="javascript:pageBack();" class="box_pageBack">
		<span></span>
		<font></font>
	</a>
	<header>
	</header>
	<div class="product_details">
		<!--galleryStart-->
		<div class="p_imgs">
			<div class="slider1">
				
					<div class="slide">
						<a onClick="show('${pageScope.basePath}plug-in/shop/picture/160555-1306901014.jpg')">
							<img src="${pageScope.basePath}plug-in/shop/picture/160555-1306901014.jpg" onerror="javascript:this.src='/mall/style/v3/weimall/images/no_img.jpg'"> 
						</a>
					</div>
				
					<div class="slide">
						<a onClick="show('${pageScope.basePath}plug-in/shop/picture/160555-1884653618.jpg')">
							<img src="${pageScope.basePath}plug-in/shop/picture/160555-1884653618.jpg" onerror="javascript:this.src='/mall/style/v3/weimall/images/no_img.jpg'"> 
						</a>
					</div>
				
					<div class="slide">
						<a onClick="show('${pageScope.basePath}plug-in/shop/picture/160555-1883730097.jpg')">
							<img src="${pageScope.basePath}plug-in/shop/picture/160555-1883730097.jpg" onerror="javascript:this.src='/mall/style/v3/weimall/images/no_img.jpg'"> 
						</a>
					</div>
				
					<div class="slide">
						<a onClick="show('${pageScope.basePath}plug-in/shop/picture/160555-1882806576.jpg')">
							<img src="${pageScope.basePath}plug-in/shop/picture/160555-1882806576.jpg" onerror="javascript:this.src='/mall/style/v3/weimall/images/no_img.jpg'"> 
						</a>
					</div>
				
					<div class="slide">
						<a onClick="show('${pageScope.basePath}plug-in/shop/picture/160555-1881883055.jpg')">
							<img src="${pageScope.basePath}plug-in/shop/picture/160555-1881883055.jpg" onerror="javascript:this.src='/mall/style/v3/weimall/images/no_img.jpg'"> 
						</a>
					</div>
				
					<div class="slide">
						<a onClick="show('${pageScope.basePath}plug-in/shop/picture/160555-1880959534.jpg')">
							<img src="${pageScope.basePath}plug-in/shop/picture/160555-1880959534.jpg" onerror="javascript:this.src='/mall/style/v3/weimall/images/no_img.jpg'"> 
						</a>
					</div>
				
					<div class="slide">
						<a onClick="show('${pageScope.basePath}plug-in/shop/picture/160555-1880036013.jpg')">
							<img src="${pageScope.basePath}plug-in/shop/picture/160555-1880036013.jpg" onerror="javascript:this.src='/mall/style/v3/weimall/images/no_img.jpg'"> 
						</a>
					</div>
				
			</div>
			<!-- 展现弹出之后的图片 -->
			<div id="imgDiv" style="display:none;"><a href="javascript:r();"><img id="imgBase" src=""/></a></div>
			
			
				
					<span class="p_saleNum">已售出&nbsp;${goods.getSellCount()}&nbsp;件</span>
				
			
		</div>
		<!--galleryEnd-->
		<div class="p_info">
			<h5>${goods.title }</h5>
            <h4></h4>
			
			
			
				<h4 style="color: #333;">商户电话：
					<a href="tel:400-680-3659">
						400-680-3659 
						<img alt="" width="10" height="12" src="${pageScope.basePath}plug-in/shop/picture/icon_phone.png"/>
					</a>
				</h4>
			
			
			
				<h6 style="height: auto;">
					
					
				</h6>
				<input type="hidden" name="" value="${goods.price }" id="singlePrice"/>
			
			
		</div>
		
		
		
			<div class="slt_instal" id="partSelect" style="height: auto;">
				<font>购买价格：</font>
				<div style="float: left;">
					
						<a onclick="selectExchange(0);" class="active" style="width: auto;padding: 0px 5px 0px 5px;">${goods.price }元</a>
						<br/>
					
					
				</div>
				<div style="clear: both;"></div>
			</div>
			<script type="text/javascript">
			$(function(){
				var exchangeType = 0;
				
				selectExchange(exchangeType);
				if($("#partSelect a").size() == 1){
					$("#partSelect a").addClass("active").siblings().removeClass();
				}
				$("#partSelect a").click(function(){
					$(this).addClass("active").siblings().removeClass();
				});
			});
			
			function selectExchange(exchangeType){
				$('#buyType').val(exchangeType);
				$('#buyAmount').val(1);
				checkBtnByStock(1, parseInt(productStock));
				calculationPrice();
			}
			</script>
		
		<div id="instmsg" style="height: 25px;line-height: 25px;padding: 3px 0px 4px 10px;border-bottom: 1px solid #DCDCDC;display: none;">
			
		</div>
		<div id="freeRate" style="height: 25px;line-height: 25px;padding: 3px 0px 4px 10px;border-bottom: 1px solid #DCDCDC;display: none;">
			
		</div>
		
		
		<div class="setNum">
			数量
			<span>
				<a id="btnMinus" href="javascript:minus()" class="disable">-</a>
				<input id="buyAmount" name="buyAmount" type="text" value="1" maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'1')" readonly="readonly">
				<a id="btnAdd" href="javascript:add();">+</a>
			</span>
		</div>
		<!-- 活动资格查询按钮 -->
		
		<div onclick="toValidQualification()" style="display: none;height: 32px;overflow: hidden;" id="qualification">
        	<a class="selectType">
        		活动资格查询
        		<span></span>
        	</a>
       	</div>
		<!--menuStart-->
		<menu class="borB">
			<a id="anr" href="javascript:tabOn('1');"  class="borRs" style="width: 100%;">商品介绍</a>
			<!-- <a id="acs" href="javascript:tabOn('2');">规格参数</a> -->
		</menu>
		<div class="show_details" onclick="tabOn('1');" id="showLine">-------------&nbsp;点击查看图文详情&nbsp;-------------</div>
		<div class="p_intro" id="nr" style="display: none;"></div>
		<div class="p_intro" id="cs" style="display: none;"></div>
         
          <!--menuEnd-->
          <div class="p_buy" style="min-height: 50px;height: auto;">
          	
    			
    			
    				
    					<span><label id="totalPrice">${goods.price }</label><a onclick="buy_submit();" class="buyNow" >立即购买</a></span>
    					<a onclick="add_shopcar();" class="buyNow" style="background-color: #FF2B02;">加入购物车</a>
    				
    				
	    		
	    		
    		
    			
          </div>
      </div>
	</div>
 </form>
 
 	<!-- 规格弹出层开始 -->
	<div id="selectType_cont" style="display: none;">
		<!--maskStart-->
		<div class="mask"></div>
		<!--maskEnd-->
		<div class="popup">
			<div class="title">
				<div class="LR"></div>
				<div class="center">选择</div>
				<div class="LR">
					<a title="关闭" class="close" onClick="close_cont('selectType_cont')"></a>
				</div>
			</div>
			<div class="listType">
				<ul>
					
				</ul>
			</div>
			<div class="box_btns box_btns_one">
				<a onClick="close_cont('selectType_cont')">确&nbsp;&nbsp;定</a>
			</div>
		</div>
	</div>
	<!-- 规格弹出层结束 -->
	
 <script type="text/javascript">
 var istmentPartRefundFlag = '0';// 是否支持部分退货
$(function(){
	// 更新加减按钮状态以及事件
	checkBtnByStock(parseInt($('#buyAmount').val()), parseInt(productStock));
	if('2' == '1' && '1' == installmentFlag){
		// 根据默认选中的分期数初始化分期显示
		$("a[id^=aInstment_]").each(function(i,obj){
			var className = $(obj).attr("class");
			if(className && className.indexOf("active") > -1){
				var id = obj.id.split("_")[1];
				if(!isNaN(id)){
					selectInst(id);
				}
				return false;
			}
		});
	}
	initImgWidth();
});
/**
 * 设置商品详情图片100%宽度
 */
function initImgWidth(){
	// 详情图片宽度适应设置
	$(".p_intro img").each(function(i,obj){
		$(obj).css("width","100%");
		$(obj).css("height","auto");
		$(obj).removeAttr("width");
		$(obj).removeAttr("height");
	});
}


/*
 * 分期数选择
 */
function selectInst(instmentNum){
	$('a[id^=aInstment]').removeAttr('class','active');
	$('#buyType').val(instmentNum);
	$('#aInstment_'+instmentNum).attr('class','active');
	$("#instmsg").css("display","none");
	var btnOptions = {'minusFlag':false,'addFlag':true};
	if($('#buyAmount').val() != 1){
		btnOptions.minusFlag = true;
	} else {
		btnOptions.minusFlag = false;
	}
	// 不支持部分退货，并且分期数不为1，则显示提示信息
	if(instmentNum != '1' && istmentPartRefundFlag == '0'){
		$("#instmsg").css("display","block");
		$('#buyAmount').val(1);
		$("#freeRate").css("display","none");
		btnOptions.minusFlag = false;
		btnOptions.addFlag = false;
	}
	// 选择期数改变月供数据
	var perPrice = parseFloat($("#inst_" + instmentNum).val()).toFixed(2);
	var html = '<label>￥' + perPrice + '</label> X ' + instmentNum + '期';
	$('#total_price').html(html);
	calculationPrice();
	checkAmountBtn(btnOptions);// 更新加减按钮样式以及事件
}

/**
 * 控制加减按钮是否有效
 * var options = {options.minusFlag:true,options.addFlag:true};
 */
function checkAmountBtn(options){
	if(!options || options == null || options == '' || options == 'undefined'){
		$('#btnAdd').removeClass('disable');
		$('#btnAdd').attr('href','javascript:add();');
		$('#btnMinus').removeClass('disable');
		$('#btnMinus').removeAttr('href','javascript:minus();');
		return ;
	}
	if(options && options.minusFlag){
		$('#btnMinus').removeClass('disable');
		$('#btnMinus').attr('href','javascript:minus();');
	} else {
		$('#btnMinus').addClass('disable');
		$('#btnMinus').removeAttr('href');
	}
	if(options && options.addFlag){
		$('#btnAdd').removeClass('disable');
		$('#btnAdd').attr('href','javascript:add();');
	} else {
		$('#btnAdd').addClass('disable');
		$('#btnAdd').removeAttr('href');
	}
};

function checkBtnByStock(buyAmount,stockAmount){
	var btnOptions = {'minusFlag':true,'addFlag':true};
	if(buyAmount == 1){
		btnOptions.minusFlag = false;
	}
	if(buyAmount >= stockAmount){
		btnOptions.addFlag = false;
	}
	checkAmountBtn(btnOptions);
}

 /*
 * 详细参数切换
 */
 function tabOn(value){
		if ('1' == value){
			$("#nr").show();
			$("#cs").hide();
			$("#acs").removeAttr("class");
			$("#anr").attr("class","active borRs");
		} else if ('2' == value){
			$("#nr").hide();
			$("#cs").show();
			$("#anr").removeAttr("class");
			$("#anr").addClass("borRs");
			$("#acs").attr("class","active");
		}
	//showDesction(value);
		showDetail();
	}
 
/**
 * 增加数量
 */
 function add(){
		var newBuyAmount = parseInt($('#buyAmount').val()) + 1;
		if ('E' == productCategory){
			// 判断是否超限购买
			if (newBuyAmount > 5){
				$('#buyAmount').val(5);
				newBuyAmount = 5;
				productStock = 5;// 电子礼券库存设置为5
			} else {
				if (parseInt(productStock) < newBuyAmount){
					showFail('库存不足，请修改购买数量，最多可购买' + productStock + "件");
					$('#buyAmount').val(1);
					newBuyAmount = 1;
				} else {
					$('#buyAmount').val(newBuyAmount);
				}
			}
		}  else if ('4' == productBelongs){ 
			// 判断是否超限购买
			if (newBuyAmount > freeTaxLimit){
				$("#freeRate").css("display","block");
				newBuyAmount = $('#buyAmount').val();
				$("#taxtip").html("单笔订单最多购买"+newBuyAmount+"件");
				$('#buyAmount').val(newBuyAmount);
			} else {
				if (parseInt(productStock) < newBuyAmount){
					showFail('库存不足，请修改购买数量，最多可购买' + productStock + "件");
					$('#buyAmount').val(1);
					newBuyAmount = 1;
				} else {
					$('#buyAmount').val(newBuyAmount);
				}
			}
		} else {
			if (newBuyAmount > 99) {
				showFail("对不起，购买数量为1-99！");
				$('#buyAmount').val(1);
				newBuyAmount = 1;
			}else if (parseInt(productStock) < newBuyAmount){
				showFail('库存不足，请修改购买数量，最多可购买' + productStock + "件");
				$('#buyAmount').val(1);
				newBuyAmount = 1;
			} else {
				$('#buyAmount').val(newBuyAmount);
			}
		}
		calculationPrice();
		// 更新加减按钮状态以及事件
		checkBtnByStock(parseInt(newBuyAmount), parseInt(productStock));
	}
	/**
	 * 数量减少
	 */
	function minus(){
		var minusBuyAmount = parseInt($('#buyAmount').val()) - 1;
		if (minusBuyAmount < 1) {
			$('#buyAmount').val(1);
			minusBuyAmount = 1;
		} else {
			$('#buyAmount').val(minusBuyAmount);
		}
		if ('4' == productBelongs){ 
			// 判断是否超限购买
			if (minusBuyAmount < freeTaxLimit){
				$("#freeRate").css("display","none");
			} 
		} 
		calculationPrice();
		// 更新加减按钮状态以及事件
		checkBtnByStock(parseInt(minusBuyAmount), parseInt(productStock));
	}

	/**
	 * 检查数量
	 */
	function checkBuyAmount(){
		var  amount=$('#buyAmount').val();
		if(!/^[0-9]*$/.test(amount)){
			showFail("只能输入数字");	
			return;
		}
		var inputBuyAmount = parseInt($.trim($('#buyAmount').val()));
		if(isNaN(inputBuyAmount) || inputBuyAmount < 1){
			$('#buyAmount').val(1);
			inputBuyAmount = 1;
		}
		if ('E' == productCategory){
			if (inputBuyAmount > 5){
				showFail('最多可购买5件');
				$('#buyAmount').val(5);
				inputBuyAmount = 5;
				productStock = 5;
			} else {
				if (parseInt(productStock) < inputBuyAmount){
					showFail('库存不足，请修改购买数量，最多可购买' + productStock + "件");
					$('#buyAmount').val(1);
					inputBuyAmount = 1;
				} 
			}
		} else {
			if (inputBuyAmount > 99) {
				showFail("对不起，购买数量为1-99！");
				$('#buyAmount').val(1);
				inputBuyAmount = 1;
			} else if (parseInt(productStock) < inputBuyAmount){
				showFail('库存不足，请修改购买数量，最多可购买' + productStock + "件");
				$('#buyAmount').val(1);
				inputBuyAmount = 1;
			}
		}
		calculationPrice();
		// 更新加减按钮状态以及事件
		checkBtnByStock(parseInt(inputBuyAmount), parseInt(productStock));
	}
	
	/**
	 * 提交（非分期）
	 */
	function buy_submit() {
		$("#productDetailForm").attr("action","${pageScope.basePath}shopMainController.do?AddshopOrder");
		$("#productDetailForm").submit();
	}
	/**
	 * 加入购物车
	 */
	function add_shopcar() {
		$("#productDetailForm").attr("action","${pageScope.basePath}shopMainController.do?AddshopCar");
		$("#productDetailForm").submit();
	}
	
	/**
	 * 提交（分期）
	 */
	function instBuy_submit() {
		$("#instalment_cont").css("display","block");
		window.scrollTo(0,0);
	}
	
	/**
	* 计算总价
	*/
	function calculationPrice(){
		var num=parseInt($('#buyAmount').val());
		var singlePrice=$("#singlePrice").val();
		var buyType = parseInt($('#buyType').val());
		singlePrice=singlePrice.replace(/,/ig,"");
		//积分总价
		var totalPrice= num*singlePrice + '元';
		//现金总价
		if('2' == '1'){
			totalPrice = '￥' + fmoney(totalPrice,2);
			//清空单价位置显示总手续费
			$("#feeAmt").html('');
			$("#feeTips").hide();
			//支持分期并且分期数大于1,显示分期价格
			if($('#inst_' + buyType).val() && buyType > 1 && installmentFlag == '1'){
				totalPrice = parseFloat($('#inst_' + buyType).val()) * num;
				totalPrice = '￥' + fmoney(totalPrice,2);
				totalPrice += " X " + buyType + "期";
				//单价位置显示总手续费
				var instFeeAmount = parseFloat($("#instFeeAmount_" + buyType).val());
				if(instFeeAmount > 0){
					$("#feeAmt").html(fmoney(instFeeAmount, 2));
					$("#feeTips").show();
				}
			}
		}
		//补差价
		if(buyType == -1){
			var salePartCredits = parseInt($("#salePartCredits").val());
			var partPrice = parseFloat($("#partPrice").val());
			totalPrice = (salePartCredits*num) + '元';
			totalPrice += '+￥' + fmoney(partPrice*num, 2);
		}
		$("#totalPrice").html(totalPrice);
	}
	
	/**
	 * 格式化金额
	 */
	function fmoney(s, n) { 
		n = n > 0 && n <= 20 ? n : 2; 
		s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + ""; 
		var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1]; 
		t = ""; 
		for (var i = 0; i < l.length; i++) { 
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : ""); 
		} 
		return t.split("").reverse().join("") + "." + r; 
	} 
	//展示图片
	function show(src){
		$('#imgBase').attr("src",src);
		$('#imgDiv').toggle();
	}
	//图片隐藏
	function r(){
		//$('.bx-wrapper').toggle();
		$('#imgDiv').hide();
	}
	
	
	
	/*
	* 加载详细信息参数
	*/
	function showDesction(id){
		if('1' == id){
			fillAndScrollTo('nr', descriptions);
		}
		if('2' == id){
			fillAndScrollTo('cs', specifications);
		}
		initImgWidth();
	}
	/**
	 * 填充数据并向下滚动一段距离
	 * @param id:标签ID,content:标签显示内容
	 */
	function fillAndScrollTo(id, content){
		if ($('#' + id).html() == '' || $('#' + id).html() == null) {
			$('#' + id).html(content);
		}
		if($('#' + id).html() != '' && $('#' + id).html() != null){
			window.scroll(0, $('#' + id).scrollTop() + 100);
		}
	}
	
	function showDetail(){
		$("#nr").html(descriptions);
		$("#cs").html(specifications);
		$("#showLine").hide();
		initImgWidth();
	}
	//var descriptions = '<div style="text-align: center;"><img alt="" src="${pageScope.basePath}plug-in/shop/picture/20151022160425.jpg" /><br /><img alt="" src="${pageScope.basePath}plug-in/shop/picture/20151022160436.jpg" /><br /><img alt="" src="${pageScope.basePath}plug-in/shop/picture/20151022160447.jpg" /><br /><img alt="" src="${pageScope.basePath}plug-in/shop/picture/20151022160500.jpg" /><br /><img alt="" src="${pageScope.basePath}plug-in/shop/picture/20151022160509.jpg" /><br /><img alt="" src="${pageScope.basePath}plug-in/shop/picture/20151022160520.jpg" /><br /><img alt="" src="${pageScope.basePath}plug-in/shop/picture/20151022160532.jpg" /><br /><img alt="" src="${pageScope.basePath}plug-in/shop/picture/20151022160543.jpg" /></div>';// 详情
	var descriptions='${goods.descriptions}';
	var specifications = '${goods.descriptions}';// 参数
 </script>
 

		 <!--手机数码End-->
        <!--menuStart-->
       
        <!--menuEnd-->
       <!--  <div style="height: 20px;width: 100%;"></div> -->
        <!-- 版权 -->
		<footer class="copyright">银行卡商城 2015 – 版权所有 沪ICP备11039870号-2</footer>
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
                    <img align="absmiddle" src="${pageScope.basePath}plug-in/shop/picture/icon_alert.png"><span id="failMessage">对不起，操作失败！</span>
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
                  <img align="absmiddle" src="${pageScope.basePath}plug-in/shop/picture/icon_complete.png"><span id="succMessage">恭喜您，操作成功！</span>
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
				<img align="absmiddle" src="${pageScope.basePath}plug-in/shop/picture/icon_alert.png">
				<span id="confirmMessage">提示信息</span>
			</div>
			<div class="box_btns">
				<a id="btn_cancel" onClick="closePopupConfirm()" class="blur">取&nbsp;&nbsp;消</a>
				<a href="javascript:void(0);" id="btn_sub">确&nbsp;&nbsp;定</a>
			</div>
		</div>
	</div>
	<div id="popup_waiting" style="display: none">
      <!--maskStart-->    
          <div class="mask"></div>
      <!--maskEnd-->        
          <div class="popup" align="center">
			<img align="absmiddle" src="${pageScope.basePath}plug-in/shop/picture/bx_loader.gif">
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
