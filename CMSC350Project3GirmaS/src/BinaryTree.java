/**
 * BinaryTree.java - Repreresents a binary tree object with nested Node class
 * and multiple methods to access and manipulate properties of a object with
 * binary tree structure.
 * 
 * @author Girma, Senay
 * @version 1.0 10/18/2022
 * 
 *          Project 3
 */

public class BinaryTree {

	private String stringInput; // user provided input string
	private char value; // individual character accessed from input string
	private int stringIndex = 0; // index to keep track of characters from input string
	public boolean isBuilt = false; // status of the building the object to show on GUI
	private static Node root;

	/**
	 * BinaryTree constructor instantiates a binary tree object from string that
	 * represents the structure provided by user. String input is first checked for
	 * syntax error, in which case an exception is thrown.
	 * 
	 */
	public BinaryTree(String stringInput) throws InvalidTreeSyntax {
		this.stringInput = stringInput;

		long openBracketCount = stringInput.chars().filter(ch -> ch == '(').count();
		long closeBracketCount = stringInput.chars().filter(ch -> ch == ')').count();
		long spaceCharCount = stringInput.chars().filter(ch -> ch == ' ').count();

		if (openBracketCount % closeBracketCount != 0) {
			// if number of brackets is not even, a closing or opening bracket is likely
			// left out
			throw new InvalidTreeSyntax(
					"All opening brackets must have matching closing bracket.  " + "Please provide a valid string");
		}

		if (stringInput.length() - (openBracketCount + closeBracketCount + spaceCharCount) == 0) {
			// empty brackets with no data should not be accepted
			throw new InvalidTreeSyntax(
					"Only the brackets of the Binary tree are provided. " + "Data for atleast 1 node is required");
		}

		makeTree(stringInput);

	}

	/**
	 * MakeTree method to construct a binary tree object from user input string, which is validated by the constructor.
	 * 
	 * @param stringInput: validated user provided string representation of the binary structure.
	 * 
	 */
	private Node makeTree(String stringInput) {

		if (stringInput.isEmpty() || stringInput == null || stringIndex >= stringInput.length()) {
			return null;
		}

		//character is a data value
		while (stringIndex < stringInput.length() && stringInput.charAt(stringIndex) != '('
				&& stringInput.charAt(stringIndex) != ')') {
			value = stringInput.charAt(stringIndex);
			stringIndex++;
		}

		Node node = new Node(value);
		
		if (stringIndex == 2) {//first non-bracket letter is the root node
			root = node;
		}

		//notifier user on GUI that binary tree successfully built
		if (stringIndex >= stringInput.length()) {
			isBuilt = true;
			return node;
		}

		//link binary tree nodes for bracket characters
		if (stringIndex < stringInput.length() && stringInput.charAt(stringIndex) == '(') {
			stringIndex++;
			node.leftNode = makeTree(stringInput);
			stringIndex++;

			if (stringIndex < stringInput.length() && stringInput.charAt(stringIndex) == '(') {
				stringIndex++;
				node.rightNode = makeTree(stringInput);
				stringIndex++;
			}
		}

		isBuilt = true; //notifier user on GUI that binary tree successfully built
		return node;
	}

	/**
	 * Node nested class that represents individual node objet with data, rightNode, and leftNode fields.
	 * 
	 */
	public static class Node {
		private char data;
		private Node rightNode;
		private Node leftNode;

		/**
		 * Constructor creates Node objects with data provided as parameter and left and right nodes 
		 * are set as null at construction time.
		 * 
		 */
		public Node(char data) {
			this.data = data;
			this.rightNode = null;
			this.leftNode = null;
		}

	}

	/**
	 * isBalanced method returns true only if the height of the left and right subtrees don't differ by more then 1. 
	 * this method calls a helper function that performs a recursive check on the root node provided as parameter. 
	 * isBalanced is called from the "isBalanced" button on the GUI
	 * 
	 */
	public static String isBalanced() {
		boolean isBalanced = isBalancedHelper(root);
		String result = "The Binary Tree is ";
		result = isBalanced ? result + "Balanced" : result + "Not Balanced";
		return result;
	}
	
	/**
	 * isFull method returns true if the tree contains the maximum number of nodes for it's height. This is calculated using
	 *  the fact that the maximum number of nodes for a tree with height h is 2 to the power of h minus 1. 
	 * this method calls a helper function that performs the calculation. 
	 * isBalanced is called from the "isFull" button on the GUI
	 * 
	 */
	public static String isFull() {
		boolean isFull = false;
		String result = "The Binary Tree is ";
		isFull = isFullHelper(root);
		result = isFull ? result + "Full" : result + "Not Full";
		return result;
	}

	/**
	 * isProper method returns true if there is either 0 or 2 children for each node. This method calls a helper 
	 * function that performs the calculation. isBalanced is called from the "isProper" button on the GUI
	 * 
	 */
	public static String isProper() {
		boolean isProper = false;
		String result = "The Binary Tree is ";
		isProper = isProperHelper(root);

		result = isProper ? result + "Proper" : result + "Not Proper";
		return result;
	}

	/**
	 * height method returns the height of the tree. The height is the longest path from the root to any leaf node. 
	 * This method calls a helper function that performs a recursive check on the root node provided as parameter. 
	 * This method is called from the "height" button on the GUI
	 * 
	 */
	public static String height() {
		int result;
		result = heightHelper(root);
		return Integer.toString(result);
	}

	public static String nodes() {
		int result = countNodes(root);
		return "The number of Nodes in the Binary Tree is: " + Integer.toString(result);
	}

	/**
	 * height method returns the height of the tree. The height is the longest path from the root to any leaf node. 
	 * This method calls a helper function that performs a recursive check on the root node provided as parameter. 
	 * This method is called from the "height" button on the GUI
	 * 
	 */ 
	public static String inorder() {
		StringBuilder sb = new StringBuilder();
		return inOrderHelper(root, sb);
	}

	private static int countNodes(Node node) {

		if (node == null) {
			return 0;
		} else {
			return 1 + countNodes(node.rightNode) + countNodes(node.leftNode);
		}
	}

	private static int heightHelper(Node node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + Math.max(heightHelper(node.leftNode), heightHelper(node.rightNode));
		}

	}

	private static boolean isBalancedHelper(Node node) {
		if (node == null) {
			return true;
		} else {
			int leftHeight = heightHelper(node.leftNode);
			int rightHeight = heightHelper(node.rightNode);

			if (Math.abs(leftHeight - rightHeight) <= 1 && isBalancedHelper(node.leftNode)
					&& isBalancedHelper(node.rightNode)) {
				return true;
			}
		}
		return false;
	}

	private static String inOrderHelper(Node node, StringBuilder sb) {
		if (node == null) {
			return "";
		} else {
			System.out.print(" ( ");
			sb.append(" ( ");
			inOrderHelper(node.leftNode, sb);
			System.out.print(node.data);
			sb.append(node.data);
			inOrderHelper(node.rightNode, sb);
			System.out.print(" ) ");
			sb.append(" ) ");
		}

		return sb.toString();
	}

	private static boolean isFullHelper(Node node) {
		int height = heightHelper(node);
		int nodeCount = countNodes(node);
		int maxNodeForHeight = (int) Math.pow(2, height) - 1;

		if (maxNodeForHeight == nodeCount)
			return true;
		return false;

	}

	private static boolean isProperHelper(Node node) {
		if (node == null)
			return true;
		if (node.leftNode == null && node.rightNode == null)
			return true;
		if (node.leftNode != null && node.rightNode != null)
			return (isProperHelper(node.leftNode) && isProperHelper(node.rightNode));
		return false;
	}

}
