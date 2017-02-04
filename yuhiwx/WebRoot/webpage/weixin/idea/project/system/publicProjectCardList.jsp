<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="publicProjectCardList" checkbox="true" fitColumns="false" title="public_project_card" actionUrl="publicProjectCardController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="区域"  field="part"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="repairperson"  field="repairperson"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="place"  field="place"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="desc"  field="descs"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="createtime"  field="createtime" formatter="yyyy-MM-dd" hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="distribution"  field="distribution"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="distributime"  field="distributime"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="gettime"  field="gettime"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="finishtime"  field="finishtime"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="isok"  field="isok"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="other"  field="other"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="state"  field="state"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="reminderstate"  field="reminderstate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="handletime"  field="handletime"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="publicProjectCardController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="publicProjectCardController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="publicProjectCardController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="publicProjectCardController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="publicProjectCardController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/project/publicProjectCardList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#publicProjectCardListtb").find("input[name='createtime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#publicProjectCardListtb").find("input[name='createtime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'publicProjectCardController.do?upload', "publicProjectCardList");
}

//导出
function ExportXls() {
	JeecgExcelExport("publicProjectCardController.do?exportXls","publicProjectCardList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("publicProjectCardController.do?exportXlsByT","publicProjectCardList");
}
 </script>