<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
    <%-- 
		把与语言相关的所有字符串都写成变量！！！
 	--%>
 <%
 /*
 1. 获取Locale，这是由客户端的浏览器提供的Locale
 2. 创建ResourceBundle
 3. 把所有的语言信息使用rb.getString("xxx")来替换！
 */
 	Locale locale = request.getLocale();
 	ResourceBundle rb = ResourceBundle.getBundle("res", locale);
 %>
 <form action ="" method="post">
 	<%=rb.getString("login") %>
 	<%=rb.getString("username") %>:<input type="text" name="username"/><br/>
 	<%=rb.getString("password") %>:<input type="password" name="password"/><br/>
 	<input type="submit" value=<%=rb.getString("login") %> />
 </form>
  </body>
</html>
