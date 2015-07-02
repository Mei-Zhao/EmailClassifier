package org.DAO;

import java.sql.ResultSet;

import org.Bean.UserBean;


public interface ControllDBDAO {
	public UserBean login(String sql,String name,String password)throws Exception;
	public void executeUpdate(String sql)throws Exception;
	public ResultSet executeQuery(String sql) throws Exception;
}
