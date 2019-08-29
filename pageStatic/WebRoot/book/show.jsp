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
    
    <title>My JSP 'show.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h2 align="center">图书</h2>
    <table align="center" border="1" bordercolor="blue" width="80%">
    	<tr>
    		<th>书名</th>
    		<th>价格</th>
    		<th>类别</th>
    	</tr>
    	<c:forEach items="${books }" var="book">
    		<tr>
    			<td>${book.bname }</td>
    			<td>${book.price }</td>
    			<c:choose>
    				<c:when test="${book.category eq '1' }">
    					<td style="color:yellow;">JavaSE</td>
    				</c:when>
    				<c:when test="${book.category eq '2' }">
    					<td style="color:red;">JavaEE</td>
    				</c:when>
    				<c:when test="${book.category eq '3' }">
    					<td style="color:green;">JavaFram</td>
    				</c:when>
    			</c:choose>
    		</tr>
    	</c:forEach>
    </table>
  </body>
</html>
