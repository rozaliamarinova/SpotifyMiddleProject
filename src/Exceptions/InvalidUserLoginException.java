package Exceptions;

public class InvalidUserLoginException extends Exception {
	
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Unsuccessful login.";
	}

}
