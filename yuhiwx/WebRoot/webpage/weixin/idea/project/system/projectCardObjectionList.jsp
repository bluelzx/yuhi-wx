<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="projectCardObjectionList" checkbox="true" fitColumns="false" title="project_card_objection" actionUrl="projectCardObjectionController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="1.情况属实2.维修及格3.维修不及格4.自然损耗5.其他原因"  field="status"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="1.物业方面意见（单张未开始）2.保修方面意见3.物业验收意见4.公司验收意见"  field="typeid"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="其他补充"  field="othercontent"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createtime" formatter="yyyy-MM-dd" hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="关联工单号"  field="projectcardid"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作人"  field="usercode"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="projectCardObjectionController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="projectCardObjectionController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="projectCardObjectionController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="projectCardObjectionController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="projectCardObjectionController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/project/projectCardObjectionList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#projectCardObjectionListtb").find("input[name='createtime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#projectCardObjectionListtb").find("input[name='createtime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'projectCardObjectionController.do?upload', "projectCardObjectionList");
}

//导出
function ExportXls() {
	JeecgExcelExport("projectCardObjectionController.do?exportXls","projectCardObjectionList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("projectCardObjectionController.do?exportXlsByT","projectCardObjectionList");
}
 </script>