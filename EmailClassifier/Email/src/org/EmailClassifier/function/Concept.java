/**
 * 
 */
package org.EmailClassifier.function;

/**
 * @author 
 *
 */
public interface Concept {

	public String getName();
	
	public Concept getParent();
	
	public Instance[] getInstances();
}
