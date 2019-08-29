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
    
    <title>My JSP 'json2.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="<c:url value='/ajax-lib/ajaxutils.js'/>"></script>
<script type="text/javascript">
window.onload = function(){
/*
	得到所有的省份
*/
	var province = document.getElementById("province");
		
		ajax(
				{
					url:"<c:url value = 'ProvinceServlet'/>",
					type:"json",
					callback:function(data){
						for(var i=0;i<data.length;i++){
							var data1 = data[i];
							var option = document.createElement("option");
							option.value = data1.pid;
							var text = document.createTextNode(data1.pname);
							option.appendChild(text);
							province.appendChild(option);
						}
							
					}
				}
			);
			
/*
	通过省份id得到城市
*/
 	
	
/*
	province.onchange = function(){
		ajax(
				{
					url:"<c:url value = 'CityServlet'/>",
					type:"json",
					method:"POST",
					params:"id="+this.value,
					callback:function(data){
					var city = document.getElementById("city");
					var cityOption = city.getElementsByTagName("option");
						while(cityOption.length>1){
								city.removeChild(cityOption(1));
						}
						for(var i=0;i<data.length;i++){
							var data1 = data[i];
							var option = document.createElement("option");
							var text = document.createTextNode(data1.cname);
							option.appendChild(text);
							city.appendChild(option);
						}
							
					}
				}
			);
	}; */
	
	province.onchange = function(){
		var xmlHttp = createXMLHttpRequest();
		xmlHttp.open("POST","<c:url value='CityServlet'/>",true);
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xmlHttp.send("id="+this.value);//将选中的省份传给servlet
		xmlHttp.onreadystatechange = function(){
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
				
				//把select中的所有option移除（除了请选择）
				
				var citySelect = document.getElementById("city");
				var optionEleList = citySelect.getElementsByTagName("option");
				// 循环遍历每个option元素，然后在citySelect中移除
				while(optionEleList.length > 1){
					citySelect.removeChild(optionEleList[1]);//总是删除下标为1的元素，因为删除了1,2就变成1了
				}
				
				var text = xmlHttp.responseText;
				var citys = eval("("+text+")");
				
				for(var i=0;i<citys.length;i++){
					var city1 = citys[i];
					//使用cityName创建option添加到citySelect下面
					var option = document.createElement("option");
					var textNode = document.createTextNode(city1.cname);
					option.appendChild(textNode);
					citySelect.appendChild(option);
				}
			}
		};
	};
};
</script>
  </head>
  
  <body>
    省：<select id="province">
    	<option>---请选择省份---</option>
    </select>
    市：<select id="city">
    	<option>---请选择地区---</option>
    </select>
  </body>
</html>
