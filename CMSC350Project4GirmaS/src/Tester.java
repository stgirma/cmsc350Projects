import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Tester<V> {

	static TesterGraph testerGraph = new TesterGraph();
	static TesterGraph.Node node;
	public static void fileReader() {
		File inputFile=null;
		Scanner scanner;
		
		
		JFileChooser fileSelector = new JFileChooser("C:\\Users\\senay\\Downloads\\classDependency");
		int result = fileSelector.showOpenDialog(null);       
	       if (result == JFileChooser.APPROVE_OPTION)
	    	   inputFile = fileSelector.getSelectedFile();
	       else
	       {
	           JOptionPane.showMessageDialog(null, "No file selected. Please selecte a valid file");
	           System.exit(1);
	       }
	       try {
	    	   
	    	   		scanner = new Scanner(inputFile);
	    	   		int lineIndex=0;
	    	   		int classIndex=0;
	    	   		
	    	   		while(scanner.hasNextLine()) {
	    	   		
	    	   		node = null;	
					String dependencyListString = scanner.nextLine();
					String[] dependencyList = dependencyListString.split(" ");
					
					//System.out.println(dependencyList.length);
					
					for (int i = 0; i < dependencyList.length; i++) {
						if(i==0) {
							System.out.println("\nHead Node " + dependencyList[i]);
							//System.out.println();
							node=new TesterGraph.Node(dependencyList[i]);
							testerGraph.addDependency(node, lineIndex);
						}else {
							System.out.print(dependencyList[i] + "  ");
							node=new TesterGraph.Node(dependencyList[i]);
							testerGraph.addDependent(node, lineIndex);
						}
						//System.out.println();
						
						
					}
					
					lineIndex++;
					
//					for (String string : dependencyList) {
//						System.out.println(string);
//					}
					//System.out.println("=========================next line===============");	
	    	   		}
				}catch(Exception e) {
					JOptionPane.showMessageDialog(new JFrame(), e.toString(), "Dialog", JOptionPane.ERROR_MESSAGE);
					System.exit(1);
				}
			}
	     
	static class Node{
		String NodeName;
		LinkedList<Node> dependents = new LinkedList<>();
		Node(String nodeName){
			this.NodeName = nodeName;
		}
		@Override
		public String toString() {
			return NodeName;
		}
	}
	public static LinkedList<Node> addLinkedList(String name, String handle) {
		LinkedList<Node> handle1 = new LinkedList<Node>();
		handle1.add(new Node(name));
		return handle1;
	}
	
	
	public static void main(String[] args) {
		//fileReader();
		//String moveForward = "          ";
		
		//System.out.print("The quick brownfor\n" + moveForward +"jumps ofver the lazy");

		ArrayList<LinkedList<Node>> adj = new ArrayList<LinkedList<Node>>();
		
		adj.add(addLinkedList("ClassA", "A"));
		adj.add(addLinkedList("ClassB", "B"));
		adj.add(addLinkedList("ClassC", "C"));
		adj.add(addLinkedList("ClassD", "D"));
		adj.add(addLinkedList("ClassE", "E"));
		
		adj.get(3).addLast(new Node("Completly Different"));
		
		
	
		adj.forEach((ll) ->{
			if(ll.getFirst().NodeName == "ClassB")
				System.out.println(ll.getFirst());
		});
		System.out.println("====================");
		adj.forEach((ll) ->{
			
			System.out.println(ll.getLast());
		});
	}

}
