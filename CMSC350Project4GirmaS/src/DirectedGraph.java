import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Consumer;
import java.util.*;

public class DirectedGraph<V> {
	
	static class Node{
		String nodeName;
		LinkedList<Node> dependents = new LinkedList<>();
		Node(String nodeName){
			this.nodeName = nodeName;
			this.dependents = dependents;
		}
		
		public LinkedList<Node> getDependents() {
			return dependents;
		}

		public void setDependents(LinkedList<Node> dependents) {
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
	//Set<V> visited = new HashSet<V>();
	ArrayList<V> visited = new ArrayList<V>();
	Set<V> discovered = new HashSet<V>();
	boolean isCircular = false;
	ArrayList<V> unreachableList = new ArrayList<V>();
	
	public ArrayList<LinkedList<V>> adjacencyList = new ArrayList<LinkedList<V>>();
	
	public void printAdjacencyList() {
		for (int i = 0; i < adjacencyList.size(); i++) {
			for (int j = 0; j < adjacencyList.get(i).size(); j++) {

				}
			//System.out.println("printing "+ adjacencyList.get(i));
			printLinkedList((LinkedList<Node>) adjacencyList.get(i));
			}
			
		}
	
	public void printLinkedList(LinkedList<Node> linkedList) {
		linkedList.forEach((nd) ->{
			//System.out.println(nd.toString());
			//System.out.println(nd.dependents.toString());
			
		});
	}
	
	/*
	public Node getNode(Node node) {
		Node foundNode = null;
		for (int i = 0; i < adjacencyList.size(); i++) {
			if(node.equals(adjacencyList.get(i).getFirst()))
				foundNode = node;
			foundNode = null;
		}
		return foundNode;
	}

	
	public Node getNode(Node node) {
		LinkedList<Node> ll = new LinkedList();
		Node foundNode = null;
		for (int i = 0; i < adjacencyList.size(); i++) {
			
			adjacencyList.get(i).forEach((Node)(nd) ->{
				
			});
			
			
			
			if(node.equals(adjacencyList.get(i).getFirst()))
				foundNode = node;
			foundNode = null;
		}
		return foundNode;
	}
		*/
	public void addDependency(Node dependency) {
		LinkedList<V> dependencyList = new LinkedList();
		dependencyList.add((V) dependency);
		adjacencyList.add(dependencyList);
		
		
		//System.out.println("Dependency list size is now " + dependencyList.size());
		//System.out.println("AdjacencyList list size is now " + adjacencyList.size());
		
		

	}
	
	public void addDependent(Node dependency, Node dependent) {
		adjacencyList.forEach((nodeLL) ->{
			if(nodeLL.getFirst()==dependency)
				dependency.dependents.addLast(dependent);
		});
	}
	
	public void depthFirstSearch() {
		isCircular = false;
		depthFirsSearchHelper(headNode);
	}

	private void depthFirsSearchHelper(V node) {
		if(discovered.contains(node)) {
			isCircular = true;
			hierarchy.cycleDetected();
			parenthesized.cycleDetected();	
			return;
		}
		
		hierarchy.processVertex((DirectedGraph.Node) node);
		parenthesized.processVertex((DirectedGraph.Node) node);
		
		hierarchy.descend((DirectedGraph.Node) node);
		parenthesized.descend((DirectedGraph.Node) node);
		
		discovered.add(node);
		visited.add(node);
		
		Node currentNode = null;
		currentNode = (Node) node;
		LinkedList<V> dependentList = (LinkedList<V>)getDependentsList(currentNode);
		try {
			
			System.out.println("current node: " +currentNode.nodeName);
			System.out.println("current node dependents: " + dependentList);
		}catch(Exception e) {
			
		}
	
		
		//LinkedList<V> dependentList = (LinkedList<V>)currentNode.dependents;
		//System.out.println("current node dependents: " +currentNode.dependents);
		dependentList.forEach((dependentNode) ->{
			if(dependentNode != null)
				depthFirsSearchHelper(dependentNode);
			
		});
	}
	
	public LinkedList<Node> getDependentsList(Node n) {
		LinkedList<V> dependencyList = new LinkedList();

		for (int i = 0; i < adjacencyList.size(); i++) {
			Node indexedNode=null;
			
			for (int j = 0; j < adjacencyList.get(i).size(); j++) {
				indexedNode = (DirectedGraph.Node) adjacencyList.get(i).get(j);
				if(n.nodeName == indexedNode.nodeName) {
					System.out.println("Getting dependents for node "+ indexedNode.nodeName);
					return indexedNode.dependents;
				}	
			}	
		}
				 
		return null;
	}
	
	
	public void getUnreachableList() {
		for (int i = 0; i < adjacencyList.size(); i++) {
			for (int j = 0; j < adjacencyList.get(i).size(); j++) {
				if(!visited.contains(adjacencyList.get(i).get(j))) {
					unreachableList.add(adjacencyList.get(i).get(j));
					Node currentNode = (Node)adjacencyList.get(i).get(j);
					LinkedList<Node> dependentList = currentNode.dependents;
					for (Node n : dependentList) {
						if(!visited.contains((V)n))
							unreachableList.add((V)n);
					}
					
				}
			}
			
		}
	}
		

}


