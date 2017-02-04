<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="projectSignList" checkbox="true" fitColumns="false" title="签到记录表1" actionUrl="projectSignController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="签到人名称"  query="true"  field="openid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="签到时间"  field="createTime"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="签到状态"  style="background-color:green;_1,background-color:red;_2" field="type" dictionary="type" replace="签到_1,签退_2" query="true"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="projectSignController.do?doDel&id={id}" />
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="projectSignController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="projectSignController.do?goUpdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'projectSignController.do?upload', "projectSignList");
}

//导出
function ExportXls() {
	JeecgExcelExport("projectSignController.do?exportXls","projectSignList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("projectSignController.do?exportXlsByT","projectSignList");
}
 </script>