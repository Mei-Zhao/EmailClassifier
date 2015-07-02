package org.Bean;

public class UserBean {
	private  String name = null;
	private  String password = null;

	public UserBean(){
		
	}
	public UserBean(String name,String password)
	{
		this.name = name;
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	

}
