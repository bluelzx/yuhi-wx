<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="projectCardList"  checkbox="true" fitColumns="false" title="工程单管理" actionUrl="projectCardController.do?datagrid" idField="id" fit="true" pageSize="15" queryMode="group">
   <t:dgCol title="工单号" url="" funname="showDetail(this)" align="center" frozenColumn="true" field="id"  hidden="true"   query="true"  queryMode="single"  width="140"></t:dgCol>
   <t:dgCol title="房号" align="center" frozenColumn="true" field="roomid"  hidden="true"   query="true"  queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="工单业主"  frozenColumn="true" field="owner" query="true"  hidden="true"  queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="工单状态"  query="true"  frozenColumn="true" style="background-color:red;_1,background-color:#FE4C40;_3,background-color:#FE0000;_2" field="state"   queryMode="single"  hidden="true"   width="90" replace="待接单_1,待办理_2,办理中 _3,办理结束(待评价)_4,待回访_5,办理完成_6" ></t:dgCol>
   <t:dgCol title="工单类型" align="center"  frozenColumn="true"  field="pctype" query="true"  queryMode="single"  hidden="true"   width="60" replace="服务单_0,公共单_1" ></t:dgCol>
   <t:dgCol title="服务类型" align="center" frozenColumn="true"  field="pcserver"  queryMode="single"  hidden="true"   width="60" ></t:dgCol>
   <t:dgCol title="工单可用" align="center" frozenColumn="true" style="background-color:green;_1,background-color:red;_0" field="isok"  hidden="true" query="true"  queryMode="single"   width="60" replace="禁用_0,可用_1"></t:dgCol>
   <t:dgCol title="办理工程人员" align="center" frozenColumn="true" field="name"  hidden="true"  queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="手机" field="phone"  hidden="true" query="true"  queryMode="single"   width="120"></t:dgCol>
   <t:dgCol title="其他联系方式" field="othercontact"  hidden="true"  queryMode="single"   width="120"></t:dgCol>
   <t:dgCol title="创建日期" align="center"  field="create_time" query="true"   hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="办理时间" align="center" field="handle_time"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="分配时间" align="center" field="distribution_time"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="接单时间" align="center" field="get_Time"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="结束时间" align="center" field="finished_time"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="问题描述" align="center" field="content"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="评价审核" align="center" field="throughaudit" query="true"  queryMode="single"  hidden="true"   width="120" replace="不能审核_5,可审核_0,不通过_2,通过 _1" ></t:dgCol>
   <t:dgCol title="支付状态" align="center" field="ispay" query="true"  queryMode="single"  hidden="true"   width="60" replace="未支付_0,微信已支付 _1,人工已支付 _2" ></t:dgCol>
   <t:dgCol title="所属项目" align="center"  field="region"  hidden="true"  queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="备注"  field="descs"  hidden="true"  queryMode="group"  width="120"></t:dgCol><%--
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   --%><t:dgToolBar title="审核" icon="icon-add" funname="throughAudit"></t:dgToolBar>
    <t:dgToolBar title="工单类型设置" icon="icon-add" funname="confirmpc"></t:dgToolBar>
   <t:dgToolBar title="人工支付确认" icon="icon-add" funname="confirmpay"></t:dgToolBar>
    <t:dgToolBar title="关闭工单" icon="icon-add" funname="closeProjectCard"></t:dgToolBar>
   <t:dgToolBar title="回访描述" icon="icon-edit"  funname="callBackProjectCard"></t:dgToolBar>
   <t:dgToolBar title="查看耗材" icon="icon-edit"  url="projectCardInvitationController.do?projectCardInvitation" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="查看支付详情" icon="icon-search"  funname="checkpayDetail"></t:dgToolBar>
   <t:dgToolBar title="工单打印" icon="icon-search"  funname="showreport"></t:dgToolBar>
   <%--<t:dgToolBar title="编辑" icon="icon-edit" url="projectCardController.do?goUpdate" funname="update"></t:dgToolBar>
   --%><%--
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   --%>
   <t:dgToolBar title="接拒单历史记录" icon="icon-search" funname="checkpcvDetail"></t:dgToolBar>
   <t:dgToolBar title="工程单导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 
 //查看耗材
 function showDetail(e){
	 var idstr=$(e).html();
     if (idstr&&idstr.length>1) {
    	 createdetailwindow("工程单详情", "projectCardController.do?goUpdate&id="+idstr,613,374);    		 
     }else{
    	 tip("请重新选择");
     }
	 
 }
 //接拒单历史记录
 function checkpcvDetail(){
	 var rows = $("#projectCardList").datagrid('getSelections');
     if (rows.length > 0&&rows.length==1) {
    	 createdetailwindow("接拒单历史记录", "projectPickHistoryController.do?projectPickHistory&id="+rows[0].id,741,384);    		 
     }else{
    	 tip("请选择一条数据");
     }
	 
 }
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#projectCardListtb").find("input[name='createTime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#projectCardListtb").find("input[name='createTime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#projectCardListtb").find("input[name='handleTime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#projectCardListtb").find("input[name='handleTime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#projectCardListtb").find("input[name='distributionTime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#projectCardListtb").find("input[name='distributionTime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#projectCardListtb").find("input[name='getTime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#projectCardListtb").find("input[name='getTime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#projectCardListtb").find("input[name='finishedTime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#projectCardListtb").find("input[name='finishedTime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
 /**
  * 创建添加或编辑窗口
  * 
  * @param title
  * @param addurl
  * @param saveurl
  */
 function createwindowforstate(title, addurl,width,height,id) {
 	width = width?width:700;
 	height = height?height:400;
 	if(width=="100%" || height=="100%"){
 		width = window.top.document.body.offsetWidth;
 		height =window.top.document.body.offsetHeight-100;
 	 }
 		//--author：JueYue---------date：20140427---------for：弹出bug修改,设置了zindex()函数
 		$.dialog({
			content: 'url:'+addurl,
			lock : true,
			width:width,
			//zIndex:1990,
			height:height,
			parent:windowapi,
			title:title,
			opacity : 0.3,
			cache:false,
		    ok: function(){
		    	var name=$(this.iframe).contents().find("#pctype").find("option:selected").text();
		    	$.ajax({
					url : "projectCardController.do?setProjectType",
					data:{"id":id,"pctype":name},
					type : 'POST',
					cache : false,
					success : function(data) {
						if(data.indexOf("success")){
							tip("操作成功！");
							 $("#projectCardList").datagrid('reload');
						}else{
							tip("操作异常！");
						}
					}
				});
				return true;
		    },
		    cancelVal: '关闭',
		    cancel: true /*为true等价于function(){}*/
		}).zindex();

 }
 //查看耗材
 function checkInvDetail(){
	 var rows = $("#projectCardList").datagrid('getSelections');
     if (rows.length > 0&&rows.length==1) {
    	 createdetailwindow("耗材详情", "projectCardController.do?gopayDetail&id="+rows[0].id,604,500);    		 
     }else{
    	 tip("请选择一条数据");
     }
	 
 }
 //工单服务类型设置
 function confirmpc(){
	 var rows = $("#projectCardList").datagrid('getSelections');
     if (rows.length > 0&&rows.length==1) {
		//if(rows[0].state=='3'){
			createwindowforstate("工单类型设置", "projectCardController.do?finishPc",604,38,rows[0].id);    		 
    //	 }else{
    	//	 tip("只有办理中的工单支持后台完成！");
    		// return;
    	 //}
     }else{
    	 tip('请选择一条记录再操作！');
 		return;
     }
    	 
	 
 }
 //显示打印
