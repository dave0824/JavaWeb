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
  	<h1>登录</h1>
  <%
	/*
	读取名为uname的Cookie!
	如果为空显示：""
	如果不为空显示：Cookie的值
	*/
	String uname = "";
	Cookie[] cs = request.getCookies();//获取请求中所有的cookie
	if(cs != null) {// 如果存在cookie
		for(Cookie c : cs) {//循环遍历所有的cookie
			if("uname".equals(c.getName())) {//查找名为uname的cookie
				uname = c.getValue();//获取这个cookie的值，给uname这个变量
			}
		}
	}
 %>
	<%
		String message = "";
		String msg = (String)request.getAttribute("msg");//获取request域中的名为msg的属性
		if(msg != null) {
			message = msg;
		}
	%>
	<font color="red"><b><%=message %> </b></font>
    <form action="/TestSession/testSessionServlet" method="post">
               姓名：<input type="text" name="name" value="<%=uname %>"/><br/>
               密码：<input type="password" name="password" /><br/>
               验证码：<input type="text" name="vcode" size="3"/>
             <img id="img" src="/TestSession/VerifyCodeServlet"/>
               <a href="javascript:_replace()"><font size="2">看不清，换一张</font></a><br/>
           <input type="submit" value="提交"/>
    </form>
  </body>
  <script type="text/javascript">
  	function _replace(){
		var img = document.getElementById("img");
		img.src = "/TestSession/VerifyCodeServlet?a="+new Date().getTime();
	}
  </script>
</html>
