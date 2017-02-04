<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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


<style type="text/css">
.allnum {
    width: 20px;
    height: 20px;
    border-radius: 50%;
    background: red;
    color: #fff;
    position: absolute;
    font-size: 12px;
    right: 10px;
    text-align: center;
    padding: 2px;
    line-height: 16px;
}
.ocart {
    margin-left: 15%;
    width: 50px;
    height: 50px;
    line-height: 50px;
    border-radius: 50%;
    text-align: center;
    background: #FA9448;
    position: absolute;
}
.bordernone {
    border: none;
}
.ptb15lr20 {
    padding: 15px 20px;
}
.font18 {
    font-size: 18px;
}
.text-white {
    color: #fff;
}
.bordernone {
    border: none;
}
.bg-yellow {
    background: #FA9448;
}
.btn-block {
    display: block;
    width: 100%;
}
.obto {
    background: #fff;
}
.bottoms {
    width: 100%;
    z-index: 999;
    position: fixed;
    bottom: 0;
}
.bordertop {
    border-top: 1px solid #e8e9eb;
}
.col-xs-5 {
    width: 66.666667%;
    float: left;
}
.col-xs-3 {
    width: 16%;
        float: left;
}
.clearPadding {
    padding: 0;
}
.col-xs-4 {
    width: 33.33333333%;
       float: left;
}
.pt15 {
    margin-left: 29%;
    padding-top: 4px;
}
.text-dining {
    color: #ff7d7c;
}
.font16 {
    font-size: 16px;
}
.text-dining {
    color: #ff7d7c;
}
</style>


<title>元海微商城</title>

<link href="${pageScope.basePath}plug-in/shop/css/common_3.css" rel="stylesheet" type="text/plug-in/shop/css">
<link href="${pageScope.basePath}plug-in/layer/need/layer.css" rel="stylesheet">
<script src="${pageScope.basePath}plug-in/shop/js/jquery.js"></script>
<script src="${pageScope.basePath}plug-in/shop/js/plug-in/shop/json_parse.js"></script>
<script src="${pageScope.basePath}plug-in/shop/js/common.js"></script>

<script type="text/javascript">
	//plug-in/shop/js全局项目路径
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
        	var url = 'shopMainController.do?getGoodsListforpage&search='+keyword + '&type=' + productType
        	+ '&part=' + '${part}';
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
	    	var url = '/mall/weiproduct/weiSearchProductList.action?productCtg=359&classType=&keyword='+keyword;
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
	    	var url = '/mall/weiproduct/weiSearchProductList.action?productCtg=359&classType='+classType+'&keyword='+keyword;
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
<link href="${pageScope.basePath}plug-in/shop/css/mall_only_3.css" rel="stylesheet" type="text/plug-in/shop/css">
</head>
<body>
	<div class="body_frame" style="padding-top:44px;">
		
		<header>
			<div class="head" id="header" style="">
                <div class="LR"><a id="btnGoBack" href="javascript:pageBack()" class="back"><img src="${pageScope.basePath}plug-in/shop/picture/icon_back.png"></a></div>
                <div class="center">元海微商城</div>
                <div class="LR">
                	<a href="javascript:void(0);"   class="home" style="">
                		<img style="width: 28px;" src="${pageScope.basePath}plug-in/shop/picture/iconmenu_home_active.png">
                	</a>
                </div>
            </div>
			
		</header>
		<!-- include -->
		






<script type="text/javascript">
var imagePath='http://img.leagcard.com/cms/online';
var appPath='/mall';
$(function(){
	var productType = '2';
	if('2' == productType){
		chiosePage('shoppingMall');
		changeHeaderTtile('微商城');
	} else {
		chiosePage('creditsExchange');
		changeHeaderTtile('俏积分');
	}
	$("footer").plug-in/shop/css("display","none");
});
function submitForm(){
	document.getElementById("searchForm").submit();
}
//加载排序
var orderSortBy = "";
var orderType = "";
$(function(){
	afterSort(orderType,orderSortBy);
});
/**
 * 排序后按钮样式修改
 * 获取&lt;div id="sor_img"&gt;下的两个&lt;img&gt;
 * @param {} orderType
 * @param {} orderSortBy
 */
