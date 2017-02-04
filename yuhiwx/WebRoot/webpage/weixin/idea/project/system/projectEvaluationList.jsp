<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="projectEvaluationList" checkbox="true" fitColumns="false" title="评价与回访" actionUrl="projectEvaluationController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="评价内容"  field="content"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="工程单id"  field="cardid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="1评价2回访"  field="type"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="发起人id"  field="openid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="projectEvaluationController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="projectEvaluationController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="projectEvaluationController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="projectEvaluationController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="projectEvaluationController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/project/projectEvaluationList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'projectEvaluationController.do?upload', "projectEvaluationList");
}

//导出
function ExportXls() {
	JeecgExcelExport("projectEvaluationController.do?exportXls","projectEvaluationList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("projectEvaluationController.do?exportXlsByT","projectEvaluationList");
}
 </script>