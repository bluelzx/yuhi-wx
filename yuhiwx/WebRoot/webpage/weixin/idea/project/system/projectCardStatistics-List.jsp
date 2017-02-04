<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
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
    <!-- 引入 ECharts 文件 --><%--
    <script src="<%=basePath%>/webpage/weixin/idea/huodong/toupiao/source/echarts.min.js"></script>
--%>
 <script src="<%=basePath%>/plug-in/charts/echarts.min.js"></script>
<script src="<%=basePath%>/plug-in/forum/curstrom.js"></script>

<style type="text/css">
input{width: 100%;}
</style>
</head>

<body >

<div style="width: 100%;height: 200px">
<div  style="width: 28%;float: left;overflow: hidden;padding-top: 20px">
	<table>
	<tr>
	<td>时间(月):</td><td><input name="month" type="month"><br/></td>
	</tr><%--
	<tr>
	<td>工程单类型:</td><td><input name="type"><br/></td>
	</tr>
	<tr>
	<td>工程单类型:</td><td><input name="type"><br/></td>
	</tr>
	<tr>
	<td>工程单类型:</td><td><input name="type"><br/></td>
	</tr>
	<tr>
	<td>工程单类型:</td><td><input name="type"><br/></td>
	</tr>
	<tr>
	<td>工程单类型:</td><td><input name="type"><br/></td>
	</tr>
	--%>
	<tr></tr>
	<tr>
	<td colspan="2" rowspan="2"><button onclick="onRengen()" style="width: 100%">查询</button></td>	
	</tr>
	<tr></tr>
	</table>
</div>
 <div id="totalCount"  style="width: 70%;height:300px;float: left;"></div>
</div>
 	
    <div id="main" style="width:100%;height:250px;margin: 3% auto;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
       	  var myChart = echarts.init(document.getElementById('main'));
       	  var totalCount = echarts.init(document.getElementById('totalCount'));
       	 
       	  function initPieCount(data){
       		var gailv=getproject(data.piedata);
       		totalCount.setOption({
				    title : {
				        text: '工程单数量统计',
				        subtext: 'monthCount',
				        x:'right'
				    },
				    visualMap: {
				        show: false,
				        min: 80,
				        max: 600,
				        inRange: {
				            colorLightness: [0, 1]
				        }
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				        orient: 'vertical',
				        left: 'left',
				        data: ['待接单:'+gailv[0]+'%',
				               '待办理:'+gailv[1]+'%',
				               '办理中:'+gailv[2]+'%',
				               '办理结束(待评价):'+gailv[3]+'%',
				               '待回访:'+gailv[4]+'%',
				               '办理完成:'+gailv[5]+'%']
				    },
				    series : [
				        {
				            name: '月度统计',
				            type: 'pie',
				            radius : '55%',
				            center: ['50%', '60%'],
				            data:[
				                {value:data.piedata[0], name:'待接单:'+gailv[0]+'%'},
				                {value:data.piedata[1], name:'待办理:'+gailv[1]+'%'},
				                {value:data.piedata[2], name:'办理中:'+gailv[2]+'%'},
				                {value:data.piedata[3], name:'办理结束(待评价):'+gailv[3]+'%'},
				                {value:data.piedata[4], name:'待回访:'+gailv[4]+'%'},
				                {value:data.piedata[5], name:'办理完成:'+gailv[5]+'%'}
				            ],
				            itemStyle: {
				                emphasis: {
				                    shadowBlur: 10,
				                    shadowOffsetX: 0,
				                    shadowColor: 'rgba(0, 0, 0, 0.5)'
				                }
				            }
				        }
				    ]
				});
       	  }
       	  //计算各工单比例
       	  function getproject(d){
       		  var ary=new Array();
       		  var count=0;
       		  for(var i=0;i<d.length;i++)count+=parseInt(d[i]);
       		  for(var i=0;i<d.length;i++){
       			  var a=(parseInt(d[i])/count*100).toFixed(2);
        			ary.push(a);
        	  }
       		  return ary;
       	  }
       	  //初始化柱状图（月统计）
       	  function initMonthCount(datas){
       		myChart.setOption({ 
		    	title: {
            		text: '工单状态统计',
            		subtext: 'project engineering monthly statistics'
        		},
        		 tooltip : {
        		        trigger: 'axis',
        		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
        		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        		        },
        		        formatter:function (params, ticket, callback) {
		                    return "日期:"+(params[1].dataIndex+1)+"号，工程单数量"+params[1].data+"条";
		                }
        		    },
        		    toolbox: {
        		        show: true,
        		        feature: {
        		            dataView: {readOnly: false},
        		            magicType : {show: true, type: ['line', 'bar']},
        		            restore : {show: true},
		                    saveAsImage : {show: true}
        		        }
        		    },
        		legend: {
            		data:['日期']
        		},
		        xAxis: {
		            data: datas.categories
		        },
		        yAxis: {},
		        series: [{
		            // 根据名字对应到相应的系列
		             type: 'bar',
		             itemStyle: {
			                normal: {color: 'rgba(0,0,0,0.05)'}
			            },
			            barGap:'-100%',
			            barCategoryGap:'40%',
			            animation: false,
		        },{
		        	name: '日期',
		            type: 'bar',
		            itemStyle: {
		                normal: {
		                    color: new echarts.graphic.LinearGradient(
		                        0, 0, 0, 1,
		                        [
		                            {offset: 0, color: '#83bff6'},
		                            {offset: 0.5, color: '#188df0'},
		                            {offset: 1, color: '#188df0'}
		                        ]
		                    )
		                },
		                emphasis: {
		                    color: new echarts.graphic.LinearGradient(
		                        0, 0, 0, 1,
		                        [
		                            {offset: 0, color: '#2378f7'},
		                            {offset: 0.7, color: '#2378f7'},
		                            {offset: 1, color: '#83bff6'}
		                        ]
		                    )
		                }
		            },
		            data:  datas.data
		        	}
		        ]
		    });
       	  }
       	  //渲染页面
       		function onRengen(){
       			var month=$("[name=month]").val();
       			if(month){
       				var data=month.split("-");
       				var datestr=data[0]+"-"+data[1]+"-01 00:00:00";
       				myChart.clear(); //清楚数据
                	totalCount.clear(); //清楚数据
                	myChart.showLoading();
            		totalCount.showLoading();
        			// 异步加载数据
        			sendAjaxRequest('<%=basePath%>projectCardStatisticsController.do?statisticsData&times='+datestr,function(datass){
        				myChart.hideLoading();
     			  		totalCount.hideLoading(); 
     			  		debugger
        					// 填入数据
        			  		if(datass){
        			  			datas=eval("("+datass+")");
             			  		 //初始化汇总
           			       		initPieCount(datas);
             			  		initMonthCount(datas);
        			  		}else tip("该日期无数据！");
        			});
       			}else{
       				tip("请选择日期！");
       			}
       		}
        	
    </script>
</body>

</html>