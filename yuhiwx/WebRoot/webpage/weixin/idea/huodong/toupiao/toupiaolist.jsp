<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- saved from url=(0068)http://tn.accoo.cn/vote-992.html?openid=oXDyzjvkCkT_vg1boTc6E8o0F5ZU -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Cache-Control" content="no-cache">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="yes" name="apple-mobile-web-app-capable">
<title>${voteEntity.title}</title>
<meta name="description"
	content="">
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>/webpage/weixin/idea/huodong/toupiao/source/style.css">
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>/webpage/weixin/idea/huodong/toupiao/source/dialog1.css">
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>/webpage/weixin/idea/huodong/toupiao/source/gz_tip.css">
<script type="text/javascript"
	src="<%=basePath%>/webpage/weixin/idea/huodong/toupiao/source/jquery.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/webpage/weixin/idea/huodong/toupiao/source/main.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/webpage/weixin/idea/huodong/toupiao/source/Tip_bao.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/webpage/weixin/idea/huodong/toupiao/source/dialog_min1.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/webpage/weixin/idea/huodong/toupiao/source/swiper.min.js"></script>
<!--滚动图片-->
<link rel="stylesheet"
	href="<%=basePath%>/webpage/weixin/idea/huodong/toupiao/source/swiper.min.css">
