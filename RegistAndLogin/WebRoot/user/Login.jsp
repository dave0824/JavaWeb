<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Regist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<%--   	<%
		String message = "";
		String msg = (String)request.getAttribute("msg");//获取request域中的名为msg的属性
		if(msg != null) {
			message = msg;
		}
	%> --%>
    <h1>登录</h1>
    <font color="red"><b>${msg }</b></font>
    <form action="${pageContext.request.contextPath }/LoginServlet" method="post">
           用户名：<input type="text" name="username" value="${user.username}"/><font color="red" size="2">${errors.username }</font><br/>
           密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" name="password"/><font color="red" size="2">${errors.password }</font><br/>
           验证码:<input type="text" name="vcode" size="3"/>
           <img id="img" src="${pageContext.request.contextPath }/VerifyCodeServlet">
           <a  href="javascript:_replace()"><font size="2">看不清，换一张</font></a><font size="2" color="red">  ${errors.verifyCode }</font><br/>
           <input type="submit" value="登录"/>
    </form>
  </body>
    <script type="text/javascript">
  	function _replace(){
		var img = document.getElementById("img");
		img.src = "${pageContext.request.contextPath }/VerifyCodeServlet?a="+new Date().getTime();
	}
  </script>
</html>
