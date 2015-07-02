package org.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {
	//�ر�����
	public static void closeObject(Connection conn){
		try{
			if(conn != null){
				conn.close();	
			}	
		} catch (SQLException e) {
				System.out.println("�ر�connection�쳣");
				e.printStackTrace();
			}
	}
	//�رս����
	public static void closeObject(ResultSet rs)
	{
		try{
			if(rs != null){
				rs.close();
			}
		}catch (SQLException e) {
			System.out.println("�ر�ResultSet�쳣");
			e.printStackTrace();
		}
		
	}
	//�ر�����
	public static void closeObject(Statement st)
	{
		try{
			if(st != null){
				st.close();
			}
		}catch (SQLException e) {
			System.out.println("�ر�Statement�쳣");
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
