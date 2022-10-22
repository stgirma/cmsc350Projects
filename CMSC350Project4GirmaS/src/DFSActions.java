
public interface DFSActions<V> {
	
	public void cycleDetected();
	public void processVertex(V v);
	public void descend(V v);
	public void ascend(V v);

}
