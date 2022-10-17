/**
 *InvalidPolynomialSyntax.java - unChecked exception class that is thrown if the supplied string contains coefficients or exponents 
 *of an improper type or if the exponents fail to be listed in strictly descending order.
 * 
 * @author  Girma, Senay
 * @version 1.0 10/10/2022
 * 
 * Project 2
 */
public class InvalidPolynomialSyntax extends RuntimeException{
	
	private String message;

	public InvalidPolynomialSyntax(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " : " + message;
	}
}

