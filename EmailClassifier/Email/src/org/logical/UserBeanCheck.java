package org.logical;

import org.Bean.UserBean;
import org.DAO.ControllDBDAO;
import org.DAO.DAOImpl.ControllDBDAOImpl;

public class UserBeanCheck {
	private static UserBeanCheck usercheck =null;
	private UserBeanCheck(){
	//私有的构造方法	
	}

	public static UserBeanCheck getInstance(){
		if(usercheck == null){
			usercheck = new UserBeanCheck();
		}
		return usercheck;
	}
	
	public UserBean checkUser(String name,String password){
		System.out.println(name);
		System.out.println(password);
		String sql = "select * from user where userName=? and password =?";
		ControllDBDAO dao = new ControllDBDAOImpl();
		UserBean userBean = null;
		try {
			userBean = dao.login(sql,name,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userBean;
		
	}

}
