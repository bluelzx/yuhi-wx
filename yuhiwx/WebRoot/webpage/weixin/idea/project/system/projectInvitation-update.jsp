<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link href="<%=basePath %>plug-in/Validform/css/tablefrom.css" rel="stylesheet">
</head>
<body style="overflow-y: hidden"  scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="projectInvitationController.do?doUpdate">
	<input id="id" name="id" type="hidden" value="${projectInvitationPage.id }">
	<input  name="parentsid" type="hidden" value="${projectInvitationPage.parentsid }">
	<input  name="type" type="hidden" value="1">
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 名称: </label></td>
			<td class="value"><input class="inputxt" name="iname" value="${projectInvitationPage.iname}" > <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 价格: </label></td>
			<td class="value"><input class="inputxt" type="datetime" name="price"  value="${projectInvitationPage.price }"  ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 备注: </label></td>
			<td class="value"><input class="inputxt" name="idesc" value="${projectInvitationPage.idesc}" > <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 收费标准描述: </label></td>
			<td class="value"><input class="inputxt" name="priceDesc" value="${projectInvitationPage.priceDesc}" > <span class="Validform_checktip"></span></td>
		</tr>
	</table>
</t:formvalid>
</body>
</html>