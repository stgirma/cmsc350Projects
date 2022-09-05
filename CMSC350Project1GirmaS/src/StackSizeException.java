/**
 * EmptyStackException.java - Checked exception class that is thrown when a method tries to pop an element from 
 * a stack that's empty 
 * 
 * @author  Girma, Senay
 * @version 1.0 09/02/2022
 * 
 * Project 1
 */

public class StackSizeException extends RuntimeException {
	private String message;
	//the Stack size, negative indicating not enough element and positive indicating elements still left in Stack
	private int size;
	
	public StackSizeException(int size) {
		this.size = size;
		if(size<0) {
			message = "The program is trying to access an element from a Stack that is Empty";
		}else if(size >0) {
			message = "There is a syntax error in the expression. Elments still left in the Stack after the conversion";
		}
		
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " : " + message;
	}

}
