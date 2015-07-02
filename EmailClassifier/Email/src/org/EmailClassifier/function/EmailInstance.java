package org.EmailClassifier.function;



import java.util.Map;
import org.Bean.Message;

/**
 * Instance for classification.
 */
public class EmailInstance extends BaseInstance {
    
    private static int DEFAULT_TOP_N_TERMS = 10;
    
    private int id;
    
    public EmailInstance(String emailCategory, Message email) {
        this(emailCategory, email, DEFAULT_TOP_N_TERMS);
    }
    
    public EmailInstance(String emailCategory, Message email, int topNTerms) {
        super();
        this.id = email.getId();
       
        // email category is our concept
        this.setConcept(new BaseConcept(emailCategory));//概念
        
        /**
         * TODO: 5.3 -- Considering more attributes as part of the EmailInstance
         * 
         *   -- Separate "subject" and "body"
         *   -- timestamp
         *   -- "from"
         *   -- "to"
         *   -- "to" cardinality
         */
        // extract top N terms from email content and subject
        String text = email.getTheme() + " " + email.getMessage(); 
       
        Content content = new Content(email.getId(), text, topNTerms);
        Map<String, Integer> tfMap = content.getTFMap();
        
        attributes = new StringAttribute[1];//包括属性名和valus值
        
        String attrName = "Email_Text_Attribute";
        String attrValue = "";
        for(Map.Entry<String, Integer> tfEntry : tfMap.entrySet()) {
            attrValue = attrValue + " " + tfEntry.getKey();
        }
       
        attributes[0] = new StringAttribute(attrName,attrValue);
    }
    
    
}