function showreport(){
	 var rows = $("#projectCardList").datagrid('getSelections');
     if (rows.length > 0&&rows.length==1) {
    	 ow("http://localhost:8080/Report/ReportServer?reportlet=WorkBook6.cpt&pcid="+rows[0].id)
     }else{
    	tip('请选择一条记录再进行操作！');
		return;
    }
}
 
 
 //查看支付详情
 function checkpayDetail(){
	 var rows = $("#projectCardList").datagrid('getSelections');
     if (rows.length > 0&&rows.length==1) {
    	 if(rows[0].pctype=='1'){
    		 tip("公共单无法查看支付详情！");
    		 return;
    	 }
    	 if(rows[0].isPay=='0'){
    		 tip("该工程单未支付！");
    		 return;
    	 }
    	 if(rows[0].isPay=='2'){
    		 tip("人工单无法查看支付详情！");
    		 return;
    	 }
    	 createdetailwindow("支付详情", "projectCardController.do?gopayDetail&id="+rows[0].id,604,500);    		 
     }else{
    	 tip("请选择一条数据");
     }
	 
 }
//支付确认
 function confirmpay() {
 	var rows = $("#projectCardList").datagrid('getSelections');
     if (rows.length > 0&&rows.length==1) {
    	 if(rows[0].pctype=='1'){
    		 tip("公共单不需要支付确认！");
    		 return;
    	 }
    	 if(rows[0].isPay=='1'||rows[0].isPay=='2'){
    		 tip("该工程单已经支付成功！");
    		 return;
    	 }
    	 debugger
    	 if(rows[0].state=='1'||rows[0].state=='2'||rows[0].state=='3'){
        		 tip("该工程单无法人工支付确认！");
        		 return;
          }
    	 $.dialog.confirm("该工程单已经支付？",function(r){
    		 if(r){
    			 if(rows[0].isok.indexOf("1")>-1){
    				 $.ajax({
     		    		url:"projectCardController.do?confirmpay&id="+rows[0].id,
     		    		type : 'GET',
     					cache : false,
     					success : function(data) {
     						var d = $.parseJSON(data);
    						if (d.success) {
    							tip("操作成功！");
    							reloadTable();
    						}
     					}
     		    	}); 
    			 }else{
    				 tip('请勿重复禁用！');
    			 }
    		 }
    	 });
     }else{
    	tip('请选择一条记录再操作！');
		return;
    }
}
 
 //回访工单
 function callBackProjectCard(){
	 var rows = $("#projectCardList").datagrid('getSelections');
     if (rows.length > 0&&rows.length==1) {
    	 if(rows[0].pctype=='1'){
    		 tip("公共单无法工单回访！");
    		 return;
    	 }
    	 if(rows[0].state==5){
    	 createwindow("工单回访", "projectCardController.do?goCallback&id="+rows[0].id,604,200);    		 
    	 }else{
    		 tip("客户未评价无法回访！");
    	 }
     }else{
    	tip('请选择一条记录再操作！');
		return;
    }
 }
 
