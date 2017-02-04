<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
		String voteid=	request.getParameter("voteid");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- saved from url=(0068)http://tn.accoo.cn/vote-992.html?openid=oXDyzjvkCkT_vg1boTc6E8o0F5ZU -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Cache-Control" content="no-cache">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta charset="utf-8">
<title></title>
    <!-- 引入 ECharts 文件 -->
    <script src="<%=basePath%>/webpage/weixin/idea/huodong/toupiao/source/echarts.min.js"></script>
<script src="<%=basePath%>/plug-in/forum/curstrom.js"></script>
</head>

<body >

 <!-- 为 ECharts 准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width:700px;height:500px;margin: 10% auto;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        /* // 指定图表的配置项和数据
        var option = {
            title: {
                text: '歌唱比赛'
            },
            tooltip: {},
            legend: {
                data:['得票数']
            },
            xAxis: {
                data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            },
            yAxis: {},
            series: [{
                name: '销量',
                type: 'bar',
                data: [5, 20, 36, 10, 10, 20]
            }]
        }; */
        var voteid='<%=voteid%>';
        myChart.showLoading();
			// 异步加载数据
			if(voteid.indexOf(".jsp"))voteid=voteid.substring(0,voteid.indexOf(".jsp"));
			sendAjaxRequest('<%=basePath%>huodongController.do?getpicketresult&voteid='+voteid,function(datass){
			 // 填入数据
			 datas=eval("("+datass+")");
			  		myChart.hideLoading();
			    	myChart.setOption({ title: {
                		text: '歌唱比赛'
            		},
            		 legend: {
                		data:['得票数']
            		},
			        xAxis: {
			            data: datas.categories
			        },
			        yAxis: {},
			        series: [{
			            // 根据名字对应到相应的系列
			            name: '得票数',
			             type: 'bar',
			            data: datas.data
			        }]
			    });
			});
    </script>
</body>

</html>