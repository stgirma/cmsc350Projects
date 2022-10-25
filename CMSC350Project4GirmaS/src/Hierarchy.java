 /**
 * Hierarchy<V>.java - Generic class which implements DFSActions. it keeps track of the traversal during the depth first
 * search. each action results in the construction of hierarchical representation of the graph. 
 * 
 * @author  Girma, Senay
 * @version 1.0 10/24/2022
 * Project 4
 */
import java.util.LinkedList;
import java.util.Queue;

public class Hierarchy<V> implements DFSActions<V> {

	//Queue is used to keep track of each action and each action is retrieved to build the string represntation
	StringBuilder outPutString = new StringBuilder();
	Queue<String> outPutQueue = new LinkedList<>();
	String indent = "";

	@Override
	public void cycleDetected() {
		//calss depends on it's own dependent creating a circular reference
		outPutQueue.add(" *");
	}

	@Override
	public void processVertex(V v) {
		outPutQueue.add(indent + v.toString());
	}

	@Override
	public void descend(V v) {
		//if we move from one level of dependency to another add 4 space indentation to separate that level
		indent = indent + "    ";

	}

	@Override
	public void ascend(V v) {
		//if we move from one level of dependency to another remove 4 spaces 
		indent = indent.substring(4);

	}

	/**
	 * toString - Overridden method to construct the string representation from the list of of actions listed in the 
	 * queue object. This method is inspired suggestions made in class discussion
	 * 
	 */
	@Override
	public String toString() {

		StringBuilder outPutString = new StringBuilder();
		for (String nextString : outPutQueue) {
				if (!nextString.equals(" *"))
					outPutString.append("\n");
			
			outPutString.append(nextString);
		}
		outPutString.append("\n");

		return outPutString.toString();

	}

}
