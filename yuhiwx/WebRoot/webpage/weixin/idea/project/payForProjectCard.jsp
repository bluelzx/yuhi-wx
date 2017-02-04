<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html style="height: 100%;">
<head>
    <meta charset="utf-8">
    <title>支付页面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="<%=basePath%>/plug-in/mui/css/mui.min.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
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

<div class="mui-content" style="height: 100%;">

	
    <div class="mui-content-padded" style="height: 100%;margin-top: 10px">
  
	    <div style="height:58%;overflow: auto;">
	    		<ul class="mui-table-view" id="datagrid">
	    		</ul>
	    </div>
    	<form class="mui-input-group">
            <div class="mui-input-row">
                <label>数量&nbsp;:&nbsp;</label>
               <input  disabled="disabled" style="margin-top: 14px; padding-right: 2%;"  id="count" class="input"  />
            </div>
            <div class="mui-input-row">
             	<label>价格&nbsp;:&nbsp;</label>
                <input id="price" style="margin-top: 14px; padding-right: 2%;" disabled="disabled" class="input" />
            </div>
        </form>
     
        
        <div class="title">&nbsp;</div> 
        <button type="button" onclick="submit()" style="padding: 10px;" class="mui-btn mui-btn-primary">确定支付</button>
        <div class="title" style="height: 10px">&nbsp;</div>
        <input  id="json"></input>
       </div>
</div>
</body>
<script type="text/javascript">
	var pcid='<%=request.getParameter("pcid")%>';
	var openid='<%=request.getParameter("openid")%>';
	var InvitationDataAry=[]; //耗材数组
	var count=0;
	var price=0;
	
	$(function() {
		initView();
	});
	
	//初始化已有耗材
	function initView(){
		 $(".shanrd-box").show();
		//获取数据
		$.ajax({ 
            type:"GET", 
            url:"<%=basePath%>projectCardInvitationController.do?getExitsInvitation&pcid="+pcid, 
            dataType:"json",      
            contentType:"application/json",               
            success:function(data){ 
            	if(data&&data.resultList){
            		count=data.count;
                	for(var i=0;i<data.resultList.length;i++){
                		//描绘界面
                  		setinToview(data.resultList[i].id,data.resultList[i].name,data.resultList[i].price,data.resultList[i].descs);
                  		price+=parseInt(data.resultList[i].price);
                	}
                	$("#count").val("一共花费"+count+"件耗材");
                	$("#price").val(""+price+"元");
                }           
            	setTimeout(function(){
                    $(".shanrd-box").hide();
                },200);
            } 
         });
	
	}
	
	//界面添加耗材
	function setinToview(value,text,price,desc){
			$("#datagrid").append('<li value="'+value+'" index="'+InvitationDataAry.length+'" class="mui-table-view-cell mui-media"><div class="mui-media-body"><span>'+text+'</span><p class="mui-ellipsis"><strong>收费标准：</strong>'+desc+'</p><p><strong>现收费：</strong>'+price+'元</p></div></li>');
	}
	
	
	 function submit(){
         try{
      	   $.ajax({
                 type: 'POST',
                 url: '<%=basePath%>wechatPay/jsOarder.do',
                 data: {'detail':'耗材数量:'+count+'件,'+'耗材总价格:'+price+'元',
                	    'desc':'工程单'+pcid+'号耗材支付',
                	    'goodSn':pcid,
                	    'orderSn':pcid,
                	    'amount':price,
                	    'openId':openid},
                 success: function(data){
                 console.log(data.obj);
                 data = eval("(" + data + ")");
                 var appId=data.obj.appId;
                 var timeStamp=data.obj.timeStamp;
                 var nonceStr=data.obj.nonceStr;
                 var package=data.obj.package;
                 var paySign=data.obj.paySign;
             	if(!WeixinJSBridge){
                  	 alert("未知异常");
                  	 return false;
                   }
                 //$("#json").val(JSON.stringify(WeixinJSBridge));
                 WeixinJSBridge.invoke(
                         'getBrandWCPayRequest', {
                             "appId":appId,     //公众号名称，由商户传入
                             "timeStamp":timeStamp,         //时间戳，自1970年以来的秒数
                             "nonceStr":nonceStr, //随机串
                             "package":package,
                             "signType":"MD5",         //微信签名方式：
                             "paySign":paySign //微信签名
                         },
                         function(res){
                        	 alert();
                             WeixinJSBridge.log(res.err_msg);
                             if(res.err_msg == "get_brand_wcpay_request:ok"){
                                 //支付成功调用
                             //history.go(0);  
                                 alert("成功");
                                 changeProjectCardPayState();
                             }else if(res.err_msg == "get_brand_wcpay_request:cancel"){
                                 //取消支付调用
                                 alert("取消");

                             }else{
                            	 $("#json").val(JSON.stringify(res));
                                 //支付失败
                                 alert("失败");

                             }
                         }
                 );
             } ,
                 dataType: "json"});
         }catch(e){
      	   alert();
         }
      }
		//修改工单支付状态
		function changeProjectCardPayState(){
			//获取数据
			$.ajax({ 
	            type:"GET", 
	            url:"<%=basePath%>projectCardController.do?confirmpay&pcid="+pcid, 
	            dataType:"json",      
	            contentType:"application/json",               
	            success:function(data){ 
	            	if(data&&data.resultList){
	            		alert();
	            		window.parent.addpayCallback();
	                }           
	            } 
	         }); 
		}
</script>

</html>