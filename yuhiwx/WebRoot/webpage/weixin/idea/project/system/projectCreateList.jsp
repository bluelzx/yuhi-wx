<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="projectCreateList" checkbox="true"  fitColumns="false" title="待处理工程单" actionUrl="projectCreateController.do?datagrid" idField="id" fit="true" queryMode="group">
     <t:dgCol title="工单号"  url="" funname="showDetail(this)" align="center" frozenColumn="true" field="id"  hidden="true"   query="true"  queryMode="single"  width="140"></t:dgCol>
   <t:dgCol title="part"  field="part"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
 <t:dgCol title="工单类型" frozenColumn="true" align="center" field="pctype"  hidden="true" queryMode="single"  width="120" replace="服务单_0,公共单_1"></t:dgCol>
<t:dgCol title="区域" frozenColumn="true"  field="publicplace"  hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期" frozenColumn="true" align="center" field="create_date"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="发单人" query="true"  align="center"  field="ownername"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="手机"  field="phone"  hidden="true" query="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="其他联系方式"  field="othercontact"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="设备设施问题描述"  field="content"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="可入户开始时间"  field="starttime" formatter="yyyy-MM-dd hh:mm:ss" hidden="true"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="可入户结束时间"  field="endtime" formatter="yyyy-MM-dd hh:mm:ss" hidden="true"  queryMode="group"  width="120"></t:dgCol>
  <t:dgCol title="备注"  field="descs"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
      <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgToolBar title="分配" icon="icon-add" funname="partProjectCard"></t:dgToolBar>
   <t:dgToolBar title="人工下单" icon="icon-add" funname="personProjectCard"></t:dgToolBar>
   <t:dgToolBar title="公共服务下单" icon="icon-add" funname="publicProjectCard"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="projectCreateController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgDelOpt title="删除" url="projectCreateController.do?doDel&id={id}" />
   <t:dgToolBar title="接拒单历史记录" icon="icon-search" funname="checkpcvDetail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <span id="alert_sound"></span>
 
 <script type="text/javascript">
 
 //查看耗材
 function showDetail(e){
	 var idstr=$(e).html();
     if (idstr&&idstr.length>1) {
    	 createdetailwindow("工程单详情", "projectCreateController.do?goUpdate&id="+idstr,600,385);    		 
     }else{
    	 tip("请重新选择");
     }
 }
 //接拒单历史记录
 function checkpcvDetail(){
	 var rows = $("#projectCreateList").datagrid('getSelections');
     if (rows.length > 0&&rows.length==1) {
    	 createdetailwindow("接拒单历史记录", "projectPickHistoryController.do?projectPickHistory&id="+rows[0].id,741,384);    		 
     }else{
    	 tip("请选择一条数据");
     }
	 
 }
 //公共服务下单
 function publicProjectCard(){
	  createwindow("公共服务下单", "projectCreateController.do?publicProjectCard",604,350);
 }
//人工单录入
 function personProjectCard(){
    createwindow("人工下单", "projectCreateController.do?personcard",606,451
    		,function(){
    	$("#projectCreateList").datagrid('reload');
    });    		 
 }
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#projectCreateListtb").find("input[name='createDate_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#projectCreateListtb").find("input[name='createDate_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
		//消息检查
 		setTimeOutRefule();
 });
 var time;
 //定时拉取数据
 function setTimeOutRefule(){
	 time=setTimeout(function(){
		 $.ajax({
				url : 'projectCreateController.do?getMsg&deptid=${deptid}',
				type : 'GET',
				cache : false,
				success : function(data) {
					if(data){
						var d = $.parseJSON(data);
						tip("您有新的待分配工单"+d.msg+"张");
						AudioPerform();
						$("#projectCreateList").datagrid('reload');
					}
				},
				error:function(){
					alert("非常抱歉，服务消息获取异常，请手动刷新本页面！");
				}
			});
		 setTimeOutRefule();
	 },2000);
 }
 function partProjectCard(width,height) {
	    var rows = $("#projectCreateList").datagrid('getSelections');
	    if(width=="100%" || height=="100%"){
			width = window.top.document.body.offsetWidth;
			height =window.top.document.body.offsetHeight-100;
		}
	    if (rows.length > 0&&rows.length==1) {
	    	var dialogs=$.dialog({
				content: 'url:projectPersonController.do?partProjectCard&isok=1&part='+rows[0].part,
				lock : true,
				//zIndex:1990,
				width:700,
				height:400,
				title:"分配单",
				opacity : 0.3,
				cache:false,
			    ok: function(){
			    	iframe = this.iframe.contentWindow;
			    	var openid=iframe.getpardPersonOpenid();
			    	if(openid){
			    	send("projectCreateController.do?doUpdate",rows[0].id,openid);
					return true;
			    	}else 
			    		return false;
			    },
			    cancelVal: '关闭',
			    cancel: true /*为true等价于function(){}*/
			}).zindex();
	    }else{
	    	 tip("请选择一条数据");
	     }
}
//分配单
function send(url,id,openid){
	$.ajax({
		url : url,
		type : 'post',
		data : {
			id :id,
			openid :openid
		},
		cache : false,
		success : function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
				var msg = d.msg;
				tip(msg);
				$("#projectCreateList").datagrid('reload');
			}
		}
	});
}
function AudioPerform() {
    var ua = navigator.userAgent.toLowerCase();
    var audiopath = "http://zjdx1.sc.chinaz.com/Files/DownLoad/sound1/201212/2478.mp3";
    if (ua.match(/msie ([\d.]+)/)) {
        jQuery('#alert_sound').html('<object classid="clsid:22D6F312-B0F6-11D0-94AB-0080C74C7E95"><param name="AutoStart" value="1" /><param name="Src" value="' + audiopath + '" /></object>');
    }
    else if (ua.match(/firefox\/([\d.]+)/)) {
        //            jQuery('#alert_sound').html('<embed src="' + audiopath + '" type="audio/wav" hidden="true" loop="false" mastersound></embed>');
        jQuery('#alert_sound').html('<audio autoplay="autoplay"><source src="' + audiopath + '" type="audio/wav"/><source src="$!{TempletPath}images/ring.wav" type="audio/mpeg"/></audio>');
    }
    else if (ua.match(/chrome\/([\d.]+)/)) {
        jQuery('#alert_sound').html('<audio src="' + audiopath + '" type="audio/wav" autoplay=”autoplay” hidden="true"></audio>');
    }
    else if (ua.match(/opera.([\d.]+)/)) {
        jQuery('#alert_sound').html('<embed src="' + audiopath + '" hidden="true" loop="false"><noembed><bgsounds src=' + audiopath + '></noembed>');
    }
    else if (ua.match(/version\/([\d.]+).*safari/)) {
        jQuery('#alert_sound').html('<audio src="' + audiopath + '" type="audio/wav" autoplay=”autoplay” hidden="true"></audio>');
    }
    else {
        jQuery('#alert_sound').html('<embed src="' + audiopath + '" type="audio/wav" hidden="true" loop="false" mastersound></embed>');
    }
}
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'projectCreateController.do?upload', "projectCreateList");
}

//导出
function ExportXls() {
	JeecgExcelExport("projectCreateController.do?exportXls","projectCreateList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("projectCreateController.do?exportXlsByT","projectCreateList");
}
 </script>