<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;"><%--
  <t:datagrid name="projectInvitationList" checkbox="true" fitColumns="false" title="project_invitation" actionUrl="projectInvitationController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="品名"  field="name"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="单价"  field="price"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="备注"  field="desc"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="收费标准描述"  field="priceDesc"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="parentsid"  field="parentsid"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="projectInvitationController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="projectInvitationController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="projectInvitationController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="projectInvitationController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="projectInvitationController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  --%>
 <div id="p" class="easyui-panel" title="耗材列表"     
        style="background:#fafafa;"   
        data-options="iconCls:'icon-save',closable:false,fit:true,    
                collapsible:false,minimizable:false,maximizable:false">   
       <table id="tt"></table>
</div>  
<div id="tb">
	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">添加耗材</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="update()">修改耗材</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除耗材</a>
</div>
  </div>
 </div>
 <script type="text/javascript">
 
 $('#tt').treegrid({
	 	fit:true,
	   	url:'projectInvitationController.do?departgrid',
	    idField:'id',
	    treeField:'text',
	    toolbar:'#tb',
	    lines : true,  
	    striped : true,  
	    checkOnSelect : true,  
	     collapsible : true,  
	     showFooter : true,  
	     rownumbers : true,  
	     pagination : false,  
	     nowarp : false,  
	     border : false,
	     nowrap: false,
	     rownumbers: true,
	     animate: false,  
	     autoRowHeight:false,  
	      collapsible: false,  
	      fitColumns: true,  
	      rownumbers : true,  
	      singleSelect : true,//需设置
	     loadMsg:'数据加载中请稍后……',
	     onBeforeLoad:function(row,param){
	       	 if(row&&row.id)$(this).treegrid('options').url='projectInvitationController.do?departgrid&parentid='+row.id;
	       	 else $(this).treegrid('options').url='projectInvitationController.do?departgrid&parentid=1';
	     	return true; 
	     },
       // onLoadSuccess:function(){$('#tt').treegrid('collapseAll');},
	    columns:[[
			{title:'耗材名',field:'text',width:180},
			{title:'收费标准',field:'priceDesc',width:120},
			{title:'价格',field:'price',width:120},
			{title:'备注',field:'desc',width:80}
	    ]]
	    
	});
 
 
 function add(){
	 var node=$('#tt').treegrid('getSelected');
	 if(node){
		 createwindow("耗材操作", "projectInvitationController.do?goUpdate&parentsid="+node.id,601,166,function(){
			 reloadtable(); 
		 });    		 
	 }else{
		 createwindow("添加耗材类型", "projectInvitationController.do?goUpdate&parentsid=1",601,166,function(){
			 reloadtable(); 
		 }); 
	 }
 }
 function update(){
	 var node=$('#tt').treegrid('getSelected');
	 if(node){
		 createwindow("耗材编辑", "projectInvitationController.do?goUpdate&id="+node.id,601,166,function(){
			 reloadtable(); 
		 });    		 
	 }else{
		 tip('请选择一个项目');
	 }
 }
 function del(){
	 var node=$('#tt').treegrid('getSelected');
	 if(node){
		 $.dialog.confirm("请谨慎，确定删除工程耗材？",function(r){
    		 if(r){
    				 $.ajax({
    					url : 'projectInvitationController.do?doDel&id='+node.id,
    					type : 'get',
    					cache : false,
    					success : function(data) {
    						var d = $.parseJSON(data);
    						if(d.msg){
    							tip(d.msg);
    						}else if (d.success) {
    							tip('操作成功');
    						}
    						reloadtable();
    					}
    				}); 
    		 }
		 });
		    		 
	 }else{
		 tip('请选择一个项目');
	 }
 }
 function reloadtable(){
	 $('#tt').treegrid('reload');
 }
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'projectInvitationController.do?upload', "projectInvitationList");
}

//导出
function ExportXls() {
	JeecgExcelExport("projectInvitationController.do?exportXls","projectInvitationList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("projectInvitationController.do?exportXlsByT","projectInvitationList");
}
 </script>