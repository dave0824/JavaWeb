<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>top</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	a:link {
    color:#FF0000;
    text-decoration:underline;
    }
    a:visited {
    color:#00FF00;
    text-decoration:none;
    }
    a:hover {
    color:#000000;
    text-decoration:none;
    }
    a:active {
    color:#FFFFFF;
    text-decoration:none;
    }
</style>

  </head>
  
  <body style="background: rgb(78,78,78);color: white;">
<h1 style="text-align: center; ">dave网络图书商城后台管理</h1>
管理员：${sessionScope.session_admin.aname }&nbsp;&nbsp;|&nbsp;&nbsp;
<a href="<c:url value='/admin/AdminServlet?method=quit'/>" target="_parent">退出</a>&nbsp;&nbsp;|&nbsp;&nbsp;
<a href="<c:url value='/index.jsp'/>" target="_parent">用户界面</a>&nbsp;&nbsp;|&nbsp;&nbsp;
  </body>
</html>
