<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>个人信息完善</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="<%=basePath%>/plug-in/mui/css/mui.min.css">
    <style>
    	 .shanrd-box{
            position:fixed;
            width: 100%;
            height: 100%;
            left:0px;
            top:0px;
            background-color: rgba(0,0,0,0.6);
            display: -webkit-box;
            -webkit-box-pack:center;
            -webkit-box-align:center;
            z-index:99999;
        }
        .dialogbox{
            padding:1em;
            border-radius:10px;
            background-color: rgba(0,0,0,0.8);

        }
        .load{
            color:white;
            text-indent:2em;
            display:block;
            background-color:white;
            line-height:1.3em;
            background: url("<%=basePath%>/userfiles/images/loading-2.gif") no-repeat;
            background-position:0 2;
            background-size:20px;
        }
        h5 {
            margin: 5px 7px;
        }
        .input{text-align: right}
        .right-input input{padding-right: 35px}
        .mui-btn{width: 100%}
        .mui-input-group .mui-input-row{
        	height: 43px;
        }
    </style>
</head>

<body>
<div class="shanrd-box">
    <div class="dialogbox">
        <div class="load">loading...</div>
    </div>
</div>
<header class="mui-bar mui-bar-nav">
    <h1 class="mui-title">个人信息完善</h1>
</header>
<div class="mui-content">
    <div class="mui-content-padded" style="margin-top: 10px">
        <form class="mui-input-group">
            <div class="mui-input-row">
                <label>称呼&nbsp;:&nbsp;</label>
                <input type="text" name="name" style="text-align: left;" class="input" placeholder="请填写称呼..." >
            </div>
            <div class="mui-input-row">
                <label>手机&nbsp;:&nbsp;</label>
                <input type="text" class="input" style="text-align: left;" maxlength="11" name="phone" placeholder="请填写手机..."  >
            </div>
            <div class="mui-input-row">
                <label>性别&nbsp;:&nbsp;</label>
                <SELECT class="input"  name="sex">
						    <OPTION value="1">男</OPTION>
						    <OPTION value="0">女</OPTION>
				</SELECT>
            </div>
            <div class="mui-input-row">
                <label>区域&nbsp;:&nbsp;</label>
                <SELECT class="map"> 
				</SELECT>
            </div>
             <div class="mui-input-row" style="background: #F7F7F6;">
                <label>技能&nbsp;:&nbsp;</label>
       		 </div>
        	<textarea style="width: 100%" rows="4" name="skill" placeholder="请填写个人技能说明..." ></textarea>
        </form>
        <div class="title">&nbsp;</div>
        <div class="title">&nbsp;</div>
        <button type="button" onclick="submitBidding(this)" style="padding: 10px;" class="mui-btn mui-btn-primary">保存</button>
        <div class="title" style="height: 10px">&nbsp;</div>
       
         
    </div>
</div>
</body>
<script src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript">
	var openid='${openid}';

	var elemlength=$(".map").length;
	
	$(function() {
	   	loadSelectData(0,0,null);
	   
	});
		
    function loadSelectData(index,parentid,after){
        $(".shanrd-box").show();
        $select=$(".map").eq(index);
        if(!$select)return;
        $select.empty();
        //projectInvitationController.do?getInvitationbyParentid&parentsid="+parentid
        $.getJSON("<%=basePath%>projectPartController.do?getPartbyParentid&parentsid="+parentid,"",function(data){
        	$select.append('<option value="">请选择...</option>');
        	for(var i=0;i<data.resultList.length;i++){
              $select.append("<option value='"+data.resultList[i].id+"'>"+data.resultList[i].region+"</option>");
            }
           if(after&&typeof after ==='function')after();
           setTimeout(function(){
               $(".shanrd-box").hide();
           },200);
        });
    }
    
	function submitBidding(e){
		var place=$(".map:last").val();
		var sex=$("[name=sex]").val();
		var phone=$("[name=phone]").val();
		var name=$("[name=name]").val();
		if(!sex||!phone||!name){
			alert("请完善个人信息！");
			return;
		}
		if(!place){
			alert("地区未选择！");
			return;
		}
		var skill=$("[name=skill]").val();
		$(e).attr('disabled','true');
		var partdesc=$(".map").find("option:selected").text();
		$.post('<%=basePath%>projectPersonController.do?doAdd',
				{skill:skill,part:place,partdesc:partdesc,openid:openid,sex:sex,phone:phone,name:name},
				  function(data){
					if(!data||!data.jsonStr||data.jsonStr.indexOf("true")==-1){
						alert("后台异常！");
						$(e).removeAttr("disabled"); 
					}else{
						//成功	
						alert("綁定成功！！");
						WeixinJSBridge.call('closeWindow');
					}
				  },
		"json");
	}


</script>

</html>