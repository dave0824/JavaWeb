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
	var useEle = document.getElementById("usernameEle");
	//给按钮添加事件监听
	useEle.onblur = function(){
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
		/************修改open方法，指定请求方式为POST**************/
		xmlHttp.open("POSt", "<c:url value='/ValidateUsernameServlet'/>", true);
		/************设置请求头：Content-Type************/
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		/*
		3.发送请求
		*/
		xmlHttp.send("username="+useEle.value);//POST请求发送信息为请求体
		/*
		4. 给异步对象的onreadystatechange事件注册监听器
		*/
		xmlHttp.onreadystatechange = function(){ //当xmlHttp的状态发生变化时执行
			// 双重判断：xmlHttp的状态为4（服务器响应结束），以及服务器响应的状态码为200（响应成功）
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
				//获取服务器的响应，判断是否为1
				// 是：获取span，添加内容：“用户名已被注册”
				var span = document.getElementById("errSpan");
				var text = xmlHttp.responseText;
				if(text == "1"){
					span.innerHTML = "用户名已被注册";
				}else{
					span.innerHTML = "";
				}
			}
		};
	};
};
</script>
  </head>
  
  <body>
  <h2>注册用户名校验</h2>
    <form action="" method="post">
            用户名：	<input type="text" id="usernameEle" name="username"/><span id="errSpan"></span><br/>
            密    码：   <input type="password" id="passwordEle" name="password"/><br/>
            <input type="submit" value="提交"/>
    </form>
  </body>
</html>
