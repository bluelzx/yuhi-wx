<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>DEMO示例</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link href="<%=basePath %>plug-in/Validform/css/tablefrom.css" rel="stylesheet">
</head>
<body style="overflow-y: hidden"  scroll="no">
<form dialog="true"  layout="table">
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 名称: </label></td>
			<td class="value"><input class="inputxt" name="openid" value="${projectPersonPage.name}" > <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 区域: </label></td>
			<td class="value"><input class="inputxt" type="datetime" name="createTime"  value="${projectPersonPage.partdesc }"  ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 手机: </label></td>
			<td class="value"><input class="inputxt" name="openid" value="${projectPersonPage.phone}" > <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 技能: </label></td>
			<td class="value"><input class="inputxt" name="openid" value="${projectPersonPage.skill}" > <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 可用: </label></td>
			<td class="value"><input class="inputxt" name="createTime"  value="<c:if test='${projectPersonPage.isok==1}'>可用</c:if><c:if test='${projectPersonPage.isok==2}'>禁用</c:if>"  ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
	</table>
</form>
</body>
</html>