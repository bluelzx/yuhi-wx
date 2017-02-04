<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>新增素材</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link type="text/css" rel="stylesheet"
	href="plug-in/weixin/css/appmsg_edit.css" />
<link type="text/css" rel="stylesheet"
	href="plug-in/weixin/css/jquery.fileupload.css" />
<link type="text/css" rel="stylesheet"
	href="plug-in/bootstrap/css/bootstrap.min.css" />
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>

<!--fileupload-->
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
<!--UEditor-->
<script type="text/javascript" charset="utf-8"
	src="plug-in/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="plug-in/ueditor/ueditor.all.min.js"></script>


</head>
<body>
	<div class="main_bd">
		<div class="media_edit_area" id="js_appmsg_editor">
			<div class="appmsg_editor" style="margin-top: 0px;">
				<div class="inner" style="width: 100%">
				<t:formvalid formid="formobj" dialog="true"
				 layout="table" action="groupSendMes.do?uploadMesofpicAndText">
					<!-- <form   action="" method="post" enctype="multipart/form-data"> -->
						<table cellpadding="0" cellspacing="1" class="formtable">
							<tr>
								<td align="right"><label class="Validform_label">
										素材名字: </label></td>
								<td class="value">
								<input type="text" name="name"  datatype="*" errormsg="该字段不为空"> <span class="Validform_checktip"></span>
								
								 </td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										标题: </label></td>
								<td class="value">
								<input type="text" name="title" datatype="*" errormsg="该字段不为空"> <span class="Validform_checktip"></span>
								
								 </td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										作者: </label></td>
								<td class="value">
								<input type="text" name="author" datatype="*" errormsg="该字段不为空"> <span class="Validform_checktip"></span>
								 </td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										图片: </label></td>
								<td class="value">
										<select name="thumb_media_id" style="width: 220px">
										<c:forEach items="${piclist}" var="pic">
										<option value="${pic[0]}">${pic[1]}</option>
										</c:forEach>
										</select>
								<span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">所属分类</label>
								</td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										描述: </label></td>
								<td class="value">
								<input type="text" name="digest" datatype="*" errormsg="该字段不为空"> <span class="Validform_checktip"></span>
								 </td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										页面url: </label></td>
								<td class="value">
								<input type="text" name="content_source_url" datatype="url" errormsg="必须是URL"> <span class="Validform_checktip"></span>
								 </td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										内容: </label></td>
								<td class="value"><textarea name="descriptions" id="content"
										style="width: 100%;height:300px"datatype="*" errormsg="该字段不为空">${cmsArticlePage.content}</textarea> 
										<span class="Validform_checktip"></span>
									<script type="text/javascript">
							        var editor = UE.getEditor('content');
							    </script></td>
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