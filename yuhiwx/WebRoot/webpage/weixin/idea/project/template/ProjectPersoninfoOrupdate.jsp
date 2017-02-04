<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>个人信息</title>
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
                <input type="text" name="name"  disabled="disabled" value="${pp.name}" style="text-align: left;" class="input" placeholder="请填写称呼..." >
            </div>
            <div class="mui-input-row">
                <label>手机&nbsp;:&nbsp;</label>
                <input type="text"  disabled="disabled" value="${pp.phone}" class="input" style="text-align: left;" maxlength="11" name="phone" placeholder="请填写手机..."  >
            </div>
            <div class="mui-input-row" id="place">
                <label style="width: 25%">地区&nbsp;:&nbsp;</label>
                <input type="text" class="input" disabled="disabled"  value="${pp.partdesc}" style="width:71%; text-align: left;" >
            </div>
            <div class="mui-input-row place">
                <label>区域&nbsp;:&nbsp;</label>
                <SELECT class="map"> 
				</SELECT>
            </div>
             <div class="mui-input-row" style="background: #F7F7F6;">
                <label>技能&nbsp;:&nbsp;</label>
       		 </div>
        	<textarea style="width: 100%" rows="4" name="skill" placeholder="请填写个人技能说明..." >${pp.skill }</textarea>
        </form>
        <div class="title">&nbsp;</div>
         <div  class="mui-input-row" id="uf" style="display:none;">
          <button type="button"  style="   width: 49%;margin-left: 2%" onclick="onupdate(false)" class="mui-btn mui-btn-primary">取消编辑</button>
          <button type="button"  style="width: 49%;" class="mui-btn mui-btn-primary" onclick="submitBidding(this)">保存</button>
         </div>
        <div class="title">&nbsp;</div>
        
        <button type="button" id="ut" onclick="onupdate(true)" style="padding: 10px;margin-top: 5px;" class="mui-btn mui-btn-primary">编辑</button>
       
         
    </div>
</div>
</body>
<script src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript">
	var openid='${pp.openid}';
	var id='${pp.id}';
	var elemlength=$(".map").length;
	
	$(function() {
		if(!openid||!id){
			alert('访问异常');
			WeixinJSBridge.call('closeWindow');
		}
		 $(".place").css("display","none");
		 $(".shanrd-box").hide();
	});
		
	function onupdate(isupdate){
		if(isupdate){
			$("#ut").css("display","none");
			$("#place").css("display","none");
			$(".input").removeAttr("disabled");
			$(".place").css("display","block");
			loadSelectData(0,0,null);
		   	$('.map').change(function(){ 
		   		getNextselect(this);
		   	});	
		   	$("#uf").css("display","block");
		}else{
			$("#uf").css("display","none");
			$("#place").css("display","block");
			$(".input").attr("disabled","disabled");
			$(".place").css("display","none");		
			$("#ut").css("display","block");
		}
	}
	
	
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
		var phone=$("[name=phone]").val();
		var name=$("[name=name]").val();
		if(!phone||!name){
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
		$.post('<%=basePath%>projectPersonController.do?doUpdate',
				{skill:skill,part:place,partdesc:partdesc,openid:openid,phone:phone,name:name,id:id},
				  function(data){
					if(!data||!data.jsonStr||data.jsonStr.indexOf("true")==-1){
						alert("后台异常！");
						$(e).removeAttr("disabled"); 
					}else{
						//成功	
						alert("修改成功！");
						WeixinJSBridge.call('closeWindow');
					}
				  },
		"json");
	}


</script>

</html>