<%@page import="org.logical.MessageBiz"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'delete.jsp' starting page</title>
    
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
 	MessageBiz biz = new MessageBiz ();		
	int id = Integer.parseInt(request.getParameter("id"));		
	if (biz.DeleteMessage(id)) {				
		RequestDispatcher rd;
		request.setAttribute("username",request.getParameter("username"));
		rd = request.getRequestDispatcher("receive.jsp");
		rd.forward(request, response);
	} else {
		session.setAttribute("message", "删除失败！");
		response.sendRedirect("errors.jsp");
	}
	
%>
  </body>
</html>
