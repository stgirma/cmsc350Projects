import java.io.File;
import java.io.FileNotFoundException;
import java.time.temporal.TemporalAdjuster;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;


public class ClassDependencyViewer{
	
	static DirectedGraph graph = new DirectedGraph();
	
	public void readDependencyFromFile() {
    
    Scanner scanner;
    File inputFile = null;

    JFileChooser fileSelector = new JFileChooser("C:\\Users\\senay\\Downloads\\classDependency");
   // JFileChooser fileSelector = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    int result = fileSelector.showOpenDialog(null);
    if(result == JFileChooser.APPROVE_OPTION)
        inputFile = fileSelector.getSelectedFile();
    else{
        JOptionPane.showMessageDialog(null, "No file selected. Please selecte a file to read class dependencies from");
        System.exit(1);
    }

    
        try {
        	scanner = new Scanner(inputFile);
        	//System.out.println("Entering while loop to read file line by line");
            while(scanner.hasNextLine()){
            	//System.out.println("reading next line");
                String dependencyString = scanner.nextLine();
                String[] dependencyListArray = dependencyString.split(" ");
                
                DirectedGraph.Node dependencyNode = new DirectedGraph.Node(dependencyListArray[0]);
                graph.addDependency(dependencyNode); 
                if(graph.headNode == null)
                    graph.headNode = dependencyNode;
                //System.out.println("dependency node created and instantiated with node.name " + dependencyNode.nodeName);
                for(int i=1; i<dependencyListArray.length;i++){
                    DirectedGraph.Node dependentNode = new DirectedGraph.Node(dependencyListArray[i]);
                    graph.addDependent(dependencyNode, dependentNode);
                    //System.out.println("creating the rest of the nodes..." + dependentNode.nodeName);
                }
                
                
            }
            //System.out.println("No more lines to read ");
            
        } catch (ArrayIndexOutOfBoundsException | FileNotFoundException e) {
        	JOptionPane.showMessageDialog(null, "Encountered a problem instantiating graph edges. Exiting...");
            System.exit(1);
        }
    
	}


    public static void main(String[] args) {
    	ClassDependencyViewer classDependencyViewr = new ClassDependencyViewer();
    	Hierarchy h = new Hierarchy();
    	classDependencyViewr.readDependencyFromFile();
    	graph.depthFirstSearch();
    	//System.out.println(graph.parenthesized.toString());
    	//System.out.println(graph.hierarchy.toString());
    	
    	
    	
    	graph.printAdjacencyList();
    	//System.out.println(graph.visited.size());

    	System.out.println("finished running");
    }

}