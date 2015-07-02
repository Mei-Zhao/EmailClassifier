package org.EmailClassifier.function;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.Bean.Message;

public class EmailDataset {
    
    private Map<Integer, Message> emails;
    
    // By default we set up  an email dataset for binary classification
    private boolean isBinary=true;
    
    public EmailDataset(List<Message> emailList) {
        this.emails = new HashMap<Integer, Message>(emailList.size());
        for(Message e : emailList) {
            emails.put(e.getId(), e);
        }
    }

    public List<Message> getEmails() {
        return new ArrayList<Message>(emails.values());
    }
    
    public Message findEmailById(String id) {
        return emails.get(id);
    }
    
    public void printEmail(String id) {
    	Message e = findEmailById(id);
        if( e != null ) {
            System.out.println(e.toString());
        }
        else {
            System.out.println("Email not found (email id: '" + id + "')");
        }
    }
    
    public void printAll() {
        for(Entry<Integer, Message> e : emails.entrySet()) {
        	Message email = e.getValue();
            System.out.println(email);
        }
    }

    public int getSize() {
        return emails.size();
    }
    
	/**
	 * @return the isBinary
	 */
	public boolean isBinary() {
		return isBinary;
	}

	/**
	 * @param isBinary the isBinary to set
	 */
	public void setBinary(boolean isBinary) {
		this.isBinary = isBinary;
	}

    
    public TrainingSet getTrainingSet(int topNTerms) {
    	
        List<EmailInstance> allInstances = createEmailInstances(topNTerms);
        EmailInstance[] instances = 
            allInstances.toArray(new EmailInstance[allInstances.size()]); 
        return new TrainingSet(instances);
    }

    private List<EmailInstance> createEmailInstances(int topNTerms) {
        List<EmailInstance> allInstances = new ArrayList<EmailInstance>();
        
        for(Message email : getEmails()) {
            EmailInstance i = toEmailInstance(email, topNTerms);
            
            allInstances.add(i);
            
        }
       
        return allInstances;
    }

    public EmailInstance toEmailInstance(Message email, int topNTerms) {
        String emailCategory = getEmailCategory(email);
        return new EmailInstance(emailCategory, email, topNTerms);
    }
        
    private String getEmailCategory(Message email) {
        
//    	if (isBinary()) {
//    		if( true) {
//    			//email.getId().startsWith("spam-")
//            return "SPAM";
//    		}
//	        else {
//	            return "NOT SPAM";
//	        }
//    	} else {
//    		// relying id to have pattern: "biz-???", "world-???", ...
//	        String[] parts = null;
//	        	//email.getId().split("-");
//	        if(parts.length < 2) {
//	            throw new RuntimeException(
//	                    "Unsupported id format. Expected id format: '<catgory>-???'");
//	        }
//	        return parts[0].toUpperCase();
//    	}
    	return email.getTheme();
    }
}
