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
    
    <title>My JSP 'activation.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<!-- <script type="text/javascript">
	var email = document.getElementById("email");
	email.change = function(){
		var info = this.val();
		alert(info);
		var a = document.getElementById("a");
		a.href = "<c:url value='/UserServlet?method=send&email='/>"+info;
		alert(a.href);
	};
</script> -->
<!-- <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
	<script type="text/javascript">
	$("#email").ready(function(){
       //点击链接的时候调用
	  $("#a").click(function(){
		alert(1);
          //得到input的值
	  	var email = $("#email").val();
          //设置linkToCart的href的值
	      $("#a").attr("href","<c:url value='/UserServlet?method=send&email='/>"+email);
	  });
	});
</script> -->
<!-- <script type="text/javascript">
	var email = document.getElementById('email');
	var a = document.getElementById("aa");
	var sHref = a.getAttribute('href');
	
	email.oninput = set;
	function set (){
		a.setAttribute('href', sHref+email.value);
	}
	
	function pSet(event){
		var event = event || window.event;
		if(event.propertyName === 'value'){
			this.onpropertychange = null;
			set();
			this.onpropertychange = pSet;
		}
	}
	
	email.onpropertychange = pSet;
</script> -->
<script type="text/javascript">
window.onload = function(){
	var email = document.getElementById("email");
	email.onchange = function(){
		var aa = document.getElementById("aa");
		aa.href = "<c:url value='/UserServlet?method=send&email='/>" + email.value; 
	};
};
</script>
  </head>
 
  <body>
  	<h2>激活账户</h2>
   	<p style="color: red; font-weight: 900">${msg }</p>
  	<form action="<c:url value='/UserServlet'/>" method="post">
  		<input type="hidden" name="method" value="active"/>
  		激活账号邮箱：<input type="text" id="email" name="email" value="${email }"/><a id="aa" href="<c:url value='/UserServlet?method=send&email=${email }'/>">点击获取激活码</a><br/>
  		激   活   秘  钥：	<input type="text" name="code"/><br/>
  		<input type="submit" value="提交">
  	</form>
  	  <a href="<c:url value='/index.jsp'/>">主页</a>&nbsp;
  		<a href="<c:url value='/jsps/user/login.jsp'/>">登录</a>&nbsp;
  		<a href="<c:url value='/jsps/user/regist.jsp'/>">注册</a>
  </body>
</html>
