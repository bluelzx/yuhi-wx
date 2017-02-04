<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script>
/**
*客户端我的工程单
**/
var loadbox=document.getElementById("shanrd-box");
		var owner='${ownerid}';
		var openid='${openid}';
		var pccountmap=eval("("+'${pccountmap}'+")");
		var gettpl = document.getElementById('template').innerHTML; 
		var type=0;
		mui.init();
		//初始化单页view
		var viewApi = mui('#app').view({
			defaultPage: '#setting'
		});
		//初始化单页的区域滚动
		mui('.mui-scroll-wrapper').scroll();
		var view = viewApi.view;
		(function($) {
			loadbox.style.display="none";
			initPcIndex();
			//处理view的后退与webview后退
			var oldBack = $.back;
			$.back = function() {
				clearViewCache();
				if (viewApi.canBack()) {
					viewApi.back();
				} else { 
					oldBack();
				}
			};
			var item2 = document.getElementById('item2mobile');
			document.getElementById('slider').addEventListener('slide', function(e) {
				//tab当前页数
				type=e.detail.slideNumber;
				var elem=document.getElementById('item'+(type+1)+'mobile');
				var isshow=elem.attributes["isshow"]; 
				if(!isshow)loadDataForType(type);
			});
			AsyncloadDataForType('<%=basePath%>projectCreateController.do?getProjectCreateList&ownerid='+owner);
		})(mui);
		
			//立即催单
			function PCReminder(pcid,e){
				loadbox.style.display="-webkit-box";
				mui.ajax('<%=basePath%>projectCardController.do?PCReminder&ownerid='+owner+"&id="+pcid,{
					type:'GET',//HTTP请求类型
					timeout:10000,//超时时间设置为10秒；
					success:function(data){
						loadbox.style.display="none";
						data=eval("("+data+")");
						if(data&&data.success){
							e.disabled=true; 
							e.innerHTML = "已催单";
							e.style.cssText="background-color: #999;border:1px solid #6B6C6D;";
							mui.toast('催单成功');
						}
					},
					error:function(xhr,type,errorThrown){
						loadbox.style.display="none";
						mui.toast('访问异常');
						console.log(type);
					}
				});
			}
			//取消工单
			function canelPC(pcid){
				var btnArray = ['确定','取消'];
				mui.confirm('确定此操作？', '请谨慎！', btnArray, function(e) {
				if (e.index == 0) {
					loadbox.style.display="-webkit-box";
					mui.ajax('<%=basePath%>projectCreateController.do?doDel&id='+pcid,{
						type:'GET',//HTTP请求类型
						timeout:10000,//超时时间设置为10秒；
						success:function(data){
							loadbox.style.display="none";
							data=eval("("+data+")");
							if(data&&data.success){
								mui.toast('取消工单成功');
								AsyncloadDataForType('<%=basePath%>projectCreateController.do?getProjectCreateList&ownerid='+owner);
							}
						},
						error:function(xhr,type,errorThrown){
							loadbox.style.display="none";
							mui.toast('访问异常');
							console.log(type);
						}
					});
				}
				});
			}
			//显示未分配详情
			function showProjectCreateDetail(projectCardid){
				routeUrl('<%=basePath%>projectCreateController.do?goProjectcardByid&projectCardid='+projectCardid,"工单详细");
			}
			//显示详情
			function showProjectCardDetail(projectCardid){
				routeUrl('<%=basePath%>projectCardController.do?goProjectcardByid&projectCardid='+projectCardid,"工单详细");
			}
			//异步获取选中tab的数据
			function loadDataForType(type){
					//办理结束  5,6都要
					if(type=="1")type="1,2";
					if(type=="2")type="3,";
					if(type=="3")type="4,5,6";
					AsyncloadDataForType('<%=basePath%>projectCardController.do?getMyProjectListData&ownerid='+owner+'&type='+type);
			}
			//异步获取选中tab的数据
			function AsyncloadDataForType(url){
				mui.ajax(url,{
					type:'GET',//HTTP请求类型
					timeout:10000,//超时时间设置为10秒；
					success:function(data){
						var elem=document.getElementById('item'+(type+1)+'mobile');
						elem.setAttribute("isshow","isshow");
						elem.querySelector('.mui-scroll').innerHTML =getcontent(data);
					},
					error:function(xhr,type,errorThrown){
						//异常处理；
						mui.toast('访问异常');
						console.log(type);
					}
				});
			}
			//立即评价
			function evaluateProjectCard(id,menuindex){
				var btnArray = ['提交','取消'];
				mui.confirm('<textarea id="evaluatecontent" row="7" class="mui-input-clear question" style="margin-bottom:0;"  placeholder="填写对服务的评价"></textarea>', '请你填写对该服务的评价！', btnArray, function(e) {
					if (e.index == 0) {
						loadbox.style.display="-webkit-box";
						var data=document.getElementById("evaluatecontent").value;
						if(!data&&data.length<1){
							data.placeholder="请填写评价！";
							return false;
						}
						mui.ajax('<%=basePath%>projectCardController.do?updateProjectState',{
							data:{
								'openid':openid,
								type:"10082",
								id:id,
								evaluatecontent:data
							},
							type:'POST',//HTTP请求类型
							timeout:10000,//超时时间设置为10秒；
							success:function(data){
								loadbox.style.display="none";
								if(data&&data.indexOf("success")!=-1){
									mui.toast("操作成功！");
									loadDataForType(menuindex);
								}else{
									mui.toast("后台异常！请联系管理员");
								}
							},
							error:function(xhr,type,errorThrown){
								loadbox.style.display="none";
								mui.toast('访问异常');
								console.log(types);
							}
						});
					}
				});
			}
			//接拒单
			function getOrsetProjectCard(types,id,menuindex){
				var btnArray = ['确定','取消'];
				mui.confirm('确定此操作？', '请谨慎！', btnArray, function(e) {
					if (e.index == 0) {
						loadbox.style.display="-webkit-box";
						mui.ajax('<%=basePath%>projectCardController.do?updateProjectState',{
							data:{
								'openid':openid,
								type:types,
								id:id
							},
							type:'POST',//HTTP请求类型
							timeout:10000,//超时时间设置为10秒；
							success:function(data){
								loadbox.style.display="none";
								if(data&&data.indexOf("success")!=-1){
									mui.toast("操作成功！");
									loadDataForType(menuindex);
								}else{
									mui.toast("后台异常！请联系管理员");
									loadDataForType(menuindex);
								}
							},
							error:function(xhr,type,errorThrown){
								loadbox.style.display="none";
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
					for(var i=0;i<data.resultList.length;i++){
						data.resultList[i].createTime=formatDate(data.resultList[i].createTime);
						var time=formatDate(data.resultList[i].getTime);
						data.resultList[i].getTime=time?time:"暂未接单";
					}
					data.type=type;
					//if(type==1)elem=document.getElementById("pcindex1").innerHTML=data&&data.length?data.length:0;
				}catch(e){
					//data=type;
				}
				//for(var i=0;i<data.length;i++){
					var elem=document.getElementById("pcindex"+(type+1));
					elem.innerHTML=data.resultList.length;
					laytpl(gettpl).render(data, function(html){
  						content = html;
					});	
				//}
				return content;	
			}
			function formatDate (strTime) {
				if(!strTime)return null;
			    var date = new Date(parseInt(strTime));
			    return date.getFullYear()+"-"
					    +(date.getMonth()+1)+"-"
					    +date.getDate()+"";
					    //+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
			}
			//路由到指定url
			function routeUrl(url,title){
				document.getElementById("title").innerHTML=title;
				viewApi.go("#account");
				var detail=document.getElementById('detail');
				detail.setAttribute("src",url);
			}
			//清楚视图缓存
			function clearViewCache(){
				document.getElementById('detail').setAttribute("src","");
			}
			//初始化角标
		function initPcIndex(){
			for(var i=0;i<pccountmap.length;i++){
				var count=0;
				var elem=null;
				switch(parseInt(pccountmap[i].state)){
					case 0: //未分配  -projectcreate
						
						break;
					case 1:
					case 2://待接单,待办理
						elem=document.getElementById("pcindex2");
						count=parseInt(elem.innerHTML)+pccountmap[i].count;
						break;
					case 3:
						elem=document.getElementById("pcindex3");
						count=parseInt(elem.innerHTML)+pccountmap[i].count;
						break;
					case 4:
					case 5:
					case 6:
						elem=document.getElementById("pcindex4");
						count=parseInt(elem.innerHTML)+pccountmap[i].count;
						break;
				}
				elem.innerHTML=count;
			}
		}
			
			
		//支付页面
		var InvitationDataAry=[]; //耗材数组
		var count=0;
		var price=0;
		var pcid;
		
		function pay(projectCardid){
			viewApi.go("#paybox");
			mui.ajax('<%=basePath%>webpage/weixin/idea/project/template/payForProjectCard.jsp',{
				type:'GET',//HTTP请求类型
				timeout:10000,//超时时间设置为10秒；
				success:function(data){
					var detail=document.getElementById('paycontent');
					detail.innerHTML=data;
					pcid=projectCardid;
					initView();
				},
				error:function(xhr,type,errorThrown){
					//异常处理；
					mui.toast('访问异常');
					console.log(type);
				}
			});
			//routeUrl('projectCardController.do?goPay&pcid='+projectCardid+'&openid='+openid,"工单支付详情");
			//把支付目录更换为template否则路由无法支付成功
		}
		//初始化已有耗材
		function initView(){
			//获取数据
			mui.ajax({ 
	            type:"GET", 
	            url:'<%=basePath%>projectCardInvitationController.do?getExitsInvitation&pcid='+pcid, 
	            dataType:'json',      
	            contentType:'application/json',               
	            success:function(data){ 
	            	if(data&&data.resultList){
	            		count=data.count;
	                	for(var i=0;i<data.resultList.length;i++){
	                		//描绘界面
	                  		setinToview(data.resultList[i].id,data.resultList[i].name,data.resultList[i].price,data.resultList[i].descs);
	                  		price+=parseInt(data.resultList[i].price);
	                	}
	                	document.getElementById("count").value="一共花费"+count+"件耗材";
	                	document.getElementById("price").value=""+price+"元";
	                }           
	            } 
	         });
		
		}
		
		//界面添加耗材
		function setinToview(value,text,price,desc){
			desc=desc?desc:'暂无';
			document.getElementById("datagrid").innerHTML+='<li value="'+value+'" index="'+InvitationDataAry.length+'" class="mui-table-view-cell mui-media"><div class="mui-media-body"><span>'+text+'</span><p class="mui-ellipsis"><strong>收费标准：</strong>'+desc+'</p><p><strong>现收费：</strong>'+price+'元</p></div></li>';
		}
		//调用支付
		function submit(){
	        try{
	        	if(!WeixinJSBridge){
                 	 alert("请在微信客户端打开！！");
                 	 return false;
                  }
	      	   mui.ajax({
	                 type: 'POST',
	                 url: '<%=basePath%>wechatPay/jsOarder.do',
	                 data: {'detail':'耗材数量:'+count+'件,'+'耗材总价格:'+price+'元',
	                	    'desc':'工程单'+pcid+'号耗材支付',
	                	    'goodSn':pcid,
	                	    'orderSn':pcid,
	                	    'amount':price,
	                	    'openId':openid},
	                 success: function(data){
	                 data = eval("(" + data + ")");
	                 var appId=data.obj.appId;
	                 var timeStamp=data.obj.timeStamp;
	                 var nonceStr=data.obj.nonceStr;
	                 var package=data.obj.package;
	                 var paySign=data.obj.paySign;
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
	                             if(res.err_msg == "get_brand_wcpay_request:ok"){
	                                 //支付成功调用
	                                 //alert("成功");
	                                 changeProjectCardPayState();
	                             }else if(res.err_msg == "get_brand_wcpay_request:cancel"){
	                                 //取消支付调用
	                                // alert("取消");
	                                 //changeProjectCardPayState();
	                             }else{
	                            	 //$("#json").val(JSON.stringify(res));
	                                 //支付失败
	                                 alert("支付失败，请稍后重新支付！");
	                             }
	                         });
	             },dataType: "json"});
	         }catch(e){
	        	 alert("请在微信客户端打开！！");
	        	 return false;
	         } 
	      }
		//修改工单支付状态
		function changeProjectCardPayState(){
			//获取数据
			mui.ajax({ 
	            type:"GET", 
	            url:'<%=basePath%>projectCardController.do?confirmpay&id='+pcid+'&paytype=1', 
	            dataType:'json',      
	            contentType:'application/json',               
	            success:function(data){ 
	            	if(data&&data.success){
	        			viewApi.back();
	            		loadDataForType(4);
	        			mui.toast('支付成功！');
	                }           
	            } 
	         }); 
		}

</script>