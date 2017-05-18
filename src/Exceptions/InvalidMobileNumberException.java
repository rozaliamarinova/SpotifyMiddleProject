package Exceptions;

public class InvalidMobileNumberException extends Exception{
	
	@Override
	public String getMessage() {
		return "Invalid mobile number!";
	}

}
