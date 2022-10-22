import java.util.LinkedList;
import java.util.Queue;


public class Hierarchy<V> implements DFSActions<V> {

	StringBuilder outPutString = new StringBuilder();
	Queue<String> outPut = new LinkedList<>();
	
	@Override
	public void cycleDetected() {
		outPut.add("*");
		outPutString.append("*");
	}

	@Override
	public void processVertex(V v) {
		outPut.add(v.toString());	
		outPutString.append(v.toString());
	}

	@Override
	public void descend(V v) {
		outPut.add("(");
		outPutString.append("(");
	}

	@Override
	public void ascend(V v) {
		outPut.add("");
		outPutString.append("");
		
	}

	@Override
	public String toString() {
		
		return outPutString.toString();
	}


}
