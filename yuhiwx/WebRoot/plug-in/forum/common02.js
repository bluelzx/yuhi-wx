// JavaScript Document
/*
author:david;
time:2014.07.10
*/
function addCssByLink(url){
			var doc=document;
			var link=doc.createElement("link");
			link.setAttribute("rel", "stylesheet");
		    link.setAttribute("type", "text/css");
		    link.setAttribute("href", url);
		
			var heads = doc.getElementsByTagName("head");
			if(heads.length)
				heads[0].appendChild(link);
			else
				doc.documentElement.appendChild(link);
		}
$(document).ready(function(e) {
			
//			/*伪单选项*/
//			$(".sex").find("input").click(function(){
//				var index=$(".sex").find("input").index(this);
//				$(".sex").find("input").removeClass("active").eq(index).addClass("active");
//			});
			/*点击接受时可提交*/
//			$("#tongyi").click(function(){
//				if($("#tongyi")[0].checked==true){
//					$("#tijiao")[0].disabled=false;
//					$("#tijiao").css('cursor','pointer');
//					$("#tijiao").addClass("but");
//				}
//			});
//			/*点击不接受时不可提交*/
//			$("#notongyi").click(function(){
//				if($("#notongyi")[0].checked==true){
//					$("#tijiao")[0].disabled=true;
//					$("#tijiao").css('cursor','');
//					$("#tijiao").removeClass("but");
//					
//				}
//			});
			
			$("#send").click(function(){
				djstime();
			});
			/*验证码倒计时*/
			function djstime(){
				var e1=$("#send").first();
				var i=10;
				var interval=setInterval(function(){
					e1.html("剩余"+i+"秒");
					$("#send").css("line-height","35px");
					i--;
					if(i<0){
						$("#send").css({cursor:"pointer"});
						$("#send").css("line-height","18px");
						e1.html("重新<br>获取");
						clearInterval(interval);	
					}
				},1000);	
			}
});