 /**
 * ParenthesizedList<V>.java - Generic class which implements DFSActions. it keeps track of the traversal during the 
 * depth first search. each action results in the construction of parenthesized representation of the graph. 
 * 
 * @author  Girma, Senay
 * @version 1.0 10/24/2022
 * Project 4
 */
import java.util.LinkedList;
import java.util.Queue;

public class ParenthesizedList<V> implements DFSActions<Object> {

	StringBuilder outPutString = new StringBuilder();
	Queue<String> outPutQueue = new LinkedList<>();
	String indent = "";
	
	@Override
	public void cycleDetected() {
		outPutQueue.add("*");
	}

	@Override
	public void processVertex(Object v) {
		outPutQueue.add(v.toString());
		
	}

	@Override
	public void descend(Object v) {
		outPutQueue.add("(");
		
	}


	@Override
	public void ascend(Object v) {
		outPutQueue.add(")");
		//indent = indent.substring(4);
		
	}

	/**
	 * toString - Overridden method to construct the string representation from the list of of actions listed in the 
	 * queue object. This method is inspired suggestions made in class discussion
	 * 
	 */
	
	@Override
	public String toString() {
		StringBuilder outPutString = new StringBuilder();
		outPutString.append("( ");
		for (String nextString : outPutQueue) {
			
			outPutString.append(" "+nextString+" ");
			
		}
		outPutString.append(" )");

		String returnString = outPutString.toString();
		//when there is no dependency level difference between consecutive classes, the bracket is still added in the queue.
		//remove the two brackets.
		returnString = returnString.replace("  (  *  )  ", " * ");
		returnString = returnString.replace("  (  )  ", " ");

		return returnString;

	}

}
