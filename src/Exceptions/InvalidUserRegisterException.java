package Exceptions;

public class InvalidUserRegisterException extends Exception {
	
	@Override
	public String getMessage() {
		
		return "Unsuccessful register";
	}

}
