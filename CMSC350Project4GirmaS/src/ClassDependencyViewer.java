import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class ClassDependencyViewer {

	static DirectedGraph graph = new DirectedGraph();

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

			while (dependenceReader.hasNextLine()) {

				String dependencyString = dependenceReader.nextLine();
				String[] dependencyListArray = dependencyString.split(" ");

				DirectedGraph.Node dependencyNode = new DirectedGraph.Node(dependencyListArray[0]);

				if (graph.headNode == null) {
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

	public static void main(String[] args) {
		ClassDependencyViewer classDependencyViewr = new ClassDependencyViewer();
		// Hierarchy h = new Hierarchy();
		classDependencyViewr.readDependencyFromFile();
		// graph.printAdjacencyList();
		// graph.test();

		graph.depthFirstSearch();
		// System.out.println(graph.parenthesized.toString());
		// System.out.println(graph.hierarchy.toString());

		System.out.println("================================================");

		System.out.println(graph.hierarchy.toString());

		System.out.println("================================================");

		System.out.println(graph.parenthesized.toString());
		// System.out.println(graph.visited.size());

		// System.out.println("Discovered: " + graph.discovered);
		// System.out.println("Visited: " + graph.visited);

		System.out.println("finished running");
		// System.out.println("UnreachableList: " + graph.getUnreachableList());
	}

}