/**
 * 
 */
package org.EmailClassifier.function;

import  org.EmailClassifier.function.Attribute;

/**
 * @author babis
 *
 */
public interface Instance {

	public Attribute[] getAtrributes();
	
	public Concept getConcept();
	
	public void print();
	
	public Attribute getAttributeByName(String attrName); 
}
