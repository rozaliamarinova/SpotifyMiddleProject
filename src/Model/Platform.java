package Model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Exceptions.InvalidUserLoginException;
import Exceptions.InvalidUserRegisterException;


public class Platform {
	
	
	private static final String PLATFORM_NAME = "Spotify";
	private String name;
	private HashMap<String,ArrayList<Album>> albums; //for searching album by genre and name
	private HashSet<Song> allSongs;  //for searching a song
	private HashSet<User> users;
	private static Platform getInstance;
	private static boolean exists;
	private static final String PASS_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    private static final String EMAIL_PATTERN =
    		"[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
    private static final String USERNAME_PATTERN = 
    		"^[a-z0-9_-]{3,15}$";
    private static final String MOBILEPHONE_PATTERN =
    		"^\\+[0-9]{2,3}+-[0-9]{10}$";
        	
	
	
	private Platform() {
		
		this.name = PLATFORM_NAME;
		this.albums = new HashMap<>();
		this.users = new HashSet<>();
		this.allSongs = new HashSet<>();
		
	}
	
	public static Platform getPlatformInstance(){
		if(exists){
			return getInstance;
		}
		else{
			getInstance = new Platform();
			exists = true;
			return getInstance;
		}
	}
	
	
	public void createAlbum(Album album){
		if(!this.albums.containsKey(album.getGenre())){
			this.albums.put(album.getGenre(), new ArrayList<>());
		}
		this.albums.get(album.getGenre()).add(album); //add by genre
	}
	
	
	public void addSongPlatofrm(Song song,Album album){ //add to album and to 
		
		for(Map.Entry<String,ArrayList<Album>> entry : this.albums.entrySet()){
			if(album.equals(album)){
				album.getSongs().add(song);
			}
		}
		this.allSongs.add(song);
		
	}
	
	
	
	public class SearchEngine {


		public ArrayList<Song> searchBySongName(String text) { //returns an arrayList of matching songs
			ArrayList<Song> songsSearched = null;
			for(Song s : allSongs){
				String text1 = text.toLowerCase();
				if(s.getTitle().contains(text1)){
					songsSearched.add(s);
				}
			}
			return songsSearched;
		}
		
		
		public ArrayList<Album> searchByAlbum(String text) { //returns an arrayList of matching albums
			ArrayList<Album> albumsSearched = null;
			for(Map.Entry<String,ArrayList<Album>> entry : albums.entrySet()){
				for(Album a : entry.getValue()){
					if(a.getTitle().toLowerCase().contains(text)){
						albumsSearched.add(a);
					}
				}
			}
			return albumsSearched;
		}
		
			
		public ArrayList<Album> searchGenre(Genre genre){
			ArrayList<Album> albumsSearched = null;
			if(!albums.containsKey(genre)){
				for(Map.Entry<String,ArrayList<Album>> entry : albums.entrySet()){
					albumsSearched = entry.getValue();
				}
			}
			return albumsSearched;
		}
		
		
		public void searchUser(){
			//TODO
		}

	}
	
	public void printTop10(){
		TreeSet<Song> mostPlayed = new TreeSet(new Comparator<Song>(){

			@Override
			public int compare(Song arg0, Song arg1) {
				return arg0.getTimesPlayed() - arg1.getTimesPlayed();
			}
		});
		
		System.out.println("Top 10 songs : ");
		for(Song s : mostPlayed){
			System.out.println(s);
		}
	}
	
	
//	public static User registerUser(String name,String username, String password, String email,String city, String gender,Date birthday, String mobileNumber) throws InvalidUserRegisterException{
//		boolean valid = true;
//		if (isValidEmail(email) && isValidUsername(username) && isValidPassword(password)
//				&& isValidMobileNumber(mobileNumber)) {
//			for (User u : users) {
//				if (u.getUsername().equals(username) || u.getEmail().equals(email)) {
//					valid = false;
//					throw new InvalidUserRegisterException();
//				}
//			}
//			if (valid) {
//				users.add(new User(name,username, password, email, city,gender,birthday, mobileNumber));
//			}
//		}
//	}
	
	
	public void logIn(String username, String password) throws InvalidUserLoginException {
		boolean valid = false;
		for (User u : this.users) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				System.out.println("Login successful");
				valid = true;
				break;
			}

		}
		if (!valid) {
			throw new InvalidUserLoginException();
		}
	}


	public Map<String, ArrayList<Album>> getAlbums() {
		return Collections.unmodifiableMap(albums);
	}

	public Set<Song> getAllSongs() {
		return Collections.unmodifiableSet(allSongs);
	}

	
	
	public static boolean isValidPassword(String password) {

		Pattern pattern = Pattern.compile(PASS_PATTERN);
		Matcher matcher = pattern.matcher(password);
		if (matcher.matches() && !password.isEmpty() && password!=null) {
			return true;
		} else {
			System.out.println("The entered password is not valid.");
			return false;
		}
	}
	
	
	public static boolean isValidEmail(String email) {

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches() && !email.isEmpty() && email!=null) {
			return true;
		} else {
			System.out.println("The entered email is not valid.");
			return false;
		}
	}
	
	
	public static boolean isValidUsername(String username) {

		Pattern pattern = Pattern.compile(USERNAME_PATTERN);
		Matcher matcher = pattern.matcher(username);
		if (matcher.matches() && !username.isEmpty() && username!=null) {
			return true;
		} else {
			System.out.println("The entered username is not valid.");
			return false;
		}
	}
	
	
	public static boolean isValidMobileNumber(String mobileNumber) {
	    Pattern pattern = Pattern.compile(MOBILEPHONE_PATTERN);
	    Matcher matcher = pattern.matcher(mobileNumber);
	    if(matcher.matches() && !mobileNumber.isEmpty() && mobileNumber!=null){
	        return true;
	    } else {
	    	System.out.println("The entered mobile number is not valid.");
	    	return false;
	    }

	}
	
	
	
}
