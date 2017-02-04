<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;"><%--
  <t:datagrid name="projectPartList" title="project_part" actionUrl="projectPartController.do?departgrid" treegrid="true" idField="departid" fit="true" pagination="false" >
   <t:dgCol title="id"  field="id"  treefield="id" hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="区域"  field="region"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="楼宇"  field="building"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="位置"  field="placeDetail"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="projectPartController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="projectPartController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="projectPartController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="projectPartController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="projectPartController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  --%>
 <div id="p" class="easyui-panel" title="区域列表"     
        style="background:#fafafa;"   
        data-options="iconCls:'icon-save',closable:false,fit:true,    
                collapsible:false,minimizable:false,maximizable:false">   
       <table id="tt"></table>
</div>  
<div id="tb">
	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">添加区域</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="update()">修改区域</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除区域</a>
	<%--<a href="#" id="upload" class="easyui-linkbutton" iconCls="icon-remove" plain="true" href="javascript:void(0);">导入</a>
	--%><input id="upfile" type="file" style="display: none;" name="upfile">
</div>
  </div>
 </div>
 <script type="text/javascript" src="<%=basePath %>plug-in/fileupload/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=basePath %>plug-in/fileupload/Eajaxfileupload.js"></script> 	
 <script type="text/javascript">
 uploadFile("upload",function(data){
	 debugger
		$("#filekey").val(data.msg);
		$("#upmsg").show();
	},"projectPartController.do?importExcel",null,"");
 $('#tt').treegrid({
	   	url:'projectPartController.do?departgrid&parentid=${deptid}',
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
       	 if(row&&row.id)$(this).treegrid('options').url='projectPartController.do?departgrid&parentid='+row.id;
       	 else $(this).treegrid('options').url='projectPartController.do?departgrid&parentid=${deptid}';
     	return true; 
     },
     //onLoadSuccess:function(){$('#tt').treegrid('collapseAll');},
	    columns:[[
			{title:'地区',field:'text',width:300},
			{title:'描述',field:'desc',width:300}
	    ]]
	    
	});
 
//异步上传文件
 function uploadFile(id,callback,url,ElementId,parse) {
 	 $("#"+id).click(function(){
 		var _file=$("#upfile");
 		if(callback===""||callback==null||!typeof callback === "function") {
 			console.log("callback is function and Cannot be empty");
 			return;
 		}
 		$("#upfile").click();
 		_file.change(function(){
 			var _value=_file.val();
 			if(_value){
 				if(parse instanceof Array&&parse.length>=0){
 					var _suffix=_value.substring(_value.lastIndexOf(".")+1); //文件后缀名;
 					if(parse.indexOf(_suffix)<0){
 						alert("请上传后缀为"+parse+"的文件");
 						return;
 					}
 				}
 			 	$.ajaxFileUpload({  
                    url:url==null?"http://localhost:8081/yuhiwx/uploadController/upload.do":url,  
                    secureuri:false,  
                    fileElementId:ElementId==null?"upfile":ElementId,  
                    dataType: 'json',  
                    success:callback,  
                    error: function (s, xml, status, e){  
                        console.info('上传失败:未知异常!');  
                    }  
                }); 
 	 	}; 
 	});
 });
 }
 function add(){
	 var node=$('#tt').treegrid('getSelected');
	 if(node){
		 createwindow("区域操作", "projectPartController.do?goUpdate&parentsid="+node.id,654,194,function(){
			 reloadtable(); 
		 });    		 
	 }else{
		 createwindow("添加楼座", "projectPartController.do?goUpdate&parentsid=${deptid}",654,194,function(){
			 reloadtable(); 
		 }); 
	 }
 }
 function update(){
	 var node=$('#tt').treegrid('getSelected');
	 if(node){
		 createwindow("区域编辑", "projectPartController.do?goUpdate&id="+node.id,654,194,function(){
			 reloadtable(); 
		 });    		 
	 }else{
		 tip('请选择一个项目');
	 }
 }
 function del(){
	 var node=$('#tt').treegrid('getSelected');
	 if(node.text.indexOf("第")!=-1||
		node.text.indexOf("层")!=-1||
		node.text.indexOf("座")!=-1){
		 tip("只能删除房间！");
		 return false;
	 }
	 if(node){
		 $.dialog.confirm("请谨慎，确定删除工程区域？",function(r){
    		 if(r){
    				 $.ajax({
    					url : 'projectPartController.do?doDel&id='+node.id,
    					type : 'get',
    					cache : false,
    					success : function(data) {
    						var d = $.parseJSON(data);
    						if (d.success) {
    							tip('操作成功');
    							reloadtable();
    						}
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
	openuploadwin('Excel导入', 'projectPartController.do?upload', "projectPartList");
}

//导出
function ExportXls() {
	JeecgExcelExport("projectPartController.do?exportXls","projectPartList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("projectPartController.do?exportXlsByT","projectPartList");
}
 </script>