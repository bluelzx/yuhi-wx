<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>DEMO示例</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" layout="table" action="">
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		
		<%--<tr>
			<td align="right"><label class="Validform_label"> 请填写评价内容: </label></td>
			<td class="value">
			<textarea rows="10" name="othercontent" style="height: 100%;width: 100%;"></textarea>
			</td>
		</tr>
		--%><tr>
			<td align="right"><label class="Validform_label"> 请选择服务类型: </label></td>
			<td class="value"><select id="pctype" name="pctype" datatype="*">
					<option value="">请选择</option>
					<c:forEach items="${entity}" var="e">
						<option value="${e.id}">${e.name}</option>
					</c:forEach>
			</select> <span class="Validform_checktip">请选择服务类型</span></td>
		</tr>
	</table>
</t:formvalid>
</body>