/**
 * ajax的通用方法
 */
//创建XmlHttp
function createXMLHttpRequest() {
	try {
		return new XMLHttpRequest();//大多数浏览器
	} catch (e) {
		try {
			return ActvieXObject("Msxml2.XMLHTTP");//IE6.0
		} catch (e) {
			try {
				return ActvieXObject("Microsoft.XMLHTTP");//IE5.5及更早版本	
			} catch (e) {
				alert("哥们儿，您用的是什么浏览器啊？");
				throw e;
			}
		}
	}
}

/*
 * option对象有如下属性
 
 		请求方式:method, 
		请求的url: url, 
		是否异步:asyn, 
		请求体:params, 
		回调方法:callback,
		服务器响应数据转换成什么类型:type
*/
function ajax(option){
	/*
	 * 1.得到xmlHttp
	 */
	xmlHttp = createXMLHttpRequest();
	if(!option.method){//设置method默认为GET请求
		option.method = "GET";
	}
	
	if(option.asyn == undefined){//设置默认为异步
		option.asyn = true;
	}
	/*
	 * 2.打开连接
	 */
	xmlHttp.open(option.method,option.url,option.asyn);
	
	if(option.method == "POST"){
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		alert(option.params);
	}
	/*
	 * 3.发送请求体
	 */
	xmlHttp.send(option.params);
	
	/*
	 * 注册监听
	 */
	xmlHttp.onreadystatechange = function(){
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200){//双重判断
			var data;
			// 获取服务器的响应数据，进行转换！
						if(!option.type) {//如果type没有赋值，那么默认为文本
							data = xmlHttp.responseText;
						} else if(option.type == "xml") {
							data = xmlHttp.responseXML;
						} else if(option.type == "text") {
							data = xmlHttp.responseText;
						} else if(option.type == "json") {
							var text = xmlHttp.responseText;
							data = eval("(" + text + ")");
						}
						
						// 调用回调方法
						option.callback(data);
			
		}
	};
	
};