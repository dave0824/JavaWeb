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
    
    <title>My JSP 'json1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script type="text/javascript">
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
window.onload = function(){
 var btn = document.getElementById("btn");
 btn.onclick = function(){
 
 	var xmlHttp = createXMLHttpRequest();
 	xmlHttp.open("GET","<c:url value='AServlet'/>",true);
 	xmlHttp.send(null);
 	xmlHttp.onreadystatechange = function(){
 	
 		if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
 			var text = xmlHttp.responseText;//得到一个json串
 			//执行json串
 			var person = eval("(" + text + ")");
 			document.getElementById("h").innerHTML = text;
 		}
 	};
 };
};
		
</script>
  </head>
  
  <body>
    <button id="btn">点击这里</button>
    <h1 id="h"></h1>
  </body>
</html>
