<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("basePath", basePath);
	String type=request.getParameter("type");
	String cardid="";
	if(type.equals("2")){
	cardid=request.getParameter("cardid");
	}
	request.setAttribute("type", type);
	//${pageScope.basePath}
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!-- saved from url=(0063)http://www.17sucai.com/preview/59495/2014-07-11/demo/demo.html# -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title><c:if test="${type==1}">发布新帖子</c:if><c:if test="${type==2}">添加新回复</c:if></title>
</head>

<body>
<div class="wrap">
<form class="form1" action="<c:if test="${type==1}">${pageScope.basePath}forumController.do?addshowSendCard</c:if><c:if test="${type==2}">${pageScope.basePath}forumController.do?callbackfollowCard</c:if>" method="post">
	<input name="cardid" type="hidden" value="<%=cardid%>">
	<div class="bgfff form ov">
		<div class="fb">请填写详情：</div>
         <ul class="cb">
              <c:if test="${type==1}"> <li>
            	<div class="fl la_bg tc"><label for="name" class="lable">帖主题</label></div>
                <div class="fl l_r"><input type="text" required="" name="title" id="title" class="w_img95 i_bor"></div>
               </li>
               
              <li>
            	<div class="fl la_bg tc"><label for="name" class="lable">发帖类型</label></div>
                <div class="fl l_r"><input type="text" id="type" name="type" required="" class="w_img95 i_bor"></div>
               </li>
               </c:if>
               
               <li><div class="fl la_bg tc" style="background-color: #0a86ce;width: 100%">
               <label for="name" class="lable">
               	<c:if test="${type==1}">
               	发帖内容
               	</c:if>
               	<c:if test="${type==2}">
               	回复内容
               	</c:if>
               	</label>
               </div> </li>
               <li>
            	
                <div >
                 <textarea name="cardcontent" id="content"
										style="width:100%;height:300px"></textarea> 
				</div>
               </li>
               
              <!--  <li>
            	<div class="fl check tc"><label for="name" class="lrcol">输入校证码</label></div>
                <div class="fl check_r"><input type="text" id="name" class="w_img95 i_bor"></div>
                <div class="fr"><span class="fr check_span" id="send">获 &nbsp;&nbsp;&nbsp;&nbsp;取<br>验证码</span></div>
               </li> -->
         </ul>
    </div>
    
      <div class="bgfff form ov">
      <c:if test="${type==1}">
		<div class="fb">发帖申明：</div>
        <p class="font_p pt15">请认真阅读以下协议，在发帖之前，你必须接受此协议的条款。</p>
        <div class="xieyi">
        	<p>1、该协议的目的是为了活动发起人或组织者或领队
（以下协议中简称：发起者）和同行成员明确知晓户外活动所存在的风险，提高参加人员的抗风险和自律能力，免除活动的发起人和同行成员在活动中出现的相关赔偿及法律连带等责任，让户外活动更加安全、健康、快乐。</p>

			<p>2、活动中，对于违犯国家相关法规、恶意侵犯他人或其它涉及犯罪行为的事件，则不在此协议范围内，必须由个人承担相应的法律责任。</p>           
          
			<p>3、发帖中，对于违犯国家相关法规、恶意侵犯他人或其它涉及犯罪行为的事件，则不在此协议范围内，必须由个人承担相应的法律责任。</p>
			<p>4、提交证明你同意以上条约！</p>
        </div></c:if>
        
          <div class="cb pt20">
           验证码：<input type="text" required="" name="vaildcode" id="vaildcode" style="border: 1px solid #000;height:25px">
          <img height="30px" src="${pageScope.basePath}webpage/common/validatecode.jsp"/>
          </div>
        <div class="cb pt20"><input type="button" onclick="postdata()" class="nobut but submitbutton" style="cursor: pointer;" value="提交信息"></div>
        
    </div>
</form>  


</div>


<audio controls="controls" style="display: none;"></audio>
</body>
<link type="text/css" rel="stylesheet" href="${pageScope.basePath}plug-in/forum/base.css">
<link rel="stylesheet" type="text/css" href="${pageScope.basePath}plug-in/forum/common02.css">
<script src="${pageScope.basePath}plug-in/layer/layer.js"
	type="text/javascript"></script>
