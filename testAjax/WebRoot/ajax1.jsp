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
		xmlHttp.open("GET","<c:url value='/AServlet'/>",true);
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
				//获取服务器的响应结束
				var text = xmlHttp.responseText;
				//获取h1
				var h1 = document.getElementById("h1");
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