<!--滚动图片-->
<style type="text/css">
body {
	background: #f5f5f5 no-repeat;
	background-size: 100% auto;
}
</style>
</head>
<body>
	<!-- <div class="tongji">
		<ul>
			<li>参赛选手<br>
			<span>aaa</span>
			</li>
			<li>累计投票<br>
			<span>${voteEntity.totalpicket}</span>
			</li>
			<li>参与人数<br>
			<span>${voteEntity.totalperson}</span>
			</li>
		</ul>
		<input type="hidden" name="searchNameId" id="searchNameId" value="">
	</div> -->
	<div class="con">
		<p class="tips">
			<span><i></i>活动说明</span>
		</p>
		<p class="neirong">${voteEntity.explains}</p>
		<!--   <p class="tips"><span><i></i>投票报名</span></p>
        <p class="neirong"><a href="http://m.tn.ccoo.cn/baoming/baoming.aspx?uid=22&sid=7092&openid=oXDyzjvkCkT_vg1boTc6E8o0F5ZU" class="more">[我要报名]</a></p> -->
		<p class="tips">
			<span><i></i>活动投票区</span>
		</p>
		<section class="falls">
			<ul class="fl columns" id="fl columns"></ul>
			<!-- <p id="loadMore">
				<span>加载更多照片</span>
			</p> -->
			<input type="hidden" name="maxpage" id="maxpage" value="1000"
				isclick="1">
		</section>
		<script type="text/javascript">
			var aBox = $('.columns');
			var n = 0, s = 10;
			var liW = document.body.clientWidth;
			var timer = null;
			var imgIndex = 0;
			var dataArr = [];
			var bReady = true;
			var bAjax = true;
			var iNow = 0;
			$('#loadMore').click(function() {
				if (!bAjax)
					return;
				bAjax = false;
				loadImg();
			});
			loadImg();
			function loadImg() {
				n++;
				if (n > $('#maxpage').val()) {
					$("#maxpage").attr("isclick", 0);
					$('#loadMore').html('没有照片啦！');
					return false;
				}
				$('#loadMore')
						.html(
								'<img src="http://img.pccoo.cn/ewx/shop/album/images/loading.gif"> 加载中 请稍后...');
				var searchNameId = $("#searchNameId").val();
				if (searchNameId != '0' && searchNameId != '') {
					$.ajax({
						type : 'post',
						url : '<%=basePath%>huodongController.do?getpicketlist',
						data : {
							curPage : n,
							pageSize : s,
							uid : '22',
							openid : "oXDyzjvkCkT_vg1boTc6E8o0F5ZU",
							showtype : 1,
							cmdid : 3,
							voteid : '${voteEntity.id}',
							itemname : searchNameId
						},
						success : function(data) {
							if (data != '') {
								var arr = eval('(' + data + ')');
								if (arr.length < s) {
									$("#maxpage").attr("isclick", 0);
								}
								create10(arr);
							} else {
								$("#maxpage").attr("isclick", 0);
								$('#loadMore').html('没有照片啦！');
							}
						}
					});
				} else {
					$.ajax({
						type : 'post',
						url : '<%=basePath%>huodongController.do?getpicketlist',
						data : {
							curPage : n,
							pageSize : s,
							uid : '22',
							openid : "oXDyzjvkCkT_vg1boTc6E8o0F5ZU",
							showtype : 1,
							cmdid : 3,
							voteid : '${voteEntity.id}',
							itemname : ""
						},
						success : function(data) {
							if (data != '') {
								var arr = eval('(' + data + ')');
								if (arr.length < s) {
									$("#maxpage").attr("isclick", 0);
								}
								create10(arr);
							} else {
								$("#maxpage").attr("isclick", 0);
								$('#loadMore').html('没有照片啦！');
							}
						}
					});
				}
			}
			function loadImgFind(itemid) {
				document.getElementById('fl columns').scrollIntoView();
				n = 0;
				n++;
				$('.falls ul li').remove();
				if (n > $('#maxpage').val()) {
					$("#maxpage").attr("isclick", 0);
					$('#loadMore').html('没有照片啦！');
					return false;
				}
				$('#loadMore')
						.html(
								'<img src="http://img.pccoo.cn/ewx/shop/album/images/loading.gif"> 加载中 请稍后...');
				$.ajax({
					type : 'post',
					url : '<%=basePath%>huodongController.do?getpicketlist',
					data : {
						curPage : n,
						pageSize : s,
						uid : '22',
						openid : "oXDyzjvkCkT_vg1boTc6E8o0F5ZU",
						showtype : 1,
						cmdid : 3,
						voteid : "${voteEntity.id}",
						itemname : itemid
					},
					success : function(data) {
						if (data != '') {
							var arr = eval('(' + data + ')');
							if (arr.length < s) {
								$("#maxpage").attr("isclick", 0);
							}
							create10(arr);
						} else {
							$("#maxpage").attr("isclick", 0);
							$('#loadMore').html('没有照片啦！');
						}
					}
				});
			}
			function create10(arr) {
				if (!bReady)
					return;
				bReady = false;
				//dataArr = dataArr.concat(arr);
				var m = 0;
				var timer = setInterval(
						function() {
							var oDiv = document.createElement('li');
							oDiv.style.cursor = 'pointer';
							//oDiv.innerHTML = '<img src="'+arr[n].src+'" alt="'+arr[n].title+'">';
							var picNewHref = '';
							if ('0' == '0') {
								picNewHref = '/picshow-992-'
										+ arr[m].itmeid
										+ '.html?openid=oXDyzjvkCkT_vg1boTc6E8o0F5ZU';
							} else {
								picNewHref = '/voteshow-992-'
										+ arr[m].itmeid
										+ '.html?openid=oXDyzjvkCkT_vg1boTc6E8o0F5ZU';
							}
							var endDate = new Date('2016/6/22 13:43:35');
							var nowDate = new Date('2016/6/29 14:53:24');
							var choicetype='${choicetype}';
							if (choicetype==null||choicetype!='1') {
								oDiv.innerHTML = '<a href="JavaScript:void(0);">\
      <div class="li_box"><a href="JavaScript:void(0);" >\
	  <div class="xs_pic">\
	  <img src="' + arr[m].src + '">\
	  <div class="biaozhu_s">'
										+ arr[m].orderid
										+ ' '
										+ arr[m].name
										+ '</div>\
	  </div>\
	  </a><div class="toupiao" id="' + arr[m].id + '">\
		  <span class="piaoshu">总票数'
										+ arr[m].piaocount
										+ '</span><i style="display:none">'
										+ arr[m].name
										+ '</i> \
 <a onclick="toupiao(\''+arr[m].id+'\',this,\''+arr[m].piaocount+'\')" href="javascript:;" class="tp">投票</a>\
	  </div>\
  </div>\
  </a>';
							} else {
								oDiv.innerHTML = '<a href="JavaScript:void(0);">\
      <div class="li_box"><a href="JavaScript:void(0);" >\
	  <div class="xs_pic">\
	  <img src="' + arr[m].src + '">\
	  <div class="biaozhu_s">'
										+ arr[m].orderid
										+ '号-'
										+ arr[m].name
										+ '</div>\
	  </div>\
	  </a><div class="toupiao" id="' + arr[m].id + '">\
		  <span class="piaoshu">总票数'
										+ arr[m].piaocount
										+ '</span><i style="display:none">'
										+ arr[m].name
										+ '</i>\
										</div>\
  </div>\
  </a>';
							}
							oDiv.index = imgIndex++;
							var oBox = findMin(aBox);
							oBox.appendChild(oDiv);

							oDiv.onclick = function() {
								iNow = oDiv.index;
							}
							m++;
							if (m == arr.length) {
								$('#loadMore').html('<span>加载更多照片</span>');
								clearInterval(timer);
								bReady = true;
								bAjax = true;
							}
						}, 100)
			};
			function findMin(aUl) {
				var Min = aUl[0];
				for ( var i = 1; i < aUl.length; i++) {
					if (aUl[i].scrollHeight < Min.scrollHeight) {
						Min = aUl[i];
					}
				}
				return Min;
			};
			
			function toupiao(id,even,picketcount){
				 if(confirm('是否进行投票选择？')){
					$.ajax({
							type : 'get',
							url : '<%=basePath%>huodongController.do?addpicket',
							data : {
								id : id,
								pageSize : s,
								uid : '22',
								openid : '${openid}',
								showtype : 1,
								cmdid : 3,
								voteid : "${voteEntity.id}"
							},
							success : function(data) {
								if (data.indexOf("SUCCESS")) {
									alert("谢谢投票");
									 $(even).html("已投票");
									$(even).css("background-color","#C3BFC0");
									picketcount=parseInt(picketcount)+1;
									$("#"+id).children(".piaoshu").html("总票数"+picketcount);
									$(".tp").removeAttr("onclick"); 
								} else {
									alert("操作异常,请刷新本页重试");
								}
							}
						});
				}
			}
		</script>
	</div>
	<audio controls="controls" style="display: none;"></audio>
	<div
		style="position: static; width: 0px; height: 0px; border: none; padding: 0px; margin: 0px;">
		<div id="trans-tooltip">
			<div id="tip-left-top"
				style="background: url(chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-left-top.png);"></div>
			<div id="tip-top"
				style="background: url(chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-top.png) repeat-x;"></div>
			<div id="tip-right-top"
				style="background: url(chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-right-top.png);"></div>
			<div id="tip-right"
				style="background: url(chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-right.png) repeat-y;"></div>
			<div id="tip-right-bottom"
				style="background: url(chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-right-bottom.png);"></div>
			<div id="tip-bottom"
				style="background: url(chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-bottom.png) repeat-x;"></div>
			<div id="tip-left-bottom"
				style="background: url(chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-left-bottom.png);"></div>
			<div id="tip-left"
				style="background: url(chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-left.png);"></div>
			<div id="trans-content"></div>
		</div>
		<div id="tip-arrow-bottom"
			style="background: url(chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-arrow-bottom.png);"></div>
		<div id="tip-arrow-top"
			style="background: url(chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-arrow-top.png);"></div>
	</div>
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