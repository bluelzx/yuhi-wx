<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinVoteItemsList" checkbox="true" fitColumns="false" title="投票选择项" actionUrl="weixinVoteItemsController.do?datagrid&voteid=${voteid}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="图片"  field="src"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="名字"  field="name"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="号码"  field="orderid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="票数"  field="piaocount"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinVoteItemsController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="weixinVoteItemsController.do?goAdd&voteid=${voteid}" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinVoteItemsController.do?goUpdate" funname="update"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/aa/weixinVoteItemsList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinVoteItemsController.do?upload', "weixinVoteItemsList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinVoteItemsController.do?exportXls","weixinVoteItemsList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinVoteItemsController.do?exportXlsByT","weixinVoteItemsList");
}
 </script>