<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinShopShopcarList" checkbox="true" fitColumns="false" title="weixin_shop_shopcar" actionUrl="weixinShopShopcarController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="商品id"  field="wsGoodsId"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="修改日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="单价"  field="wsGoodsPrice"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="数量"  field="wsGoodsCount"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="买家名称"  field="userid"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgToolBar title="查看购物车" icon="icon-search" url="weixinShopShopcarController.do?goUpdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/aaaa/weixinShopShopcarList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#weixinShopShopcarListtb").find("input[name='createDate_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#weixinShopShopcarListtb").find("input[name='createDate_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinShopShopcarController.do?upload', "weixinShopShopcarList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinShopShopcarController.do?exportXls","weixinShopShopcarList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinShopShopcarController.do?exportXlsByT","weixinShopShopcarList");
}
 </script>