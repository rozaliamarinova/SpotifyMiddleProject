package Exceptions;

public class InvalidPasswordException extends Exception{
	
	@Override
	public String getMessage() {
		return "Invalid password! Password must be between 6 and 20 characters long!";
	}

}
