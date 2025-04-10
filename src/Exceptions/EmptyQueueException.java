package Exceptions;

@SuppressWarnings("serial")
public class EmptyQueueException extends RuntimeException {
	public EmptyQueueException(String msg) {
		super(msg);
	}
}
