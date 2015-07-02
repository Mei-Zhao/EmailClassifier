<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'write.jsp' starting page</title>
    
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
   <p align="center"><strong>回复邮件</strong></p>
   <form method="post" action="controller">
    <input type="hidden" name="action" value="write" >
   	<table width="600"border="0" align="center" cellpadding="2" cellspacing="10">
   		<tr>
   			<td width="70" height="30" align="center">发件人</td>
   			<td ><input type="text" name="sender" size="65" readonly="readonly" value=<%= request.getParameter("receiver")%>></td>
   		</tr>
   		<tr>
   			<td height="30" align="center">收件人</td>
   			<td><input type="text" name="receiver" size="65" readonly="readonly" value=<%= request.getParameter("sender")%>></td>
   		</tr>
   		<tr>
   			<td height="30"align="center">主题</td>
   			<td ><input type="text" name="theme" size="65" readonly="readonly" value=<%= request.getParameter("theme")%>></td>
   		</tr>
		<tr >
   			<td height="30"align="center">邮件内容</td>
   			<td >
   				<textarea name="message" rows="12" cols="55"></textarea>
   			</td>
   		</tr>
   		<tr>
   			<td colspan="3" align="center" height="30">
   			<input type="submit" value="发送"><input type="reset" value="重置">
   			</td>	
   		</tr>
   		
   	</table>
   </form>
   	
  </body>
</html>
