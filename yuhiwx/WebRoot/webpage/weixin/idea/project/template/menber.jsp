<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hello MUI</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <!--标准mui.css-->
    <link rel="stylesheet" href="<%=basePath%>/plug-in/mui/css/mui.min.css">
    <style>
        h5 {
            margin: 5px 7px;
        }
        .input{text-align: right}
        .right-input input{padding-right: 35px}
        .mui-btn{width: 100%}
    </style>
</head>

<body>
<header class="mui-bar mui-bar-nav">
    <h1 class="mui-title">会员信息</h1>
</header>
<div class="mui-content">
    <div class="mui-content-padded" style="margin: 0px">
        <form class="mui-input-group">
            <div class="mui-input-row">
                <label>名字</label>
                <input type="text" class="input" placeholder="Kyle.Chan" disabled="true">
            </div>
            <div class="mui-input-row">
                <label>手机</label>
                <input type="text" class="input" placeholder="18099998921" disabled="true">
            </div>
            <div class="mui-input-row">
                <label>证件号</label>
                <input type="text" class="input" placeholder="444090912333819" disabled="true">
            </div>
            <div class="mui-input-row">
                <label>地址</label>
                <input type="text" class="input" placeholder="中国广东佛山" disabled="true">
            </div>
            <div class="mui-input-row right-input">
                <label>我的房产</label>
                <input type="text" class="input" value="未绑定" disabled="true">
                <a class="mui-navigate-right"></a>
            </div>
        </form>
        <div class="title">&nbsp;</div>
        <form class="mui-input-group">
            <div class="mui-input-row">
                <label>会员等级</label>
                <input type="text" class="input" placeholder="3级" disabled="true">
            </div>
        </form>
        <div class="title">&nbsp;</div>
        <button type="button" class="mui-btn mui-btn-primary">修改</button>
        <div class="title" style="height: 10px">&nbsp;</div>
        <button type="button" class="mui-btn">取消</button>
    </div>
</div>
<script src="./js/mui.min.js"></script>
<script>
    mui.init({
        swipeBack: true //启用右滑关闭功能
    });
</script>
</body>

</html>