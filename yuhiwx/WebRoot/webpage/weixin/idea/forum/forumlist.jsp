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
<!-- saved from url=(0082)http://121.199.40.152/demo2014/weishop/forum.php?mod=guide&view=newthread&mobile=2 -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Cache-control" content="no-cache">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
<meta name="format-detection" content="telephone=no">
<meta name="keywords" content="">
<!--<base href="http://121.199.40.152/demo2014/weishop/">-->
<base href=".">
<title>元海论坛</title>

<script src="${pageScope.basePath}plug-in/jquery/jquery-1.8.3.min.js"
	type="text/javascript"></script>
<script type="text/javascript">var STYLEID = '3', STATICURL = 'static/', IMGDIR = 'template/comeing_weixin/touch/style', VERHASH = 'uqn', charset = 'gbk', discuz_uid = '0', cookiepre = 'YSVH_2132_', cookiedomain = '', cookiepath = '/', showusercard = '1', attackevasive = '0', disallowfloat = 'newthread', creditnotice = '1|威望|,2|金钱|,3|贡献|', defaultstyle = '', REPORTURL = 'aHR0cDovLzEyMS4xOTkuNDAuMTUyL2RlbW8yMDE0L3dlaXNob3AvZm9ydW0ucGhwP21vZD1ndWlkZSZ2aWV3PW5ld3RocmVhZCZtb2JpbGU9Mg==', SITEURL = 'http://121.199.40.152/demo2014/weishop/', JSPATH = 'static/js/';</script>
 <script src="${pageScope.basePath}plug-in/forum/common.js"
	type="text/javascript"></script> 


</head>

<body id="nv_forum" class="pg_guide">
	<div id="header">
		<div class="header">
			<a
				href="javascript:void(0);"
				class="logo"><img
				src="${pageScope.basePath}plug-in/forum/logo.png"> <font
				style="color: white;text-indent: 1em;">元海微论坛</font> </a>
			<ul>
				<%-- <li><a
					href=""><img
						src="${pageScope.basePath}plug-in/forum/search.png"> </a></li> --%>
			</ul>
		</div>
	</div>
	<div id="main">
		<section class="indexslides cl">
			<div class="flexslider">

				<div class="flex-viewport"
					style="overflow: hidden; position: relative;">
					<ul class="slides cl"
						style="width: 1400%; transition-duration: 0.6s; transform: translate3d(-768px, 0px, 0px);">
						<c:forEach items="${adminlist.resultList}" var="admin" varStatus="in">
						<li class="clone"
							style="width: 384px; float: left; display: block;"><a
							href="${pageScope.basePath}forumController.do?showSendCardsDetail&id=${admin[1]}"><img src="${pageScope.basePath}${admin[2]}">
						</a><span class="guide-title">${admin[0]}</span></li>
						
						</c:forEach>
					</ul>
				</div>
				<ol class="flex-control-nav flex-control-paging">
					<c:forEach begin="0" end="${adminlist.resultList.size()}" var="i" varStatus="in">
							<li><a class="flex-active">${i+1}</a></li>
					</c:forEach>
				</ol>
			</div>
		</section>
		
		<script type="text/javascript">
function ngrid_sel(obj, show) {
jQuery(obj).parent().addClass('active');
jQuery(obj).parent().siblings().removeClass('active');
jQuery(show).show();
jQuery(show).siblings().hide();
}

jQuery(window).load(function(){
jQuery('.flexslider').flexslider({
animation: "slide",
directionNav: false,
start: function(slider){
jQuery('body').removeClass('loading');
}
});
});

