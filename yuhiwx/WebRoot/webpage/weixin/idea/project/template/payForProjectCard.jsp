<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="height: 100%;">
<head>
    <meta charset="utf-8">
    <title>支付页面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
</head>

<body style="height: 100%;">

<div class="mui-content" style="height: 100%;">

	
    <div class="mui-content-padded" style="height: 100%;margin-top: 10px">
  
	    <div style="height:58%;overflow: auto;">
	    		<ul class="mui-table-view" id="datagrid">
	    		</ul>
	    </div>
    	<form class="mui-input-group">
            <div class="mui-input-row">
                <label>数量&nbsp;:&nbsp;</label>
               <input  disabled="disabled" style="text-align: right;margin-top: 10px; padding-right: 2%;"  id="count" class="input"  />
            </div>
            <div class="mui-input-row">
             	<label>价格&nbsp;:&nbsp;</label>
                <input id="price" style="    text-align: right;margin-top: 10px; padding-right: 2%;" disabled="disabled" class="input" />
            </div>
        </form>
     
        
        <div class="title">&nbsp;</div> 
        <button type="button" onclick="submit()" style="padding: 10px;width: 100%;" class="mui-btn mui-btn-primary">确定支付</button>
        <div class="title" style="height: 10px">&nbsp;</div>
        <%--<input  id="json"></input>
       --%></div>
</div>
</body>


</html>