<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title></title>
  <t:base type="jquery,easyui,tools"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">

  <t:formvalid formid="eve" dialog="true" usePlugin="password" layout="table" action="weixinVoteController.do?doAdd">
   <input id="id" name="id" type="hidden" value="${weixinVote.id}">
   <input id="accountid" name="createDate" type="hidden" value="${weixinVote.createDate}">
    <input id="accountid" name="accountid" type="hidden" value="${weixinVote.accountid}">
   <table style="width:600px;" cellpadding="0" cellspacing="1" class="formtable">
     <tr>
     <td align="right">
      <label class="Validform_label">
     	投票标题:
      </label>
     </td>
     <td class="value">
       <input id="templateName" class="inputxt" name="title"  value="${weixinVote.title}" datatype="*" nullmsg="模板名称不能为空！">
      <span class="Validform_checktip">请输入投票标题！</span>
     </td>
    </tr>
    <tr>
     <td align="right">
      <label class="Validform_label">
     	累计总票数:
      </label>
     </td>
     <td class="value">
       <input id="templateName" class="inputxt" name="totalpicket"  value="${weixinVote.totalpicket}" type="number" datatype="*" nullmsg="模板名称不能为空！">
      <span class="Validform_checktip">请输入累计总票数！</span>
     </td>
    </tr>
    <tr>
     <td align="right">
      <label class="Validform_label">
     	参与人数:
      </label>
     </td>
     <td class="value">
       <input id="templateName" class="inputxt" name="totalperson"  value="${weixinVote.totalperson}" type="number" datatype="*" nullmsg="模板名称不能为空！">
      <span class="Validform_checktip">请输入参与人数！</span>
     </td>
    </tr>
    <tr>
     <td align="right">
      <label class="Validform_label">
    	 活动说明:
      </label>
     </td>
     <td class="value">
          <textarea rows="15"  cols="80" name="explains" id="content">${weixinVote.explains}</textarea>
          <br>
      	  <span class="Validform_checktip">请输入 活动说明！</span>
     </td>
    </tr>
   </table>
  </t:formvalid>
   
 </body>