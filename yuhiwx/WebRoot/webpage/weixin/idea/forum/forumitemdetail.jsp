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
<!-- saved from url=(0080)http://121.199.40.152/demo2014/weishop/forum.php?mod=viewthread&tid=559&mobile=2 -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Cache-control" content="no-cache">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
<meta name="format-detection" content="telephone=no">
<meta name="keywords" content="">
<meta name="description" content="">
<base href=".">
<title>${entity.title}</title>
<script src="${pageScope.basePath}plug-in/jquery/jquery-1.8.3.min.js"
	type="text/javascript"></script>
</head>

<body id="nv_forum" class="pg_viewthread">
	<div id="header">
		<div class="header">
			<a	href="${pageScope.basePath}forumController.do?showSendCards"
				class="logo"><img
				src="${pageScope.basePath}plug-in/forum/logo.png"><font
				style="color: white;text-indent: 1em;">元海微论坛</font></a>
			<ul>
				
			</ul>
		</div>
	</div>
	<div id="main">
		<script type="text/javascript">
function showmanage(){
if(document.getElementById('manage').style.display=='none'){
document.getElementById('manage').style.display='';
}else{
document.getElementById('manage').style.display='none';
}
}
</script>
		<div class="viewthread_info">
			<h2>
				<a href="javascript:void(0);">${entity.title}</a>
			</h2>
			<p>
				<em>${entity.updateDate}</em>
				楼主 &nbsp;&nbsp;类型:${entity.type}
			</p>
		</div>

		<div class="viewthread_first">
			<!--warn-->
			<!--subject-->
			<!--banned-->
			<!--price-->
			<!--sort-->
			<!--content-->
			<div class="viewthread_content">${entity.cardcontent}</div>
			<!--attachment-->
		</div>
		<div class="postitle">
			共有<span>${followlist.count}</span>条评论
		</div>
		<table cellspacing="0" cellpadding="0" id="listtableid"
			class="postlist">
			<c:forEach items="${followlist.resultList}" var="follow" varStatus="in">
			
			<tbody id="autolist_${follow[4]}">
				<tr>
					<td class="threadarea">
						<div>
							<div class="cl postuser">
							<span>
								<c:choose>
								<c:when test="${in.index==0}"> 沙发 </c:when>
								<c:when test="${in.index==1}"> 破沙发 </c:when>
								<c:when test="${in.index>1}"> 板凳 </c:when>
								</c:choose>
							 	<c:if test="${follow[5]==OPENID}">
								<span onclick="deletefollow('${follow[4]}')" style="color: #FD5656;margin-left:5px">删除</span>
							    </c:if>
							</span>
								<img src="${follow[2]}">
								<h3>${follow[1]}</h3>
								<p>${follow[0]}</p>
							</div>
							<div class="viewpost">
								<!--warn-->
								<!--subject-->
								<!--banned-->
								<!--price-->
								<!--sort-->
								<!--content-->
								<div class="viewthread_content">${follow[3]}
								
								</div>
								<!--attachment-->
					
							</div>
						</div>

						<div id="manage_4335" class="managepost" style="display:none">
							<ul>
								<li><a>回复</a>
								</li>
							</ul>
						</div></td>
				</tr>
			</tbody>
			</c:forEach>
		</table>
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
	<div id="footernv" style=" text-align: center;  padding-top: 21px;">
  <a href="${pageScope.basePath}webpage/weixin/idea/forum/addsendCard.jsp?type=2&cardid=${entity.id}">
    <i class="fa fa-pencil fa-2x">快速回复</i>
    </a>
</div>

</body>
<script src="${pageScope.basePath}plug-in/layer/layer.js"
	type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="${pageScope.basePath}plug-in/forum/style.css">
<script type="text/javascript">var STYLEID = '3', STATICURL = 'static/', IMGDIR = 'template/comeing_weixin/touch/style', VERHASH = 'uqn', charset = 'gbk', discuz_uid = '0', cookiepre = 'YSVH_2132_', cookiedomain = '', cookiepath = '/', showusercard = '1', attackevasive = '0', disallowfloat = 'newthread', creditnotice = '1|威望|,2|金钱|,3|贡献|', defaultstyle = '', REPORTURL = 'aHR0cDovLzEyMS4xOTkuNDAuMTUyL2RlbW8yMDE0L3dlaXNob3AvZm9ydW0ucGhwP21vZD12aWV3dGhyZWFkJnRpZD01NTkmbW9iaWxlPTI=', SITEURL = 'http://121.199.40.152/demo2014/weishop/', JSPATH = 'static/js/';</script>
<script type="text/javascript">
function goto(url){window.location.href=url;}
		function deletefollow(ids){
				layer.open({
			    content: "注意!删除后不能恢复该回复，确定删除该恢复吗?",
			    btn: ['确认', '取消'],
			    shadeClose: false,
			    yes: function(){
						 $.ajax({
							url:"forumController.do?deletefollowCard&id="+ids,
							type:"POST",
							success:function(data){
									 var even=$("#autolist_"+ids);
										if(data.indexOf("success")){
										 layer.open({content: '删除成功', time: 1});
										 even.html("");
										}else{
										 layer.open({content: '删除失败', time: 1});
										} 
								}
							}); 
			    }, no: function(){
			        layer.open({content: '且行且珍惜~', time: 1});
			    }
			});
		}
</script>

<style type="text/css">
#footernv li{
margin-top: 10px;
}
.fa-pencil{
font-size:15px;
font-weight:400;
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