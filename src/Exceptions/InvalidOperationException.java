package Exceptions;

@SuppressWarnings("serial")
public class InvalidOperationException extends RuntimeException{
	public InvalidOperationException(String msg) {
		super(msg);
	}
}

