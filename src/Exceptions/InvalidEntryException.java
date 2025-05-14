package Exceptions;

@SuppressWarnings("serial")
public class InvalidEntryException extends RuntimeException {
	public InvalidEntryException (String s) {
		super(s);
	}
}
