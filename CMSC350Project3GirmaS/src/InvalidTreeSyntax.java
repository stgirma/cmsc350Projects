
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
