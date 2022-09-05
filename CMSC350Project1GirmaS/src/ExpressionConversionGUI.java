
/**
 * CMSC350Project1GirmaS.java - a class where all user interface is implemented to enter value to be converted to Postfix
 * or Prefix, select the type of conversion, and a display the converted value. This class instantiates and implements the 
 * Converter and ExceptionHandler classes to achieve proper conversion and error handling. 
 * 
 * @author  Girma, Senay
 * @version 1.0 08/30/2022
 * Project 1
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ExpressionConversionGUI extends JFrame implements ActionListener {
	
	private JTextField inputExpressionTextField, resultExpressionTextField;
	private JLabel inputFieldLabel, resultFieldLabel;
	
	/**
	 * Constructor - The constructor defines the GUI window and all components that go on it.
	 * 
	 */
	public ExpressionConversionGUI() {
		// create labels
		inputFieldLabel = new JLabel("Enter Expression ");
		resultFieldLabel = new JLabel("Result ");
		
		//input text fields
		inputExpressionTextField = new JTextField(20);
		resultExpressionTextField = new JTextField(20);
	
		//create buttons
		JButton prefixToPostfixButton = new JButton("Prefix To Postfix");
		JButton postfixToPrefixButton = new JButton("Postfix To Prefix");
		
		setTitle("Expression Converter");
		setSize(400,150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3,1));
	
		JComponent[] userInputComponents = {inputFieldLabel, inputExpressionTextField};
		addComponentsToPanel(userInputComponents);	
		
		JComponent[] controlComponents = {prefixToPostfixButton, postfixToPrefixButton};
		addComponentsToPanel(controlComponents);

		JComponent[] outputComponents = {resultFieldLabel, resultExpressionTextField};
		addComponentsToPanel(outputComponents);
		
		prefixToPostfixButton.addActionListener(this);
		postfixToPrefixButton.addActionListener(this);
		
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
	 * actionPerformed - overridden actionPerformed method that comes with ActionListner. Listens to events from the two
	 * buttons and calls respective methods for each. User input in the textfield is also done here to ensure null or 
	 * empty space is not provided by user.
	 * 
	 * @param e: ActionEvent item
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//get user provided expression
		String expression = inputExpressionTextField.getText();
		String noInputErrorMessage = "You have not provided a value to be converted. Please try again.";

		//check user provided a value
		if((expression==null) || expression.trim().isEmpty()) {
			JOptionPane.showMessageDialog(new JFrame(), noInputErrorMessage, "Dialog", JOptionPane.ERROR_MESSAGE);
		}
		
		//Define and Instantiate ExressionConverter class with the user provided expression parameter for the constructor
		//call respective method in ExpressionConver class for each class. If exception is raised, show message using 
		//JOPtionpan window.
		try {
				switch(e.getActionCommand()) {
				case "Prefix To Postfix":{
					ExpressionConverter converter = new ExpressionConverter(expression);
					resultExpressionTextField.setText(converter.convertPrefixToPostfix());
				}
				break;
				
				case "Postfix To Prefix":{
					ExpressionConverter converter = new ExpressionConverter(expression);
					resultExpressionTextField.setText(converter.convertPostfixToPrefix());
					System.out.println("Postfix to prefix1 selected for label " + resultExpressionTextField.getText());
				}
				break;
				}
			}catch(StackSizeException exception){
				JOptionPane.showMessageDialog(new JFrame(), exception.toString(), "Dialog", JOptionPane.ERROR_MESSAGE);
	
			}
	}

	//main method to instantiate the main class and set dialog window visible.
	public static void main(String[] args)
	{
		ExpressionConversionGUI converterGUI = new ExpressionConversionGUI();
		converterGUI.setVisible(true);
	}
}

