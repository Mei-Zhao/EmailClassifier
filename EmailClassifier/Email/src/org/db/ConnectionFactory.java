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
		//������
		InputStream is = Thread.currentThread().getContextClassLoader()
		.getResourceAsStream("jdbc.properties");
		try{
			//���������е����ݼ��سɼ�ֵ��
			
			prop.load(is);	
			
		}catch(IOException e){
			System.out.println("���������ļ�����");	
			e.printStackTrace();
			
		}
		//��ȡ�����ļ���ֵ
		this.driver = (String)prop.get("driver");
		this.dbURL = (String)prop.get("url");
		this.user = (String)prop.get("user");
		this.password = (String)prop.get("password");
		
	}
	
	public static Connection getConnection(){
		Connection conn = null;
		//��factory������ʱ����һ��connectionfactory����
		if(factory == null){
			try{
				factory = new ConnectionFactory();
				
			}catch(Exception e){
				System.out.println(e.getMessage());
				e.printStackTrace();
				return null;
				
			}
		}
		//������Ӷ�����ڣ����ȡ���Ӷ���
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
