<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>业主绑定</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    
</head>

<body>
	<select  class="map" style="border-right:none;border-left:none;border-color: #E6E3E3;" >
                   	 <option>请选择...</option>
   </select>
   <select  class="map" style="border-right:none;border-left:none;border-color: #E6E3E3;" >
                   	 <option>请选择...</option>
   </select>
   <select  class="map" style="border-right:none;border-left:none;border-color: #E6E3E3;" >
                   	 <option>请选择...</option>
   </select>
   <select   class="map" style="border-right:none;border-left:none;border-color: #E6E3E3;" >
                   	 <option>请选择...</option>
   </select>
</body>
<script src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript">
var elemlength=$(".map").length;
$(function() {
   	loadSelectData(0,1,null);
   	$('.map').change(function(){ 
   		getNextselect(this);
   	});
});
	function getNextselect(elem){
		var parentid =$(elem).val();
		if(!parentid)return;
		var index=parseInt($(".map").index($(elem)))+1
		loadSelectData(index,parentid,null);
	}
    function loadSelectData(index,parentid,after){
    	debugger
        $(".shanrd-box").show();
        $select=$(".map").eq(index);
        if(!$select)return;
        $.getJSON("<%=basePath%>projectInvitationController.do?getInvitationbyParentid&parentsid="+parentid,"",function(data){
            for(var i=0;i<data.resultList.length;i++){
              $select.append("<option value='"+data.resultList[i].id+"'>"+data.resultList[i].name+"</option>");
            }
           if(after&&typeof after ==='function')after();
           setTimeout(function(){
               $(".shanrd-box").hide();
           },1000)
        });
    }
</script>

</html>