<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- saved from url=(0073)http://www.17sucai.com/preview/171072/2016-04-07/usercenter/ddcenter.html -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="keywords" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>工程单中心</title>
<link href="<%=basePath%>/webpage/weixin/idea/project/sshtml_files/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>/webpage/weixin/idea/project/sshtml_files/style.css" rel="stylesheet">
<script src="<%=basePath%>/plug-in/jquery/jquery-1.8.3.min.js"></script>
</head>
<body class="huibg">
	<!-- <div class="menu-wrap">
		<nav class="menu">
			<div class="icon-list">
        <a href="http://www.17sucai.com/preview/171072/2016-04-07/usercenter/index.html"><i class="iconfont icon-home"></i><span>首页</span></a>
        <a href="http://www.17sucai.com/preview/171072/2016-04-07/usercenter/personalcenter.html"><i class="iconfont icon-yonghux"></i><span>个人中心</span></a>
        <a href="./sshtml_files/sshtml.html"><i class="iconfont icon-liebiao"></i><span>订单中心</span></a>
        <a href="http://www.17sucai.com/preview/171072/2016-04-07/usercenter/userinfo.html"><i class="iconfont icon-xitongmingpian"></i><span>个人信息</span></a>
        <a href="http://www.17sucai.com/preview/171072/2016-04-07/usercenter/dizhi.html"><i class="iconfont icon-dizhi"></i><span>地址信息</span></a>
      </div>
		</nav>
		<button class="close-button" id="close-button">Close Menu</button>
		<div class="morph-shape" id="morph-shape" data-morph-open="M-1,0h101c0,0,0-1,0,395c0,404,0,405,0,405H-1V0z">
			<svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" viewBox="0 0 100 800" preserveAspectRatio="none">
				<path d="M-1,0h101c0,0-97.833,153.603-97.833,396.167C2.167,627.579,100,800,100,800H-1V0z"></path>
			<desc>Created with Snap</desc><defs></defs></svg>
		</div>
	</div> -->

	<nav class="navbar text-center">
		<!--  <button class="topleft" onclick="javascript:history.go(-1);"><span class="iconfont icon-fanhui"></span></button> -->
		<a class="navbar-tit center-block">工程单中心</a>
		<!-- 	<button class="topnav" id="open-button"><span class="iconfont icon-1"></span></button> -->
	</nav>
	<br>
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="ddcenter.html#sp1" data-toggle="tab">未处理</a>
		</li>
		<li><a href="ddcenter.html#sp2" data-toggle="tab">已完成</a>
		</li>
	</ul>
	<c:set var="nosuccess" value="false" ></c:set>
	<c:set var="success" value="false" ></c:set>
	
	<div id="myTabContent" class="tab-content">
	
		<div class="tab-pane fade in active" id="sp1">
			<ul class="ddlist">
			  	<c:forEach items="${projectlist}" var="item">
					<c:if test="${item[8]!='待评价'&&item[8]!='待回访'&&item[8]!='已回访'}">
			  		<c:set var="nosuccess" value="true" ></c:set>
						<li>
						<p>工程单号：${item[9]}</p>
						<p>创建时间：${item[1]}</p>
						<p>发起人称呼：${item[10]}</p>
						<p>发起人手机：${item[6]}</p>
						<p>发起人内容：${item[7]}</p>
						<p>
							<span>状态：${item[8]}</span>
						</p> 
						<c:if test="${idtype==1}">
						<p>
							<span>
							<c:choose>
	  							<c:when test="${item[8]=='待接单'}">
	  							<a href="<%=basePath %>projectCardController.do?updateProjectState&id=${item[9]}&type=10078">
						  		操作：	<button class="btn">接单</button></a>
						  			<a href="<%=basePath %>projectCardController.do?updateProjectState&id=${item[9]}&type=10079">
						  			<button class="btn">拒单</button></a>
						  		</c:when>
						
						  		<c:when test="${item[8]=='办理中'}">
						  		操作：	请填写办理结论<br/>
						  			 <textarea rows="5" cols="30" style="width:100%;" name="finishedcontent"></textarea><br/><br/>
									<button class="btn" onclick="submitchange('<%=basePath %>projectCardController.do?updateProjectState&id=${item[9]}&type=10080')">完成办理</button>
						  			<script type="text/javascript">
						  				function submitchange(url){
						  					location.href=url+"&finishedcontent="+$("[name=finishedcontent]").val()
						  				}
						  			</script>
						  		</c:when>
						  		<c:when test="${item[8]=='待办理'}">
								操作：	<a href="<%=basePath %>projectCardController.do?updateProjectState&id=${item[9]}&type=10081">
									<button class="btn">开始办理</button></a>
						  		</c:when>
	  					</c:choose>
							</span>
						</p> 
						</c:if>
					</li>
					</c:if>
				</c:forEach>
				<c:if test="${nosuccess=='false'}">
				<li style="text-align: center;font-size: 15px">暂无未处理的工单~</li>
				</c:if>
				</ul>
			</div>
		 <div class="tab-pane fade" id="sp2">
			<ul class="ddlist">
				<c:forEach items="${projectlist}" var="item">
				<c:if test="${item[8]=='待评价'||item[8]=='待回访'||item[8]=='已回访'}">
				<c:set var="success" value="true" ></c:set>
				<li>
						<p>工程单号：${item[9]}</p>
						<p>创建时间：${item[1]}</p>
						<p>发起人手机：${item[6]}</p>
						<p>发起人内容：${item[7]}</p>
						<p>
							<span>状态：${item[8]}</span>
						</p>  
						<c:if test="${idtype==2}">
							操作：
							<c:if test="${item[8]=='待评价'}">
	  							操作：	请填写评价<br/>
						  			 <textarea rows="5" cols="30" style="width:100%;" name="evaluatecontent"></textarea><br/>
						  			 评分等级:<div id="xzw_starSys"  style="width:100%;">
												<div id="xzw_starBox">
													<ul class="star" id="star">
														<li><a href="javascript:void(0)" title="1" class="one-star">1</a></li>
														<li><a href="javascript:void(0)" title="2" class="two-stars">2</a></li>
														<li><a href="javascript:void(0)" title="3" class="three-stars">3</a></li>
														<li><a href="javascript:void(0)" title="4" class="four-stars">4</a></li>
														<li><a href="javascript:void(0)" title="5" class="five-stars">5</a></li>
													</ul>
													 <div class="current-rating" id="showb"></div> 
												</div>
											</div>  
										<input type="hidden" name="successlevel" value=""><br/>
									<button class="btn" style="margin-top: 10px;width:100%;" onclick="submitchange('<%=basePath %>projectCardController.do?updateProjectState&id=${item[9]}&type=10082')">提交</button>
						  			<script type="text/javascript">
						  				function submitchange(url){
						  					location.href=url+"&evaluatecontent="+$("[name=evaluatecontent]").val()+"&successlevel="+$("[name=successlevel]").val();
						  				}
										$(document).ready(function(){
										var stepW = 24;
										$(this).find('a').blur();
										 var stars = $("#star > li");
										    stars.each(function(i){
										        $(stars[i]).click(function(e){
										            var n = i+1;
										           	$("#showb").css({"width":stepW*n});
										            $(this).find('a').blur();
										            $("[name=successlevel]").val(n+"");
										        });
										    });
										});
									</script>
						  	</c:if>
						</c:if>
						</li>
				</c:if>
				</c:forEach>
				<c:if test="${success=='false'}">
				<li style="text-align: center;font-size: 15px">暂无更多记录~</li>
				</c:if>
			</ul>
		</div> 

	</div>
	  	
	  
	
	
	
</body>

<script src="<%=basePath%>/webpage/weixin/idea/project/sshtml_files/bootstrap.min.js"></script>

</html>
