<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="syvscribeusersList" checkbox="true" fitColumns="false" title="关注用户" actionUrl="syvscribeusersController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="城市"  field="city"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="头像"  field="headimgurl"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="语言"  field="language"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="国家"  field="country"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="分组id"  field="groupid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="昵称"  field="nickname"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="地区"  field="province"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
  <%--  <t:dgCol title="备注"  field="remark"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="关注时间"  field="subscribetime"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="关注"  field="subscribe"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="性别"  field="sex"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
<%--    <t:dgToolBar title="录入" icon="icon-add" url="syvscribeusersController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="syvscribeusersController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="syvscribeusersController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="syvscribeusersController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "syvscribeusersList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'syvscribeusersController.do?upload', "syvscribeusersList");
}

//导出
function ExportXls() {
	JeecgExcelExport("syvscribeusersController.do?exportXls","syvscribeusersList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("syvscribeusersController.do?exportXlsByT","syvscribeusersList");
}
 </script>