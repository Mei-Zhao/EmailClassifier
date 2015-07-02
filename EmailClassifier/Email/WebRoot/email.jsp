<%@page import="org.Bean.UserBean"%>
<%@ page language="java" import="java.util.*" contentType="text/html" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 	 <base href="<%=basePath%>">
   	<title>Email 'email.jsp' </title>  
   	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
   	<link rel="stylesheet" type="text/css" href="css/style.css">	  	
  </head>
  <body>
   <form  method="post" id="myform" action="controller">
   <input type="hidden" name="action" value="email" >
  	<div id="top">
  		<div class="block">
  		<strong>用户:</strong>
  		<%
  		String struser = (String) request.getAttribute("name");
  		out.print(struser);
  		 %>
  		 <strong>您好！</strong>
  		</div>
  		<div id="exit">
  			<a href="index.jsp?usernamemain=a">
  			<%= (String)((UserBean)request.getSession().getAttribute("userobj")).getName() %><strong>退出</strong>
  			</a>
  		</div>
  	</div>
  	<div id ="bottom">
  		<hr size="1" color="blue" width="96%" align="center">
  			<div id="left" >
  				<div>
  				<a href="write.jsp?username=<%= request.getAttribute("name") %>" target="10-20"><img src="css/pictures/write.jpg" ></a>
  				<a href="receive.jsp?username=<%= request.getAttribute("name") %>" target="10-20"><img src="css/pictures/receive.jpg" ></a>
  					

  				</div>
  				
  				<div>
  					<ul id="nav" >
  						<li><a href="receive.jsp?username=<%= request.getAttribute("name") %>" target="10-20">收件箱</a></li>
  						<li><a href="send.jsp?username=<%= request.getAttribute("name") %>" target="10-20">已发送</a></li>	
  						<li><a href="biz.jsp?username=<%= request.getAttribute("name") %>" target="10-20">商业</a></li>	
  						<li><a href="pe.jsp?username=<%= request.getAttribute("name") %>" target="10-20">体育</a></li>
  						<li><a href="world.jsp?username=<%= request.getAttribute("name") %>" target="10-20">世界</a></li>
  						<li><a href="rubbish.jsp?username=<%= request.getAttribute("name") %>" target="10-20">垃圾</a></li>
  					</ul>	
  				</div>		
  			</div>
  			<div id="right">
  				<div>
  					<iframe src="receive.jsp?username=<%= request.getAttribute("name") %>" name="10-20"  width="1000px" height="500px" frameboder="0px"></iframe>		
  				</div>
  				
  				<p align="center">版权所有@窝窝</p>		
  			
  			</div>
  	</div>
  </form>	
</body>
</html>