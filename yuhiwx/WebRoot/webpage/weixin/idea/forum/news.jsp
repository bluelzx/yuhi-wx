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
<!DOCTYPE html>
<!-- saved from url=(0058)http://121.199.40.152/demo2014/weishop/portal.php?mobile=2 -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Cache-control" content="no-cache">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
<meta name="format-detection" content="telephone=no">
<meta name="keywords" content="门户">
<meta name="description" content="门户 ,Comenigs！">
<!--<base href="http://121.199.40.152/demo2014/weishop/">-->
<base href=".">
<title>门户 - 手机版 - Powered by Discuz!</title>
<link rel="stylesheet"
	href="${pageScope.basePath}plug-in/forum/style.css" type="text/css"
	media="all">
<script src="${pageScope.basePath}plug-in/forum/jquery-1.8.3.min.js"
	type="text/javascript"></script>
<script type="text/javascript">var STYLEID = '3', STATICURL = 'static/', IMGDIR = 'template/comeing_weixin/touch/style', VERHASH = 'uqn', charset = 'gbk', discuz_uid = '0', cookiepre = 'YSVH_2132_', cookiedomain = '', cookiepath = '/', showusercard = '1', attackevasive = '0', disallowfloat = 'newthread', creditnotice = '1|威望|,2|金钱|,3|贡献|', defaultstyle = '', REPORTURL = 'aHR0cDovLzEyMS4xOTkuNDAuMTUyL2RlbW8yMDE0L3dlaXNob3AvcG9ydGFsLnBocD9tb2JpbGU9Mg==', SITEURL = 'http://121.199.40.152/demo2014/weishop/', JSPATH = 'static/js/';</script>
<script src="${pageScope.basePath}plug-in/forum/common.js"
	type="text/javascript"></script>
<script type="text/javascript">
function goto(url){window.location.href=url;}
</script>

</head>

<body id="nv_portal" class="pg_index">
	<div id="header">
		<div class="header">
			<a
				href="http://121.199.40.152/demo2014/weishop/forum.php?forumlist=1&mobile=2"
				class="logo"><img
				src="${pageScope.basePath}plug-in/forum/logo.png">Comenigs！</a>
			<ul>
				<li><a
					href="http://121.199.40.152/demo2014/weishop/search.php?mod=forum&mobile=2"><img
						src="${pageScope.basePath}plug-in/forum/search.png">
				</a>
				</li>
			</ul>
		</div>
	</div>
	<div id="main">
		<script language="javascript">
function showcats(){
if(document.getElementById('allcats').style.display=='none'){
document.getElementById('allcats').style.display='';
}else{
document.getElementById('allcats').style.display='none';
}
}

