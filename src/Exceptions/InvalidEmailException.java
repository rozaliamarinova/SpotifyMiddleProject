package Exceptions;

public class InvalidEmailException extends Exception{
	
	@Override
	public String getMessage() {
		return "Invalid email!";
	}

}
