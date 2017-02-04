<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>DEMO示例</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" layout="table" action="ownerController.do?sendMesforOwner">
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right"><label class="Validform_label"> 公告模板: </label></td>
			<td class="value"><select id="depId" name="templateid" datatype="*">
				<c:forEach items="${ProjectOwnermes}" var="mes">
					<option value="${mes.id }" >${mes.title}</option>
				</c:forEach>
			</select> <span class="Validform_checktip">请选择公告</span></td>
		</tr>
	</table>
</t:formvalid>
</body>