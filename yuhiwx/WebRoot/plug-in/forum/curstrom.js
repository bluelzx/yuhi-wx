/**
 * make curstromjs
 * @param url
 */

//ansy get css file
function addCssByLink(url) {
	var doc = document;
	var link = doc.createElement("link");
	link.setAttribute("rel", "stylesheet");
	link.setAttribute("type", "text/css");
	link.setAttribute("href", url);

	var heads = doc.getElementsByTagName("head");
	if (heads.length)
		heads[0].appendChild(link);
	else
		doc.documentElement.appendChild(link);
}
//choiceall checkbox
function choiceAll(eve,evename) {
	var boxs = document.getElementsByName(evename);
	for ( var i = 0; i < boxs.length; i++) {
		boxs[i].checked = eve.checked ? true : false;
	}
}

//ansytask 
var XMLHttpReq;  
function createXMLHttpRequest() {  
    try {  
        XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
    }  
    catch(E) {  
        try {  
            XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
        }  
        catch(E) {  
            XMLHttpReq = new XMLHttpRequest();  
        }  
    }  
  
}  
function sendAjaxRequest(url,callback) {  
    createXMLHttpRequest();                                //make XMLHttpRequest object  
    XMLHttpReq.open("post", url, true);  
    XMLHttpReq.onreadystatechange = function(){   //ajaxCallback
		     if (XMLHttpReq.readyState == 4) {    
		        if (XMLHttpReq.status == 200) {  
		            var text = XMLHttpReq.responseText;  
		            callback(text);
		        }  
		    }  
    }; 
    XMLHttpReq.send(null);  
}
//clear array replace eve
function unique(arr) {
  var ret = []
  var hash = {}
  for (var i = 0; i < arr.length; i++) {
    var item = arr[i]
    var key = typeof(item) + item
    if (hash[key] !== 1) {
      ret.push(item)
      hash[key] = 1
    }
  }
  return ret
}
/*  
**    ====================================
**    类名：CLASS_LIANDONG_YAO  
**    功能：多级连动菜单  
**    作者：YAODAYIZI     
**/   
  function CLASS_LIANDONG_YAO(array)
  {
   //数组，联动的数据源
   this.array=array; 
   this.indexName='';
   this.obj='';
   //设置子SELECT
 // 参数：当前onchange的SELECT ID，要设置的SELECT ID
      this.subSelectChange=function(selectName1,selectName2)
   {
   //try
   //{
    var obj1=document.all[selectName1];
    var obj2=document.all[selectName2];
    var objName=this.toString();
    var me=this;
    obj1.onchange=function()
    {
     me.optionChange(this.options[this.selectedIndex].value,obj2.id)
    }
   }
   //设置第一个SELECT
 // 参数：indexName指选中项,selectName指select的ID
   this.firstSelectChange=function(indexName,selectName)  
   {
   this.obj=document.all[selectName];
   this.indexName=indexName;
   this.optionChange(this.indexName,this.obj.id)
   }
  // indexName指选中项,selectName指select的ID
   this.optionChange=function (indexName,selectName)
   {
    var obj1=document.all[selectName];
    var me=this;
    obj1.length=0;
    obj1.options[0]=new Option("请选择",'');
    for(var i=0;i<this.array.length;i++)
    { 
     if(this.array[i][1]==indexName)
     {
     //alert(this.array[i][1]+" "+indexName);
      obj1.options[obj1.length]=new Option(this.array[i][2],this.array[i][0]);
     }
    }
   } 
  }

