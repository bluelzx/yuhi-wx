<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="wsGoodsTypeList" checkbox="true" fitColumns="false" title="商品分类" actionUrl="weixinMallCategoryController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="图标" field="imgurl" image="true"  imageSize="20,70" width="120" ></t:dgCol>
   <t:dgCol title="分类名称"  field="name"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="父分类"  field="parentid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="所属卖家"  field="sellerId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinMallCategoryController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="weixinMallCategoryController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinMallCategoryController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinMallCategoryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinMallCategoryController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "wsGoodsTypeList.js"></script>		
 <script type="text/javascript">
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinMallCategoryController.do?upload', "wsGoodsTypeList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinMallCategoryController.do?exportXls","wsGoodsTypeList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinMallCategoryController.do?exportXlsByT","wsGoodsTypeList");
}
 </script>