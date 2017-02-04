<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="projectPersonList" checkbox="true" fitColumns="false" title="工程人员表" actionUrl="projectPersonController.do?datagrid&isok=${isok}&part=${part}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="名称"  field="name"  hidden="true"  align="center" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="区域"  field="part"  hidden="false"  align="center" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="区域"  field="partdesc"  hidden="true"  align="center" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="手机"  field="phone"  hidden="true"  align="center" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="技能"  field="skill"  hidden="true"  align="center" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="openid"  field="openid"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="可用"  field="isok" align="center" style="background-color:green;_1,background-color:red;_2"  hidden="true" replace="可用_1,禁用_2"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="解绑"  url="projectPersonController.do?doDel&id={id}" />
   <t:dgToolBar title="启用/禁用" icon="icon-add" url="projectPersonController.do?resetstate" funname="resetstate"></t:dgToolBar>
   <t:dgToolBar title="批量解绑"  icon="icon-remove" url="projectPersonController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="projectPersonController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/project/projectPersonList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 function getpardPersonOpenid(){
	 var rows = $("#projectPersonList").datagrid('getSelections');
	    if (rows.length > 0&&rows.length==1) {
	    	return rows[0].openid+","+rows[0].name+","+rows[0].phone;
	    }else{
	    	tip("请选择一条需要操作的数据");
	    }
 }
 function resetstate(title,url,gname) {
		gridname=gname;
	    var rows = $("#"+gname).datagrid('getSelections');
	    if (rows.length > 0&&rows.length==1) {
	    	$.dialog.confirm('你确定操作该数据吗?', function(r) {
			   if (r) {
					$.ajax({
						url : url,
						type : 'post',
						data : {
							id : rows[0].id
						},
						cache : false,
						success : function(data) {
							var d = $.parseJSON(data);
							if (d.success) {
								var msg = d.msg;
								tip(msg);
								reloadTable();
								$("#"+gname).datagrid('unselectAll');
								id='';
							}
						}
					});
				}
			});
		} else {
			tip("请选择需要操作的数据");
		}
	}
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'projectPersonController.do?upload', "projectPersonList");
}

//导出
function ExportXls() {
	JeecgExcelExport("projectPersonController.do?exportXls","projectPersonList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("projectPersonController.do?exportXlsByT","projectPersonList");
}
 </script>