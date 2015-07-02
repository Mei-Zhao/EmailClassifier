package org.DAO.DAOImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.Bean.UserBean; 
import org.DAO.ControllDBDAO;
import org.db.ConnectionFactory;
import org.db.DatabaseUtils;

public class ControllDBDAOImpl implements ControllDBDAO{
	//��¼��֤
	
	public UserBean login(String sql,String name,String password) throws Exception{
		Connection conn = null;
		ResultSet rs = null;
		int count = 0;
		UserBean userBean = new UserBean();
		//��ֹע��©��
		PreparedStatement pstmt = null;
		try{
			conn = ConnectionFactory.getConnection();
			pstmt = conn.prepareStatement(sql) ;
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			while(rs.next()){
				userBean.setName(rs.getString("userName"));
				userBean.setPassword(rs.getString("password"));
				count++;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			DatabaseUtils.closeObject(rs,pstmt,conn);		
		}
		return userBean;
	}
	//ִ��insert��update��delete���
	
	public void executeUpdate(String sql)throws Exception{
		Connection conn = null;
		Statement stmt = null;
		try{
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
		}catch(SQLException e){
			e.printStackTrace();
			System.err.println("ִ��SQL������" + e.getMessage());
		}finally{
			DatabaseUtils.closeObject(stmt, conn);
		}
		
	}
	//ִ��select���
	
	public ResultSet executeQuery(String sql) throws Exception{
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		try{
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
		}catch(SQLException e){
			throw e;			
		}
		return rs;		
	}


}
