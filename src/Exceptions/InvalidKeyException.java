package Exceptions;

@SuppressWarnings("serial")
public class InvalidKeyException extends RuntimeException{
	public InvalidKeyException(String s) {
		super(s);
	}
}
