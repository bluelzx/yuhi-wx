<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>我的工单(工程)</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=basePath%>/plug-in/mui/css/mui.min.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/plug-in/mui/css/feedback-min.css" />
		<style>
	.mui-plus.mui-android header.mui-bar{display:none}.mui-plus.mui-android .mui-bar-nav~.mui-content{padding:0}.hm-description{margin:.5em 0}.hm-description>li{font-size:14px;color:#8f8f94}html,body{background-color:#efeff4}.mui-views,.mui-view,.mui-pages,.mui-page,.mui-page-content{position:absolute;left:0;right:0;top:0;bottom:0;width:100%;height:100%;background-color:#efeff4}.mui-pages{top:46px;height:auto}.mui-scroll-wrapper,.mui-scroll{background-color:#efeff4}.mui-page.mui-transitioning{-webkit-transition:-webkit-transform 300ms ease;transition:transform 300ms ease}.mui-page-left{-webkit-transform:translate3d(0,0,0);transform:translate3d(0,0,0)}.mui-ios .mui-page-left{-webkit-transform:translate3d(-20%,0,0);transform:translate3d(-20%,0,0)}.mui-navbar{position:fixed;right:0;left:0;z-index:10;height:44px;background-color:#f7f7f8}.mui-navbar .mui-bar{position:absolute;background:transparent;text-align:center}.mui-android .mui-navbar-inner.mui-navbar-left{opacity:0}.mui-ios .mui-navbar-left .mui-left,.mui-ios .mui-navbar-left .mui-center,.mui-ios .mui-navbar-left .mui-right{opacity:0}.mui-navbar .mui-btn-nav{-webkit-transition:none;transition:none;-webkit-transition-duration:.0s;transition-duration:.0s}.mui-navbar .mui-bar .mui-title{display:inline-block;width:auto}.mui-page{display:none}.mui-pages .mui-page{display:block}.mui-page .mui-table-view:first-child{margin-top:15px}.mui-page .mui-table-view:last-child{margin-bottom:30px}.mui-table-view{margin-top:20px}.mui-table-view span.mui-pull-right{color:#999}.head{height:40px}#head{line-height:40px}.head-img{width:40px;height:40px}#head-img1{position:absolute;bottom:10px;right:40px;width:40px;height:40px}.update{font-style:normal;color:#999;margin-right:-25px;font-size:15px}.mui-fullscreen{position:fixed;z-index:20;background-color:#000}.mui-ios .mui-navbar .mui-bar .mui-title{position:static}#feedback .mui-popover{position:fixed}#feedback .mui-table-view:last-child{margin-bottom:0}#feedback .mui-table-view:first-child{margin-top:0}.mui-control-content{background-color:#efeff4;min-height:50%;overflow:scoll}.mui-control-content .mui-loading{margin-top:50px}#shanrd-box{position:fixed;width:100%;height:100%;left:0;top:0;background-color:rgba(0,0,0,0.3);display:-webkit-box;-webkit-box-pack:center;-webkit-box-align:center;z-index:99999}.dialogbox{padding:1em;border-radius:10px;background-color:rgba(0,0,0,0.8)}.load{color:white;text-indent:2em;display:block;background-color:white;line-height:1.3em;background:url("<%=basePath%>/userfiles/images/loading-2.gif") no-repeat;background-position:0 2;background-size:20px}
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
			<div id="slider" class="mui-slider" style="    height: 100%;">
				<div id="sliderSegmentedControl" class="mui-slider-indicator mui-segmented-control mui-segmented-control-inverted">
					<a class="mui-control-item" href="#item1mobile">
						待接单&nbsp;<span id="pcindex1" class=" mui-badge mui-badge-primary">0</span>
					</a>
					<a class="mui-control-item" href="#item2mobile">
						待办理&nbsp;<span id="pcindex2" class="mui-badge mui-badge-success">0</span>
					</a>
					<a class="mui-control-item" href="#item3mobile">
						办理中&nbsp;<span id="pcindex3" class="mui-badge mui-badge-danger">0</span>
					</a>
					<a class="mui-control-item" href="#item4mobile">
						已完成&nbsp;<span  id="pcindex4" class="mui-badge mui-badge-purple">0</span>
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
						<div id="scroll2" class="mui-scroll-wrapper">
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
					<div id="item4mobile" class="mui-slider-item mui-control-content" style="height: 90%">
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
			<div class="mui-page-content">
				<iframe id="detail"  frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes" style="width: 100%;height: 100%;" src=""></iframe>
			</div>
		</div>
		<!--详情结束                            140  {{# if(d.type!=1){ }}-->
		<script id="template" type="text/html">
						{{# if(d&&d.resultList&&d.resultList.length>0){ }}
							{{# for(var i = 0, len = d.resultList.length; i < len; i++){ }}
										<div class="mui-card">
											<div class="mui-card-header">
											<p style="margin-bottom:0px;width: 100%;color: black;">工单号: {{d.resultList[i].id}}<br/>
												{{# if(1!=1){ }}<span style="float:left;color: #8A8A8A;">接单时间: {{d.resultList[i].handle_time}}</span>{{# } }}
												<span style="float:left;color: #8A8A8A;">创建时间:{{d.resultList[i].create_time}}</span>											
											</p>
											</div>
											<div class="mui-card-content">
												<div class="mui-card-content-inner">
													{{d.resultList[i].content}} 
													<span style="float:right">
													{{# if(d.resultList[i].pctype==0){ }}
														服务单													
													{{# }else{ }}
														公共单
													{{# } }}</span>
												</div>
											</div>
											<div class="mui-card-footer">
												
									{{# if(d.resultList[i].state==1){ }}
										<button onclick="getOrsetProjectCard(10078,'{{d.resultList[i].id}}','{{d.type}}')" type="button"  class="mui-btn mui-btn-primary">
											 接受工单
										</button>
										<button onclick="getOrsetProjectCard(10079,'{{d.resultList[i].id}}','{{d.type}}')" type="button" class="mui-btn mui-btn-danger">
											 拒绝工单
										</button>
									{{# } }}
									{{# if(d.resultList[i].state==2){ }}
										{{# if(d.resultList[i].invitationid==1){ }}
										<button onclick="setInvitation('{{d.resultList[i].id}}')" type="button" class="mui-btn mui-btn-primary">
											编辑耗材
										</button>
										{{# }else{  }}
										<button onclick="setInvitation('{{d.resultList[i].id}}')" type="button" class="mui-btn mui-btn-primary">
											添加耗材
										</button>
										{{# } }}
										<button onclick="getOrsetProjectCard(10081,'{{d.resultList[i].id}}','{{d.type}}','{{d.resultList[i].invitationid}}')" type="button" class="mui-btn mui-btn-danger">
											开始办理
										</button>
									{{# } }}
									{{# if(d.resultList[i].state==3){ }}
										<button onclick="getOrsetProjectCard(10080,'{{d.resultList[i].id}}','{{d.type}}')" type="button" class="mui-btn mui-btn-primary">
											办理结束
										</button>
									{{# } }}
									{{# if(d.resultList[i].state==4||d.resultList[i].state==5||d.resultList[i].state==6){ }}
										<button onclick="showRepir('{{d.resultList[i].ownerobjectionid}}')" type="button" class="mui-btn mui-btn-primary">
											查看评价
										</button>
									{{# } }}

										
										<a  class="mui-btn mui-btn-royal"  onclick="showProjectCardDetail('{{d.resultList[i].id}}');">
											 查看详细
										</a>
											</div>
										</div>

									{{# } }}
						{{# }else{ }}
									<div class="mui-card" >
											<div class="mui-card-header" style="font-size:16px;text-align:center;display:block;">暂无工单</div>
									</div>
							{{# } }}
					<div class="mui-card" >
						<button onclick="loadDataForType('{{d.type}}')" type="button" style="width:100%" class="mui-btn mui-btn-royal">
											重新获取数据
						</button>
					</div>
		</script>
	<%--	<script id="template" type="text/html">
{{#if(d&&d.resultList&&d.resultList.length>0){}}{{#for(var i=0,len=d.resultList.length;i<len;i++){}}<div class="mui-card"><div class="mui-card-header"><p style="margin-bottom:0px;width: 100%;color: black;">工单号:{{d.resultList[i].id}}<br/>{{#if(d.type!=1){}}<span style="float:left;color: #8A8A8A;">接单时间:{{d.resultList[i].getTime}}</span>{{#}}}<span style="float:right;color: #8A8A8A;">创建时间:{{d.resultList[i].createTime}}</span></p></div><div class="mui-card-content"><div class="mui-card-content-inner">{{d.resultList[i].content}}<span style="float:right">{{#if(d.resultList[i].pctype==0){}}服务单{{#}else{}}公共单{{#}}}</span></div></div><div class="mui-card-footer">{{#if(d.resultList[i].state==1){}}<button onclick="getOrsetProjectCard(10078,'{{d.resultList[i].id}}','{{d.type}}')"type="button"class="mui-btn mui-btn-primary">接受工单</button><button onclick="getOrsetProjectCard(10079,'{{d.resultList[i].id}}','{{d.type}}')"type="button"class="mui-btn mui-btn-danger">拒绝工单</button>{{#}}}{{#if(d.resultList[i].state==2){}}{{#if(d.resultList[i].invitationid==1){}}<button onclick="setInvitation('{{d.resultList[i].id}}')"type="button"class="mui-btn mui-btn-primary">编辑耗材</button>{{#}else{}}<button onclick="setInvitation('{{d.resultList[i].id}}')"type="button"class="mui-btn mui-btn-primary">添加耗材</button>{{#}}}<button onclick="getOrsetProjectCard(10081,'{{d.resultList[i].id}}','{{d.type}}','{{d.resultList[i].invitationid}}')"type="button"class="mui-btn mui-btn-danger">开始办理</button>{{#}}}{{#if(d.resultList[i].state==3){}}<button onclick="getOrsetProjectCard(10080,'{{d.resultList[i].id}}','{{d.type}}')"type="button"class="mui-btn mui-btn-primary">办理结束</button>{{#}}}{{#if(d.resultList[i].state==4||d.resultList[i].state==5||d.resultList[i].state==6){}}<button onclick="showRepir('{{d.resultList[i].propertyobjectionfid}}')"type="button"class="mui-btn mui-btn-primary">查看评价</button>{{#}}}<a class="mui-btn mui-btn-royal"onclick="showProjectCardDetail('{{d.resultList[i].id}}');">查看详细</a></div></div>{{#}}}{{#}else{}}<div class="mui-card"><div class="mui-card-header"style="font-size:16px;text-align:center;display:block;">暂无工单</div></div>{{#}}}<div class="mui-card"><button onclick="loadDataForType('{{d.type}}')"type="button"style="width:100%"class="mui-btn mui-btn-royal">重新获取数据</button></div>
		</script>--%>
		
		<script src="<%=basePath%>/plug-in/mui/js/mui.min.js"></script>
		<script src="<%=basePath%>/plug-in/laytpl/laytpl.js"></script>
		<script src="<%=basePath%>/plug-in/mui/js/mui.view.js "></script>
		<%--生产环境需注释 --%>
		<%@include file="dev/personMyproject.jsp" %>
		<%--
		<script type="text/javascript">
		var loadbox=document.getElementById("shanrd-box");var openid='${openid}';var pccountmap=eval("("+'${pccountmap}'+")");var gettpl=document.getElementById('template').innerHTML;var type=1;mui.init();var viewApi=mui('#app').view({defaultPage:'#setting'});mui('.mui-scroll-wrapper').scroll();var view=viewApi.view;(function($){loadbox.style.display="none";initPcIndex();var oldBack=$.back;$.back=function(){if(viewApi.canBack()){viewApi.back();}else{oldBack();}};var item2=document.getElementById('item2mobile');document.getElementById('slider').addEventListener('slide',function(e){type=e.detail.slideNumber+1;var elem=document.getElementById('item'+type+'mobile');var isshow=elem.attributes["isshow"];if(!isshow)loadDataForType(type);});loadDataForType(1);})(mui);function showRepir(id){if(!id||id.indexOf("null")!=-1){mui.toast('暂无业主评价！');return;}loadbox.style.display="-webkit-box";mui.ajax('<%=basePath%>projectCardObjectionController.do?getObjection&pcid='+id,{type:'GET',timeout:10000,success:function(data){loadbox.style.display="none";data=eval("("+data+")");if(data.success){mui.alert(data.obj.othercontent,'业主评价！');}},error:function(xhr,type,errorThrown){$(".shanrd-box").hide();mui.toast('访问异常');console.log(type);}});}function addinCallback(){loadDataForType(2);viewApi.back();mui.toast('添加成功！');}function setInvitation(id){routeUrl('<%=basePath%>projectInvitationController.do?addInvitationForProjectCard&id='+id,"添加耗材");}function showProjectCardDetail(projectCardid){routeUrl('<%=basePath%>projectCardController.do?goProjectcardByid&projectCardid='+projectCardid,"工单详细");}function loadDataForType(type){if(type=="4")type="4,5,6";AsyncloadDataForType('<%=basePath%>projectCardController.do?getppProjectListData&openid='+openid+'&type='+type);}function AsyncloadDataForType(url){mui.ajax(url,{type:'GET',timeout:10000,success:function(data){var elem=document.getElementById('item'+type+'mobile');elem.setAttribute("isshow","isshow");elem.querySelector('.mui-scroll').innerHTML=getcontent(data);},error:function(xhr,type,errorThrown){mui.toast('访问异常');console.log(type);}});}function getOrsetProjectCard(types,id,menuindex,inid){var btnArray=['确定','取消'];var mes="";if(types==10081&&inid==0)mes+='该工单还未添加耗材,';mes+='确定此操作？';mui.confirm(mes,'请谨慎！',btnArray,function(e){if(e.index==0){loadbox.style.display="-webkit-box";mui.ajax('<%=basePath%>projectCardController.do?updateProjectState',{data:{'openid':openid,type:types,id:id},type:'POST',timeout:10000,success:function(data){loadbox.style.display="none";if(data&&data.indexOf("success")!=-1){mui.toast("操作成功！");loadDataForType(menuindex);if(types==10078)resetMenu(1,2);if(types==10081)resetMenu(1,3);if(types==10080)resetMenu(1,4);}else{mui.toast("后台异常！请联系管理员");loadDataForType(menuindex);}},error:function(xhr,type,errorThrown){loadbox.style.display="none";mui.toast('访问异常');console.log(types);}});}});}function initPcIndex(){for(var i=0;i<pccountmap.length;i++){var count=0;var elem=null;switch(parseInt(pccountmap[i].state)){case 1:elem=document.getElementById("pcindex1");count=parseInt(elem.innerHTML)+pccountmap[i].count;break;case 2:elem=document.getElementById("pcindex2");count=parseInt(elem.innerHTML)+pccountmap[i].count;break;case 3:elem=document.getElementById("pcindex3");count=parseInt(elem.innerHTML)+pccountmap[i].count;break;case 4:case 5:case 6:elem=document.getElementById("pcindex4");count=parseInt(elem.innerHTML)+pccountmap[i].count;break;}elem.innerHTML=count;}}function getcontent(data){var content="";try{data=eval("("+data+")");for(var i=0;i<data.resultList.length;i++){data.resultList[i].createTime=formatDate(data.resultList[i].createTime);data.resultList[i].getTime=formatDate(data.resultList[i].getTime);}data.type=type;}catch(e){alert("数据获取异常,请重新获取！");}var elem=document.getElementById("pcindex"+type);elem.innerHTML=data.resultList.length;laytpl(gettpl).render(data,function(html){content=html;});return content;}function formatDate(strTime){var date=new Date(parseInt(strTime));return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+"";}function routeUrl(url,title){document.getElementById("title").innerHTML=title;viewApi.go("#account");var detail=document.getElementById('detail');detail.setAttribute("src",url);}function resetMenu(count,menuindex){resetCount(count,menuindex);var menu=document.getElementById('item'+menuindex+'mobile');menu.removeAttribute("isshow");}function resetCount(count,menuindex){var elem=document.getElementById("pcindex"+menuindex);elem.innerHTML=parseInt(elem.innerHTML)+count;}
		</script>
		 --%>
	</body>

</html>