<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>DEMO示例</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript" src="<%=basePath %>plug-in/fileupload/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=basePath %>plug-in/fileupload/Eajaxfileupload.js"></script> 	
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="projectOwnermesController.do?doUpdate">
	<input id="id" name="id" type="hidden" value="${projectOwnermesPage.id }">
	<input  name="part" type="hidden" value="${projectOwnermesPage.part }">
	<input name="createName" type="hidden" value="${projectOwnermesPage.createName }">
	<input  name="createDate" type="hidden" value="${projectOwnermesPage.createDate }">
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="15%" nowrap><label class="Validform_label"> 标题: </label></td>
			<td class="value">
				<input id="userName" class="inputxt" name="title" style="height: 100%;width: 60%;" value="${projectOwnermesPage.title }" datatype="*">
			</td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 内容: </label></td>
			<td class="value">
			<textarea rows="10" name="content" style="height: 100%;width: 100%;">${projectOwnermesPage.content }</textarea>
			</td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 备注: </label></td>
			<td class="value">
			<textarea rows="10" name="remark" style="height: 100%;width: 100%;">${projectOwnermesPage.remark }</textarea>
			</td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 通过模板: </label></td>
			<td class="value">
			<button id="upload" type="button">word模板上传</button>
			<span id="upmsg" class="Validform_checktip Validform_right" style="display: none;">上传成功!</span>
			<input id="upfile" type="file" style="display: none;" name="upfile">
			<input id="filekey" type="hidden"  name="templateword" value="${projectOwnermesPage.templateword}">
			</td>
		</tr>
	</table>
</t:formvalid>
<script type="text/javascript">
$(function(){
	uploadFile("upload",function(data){
		$("#filekey").val(data.msg);
		$("#upmsg").show();
	},"projectOwnermesController.do?doAddTmpleate",null,"");
});
//异步上传文件
function uploadFile(id,callback,url,ElementId,parse) {
	 $("#"+id).click(function(){
		var _file=$("#upfile");
		if(callback===""||callback==null||!typeof callback === "function") {
			console.log("callback is function and Cannot be empty");
			return;
		}
		$("#upfile").click();
		_file.change(function(){
			var _value=_file.val();
			if(_value){
				if(parse instanceof Array&&parse.length>=0){
					var _suffix=_value.substring(_value.lastIndexOf(".")+1); //文件后缀名;
					if(parse.indexOf(_suffix)<0){
						alert("请上传后缀为"+parse+"的文件");
						return;
					}
				}
			 	$.ajaxFileUpload({  
                   url:url==null?"http://localhost:8081/yuhiwx/uploadController/upload.do":url,  
                   secureuri:false,  
                   fileElementId:ElementId==null?"upfile":ElementId,  
                   dataType: 'json',  
                   success:callback,  
                   error: function (s, xml, status, e){  
                       console.info('上传失败:未知异常!');  
                   }  
               }); 
	 	}; 
	});
});
}

</script>
</body>