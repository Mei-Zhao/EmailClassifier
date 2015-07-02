package org.logical;

import org.Bean.Message;
import org.DAO.ControllDBDAO;
import org.EmailClassifier.ClassfierUse;
//过滤器所在：垃圾分类和自动归档
//写邮件插入数据库
public class MessageBiz {
	private Message ms;
	public MessageBiz(){
		
	}
	public MessageBiz(Message ms){
		this.ms = ms;
		ClassfierUse use = new ClassfierUse();
		String str = use.useclassifier(ms);
		ms.setFlag(str);
		
	}
	public void InsertMessage(){
		
		ControllDBDAO dao = new org.DAO.DAOImpl.ControllDBDAOImpl();
		String sql = "INSERT INTO email VALUES (NULL,'"+ms.getSender()+"','"+ms.getReceiver()+"','"+ms.getTheme()+"','"+ms.getMessage()+"','"+ms.getFlag()+"');";
		try {
			dao.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public boolean DeleteMessage(int id){
		boolean binary = false;
		ControllDBDAO dao = new org.DAO.DAOImpl.ControllDBDAOImpl();
		String sql = "delete from email where id = '"+id+"'";
		try {
			dao.executeUpdate(sql);
			binary = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//binary = true;
		}
		return binary;
	}
	public static void main(String args[]){
		
	ClassfierUse use = new ClassfierUse();
	use.useclassifier(null);

	}

}
