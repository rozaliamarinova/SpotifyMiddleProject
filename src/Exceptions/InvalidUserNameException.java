package Exceptions;

public class InvalidUserNameException extends Exception{

	@Override
	public String getMessage() {
		return "Invalid username!";
	}
	
}
