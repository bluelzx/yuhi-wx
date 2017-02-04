<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en" class="feedback">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>问题反馈</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/plug-in/mui/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/plug-in/mui/css/feedback.css" />
	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<button id="submit" class="mui-btn mui-btn-blue mui-btn-link mui-pull-right">发送</button>
			<h1 class="mui-title">问题反馈</h1>
		</header>
		<div class="mui-content">
			<div class="mui-content-padded">
				<div class="mui-inline">问题和意见</div>
				<a class="mui-pull-right mui-inline" href="#popover">
					快捷输入
					<span class="mui-icon mui-icon-arrowdown"></span>
				</a>
				<!--快捷输入具体内容，开发者可自己替换常用语-->
				<div id="popover" class="mui-popover">
					<div class="mui-popover-arrow"></div>
					<div class="mui-scroll-wrapper">
						<div class="mui-scroll">
							<ul class="mui-table-view">
								<!--仅流应用环境下显示-->
								<li class="mui-table-view-cell stream">
									<a href="#">桌面快捷方式创建失败</a>
								</li>
								<li class="mui-table-view-cell"><a href="#">界面显示错乱</a></li>
								<li class="mui-table-view-cell"><a href="#">启动缓慢，卡出翔了</a></li>
								<li class="mui-table-view-cell"><a href="#">偶发性崩溃</a></li>
								<li class="mui-table-view-cell"><a href="#">UI无法直视，丑哭了</a></li>
							</ul>
						</div>
					</div>

				</div>
			</div>
			<div class="row mui-input-row">
				<textarea id='question' class="mui-input-clear question" placeholder="请详细描述你的问题和意见..."></textarea>
			</div>
			<p>图片(选填,提供问题截图,总大小10M以下)</p>
			<textarea id='question' class="mui-input-clear question" placeholder="请详细描述你的问题和意见..."></textarea>
			<p>QQ/邮箱</p>
			<div class="mui-input-row">
				<input id='contact' type="text" class="mui-input-clear contact" placeholder="(选填,方便我们联系你 )" />
			</div>
			<div class="mui-content-padded">
				<div class="mui-inline">应用评分</div>
				<div class="icons mui-inline" style="margin-left: 6px;">
					<i data-index="1" class="mui-icon mui-icon-star"></i>
					<i data-index="2" class="mui-icon mui-icon-star"></i>
					<i data-index="3" class="mui-icon mui-icon-star"></i>
					<i data-index="4" class="mui-icon mui-icon-star"></i>
					<i data-index="5" class="mui-icon mui-icon-star"></i>
				</div>
			</div><br />
		</div>
		<script src="<%=basePath%>/plug-in/mui/js/mui.min.js"></script>
		<script type="text/javascript">
			mui.init();
			mui('.mui-scroll-wrapper').scroll();
			//应用评分
			mui('.icons').on('tap','i',function(){
			  	var index = parseInt(this.getAttribute("data-index"));
			  	var parent = this.parentNode;
			  	var children = parent.children;
			  	if(this.classList.contains("mui-icon-star")){
			  		for(var i=0;i<index;i++){
		  				children[i].classList.remove('mui-icon-star');
		  				children[i].classList.add('mui-icon-star-filled');
			  		}
			  	}else{
			  		for (var i = index; i < 5; i++) {
			  			children[i].classList.add('mui-icon-star')
			  			children[i].classList.remove('mui-icon-star-filled')
			  		}
			  	}
			  	starIndex = index;
		  });
		  	//选择快捷输入
	mui('.mui-popover').on('tap','li',function(e){
	  document.getElementById("question").value = document.getElementById("question").value + this.children[0].innerHTML;
	  mui('.mui-popover').popover('toggle')
	}) 
		</script>
	</body>

</html>