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
		 trainEmailDS = EmailData.createTrainingDataset();//训练集
		 emailFilter = new EmailClassifier(trainEmailDS, 2);//分类器有训练集创建
		 emailFilter.train();//训练分类器
	}
	

	public String useclassifier(Message email){
//		 trainEmailDS = EmailData.createTrainingDataset();//训练集
//		 emailFilter = new EmailClassifier(trainEmailDS, 2);//分类器有训练集创建
//		 emailFilter.train();//训练分类器
		String str = null; 
		str = this.emailFilter.classify(email);
		return str;
	
	}


}
