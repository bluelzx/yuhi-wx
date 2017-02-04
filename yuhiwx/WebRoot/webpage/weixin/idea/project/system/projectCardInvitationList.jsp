<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="projectCardInvitationList" fitColumns="false" title="工程单耗材" actionUrl="projectCardInvitationController.do?datagrid&projectcardid=${projectCardid}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="耗材类别" replace="基础耗材_1,人工耗材_2"  field="typeid"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="耗材名称"  field="name"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="基础耗材id"  field="invitationid"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="数量"  field="count"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="单价"  field="price"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="desc"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="工单号"  field="projectcardid"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <%--<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="projectCardInvitationController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="projectCardInvitationController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="projectCardInvitationController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="projectCardInvitationController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="projectCardInvitationController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  --%></t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'projectCardInvitationController.do?upload', "projectCardInvitationList");
}

//导出
function ExportXls() {
	JeecgExcelExport("projectCardInvitationController.do?exportXls","projectCardInvitationList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("projectCardInvitationController.do?exportXlsByT","projectCardInvitationList");
}
 </script>