 /**
 * InvalidTreeSyntax.java - custom exceptoin handler to ensure user provided valid input. 
 * 
 * @author  Girma, Senay
 * @version 1.0 10/24/2022
 * Project 3
 */
public class InvalidTreeSyntax extends Exception {
	private String message;

	public InvalidTreeSyntax (String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " : " + message;
	}

}
