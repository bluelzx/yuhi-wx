<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>新增素材</title>
<t:base type="ckfinder,ckeditor,jquery,easyui,tools,DatePicker"></t:base>
<link rel="stylesheet" type="text/css" href="plug-in/jquery-ui/css/ui-lightness/jquery-ui-1.9.2.custom.min.css">


</head>
<body>
	<div class="main_bd">
		<div class="media_edit_area" id="js_appmsg_editor">
			<div class="appmsg_editor" style="margin-top: 0px;">
				<div class="inner">
				<t:formvalid formid="formobj" dialog="true" usePlugin="password"
				 layout="table" action="groupSendMes.do?doAdd" >
					<!--  <form   action="groupSendMes.do?doAdd" method="post" enctype="multipart/form-data"> -->
						<input type="hidden" id="btn_sub" class="btn_sub">
						<table cellpadding="0" cellspacing="1" class="formtable" style="width: 870px;">
							
							<tr>
								<td align="right"><label class="Validform_label">
										上传素材: </label></td>
								<td class="value">
								 <!-- <input type="file" name="file">  -->
								 <t:ckfinder name="file" uploadType="Files" value="" buttonClass="ui-button" buttonValue="上传素材"></t:ckfinder> 
								 </td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										素材名字: </label></td>
								<td class="value">
								<!-- <input type="name" name="name"> -->
								<input type="text" name="name"  datatype="*" errormsg="该字段不为空"> <span class="Validform_checktip"></span>
								 </td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										素材类型: </label></td>
								<td class="value">
										 <select name="sourcetype">
										<option value="IMAGE">图片</option>
										<option value="VOICE">声音</option>
									   <option value="VIDEO">视频</option>
										<option value="THUMB">缩略图</option>
										<option value="NEWS">图文</option>
										</select> 
								<span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">所属分类</label>
								</td>
							</tr>
						</table>
						<!-- </form> -->
					</t:formvalid>
				</div>
				<i class="arrow arrow_out" style="margin-top: 0px;"></i> <i
					class="arrow arrow_in" style="margin-top: 0px;"></i>
			</div>
		</div>
	</div>
</body>