<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>DEMO示例</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="jeecgDemoController.do?save">
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 称呼: </label></td>
			<td class="value"><input class="inputxt" name="openid" value="${owner.NAME}" > <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 性别: </label></td>
			<td class="value"><input class="inputxt" name="createTime"  value='<c:if test="${owner.SEX==1}">可用</c:if><c:if test="${owner.sex==0}">禁用</c:if>' ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 手机: </label></td>
			<td class="value"><input class="inputxt" name="openid" value="${owner.PHONE}" > <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 所属地区: </label></td>
			<td class="value"><input class="inputxt" name="openid" value="${owner.PARTDESC}" > <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 可用: </label></td>
			<td class="value"><input class="inputxt" name="openid" value='<c:if test="${owner.ISOK==1}">可用</c:if><c:if test="${owner.isok==0}">禁用</c:if>' > <span class="Validform_checktip"></span></td>
		</tr>
	</table>
</t:formvalid>
</body>