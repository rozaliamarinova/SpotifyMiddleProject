package Model;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.MBeanOperationInfo;

import Exceptions.InvalidEmailException;
import Exceptions.InvalidMobileNumberException;
import Exceptions.InvalidNameException;
import Exceptions.InvalidPasswordException;
import Exceptions.InvalidPlaylistException;
import Exceptions.InvalidUserLoginException;
import Exceptions.InvalidUserNameException;
import Exceptions.PlayListWithSameNameException;

public class User {
	
	private static final String NAME_PATTERN = "^[A-Za-z]+$";
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9]+.[a-z.]+$";
    private static final String MOBILEPHONE_PATTERN = "([08]{2}+[0-9]{8})";
    private static final String USERNAME_PATTERN = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
	
	private int userId;
	private String name;
	private String username;
	private String password;
	private String city;
	private String email;
	private String profilePhoto;
	private Date birthday;
	private String gender;
	private String mobileNumber;
	private ArrayList<Playlist> playLists;
	private HashSet<Song> likedSongs;
	private Pattern pattern = null;
	private Matcher matcher = null;
	
	
	public User(String name,String username, String password, String email, String city, Date birthday, String gender, String mobileNumber) throws InvalidUserLoginException, InvalidEmailException, InvalidPasswordException, InvalidMobileNumberException, InvalidNameException, InvalidUserNameException {
		if(this.patternFinder(NAME_PATTERN, name)){
			this.name = name;
			System.out.println("SUCCESS");
		}
		else{
			throw new InvalidNameException();
		}
		if(this.patternFinder(EMAIL_PATTERN, email)){
			this.email = email;
			System.out.println("SUCCESS");
		}
		else{
			throw new InvalidEmailException();
		}
		if(this.patternFinder(USERNAME_PATTERN, password)){
			this.password = password;
			System.out.println("SUCCESS");
		}
		else{
			throw new InvalidPasswordException();
		}
		if(this.patternFinder(USERNAME_PATTERN, username)){
			this.username = username;
			System.out.println("SUCCESS" + username);
		}
		else{
			throw new InvalidUserNameException();
		}
		if(this.patternFinder(MOBILEPHONE_PATTERN, mobileNumber)){
			this.mobileNumber = mobileNumber;
			System.out.println("SUCCESS " + mobileNumber);
		}
		else{
			throw new InvalidMobileNumberException();
		}
		this.gender = gender;
		this.likedSongs = new HashSet<>();
		this.playLists = new ArrayList<>();
		this.birthday = birthday;
		this.city = city;
	}
	
	
	
	public boolean patternFinder(String regex, String field) {
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(field);
		if (matcher.matches() && !field.isEmpty() && field != null) {
			return true;
		} else {
			return false;
		}
	}
	
	

	public void createPlayList(int id,String title,Song song) throws PlayListWithSameNameException{
		for(Playlist p : this.playLists){
			if(p.getTitle().equals(title)){
				throw new PlayListWithSameNameException();
			}
		}
		this.playLists.add(new Playlist(id,userId, title));
	}
	

	public void addToPlayList(String playList,Song song) throws InvalidPlaylistException {
		for(Playlist p : this.playLists){
			if(p.getTitle().equals(playList)){
				p.addSong(song);
			}
		}
		throw new InvalidPlaylistException();
	}
	
	
	public void setName(String name) throws InvalidNameException{
		if(!name.equals(null) && !name.isEmpty() && name.matches(NAME_PATTERN)){
			this.name = name;
		}
		else{
			throw new InvalidNameException();
		}
	}
	
	public void setUsername(String username) throws InvalidUserNameException {
		if(!username.equals(null) && !username.isEmpty() && username.matches(USERNAME_PATTERN)){
			this.username = username;
		}
		else{
			throw new InvalidUserNameException();
		}
	}
	
	public void setPassword(String password) throws InvalidPasswordException {
		if(!password.equals(null) && !password.isEmpty() && password.matches(USERNAME_PATTERN)){
			this.password = password;
		}
		else{
			throw new InvalidPasswordException();
		}
	}
	
	public void setEmail(String email) throws InvalidEmailException{
		if(!email.equals(null) && !email.isEmpty() && email.matches(EMAIL_PATTERN)){
			this.email = email;
		}
		else{
			throw new InvalidEmailException();
		}
	}
	
	public void setMobileNumber(String mobileNumber) throws InvalidMobileNumberException{
		if(!mobileNumber.equals(null) && !mobileNumber.isEmpty() && mobileNumber.matches(MOBILEPHONE_PATTERN)){
			this.mobileNumber = mobileNumber;
		}
		else{
			throw new InvalidMobileNumberException();
		}
	}

	public void setGender(String gender) throws InvalidUserLoginException {
		if(gender.equals("Male") || gender.equals("Female")){
			this.gender = gender;
		}
		else{
			throw new InvalidUserLoginException();
		}
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setPhoto(String photo) {
        this.profilePhoto = photo;
    }



	public void updateProfile(){
		//TODO
	}
	
	
	public void playASong(Song song){
		song.setTimesPlayed();
	}
	
	
	//TODO not unmodifiable?
	public void postAComment(Album album,Comment c){
		album.addComment(c);
	}
	
	
	public void likeSong(Song song){
		this.likedSongs.add(song);
		song.addLikes(this);
	}
	
	
	public void removeLikedSong(Song song){
		this.likedSongs.remove(song);
	}
	
	
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password; //would fix this, public for the moment because of UserDAO
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	public String getGender() {
		// TODO Auto-generated method stub
		return this.gender;
	}


	public String getCity() {
		// TODO Auto-generated method stub
		return this.city;
	}


	public String getMobileNumber() {
		// TODO Auto-generated method stub
		return this.mobileNumber;
	}


	public Date getBirthday() {
		// TODO Auto-generated method stub
		return this.birthday;
	}

	public int getId(){
		return this.userId;
	}

	public void setId(int id) {
		// TODO Auto-generated method stub
		this.userId = id;
	}


}
