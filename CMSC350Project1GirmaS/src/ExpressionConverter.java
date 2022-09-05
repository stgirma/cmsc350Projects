
/**
 * ExpressionConverter.java - provides two public methods prefixToPostfix and postfixToPrefix. Each method accepts 
 * an expression and convert to the respective form and return the converted expression. 
 * 
 * @author  Girma, Senay
 * @version 1.0 09/02/2022
 * 
 * Project 1
 */


import java.util.Stack;

public class ExpressionConverter {

	// declare all common strings and two stacks used through out this class
	private String expression, token, convertedExpression, firstOperand, secondOperand;
	private Stack<String> reversalStack, operandStack;

	// any token that matches any of the characters in operatorList is considered as
	// an operator. For the current project
	// only 4 operators are used. Additional operator character such as '^', '!', or
	// '%' can be added in the future.
	// TODO: Provide easier way to modify and congfigure operatorList.
	private final String operatorList = "+-/*";
	private final String regExPattern = "((?<=\\+)|(?=\\+)|(?<=\\*)|(?=\\*)|(?<=\\-)|(?=\\-)|(?<=/)|(?=/))|(?<=\\s)|(?=\\s)";

	/**
	 * Constructor - Initializes the two stacks and assigns the expression string to
	 * string declared above.
	 * 
	 * @param expression the expression string that will be converted and returned.
	 */
	public ExpressionConverter(String expression) {
		this.expression = expression;
		reversalStack = new Stack<String>();
		operandStack = new Stack<String>();
	}

	/**
	 * convertPrefixToPostfix - Converts the expression expression provided in the
	 * constructor to Postfix form and returns converted expression.
	 * 
	 * @return convertedExpression: result string converted to Postfix expression.
	 */
	public String convertPrefixToPostfix() {
		// reverse the expression
		reverseExpression(this.expression);

		// pop one token at a time, run it through pushToOperandStack method until there
		// are no more tokens left in reverseStack
		while (!this.reversalStack.isEmpty()) {
			token = this.reversalStack.pop();

			pushToOperandStack(new String[] { token, "prefixToPostfix" });
		}

		// pop the converted expression from the Operand stack
		convertedExpression = this.operandStack.pop();

		// Once the converted expression is popped from operandStack, there shouldn't be
		// anymore elements left. If so, throw exception
		if (this.operandStack.size() > 0) {
			
			throw new StackSizeException(1);
		}
		
		return convertedExpression;
	}

	/**
	 * convertPostfixToPrefix - Converts the expression expression provided in the
	 * constructor to prefix form and returns converted expression.
	 * 
	 * @return convertedExpression: result string converted to prefix expression.
	 */
	public String convertPostfixToPrefix() {
		//tokenize expression
		String[] tokenizedExression = expressionTokenizer(expression);
		
		//loop through the tokenizedExpressn list and push to stack
		for(String token: tokenizedExression) {
			if(!token.trim().isEmpty()) {
				pushToOperandStack(new String[] { token, "postfixToPrefix" });
			}
		}
		
		// pop the converted expression from the Operand stack
		convertedExpression = this.operandStack.pop();
		
		// Once the converted expression is popped from operandStack, there shouldn't be
		// anymore elements left. If so, throw exception
		if (this.operandStack.size() > 0) {
			throw new StackSizeException(1);
		}

		return convertedExpression;
	}

	/**
	 * pushToOperandStack - Evaluates if the given token is an operator or operand. if operand, it's pushed to the stack
	 * if the token is an operator, two previous tokens are popped and concatenated with the operator and pushed back based
	 * on the calling method.
	 * 
	 * @param args: Two value String array, the first containing the token and the second the calling method name.
	 */
	private void pushToOperandStack(String[] args) {
		String token = args[0];
		String conversion = args[1];//prefixToPostfix or postfixToPrefix
		
		//if token is an int it's pushed to the stack
		try {
			Integer.parseInt(token);
			this.operandStack.push(token + " ");
			
		} catch (NumberFormatException ex) {
		//if token is not an int, it's an operator and require popping to values from stack, so ensure stack has two values
			if (this.operandStack.size() < 2) {
				throw new StackSizeException(-1);
			} else {
				
				firstOperand = this.operandStack.pop();
				secondOperand = this.operandStack.pop();
				switch (conversion) {
				case "prefixToPostfix": {
					this.operandStack.push(firstOperand + secondOperand + token + " ");
				}
					break;
				case "postfixToPrefix": {
					this.operandStack.push(token +" " + secondOperand + firstOperand);
				}
					break;
				}
			}
		}

	}

	/**
	 * reverseExpression - calls expressionTokenizer to tokenize expression and pushes each non-space token into 
	 *reversalStack which essentially reverses the tokens.
	 * 
	 * @param expression: String expression provided by the constructor.
	 */
	private void reverseExpression(String expression) {

		for (String token : expressionTokenizer(expression)) {
			//ensure token is not a space
			if (!token.trim().isEmpty()) {
				this.reversalStack.push(token);
			} else {
				// it's a space so do nothing
			}
		}
	}

	/**
	 * expressionTokenizer - tokenizes the provided expression using space and the four operators as regex pattern. Any additional
	 * operator that needs to be added can be added in the regExPattern variable. 
	 * 
	 * @param expression: String expression provided by the constructor.
	 * @return tokenizedExpression: String[] list of tokens including spaces.
	 */
	private String[] expressionTokenizer(String exression) {
		String[] tokenizedExpression = expression.split(regExPattern);
		return tokenizedExpression;
	}

}