<script type="text/javascript" src="${pageScope.basePath}plug-in/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageScope.basePath}plug-in/forum/common02.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageScope.basePath}plug-in/ckeditor/ckeditor.js"></script>
	<script type="text/javascript">
	
	CKEDITOR.replace('cardcontent');
	
		//var editor = UE.getEditor('content');
		function goto(url){window.location.href=url;}
		function postdata(){
					var type=$("#type").val();
					var title=$("#title").val();
					var	content=CKEDITOR.instances.content.getData();
			switch(${type}){
				case 1:
					if(!type){
					 layer.open({content: '类型不能为空', time: 1});
					return false;
					}else if(!title){
					layer.open({content: '标题不能为空', time: 1});
					return false;
					}else if(!content){
					layer.open({content: '发帖内容不能为空', time: 1});
					return false;
					}
				break;
				case 2:
					if(!content){
					layer.open({content: '回复内容不能为空', time: 1});
					return false;
					}
				break;
			}
			var vaildcode=$("#vaildcode").val();
			if(!vaildcode){
					layer.open({content: '验证码不能为空', time: 1});
					return false;
			}
			$.ajax({
				url:$(".form1").attr("action")+"&type="+type+"&title="+title+"&content="+content+"&vaildcode="+vaildcode,
				type:"POST",
				success:function(data){
					if(data.indexOf(".do")>0){
						$(".submitbutton").attr("disabled",true);
						layer.open({content: '提交成功', time: 1});				
						location.href="${pageScope.basePath}forumController.do?showSendCards";					
					}else{
						 layer.open({content: '请完善提交信息', time: 1});
					}
				}
			});
		/* $(".form1").submit(function(){
			switch(${type}){
				case 1:
					if(!$("#type").val()){
					 layer.open({content: '类型不能为空', time: 1});
					return false;
					}else if(!$("#title").val()){
					layer.open({content: '标题不能为空', time: 1});
					return false;
					}else if(!$("#content").val()){
					layer.open({content: '发帖内容不能为空', time: 1});
					return false;
					}
				break;
				case 2:
					if(!$("#content").val()){
					layer.open({content: '回复内容不能为空', time: 1});
					return false;
					}
				break;
			}
			$(".submitbutton").attr("disabled",true);
		}); */
		}
			//addCssByLink('${pageScope.basePath}plug-in/forum/base.css');
			//addCssByLink('${pageScope.basePath}plug-in/forum/common02.css');
			
		
		
	 </script>
<style type="text/css">#yddContainer{display:block;font-family:Microsoft YaHei;position:relative;width:100%;height:100%;top:-4px;left:-4px;font-size:12px;border:1px solid}#yddTop{display:block;height:22px}#yddTopBorderlr{display:block;position:static;height:17px;padding:2px 28px;line-height:17px;font-size:12px;color:#5079bb;font-weight:bold;border-style:none solid;border-width:1px}#yddTopBorderlr .ydd-sp{position:absolute;top:2px;height:0;overflow:hidden}.ydd-icon{left:5px;width:17px;padding:0px 0px 0px 0px;padding-top:17px;background-position:-16px -44px}.ydd-close{right:5px;width:16px;padding-top:16px;background-position:left -44px}#yddKeyTitle{float:left;text-decoration:none}#yddMiddle{display:block;margin-bottom:10px}.ydd-tabs{display:block;margin:5px 0;padding:0 5px;height:18px;border-bottom:1px solid}.ydd-tab{display:block;float:left;height:18px;margin:0 5px -1px 0;padding:0 4px;line-height:18px;border:1px solid;border-bottom:none}.ydd-trans-container{display:block;line-height:160%}.ydd-trans-container a{text-decoration:none;}#yddBottom{position:absolute;bottom:0;left:0;width:100%;height:22px;line-height:22px;overflow:hidden;background-position:left -22px}.ydd-padding010{padding:0 10px}#yddWrapper{color:#252525;z-index:10001;background:url(chrome-extension://eopjamdnofihpioajgfdikhhbobonhbb/ab20.png);}#yddContainer{background:#fff;border-color:#4b7598}#yddTopBorderlr{border-color:#f0f8fc}#yddWrapper .ydd-sp{background-image:url(chrome-extension://eopjamdnofihpioajgfdikhhbobonhbb/ydd-sprite.png)}#yddWrapper a,#yddWrapper a:hover,#yddWrapper a:visited{color:#50799b}#yddWrapper .ydd-tabs{color:#959595}.ydd-tabs,.ydd-tab{background:#fff;border-color:#d5e7f3}#yddBottom{color:#363636}#yddWrapper{min-width:250px;max-width:400px;}</style>

</html>