<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>DEMO示例</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" layout="table" action="projectCardController.do?CallbackprojectCard">
	<input id="id" name="ids" type="hidden" value="${id }">
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		
		<tr>
			<td align="right"><label class="Validform_label"> 请填写回访内容: </label></td>
			<td class="value">
			<textarea rows="10" name="othercontent" style="height: 100%;width: 100%;"></textarea>
			</td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 请选择评价: </label></td>
			<td class="value"><select id="status" name="status" datatype="*">
					<option value="1">情况属实</option>
					<option value="2">维修及格</option>
					<option value="3">维修不及格</option>
					<option value="4">自然损耗</option>
					<option value="5">其他原因</option>
			</select> <span class="Validform_checktip">请选择评价</span></td>
		</tr>
	</table>
</t:formvalid>
</body>