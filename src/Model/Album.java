package Model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

public class Album {
	
	private int albumId;
	private String title;
	private String genre;
	private String photo;
	private String artist;
	private String description;
	private HashSet<Song> songs; 
    private ArrayList<Comment> comments;
    
    
	public Album(int albumId,String title, String artist, String genre) {
		this.albumId = albumId;
		this.title = title;
		this.genre = genre;
		this.artist = artist;
		this.songs = new HashSet();
		this.comments = new ArrayList();
	}


	public HashSet<Song> getSongs() {
		return songs;
	}


	public String getGenre() {
		// TODO Auto-generated method stub
		return this.genre;
	}


	public String getArtist() {
		// TODO Auto-generated method stub
		return this.artist;
	}
	
	
	
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Album other = (Album) obj;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (genre != other.genre)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}



	public String getTitle() {
		return this.title;
	}


	public void addComment(Comment c) {
		this.comments.add(c);
	}


	public List<Comment> getComments() {
		return Collections.unmodifiableList(comments);
	}


	public int getId() {
		// TODO Auto-generated method stub
		return this.albumId;
	}
	
	


}
