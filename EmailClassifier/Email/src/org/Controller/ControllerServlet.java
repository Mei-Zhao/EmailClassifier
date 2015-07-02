package org.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.Bean.Message;
import org.Bean.UserBean;
import org.logical.MessageBiz;
import org.logical.UserBeanCheck;

public class ControllerServlet extends HttpServlet  {
	/**
	 * 根据条件控制页面跳转
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException,IOException {

    request.setCharacterEncoding("utf-8");
    String action = request.getParameter("action");
    
    if(isValidated(request) && ("index".equals(action)))
    {
    	//验证用户已登录
    	gotoPage("index.jsp",request,response);
    }
    
    if("index".equals(action))
    {
    	//单例模式
    	UserBeanCheck usercheck = UserBeanCheck.getInstance();
    	//验证码验证
    	String validateCode = request.getParameter("validateCode");
        String validateCodeInSession = (String) request.getSession().getAttribute("rand");
        
    	if (validateCodeInSession == null || validateCode == null

	              ||!validateCode.equalsIgnoreCase(validateCodeInSession)) {

	           request.setAttribute("msg", "验证码错误");
	           request.getSession().removeAttribute("rand");
	           
	           gotoPage("index.jsp", request, response);
	           
    	}else{
    		
    		String name = request.getParameter("userName");
    		String password = request.getParameter("userPWD");

    		UserBean user = usercheck.checkUser(name, password);
    		String str1= (user.getName());
    		//System.out.println(str1);
    		String str2= (user.getPassword());
    		//System.out.println(str2);
    		//用户验证：是否有效用户名和密码
    		if(name.equals(str1) && password.equals(str2)){
    			request.getSession().removeAttribute("rand");
    			request.setAttribute("name", request.getParameter("userName"));
    			HttpSession session = request.getSession();
    			//将user对象保存到session中，在email页面中通过session取得user对象
    			session.removeAttribute("userobj");
    			session.setAttribute("userobj", user);
    			gotoPage("email.jsp", request, response);
    			}
    		else {
    		
    				//用户验证失败
    				request.getSession().removeAttribute("rand");
    				request.setAttribute("msg", "用户名或者密码错误");
    				gotoPage("index.jsp", request, response);
    		
    			}
    	}
    }
    //对于其他的action请求
    else if("write".equals(action)){
    	String sender = request.getParameter("sender");
    	String receiver = request.getParameter("receiver");
    	String theme = request.getParameter("theme");
    	String message = request.getParameter("message");
    	Message ms = new Message();
    	ms.setSender(sender);
    	ms.setReceiver(receiver);
    	ms.setTheme(theme);
    	ms.setMessage(message);
    	MessageBiz msbiz = new MessageBiz(ms);
    	msbiz.InsertMessage();
    	gotoPage("ok.jsp", request, response);
    	}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException,IOException {
		doPost(request, response);
	}
	private  boolean isValidated(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		if(session.getAttribute("name")!= null)
		{
			return true;
		}
		return false;
		
	}
	
	private void gotoPage(String targetUrl,HttpServletRequest request,HttpServletResponse response)
	             throws ServletException,IOException
	{
		RequestDispatcher rd;
		rd = request.getRequestDispatcher(targetUrl);
		rd.forward(request, response);
	}

}
