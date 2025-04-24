package Exceptions;

@SuppressWarnings("serial")
public class InvalidPositionException extends RuntimeException {
	public InvalidPositionException(String msg) {
		super(msg);
	}
}
