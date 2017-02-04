<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script>
/**
*客户端我的工程单(工程)
**/
var loadbox=document.getElementById("shanrd-box");
var openid='${openid}';
var pccountmap=eval("("+'${pccountmap}'+")");
var gettpl = document.getElementById('template').innerHTML;
var type=1;
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
		type=e.detail.slideNumber+1;
		var elem=document.getElementById('item'+type+'mobile');
		var isshow=elem.attributes["isshow"]; 
		if(!isshow)loadDataForType(type);	
	});
	loadDataForType(1);
})(mui);
	//显示评价
	function showRepir(id){
		if(!id||id.indexOf("null")!=-1){
			mui.toast('暂无业主评价！');
			return;
		}
		loadbox.style.display="-webkit-box";
		mui.ajax('<%=basePath%>projectCardObjectionController.do?getObjection&pcid='+id,{
			type:'GET',//HTTP请求类型
			timeout:10000,//超时时间设置为10秒；
			success:function(data){
				loadbox.style.display="none";
				data=eval("("+data+")");
				if(data.success){
				mui.alert(data.obj.othercontent, '业主评价！');
				}
			},
			error:function(xhr,type,errorThrown){
				$(".shanrd-box").hide();
				//异常处理；
				mui.toast('访问异常');
				console.log(type);
			}
		});
	}
	//添加耗材回调
	function addinCallback(){
		loadDataForType(2);
		viewApi.back();
		mui.toast('添加成功！');
	}
	//添加耗材
	function setInvitation(id){
		routeUrl('<%=basePath%>projectInvitationController.do?addInvitationForProjectCard&id='+id,"添加耗材");
	}
	//显示详情
	function showProjectCardDetail(projectCardid){
		routeUrl('<%=basePath%>projectCardController.do?goProjectcardByid&projectCardid='+projectCardid,"工单详细");
	}
	//异步获取选中tab的数据
	function loadDataForType(type){
			//办理结束  5,6都要
			if(type=="4")type="4,5,6";
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
	function getOrsetProjectCard(types,id,menuindex,inid){
		var btnArray = ['确定','取消'];
		var mes="";
		var title="请谨慎！"
		//if(types==10081&&inid==0)mes+='该工单还未添加耗材,';
		if(types==10081){
			debugger
			if(inid.indexOf("null")!=-1||inid==""||inid==0){
				mui.toast("请添加工程耗材");return false;
				}}
		if(types==10079){//判断是否是拒单
			mes+='<textarea id="evaluatecontent"  class="mui-input-clear question" style="margin-bottom:0;height:120px"  placeholder="请填写拒单原因"></textarea>';
			title='请你填写拒单原因！';
		}else		mes+='确定此操作？';
		mui.confirm(mes, title, btnArray, function(e) {
			if (e.index == 0) {
				var data="";
				if(types==10079){
					data=document.getElementById("evaluatecontent").value;
					if(!data&&data.length<1){
						mui.toast("操作异常！请重新拒单并填写拒单原因！");
						return ;
					}
				}
				loadbox.style.display="-webkit-box";
				var formdata={openid:openid,type:types,id:id,pickdescs:data};
				mui.ajax('<%=basePath%>projectCardController.do?updateProjectState',{
					data:formdata,
					type:'POST',//HTTP请求类型
					timeout:10000,//超时时间设置为10秒；
					success:function(data){
						loadbox.style.display="none";
						if(data&&data.indexOf("success")!=-1){
							mui.toast("操作成功！");
							loadDataForType(menuindex);
							if(types==10078)resetMenu(1,2);
							if(types==10081)resetMenu(1,3);
							if(types==10080)resetMenu(1,4);
						}else{
							mui.toast("后台异常！请联系管理员");
							loadDataForType(menuindex);
						}
					},
					error:function(xhr,type,errorThrown){
						//异常处理；
						loadbox.style.display="none";
						mui.toast('访问异常');
						console.log(types);
					}
				});
			}
		});
	}
	//初始化角标
	function initPcIndex(){
		for(var i=0;i<pccountmap.length;i++){
			var count=0;
			var elem=null;
			switch(parseInt(pccountmap[i].state)){
				case 1:
					elem=document.getElementById("pcindex1");
					count=parseInt(elem.innerHTML)+pccountmap[i].count;
					break;
				case 2:
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
	//获取模板合并数据html
	function getcontent(data){
		var content="";
		try{
			data=eval("("+data+")");
			for(var i=0;i<data.resultList.length;i++){
				data.resultList[i].createTime=formatDate(data.resultList[i].createTime);
				data.resultList[i].getTime=formatDate(data.resultList[i].getTime);
			}
			data.type=type;
		}catch(e){
			//data=type;
			alert("数据获取异常,请重新获取！");
		}
		//for(var i=0;i<data.length;i++){
			var elem=document.getElementById("pcindex"+type);
			elem.innerHTML=data.resultList.length;
			laytpl(gettpl).render(data, function(html){
					content = html;
			});	
		//}
		return content;	
	}
	function formatDate (strTime) {
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
	//重设条数和未刷新状态
	function resetMenu(count,menuindex){
		resetCount(count,menuindex);
		var menu=document.getElementById('item'+menuindex+'mobile');
		menu.removeAttribute("isshow");
	}
	//重设角标
	function resetCount(count,menuindex){
		var elem=document.getElementById("pcindex"+menuindex);
		elem.innerHTML=parseInt(elem.innerHTML)+count;
	}
	//清楚视图缓存
	function clearViewCache(){
		document.getElementById('detail').setAttribute("src","");
	}
</script>