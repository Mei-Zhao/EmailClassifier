package org.EmailClassifier.function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.Bean.Message;



public class EmailData {

    
    /*
     * List of html files that we will treat as emails.
     */
    public static String[][] TRAINING_DATA = new String[][] {
    	///项目名/WebRott目录下的XML文件名，这就OK。
    	//{"src/org/ssports-1.txt"},
    	//{"/sports-1.txt"},
    	//{"/Email/WebRoot/sports-1.txt"},
    	{"E://data//sports-1.txt"},
    	{"E://data//sports-2.txt"},
    	{"E://data//sports-3.txt"},
    	{"E://data//biz-1.txt"},
    	{"E://data//biz-2.txt"},
    	{"E://data//biz-3.txt"},
    	{"E://data//spam-1.txt"},
    	{"E://data//spam-2.txt"},
    	{"E://data//spam-3.txt"},
    	{"E://data//world-1.txt"},
    	{"E://data//world-2.txt"},
    	{"E://data//world-3.txt"},


    };

//    public static String[][] TEST_DATA = new String[][] {
//        {"/data/ch02/biz-01.html", "aa@senderhost", "100@host"},
//        {"/data/ch02/sport-01.html", "bb@senderhost","101@host"},
//        {"/data/ch02/usa-01.html", "cc@senderhost", "102@host"},
//        {"/data/ch02/world-01.html", "dd@senderhost", "103@host"},
//        {"/data/ch02/spam-biz-01.html", "friend@senderhost", "104@host"}
//    };
    
    public static List<Message> loadEmails(String[][] allEmails) throws Exception {
        
        List<Message> emailList = new ArrayList<Message>();
        for(String[] emailData : allEmails) {
            String filename = emailData[0];
            Message email = loadEmailFromFile(filename);
            
            emailList.add(email);
        }
        
        return emailList;
    }
    
    public static EmailDataset createTrainingDataset() {
        List<Message> allEmails = null;
		try {
			allEmails = loadEmails(TRAINING_DATA);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new EmailDataset(allEmails);
    }

//    public static EmailDataset createTestDataset() {
//        List<Message> allEmails = loadEmails(TEST_DATA);
//        return new EmailDataset(allEmails);
//    }
    
    public static Message loadEmailFromFile(String htmlFile) throws Exception {
    	Message email = new Message();
    	System.out.println("htmlFile is:"+ htmlFile);
    	//URL str = EmailData.class.getResource(htmlFile);
    	File file = new File(htmlFile);//创建本地文件从txt读取
    	List al = new ArrayList();
        String s  = null;
        int id = 0;
        String sender  = null;
        String receiver  = null;
        String theme = null;
        String message = null;
        
    	FileReader fr = new FileReader(file);
    	BufferedReader br = new BufferedReader(fr);
    	while((s=br.readLine())!=null){
    		al.add(s);	
    	}
    	br.close();
    	fr.close();
    	
    	{
    		String str1 = (String) al.get(0);
    		Integer it = new Integer(str1);
    		id = it.intValue();//已经成功读取数据
    		System.out.println(id);
    		sender = (String) al.get(1);
    		
    		receiver = (String) al.get(2);
    
    		theme = (String) al.get(3);
    	
    	
    		message = (String) al.get(4);
    	
    		
    		email.setId(id);
    		email.setSender(sender);
    		email.setReceiver(receiver);
    		email.setTheme(theme);
    		email.setMessage(message);      
    	}
    	 return email;
    }
}
