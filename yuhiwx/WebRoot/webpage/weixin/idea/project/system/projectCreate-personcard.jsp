<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>DEMO示例</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style type="text/css">input{line-height: 1.5em;}</style>
</head>
<body style="overflow-y: hidden" scroll="no">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="plug-in/autocompleter/jquery.autocompleter.js"></script>
<t:formvalid formid="formobj" beforeSubmit="validpersonform"  dialog="true" usePlugin="password" layout="table" action="projectCreateController.do?doAddforWindow">
	<input  name="pctype" type="hidden" value="1">
	
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<%--<tr>
			<td align="right" width="15%" nowrap><label class="Validform_label"> 客户名: </label></td>
			<td class="value" width="85%">
				<select class="inputxt" name="ownerid" onchange="changeplace(this)">
				<option>请选择...</option>
				<c:forEach items="${owners}" var="o">
					<option value="${o.id}" placeid="${o.partid}" placedesc="${o.partdesc }" >${o.name}</option>
				</c:forEach>
				</select>
			
			<input class="inputxt" type="text"  name="ownerid" value="" style="width:90%" >
			
			</td>
		</tr>--%>
		<tr>
			<input class="inputxt" type="hidden" id="partid" name="part" value="" >
			<td align="right" nowrap><label class="Validform_label"> 区域: </label></td>
			<td class="value">
				<%--<SELECT class="maps"> <option value="">请选择区域...</option>
				</SELECT>
                <SELECT class="maps"> <option value="">请选择楼宇...</option>
				</SELECT>
                <SELECT class="maps"> <option value="">请选择楼层...</option>
				</SELECT>
                <SELECT class="maps" onchange="setpublicplace()"> <option value="" >请选择房号...</option>
				</SELECT>
				--%>
				<input class="inputxt" type="text" id="tagid" value="" style="width:90%" >
				<input name="roomid" type="hidden">
				<input name="roomsbyid" type="hidden">
			</td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 手机号码: </label></td>
			<td class="value"><input style="width:90%" class="inputxt" type="number" name="phone"  > <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 其他联系方式: </label></td>
			<td class="value"><input class="inputxt" name="otherContact" style="width:90%"   ></span></td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 可入户时间(起): </label></td>
			<td class="value"><input name="starttime"  readOnly="true"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:90%"    pattern="yyyy-MM-dd"/> <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 可入户时间(止): </label></td>
			<td class="value"><input name="endtime" readOnly="true" class="Wdate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
				 pattern="yyyy-MM-dd hh:mm:ss" errormsg="日期格式不正确!" style="width:90%" ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 问题描述: </label></td>
			<td class="value"><textarea rows="6"  style="width:100%"name="content"></textarea></td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 备注（可选）: </label></td>
			<td class="value"><textarea rows="6" style="width:100%" name="descs">768768</textarea></td>
		</tr>
	</table>
</t:formvalid>
<script type="text/javascript">
//autocomplete
//缓存
var cache = {};
$( "#tagid" ).autocomplete({
    source: function(request, response ) {
        var term = request.term;
        if ( term in cache ) {
            response( $.map( cache[ term ], function( item ) {
                return {
                    value:item.region,
                    id:item.id
                }
            }));
            return;
        }
        $.ajax({
            url: "projectPartController.do?autodepart",
            dataType: "json",
            data:{wirter: request.term},
            success: function( data ) {
                cache[ term ] = data;
                response( $.map( data, function( item ) {
                    return {
                        value: item.region,
                        id:item.id
                    }
                }));
            }
        });
    },
    minLength: 1,
    select: function( event, ui ) {
    	$("[name=roomid]").val(ui.item.value);
    	$("[name=roomsbyid]").val(ui.item.id);
    }
});
function validpersonform(){
	debugger
	var roomid=$("[name=roomid]").val();
	var roomsbyid=$("[name=roomsbyid]").val();
	var phone =$("[name=phone]").val();
	var content =$("[name=content]").val();
	if(roomid&&roomsbyid){
	}else{
	alert("请正确填写房间号！");
	return false;
	}
	if(!phone||phone.length!=11){
		alert("手机号码不正确！");
		return false;
	}
	if(!content||content.length<=0){
		alert("问题描述不能为空！");
		return false;
	}
}

function changeplace(e){
	var a=$(e).find("option:selected");
	var placedesc= a.attr("placedesc");
	var placeid= a.attr("placeid");
	$("#partid").val(placeid);
	$("#placedesc").val(placedesc);
}
var elemlength=$(".maps").length;
$(function(){
	//loadSelectData(0,0,null);//地址联动
	//$('.maps').change(function(){ 
   	//	getNextselect(this);
   	//});
});


function setpublicplace(){
	var partdesc="";
	$(".maps").each(function(i){
		partdesc+=$(this).find("option:selected").text();
	});
	$("[name=publicplace]").val(partdesc);
	$("[name=roomid]").val($(".maps:last").val());
}
function getNextselect(elem){
	var parentid =$(elem).val();
	if(!parentid)return;
	var index=parseInt($(".maps").index($(elem)))+1
	loadSelectData(index,parentid,null);
}
function loadSelectData(index,parentid,after){
    $selects=$(".maps").eq(index);
    $selects.empty();
    if(!$selects)return;
    //projectInvitationController.do?getInvitationbyParentid&parentsid="+parentid
    $.getJSON("<%=basePath%>projectPartController.do?getPartbyParentid&parentsid="+parentid,"",function(data){
       if(data.resultList.length<=0)$selects.find("option:eq(0)").text("无更多选择！");
       else $selects.append("<option value=''>请选择...</option>");
    	for(var i=0;i<data.resultList.length;i++){
          $selects.append("<option value='"+data.resultList[i].id+"'>"+data.resultList[i].region+"</option>");
        }
       if(after&&typeof after ==='function')after();
    });
}
</script>
</body>
