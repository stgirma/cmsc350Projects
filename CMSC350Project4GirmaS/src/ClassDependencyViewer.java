/**
 * CMSC350Project4GirmaS.java - Main class to get user to select a class dependency file. This class instantiates and
 * implements DirectedGraph, Hierarchy, and ParenthesizedList classes to get all the formated dependency representation.
 * 
 * @author  Girma, Senay
 * @version 1.0 10/24/2022
 * Project 4
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class ClassDependencyViewer {

	static DirectedGraph graph = new DirectedGraph();

	/**
	 * readDependencyFromFile - Method to give user the ability to select a file. the method reads each line of the file
	 * and convert it to string array. Each array entry is converted Node object and added to LinkedList 
	 * 
	 */
	public void readDependencyFromFile() {

		File inputFile = null;

		JFileChooser fileSelector = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int result = fileSelector.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION)
			inputFile = fileSelector.getSelectedFile();
		else {
			JOptionPane.showMessageDialog(null,
					"No file selected. Please selecte a file to read class dependencies from");
			System.exit(1);
		}

		try {
			Scanner dependenceReader = new Scanner(inputFile);
			Scanner dependentReader = new Scanner(inputFile);

			/*The file is read twice. on the first run, 0th index of every line is used to instantiate the "dependency"
			 * object and on the second run, the rest of the line are added as dependent object to the respective dependency.
			 * 
			 * */
			while (dependenceReader.hasNextLine()) {

				String dependencyString = dependenceReader.nextLine();
				String[] dependencyListArray = dependencyString.split(" ");

				DirectedGraph.Node dependencyNode = new DirectedGraph.Node(dependencyListArray[0]);

				if (graph.headNode == null) {//this is done only once to assign the head node, which is used to start the search.
					graph.headNode = dependencyNode;
					graph.adjacencyList.add(dependencyNode);
				} else if (!graph.adjacencyList.contains(dependencyNode)) {
					DirectedGraph.Node previousNode = (DirectedGraph.Node) graph.adjacencyList.getLast();
					graph.adjacencyList.add(dependencyNode);
					previousNode.nextNode = dependencyNode;
				}

			}

			DirectedGraph.Node currentDependencyNode = (DirectedGraph.Node) graph.headNode;
			while (dependentReader.hasNextLine()) {

				String dependentString = dependentReader.nextLine();
				String[] dependentListArray = dependentString.split(" ");

				for (int i = 1; i < dependentListArray.length; i++) {
					DirectedGraph.Node dependentNode = new DirectedGraph.Node(dependentListArray[i]);
					graph.addDependent(currentDependencyNode, dependentNode);

				}

				currentDependencyNode = currentDependencyNode.nextNode;

			}

		} catch (ArrayIndexOutOfBoundsException | FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Encountered a problem instantiating graph edges. Exiting...");
			System.exit(1);
		}

	}

	/**
	 * Main - Main method of the program, which instantiates all the classes in the program and runs all result
	 * display.
	 * 
	 * @param args: default argument list string array: Not used.
	 */
	public static void main(String[] args) {
		ClassDependencyViewer classDependencyViewr = new ClassDependencyViewer();
		classDependencyViewr.readDependencyFromFile();
		
		graph.depthFirstSearch();

		System.out.println("=============================================================");
		System.out.println("Hierarchical representation of Class Dependency:");

		System.out.println(graph.hierarchy.toString());

		System.out.println("=============================================================");
		System.out.println("Parenthesized List representation of Class Dependency:");

		System.out.println(graph.parenthesized.toString());

		System.out.println("finished running");

	}

}