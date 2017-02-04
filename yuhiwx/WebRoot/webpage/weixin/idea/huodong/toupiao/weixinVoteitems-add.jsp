<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title></title>
  <t:base type="jquery,easyui,tools"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">

  <t:formvalid formid="eve" dialog="true" usePlugin="password" layout="table" action="weixinVoteItemsController.do?doAdd">
   <input id="id" name="id" type="hidden" value="${weixinVoteItems.id}">
    <input id="accountid" name="voteid" type="hidden" value="${voteid}">
   <table style="width:600px;" cellpadding="0" cellspacing="1" class="formtable">
     <tr>
     <td align="right">
      <label class="Validform_label">
     	图片连接:
      </label>
     </td>
     <td class="value">
       <input id="templateName" class="inputxt" name="src"  value="${weixinVoteItems.src}" datatype="*" nullmsg="模板名称不能为空！">
      <span class="Validform_checktip">图片连接！</span>
     </td>
    </tr>
    <tr>
     <td align="right">
      <label class="Validform_label">
     	编号:
      </label>
     </td>
     <td class="value">
       <input id="templateName" class="inputxt" name="orderid"  value="${weixinVoteItems.orderid}" type="number" datatype="*" nullmsg="模板名称不能为空！">
      <span class="Validform_checktip">请输入编号！</span>
     </td>
    </tr>
    <tr>
     <td align="right">
      <label class="Validform_label">
     	票数:
      </label>
     </td>
     <td class="value">
       <input id="templateName" class="inputxt" name="piaocount"  value="${weixinVoteItems.piaocount}" type="number" datatype="*" nullmsg="模板名称不能为空！">
      <span class="Validform_checktip">请输入票数！</span>
     </td>
    </tr>
   </table>
  </t:formvalid>
   
 </body>