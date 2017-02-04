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
		<title>填写工程单详细</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/plug-in/mui/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/plug-in/mui/css/feedback.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/plug-in/cal/lCalendar2.css" />
	
	<style type="text/css">
	.red{color: red;}input,textarea{color: #8f8f94;}
	p{color:#444448;}.timeselect{float: left;margin-right: 2px;}
	</style>
	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<button onclick="subCreateProjectCard(this)"
			 class="mui-btn mui-btn-blue mui-btn-link mui-pull-right">提交</button>
			<h1 class="mui-title">填写工程单详细</h1>
		</header>
		<div class="mui-content">
			<p><span class="red">*&nbsp;</span>手机号(必填)</p>
			<input class="mui-input-clear " id="phone" value="<%=request.getParameter("phone")%>" style="margin-bottom: 3px;"  type="text" placeholder="请填写有效的号码..."></input>
			<!-- <p>图片(选填,提供问题截图,总大小10M以下)</p>
			<input class="mui-input-clear " style="margin-bottom: 3px;" type="text" placeholder="请详细描述你的问题和意见..."></input>
			 -->
			<p><span class="red">*&nbsp;</span>入户处理时间(必填)</p>
			<div style="background-color: white;padding: 5px;">
			<div class="mui-content-padded">
				<div class="mui-inline" style="color:#444448;"><span class="red">*&nbsp;</span>&nbsp;开始时间:&nbsp;<font id="starttime"></font></div>
				<a class="mui-pull-right mui-inline" show="starttime"  onclick="editDatetime(event);" style="color:#444448;">
					<span class="timeselect" style="margin-top: 2px">选择时间</span> 
					<span class="mui-icon mui-icon-plus" style="font-size: 22px;"></span>
				</a>
			</div>
			<div class="mui-content-padded">
				<div class="mui-inline" style="color:#444448;"><span class="red">*&nbsp;</span>&nbsp;结束时间:&nbsp;<font id="endtime"></font></div>
				<a class="mui-pull-right mui-inline" show="endtime" onclick="editDatetime(event);" style="color:#444448;">
					<span class="timeselect" style="margin-top: 2px">选择时间</span> 
					<span class="mui-icon mui-icon-plus"  style="font-size: 22px;"></span>
				</a>
			</div>
			</div>
			<p>其他联系方式(可选)</p>
			<div class="mui-input-row">
				<input id='contact' type="text" style="margin-bottom: 3px;" class="mui-input-clear contact" placeholder="方便我们以多方式联系你... " />
			</div>
			<div class="mui-content-padded">
				<div class="mui-inline" style="color:#444448;"><span class="red">*&nbsp;</span>问题描述(必填)</div>
				<a class="mui-pull-right mui-inline" href="#popover" style="color:#444448;">
					<span class="timeselect">快捷输入</span> 
					<span class="mui-icon mui-icon-arrowdown" style="font-size: 22px;"></span>
				</a>
				<div id="popover" class="mui-popover">
					<div class="mui-popover-arrow"></div>
					<div class="mui-scroll-wrapper">
						<div class="mui-scroll">
							<ul class="mui-table-view">
								<!--仅流应用环境下显示-->
								<li class="mui-table-view-cell stream">
									<a href="#">桌面快捷方式创建失败</a>
								</li>
								<li class="mui-table-view-cell"><a href="#">公共设施损坏</a></li>
								<li class="mui-table-view-cell"><a href="#">家电安装与维修</a></li>
								<li class="mui-table-view-cell"><a href="#">访问设施维修</a></li>
								<li class="mui-table-view-cell"><a href="#">更换设施</a></li>
							</ul>
						</div>
					</div>

				</div>
			</div>
			<div class="row mui-input-row">
				<textarea id='question' class="mui-input-clear question" placeholder="请详细描述你所遇到的问题..."></textarea>
			</div>
			
		</div>
		<script src="<%=basePath%>/plug-in/mui/js/mui.min.js"></script>
		<script src="<%=basePath%>/plug-in/cal/lCalendar2.js"></script>
		<script type="text/javascript">
			
		var question=document.getElementById("question");
		var desc=document.getElementById("desc");
		var phone=document.getElementById("phone");
		var starttime=document.getElementById("starttime");
		var endtime=document.getElementById("endtime");
		var contact=document.getElementById("contact");
		
		(function($) {
				mui.init();
				mui('.mui-scroll-wrapper').scroll();
				var result = $('.result');
				//选择快捷输入
				mui('.mui-popover').on('tap','li',function(e){
					//question.value = question.value + this.children[0].innerHTML;
					question.value = this.children[0].innerHTML;
				  mui('.mui-popover').popover('toggle')
				});
			})(mui);
		
		function subCreateProjectCard(elem){
			elem.setAttribute("disabled","disabled");
			var formdata={
					ownerid:'<%=request.getParameter("ownerid")%>',
					openid:'<%=request.getParameter("openid")%>',
					part:'<%=request.getParameter("part")%>',
					content:question.value,
					desc:desc.value,
					phone:phone.value,
					starttime:starttime.innerHTML+":00",
					endtime:endtime.innerHTML+":00",
					otherContact:contact.value,
					invitationid:'1'
				};
				mui.ajax('<%=basePath%>projectCreateController.do?doAdd',{
					data:formdata,
					type:'post',//HTTP请求类型
					timeout:10000,//超时时间设置为10秒；
					success:function(data){
						if(data.indexOf("1")!=-1){
							WeixinJSBridge.call('closeWindow');
							//location.href="<%=basePath%>webpage/weixin/idea/project/template/projectMenuTab2.jsp?ownerid=<%=request.getParameter("ownerid")%>";							
						}else{
							alert("异常");
							elem.removeAttribute("disabled");
						}
					},
					error:function(xhr,type,errorThrown){
						//异常处理；
						mui.toast('访问异常');
						console.log(type);
					}
				});
		}
		</script>
	</body>

</html>