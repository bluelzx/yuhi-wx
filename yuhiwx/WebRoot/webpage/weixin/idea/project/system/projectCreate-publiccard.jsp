<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>DEMO示例</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="projectCreateController.do?doAddPublicforWindow">
	<input  name="pctype" type="hidden" value="1">
	
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="15%" nowrap><label class="Validform_label"> 设施类型: </label></td>
			<td class="value" width="85%">
				<select style="width:45%" onchange="loadselectVlaue(1,$(this).val());" class="inputxt map" name="">
				<option value="">请选择...</option>
				</select>
				<select style="width:50%" class="inputxt map" onchange="settypevalue()">
				<option value="">请选择...</option>
				</select>
				<input style="display: none;height: 1.5em;width: 50%;" name="pcserver" class="inputxt map">
			</td>
		</tr>
		<tr>
			<td align="right" width="15%"><label class="Validform_label"> 保修地点: </label></td>
			<td class="value" width="85%">
                <SELECT class="maps"> <option value="">请选择区域...</option>
				</SELECT>
                <SELECT class="maps"> <option value="">请选择楼宇...</option>
				</SELECT>
                <SELECT class="maps"> <option value="">请选择楼层...</option>
				</SELECT>
                <SELECT class="maps" onchange="setpublicplace()"> <option value="" >请选择房号...</option>
				</SELECT>
				<input name="publicplace" type="hidden">	
			</td>
		</tr>
		<tr>
			<td align="right" width="15%"><label class="Validform_label"> 备注: </label></td>
			<td class="value" width="85%"><textarea rows="8" style="width: 100%" name="desc"></textarea></td>
		</tr>
	</table>
</t:formvalid>
<script type="text/javascript">
var elemlength=$(".maps").length;
$(function(){
	
	loadselectVlaue(0,1);
	loadSelectData(0,0,null);//地址联动
	$('.maps').change(function(){ 
   		getNextselect(this);
   	});
});
function settypevalue(){
	var parttype="";
	$(".map").each(function(i){
		parttype+=$(this).find("option:selected").text();
	});
	$("[name=pcserver]").val(parttype);
}
function setpublicplace(){
	var partdesc="";
	$(".maps").each(function(i){
		partdesc+=$(this).find("option:selected").text();
	});
	$("[name=publicplace]").val(partdesc);
}
function getNextselect(elem){
	var parentid =$(elem).val();
	if(!parentid)return;
	var index=parseInt($(".maps").index($(elem)))+1
	loadSelectData(index,parentid,null);
}
function loadSelectData(index,parentid,after){
    $selects=$(".maps").eq(index);
    if(!$selects)return;
    //projectInvitationController.do?getInvitationbyParentid&parentsid="+parentid
    $.getJSON("<%=basePath%>projectPartController.do?getPartbyParentid&parentsid="+parentid,"",function(data){
       if(data.resultList.length<=0)$selects.find("option:eq(0)").text("无更多选择！");
       else $selects.find("option:eq(0)").text("请选择...");
    	for(var i=0;i<data.resultList.length;i++){
          $selects.append("<option value='"+data.resultList[i].id+"'>"+data.resultList[i].region+"</option>");
        }
       if(after&&typeof after ==='function')after();
    });
}

function loadselectVlaue(index,parentsid){
	//首框选择联动
	if(index==1&&parentsid=="other"){
		$(".map").eq(index).hide();
		$(".map").eq(2).show();
		return;
	}
	$select=$(".map").eq(index);
	if(index==1){
		$select.show();
		$(".map").eq(2).hide();
	}
	$select.find("option").remove(); 
	if(!$select)return;
    $.getJSON('<%=basePath%>projectCreateController.do?getInvitationbyParentid&parentsid='+parentsid,"",function(data){
    	result=data.resultList;
    	$select.append('<option value="">请选择...</option>');
    	for(var i=0;i<data.resultList.length;i++){
          $select.append("<option value='"+data.resultList[i].id+"'>"+data.resultList[i].iname+"</option>");
        }
    	if(index==0)$select.append('<option value="other">其他类</option>');
    });

}



</script>
</body>