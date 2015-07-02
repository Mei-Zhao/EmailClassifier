package org.EmailClassifier;

import org.Bean.Message;
import org.EmailClassifier.function.EmailClassifier;
import org.EmailClassifier.function.EmailData;
import org.EmailClassifier.function.EmailDataset;
import org.omg.CORBA.PUBLIC_MEMBER;


public class ClassfierUse {
	private   EmailDataset trainEmailDS;
	private   EmailClassifier emailFilter;
	
	public ClassfierUse()
	{
		// Create and train classifier
		 trainEmailDS = EmailData.createTrainingDataset();//ѵ����
		 emailFilter = new EmailClassifier(trainEmailDS, 2);//��������ѵ��������
		 emailFilter.train();//ѵ��������
	}
	

	public String useclassifier(Message email){
//		 trainEmailDS = EmailData.createTrainingDataset();//ѵ����
//		 emailFilter = new EmailClassifier(trainEmailDS, 2);//��������ѵ��������
//		 emailFilter.train();//ѵ��������
		String str = null; 
		str = this.emailFilter.classify(email);
		return str;
	
	}


}
