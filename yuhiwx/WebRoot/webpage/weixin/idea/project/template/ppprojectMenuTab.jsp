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
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/plug-in/mui/css/app.css" />
	</head>
	<body>
		<style>
			.mui-control-content {
				background-color: #EFEFF4;
				min-height: 500px;
			}
			.mui-control-content .mui-loading {
				margin-top: 50px;
			}
		</style>
		<header class="mui-bar mui-bar-nav">
			<h1 class="mui-title">我的工单</h1>
		</header>
		<div class="mui-content">
			<div id="slider" class="mui-slider">
				<div id="sliderSegmentedControl" class="mui-slider-indicator mui-segmented-control mui-segmented-control-inverted">
					<a class="mui-control-item" href="#item2mobile">
						待接单
					</a>
					<a class="mui-control-item" href="#item3mobile">
						待办理
					</a>
					<a class="mui-control-item" href="#item4mobile">
						办理中
					</a>
					<a class="mui-control-item" href="#item5mobile">
						办理结束
					</a>
				</div>
				<div id="sliderProgressBar" class="mui-slider-progress-bar mui-col-xs-3"></div>
				<div class="mui-slider-group" >
					<div id="item1mobile" class="mui-slider-item mui-control-content mui-active">
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
					<div id="item2mobile" class="mui-slider-item mui-control-content">
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
					<div id="item3mobile" class="mui-slider-item mui-control-content">
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
					<div id="item4mobile" class="mui-slider-item mui-control-content">
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
		
		<script id="template" type="text/html">
						{{# if(d&&d.resultList&&d.resultList.length>0){ }}
							{{# for(var i = 0, len = d.resultList.length; i < len; i++){ }}
										<div class="mui-card">
											<div class="mui-card-header">页眉</div>
											<div class="mui-card-content">
												<div class="mui-card-content-inner">
													{{d.resultList[i].content}}
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
										<button onclick="setInvitation('{{d.resultList[i].id}}')" type="button" class="mui-btn mui-btn-primary">
											添加耗材
										</button>
										<button onclick="getOrsetProjectCard(10081,'{{d.resultList[i].id}}','{{d.type}}')" type="button" class="mui-btn mui-btn-danger">
											开始办理
										</button>
									{{# } }}
									{{# if(d.resultList[i].state==3){ }}
										<button onclick="getOrsetProjectCard(10080,'{{d.resultList[i].id}}','{{d.type}}')" type="button" class="mui-btn mui-btn-primary">
											办理结束
										</button>
									{{# } }}
									{{# if(d.resultList[i].state==4){ }}
										<button onclick="" type="button" class="mui-btn mui-btn-primary">
											查看评价
										</button>
									{{# } }}



										<button onclick="openwindow()" type="button" class="mui-btn mui-btn-danger">
											查看详细
										</button>	
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
		<script src="<%=basePath%>/plug-in/mui/css/mui.min.js"></script>
		<script src="<%=basePath%>/plug-in/laytpl/laytpl.js"></script>
		<script>
		var openid='<%=request.getParameter("openid")%>';
		var gettpl = document.getElementById('template').innerHTML; 
		var type=1;
			mui.init({swipeBack: false});
			
			(function($) {
				$('.mui-scroll-wrapper').scroll({
					indicators: true //是否显示滚动条
				});
				var item2 = document.getElementById('item2mobile');
				document.getElementById('slider').addEventListener('slide', function(e) {
					//tab当前页数
					type=e.detail.slideNumber+1;
					var elem=document.getElementById('item'+type+'mobile');
					var isshow=elem.attributes["isshow"]; 
					if(!isshow)loadDataForType(type);	
				});
				loadDataForType(1);
			})(mui);
			
			
			
			//异步获取选中tab的数据
			function loadDataForType(type){
					AsyncloadDataForType('<%=basePath%>projectCardController.do?getppProjectListData&openid='+openid+'&type='+type);
			}
			
			//异步获取选中tab的数据
			function AsyncloadDataForType(url){
				mui.ajax(url,{
					type:'GET',//HTTP请求类型
					timeout:10000,//超时时间设置为10秒；
					success:function(data){
						var elem=document.getElementById('item'+type+'mobile');
						elem.setAttribute("isshow","isshow");
						elem.querySelector('.mui-scroll').innerHTML = getcontent(data);
					},
					error:function(xhr,type,errorThrown){
						//异常处理；
						mui.toast('访问异常');
						console.log(type);
					}
				});
			}
			/**
			评价框
			mui.confirm('<textarea id="desc" class="mui-input-clear question" style="margin-bottom:0;"  placeholder="对问题的补充说明"></textarea>', '请谨慎！', btnArray, function(e) {
				mui.toast('访问');
			});
			*/
			//接拒单
			function getOrsetProjectCard(types,id,menuindex){
				var btnArray = ['确定','取消'];
				mui.confirm('确定此操作？', '请谨慎！', btnArray, function(e) {
					if (e.index == 0) {
						mui.ajax('<%=basePath%>projectCardController.do?updateProjectState',{
							data:{
								'openid':openid,
								type:types,
								id:id
							},
							type:'POST',//HTTP请求类型
							timeout:10000,//超时时间设置为10秒；
							success:function(data){
								if(data&&data.indexOf("success")!=-1){
									mui.toast("操作成功！");
									loadDataForType(menuindex);
								}else{
									mui.toast("后台异常！请联系管理员");
									loadDataForType(menuindex);
								}
							},
							error:function(xhr,type,errorThrown){
								//异常处理；
								mui.toast('访问异常');
								console.log(types);
							}
						});
					}
				});
			}
			
			
			
			//获取模板合并数据html
			function getcontent(data){
				var content="";
				try{
					data=eval("("+data+")");
					data.type=type;
				}catch(e){
					//data=type;
				}
				//for(var i=0;i<data.length;i++){
					laytpl(gettpl).render(data, function(html){
  						content = html;
					});	
				//}
				return content;	
			}
			
			function openwindow(pcid){
				//location.href="<%=basePath%>projectCardController.do?goProjectcardByid&projectCard="+pcid;
				mui.openWindow({
					    url:"https://www.baidu.com",
					    id:"newpage.html",
					    styles:{
						     top:50//新页面顶部位置
						     // bottom:newage-bottom-position,//新页面底部位置
						     // width:newpage-width,//新页面宽度，默认为100%
						    //  height:newpage-height//新页面高度，默认为100%
						    },
					    extras:{
					     //自定义扩展参数，可以用来处理页面间传值
					    },
					    createNew:false,//是否重复创建同样id的webview，默认为false:不重复创建，直接显示
					    show:{
					      autoShow:true//页面loaded事件发生后自动显示，默认为true
					      //aniShow:animationType,//页面显示动画，默认为”slide-in-right“；
					     // duration:animationTime//页面动画持续时间，Android平台默认100毫秒，iOS平台默认200毫秒；
					    },
					    waiting:{
					      autoShow:true,//自动显示等待框，默认为true
					      title:'正在加载...'//等待对话框上显示的提示内容
					    }
					})
			}
		</script>

	</body>

</html>