function afterSort(orderType,orderSortBy){
	var nowId = "pro_sort_" +orderType;
	var imgs = $("#sort_img").children("img");
	$("a[id^=pro_sort_]").each(function(i,obj){
		$(obj).removeClass("active");
		$(obj).children("img")[0].src = imgs[0].src;
		if(nowId == obj.id){
			//当前排序
			$(obj).addClass("active");
			if("asc" == orderSortBy){
				//asc
				$(obj).children("img")[0].src = imgs[1].src;
			} else {
				//desc
				$(obj).children("img")[0].src = imgs[2].src;
			}
		}
	});
}
 /**
  * 切换排序
  * @param {} id
  */
 function orderTab(id) {
 	if (id == orderType) {
 		if (orderSortBy == "desc") {
 			if (id == "newArriv" || id == "hot") {
 				return;
 			}
 			orderSortBy = "asc";
 		} else if (orderSortBy == "asc") {
 			orderSortBy = "desc";
 		}
 	} else {
 		orderSortBy = "desc";
 	}
 	orderType = id;
 	$("#orderType").val(orderType);
 	$("#orderSortBy").val(orderSortBy);
 	afterSort(orderType, orderSortBy);
 	initFirstPage();//显示第一页
 	$("#pageForm").submit();
 }

 /**
  * 显示第一页
  */
 function initFirstPage(){
 	if(document.getElementById("page.currentPage")){
 		document.getElementById("page.currentPage").value = 1;
 	}
 }
