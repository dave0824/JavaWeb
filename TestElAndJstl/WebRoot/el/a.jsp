<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="cn.dave.user.domain.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'a.jsp' starting page</title>
    
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
    	Address  address = new Address();
    	address.setCity("南昌");
    	address.setStreet("北京西路");
    	Employee employee = new Employee();
    	employee.setName("dave");
    	employee.setAge("20");
    	employee.setAddress(address);
    	request.setAttribute("employee",employee);
     %>
  </body>
  <h1>利用EL得到request域中的employee对象</h1>
  	${requestScope.employee.address.city }
</html>
