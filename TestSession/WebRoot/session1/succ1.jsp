<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'secc1.jsp' starting page</title>
    
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
    <%
  	String uname=(String)session.getAttribute("uname");
  	if(uname==null){
  		request.setAttribute("msg","您还没登录呢。");
  		request.getRequestDispatcher("/session1/login.jsp").forward(request, response);
  	}
   %>
  	<h1>欢迎最帅的<%=uname %>回来</h1>
  	<a href="/TestSession/session1/succ2.jsp">进入我的页面</a>
  </body>
</html>
