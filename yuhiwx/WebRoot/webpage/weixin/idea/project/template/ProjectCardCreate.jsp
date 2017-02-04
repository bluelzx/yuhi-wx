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
	.red{color: red;}input,textarea,#starttime,#endtime{color: #8f8f94;}
	p{color:#444448;}.timeselect{float: left;margin-right: 2px;}
	.footer-div {
    position: fixed;
    left: 0;
    bottom: 0;
    z-index: 10;
    text-align: center;
    width: 100%;
    height: 45px;
    line-height: 45px;
    /* background: url(../img/footer-div-bg.png); */
    background: #fff;
}
.footer-div .div-right .mui-btn {
    height: 45px;
    line-height: 45px;
    margin: 0;
    padding: 0;
    width: 150px;
    border-radius: 0px;
    font-size: 0.32rem;
}
.mui-btn {
    width: 80px;
}
.mui-btn-blue, .mui-btn-primary, input[type=submit] {
    color: #fff;
    border: 1px solid #007aff;
    background-color: #007aff;
}
#shanrd-box{position:fixed;width:100%;height:100%;left:0;top:0;background-color:rgba(0,0,0,0.3);display:-webkit-box;-webkit-box-pack:center;-webkit-box-align:center;z-index:99999}.dialogbox{padding:1em;border-radius:10px;background-color:rgba(0,0,0,0.8)}.load{color:white;text-indent:2em;display:block;background-color:white;line-height:1.3em;background:url("<%=basePath%>/userfiles/images/loading-2.gif") no-repeat;background-position:0 2;background-size:20px}
	</style>
	</head>

	<body>
		<!--加载动画-->
		<div id="shanrd-box">
		    <div class="dialogbox">
		        <div class="load">系统正在处理工单中...</div>
		    </div>
		</div>
		<header class="mui-bar mui-bar-nav">
			<%--<button onclick="subCreateProjectCard(this)"
			 class="mui-btn mui-btn-blue mui-btn-link mui-pull-right">提交</button>
			--%><h1 class="mui-title">填写工程单详细</h1>
		</header>
		<div class="footer-div">
			<div style="visibility: hidden;width: 30%;float: left;" class="div-left">实付款：<span>¥&nbsp;158</span></div>
			<div class="div-right" >
				<div class="mui-btn mui-btn-primary" onclick="subCreateProjectCard(this)" style="float: right;font-size: 16px">
					提交
				</div>
			</div>
		</div>
		<div class="mui-content" style="    margin-bottom: 13%;">
			<p><span class="red">*&nbsp;</span>手机号(必填)</p>
			<input class="mui-input-clear " id="phone" value="<%=request.getParameter("phone")%>" style="margin-bottom: 3px;"  type="text" placeholder="请填写有效的号码..."></input><%--
			 <p>图片(选填,提供问题截图,总大小10M以下)</p>
			<input class="mui-input-clear " style="margin-bottom: 3px;" type="text" placeholder="请详细描述你的问题和意见..."></input>
			
			--%><p><span class="red">*&nbsp;</span>入户处理时间(必填)</p>
			<div style="background-color: white;padding: 5px;">
			<div class="mui-content-padded">
				<div class="mui-inline" style="color:#444448;"><span class="red">*&nbsp;</span>&nbsp;开始时间:&nbsp;<font id="starttime"></font></div>
				<a class="mui-pull-right mui-inline" show="starttime"  onclick="editDatetime(event);" style="color:#444448;">
					选择时间
					<span class="mui-icon mui-icon-plus" style="font-size: 22px;"></span>
				</a>
			</div>
			<div class="mui-content-padded">
				<div class="mui-inline" style="color:#444448;"><span class="red">*&nbsp;</span>&nbsp;结束时间:&nbsp;<font id="endtime"></font></div>
				<a class="mui-pull-right mui-inline" show="endtime" onclick="editDatetime(event);" style="color:#444448;">
					选择时间
					<span class="mui-icon mui-icon-plus"  style="font-size: 22px;"></span>
				</a>
			</div>
			</div>
			<p>其他联系方式(可选)</p>
			<div class="mui-input-row">
				<input id='contact' type="text" value="791120662@qq.com" style="margin-bottom: 3px;" class="mui-input-clear contact" placeholder="方便我们以多方式联系你... " />
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
				<textarea id='question' class="mui-input-clear question" placeholder="请详细描述你所遇到的问题...">公共设施损坏</textarea>
			</div>
			<p style="text-align: center;margin-bottom: 0px;" >
				广东元海集团有限公司版权所有
			</p>
			<p style="text-align: center; margin-bottom: 0px;padding-top: 0">
				Copyright © 2014 | 粤ICP备14046124号-4
			</p>
		</div>
			
		<script src="<%=basePath%>/plug-in/mui/js/mui.min.js"></script>
		<script src="<%=basePath%>/plug-in/cal/lCalendar2.js"></script>
		<script type="text/javascript">
		var loadbox=document.getElementById("shanrd-box");
		var question=document.getElementById("question");
		var desc=document.getElementById("desc");
		var phone=document.getElementById("phone");
		var starttime=document.getElementById("starttime");
		var endtime=document.getElementById("endtime");
		var contact=document.getElementById("contact");
		
		(function($) {
				loadbox.style.display="none";
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
			loadbox.style.display="-webkit-box";
			elem.setAttribute("disabled","disabled");
			var formdata={
					ownerid:'<%=request.getParameter("ownerid")%>',
					openid:'<%=request.getParameter("openid")%>',
					part:'<%=request.getParameter("part")%>',
					content:question.value,
					//desc:desc.value,
					phone:phone.value,
					starttime:starttime.innerHTML+":00",
					endtime:endtime.innerHTML+":00",
					otherContact:contact.value,
					invitationid:'0'
				};
				mui.ajax('<%=basePath%>projectCreateController.do?doAdd',{
					data:formdata,
					type:'post',//HTTP请求类型
					timeout:10000,//超时时间设置为10秒；
					success:function(data){
						if(data.indexOf("1")!=-1){
							WeixinJSBridge.call('closeWindow');
							//location.href="webpage/weixin/idea/project/template/projectMenuTab2.jsp?ownerid=<%=request.getParameter("ownerid")%>";							
						}else{
							loadbox.style.display="none";
							alert("异常");
							elem.removeAttribute("disabled");
						}
					},
					error:function(xhr,type,errorThrown){
						loadbox.style.display="none";
						mui.toast('访问异常');
						console.log(type);
					}
				});
		}
		</script>
	</body>

</html>