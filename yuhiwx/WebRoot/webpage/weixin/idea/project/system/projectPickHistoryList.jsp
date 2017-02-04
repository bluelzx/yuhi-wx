<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="projectPickHistoryList" checkbox="true" fitColumns="false" title="接据单历史纪录" actionUrl="projectPickHistoryController.do?datagrid&ids=${id}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="工程人员id"  field="ppopenid"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="工程人员名称"  field="name"  hidden="true"  queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="工程单id"  field="pcid"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" replace="拒单_0,接单_1" field="typeid"  hidden="true"  queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="拒单描述"  field="pickdescs"  hidden="true"  queryMode="group"  width="350"></t:dgCol>
   <t:dgCol title="时间"  field="time" hidden="true"  queryMode="group"  width="140"></t:dgCol>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="projectPickHistoryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar><%--
   <t:dgToolBar title="查看" icon="icon-search" url="projectPickHistoryController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
  --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/project/projectPickHistoryList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#projectPickHistoryListtb").find("input[name='time_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#projectPickHistoryListtb").find("input[name='time_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'projectPickHistoryController.do?upload', "projectPickHistoryList");
}

//导出
function ExportXls() {
	JeecgExcelExport("projectPickHistoryController.do?exportXls","projectPickHistoryList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("projectPickHistoryController.do?exportXlsByT","projectPickHistoryList");
}
 </script>