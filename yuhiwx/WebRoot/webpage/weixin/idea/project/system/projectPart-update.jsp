<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link href="<%=basePath %>plug-in/Validform/css/tablefrom.css" rel="stylesheet">
</head>
<body style="overflow-y: hidden"  scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="projectPartController.do?doUpdate">
	<input id="id" name="id" type="hidden" value="${projectPartPage.id }">
	<input  name="parentsid" type="hidden" value="${projectPartPage.parentsid }">
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right"><label class="Validform_label"> 地点: </label></td>
			<td class="value"><input class="inputxt" style="margin-top: 10px;width: 100%;height: 100%" name="region"  value="${projectPartPage.region }"  ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 备注: </label></td>
			<td class="value"><textarea name="descs" style="margin-top: 17px;width: 100%;height: 100%" rows="6" cols="">${projectPartPage.descs}</textarea> <span class="Validform_checktip"></span></td>
		</tr>
	</table>
</t:formvalid>
</body>
</html>