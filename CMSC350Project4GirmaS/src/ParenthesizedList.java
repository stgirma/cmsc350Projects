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

	
	@Override
	public String toString() {
		StringBuilder outPutString = new StringBuilder();
		outPutString.append("( ");
		for (String nextString : outPutQueue) {
			
			outPutString.append(" "+nextString+" ");
			
		}
		outPutString.append(" )");

		String returnString = outPutString.toString();
		returnString = returnString.replace("  (  *  )  ", " * ");
		returnString = returnString.replace("  (  )  ", " ");

		return returnString;

	}

}
