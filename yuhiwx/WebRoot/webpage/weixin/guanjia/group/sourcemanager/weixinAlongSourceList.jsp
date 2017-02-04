<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinAlongSourceList" checkbox="true" fitColumns="false" title="weixin_along_source" actionUrl="groupSendMes.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="mediaId"  field="mediaId"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="type"  field="type"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
     <t:dgCol title="name"  field="name"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="createDate"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgToolBar title="录入素材" icon="icon-add" url="groupSendMes.do?goAdd" funname="add"></t:dgToolBar>
    <t:dgToolBar title="录入图文素材" icon="icon-add" url="groupSendMes.do?goAddPicOrText" funname="add" ></t:dgToolBar>
   <t:dgToolBar  title="删除" icon="icon-remove" url="groupSendMes.do?doDel" funname="deleteALLSelect02" />
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#weixinAlongSourceListtb").find("input[name='createDate_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#weixinAlongSourceListtb").find("input[name='createDate_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'groupSendMes.do?upload', "weixinAlongSourceList");
}

//导出
function ExportXls() {
	JeecgExcelExport("groupSendMes.do?exportXls","weixinAlongSourceList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("groupSendMes.do?exportXlsByT","weixinAlongSourceList");
}
function del(){

}
 </script>