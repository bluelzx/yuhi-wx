<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>群发文本</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript"
	src="plug-in/forum/curstrom.js"></script>
</head>
<body>
	<div class="main_bd">
		<div class="media_edit_area" id="js_appmsg_editor">
			<div class="appmsg_editor" style="margin-top: 0px;">
				<div class="inner">
					<t:formvalid formid="formobj" dialog="true" usePlugin="password"
						layout="table" action="groupSendMes.do?sendMesByOther">
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
										类型: </label></td>
								<td class="value">
										<select name="type" onchange="RefreshSource(this.value)">
													<option value="IMAGE">图片</option>
													<option value="VOICE">声音</option>
													<option value="VIDEO">视频</option>
													<option value="THUMB">缩略图</option>
													<option value="NEWS">图文</option>
										</select>
								<span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">文本模板</label>
								</td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										图片模板: </label></td>
								<td class="value">
										<select name="picindex" id="picindex">
											<%-- <c:forEach items="${piclist}" var="text"> --%>
													<%-- <option value="${text.mediaId}">
													${text.name}
													</option> --%>
											<%-- </c:forEach> --%>
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
<script type="text/javascript">


		//checkbox ajaxtask(getTypedata)
		function RefreshSource(typename){
			    	sendAjaxRequest("groupSendMes.do?gettypedata&typename="+typename,function(data){
			    		data=eval(data);
			    		var ul = document.getElementById("picindex");
			    		ul.innerHTML="";
			    		for(var i=0;i<data.length;i++){
			    		 var option=document.createElement("option");  
   						 option.setAttribute("value", data[i][0]);  
   						 option.innerHTML=data[i][1];
   						 ul.appendChild(option);
			    		}
					});
		}
		RefreshSource('IMAGE');
</script>

