package Exceptions;

public class InvalidPlaylistException extends Exception {
	
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "No such playlist";
	}

}
