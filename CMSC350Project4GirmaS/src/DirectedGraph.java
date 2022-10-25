/**
 * DirectedGraph<V>.java - Generic class that constructs a class dependency graph of type V (vertex). The class has
 * nested static Node class which represents each vertices of the graph. The class provides DepthFirsSearch method which
 * perform a search in a depth first format. 
 * 
 * @author  Girma, Senay
 * @version 1.0 10/24/2022
 * Project 4
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class DirectedGraph<V> {

	/**
	 * Nested Static Node class - represents each vertex of the graph. The LinkedList<Node> inside Node class holds
	 * all the dependents of that class.
	 * 
	 */
	static class Node {
		String nodeName;
		Node nextNode;
		LinkedList<Node> dependents = new LinkedList<>();

		/**
		 * Constructor - Instantiates nodeName to the value specified in the parameter and dependents to null;
		 * 
		 */
		Node(String nodeName) {
			this.nodeName = nodeName;
			this.dependents = null;
		}

		@Override
		public String toString() {
			return nodeName;
		}
	}

	//classes that display the dependency graph in two different formats
	Hierarchy hierarchy = new Hierarchy();
	ParenthesizedList parenthesized = new ParenthesizedList();

	public V headNode = null;//search starts here
	
	//collections used to keep track of the nodes during iteration while searching
	public Set<V> visited = new HashSet<V>();
	public Set<V> discovered = new HashSet<V>();
	ArrayList<V> unreachableList = new ArrayList<V>();
	
	boolean isCircular = false;//if a dependency is dependent on it's own dependent, it creates a decency cycle.
	public LinkedList<V> adjacencyList = new LinkedList<V>();//represents the graph
	Node currentNode = null;

	/**
	 * addDependency - dependency is the object that the dependent is dependent on. The method checks adjacencyList if 
	 * the provided Node object already exists, and adds if it doesn't. The Added node has to be casted to V type.
	 * 
	 * @param dependency: Node object to be added.
	 */
	public void addDependency(Node dependency) {
		if (adjacencyList == null || !adjacencyList.contains(dependency)) {
			adjacencyList.add((V) dependency);
		}
	}

	/**
	 * addDependent - the provided dependent Node is added to the LinkedList of the dependency Node provided in the param.
	 * 
	 * @param dependency:Node object that hosts the LinkedList that holds the dependent Node object.
	 * @param dependent: Node object to be added in LinkedList of the dependency Node object.
	 */
	public void addDependent(Node dependency, Node dependent) {
		Node tempNode = getNode(dependent.nodeName);
		if (tempNode != null) {
			dependent = tempNode;
		}

		dependency.dependents.add(dependent);
	}

	/**
	 * getNode - searches adjacency list for Node object with node name that matches what's provided in parameter. 
	 * 
	 * @param className: name of the class to be retrieved.
	 */
	public Node getNode(String className) {

		for (V v : adjacencyList) {
			Node foundNode = (Node) v;
			if (foundNode.nodeName.equals(className))
				return foundNode;
		}
		return null;
	}

	/**
	 * depthFirstSearch - calls the helper class providing headNode as a parameter.
	 * 
	 * @param headNode: object where search starts. 
	 */
	public void depthFirstSearch() {
		depthFirsSearchHelper(headNode);
	}

	/**
	 * depthFirsSearchHelper - The helper class where all the search is performed recursively. If a class has dependents
	 * that classes dependents are exhausted first before returning to calling class. 
	 * 
	 * @param node: Generic type that represnts the Node to be searched.. 
	 */
	private void depthFirsSearchHelper(V node) {
		if (discovered.contains(node)) {
			//there is a circular reference where a dependency is dependent on its own dependent. 
			isCircular = true;
			hierarchy.cycleDetected();
			parenthesized.cycleDetected();
			return;
		}

		//Hierarchy and ParenthizedList class are notified each step taken so they can construct the graph representation
		hierarchy.processVertex(node);
		parenthesized.processVertex(node);

		hierarchy.descend(node);
		parenthesized.descend(node);

		//keep track of what's visited and when new graph node is discovered.
		discovered.add(node);
		visited.add(node);

		//helps with class specific functionality
		Node currentNode = (Node) node;
		currentNode = getNode(currentNode.nodeName);

		//recursive part where the same function is called with the next node in the dependent list inside Node.
		if (currentNode != null) {
			LinkedList<Node> currentDependentList = currentNode.dependents;

			for (int i = 0; i < currentDependentList.size(); i++) {

				depthFirsSearchHelper((V) currentDependentList.get(i));
			}
		}

		//finished dependency for that level. Go back up to previous stack
		hierarchy.ascend(node);
		parenthesized.ascend(node);
		discovered.remove(node);

	}

	/**
	 * getUnreachableList - Onec graph is constructed and a search is performed, all the nodes that were not found in the
	 * search are added to the callection.
	 * 
	 */
	public ArrayList<V> getUnreachableList() {

		Node currentNode;
		Set<Node> visitedNodes = (Set<Node>) visited;
		//check if dependencies exist in vistied collection
		for (int i = 0; i < adjacencyList.size(); i++) {
			currentNode = (Node) adjacencyList.get(i);
			if (!visitedNodes.contains(currentNode)) {
				unreachableList.add((V) currentNode);
				visitedNodes.add(currentNode);
			}

			//check if dependents existt in visited collection
			LinkedList<Node> dependentsList = currentNode.dependents;
			for (int j = 0; j < dependentsList.size(); j++) {
				if (!visitedNodes.contains(dependentsList.get(j))) {
					unreachableList.add((V) dependentsList.get(j));
					visitedNodes.add(currentNode);
				}

			}

		}

		return unreachableList;
	}
}
