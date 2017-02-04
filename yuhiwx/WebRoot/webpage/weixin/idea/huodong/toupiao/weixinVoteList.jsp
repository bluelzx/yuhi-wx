<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinVoteList" checkbox="true" fitColumns="false" title="投票表" actionUrl="weixinVoteController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd hh:mm:ss" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="累计参与人数"  field="totalperson"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="活动说明"  field="explains"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="累计总票数"  field="totalpicket"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="投票标题"  field="title"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人id"  field="accountid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinVoteController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="weixinVoteController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinVoteController.do?goUpdatevote" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看选项" icon="icon-search" url="weixinVoteController.do?goUpdateitem" funname="detail"></t:dgToolBar>
  <t:dgToolBar title="查看结果" icon="icon-search" url="weixinVoteController.do?showresult" funname="detail"></t:dgToolBar>
  
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/aaa/weixinVoteList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#weixinVoteListtb").find("input[name='createDate']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinVoteController.do?upload', "weixinVoteList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinVoteController.do?exportXls","weixinVoteList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinVoteController.do?exportXlsByT","weixinVoteList");
}
 </script>