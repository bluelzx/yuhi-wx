<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>DEMO示例</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="jeecgDemoController.do?save">
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="15%" nowrap><label class="Validform_label"> 设备号: </label></td>
			<td class="value" width="85%">
				<input id="userName" disabled="disabled" class="inputxt" name="device_info" datatype="*">
			</td>
		</tr>
			<tr>
			<td align="right" width="15%" nowrap><label class="Validform_label"> 是否关注 </label></td>
			<td class="value" width="85%">
				<input id="userName" disabled="disabled" class="inputxt" name="is_subscribe" datatype="*">
			</td>
		</tr>
			<tr>
			<td align="right" width="15%" nowrap><label class="Validform_label"> 交易类型: </label></td>
			<td class="value" width="85%">
				<input id="userName" disabled="disabled" class="inputxt" name="trade_type" datatype="*">
			</td>
		</tr>
			<tr>
			<td align="right" width="15%" nowrap><label class="Validform_label"> 设备号: </label></td>
			<td class="value" width="85%">
				<input id="userName" disabled="disabled" class="inputxt" name="device_info" datatype="*">
			</td>
		</tr>
			<tr>
			<td align="right" width="15%" nowrap><label class="Validform_label"> 付款银行: </label></td>
			<td class="value" width="85%">
				<input id="userName" disabled="disabled" class="inputxt" name="bank_type" datatype="*">
			</td>
		</tr>
		
			<tr>
			<td align="right" width="15%" nowrap><label class="Validform_label"> 订单金额(元): </label></td>
			<td class="value" width="85%">
				<input id="userName" disabled="disabled" class="inputxt" name="total_fee" datatype="*">
			</td>
		</tr>
			<tr>
			<td align="right" width="15%" nowrap><label class="Validform_label"> 货币种类: </label></td>
			<td class="value" width="85%">
				<input id="userName" disabled="disabled" class="inputxt" name="fee_type" datatype="*">
			</td>
		</tr>
			<tr>
			<td align="right" width="15%" nowrap><label class="Validform_label"> 现金支付金额(元): </label></td>
			<td class="value" width="85%">
				<input id="userName" disabled="disabled" class="inputxt" name="cash_fee" datatype="*">
			</td>
		</tr>
			<tr>
			<td align="right" width="15%" nowrap><label class="Validform_label"> 现金支付货币类型: </label></td>
			<td class="value" width="85%">
				<input id="userName" disabled="disabled" class="inputxt" name="fee_type" datatype="*">
			</td>
		</tr>
			<tr>
			<td align="right" width="15%" nowrap><label class="Validform_label"> 支付完成时间: </label></td>
			<td class="value" width="85%">
				<input id="userName" disabled="disabled" class="inputxt" name="time_end" datatype="*">
			</td>
		</tr>
	</table>
	</t:formvalid>
</body>
<script type="text/javascript">
		$(function(){
			$.ajax({
				url:'<%=basePath%>wechatPay/checkPayDetail.do?orderid=${id}',
				success:function(data){
					debugger
					data=eval("("+data+")");	
					setvalue(data);
				}
			});
		});
		function setvalue(data){
			if(data.is_subscribe=="Y"){
				$("[name=is_subscribe]").val("是");
			}else{
				$("[name=is_subscribe]").val("否");
			}
			$("[name=device_info]").val(data.is_subscribe);
			$("[name=trade_type]").val(data.trade_type);
			$("[name=device_info]").val(data.device_info);
			$("[name=fee_type]").val(data.fee_type);
			$("[name=total_fee]").val(parseInt(data.total_fee)/100);
			$("[name=time_end]").val(data.time_end);
			$("[name=bank_type]").val(data.bank_type);
			$("[name=cash_fee]").val(parseInt(data.cash_fee)/100);
			
		}
	</script>
</html>