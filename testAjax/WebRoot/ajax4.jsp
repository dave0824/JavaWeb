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
//文件加载完毕后执行
window.onload = function(){
	var btn = document.getElementById("btn");
	//给按钮添加事件监听
	btn.onclick = function(){
		/*
			ajax四步得到服务器响应，将响应写入<h1>这里</h1>
		*/
		//得到异步对象
		var xmlHttp = createXMLHttpRequest();
		/*
		2. 打开与服务器的连接
		  * 指定请求方式
		  * 指定请求的URL
		  * 指定是否为异步请求
		*/
		xmlHttp.open("GET","<c:url value='/BServlet'/>",true);
		/*
		3.发送请求
		*/
		xmlHttp.send(null);//GET请求没有请求体，但也要给出null，不然FireFox可能会不能发送！
		/*
		4. 给异步对象的onreadystatechange事件注册监听器
		*/
		xmlHttp.onreadystatechange = function(){ //当xmlHttp的状态发生变化时执行
			// 双重判断：xmlHttp的状态为4（服务器响应结束），以及服务器响应的状态码为200（响应成功）
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
				//获取服务器响应的xml
				var doc = xmlHttp.responseXML;
				//查询名为student的所有元素，再取下标为0的数组元素
				var stuEle = doc.getElementsByTagName("student")[0];
				//获取number的属性值
				var number = stuEle.getAttribute("number");
				var name;
				var age;
				var sex;
				//解决浏览器差异问题
				if(window.addEventListener){//其它浏览器
					name = stuEle.getElementsByTagName("name")[0].textContent;
					age = stuEle.getElementsByTagName("age")[0].textContent;
					sex = stuEle.getElementsByTagName("sex")[0].textContent;
				}else{//IE浏览器
					name = stuEle.getElementsByTagName("name")[0].text;
					age = stuEle.getElementsByTagName("age")[0].text;
					sex = stuEle.getElementsByTagName("sex")[0].text;
				}
				var text = number + name + age + sex;
				//获取h1标签
				var h1 = document.getElementById("h1");
				//
				h1.innerHTML = text;
			}
		};
	};
};
</script>
  </head>
  
  <body>
    <button id="btn">点我 </button>
	<h1 id="h1"></h1>
  </body>
</html>
