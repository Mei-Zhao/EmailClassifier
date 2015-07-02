package org.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	private String driver = "";
	private String dbURL = "";
	private String user = "";
	private String password = "";
	private static ConnectionFactory factory =null;

	public String getDriver() {
		return driver;
	}

	public String getDbURL() {
		return dbURL;
	}

	public String getUser() {
		return user;
	}
	public String getPassword() {
		return password;
	}
	private ConnectionFactory() throws Exception{
	Properties prop = new Properties();
		//方法链
		InputStream is = Thread.currentThread().getContextClassLoader()
		.getResourceAsStream("jdbc.properties");
		try{
			//从输入流中的数据加载成键值对
			
			prop.load(is);	
			
		}catch(IOException e){
			System.out.println("加载配置文件错误");	
			e.printStackTrace();
			
		}
		//获取配置文件中值
		this.driver = (String)prop.get("driver");
		this.dbURL = (String)prop.get("url");
		this.user = (String)prop.get("user");
		this.password = (String)prop.get("password");
		
	}
	
	public static Connection getConnection(){
		Connection conn = null;
		//当factory不存在时创建一个connectionfactory对象，
		if(factory == null){
			try{
				factory = new ConnectionFactory();
				
			}catch(Exception e){
				System.out.println(e.getMessage());
				e.printStackTrace();
				return null;
				
			}
		}
		//如果连接对象存在，则获取连接对象
		try{
			Class .forName(factory.getDriver());
			conn = DriverManager.getConnection(factory.getDbURL(),factory.getUser(),factory.getPassword()); 
		}catch(ClassNotFoundException e){
			System.out.println("NO Class" + factory.getDriver());
			e.printStackTrace();
		}catch(SQLException e){
			System.out.println("Failed to get connection" + e.getMessage());
			e.printStackTrace(); 
		}
		return conn;
	}
	

}
