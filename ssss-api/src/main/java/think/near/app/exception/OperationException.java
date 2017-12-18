package think.near.app.exception;


public class OperationException extends Exception {

	private static final long serialVersionUID = 1L;

	public OperationException() {
		super("Error while executing requested operation");
	}
 
	public OperationException(String string) {
		super(string);
	}
}