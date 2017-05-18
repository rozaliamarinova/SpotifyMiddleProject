package Exceptions;

public class PlayListWithSameNameException extends Exception{
	
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "A playlist with the same name already exists";
	}

}
