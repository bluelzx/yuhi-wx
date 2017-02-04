<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="ownerList" checkbox="true" fitColumns="false" title="园区业主管理" actionUrl="ownerController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="称呼"  field="name"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="手机号"  field="phone"  hidden="true"  queryMode="single"  width="120" query="true"></t:dgCol>
   <t:dgCol title="地区"  field="partid"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="性别" replace="女_0,男_1"   field="sex"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="openid"  field="openid"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否可用" replace="禁用_0,可用_1" style="background-color:green;_1,background-color:red;_0"  field="isok"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="ownerController.do?doDel&id={id}" />
   <t:dgToolBar title="园区通知"  icon="icon-remove"  funname="ParkCircular"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="ownerController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="ownerController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/project/ownerList.js"></script>		
 <script type="text/javascript">
 //园区通知
 function ParkCircular(){
	 var rows = $("#ownerList").datagrid('getSelections');
    createwindow("园区通知", "ownerController.do?ParkCircular",610,40);    		 
 }
 
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'ownerController.do?upload', "ownerList");
}

//导出
function ExportXls() {
	JeecgExcelExport("ownerController.do?exportXls","ownerList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("ownerController.do?exportXlsByT","ownerList");
}
 </script>