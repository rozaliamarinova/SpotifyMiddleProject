package Model;
import java.util.ArrayList;

public class Playlist {
	
	private int playlistId;
	private int userId;
	private String title;
	private ArrayList<Song> songs = new ArrayList<Song>();
	
	
	public Playlist( int playlistId, int userId, String title) {
		this.playlistId = playlistId;
		this.userId = userId;
		this.title = title;
	}
	
	
	public void addSong(Song s){
		this.songs.add(s);
	}
	
	
	public void setPlaylistId(int id){
		this.playlistId = id;
	}


	public ArrayList<Song> getSongs() {
		return songs;
	}


	public String getTitle() {
		// TODO Auto-generated method stub
		return this.title;
	}

	public int getPlaylistId(){
		return this.playlistId;
	}
	

}
