<%@page import="com.sun.org.apache.regexp.internal.RE"%>
<%@page import="org.Bean.Message"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'receive.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<style  type="text/css">
        .Mover
        {
            background: #fff;
        }
        
        .Mon
        {
            background: #f4f4f4;
        }
        .Mclick
        {
            background: #ccc;
        }
    </style>
    <script language="javascript">
        window.onload = function () {
            var trs = document.getElementsByTagName('tr');
            for (var i = 0; i < trs.length; i++) {

                trs[i].onclick = function () {
                    for (var i = 0; i < trs.length; i++) {
                        if (trs[i].className == 'Mclick' && trs[i] != this) {
                            trs[i].className = "Mover";
                        }
                    }
                    this.className = 'Mclick';
                }
                trs[i].onmouseover = function () {
                    if (this.className == 'Mclick') { }
                    else this.className = 'Mon';
                }
                trs[i].onmouseout = function () {
                    if (this.className == 'Mclick') { }
                    else this.className = "Mover";
                }
            }
        }
</script>
  </head>
  
  <body>
    <p align="left"><strong>发件箱</strong></p>
    
   <div>
   <jsp:useBean id="dopage" class="org.logical.doPage" />
	<%
  	String name= (String)request.getParameter("username");
  	int pageSize = dopage.getPageSize();//页面的现实大小
	int pageNow = dopage.getPageNow();//当前页
	dopage.setRowCount2(name);
	dopage.setPageCount();
	int rowCount = dopage.getRowCount();//总行数
	int pageCount= dopage.getPageCount();//总页数
	String s_pageNow = request.getParameter("pageNow");
	if(s_pageNow!=null){

	pageNow=Integer.parseInt(s_pageNow);
	}
  	 %>
  	 <!-- 显示 -->
  	<table  width="980"  frame="hsides" rules="rows"  >
	<tr align="left">
	<td width="200" height="30" align="center">收件人</td>
	<td width="600" height="30" align="left">主题</td>
	</tr>
	<%
	List<Message> list= dopage.selectByUser1(name,pageNow,pageSize);
	Iterator iterator = list.iterator();
	if (list.size() < 1) {
	%>
	<p align="center" ><strong>目前还没有发送邮件</strong></p>
	<%
	}
	for (int i = 0; i < list.size(); i++) {
		Message me = (Message) list.get(i);
	%>
	<tr>
	<td height="30" align="center"><%= me.getReceiver()%></td>
	<td height="30" align="left">
	<a href="detail.jsp?src=send.jsp&pageNow=<%=pageNow%>&id=<%= me.getId() %>&username=<%= request.getParameter("username") %>&pageNow=<%= request.getParameter("pageNow")%>"><%= me.getTheme()%></a>
	</td>

	<%}%>
	</table>

   </div>  
   <div align="center">
 	<%
	
	if(pageNow!=1){
	out.println("<a href=send.jsp?username="+name+"&pageNow="+(pageNow-1)+">上一页</a>");
	}
	
	for(int i = 1;i <= pageCount;i++){
	out.println("<a href=send.jsp?username="+name+"&pageNow="+i+">["+i+"]</a>");
	}

	if((pageNow!=pageCount)&&(pageCount != 0)){
	out.println("<a href=send.jsp?username="+name+"&pageNow="+(pageNow+1)+">下一页</a>");
	}
	%>
   </div>  
  </body>
</html>
