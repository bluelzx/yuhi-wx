<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>群发文本</title>
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
UEditor
<script type="text/javascript" charset="utf-8"
	src="plug-in/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="plug-in/ueditor/ueditor.all.min.js"></script> -->

<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<!-- <script type="text/javascript" charset="utf-8"
	src="plug-in/ueditor/lang/zh-cn/zh-cn.js"></script> -->
	<script type="text/javascript"
	src="plug-in/forum/curstrom.js"></script>
</head>
<body>
	<div class="main_bd">
		<div class="media_edit_area" id="js_appmsg_editor">
			<div class="appmsg_editor" style="margin-top: 0px;">
				<div class="inner">
					<t:formvalid formid="formobj" dialog="true" usePlugin="password"
						layout="table" action="groupSendMes.do?sendMesBytext">
						<input id="id" name="id" type="hidden"
							value="${cmsArticlePage.id }">
						<input type="hidden" name="accountid"
							value="${cmsArticlePage.accountid}">
						<input type="hidden" name="imageName" id="imageName"
							value="${cmsArticlePage.imageName}">
						<table cellpadding="0" cellspacing="1" class="formtable">
							<tr>
								<td align="right"><label class="Validform_label">
										接受人: </label></td>
								<td class="value">
								<input  type="hidden" 	name="openid">
								<input  type="checkbox" onchange="choiceAll(this,'openid')">全选</input>
								<c:forEach items="${userlist}" var="user">
								<input  type="checkbox" id="openid"	name="openid" value="${user.id}" >${user.nickname }</input>
								</c:forEach>
								 <span	class="Validform_checktip"></span> <label
									class="Validform_label" style="display: none;">标题</label></td>
							</tr>
							
							<tr>
								<td align="right"><label class="Validform_label">
										文本模板: </label></td>
								<td class="value">
										<select name="texttemple">
											<c:forEach items="${textlist }" var="text">
													<option value="${text.templateName},${text.accountId}">
													${text.templateName}
													</option>
											</c:forEach>
										</select>
								<span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">文本模板</label>
								</td>
							</tr>
							
							
								<tr>
								<td></td>
								<td align="left">
								<input type="submit">
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