<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%--<form class="form-horizontal" action="http://58.252.73.14:11180/vems/login!doLogin.action" method="post">
			  <div class="form-group">
			    <label for="username" class="col-sm-3 control-label">账号</label>
			    <div class="col-sm-9">
			      <input type="text" class="form-control" id="username" name="username" placeholder="账号" value="yh">
			    </div>
			  
			  </div>
			  <div class="form-group">
			    <label for="password" class="col-sm-3 control-label">密码</label>
			    <div class="col-sm-9">
			      <input type="password" class="form-control" id="password" name="password" placeholder="密码" value="123123">
			    </div>
			   
			  </div>
			  <div class="form-group">
			    <label for="secode" class="col-sm-3 control-label">验证码</label>
			    <div class="col-sm-6 yanzheng-input">
			      <input type="text" class="form-control" id="secode" name="secode" placeholder="验证码">
			    </div>
			    <div class="col-sm-3 yanzheng-img"><img id="secode_image" style="width: 80px; height: 33px;" onclick="secodeLoader();" src="/vems/login!secode.action?T=1476781311212"></div>
			    <div class="warning-tips">
			    	<img src="http://58.252.73.14:11180/vems/login!secode.action?T=1476781230511">
			    	
			    			验证码错误，请重新登录
			    	
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="enter-btn">
			      <button type="submit" class="btn btn-default btn-block">登录</button>
			    </div>
			  </div>
			</form>
  --%>
  <form class="form-horizontal" action="http://localhost:8084/yuhiwx/projectServerTypeController.do?datagrid" method="post"><%--
			  <div class="form-group">
			    <label for="username" class="col-sm-3 control-label">账号</label>
			    <div class="col-sm-9">
			      <input type="text" class="form-control" id="username" name="name" placeholder="账号" value="yh">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="password" class="col-sm-3 control-label">密码</label>
			    <div class="col-sm-9">
			      <input type="password" class="form-control" id="password" name="phone" placeholder="密码" value="123123">
			    </div>
			  </div>
			<input type="password" class="form-control" id="password" name="sex" placeholder="密码" value="1">
			    <input type="password" class="form-control" id="password" name="openid" placeholder="密码" value="123123">
			       <input type="password" class="form-control" id="password" name="partdesc" placeholder="密码" value="123123">
			     <input type="password" class="form-control" id="password" name="part" placeholder="密码" value="297eb23e57c270c50157c2758abb0008">
			  <div class="form-group">
			    <div class="enter-btn">
			      <button type="submit" class="btn btn-default btn-block">登录</button>
			    </div>
			  </div>--%>
			<input type="text" class="form-control" name="page" placeholder="密码" value="1">
			    <input type="text" class="form-control"  name="rows" placeholder="密码" value="10">
			    <input type="text" class="form-control" name="time" placeholder="密码" value="1">
			 	<button type="submit" class="btn btn-default btn-block">登录</button>
			</form>
  
  </body>
</html>
