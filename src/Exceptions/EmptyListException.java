package Exceptions;

@SuppressWarnings("serial")
public class EmptyListException extends RuntimeException{
	public EmptyListException(String msg) {
		super(msg);
	}
}
