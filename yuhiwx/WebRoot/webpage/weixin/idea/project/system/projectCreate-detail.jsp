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
			<td align="right" nowrap><label class="Validform_label"> 发单人: </label></td>
			<td class="value"><input class="inputxt"  style="width:90%" readonly="readonly"  name="openid" value="${projectCreatePage.ownerid}" > <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 房间号: </label></td>
			<td class="value"><input class="inputxt"  style="width:90%" readonly="readonly"   name="createTime"  value="${projectCreatePage.roomid}"  ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 创建日期: </label></td>
			<td class="value"><input class="inputxt"  style="width:90%" readonly="readonly"  type="datetime" name="createTime"  value="${projectCreatePage.create_date}"  ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
			<tr>
			<td align="right" nowrap><label class="Validform_label"> 描述: </label></td>
			<td class="value"><input class="inputxt"  style="width:90%" readonly="readonly"  name="openid" value="${projectCreatePage.descs}" > <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 手机: </label></td>
			<td class="value"><input class="inputxt"  style="width:90%" readonly="readonly"  name="openid" value="${projectCreatePage.phone}" > <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 其他联系方式: </label></td>
			<td class="value"><input class="inputxt"  style="width:90%" readonly="readonly"  name="openid" value="${projectCreatePage.othercontact}" > <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 可入户开始时间: </label></td>
			<td class="value"><input class="inputxt"  style="width:90%" readonly="readonly"  readonly="readonly"  type="text" name="createTime"  value="${projectCreatePage.starttime}"  ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 可入户结束时间: </label></td>
			<td class="value"><input class="inputxt"  style="width:90%" readonly="readonly"  type="text" name="createTime"  value="${projectCreatePage.endtime }"  ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 设备设施问题描述: </label></td>
			<td class="value"><textarea  rows="6"  style="width:90%" readonly="readonly"  cols="">${projectCreatePage.content}</textarea> </td>
		</tr>
	</table>
</t:formvalid>
</body>