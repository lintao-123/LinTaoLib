<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>错误页面</title>
<style type="text/css">
#time{
	position:absolute;
	top:200px;
	left:500px;
}

</style>
</head>
<body onload="shownum()">
	<script type="text/javascript"> 
	var t=3;//设定跳转的时间 
	setInterval("refer()",1000); //启动1秒定时 
	function refer(){  
	    if(t==0){ 
	        location="login.html"; //设定跳转的链接地址 
	    } 
	    document.getElementById('show').innerHTML="用户名或密码错误<br><font color='red'size='10'>"+t+
	    "</font>秒后跳回登录页面"; // 显示倒计时 
	    t--; // 计数器递减 
	} 
	</script> 
	<center>
	<div id="time">
	<font size='6'>
   <span  id="show"></span> 
   <font>
   </div>
   </center>
</body>
</html>