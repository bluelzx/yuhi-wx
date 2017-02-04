<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="jeecgDemoController.do?save">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
        <tr>
            <td align="right" nowrap><label class="Validform_label"> 签到人: </label></td>
            <td class="value"><input class="inputxt" name="openid" value="${projectSignPage.openid}" > <span class="Validform_checktip"></span></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 签到时间: </label></td>
            <td class="value"><input class="inputxt" type="datetime" name="createTime"  value="<fmt:formatDate value='${projectSignPage.createTime }' type="date" pattern="yyyy-MM-dd"/>"  ignore="ignore"> <span class="Validform_checktip"></span></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 1上班2下班: </label></td>
            <td class="value"><input class="inputxt" name="type" value='<c:if test="${projectSignPage.type==1}">上班</c:if><c:if test="${projectSignPage.type==2}">下班</c:if>' > <span class="Validform_checktip"></span></td>
        </tr>
        
    </table>
</t:formvalid>
</body>