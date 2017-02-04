<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>新增素材</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<!-- <link type="text/css" rel="stylesheet"
	href="plug-in/weixin/css/appmsg_edit.css" />
<link type="text/css" rel="stylesheet"
	href="plug-in/weixin/css/jquery.fileupload.css" />
<link type="text/css" rel="stylesheet"
	href="plug-in/bootstrap/css/bootstrap.min.css" />
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>

fileupload
<script type="text/javascript"
	src="plug-in/weixin/js/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" src="plug-in/weixin/js/load-image.min.js"></script>
<script type="text/javascript"
	src="plug-in/weixin/js/jquery.fileupload.js"></script>
<script type="text/javascript"
	src="plug-in/weixin/js/jquery.fileupload-process.js"></script>
<script type="text/javascript"
	src="plug-in/weixin/js/jquery.fileupload-image.js"></script>
<script type="text/javascript"
	src="plug-in/weixin/js/jquery.iframe-transport.js"></script>
UEditor
<script type="text/javascript" charset="utf-8"
	src="plug-in/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="plug-in/ueditor/ueditor.all.min.js"></script>
 -->

</head>
<body>
	<div class="main_bd">
		<div class="media_edit_area" id="js_appmsg_editor">
			<div class="appmsg_editor" style="margin-top: 0px;">
				<div class="inner" style="width: 100%">
					<t:formvalid formid="formobj" dialog="false"
				 layout="table" action="">
					<!-- <form   action="" method="post" enctype="multipart/form-data"> -->
						<table cellpadding="0" cellspacing="1" class="formtable">
						<tr>
								<td align="center" colspan="2"><img width="120px" height="120px" src="${entity.headimgurl}"> </td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										名字: </label></td>
								<td class="value">
								${entity.nickname }
								
								 </td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										性别: </label></td>
								<td class="value">
							<c:choose>
							<c:when test="${entity.sex=='1'}">
							男
							</c:when>
							<c:otherwise>女</c:otherwise>
							</c:choose>
							</td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										国家: </label></td>
								<td class="value">
								${entity.country }
								 </td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										城市: </label></td>
								<td class="value">${entity.city }
								
								 </td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										地区: </label></td>
								<td class="value">${entity.province }
								 </td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										关注时间: </label></td>
								<td class="value">${entity.subscribetime }
								 </td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										是否关注: </label></td>
								<td class="value">
							<c:choose>
							<c:when test="${entity.subscribe=='1'}">
							是
							</c:when>
							<c:otherwise>否</c:otherwise>
							</c:choose>
							</td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										区域: </label></td>
								<td class="value">
							<c:choose>
							<c:when test="${entity.partid==null}">
								用户还未进行绑定
							</c:when>
							<c:otherwise>${entity.partid}</c:otherwise>
							</c:choose>
							</td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										手机号码: </label></td>
								<td class="value">
							<c:choose>
							<c:when test="${entity.phone==null}">
								用户还未进行绑定
							</c:when>
							<c:otherwise>${entity.phone}</c:otherwise>
							</c:choose>
							</td>
							</tr>
						</table>
						</t:formvalid>
				</div>
				<i class="arrow arrow_out" style="margin-top: 0px;"></i> <i
					class="arrow arrow_in" style="margin-top: 0px;"></i>
			</div>
		</div>
	</div>
</body>