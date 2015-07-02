package org.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {
	//关闭连接
	public static void closeObject(Connection conn){
		try{
			if(conn != null){
				conn.close();	
			}	
		} catch (SQLException e) {
				System.out.println("关闭connection异常");
				e.printStackTrace();
			}
	}
	//关闭结果集
	public static void closeObject(ResultSet rs)
	{
		try{
			if(rs != null){
				rs.close();
			}
		}catch (SQLException e) {
			System.out.println("关闭ResultSet异常");
			e.printStackTrace();
		}
		
	}
	//关闭语句块
	public static void closeObject(Statement st)
	{
		try{
			if(st != null){
				st.close();
			}
		}catch (SQLException e) {
			System.out.println("关闭Statement异常");
			e.printStackTrace();
		}
		
	}
	
	public static void closeObject(Statement st,Connection conn)
	{
		closeObject(st);
		closeObject(conn);
		
	}
	
	public static void closeObject(ResultSet rs,Statement st,Connection conn)
	{
		closeObject(rs);
		closeObject(st, conn);
	}
	

}
