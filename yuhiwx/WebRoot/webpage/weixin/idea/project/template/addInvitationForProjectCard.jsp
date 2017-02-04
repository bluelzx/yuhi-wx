<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html style="height: 100%;">
<head>
    <meta charset="utf-8">
    <title>添加耗材</title>
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
        .delbtn{background-color:#33A3FF;color:white;height:19px;line-height: 0.42;}
    </style>
</head>

<body style="height: 100%;">
<div class="shanrd-box">
    <div class="dialogbox">
        <div class="load">loading...</div>
    </div>
</div>
<div class="mui-content" style="height: 100%;overflow: scroll;">

	
    <div class="mui-content-padded" style="height: 100%;margin-top: 10px">
  
	    <div style="height:55%;overflow: scroll;">
	    		<ul class="mui-table-view" id="datagrid">
	    		</ul>
	    </div>
	    <div class="mui-content">
		
			<!-- tab1 -->
			<div id="item1"  class="mui-control-content mui-active">
				<p  class="title" style="text-align: center;margin-bottom:0px;">注意：通过下拉框添加耗材列表项。</p>
		    	<form class="mui-input-group">
		            <div class="mui-input-row">
		                <label>类型&nbsp;:&nbsp;</label>
		                <SELECT class="map" onchange="getNextselect(this);"> <option value="">请选择...</option>
						</SELECT>
		            </div>
		            <div class="mui-input-row" id="detail">
		                <label>明细&nbsp;:&nbsp;</label>
		                <SELECT class="map" > 
						</SELECT>
		            </div>
		            <div class="mui-input-row" id="curdetail" style="display: none">
		                <label style="width: 30%;">明细&nbsp;:&nbsp;</label>
		                <input type="text" name="detail" class="input"  placeholder="请输入耗材明细.." style="text-align: left;width: 70%;"/>
		            </div>
		             <div class="mui-input-row">
		                <label style="width: 30%;">规格&nbsp;:&nbsp;</label>
		                <input type="text" name="specifications" class="input"  placeholder="请输入耗材规格.." style="text-align: left;width: 70%;"/>
		            </div>
		            <div class="mui-input-row">
		             	<label style="width: 30%;">价格&nbsp;:&nbsp;</label>
		                <input type="number" name="iprice" class="input"  placeholder="请输入耗材费.." style="float: left;text-align: left;width: 34%;"/>
		                <button type="button" style="float: right;width: 25%;margin-top: 5px;" class="mui-btn-primary" onclick="selectvalueToaddgrid(1)"> 添加耗材</button>
		            </div>
		        </form>
			</div>
		<div class="mui-input-row">
        <button type="button" onclick="subm()" style="width:48%;float:left;line-height: 2em;margin-right: 4%" class="mui-btn mui-btn-primary">保存</button>
        <button type="button" onclick="nocansubm()" style="width:48%;float:left;line-height: 2em;" class="mui-btn mui-btn-primary">无需耗材</button>
		</div>
        <div class="title" style="height: 10px">&nbsp;</div>
        
         
</div>
</div>
</div>

</body>
<script src="<%=basePath%>/plug-in/mui/js/mui.min.js"></script>
<script src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript">

	var pcid='<%=request.getParameter("id")%>';

	var elemlength=$(".map").length;
	var InvitationDataAry=[]; //耗材数组
	
	function subm(){
		if(InvitationDataAry.length!=$("li").length){
			alert("异常！请重新添加耗材");
			return ;
		}
		if(InvitationDataAry.length==0||$("li").length==0){
			alert("无需要添加的耗材！");
			return ;
		}
		if(!pcid){
			alert("异常访问本网页！");
			return ;
		}
        $.ajax({ 
            type:"POST", 
            url:"<%=basePath%>projectCardInvitationController.do?addProjectInvitation&pcid="+pcid, 
            dataType:"json",      
            contentType:"application/json",               
            data:JSON.stringify(InvitationDataAry), 
            success:function(data){ 
                  if(data.success){
                	  window.parent.addinCallback();                  	  
                  }else{
                	  alert("数据异常！请重新提交！")
                  }                
            } 
         }); 
   }
	//无需耗材
	function nocansubm(){
		var btnArray = ['确定','取消'];
		mui.confirm("确定无需耗材吗", "请谨慎！", btnArray, function(e) {
			if (e.index == 0) {
				$.ajax({ 
		            type:"POST", 
		            url:"<%=basePath%>projectCardInvitationController.do?nocansubm&pcid="+pcid, 
		            success:function(data){ 
		            	data=eval("("+data+")");
		                  if(data.success){
		                	  window.parent.addinCallback();                  	  
		                  }else{
		                	  alert("数据异常！请重新提交！")
		                  }                
		            },
		            error:function(e){
		            	  alert("数据异常！请重新提交！")
		            }
		         }); 
			}
		});
	}
	
	$(function() {
	   	loadSelectData(0,1,null,true);
	});
	
	//耗材数据移除
	function delvalueTogrid(elem){
			var li=$(elem).parent().parent().remove();	
			var value=li.attr("value");
			for(var i=0;i<InvitationDataAry.length;i++){
				if(value==InvitationDataAry[i].id){
					InvitationDataAry.splice(i,1);
					break;
				}
			}
	}
	//初始化已有耗材
	function initView(){
		//获取数据
		$.ajax({ 
            type:"GET", 
            url:"<%=basePath%>projectCardInvitationController.do?getExitsInvitation&pcid="+pcid, 
            dataType:"json",      
            contentType:"application/json",               
            success:function(data){ 
            	if(data&&data.resultList){
                	for(var i=0;i<data.resultList.length;i++){
                		//描绘界面
                      	setinToview(data.resultList[i].id,data.resultList[i].name,data.resultList[i].price,data.resultList[i].descs);	                			
                	}
                  }              
            } 
         }); 
		
		
	}
	//添加耗材 
	function selectvalueToaddgrid(tab){
		var parentid =$(".map:eq(0)").val();
		var text;
		var value;
		if(parentid=="cur"){
			 text=$("[name=detail]").val();
			 value="0";
		}else{
			 text=$(".map:last").find("option:selected").text();
			 value=$(".map:last").find("option:selected").val()+"";
		}
			var price=$("[name=iprice]").val();
			var specifications=$("[name=specifications]").val();
		if(!text||value==null||!price||!specifications){
			alert("请完善耗材信息");
			return false;
		}
		var desc;
		//新耗材
		for(var i=0;i<result.length;i++){
			if(value==result[i].id){
				desc=result[i].priceDesc;
				break;
			}
		}
		setinToview(value,text,price,desc,specifications);
	}
	//界面添加耗材
	function setinToview(value,text,price,desc,specifications){
		if(!isexitsGrid(value)){//耗材列表不存在
			if(desc){
			$("#datagrid").append('<li value="'+value+'" index="'+InvitationDataAry.length+'" class="mui-table-view-cell mui-media"><div class="mui-media-body"><span>'+text+'</span><button style="float: right" class="mui-btn-danger" onclick="delvalueTogrid(this)">删除</button><p class="mui-ellipsis"><strong>收费标准：</strong>'+desc+'&nbsp;&nbsp;&nbsp;<strong>现收费：</strong>'+price+'元</p></div></li>');
			}else{
			$("#datagrid").append('<li value="'+value+'" index="'+InvitationDataAry.length+'" class="mui-table-view-cell mui-media"><div class="mui-media-body"><span>'+text+'</span><button style="float: right" class="mui-btn-danger" onclick="delvalueTogrid(this)">删除</button><p class="mui-ellipsis"><strong>收费标准：</strong>无&nbsp;&nbsp;&nbsp;<strong>现收费：</strong>'+price+'元</p></div></li>');
			}
			//放入耗材数组
			var data={name:text,id:value,price:price,descs:desc,specifications:specifications};
			InvitationDataAry.push(data);
			//重置
			$("[name=iprice]").val("");
		}
	}
	
	//加载下一个下拉框数据
	function getNextselect(elem){
		var parentid =$(elem).val();
		if(!parentid)return;
		if(parentid=="cur"){
			$("#detail").hide();
			$("#curdetail").show();
		}else{
			$("#detail").show();
			$("#curdetail").hide();
			loadSelectData(1,parentid,null);
			var index=parseInt($(".map").index($(elem)))+1
		}
	}
	var result;
	
	//加载下拉框数据
    function loadSelectData(index,parentid,after,first){
        $(".shanrd-box").show();
        $select=$(".map").eq(index);
        $select.find("option").remove(); 
        if(!$select)return;
        $.getJSON('<%=basePath%>projectInvitationController.do?getInvitationbyParentid&parentsid='+parentid,"",function(data){
        	result=data.resultList;
        	if(first)initView(); //初始化已有耗材
        	$select.append('<option value="">请选择...</option>');
        	if(index==0)$select.append('<option value="cur">自定义耗材...</option>');
        	for(var i=0;i<data.resultList.length;i++){
              $select.append("<option value='"+data.resultList[i].id+"'>"+data.resultList[i].iname+"</option>");
            }
           if(after&&typeof after ==='function')after();
           setTimeout(function(){
               $(".shanrd-box").hide();
           },200);
        });
    }
    
    //是否存在在列表
    function isexitsGrid(value){
    	var flag=false;
    	$("#datagrid>li").each(function(i){
			var exit=$(this).attr("value");
			if(exit==value){
				flag=true;
			}
		});
    	return flag;
    }
	//切换tab页
    function switchtab(e){
    	$(".mui-control-item").removeClass("mui-active");
    	$(e).addClass("mui-active");
    	$("#item1").hide();
    	$("#item2").hide();
    	$($(e).attr("href")).show();
    }
</script>

</html>