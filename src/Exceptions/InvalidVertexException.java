package Exceptions;

@SuppressWarnings("serial")
public class InvalidVertexException extends RuntimeException{
	public InvalidVertexException(String s) {
		super(s);
	}
}
