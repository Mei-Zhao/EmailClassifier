<%@page import="org.logical.doPage"%>
<%@page import="org.Bean.Message"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% 
	doPage pageME = new doPage();
	int id = Integer.parseInt(request.getParameter("id"));
	Message message = pageME.getMessageOne(id);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'detail.jsp' starting page</title>
    
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
    <p align="center"><strong>邮件内容</strong></p>
   	<table width="600"border="0" align="center" cellpadding="2" cellspacing="10">
   		<tr>
   			<td width="70" height="30" align="center">发件人</td>
   			<td ><input type="text" name="sender" size="65" readonly="readonly" value=<%= message.getSender()%>></td>
   		</tr>
   		<tr>
   			<td height="30" align="center">收件人</td>
   			<td><input type="text" name="receiver" size="65" readonly="readonly" value=<%= message.getReceiver()%> ></td>
   		</tr>
   		<tr>
   			<td height="30"align="center">主题</td>
   			<td ><input type="text" name="theme" size="65" readonly="readonly" value=<%= message.getTheme()%> ></td>
   		</tr>
		<tr >
   			<td height="30"align="center">邮件内容</td>
   			<td >
   				<textarea name="message" rows="12" cols="55" readonly="readonly" ><%= message.getMessage()%> </textarea>
   			</td>
   		</tr>
   		<tr >
   			<td  colspan="2" align="center" height="30">
   			<a href="<%=request.getParameter("src")%>?username=<%= request.getParameter("username")%>&pageNow=<%=request.getParameter("pageNow")%>" shape="poly">
			返回</a>
			
   			<a href="reply.jsp?receiver=<%= message.getReceiver()%>&sender=<%= message.getSender()%>&theme=<%= message.getTheme()%>" shape="poly">
			回复</a>
			</td>	
   		</tr>
   		
   	</table>
  </body>
</html>
