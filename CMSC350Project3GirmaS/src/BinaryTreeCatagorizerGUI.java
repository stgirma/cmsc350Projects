 /**
 * BinaryTreeCatagorizerGUI.java - Main class a class where all user interface is implemented to enter value to 
 * be converted and checked for certain properties. 
 * 
 * @author  Girma, Senay
 * @version 1.0 10/24/2022
 * Project 3
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BinaryTreeCatagorizerGUI extends JFrame implements ActionListener {
	
	private JTextField inputBinaryTreeTextField, resultBinaryTreeTextField;
	private JLabel inputFieldLabel, resultFieldLabel;
	
	/**
	 * Constructor - The constructor defines the GUI window and all components that go on it.
	 * 
	 */
	public BinaryTreeCatagorizerGUI() {
		// create labels
		inputFieldLabel = new JLabel("Enter Tree: ");
		resultFieldLabel = new JLabel("Output: ");
		
		//input text fields
		inputBinaryTreeTextField = new JTextField(50);
		resultBinaryTreeTextField = new JTextField(50);
	
		//create buttons
		JButton makeTreeButton = new JButton("Make Tree");
		JButton isBalancedButton = new JButton("Is Balanced?");
		JButton isFullButton = new JButton("Is Full?");
		JButton isProperButton = new JButton("Is Proper?");
		JButton heightButton = new JButton("Height");
		JButton nodesButton = new JButton("Nodes");
		JButton inorderButton = new JButton("Inorder");

		setTitle("Binary Tree Catagorizer");
		setSize(650,250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3,1));
	
		JComponent[] userInputComponents = {inputFieldLabel, inputBinaryTreeTextField};
		addComponentsToPanel(userInputComponents);	
		
		JComponent[] controlComponents = {makeTreeButton, isBalancedButton, isFullButton, isProperButton, 
				heightButton, nodesButton, inorderButton};
		addComponentsToPanel(controlComponents);

		JComponent[] outputComponents = {resultFieldLabel, resultBinaryTreeTextField};
		addComponentsToPanel(outputComponents);
		
		makeTreeButton.addActionListener(this);
		isBalancedButton.addActionListener(this);
		isFullButton.addActionListener(this);
		isProperButton.addActionListener(this);
		heightButton.addActionListener(this);
		nodesButton.addActionListener(this);
		inorderButton.addActionListener(this);
		
	}
	
	/**
	 * addComponentsToPanel - Places the components defined in the constructor this method creates a panel and places
	 * each components on the respective panels
	 * 
	 * @param components: components defined in constructor.
	 */
	private void addComponentsToPanel(JComponent[] components) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		for(Component component: components)
			panel.add(component);
		add(panel);
	}

	/**
	 * actionPerformed - overridden actionPerformed method that comes with ActionListner. Listens to events from the 
	 * buttons and calls respective methods for each. User input in the textfield is also done here to ensure null or 
	 * empty space is not provided by user.
	 * 
	 * @param e: ActionEvent item
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//get user provided expression
		String expression = inputBinaryTreeTextField.getText();
		String noInputErrorMessage = "You have not provided a value to be converted. Please try again.";

		//check user provided a value
		if((expression==null) || expression.trim().isEmpty()) {
			JOptionPane.showMessageDialog(new JFrame(), noInputErrorMessage, "Dialog", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		
		//Define and Instantiate BinaryTree class with the user provided expression parameter for the constructor
		//call respective method in BinaryTree class. If exception is raised, show message using JOPtionpan window.
		try {
				switch(e.getActionCommand()) {
				case "Make Tree":{
					try {BinaryTree binaryTree = new BinaryTree(expression);
					
					if(binaryTree.isBuilt == true) 
					{resultBinaryTreeTextField.setText("Binary Tree for expression " + expression + " has been built");}
					else {resultBinaryTreeTextField.setText("Program was unable to build Binary Tree for expression " 
					+ expression );}
					}catch(InvalidTreeSyntax e1) {
						JOptionPane.showMessageDialog(new JFrame(), e1.toString(), "Dialog", JOptionPane.ERROR_MESSAGE);
						System.exit(1);
					}
				}
				break;
				
				case "Is Balanced?":{
					BinaryTree binaryTree = new BinaryTree(expression);
					resultBinaryTreeTextField.setText(binaryTree.isBalanced());
				}
				break;
				
				case "Is Full?":{
					BinaryTree binaryTree = new BinaryTree(expression);
					resultBinaryTreeTextField.setText(binaryTree.isFull());
				}
				break;
				
				case "Is Proper?":{
					BinaryTree binaryTree = new BinaryTree(expression);
					resultBinaryTreeTextField.setText(binaryTree.isProper());
				}
				break;
				
				case "Height":{
					BinaryTree binaryTree = new BinaryTree(expression);
					resultBinaryTreeTextField.setText(binaryTree.height());
				}
				break;
				
				case "Nodes":{
					BinaryTree binaryTree = new BinaryTree(expression);
					resultBinaryTreeTextField.setText(binaryTree.nodes());
				}
				break;
				
				case "Inorder":{
					BinaryTree binaryTree = new BinaryTree(expression);
					resultBinaryTreeTextField.setText(binaryTree.inorder());
				}
				break;
				
				}
			}catch(InvalidTreeSyntax exception){
				JOptionPane.showMessageDialog(new JFrame(), exception.toString(), "Dialog", JOptionPane.ERROR_MESSAGE);
	
			}
	}

	//main method to instantiate the main class and set dialog window visible.
	public static void main(String[] args) {
		BinaryTreeCatagorizerGUI binaryTreeGUI = new BinaryTreeCatagorizerGUI();
		binaryTreeGUI.setVisible(true);

	}

}
