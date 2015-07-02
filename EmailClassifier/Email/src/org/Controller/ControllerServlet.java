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
	 * ������������ҳ����ת
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException,IOException {

    request.setCharacterEncoding("utf-8");
    String action = request.getParameter("action");
    
    if(isValidated(request) && ("index".equals(action)))
    {
    	//��֤�û��ѵ�¼
    	gotoPage("index.jsp",request,response);
    }
    
    if("index".equals(action))
    {
    	//����ģʽ
    	UserBeanCheck usercheck = UserBeanCheck.getInstance();
    	//��֤����֤
    	String validateCode = request.getParameter("validateCode");
        String validateCodeInSession = (String) request.getSession().getAttribute("rand");
        
    	if (validateCodeInSession == null || validateCode == null

	              ||!validateCode.equalsIgnoreCase(validateCodeInSession)) {

	           request.setAttribute("msg", "��֤�����");
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
    		//�û���֤���Ƿ���Ч�û���������
    		if(name.equals(str1) && password.equals(str2)){
    			request.getSession().removeAttribute("rand");
    			request.setAttribute("name", request.getParameter("userName"));
    			HttpSession session = request.getSession();
    			//��user���󱣴浽session�У���emailҳ����ͨ��sessionȡ��user����
    			session.removeAttribute("userobj");
    			session.setAttribute("userobj", user);
    			gotoPage("email.jsp", request, response);
    			}
    		else {
    		
    				//�û���֤ʧ��
    				request.getSession().removeAttribute("rand");
    				request.setAttribute("msg", "�û��������������");
    				gotoPage("index.jsp", request, response);
    		
    			}
    	}
    }
    //����������action����
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
