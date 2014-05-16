package instructions;

public class InvalidParameterException extends Exception {
	private static final long serialVersionUID = -8824681393272529260L;

	public InvalidParameterException() {
		super();
	}

	public InvalidParameterException(String string) {
		super(string);
	}

}