//关闭工单
 function closeProjectCard() {
 	var rows = $("#projectCardList").datagrid('getSelections');
     if (rows.length > 0&&rows.length==1) {
    	 
    	 $.dialog.confirm("请慎重操作，一旦禁用将无法启用",function(r){
    		 if(r){
    			 if(rows[0].isok.indexOf("1")>-1){
    				 $.ajax({
     		    		url:"projectCardController.do?closeProjectCard&id="+rows[0].id,
     		    		type : 'GET',
     					cache : false,
     					success : function(data) {
     						var d = $.parseJSON(data);
    						if (d.success) {
    							tip("操作成功！");
    							reloadTable();
    						}
     					}
     		    	}); 
    			 }else{
    				 tip('请勿重复禁用！');
    			 }
    		 }
    	 });
     }else{
    	tip('请选择一条记录再操作！');
		return;
    }
}
//审核单
function throughAudit() {
	var rows = $("#projectCardList").datagrid('getSelections');
    if (rows.length > 0&&rows.length==1) {
    	if(rows[0].pctype=='1'){
   		 tip("公共单不支持审核评价！");
   		 return;
   	 	}
    	if(rows[0].throughaudit=='5'){
    		tip('该工单数据还未业主评价，无法审核意见！');
			return;
    	}
    	if(rows[0].throughaudit!=0){
    		tip('该工单数据已审核！');
			return;
    	}
    	$.messager.defaults = {
    			ok : "通过",
    			cancel : "不通过"
    		};
    	$.messager.confirm('提示', '业主意见审核通过?', function(r) {
    		var isok=r?1:2;
    		 $.ajax({
					url : "projectCardController.do?PCthroughAudit&id="+rows[0].id+"&isok="+isok,
					type : 'GET',
					cache : false,
					success : function(data) {
						var d = $.parseJSON(data);
						if (d.success) {
							tip("操作成功！");
							reloadTable();
						}
					}
				});
    	});
    }else{
    	tip('请选择一条记录再审核');
		return;
    }
}

function ow(owurl){ 
	var tmp=window.open("about:blank","","toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no") 
	tmp.moveTo(0,0); 
	tmp.resizeTo(screen.width+20,screen.height); 
	tmp.focus(); 
	tmp.location=owurl; 
	} 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'projectCardController.do?upload', "projectCardList");
}

//导出
function ExportXls() {
	JeecgExcelExport("projectCardController.do?exportXls","projectCardList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("projectCardController.do?exportXlsByT","projectCardList");
}
 </script>