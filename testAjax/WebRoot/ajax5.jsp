<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ajax1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
// 创建异步对象
function createXMLHttpRequest() {
	try {
		return new XMLHttpRequest();//大多数浏览器
	} catch (e) {
		try {
			return ActvieXObject("Msxml2.XMLHTTP");//IE6.0
		} catch (e) {
			try {
				return ActvieXObject("Microsoft.XMLHTTP");//IE5.5及更早版本	
			} catch (e) {
				alert("哥们儿，您用的是什么浏览器啊？");
				throw e;
			}
		}
	}
}

/*
 * 1. 在文档加载完毕时发送请求，得到所有省份名称，显示在<select name="province"/>中
 * 2. 在选择了新的省份时，发送请求（参数为省名称），得到xml文档，即<province>元素
 *   解析xml文档，得到其中所有的<city>，再得到每个<city>元素的内容，即市名，使用市名生成<option>，插入到<select name="city">元素中
 */
 window.onload = function(){
 	/*
	ajax四步，请求ProvinceServlet，得到所有省份名称
	使用每个省份名称创建一个<option>元素，添加到<select name="province">中
	*/
 	var xmlHttp = createXMLHttpRequest();
 	xmlHttp.open("GET", "<c:url value='ProvinceServlet'/>",true);
 	xmlHttp.send(null);
 	
 	var proSelet = document.getElementById("p");//获取select元素 
 	xmlHttp.onreadystatechange = function(){
 		if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
 			//获取服务器的响应
 			var text = xmlHttp.responseText;
 			//用，将字符串分开，得到数组
 			var arr = text.split(",");
 			//循环遍历数组，为一个对象创建一个option对象
 			for(var i=0;i<arr.length;i++){
 				var option = document.createElement("option");
 				option.value = arr[i];//将option的value属性设置为当前省份
 				var textNode = document.createTextNode(arr[i]);//创建文本结点，设置值为当前省份
 				option.appendChild(textNode);
 				proSelet.appendChild(option);
 				
 			}
 		}
 	}; 
 	
 	/*
	第二件事情：给<select name="province">添加改变监听
	使用选择的省份名称请求CityServlet，得到<province>元素(xml元素)！！！
	获取<province>元素中所有的<city>元素，遍历之！获取每个<city>的文本内容，即市名称
	使用每个市名称创建<option>元素添加到<select name="city">
	*/
	var proSelet = document.getElementById("p");//获取province元素 
	proSelet.onchange = function(){
		var xmlHttp = createXMLHttpRequest();
		xmlHttp.open("POST","<c:url value='CityServlet'/>",true);
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xmlHttp.send("pname="+proSelet.value);//将选中的省份传给servlet
		xmlHttp.onreadystatechange = function(){
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
				/*
				把select中的所有option移除（除了请选择）
				*/
				var citySelect = document.getElementById("c");
				var optionEleList = citySelect.getElementsByTagName("option");
				// 循环遍历每个option元素，然后在citySelect中移除
				while(optionEleList.length > 1){
					citySelect.removeChild(optionEleList[1]);//总是删除下标为1的元素，因为删除了1,2就变成1了
				}
				
				var doc = xmlHttp.responseXML;
				//得到citys
				var cityEleList = doc.getElementsByTagName("city");
				//遍历citys，将每个city添加到citySelect下面
				for(var i=0;i<cityEleList.length;i++){
					var city = cityEleList[i];
					var cityName
					if(window.addEventListener){
						cityName = city.textContent;//支持其它浏览器
					}else{
						cityName = city.text;//IE
					}
					//使用cityName创建option添加到citySelect下面
					var option = document.createElement("option");
					option.value = cityName;
					var textNode = document.createTextNode(cityName);
					option.appendChild(textNode);
					citySelect.appendChild(option);
				}
			}
		};
	};
 };
</script>
  </head>
  
  <body>
    <h1>省市联动</h1>
    <select name="province" id="p">
    	<option>---请选择省份---</option>
    </select>&nbsp;&nbsp;
    <select name="city" id="c">
    	<option>---请选择地区---</option>
    </select>
  </body>
</html>
