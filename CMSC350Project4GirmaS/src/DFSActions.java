 /**
 * DFSActions<V>.java - a generic interface that declares 4 method which are implemented in Hierarchy and ParenthesizedList
 * classes. 
 * 
 * @author  Girma, Senay
 * @version 1.0 10/24/2022
 * Project 4
 */
public interface DFSActions<V> {
	
	public void cycleDetected();
	public void processVertex(V v);
	public void descend(V v);
	public void ascend(V v);

}
