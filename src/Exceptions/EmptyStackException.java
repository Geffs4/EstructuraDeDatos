package Exceptions;

@SuppressWarnings("serial")
public class EmptyStackException extends RuntimeException {
	public EmptyStackException(String mensajeDeError) {
			super(mensajeDeError);
	}
}