</script>
<!--searchStart-->
	<div class="box_filter bTB">
		
			<a class="links" onClick="oper_sub('box_type')" style=""><img src="${pageScope.basePath}plug-in/shop/picture/icon_type.png" style="height: 30px;width: 30px;"><!-- <span>分类</span> --></a>
		
		
		<div id="box_type" class="box_type" style="display:none;">
			
				<a  onClick="oper_sub('box_type');selectproductCtg('2','')">全部商品</a>
				
					<a  onClick="oper_sub('box_type');selectproductCtg('2','357')">家用电器、汽车用品</a>
				
					<a class="active" onClick="oper_sub('box_type');selectproductCtg('2','359')">手机数码</a>
				
					<a  onClick="oper_sub('box_type');selectproductCtg('2','360')">电脑、办公</a>
				
					<a  onClick="oper_sub('box_type');selectproductCtg('2','361')">家居、厨具、家装</a>
				
					<a  onClick="oper_sub('box_type');selectproductCtg('2','363')">个人护理、化妆品</a>
				
					<a  onClick="oper_sub('box_type');selectproductCtg('2','364')">贵金属</a>
				
					<a  onClick="oper_sub('box_type');selectproductCtg('2','365')">运动健康</a>
				
					<a  onClick="oper_sub('box_type');selectproductCtg('2','366')">母婴、玩具、图书期刊</a>
				
					<a  onClick="oper_sub('box_type');selectproductCtg('2','1067')">箱包、奢侈品</a>
				
					<a  onClick="oper_sub('box_type');selectproductCtg('2','2570')">绿色特产、酒类食品</a>
					
			
			
		</div>
		<div class="box_search" style="">
			<input type="text" placeholder="请输入搜索关键字" id="keyword" value=""/>
			<a onclick="entersearch($('#keyword').val(),'2');"></a>
		</div>
		<a href="shopMainController.do?showUserHome" class="links" id="my_center"  style=""><img src="${pageScope.basePath}plug-in/shop/picture/icon_user.png" style="height: 30px;width: 30px;"><!-- <span>我</span> --></a>
	</div>
      <!--searchEnd-->
      
      <!--sortStart-->
      <div class="sort" style="display: none;">
          <a id="pro_sort_newArriv" class="active bR" onclick="orderTab('newArriv')">新品<img align="absmiddle" src="plug-in/shop/picture/sort_bottom.png"></a>
          <a id="pro_sort_price" class="bR" onclick="orderTab('price')" >价格<img align="absmiddle" src="${pageScope.basePath}plug-in/shop/picture/sort_top.png"></a>
          <a id="pro_sort_hot" onclick="orderTab('hot')">销量<img align="absmiddle" src="${pageScope.basePath}plug-in/shop/picture/sort_top.png"></a>
          <div id="sort_img" style="display: none;">
        	<img align='absmiddle' src='${pageScope.basePath}plug-in/shop/picture/sort_top.png'>
        	<img align='absmiddle' src='${pageScope.basePath}plug-in/shop/picture/sort_top.png'>
        	<img align='absmiddle' src='${pageScope.basePath}plug-in/shop/picture/sort_bottom.png'>
        </div>
      </div>
      <!--sortEnd-->
      
      <style type="text/plug-in/shop/css">
      	.price2{
      		font-size: 12px;color: #F00;
      	}
      	.price_unit{color: #999;font-size: 13px;}
      </style>
      <!--listStart-->
	      <div class="box_list" id="boxList">
	      	<ul style="width: 100%;">
	      	  <li>
	                  <a href="${pageScope.basePath}shopMainController.do?getGoodsforDetail&id=">
	                      <div class="img"><img src="${pageScope.basePath}plug-in/shop/picture/150540-1306901014.jpg"></div>
	                      <div class="info">
	                          <h4>苹果 iPhoneSE 64G 玫瑰金 移动联通电信4G手机 赠手机壳+钢化膜</h4>
	                          <h5>
	                          	 
	                          		一小部的一大步，高性能4英寸iphone，6S同款A9芯片，1200万像素。
	                          	
	                          </h5>
	                          <h6>
					               		￥<label>357.34*12期</label>
					               		<span>￥4,288.00</span>
			               			
			               			
				               
	                          </h6>
	                      </div>
	                  </a>
	              </li>
	      	
	      	
	      		<c:forEach items="${goodslist.resultList}" var="goods">
	      		
	      	
	      			   <li>
	                  <a href="${pageScope.basePath}shopMainController.do?getGoodsforDetail&id=${goods[5]}">
	                      <div class="img"><img src="${pageScope.basePath}${goods[2]}"></div>
	                      <div class="info">
	                          <h4>${goods[1]}</h4>
	                          <h5>
	                          	 ${goods[3]}
	                          	
	                          </h5>
	                          <h6>
					               		￥<label>${goods[4]}</label>
					               		<span>发布时间:${goods[0]}</span>
			               			
			               			
				               
	                          </h6>
	                      </div>
	                  </a>
	              </li>
	      		
	      		</c:forEach>
	           
	          </ul>
		</div>
		<div class="loading_more"><a id="getMore">拖动加载更多..</a></div>
		
		
      <!--listEnd-->
      <!-- TO_TOP_START -->
      	<a href="javascript:to_top();" class="to_top" style="display: none;"></a>
      <!-- TO_TOP_END -->
	  
		<div class="mallPage">
		   	<form id="pageForm" name="pageForm" action="/mall/weiproduct/weiProductList.action;plug-in/shop/jsessionid=5BA9129B3E30C5DEAA48C8FA7934E593.node1" method="post">
				<input type="hidden" name="keyword" value="" id="keywordHidden"/>
				<input type="hidden" name="productCtg" value="359" id="productCtg"/>
		    	<input type="hidden" name="productType" value="2" id="productType"/>
		    	<input type="hidden" name="pointCondition" id="pointCondition" value="" />
		    	<input type="hidden" name="orderType" value="" id="orderType"/>
		    	<input type="hidden" name="orderSortBy" value="" id="orderSortBy"/>
		    	<input type="hidden" name="pointRange" value="" id="pointRange"/>
		    	<input type="hidden" name="currentPage" value="1" id="currentPage"/>
		    	
		    </form>



	    </div>
	
<script src="${pageScope.basePath}plug-in/shop/js/dynamicloadpage.js"></script>
<script type="text/javascript">
	var PART_CREDITS_CASH_FLAG = '1';
	var productCtg = $("#productCtg").val();
	var productType = $("#productType").val();
	var pointCondition = $("#pointCondition").val();
	var pointRange = $("#pointRange").val();
	var orderType = $("#orderType").val();
	var orderSortBy = $("#orderSortBy").val();
	var currentPage = $("#currentPage").val();
	var totalPages = '8';
	
	var myConfig = {
		templateFlag : false,
		noTemplateCallback : function (dataObj){
			var strMon='';
			if('1' == productType){
				if ('1' == PART_CREDITS_CASH_FLAG) {
					if(dataObj.fullCredits>0 && dataObj.partPrice > 0){
						strMon='<label>'+dataObj.fullCredits+'</label>积分';	
							strMon += '<span style="margin-right: 0px;"><font class="price2">' + dataObj.partCredits + '</font>积分&nbsp;+&nbsp;￥<font class="price2">' + parseFloat(dataObj.partPrice).toFixed(2) + '</font></span>';
					} else if (dataObj.fullCredits>0) {
						strMon='<label>'+dataObj.fullCredits+'</label>积分';	
					} else {
						strMon='<label>'+dataObj.partCredits+'</label>积分&nbsp;+&nbsp;<font class="price_unit">￥</font><label>' + parseFloat(dataObj.partPrice).toFixed(2) + '</label>';
					}
				} else if (dataObj.fullCredits > 0) {
					strMon='<label>'+dataObj.fullCredits+'</label>积分';	
				}
			} else {
				if(installmentFlag == '1' && '' + dataObj.instalmentPrice != ''){
					strMon='￥<label>'+dataObj.instalmentPrice;
					if(dataObj.instalmentPrice.indexOf('期') == -1){
						strMon += '期';
					}
					strMon += '</label>';
					strMon += '<span>￥' + parseFloat(dataObj.salePrice).toFixed(2) + '</span>';
				} else {
					strMon='￥<label>'+parseFloat(dataObj.salePrice).toFixed(2)+'</label>';	
				}
			}
			var str = '';
			return str = '<li><a href="'+appPath+'/weiproduct/weiProductDetail.action?product.id='+dataObj.id+'"><div class="img"><img src='plug-in/shop/picture/ff041c29d5cc4bc6b3a5b702d4dae1ef.gif''+dataObj.logoPic+'></div> <div class="info"><h4>'+dataObj.productName+'</h4><h5>'+dataObj.productSummary +'</h5> <h6>'+strMon+'</h6></div></a></li>';
		},
		listName : "data",
		appendHtmlRootId : "#boxList ul",
		url : "/mall/weiproduct/weiProductList.action",
		params : {
			'targetType' : 'json',
			'productCtg' : productCtg,
			'productType' : productType,
			'pointCondition' : pointCondition,
			'pointRange': pointRange,
			'keyword' : $("#keywordHidden").val(),
			'orderType' : orderType,
			'orderSortBy' : orderSortBy
		},
		currentPageKey : 'page.currentPage',
		currentPage : currentPage,
		totalPage : totalPages,
		toTopFlag : true,
		topCallback: function(){
			if($(window).scrollTop() > (2 * $(window).height())){
				$(".to_top").plug-in/shop/css('display','block');
			} else {
				$(".to_top").plug-in/shop/css('display','none');
			}
		},
		loading : function(){
			//正在加载
			$("#getMore").html('<img src="plug-in/shop/picture/bx_loader.gif" width="25" height="25"/>');//出现加载图片
			//console.log("页面加载中");
		},
		loadSuccess : function() {
			//加载成功
			$("#getMore").html("拖动加载更多..");
			$("#currentPage").val(this.currentPage);
			//console.log("页面成功");
		},
		loadError : function() {
			//加载失败
			//console.log("页面失败");
			showFail("数据加载失败！");
		},
		loadEnd : function(){
			//页面加载完成
			$("#getMore").html("已加载全部");
			$("#currentPage").val(this.currentPage);
			//console.log("页面完成");
		},
		errorCallback : function (xhr){
			//console.log("系统错误!");
			showFail("数据加载失败！");
		}
	};
	var dynamic = new DynamicLoadPage(myConfig);//实例化
	dynamic.autoScroll();
	
</script>
	
		 <!--手机数码End-->
        <!--menuStart-->
       
        <!--menuEnd-->
       <!--  <div style="height: 20px;width: 100%;"></div> -->
        <!-- 版权 -->
        		    <script type="text/javascript">
      function goshopcar(){
  layer.open({
    content: '正在跳轉中..',
    style: 'background-color:#FA9448; color:#fff; border:none;',
    time: 0.5,
    end:function(){
    //TODO 
    location.href="shopMainController.do?goshopCar";
    }
});
      
      	}
      
      </script>
        
        <div class=" bottoms obto bordertop clearfix" id="footer"> 
					<div class="col-xs-3" onclick="goshopcar()">
						<div class="ocartbox" style="position:relative;">
							<div class="ocart" 	>
							<img style="padding-top: 20%;" src="${pageScope.basePath}plug-in/shop/picture/iconmenu_home_active.png"></div>
						</div>
					</div>
					<div class="col-xs-5">
						<p class="pt15"><span class="text-dining">￥</span>
						<span class="text-dining font16" id="allprice">30.00</span>
					</p>
					</div>
					<div class="col-xs-4 text-right clearPadding">
					<a href="shopMainController.do?goshopOrder">
					<button class="bg-yellow bordernone ptb15lr20 font18 btn-block text-white" type="submit">选好了</button>
					</a>
					</div>		
		</div>
		
		
		
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
 <script type="text/javascript" src="${pageScope.basePath}plug-in/layer/layer.js"> </script>
<script type="text/javascript">
	function showSuccess(message) {
		$("#popup_succ").plug-in/shop/css("display", "block");
		$("#popup_succ #succMessage").html(message);
	};
	function closeSuccess() {
		$("#popup_succ").plug-in/shop/css("display", "none");
	};
	function showFail(message) {
		$("#popup_fail").plug-in/shop/css("display", "block");
		$("#popup_fail #failMessage").html(message);
	};
	function closeFail() {
		$("#popup_fail").plug-in/shop/css("display", "none");
	};

	/**
	 * alertConfirm弹出
	 * @param {} message
	 * @param {} bindEvent 回调函数
	 */
	function showConfirmMessage(message,bindEvent){
		$("#popup_confirm").plug-in/shop/css("display", "block");
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