</script>
		<div id="topnv">
			<ul class="list_3 cl">
				<li <c:if test="${type==1}">class="a"</c:if>><a
					href="${pageScope.basePath}forumController.do?showSendCards">热门</a>
				</li>
				<li <c:if test="${type==2}">class="a"</c:if>><a
					href="${pageScope.basePath}forumController.do?showSendCards&type=2">最新</a>
				</li>
				<li <c:if test="${type==3}">class="a"</c:if>><a
					href="${pageScope.basePath}forumController.do?showSendCards&type=3">我的</a>
				</li>
			</ul>
		</div>
	
		<div class="bgeee pt10" id="minh">
			<table cellspacing="0" cellpadding="0" id="listtableid"
				class="threadlist">
				<c:forEach items="${SendCardpagelist.resultList}" var="forum" varStatus="in">


					<tbody id="autolist_${forum[5]}">
						<tr>
							<td >
								<div class="threadinfo">
									<div class="userinfo cl">
										<img src="${forum[2]}"
											class="author">
										<h3>${forum[3]}</h3>
										<p>${forum[1]}</p>
									</div>
									<div class="threadabout">
									<font onclick="goto(&#39;${pageScope.basePath}forumController.do?showSendCardsDetail&id=${forum[5]}&#39;)">讨论主题&nbsp;:&nbsp;${forum[0]}</font>
									<c:if test="${type==3}">
									<a href="javascript:void(0);" onclick="deletemedetail('${forum[5]}','${type}','${forum[4]}');" ><span>删除</span></a></c:if>
									</div>
									 
									<div class="listpost">
										<h4 style="margin-top: -6px;">
											<a href="${pageScope.basePath}forumController.do?showSendCardsDetail&id=${forum[5]}">
												查看全部${forum[4]}条评论</a>
										</h4>
									</div>

								</div>
							</td>
						</tr>
					</tbody>
				</c:forEach>


			</table>
			<a href="javascript:;"
				rel="${pageScope.basePath}forumController.do?showSendCards&pageindex=<c:if test='${(SendCardpagelist.curPageNO+1)>SendCardpagelist.count}' >1</c:if><c:if test='${(SendCardpagelist.curPageNO+1)<SendCardpagelist.count}' >${SendCardpagelist.curPageNO+1}</c:if>&type=${type}"
				curpage="" id="autopbn" totalpage="0">下一页</a>
			<script language="javascript">
			
(function() {
var list=$('listtableid');
var autopbn = $('autopbn');
var nextpageurl = autopbn.getAttribute('rel').valueOf();
var curpage = ${SendCardpagelist.curPageNO};
var totalpage = ${SendCardpagelist.count%3>0?(SendCardpagelist.count/3+1):SendCardpagelist.count/3};

var autopagenum = 100;
var maxpage = (curpage + autopagenum) > totalpage ? totalpage : (curpage + autopagenum);

var loadstatus = 0;

autopbn.onclick = function() {
var oldloadstatus = loadstatus;
loadstatus = 2;
autopbn.innerHTML = '加载中...';
getnextpagecontent();
loadstatus = oldloadstatus;
};

if(autopagenum > 0) {
window.onscroll = function () {
var curtop = Math.max(document.documentElement.scrollTop, document.body.scrollTop);
if(curtop + document.documentElement.clientHeight + 500 >= document.documentElement.scrollHeight && !loadstatus) {
loadstatus = 1;
autopbn.innerHTML = '加载中...';
setTimeout(getnextpagecontent, 1000);
}
};
}

function getnextpagecontent() {

if(curpage + 1 > totalpage) {
window.onscroll = null;
autopbn.style.display = 'none';
return;
}
if(loadstatus != 2 && curpage + 1 > maxpage) {
autopbn.innerHTML = '下一页 &raquo;';
if(curpage + 1 > maxpage) {
window.onscroll = null;
}
return;
}
curpage++;
var url = nextpageurl + '&t=' + parseInt((+new Date()/1000)/(Math.random()*1000));
var x = new Ajax('HTML');
x.get(url, function (s) {


s = s.replace(/\n|\r/g, '');
if(s.indexOf("id=\"autopbn\"") == -1) {
$("autopbn").style.display = "none";
window.onscroll = null;
}


var tableobj = $('listtableid');
var nexts = s.match(/\<tbody id="autolist_(.+)"\>(.+?)\<\/tbody>/g);

list.innerHTML+=nexts;
for(i in nexts) {
if(i == 'index' || i == 'lastIndex') {
continue;
}
var insertid = nexts[i].match(/<tbody id="autolist_(\d+)"\>/);
//if(!$('autolist_' + insertid[1])) {

//var newbody = document.createElement('tbody');
//tableobj.appendChild(newbody);
//var div = document.createElement('div');
//div.innerHTML = '<table>' + nexts[i] + '</table>';
//tableobj.replaceChild(div.childNodes[0].childNodes[0], tableobj.lastChild);
//}
}

var pageinfo = s.match(/\<span id="fd_page_bottom"\>(.+?)\<\/span\>/);
nextpageurl = nextpageurl.replace(/&pageindex=\d+/, '&pageindex=' + (curpage + 1));

//$('fd_page_bottom').innerHTML = pageinfo[1];
if(curpage + 1 > totalpage) {
autopbn.style.display = 'none';
} else {
autopbn.innerHTML = '下一页 &raquo;';
}
loadstatus = 0;
});
}

})();
</script>
			<span id="fd_page_bottom"></span>
		</div>
	</div>

