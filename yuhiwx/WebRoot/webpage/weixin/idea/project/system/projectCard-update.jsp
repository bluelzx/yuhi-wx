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
			<td align="right" width="15%" nowrap><label class="Validform_label"> 工程单号: </label></td>
			<td class="value" width="85%">
				<input id="id" class="inputxt" readonly="readonly" name="userName" value="${projectCardPage.id }" datatype="*">
				<span class="Validform_checktip">用户名范围在2~10位字符</span>
			</td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 房间号: </label></td>
			<td class="value"><input class="inputxt"   readonly="readonly"   name="createTime"  value="${projectCardPage.roomid}"  ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 服务地点: </label></td>
			<td class="value"><input class="inputxt" readonly="readonly"  name="publicplace" value="${projectCardPage.publicplace}"  ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 工程单类型: </label></td>
			<td class="value"><input class="inputxt" readonly="readonly"  name="publicplace" value="${projectCardPage.pctype}"  ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 服务类型: </label></td>
			<td class="value"><input class="inputxt" readonly="readonly"  name="publicplace" value="${projectCardPage.pcserver}"  ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 手机号码: </label></td>
			<td class="value"><input class="inputxt" readonly="readonly"  name="mobilePhone" value="${projectCardPage.phone}"  ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 其他联系方式: </label></td>
			<td class="value"><input class="inputxt" readonly="readonly"  name="otherContact" value="${projectCardPage.otherContact}"  ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 创建日期: </label></td>
			<td class="value"><input name="createDate" readonly="readonly"  class="Wdate"  style="width: 150px"
				value="${projectCardPage.create_time }" errormsg="日期格式不正确!" ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 分配时间: </label></td>
			<td class="value"><input name="createDate" readonly="readonly"  class="Wdate"  style="width: 150px"
				value="${projectCardPage.distribution_time }" errormsg="日期格式不正确!" ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 接单时间: </label></td>
			<td class="value"><input name="createDate" readonly="readonly"  class="Wdate"  style="width: 150px"
				value="${projectCardPage.get_time }" errormsg="日期格式不正确!" ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 办理时间: </label></td>
			<td class="value"><input name="createDate" readonly="readonly"  class="Wdate"  style="width: 150px"
				value="${projectCardPage.handle_time }" errormsg="日期格式不正确!" ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 结束时间: </label></td>
			<td class="value"><input name="createDate" readonly="readonly"  class="Wdate"  style="width: 150px"
				value="${projectCardPage.finished_time }" errormsg="日期格式不正确!" ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
	</table>
</t:formvalid>
</body>