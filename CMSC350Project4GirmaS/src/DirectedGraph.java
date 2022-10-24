import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Consumer;
import java.time.temporal.TemporalAdjuster;
import java.util.*;

public class DirectedGraph<V> {
	
	static class Node{
		String nodeName;
		Node nextNode;
		LinkedList<Node> dependents = new LinkedList<>();
		Node(String nodeName){
			this.nodeName = nodeName;
			this.dependents = dependents;
		}

		@Override
		public String toString() {
			return nodeName;
		}
	}
	
	Hierarchy hierarchy = new Hierarchy();
	ParenthesizedList parenthesized = new ParenthesizedList();
	
	public V headNode = null;
	public Set<V> visited = new HashSet<V>();
	public Set<V> discovered = new HashSet<V>();
	boolean isCircular = false;
	ArrayList<V> unreachableList = new ArrayList<V>();
	

	
	public LinkedList<V> adjacencyList = new LinkedList<V>();
	Node currentNode=null;
	public void printAdjacencyList() {
		for (V v : adjacencyList) {
			currentNode = (Node)v;
			System.out.println("Node: " + currentNode.toString() + "=> " + currentNode.dependents);
		}
			
	}
	
	public void addDependency(Node dependency) {
		if(adjacencyList==null || !adjacencyList.contains(dependency)) {
			adjacencyList.add((V) dependency);
		}
	}
	
	public void addDependent(Node dependency, Node dependent) {
		Node tempNode = getNode(dependent.nodeName);
		if(tempNode!=null) {
			//System.out.println("tempNode is : "+tempNode.nodeName);
			dependent = tempNode;
		}
		//System.out.println(dependent.nodeName +" is added to  "+dependency.nodeName);
		dependency.dependents.add(dependent);
	}

	public Node getNode(String className) {	
		
		for (V v : adjacencyList) {
			Node foundNode = (Node)v;
			if(foundNode.nodeName.equals(className))
				return foundNode;	
		}
		return null;
	}
	
	public void test() {
		Node tempNode;
		tempNode = getNode("ClassB");
		if(tempNode!=null) {
			System.out.println("tempNode is" + tempNode);
			System.out.println("tempNode dependentsa are" + tempNode.dependents);
		}else {
			System.out.println("tempNode.dependets are null");
		}
	}
	public void depthFirstSearch() {
		isCircular = false;
		depthFirsSearchHelper(headNode);
	}

	private void depthFirsSearchHelper(V node) {
		if(discovered.contains(node)) {
			isCircular = true;
			//System.out.println("Cycle detected: " + node);
			hierarchy.cycleDetected();
			parenthesized.cycleDetected();	
			return;
		}
		//System.out.println("dfs method started for: " + node);
		hierarchy.processVertex(node);
		parenthesized.processVertex(node);
		
		hierarchy.descend(node);
		parenthesized.descend(node);
		

		discovered.add(node);
		visited.add(node);

		
		Node currentNode = (Node) node;
		// System.out.println("currentNode name is: " + currentNode.nodeName);
		currentNode = getNode(currentNode.nodeName);
		// System.out.println("returned currentNode dependents are: " +
		// currentNode.dependents);
		// System.out.println("currentNode is " +currentNode);
		if (currentNode != null) {
			LinkedList<Node> currentDependentList = currentNode.dependents;
			//System.out.println("Dependents for currentNode are " + currentDependentList);

			for (int i = 0; i < currentDependentList.size(); i++) {
				//System.out.println("Running dfs on dependent " + currentDependentList.get(i));
				depthFirsSearchHelper((V) currentDependentList.get(i));
			}
		}
		//System.out.println("Exited recursion loop");
		hierarchy.ascend(node);
		parenthesized.ascend(node);
		discovered.remove(node);

		//System.out.println(hierarchy.toString());
	}
	
	
	
	public ArrayList<V> getUnreachableList() {

		Node currentNode;
		Set<Node> visitedNodes = (Set<Node>)visited;
		for (int i = 0; i < adjacencyList.size(); i++) {
			currentNode = (Node) adjacencyList.get(i);
			if(!visitedNodes.contains(currentNode)) {
				unreachableList.add((V) currentNode);
				visitedNodes.add(currentNode);
			}
			
			LinkedList<Node> dependentsList =  currentNode.dependents;
			for (int j = 0; j < dependentsList.size(); j++) {
				if(!visitedNodes.contains(dependentsList.get(j))) {
					unreachableList.add((V) dependentsList.get(j));
					visitedNodes.add(currentNode);
				}
				
			}
			
		}
		
		
		
		return unreachableList;
	}
}


