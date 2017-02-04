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
<title>发布工程单</title>
<link href="<%=basePath%>/webpage/weixin/idea/project/sshtml_files/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>/webpage/weixin/idea/project/sshtml_files/style.css" rel="stylesheet">
</head>
<body class="huibg">

	<nav class="navbar text-center">
		<a class="navbar-tit center-block">发布工程单</a>
	</nav>

	<div class="usercenter  accdv">
		<form action="<%=basePath%>projectCreateController.do?doAdd" method="post">
		<input type="hidden" name="openid" value="<%=request.getParameter("openid")%>">
			<input type="hidden" name="ownerid" value="<%=request.getParameter("ownerid")%>">
			<div class="row">
				<div class="col-md-2">手机号：</div>
				<div class="col-md-10">
					<input value="<%=request.getParameter("phone")%>" type="tel" maxlength="11" name="phone" id=""
						class="form-control" required="required">
				</div>
			</div>
			<div class="row">
				<div class="col-md-2">所在区域：</div>
				<div class="col-md-10">
					<div class="form-group m-r-10">
						<select class="form-control" ID="s1" NAME="s1" >
							<OPTION selected></OPTION>
						</select>
					</div>
					<div class="form-group m-r-10">
						<select class="form-control" ID="s2" NAME="s2" >
							<OPTION selected></OPTION>
						</select>
					</div>
					<div class="form-group m-r-10">
						<SELECT class="form-control" ID="s3" NAME="s3" onchange="getpartvalue()">
						    <OPTION selected></OPTION>
					    </SELECT>	
					    <input name="part" type="hidden">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2">问题描述:</div>
				<div class="col-md-10">
					<textarea class="form-control" rows="3" name="content">修理窗户</textarea>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2">入户处理时间:</div>
				<div class="col-md-10">
					<div class="form-group m-r-10">
						开始:<input class="form-control" name="starttime"  type="date" >
					</div>
					<div class="form-group m-r-10">
					   	结束:<input class="form-control"  name="endtime"  type="date" >
					</div>
				</div>
			</div>
				<div class="row">
				<div class="col-md-2">基础耗材:</div>
				<div class="col-md-10">
					<div class="form-group m-r-10">
						<select class="form-control"  >
							<OPTION selected></OPTION>
						</select>
					</div>
					<div class="form-group m-r-10">
						<select class="form-control"  >
							<OPTION selected></OPTION>
						</select>
					</div>
					<div class="form-group m-r-10">
						<SELECT class="form-control" >
						    <OPTION selected></OPTION>
					    </SELECT>	
					</div>
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
	 	array[++index]=new Array(jsondata[i].building,jsondata[i].region,jsondata[i].building);
	 	array[++index]=new Array(jsondata[i].placeDetail,jsondata[i].building,jsondata[i].placeDetail);
	  }
	 array= unique(array);
	  console.log(array);
	   var liandong=new CLASS_LIANDONG_YAO(array); //设置数据源
	    liandong.firstSelectChange("root","s1"); //设置第一个选择框
  		liandong.subSelectChange("s1","s2"); //设置子级选择框
  		liandong.subSelectChange("s2","s3");
	});
	function getpartvalue(){
	var text=document.getElementById("s1").value+document.getElementById("s2").value+document.getElementById("s3").value+"";
		document.getElementsByName("part").value=text+"";
	}
</script>

</html>