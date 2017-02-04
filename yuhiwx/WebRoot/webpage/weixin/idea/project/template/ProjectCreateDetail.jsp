<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en" class="feedback">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>工程单详细</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/plug-in/mui/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/plug-in/mui/css/feedback.css" />
	</head>

	<body>
		<div class="mui-content">
			<p>客户名称</p>
			<input class="mui-input-clear "  disabled="disabled" value="${projectCardPage.name}" style="margin-bottom: 3px;"  type="text" ></input>
			<p>详细地址</p>
			<input class="mui-input-clear "  disabled="disabled" value="${projectCardPage.partdesc}" style="margin-bottom: 3px;"  type="text" ></input>
			<p>手机号</p>
			<input class="mui-input-clear " id="phone" disabled="disabled" value="${projectCardPage.phone}" style="margin-bottom: 3px;"  type="text" ></input>
			<p >工单状态&nbsp;&nbsp;&nbsp;</p>
			<input class="mui-input-clear"  disabled="disabled" value="未分配" style="margin-bottom: 3px;" type="text"></input>
			<p>入户处理时间</p>
			<div style="background-color: white;padding: 5px;">
			<div class="mui-content-padded">
				<div class="mui-inline">&nbsp;开始时间:&nbsp;<font style="color:#403D3D" id="starttime">${projectCardPage.starttime}</font></div>
			</div>
			<div class="mui-content-padded">
				<div class="mui-inline">&nbsp;结束时间:&nbsp;<font style="color:#403D3D" id="endtime">${projectCardPage.endtime}</font></div>
			</div>
			</div>
			<p>其他联系</p>
			<div class="mui-input-row">
				<input id='contact' type="text"  disabled="disabled"  value="${projectCardPage.otherconcat}" style="margin-bottom: 3px;" class="mui-input-clear contact" placeholder="暂无其他联系方式... " />
			</div>
			<div class="mui-content-padded">
				<div class="mui-inline">
				</span>问题描述</div>
			</div>
			<div class="row mui-input-row">
				<textarea style="height:150px;"  disabled="disabled"  id='question' class="mui-input-clear question">${projectCardPage.content}</textarea>
			</div>
			<%--<p>备注(可选)</p>
			<textarea id='desc' style="height:200px;"  disabled="disabled"  class="mui-input-clear question">${projectCardPage.descs}</textarea>			
		--%></div>
	</body>
</html>