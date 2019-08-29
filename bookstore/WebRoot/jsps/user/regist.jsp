<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'regist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <h1>注册</h1>
<p style="color: red; font-weight: 900">${msg }</p>
<form action="<c:url value='/UserServlet'/>" method="post">
	<input type="hidden" name="method" value="regist"/>
	用户名：<input type="text" name="username" value="${user.username }"/><font color="red" size="2">${errors.username }</font><br/>
	密　码：<input type="password" name="password" value="${user.password }"/><font color="red" size="2">${errors.password }</font><br/>
	邮　箱：<input type="text" name="email" value="${user.email }"/><font color="red" size="2">${errors.email }</font><br/>
	验证码:<input type="text" name="vcode" size="3"/>
          <img id="img" src="${pageContext.request.contextPath }/VerifyCodeServlet">
          <a  href="javascript:_replace()"><font size="2">看不清，换一张</font></a><font size="2" color="red">  ${errors.verifyCode }</font><br/>
	<input type="submit" value="注册"/>
</form>
  </body>
      <script type="text/javascript">
  	function _replace(){
		var img = document.getElementById("img");
		img.src = "${pageContext.request.contextPath }/VerifyCodeServlet?a="+new Date().getTime();
	}
  </script>
</html>