</script>

		<table cellspacing="0" cellpadding="0" class="newscats">
			<tbody>
				<tr>
					<td>
						<ul class="cl">
							<li class="a"><a
								href="${pageScope.basePath}plug-in/forum/门户 - 手机版 - Powered by Discuz!.html">全部</a>
							</li>
							<li><a
								href="http://121.199.40.152/demo2014/weishop/portal.php?mod=list&catid=1">国内</a>
							</li>
							<li><a
								href="http://121.199.40.152/demo2014/weishop/portal.php?mod=list&catid=2">国际</a>
							</li>
							<li><a
								href="http://121.199.40.152/demo2014/weishop/portal.php?mod=list&catid=3">军事</a>
							</li>
						</ul></td>
					<th><a href="javascript:;" onclick="showcats()"><img
							src="${pageScope.basePath}plug-in/forum/morecat.png">
					</a>
					</th>
				</tr>
			</tbody>
		</table>
		<div id="allcats" style="display:none">
			<ul class="cl">
				<li><a
					href="http://121.199.40.152/demo2014/weishop/portal.php?mod=list&catid=1">国内</a>
				</li>
				<li><a
					href="http://121.199.40.152/demo2014/weishop/portal.php?mod=list&catid=2">国际</a>
				</li>
				<li><a
					href="http://121.199.40.152/demo2014/weishop/portal.php?mod=list&catid=3">军事</a>
				</li>
				<li><a
					href="http://121.199.40.152/demo2014/weishop/portal.php?mod=list&catid=4">社会</a>
				</li>
				<li><a href="http://121.199.40.152/demo2014/weishop/tech/">科技</a>
				</li>
				<li><a
					href="http://121.199.40.152/demo2014/weishop/portal.php?mod=list&catid=6">财经</a>
				</li>
				<li><a
					href="http://121.199.40.152/demo2014/weishop/portal.php?mod=list&catid=7">房产</a>
				</li>
			</ul>
		</div>
		<table cellspacing="0" cellpadding="0" id="listtableid"
			class="newslist">
			<tbody id="autolist_30">
				<tr>
					<td onclick="goto(&#39;portal.php?mod=view&amp;aid=30&#39;)">
						<div class="newsinfo">
							<h3>人人都爱Windows，直到他们遇到Mac</h3>
							<div class="cl">
								<div class="picarea">
									<img
										src="${pageScope.basePath}plug-in/forum/155659ycxkjkgnxgdxlxts.jpg.thumb.jpg">
								</div>
								<p>昨天的苹果发布会主要介绍了两款产品线的情况，由于Apple Watch
									作为家族最新成员光芒实在太过抢眼，导致另一款新品「新Macbook」
									被有意无意的忽略掉了。在桌面电脑领域，Mac家族正在试图掀起一场再次挑战 Windows ...</p>
							</div>
						</div></td>
				</tr>
			</tbody>
			<tbody id="autolist_29">
				<tr>
					<td onclick="goto(&#39;portal.php?mod=view&amp;aid=29&#39;)">
						<div class="newsinfo">
							<h3>Mac Book取代超极本？为时太早！</h3>
							<div class="cl">
								<div class="picarea">
									<img
										src="${pageScope.basePath}plug-in/forum/155226ofcv6d69t95fja5d.jpg.thumb.jpg">
								</div>
								<p>昨天凌晨的苹果发布会上基本上包含两个主题：Apple Watch与新款的Mac
									Book。相比较作为全新产品的Apple Watch获得了一致的交口称赞以外，Mac
									Book似乎收获的更多：除了一阵又一阵的惊呼以外，”Mac Book能否取代超极 ...</p>
							</div>
						</div></td>
				</tr>
			</tbody>
			<tbody id="autolist_28">
				<tr>
					<td onclick="goto(&#39;portal.php?mod=view&amp;aid=28&#39;)">
						<div class="newsinfo">
							<h3>苹果为何要重新“定义”笔记本？</h3>
							<div class="cl">
								<div class="picarea">
									<img
										src="${pageScope.basePath}plug-in/forum/154958d4dmsofeydw4yme4.png.thumb.jpg">
								</div>
								<p>第一代MacBook
									Air最让人深刻的是它的“苗条身材”。瘦到可以装进一个信封，轻到携带游走四方。它的到来让用户重新认识了笔记本电脑的便携的意义。现在的MacBook
									Air拥有7小时的续航时间，以及3磅的体重。随着时间的 ...</p>
							</div>
						</div></td>
				</tr>
			</tbody>
			<tbody id="autolist_27">
				<tr>
					<td onclick="goto(&#39;portal.php?mod=view&amp;aid=27&#39;)">
						<div class="newsinfo">
							<h3>习近平在广西团忆曾在街边吃桂林米粉</h3>
							<div class="cl">
								<div class="picarea">
									<img
										src="${pageScope.basePath}plug-in/forum/135658bhnl2d6n8lm9m986.png.thumb.jpg">
								</div>
								<p>广西是革命老区，吉林为老工业基地。在审议中，习近平与代表们讨论交流了如何振兴革命老区和老工业基地、促进少数民族地区发展等问题。总书记还动情回忆起自己年青时代第一次到桂林的往事：如诗如画的桂林山水、美丽
									...</p>
							</div>
						</div></td>
				</tr>
			</tbody>
			<tbody id="autolist_6">
				<tr>
					<td onclick="goto(&#39;portal.php?mod=view&amp;aid=6&#39;)">
						<div class="newsinfo">
							<h3>iPhone6临近 旧款iPhone转让数量猛翻30倍</h3>
							<div class="cl">
								<div class="picarea">
									<img
										src="${pageScope.basePath}plug-in/forum/142556v0dufubklwwoux60.jpg.thumb.jpg">
								</div>
								<p>美国有多家二手网站提供旧款iPhone手机的回收，其中Gazelle是规模较大的一家。从本本周一开始，这家网站推出了一个十分诱人的回收政策：用户现在就可以提交资料，将手中的iPhone5s转让给该网站，而该网站将把二手手
									...</p>
							</div>
						</div></td>
				</tr>
			</tbody>
			<tbody id="autolist_5">
				<tr>
					<td onclick="goto(&#39;portal.php?mod=view&amp;aid=5&#39;)">
						<div class="newsinfo">
							<h3>锤子手机屏幕易碎？8款手机坠地测试</h3>
							<div class="cl">
								<div class="picarea">
									<img
										src="${pageScope.basePath}plug-in/forum/142527wo0g62e6781rmv70.jpg.thumb.jpg">
								</div>
								<p>腾讯数码讯（杜杰）
									在8月27日老罗与王自如长达3小时的PK战中，二人上来的第一个话题就是关于玻璃屏幕易碎的问题，而在剑拔弩张的激烈辩论中，我们一直好奇为啥二人没有在现场摔一部锤子手机做个直播测试。这个遗憾
									...</p>
							</div>
						</div></td>
				</tr>
			</tbody>
			<tbody id="autolist_4">
				<tr>
					<td onclick="goto(&#39;portal.php?mod=view&amp;aid=4&#39;)">
						<div class="newsinfo">
							<h3>屡曝不改：三星代工厂再被指控雇佣童工</h3>
							<div class="cl">
								<p>腾讯科技讯
									三星和联想现在正在接受调查，因为此前中国劳工观察组织报道称，一家为这两家公司代工产品的工厂存在使用童工的现象。中国劳工观察组织周四发表声明说，位于广东省惠州市的海格国利电子雇用了逾10名年龄
									...</p>
							</div>
						</div></td>
				</tr>
			</tbody>
			<tbody id="autolist_3">
				<tr>
					<td onclick="goto(&#39;portal.php?mod=view&amp;aid=3&#39;)">
						<div class="newsinfo">
							<h3>Google X副总裁成美国政府CTO最热门人选</h3>
							<div class="cl">
								<div class="picarea">
									<img
										src="${pageScope.basePath}plug-in/forum/142437mih0sgswh6ayfwyy.jpg.thumb.jpg">
								</div>
								<p>如果这一消息属实的话，史密斯将替代此前宣布离职的美国政府首席技术官托德-帕克(Todd
									Park)成为历史上的第三位首席技术官。帕克在不久前表示，自己由于家庭因素决定卸任CTO一职，并将在今后前往加州为政府招募更多
									...</p>
							</div>
						</div></td>
				</tr>
			</tbody>
			<tbody id="autolist_2">
				<tr>
					<td onclick="goto(&#39;portal.php?mod=view&amp;aid=2&#39;)">
						<div class="newsinfo">
							<h3>美FDA批准硅谷项目：智能手机随时监控心脏异动</h3>
							<div class="cl">
								<div class="picarea">
									<img
										src="${pageScope.basePath}plug-in/forum/142354iq8jkqz88eve86cy.jpg.thumb.jpg">
								</div>
								<p>全球正在老龄化，对于老年人来说，心脏跳动是一个重要的健康指标，掌握跳动异常，可以避免老人出现意外心脏病甚至猝死。本周，两家硅谷公司的手机健康项目，获得FDA批准。媒体称，科技行业最近希望监管部门能够明晰
									...</p>
							</div>
						</div></td>
				</tr>
			</tbody>
			<tbody id="autolist_1">
				<tr>
					<td onclick="goto(&#39;portal.php?mod=view&amp;aid=1&#39;)">
						<div class="newsinfo">
							<h3>万达联手百度腾讯成立万达电商</h3>
							<div class="cl">
								<div class="picarea">
									<img
										src="${pageScope.basePath}plug-in/forum/142315rctgwg8t8c9igzcr.jpg.thumb.jpg">
								</div>
								<p>腾讯科技讯
									8月29日上午11点，万达集团、腾讯、百度在深圳举行战略合作签约仪式，宣布共同出资成立万达电子商务公司。万达集团董事长王健林、腾讯公司董事会主席兼首席执行官马化腾(微博)、百度公司董事长兼首席执行
									...</p>
							</div>
						</div></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="more_content" style="display:none">
		<div>
			<ul class="cl" id="items">
				<li style="height: 91px;"><a
					href="http://121.199.40.152/demo2014/weishop/home.php?mod=space&do=album&view=all&mobile=2"><div
							style="height: 36px;">
							<img src="${pageScope.basePath}plug-in/forum/1.png"
								style="height: 36px;">
						</div>
						<p style="height: 29px; line-height: 29px;">相册</p>
				</a>
				</li>
				<li style="height: 91px;"><a
					href="${pageScope.basePath}plug-in/forum/门户 - 手机版 - Powered by Discuz!.html"><div
							style="height: 36px;">
							<img src="${pageScope.basePath}plug-in/forum/2.png"
								style="height: 36px;">
						</div>
						<p style="height: 29px; line-height: 29px;">新闻</p>
				</a>
				</li>
				<li style="height: 91px;"><a
					href="http://121.199.40.152/demo2014/weishop/search.php?mod=forum&mobile=2"><div
							style="height: 36px;">
							<img src="${pageScope.basePath}plug-in/forum/3.png"
								style="height: 36px;">
						</div>
						<p style="height: 29px; line-height: 29px;">搜索</p>
				</a>
				</li>
				<li style="height: 91px;"><a
					href="http://121.199.40.152/demo2014/weishop/home.php?mod=follow&view=other&mobile=2"><div
							style="height: 36px;">
							<img src="${pageScope.basePath}plug-in/forum/4.png"
								style="height: 36px;">
						</div>
						<p style="height: 29px; line-height: 29px;">广播</p>
				</a>
				</li>
				<li style="height: 91px;"><a
					href="http://121.199.40.152/demo2014/weishop/home.php?mod=space&do=thread&view=me&mobile=2"><div
							style="height: 36px;">
							<img src="${pageScope.basePath}plug-in/forum/5.png"
								style="height: 36px;">
						</div>
						<p style="height: 29px; line-height: 29px;">我的</p>
				</a>
				</li>
				<li style="height: 91px;"><a
					href="http://121.199.40.152/demo2014/weishop/home.php?mod=space&do=favorite&view=me&type=thread&mobile=2"><div
							style="height: 36px;">
							<img src="${pageScope.basePath}plug-in/forum/6.png"
								style="height: 36px;">
						</div>
						<p style="height: 29px; line-height: 29px;">收藏</p>
				</a>
				</li>
				<li style="height: 91px;"><a
					href="http://121.199.40.152/demo2014/weishop/home.php?mod=space&do=friend&mobile=2"><div
							style="height: 36px;">
							<img src="${pageScope.basePath}plug-in/forum/7.png"
								style="height: 36px;">
						</div>
						<p style="height: 29px; line-height: 29px;">好友</p>
				</a>
				</li>
				<li style="height: 91px;"><a
					href="http://121.199.40.152/demo2014/weishop/home.php?mod=space&do=pm&mobile=2"><div
							style="height: 36px;">
							<img src="${pageScope.basePath}plug-in/forum/8.png"
								style="height: 36px;">
						</div>
						<p style="height: 29px; line-height: 29px;">短消息</p>
				</a>
				</li>
			</ul>
		</div>
	</div>
	<script language="javascript">
  h1=((document.body.clientWidth-20)/4)-25;
