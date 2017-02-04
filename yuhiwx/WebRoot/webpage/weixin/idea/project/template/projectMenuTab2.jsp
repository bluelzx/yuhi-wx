<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>我的工单(业主)</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=basePath%>/plug-in/mui/css/mui.min.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/plug-in/mui/css/feedback-min.css" />
		<style>
body,html{background-color:#efeff4}.mui-page,.mui-page-content,.mui-pages,.mui-view,.mui-views{position:absolute;left:0;right:0;top:0;bottom:0;width:100%;height:100%;background-color:#efeff4}.mui-pages{top:46px;height:auto}.mui-scroll,.mui-scroll-wrapper{background-color:#efeff4}.mui-page.mui-transitioning{-webkit-transition:-webkit-transform .3s ease;transition:transform .3s ease}.mui-page-left{-webkit-transform:translate3d(0,0,0);transform:translate3d(0,0,0)}.mui-ios .mui-page-left{-webkit-transform:translate3d(-20%,0,0);transform:translate3d(-20%,0,0)}.mui-navbar{position:fixed;right:0;left:0;z-index:10;height:44px;background-color:#f7f7f8}.mui-navbar .mui-bar{position:absolute;background:0;text-align:center}.mui-android .mui-navbar-inner.mui-navbar-left{opacity:0}.mui-ios .mui-navbar-left .mui-center,.mui-ios .mui-navbar-left .mui-left,.mui-ios .mui-navbar-left .mui-right{opacity:0}.mui-navbar .mui-btn-nav{-webkit-transition:none;transition:none;-webkit-transition-duration:0s;transition-duration:0s}.mui-navbar .mui-bar .mui-title{display:inline-block;width:auto}.mui-page{display:none}.mui-pages .mui-page{display:block}.mui-page .mui-table-view:first-child{margin-top:15px}.mui-page .mui-table-view:last-child{margin-bottom:30px}.mui-table-view{margin-top:20px}.mui-table-view span.mui-pull-right{color:#999}.head{height:40px}#head{line-height:40px}.head-img{width:40px;height:40px}#head-img1{position:absolute;bottom:10px;right:40px;width:40px;height:40px}.update{font-style:normal;color:#999;margin-right:-25px;font-size:15px}.mui-fullscreen{position:fixed;z-index:20;background-color:#000}.mui-ios .mui-navbar .mui-bar .mui-title{position:static}#feedback .mui-popover{position:fixed}#feedback .mui-table-view:last-child{margin-bottom:0}#feedback .mui-table-view:first-child{margin-top:0}.mui-control-content{background-color:#efeff4;min-height:50%;overflow:scoll}.mui-control-content .mui-loading{margin-top:50px}#shanrd-box{position:fixed;width:100%;height:100%;left:0;top:0;background-color:rgba(0,0,0,0.3);display:-webkit-box;-webkit-box-pack:center;-webkit-box-align:center;z-index:99999}.dialogbox{padding:1em;border-radius:10px;background-color:rgba(0,0,0,0.8)}.load{color:white;text-indent:2em;display:block;background-color:white;line-height:1.3em;background:url("<%=basePath%>/userfiles/images/loading-2.gif") no-repeat;background-position:0 2;background-size:20px}
		</style>
	</head>
	<body class="mui-fullscreen">
		<!--加载动画-->
		<div id="shanrd-box">
		    <div class="dialogbox">
		        <div class="load">正在处理中...</div>
		    </div>
		</div>
		<!--页面主结构开始-->
		<div id="app" class="mui-views">
			<div class="mui-view">
				<div class="mui-navbar">
				</div>
				<div class="mui-pages">
				</div>
			</div>
		</div>
		<!--页面主结构结束-->
		<!--单页面开始-->
		<div id="setting" class="mui-page">
			<!--页面标题栏开始-->
			<div class="mui-navbar-inner mui-bar mui-bar-nav">
				<h1 class="mui-center mui-title">我的工单</h1>
			</div>
			<!--页面标题栏结束-->
			<!--页面主内容区开始-->
			<div class="mui-page-content">
				<div class="mui-content" style="height: 100%;">
					<div id="slider" class="mui-slider" style="height: 100%;">
						<div id="sliderSegmentedControl" class="mui-slider-indicator mui-segmented-control mui-segmented-control-inverted">
							<a class="mui-control-item" href="#item1mobile">
								未分配&nbsp;<span id="pcindex1" class=" mui-badge mui-badge-primary">0</span>
							</a>
							<%--<a class="mui-control-item" href="#item2mobile">
								待接单&nbsp;<span id="pcindex2" class=" mui-badge mui-badge-primary">0</span>
							</a>
							--%><a class="mui-control-item" href="#item2mobile">
								待办理&nbsp;<span id="pcindex2" class=" mui-badge mui-badge-success">0</span>
							</a>
							<a class="mui-control-item" href="#item3mobile">
								办理中&nbsp;<span id="pcindex3" class=" mui-badge mui-badge-danger">0</span>
							</a>
							<%--<a class="mui-control-item" href="#item5mobile">
								待评价&nbsp;<span id="pcindex5" class=" mui-badge mui-badge-primary">0</span>
							</a>
							--%><a class="mui-control-item" href="#item4mobile">
								办理完&nbsp;<span id="pcindex4" class=" mui-badge mui-badge-purple">0</span>
							</a>
						</div>
						<div id="sliderProgressBar" class="mui-slider-progress-bar mui-col-xs-3"></div>
						<div class="mui-slider-group" style="top:0;position: static;height: 100%" >
							<div id="item1mobile" class="mui-slider-item mui-control-content mui-active" style="height: 90%">
								<div id="scroll1"  class="mui-scroll-wrapper">
									<div class="mui-scroll">
										<div class="mui-loading" style="text-align: center;">
											<div class="mui-spinner">
											</div>
											正在努力加载中...
										</div>
									</div>
								</div>
							</div>
							<div id="item2mobile" class="mui-slider-item mui-control-content" style="height: 90%">
								<div id="scroll3" class="mui-scroll-wrapper">
									<div class="mui-scroll">
										<div class="mui-loading" style="text-align: center;">
											<div class="mui-spinner">
											</div>
											正在努力加载中...
										</div>
									</div>
								</div>
		
							</div>
							<div id="item3mobile" class="mui-slider-item mui-control-content" style="height: 90%">
								<div id="scroll4" class="mui-scroll-wrapper">
									<div class="mui-scroll">
										<div class="mui-loading" style="text-align: center;">
											<div class="mui-spinner">
											</div>
											正在努力加载中...
										</div>
									</div>
								</div>
							</div>
							<div id="item4mobile" class="mui-slider-item mui-control-content" style="height: 90%">
								<div id="scroll6" class="mui-scroll-wrapper">
									<div class="mui-scroll">
										<div class="mui-loading" style="text-align: center;">
											<div class="mui-spinner">
											</div>
											正在努力加载中...
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--页面主内容区结束-->
		</div>
		<!--单页面结束-->
		<!--详情开始-->
		<div id="account" class="mui-page">
			<div class="mui-navbar-inner mui-bar mui-bar-nav">
				<button type="button" class="mui-left mui-action-back mui-btn  mui-btn-link mui-btn-nav mui-pull-left">
					<span class="mui-icon mui-icon-left-nav"></span>我的工单
				</button>
				<h1 class="mui-center mui-title" id="title">工单详细</h1>
			</div>
			<div class="mui-page-content" id="content">
				<iframe id="detail"  frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes" style="width: 100%;height: 100%;" src=""></iframe>
			</div>
		</div>
		<!--详情开始-->
		<div id="paybox" class="mui-page">
			<div class="mui-navbar-inner mui-bar mui-bar-nav">
				<button type="button" class="mui-left mui-action-back mui-btn  mui-btn-link mui-btn-nav mui-pull-left">
					<span class="mui-icon mui-icon-left-nav"></span>我的工单
				</button>
				<h1 class="mui-center mui-title">支付详情</h1>
			</div>
			<div class="mui-page-content" id="paycontent">
			</div>
		</div>
		<!--详情结束-->
		 <script id="template" type="text/html">
						{{# if(d&&d.resultList&&d.resultList.length>0){ }}
							{{# for(var i = 0, len = d.resultList.length; i < len; i++){ }}
										<div class="mui-card">
											<div class="mui-card-header">
											<p style="margin-bottom:0px;width: 100%;color: black;">工单号: {{d.resultList[i].id}}<br/>
												{{# if(d.type==3){ }}<span style="float:left;color: #8A8A8A;">接单时间: {{d.resultList[i].get_time}}</span>
												{{# }else if(d.type==0){ }}<span style="float:left;color: #8A8A8A;">创建时间:{{d.resultList[i].create_date}}</span>																				
												{{# }else{ }}<span style="float:left;color: #8A8A8A;">创建时间:{{d.resultList[i].create_time}}</span>
												{{# } }}
											</p>
											</div>
											<div class="mui-card-content">
												<div class="mui-card-content-inner">
													{{d.resultList[i].content}}
												</div>
											</div>
											<div class="mui-card-footer">
									{{# if(d.type==0){ }}
										<button onclick="canelPC('{{d.resultList[i].id}}')" type="button" class="mui-btn mui-btn-primary">
											取消工单
										</button>
									{{# } }}			
									{{# if(d.resultList[i].state==1){ }}
										{{# if(d.resultList[i].reminderstate==1){ }}
										<button  disabled="disabled" style="background-color: #969595;border:1px solid #6B6C6D;" type="button" class="mui-btn mui-btn-primary">
											已经催单
										</button>
										{{# }else{ }}
										<button onclick="PCReminder('{{d.resultList[i].id}}',this,'{{d.type}}')" type="button" class="mui-btn mui-btn-primary">
											立即催单
										</button>
										{{# } }}
									{{# } }}
									{{# if(d.resultList[i].state==2){ }}

										{{# if(d.resultList[i].reminderstate==1){ }}
										<button  disabled="disabled" style="background-color: #969595;border:1px solid #6B6C6D;" type="button" class="mui-btn mui-btn-primary">
											已经催单
										</button>
										{{# }else{ }}
										<button onclick="PCReminder('{{d.resultList[i].id}}',this,'{{d.type}}')" type="button" class="mui-btn mui-btn-primary">
											立即催单
										</button>
										{{# } }}
										
									{{# } }}
									{{# if(d.resultList[i].state==4){ }}
										<button onclick="evaluateProjectCard('{{d.resultList[i].id}}')" type="button" class="mui-btn mui-btn-primary">
											立即评价
										</button>
										{{# if(d.resultList[i].isPay==1||d.resultList[i].isPay==2){ }}
										<button disabled="disabled"  style="background-color: #969595;border:1px solid #6B6C6D;" type="button" class="mui-btn mui-btn-primary">
											已经支付
										</button>
										{{# }else{ }}
										<button onclick="pay('{{d.resultList[i].id}}')" type="button" class="mui-btn mui-btn-primary">
											立即支付
										</button>
										{{# } }}
									{{# } }}
									{{# if(d.resultList[i].state==5){ }}
										
										{{# if(d.resultList[i].propertyobjectionfid){ }}
										<button onclick="showPropertyobjection('{{d.resultList[i].id}}')" type="button" class="mui-btn mui-btn-primary">
											查看回访
										</button>
										{{# }else{ }}
										<button  disabled="disabled" style="background-color: #969595;border:1px solid #6B6C6D;" type="button" class="mui-btn mui-btn-primary">
											等待回访
										</button>	
										{{# } }}

									{{# } }}
										{{# if(d.type==0){ }}
										<a  class="mui-btn mui-btn-royal"  onclick="showProjectCreateDetail('{{d.resultList[i].id}}');">
											查看详细
										</a>
										{{# }else{ }}
										<a  class="mui-btn mui-btn-royal"  onclick="showProjectCardDetail('{{d.resultList[i].id}}');">
											查看详细
										</a>
										{{# } }}
										
											</div>
										</div>

									{{# } }}
							{{# }else{ }}
									<div class="mui-card" >
											<div class="mui-card-header" style="font-size:16px;text-align:center;display:block;">暂无该状态下的工单</div>
									</div>
							{{# } }}
					<div class="mui-card" >
						{{# if(d.type==0){ }}
						<button onclick="AsyncloadDataForType('<%=basePath%>projectCreateController.do?getProjectCreateList&ownerid='+owner);" type="button" style="width:100%" class="mui-btn mui-btn-royal">
											重新获取数据
						</button>
						{{# }else{ }}
						<button onclick="loadDataForType('{{d.type}}')" type="button" style="width:100%" class="mui-btn mui-btn-royal">
											重新获取数据
						</button>
						{{# } }}
					</div>
		</script>
		
		<script src="<%=basePath%>/plug-in/mui/js/mui.min.js"></script>
		<script src="<%=basePath%>/plug-in/laytpl/laytpl.js"></script>
		<script src="<%=basePath%>/plug-in/mui/js/mui.view.js "></script>
		<%--生产环境需注释 --%>
		<%@include file="dev/ownerMyproject.jsp" %>
		<!-- 
		<script type="text/javascript">
		var loadbox=document.getElementById("shanrd-box");var owner='${ownerid}';var openid='${openid}';var pccountmap=eval("("+'${pccountmap}'+")");var gettpl=document.getElementById('template').innerHTML;var type=0;mui.init();var viewApi=mui('#app').view({defaultPage:'#setting'});mui('.mui-scroll-wrapper').scroll();var view=viewApi.view;(function($){loadbox.style.display="none";initPcIndex();var oldBack=$.back;$.back=function(){if(viewApi.canBack()){viewApi.back();}else{oldBack();}};var item2=document.getElementById('item2mobile');document.getElementById('slider').addEventListener('slide',function(e){type=e.detail.slideNumber;var elem=document.getElementById('item'+(type+1)+'mobile');var isshow=elem.attributes["isshow"];if(!isshow)loadDataForType(type);});AsyncloadDataForType('<%=basePath%>projectCreateController.do?getProjectCreateList&ownerid='+owner);})(mui);function PCReminder(pcid,e){loadbox.style.display="-webkit-box";mui.ajax('<%=basePath%>projectCardController.do?PCReminder&ownerid='+owner+"&id="+pcid,{type:'GET',timeout:10000,success:function(data){loadbox.style.display="none";data=eval("("+data+")");if(data&&data.success){e.disabled=true;e.innerHTML="已催单";e.style.cssText="background-color: #999;border:1px solid #6B6C6D;";mui.toast('催单成功');}},error:function(xhr,type,errorThrown){loadbox.style.display="none";mui.toast('访问异常');console.log(type);}});}function canelPC(pcid){var btnArray=['确定','取消'];mui.confirm('确定此操作？','请谨慎！',btnArray,function(e){if(e.index==0){loadbox.style.display="-webkit-box";mui.ajax('<%=basePath%>projectCreateController.do?doDel&id='+pcid,{type:'GET',timeout:10000,success:function(data){loadbox.style.display="none";data=eval("("+data+")");if(data&&data.success){mui.toast('取消工单成功');AsyncloadDataForType('<%=basePath%>projectCreateController.do?getProjectCreateList&ownerid='+owner);}},error:function(xhr,type,errorThrown){loadbox.style.display="none";mui.toast('访问异常');console.log(type);}});}});}function showProjectCreateDetail(projectCardid){routeUrl('<%=basePath%>projectCreateController.do?goProjectcardByid&projectCardid='+projectCardid,"工单详细");}function showProjectCardDetail(projectCardid){routeUrl('<%=basePath%>projectCardController.do?goProjectcardByid&projectCardid='+projectCardid,"工单详细");}function loadDataForType(type){if(type=="1")type="1,2";if(type=="2")type="3,";if(type=="3")type="4,5,6";AsyncloadDataForType('<%=basePath%>projectCardController.do?getMyProjectListData&ownerid='+owner+'&type='+type);}function AsyncloadDataForType(url){mui.ajax(url,{type:'GET',timeout:10000,success:function(data){var elem=document.getElementById('item'+(type+1)+'mobile');elem.setAttribute("isshow","isshow");elem.querySelector('.mui-scroll').innerHTML=getcontent(data);},error:function(xhr,type,errorThrown){mui.toast('访问异常');console.log(type);}});}function evaluateProjectCard(id,menuindex){var btnArray=['提交','取消'];mui.confirm('<textarea id="evaluatecontent" row="7" class="mui-input-clear question" style="margin-bottom:0;"  placeholder="填写对服务的评价"></textarea>','请你填写对该服务的评价！',btnArray,function(e){if(e.index==0){loadbox.style.display="-webkit-box";var data=document.getElementById("evaluatecontent").value;if(!data&&data.length<1){data.placeholder="请填写评价！";}mui.ajax('<%=basePath%>projectCardController.do?updateProjectState',{data:{'openid':openid,type:"10082",id:id,evaluatecontent:data},type:'POST',timeout:10000,success:function(data){loadbox.style.display="none";if(data&&data.indexOf("success")!=-1){mui.toast("操作成功！");loadDataForType(menuindex);}else{mui.toast("后台异常！请联系管理员");}},error:function(xhr,type,errorThrown){loadbox.style.display="none";mui.toast('访问异常');console.log(types);}});}});}function getOrsetProjectCard(types,id,menuindex){var btnArray=['确定','取消'];mui.confirm('确定此操作？','请谨慎！',btnArray,function(e){if(e.index==0){loadbox.style.display="-webkit-box";mui.ajax('<%=basePath%>projectCardController.do?updateProjectState',{data:{'openid':openid,type:types,id:id},type:'POST',timeout:10000,success:function(data){loadbox.style.display="none";if(data&&data.indexOf("success")!=-1){mui.toast("操作成功！");loadDataForType(menuindex);}else{mui.toast("后台异常！请联系管理员");loadDataForType(menuindex);}},error:function(xhr,type,errorThrown){loadbox.style.display="none";mui.toast('访问异常');console.log(types);}});}});}function getcontent(data){var content="";try{data=eval("("+data+")");for(var i=0;i<data.resultList.length;i++){data.resultList[i].createTime=formatDate(data.resultList[i].createTime);var time=formatDate(data.resultList[i].getTime);data.resultList[i].getTime=time?time:"暂未接单";}data.type=type;}catch(e){}var elem=document.getElementById("pcindex"+(type+1));elem.innerHTML=data.resultList.length;laytpl(gettpl).render(data,function(html){content=html;});return content;}function formatDate(strTime){if(!strTime)return null;var date=new Date(parseInt(strTime));return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+"";}function routeUrl(url,title){document.getElementById("title").innerHTML=title;viewApi.go("#account");var detail=document.getElementById('detail');detail.setAttribute("src",url);}function initPcIndex(){for(var i=0;i<pccountmap.length;i++){var count=0;var elem=null;switch(parseInt(pccountmap[i].state)){case 0:break;case 1:case 2:elem=document.getElementById("pcindex2");count=parseInt(elem.innerHTML)+pccountmap[i].count;break;case 3:elem=document.getElementById("pcindex3");count=parseInt(elem.innerHTML)+pccountmap[i].count;break;case 4:case 5:case 6:elem=document.getElementById("pcindex4");count=parseInt(elem.innerHTML)+pccountmap[i].count;break;}elem.innerHTML=count;}}var InvitationDataAry=[];var count=0;var price=0;var pcid;function pay(projectCardid){document.getElementById("title").innerHTML="支付详情";viewApi.go("#account");mui.ajax('<%=basePath%>webpage/weixin/idea/project/template/payForProjectCard.jsp',{type:'GET',timeout:10000,success:function(data){var detail=document.getElementById('content');detail.innerHTML=data;pcid=projectCardid;initView();},error:function(xhr,type,errorThrown){mui.toast('访问异常');console.log(type);}});}function initView(){mui.ajax({type:"GET",url:'<%=basePath%>projectCardInvitationController.do?getExitsInvitation&pcid='+pcid,dataType:'json',contentType:'application/json',success:function(data){if(data&&data.resultList){count=data.count;for(var i=0;i<data.resultList.length;i++){setinToview(data.resultList[i].id,data.resultList[i].name,data.resultList[i].price,data.resultList[i].descs);price+=parseInt(data.resultList[i].price);}document.getElementById("count").value="一共花费"+count+"件耗材";document.getElementById("price").value=""+price+"元";}}});}function setinToview(value,text,price,desc){document.getElementById("datagrid").innerHTML+='<li value="'+value+'" index="'+InvitationDataAry.length+'" class="mui-table-view-cell mui-media"><div class="mui-media-body"><span>'+text+'</span><p class="mui-ellipsis"><strong>收费标准：</strong>'+desc+'</p><p><strong>现收费：</strong>'+price+'元</p></div></li>';}function submit(){try{mui.ajax({type:'POST',url:'<%=basePath%>wechatPay/jsOarder.do',data:{'detail':'耗材数量:'+count+'件,'+'耗材总价格:'+price+'元','desc':'工程单'+pcid+'号耗材支付','goodSn':pcid,'orderSn':pcid,'amount':'0.0'+price,'openId':openid},success:function(data){console.log(data.obj);data=eval("("+data+")");var appId=data.obj.appId;var timeStamp=data.obj.timeStamp;var nonceStr=data.obj.nonceStr;var package=data.obj.package;var paySign=data.obj.paySign;if(!WeixinJSBridge){alert("未知异常");return false;}WeixinJSBridge.invoke('getBrandWCPayRequest',{"appId":appId,"timeStamp":timeStamp,"nonceStr":nonceStr,"package":package,"signType":"MD5","paySign":paySign},function(res){if(res.err_msg=="get_brand_wcpay_request:ok"){changeProjectCardPayState();}else if(res.err_msg=="get_brand_wcpay_request:cancel"){}else{alert("支付失败，请稍后重新支付！");}});},dataType:"json"});}catch(e){console.log(e);}}function changeProjectCardPayState(){mui.ajax({type:"GET",url:'<%=basePath%>projectCardController.do?confirmpay&id='+pcid+'&paytype=1',dataType:'json',contentType:'application/json',success:function(data){if(data&&data.success){viewApi.back();loadDataForType(4);mui.toast('支付成功！');}}});}
		</script>
		 -->
	</body>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
</html>