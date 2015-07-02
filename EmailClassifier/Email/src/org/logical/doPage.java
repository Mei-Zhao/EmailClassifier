package org.logical;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.Bean.Message;
import org.DAO.ControllDBDAO;
import org.DAO.DAOImpl.ControllDBDAOImpl;

public class doPage {
	//定义每页显示数量
	private int PageSize = 8;
	//定义当前页面
	private int PageNow = 1;
	//定义总行数，查询数据库可得
	private int RowCount ;
	//定义总页数，计算可得
	private int PageCount;
	public doPage(){
		
	}
	public int getPageSize() {
		return PageSize;
	}

	public int getPageNow() {
		return PageNow;
	}
	public void setRowCount1(String usernsme) throws Exception{
		int RowCount = 0;
		ResultSet rs = null;
		ControllDBDAO dao = new ControllDBDAOImpl();
		String sql = "SELECT COUNT(id) FROM email where receiver='"+usernsme+"' AND flag NOT LIKE'spam' ";
		rs = dao.executeQuery(sql);
		while(rs.next()){
			RowCount = rs.getInt(1);
			
		}
		rs.close();
		
		this.RowCount = RowCount;
		
	}
	public void setRowCount1_flag(String usernsme,String flag) throws Exception{
		int RowCount = 0;
		ResultSet rs = null;
		ControllDBDAO dao = new ControllDBDAOImpl();
		String sql = "SELECT COUNT(id) FROM email where receiver='"+usernsme+"' AND flag LIKE '"+flag+"' ";

		rs = dao.executeQuery(sql);
		while(rs.next()){
			RowCount = rs.getInt(1);
			System.out.println(RowCount);
		}
		rs.close();
		
		this.RowCount = RowCount;
		
	}
	public List<Message> selectByUser_Flag(String username,String flag,int page_count,int row) throws Exception{
		List<Message> list = new ArrayList<Message>();
		ResultSet rs = null;
		ControllDBDAO dao = new ControllDBDAOImpl();
		String sql = "SELECT * FROM email WHERE receiver='"+username+"' and flag  LIKE'"+flag+"'  LIMIT "+(page_count-1)*row+"," +row;
		rs = dao.executeQuery(sql);
		while(rs.next()){
			Message message = new Message();
			message.setId(rs.getInt("id"));
			message.setReceiver(rs.getString("receiver"));
			message.setSender(rs.getString("sender"));
			message.setTheme(rs.getString("theme"));
			message.setMessage(rs.getString("message"));
			list.add(message);
		}
		return list;
	}
	public void setRowCount2(String usernsme) throws Exception{
		int RowCount = 0;
		ResultSet rs = null;
		ControllDBDAO dao = new ControllDBDAOImpl();
		String sql = "SELECT COUNT(id) FROM email where sender='"+usernsme+"'";
		rs = dao.executeQuery(sql);
		while(rs.next()){
			RowCount = rs.getInt(1);
		}
		rs.close();
		
		this.RowCount = RowCount;
		
	}
	public int getRowCount(){
		return RowCount;
		
	}
	public void setPageCount(){
		if(this.RowCount % this. PageSize == 0){
			this.PageCount = (RowCount / PageSize);
		}else{
			this.PageCount = (RowCount / PageSize + 1);
			}
	}
	public int getPageCount(){
		return PageCount;
		
	}
	
	public List<Message> selectByUser1(String username,int page_count,int row) throws Exception{
		List<Message> list = new ArrayList<Message>();
		ResultSet rs = null;
		ControllDBDAO dao = new ControllDBDAOImpl();
		String sql = "SELECT * FROM email WHERE sender='"+username+"' LIMIT "+(page_count-1)*row+"," +row;
		
		rs = dao.executeQuery(sql);
		while(rs.next()){
			Message message = new Message();
			message.setId(rs.getInt("id"));
			message.setReceiver(rs.getString("receiver"));
			message.setSender(rs.getString("sender"));
			message.setTheme(rs.getString("theme"));
			message.setMessage(rs.getString("message"));
			list.add(message);
		}
		return list;
	}
	public List<Message> selectByUser2(String username,int page_count,int row) throws Exception{
		List<Message> list = new ArrayList<Message>();
		ResultSet rs = null;
		ControllDBDAO dao = new ControllDBDAOImpl();
		String sql = "SELECT * FROM email WHERE receiver='"+username+"' and flag NOT LIKE'spam'  LIMIT "+(page_count-1)*row+"," +row;
		rs = dao.executeQuery(sql);
		while(rs.next()){
			Message message = new Message();
			message.setId(rs.getInt("id"));
			message.setReceiver(rs.getString("receiver"));
			message.setSender(rs.getString("sender"));
			message.setTheme(rs.getString("theme"));
			message.setMessage(rs.getString("message"));
			list.add(message);
		}
		return list;
	}
	public Message getMessageOne(int id) throws Exception{
		Message message = new Message();
		ResultSet rs = null;
		ControllDBDAO dao = new ControllDBDAOImpl();
		String sql = "SELECT * FROM email WHERE id='"+id+"' ";
		rs = dao.executeQuery(sql);
		while(rs.next()){		
			message.setId(rs.getInt("id"));
			message.setReceiver(rs.getString("receiver"));
			message.setSender(rs.getString("sender"));
			message.setTheme(rs.getString("theme"));
			message.setMessage(rs.getString("message"));			
		}
		return message;		
	}

}
