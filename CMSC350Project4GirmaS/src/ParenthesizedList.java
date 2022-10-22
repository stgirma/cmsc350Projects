import java.util.LinkedList;
import java.util.Queue;

public class ParenthesizedList<V> implements DFSActions<Object> {

	public Queue<String> outPut = new LinkedList<>();
	
	@Override
	public void cycleDetected() {
		outPut.add("*");
	}

	
	@Override
	public void processVertex(Object v) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void descend(Object v) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void ascend(Object v) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		return sb.toString();
	}

}
