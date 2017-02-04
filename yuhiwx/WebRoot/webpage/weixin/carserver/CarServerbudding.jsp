<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="keywords" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>完善你的工程人员信息</title>
<link href="<%=basePath%>/webpage/weixin/idea/project/sshtml_files/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>/webpage/weixin/idea/project/sshtml_files/style.css" rel="stylesheet">
</head>
<body class="huibg">

	<nav class="navbar text-center">
		<a class="navbar-tit center-block">完善你的工程人员信息</a>
	</nav>

	<div class="usercenter  accdv">
		<form action="<%=basePath%>weixinCarCustomerController.do?doAdd" method="post">
		<input type="hidden" name="openid" value="<%=request.getParameter("openid")%>">
			<div class="row">
				<div class="col-md-2">称呼：</div>
				<div class="col-md-10">
					<input type="text" name="name" id="" class="form-control"
						required="required">
				</div>
			</div>
			<div class="row">
				<div class="col-md-2">手机号：</div>
				<div class="col-md-10">
					<input value="111111111" type="tel" maxlength="11" name="phone" id=""
						class="form-control" required="required">
				</div>
			</div>
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-10">
					<button type="submit" class="btn btn-danger btn-block btn-lg">确
						定</button>
				</div>
			</div>
		</form>
	</div>
 
</body>

<script language="javascript" src="<%=basePath%>plug-in/forum/curstrom.js"></script>
 <script language="javascript">
  	var array=new Array();
	//加载数据
	sendAjaxRequest('<%=basePath%>projectPartController.do?getPartJsondata',function(data){
	  var jsondata=eval("("+data+")");
	  var index=-1;
	  for(var i=0;i<jsondata.length;i++){
	 	// array[index]=new Array(jsondata[i].region,jsondata[i].building,jsondata[i].placeDetail); //数据格式 ID，父级ID，名称
	 	array[++index]=new Array(jsondata[i].region,"root",jsondata[i].region);
	 	/* array[++index]=new Array(jsondata[i].building,jsondata[i].region,jsondata[i].building);
	 	array[++index]=new Array(jsondata[i].placeDetail,jsondata[i].building,jsondata[i].placeDetail); */
	  }
	 array= unique(array);
	  console.log(array);
	   var liandong=new CLASS_LIANDONG_YAO(array); //设置数据源
	    liandong.firstSelectChange("root","s1"); //设置第一个选择框
  		/* liandong.subSelectChange("s1","s2"); //设置子级选择框
  		liandong.subSelectChange("s2","s3"); */
	});
	
	function getpartvalue(e){
	var parttext=e.value;
	document.getElementById("part").value=""+parttext;
		
	}
</script>
</html>