import java.util.ArrayList;
import java.util.LinkedList;

public class TesterGraph<V> {

	static class Node{
		String className;
		Node nextNode;
		public Node(String className) {
			this.className = className;
			this.nextNode = null;
		}
		
	}

	Node dependencyNode=null;
	public ArrayList<LinkedList<V>> adjecencyList;
	
	TesterGraph(){
		adjecencyList = new ArrayList<LinkedList<V>>();
	}
	
	public void addDependency(Node node, int adjencyListIndex) {
		LinkedList<V> dependencyList = new LinkedList();
		dependencyList.add((V) node);
		adjecencyList.add(adjencyListIndex, dependencyList);
	}
	
	public void addDependent(Node dependentNode, int adjecencyListIndex) {
		adjecencyList.get(adjecencyListIndex).add((V) dependentNode);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (LinkedList<Node> linkedList : adjecencyList) {
			for (Node node : linkedList) {
				sb.append(node.className);
			}
		}
		
		
		return null;
		
	}
	

}