h2=h1/9;


hd=Math.round(h2*5);
hp=Math.round(h2*4);


hi=Math.round((h2*9))+26;

  var lis=document.getElementById('items').getElementsByTagName('li');

  for(i=0; i<lis.length; i++) {
lis[i].style.height=hi+'px';
document.getElementById('items').getElementsByTagName('div')[i].style.height=hd+'px';
document.getElementById('items').getElementsByTagName('img')[i].style.height=hd+'px';
document.getElementById('items').getElementsByTagName('p')[i].style.height=hp+'px'
document.getElementById('items').getElementsByTagName('p')[i].style.lineHeight=hp+'px';

}

function shownv(area){
var id=area+'_content';

if(document.getElementById(id).style.display=='none' || document.getElementById(id).className=='hidden'){
document.getElementById(id).className='show';
document.getElementById(id).style.display='';

}else{
document.getElementById(id).className='hidden';
}

}
</script>
	<div id="footernv">
		<ul>
			<li><a
				href="http://121.199.40.152/demo2014/weishop/forum.php?mod=guide&view=hot&mobile=2"><em></em>
			</a>
			</li>
			<li><a
				href="http://121.199.40.152/demo2014/weishop/forum.php?forumlist=1&mobile=2"><em></em>
			</a>
			</li>
			<li><a
				href="http://121.199.40.152/demo2014/weishop/member.php?mod=logging&action=login&mobile=2"><em></em>
			</a>
			</li>
			<li><a
				href="http://121.199.40.152/demo2014/weishop/home.php?mod=follow&view=follow&action=add&mobile=2"><em>l</em>
			</a>
			</li>
			<li id="end"><a href="javascript:;"
				onclick="shownv(&#39;more&#39;)"><em></em>
			</a>
			</li>
		</ul>
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