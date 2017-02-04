<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="wsGoodsList" checkbox="true" fitColumns="false" title="商品表" actionUrl="weixinShopGoodsController.do?datagrid" idField="id" fit="true" queryMode="group">
 
      <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
  
  
  <t:dgCol title="标题信息"  field="title"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="标题图片"  field="titleImg"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商品详情"  field="descriptions"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商品原价"  field="price"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商品现价"  field="realPrice"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="折扣"  field="sale"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="销售数量"  field="sellCount"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="评价数量"  field="discussCount"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="好评数量"  field="goodCount"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="差评数量"  field="badCount"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="statement"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="上架时间"  field="shelveTime"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="下架时间"  field="removeTime"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
  <%--  <t:dgCol title="快递名称"  field="expressName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="快递费用"  field="expressPrice"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
   <t:dgCol title="卖家ID"  field="sellerId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="区域"  field="part"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinShopGoodsController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add"  funname="readNews()"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinShopGoodsController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinShopGoodsController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinShopGoodsController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "wsGoodsList.js"></script>		
 <script type="text/javascript">
 
 function readNews(){
	var url = "weixinShopGoodsController.do?goAdd";
	createwindow('图文编辑','weixinShopGoodsController.do?goAdd','newstemplatelist','100%', '100%');
}
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinShopGoodsController.do?upload', "wsGoodsList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinShopGoodsController.do?exportXls","wsGoodsList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinShopGoodsController.do?exportXlsByT","wsGoodsList");
}
 </script>