<div id="footernv">
  <ul >
    <li><a href="${pageScope.basePath}forumController.do?showSendCards">
    <i class="fa fa-home fa-2x"></i></a></li>
    <li><a href="${pageScope.basePath}webpage/weixin/idea/forum/addsendCard.jsp?type=1">
    <i class="fa fa-pencil fa-2x"></i>
    </a></li>
    <li><a href="http://news.sina.cn/?r=nc&vt=4">
    <i class="fa fa-commenting fa-2x"></i>
    </a></li>
    <li >
    <a href="javascript:;" onclick="shownv('more')">
    <i class="fa fa-camera-retro fa-2x">
    </i></a></li>
    
  </ul>
</div>

</body>
<link type="text/css" rel="stylesheet" href="${pageScope.basePath}plug-in/forum/style.css">
<link type="text/css" rel="stylesheet" href="${pageScope.basePath}plug-in/font-awesome-4.5.0/css/font-awesome.min.css">
<script type="text/javascript">
var index;
		var XMLHttpReq;  
function createXMLHttpRequest() {  
    try {  
        XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");//IE高版本创建XMLHTTP  
    }  
    catch(E) {  
        try {  
            XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");//IE低版本创建XMLHTTP  
        }  
        catch(E) {  
            XMLHttpReq = new XMLHttpRequest();//兼容非IE浏览器，直接创建XMLHTTP对象  
        }  
    }  
  
}  
function sendAjaxRequest(url,callback) {  
    createXMLHttpRequest();                                //创建XMLHttpRequest对象  
    XMLHttpReq.open("post", url, true);  
    XMLHttpReq.onreadystatechange = function(){
		     if (XMLHttpReq.readyState == 4) {  
		        if (XMLHttpReq.status == 200) {  
		            var text = XMLHttpReq.responseText;  
		             if(text.indexOf("success")){
		            	layer.open({content: '删除成功', time: 1});
		            	callback();
					}else{
						layer.open({content: '删除失败', time: 1});
					}
		        }  
		    }  
    }; //指定响应函数  
    XMLHttpReq.send(null);  
}  	

		function goto(url){window.location.href=url;}
	
		function deletemedetail(ids,type,followcount){
			var titlestr="";
			if(type&&type==3){
			if(followcount>0){
			titlestr="确定删除该帖子吗?该帖子的"+followcount+"条回复也会相对应的删除哦";
			}else{titlestr="确定删除属于您的该帖子吗";}
				layer.open({
			    content: titlestr,
			    btn: ['确认', '取消'],
			    shadeClose: false,
			    yes: function(){
			     var event=$('autolist_'+ids);
					if(event){
			    	sendAjaxRequest("forumController.do?deleteSendCard&id="+ids,function(){
			    		 event.innerHTML="";
			   			 layer.open({content: '删除成功', time: 1});
			    	});
			    }
			   
			     /*   var event=$('autolist_'+ids);
					if(event){
						jquery.ajax({
							url:"forumController.do?deleteSendCard&id="+ids,
							type:"POST",
							success:function(data){
								if(data.indexOf("success")){
								 layer.open({content: '删除成功', time: 1});
								}else{
								 layer.open({content: '删除失败', time: 1});
								}
							}
						});
					}
					event.innerHTML=""; */
			    }, no: function(){
			        layer.open({content: '且行且珍惜~', time: 1});
			    }
			});
			}
		}
		
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

</script>
	<script src="${pageScope.basePath}plug-in/forum/fixed.js"
			type="text/javascript"></script>
<script src="${pageScope.basePath}plug-in/layer/layer.js"
	type="text/javascript"></script>
	<script src="${pageScope.basePath}plug-in/forum/jquery.flexslider-min.js"
			type="text/javascript"></script>
<style type="text/css">
.threadabout span {
    padding: 1px 8px;
    float: right;
    color: #FD5656;
    border: 1px solid #F38787;
}
#footernv li{
margin-top: 10px;
}
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