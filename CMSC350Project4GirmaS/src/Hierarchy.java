import java.util.LinkedList;
import java.util.Queue;

public class Hierarchy<V> implements DFSActions<V> {

	StringBuilder outPutString = new StringBuilder();
	Queue<String> outPutQueue = new LinkedList<>();
	String indent = "";

	@Override
	public void cycleDetected() {
		outPutQueue.add(" *");
	}

	@Override
	public void processVertex(V v) {
		outPutQueue.add(indent + v.toString());
	}

	@Override
	public void descend(V v) {
		indent = indent + "    ";

	}

	@Override
	public void ascend(V v) {
		indent = indent.substring(4);

	}

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
