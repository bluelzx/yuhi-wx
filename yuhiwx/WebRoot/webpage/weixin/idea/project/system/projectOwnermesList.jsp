<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="projectOwnermesList" checkbox="true" fitColumns="false" title="园区通告模板" actionUrl="projectOwnermesController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改人名称"  field="updateName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改日期"  field="updateDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="标题"  field="title"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="内容"  field="content"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="上传模板"  field="templateword"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="模板页面"  field="templatehtml"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="projectOwnermesController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="projectOwnermesController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="projectOwnermesController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="projectOwnermesController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="预览页面" icon="icon-search"  funname="showpage"></t:dgToolBar>
   <t:dgToolBar title="文档模板下载" icon="icon-putout" funname="exporttemplate"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/project/projectOwnermesList.js"></script>		
 <script type="text/javascript">
 function exporttemplate(){
	 openwindow("http://mt.yuhi.com.cn/yuhiwx/MesTemplate/物业通知通告模板.doc");    
 }
 //查看耗材
 function showpage(){
	 var rows = $("#projectOwnermesList").datagrid('getSelections');
     if (rows.length > 0&&rows.length==1) {
    	 openwindow("http://mt.yuhi.com.cn/yuhiwx/MesTemplate"+rows[0].templateword+".html");    		 
     }else{
    	 tip("请选择一条数据");
     }
	 
 }
 function openwindow(aa)
 {
     return window.open(aa,'newindow','height=600,width=900,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
      
 }
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'projectOwnermesController.do?upload', "projectOwnermesList");
}

//导出
function ExportXls() {
	JeecgExcelExport("projectOwnermesController.do?exportXls","projectOwnermesList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("projectOwnermesController.do?exportXlsByT","projectOwnermesList");
}
 </script>