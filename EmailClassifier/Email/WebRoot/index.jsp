<%@ page language="java" import="java.util.*" contentType="text/html" pageEncoding="utf-8"%>
<%

    String path = request.getContextPath();

    String basePath = request.getScheme() + "://"

           + request.getServerName() + ":" + request.getServerPort()

           + path + "/";

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
   	<title>Email 'login.jsp' starting page</title> 
   	
   	<meta http-equiv="pragma" content="no-cache">

    <meta http-equiv="cache-control" content="no-cache">

    <meta http-equiv="expires" content="0">

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

    <meta http-equiv="description" content="This is mypage">
    
   	<link rel="stylesheet" type="text/css" href="css/indexstyle.css">	
   	
   	<script type="text/javascript">
        function refreshcode(){
            document.getElementById("code").src="admin/codemakerServlet?a="+Math.random()+100;
            return false;
        }
    </script>
  </head>
  
  <body>
  	 <%

           String msg = (String)request.getAttribute("msg");

           if(msg != null && msg.length()>0)

           {

       %>

       <font color="red"><%=msg %></font>

       <%

           }

       %>
       
  <form  id="form1" name="loginform" action="controller" method="post">
  <input type="hidden" name="action" value="index" >
  <p class="text" align="center">用户登录</p>
  
  <table width="500" border="0" align="center" cellpadding="1" cellspacing="1" >
    <tr height="35px">
    	<td width="90">&nbsp;&nbsp;用户名</td>
    	<td width="110">
    	<label>
    	<input type="text" name="userName" size="20" maxlength="10"/><br>
    	</label>
    	</td>
    	<td width="300">*最大长度为10位字母或数字组合</td>
    </tr>
   <tr height="35px">
   		<td>&nbsp;&nbsp;密&nbsp;&nbsp;码</td>
   		<td>
   		<label>
   	    <input type="password" name="userPWD" size="20" maxlength="16"/><br>
   		</label>
   		</td>
   		<td>*最大长度为16位字母或数字组合</td>
   </tr>
   <tr height="35px">
   		<td>&nbsp;&nbsp;验证码</td>
   		<td >
   		<label>
   		<input type="text" name="validateCode" size="20" maxlength="4">
   		</label>
   		</td>
   		<td>
   		 <img id="code" src="admin/codemakerServlet" title="看不清点击刷新验证码" style="cursor : pointer;"  onclick="return refreshcode()"/>
   		</td>

   </tr>
   <tr>
    	 <td>&nbsp;</td>
    	 <td>
     	<label>
     	<input type="submit" value="登录" name="Submit">
     	<input type="reset" value="重置" name="Submit">
     	
     	</label>
     	
    	</td>
    	<td>&nbsp;</td>
    </tr>
  </table>
  </form>
  </body>
</html>