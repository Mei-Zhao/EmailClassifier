package org.Bean;

public class Message {
	private int id;
	private String sender;
	private String receiver;
	private String theme;
	private String message;
	private String flag;
	public Message(){
		
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getSender() {
		return sender;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getTheme() {
		return theme;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getFlag() {
		return flag;
	}

//    int ruleFired = 0;
//    
//    public int getRuleFired() {
//        return ruleFired;
//    }
//
//    public void setRuleFired(int ruleNum) {
//        System.out.println("Invoked " + this.getClass().getSimpleName() + ".setRuleFired(" + ruleNum + "), current value ruleFired=" + this.ruleFired + ", emailId: " + id );        
//        this.ruleFired = ruleNum;
//    }
//
//
//
//    @Override
//	public String toString() {
//        return "id: " + id + "\n" + 
//               "sender: " + sender + "\n" +
//               "receiver: " + receiver + "\n" +
//               "theme: " + theme + "\n" + 
//               message + "\n";
//    }
	

}
