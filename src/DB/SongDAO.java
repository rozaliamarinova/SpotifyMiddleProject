package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Model.Album;
import Model.Genre;
import Model.Song;

public class SongDAO {
	
	Connection connection = DatabaseManager.getInstance().getConnection();
	private static SongDAO instance;
	private static final String SElECT_ALL_SONGS = "SELECT s.songId,s.title,s.artist,s.albumId "
			+ "from spotify.songs s join spotify.albums a USING(albumId) where a.albumId = ?;";

			
	private SongDAO(){
		
	}
	
	public static synchronized SongDAO getInstance(){
		if(instance == null){
			instance = new SongDAO();
		}
		return instance;
	}
	
	// add song in DataBase -> by us
	public int addSong(String title, String artist, String genre, int album_id){
		String sqlQuery = "INSERT INTO spotify.songs (title, artist, genre, albumId) VALUES (?, ?, ?, ?)";
		PreparedStatement prepStatement = null;
		int songId = 0;
		try {
			prepStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, title);
			prepStatement.setString(2, artist);
			prepStatement.setString(3, genre);
			prepStatement.setInt(4, album_id);
			songId = prepStatement.executeUpdate();
			ResultSet resultSet = prepStatement.getGeneratedKeys();
			while(resultSet.next()){
				songId = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Something went wrong in addSong in SongDAO - " + e.getMessage());
		}
		finally{
			try {
				prepStatement.close();
			} catch (SQLException e) {
				System.out.println("Something went wrong when closing statement! - " + e.getMessage());
			}
		}
		return songId;
	}
	
	// search for a song by title or artist
	public List<Song> searchForSong(String name){
		String sqlQuery = "SELECT songId, albumId, title, artist FROM spotify.songs WHERE title LIKE ? OR artist LIKE ?";
		String search = "%" + name + "%";
		ArrayList<Song> songsMatching = new ArrayList<>();
		PreparedStatement prepStatement = null;
		try {
			prepStatement = connection.prepareStatement(sqlQuery);
			prepStatement.setString(1, search);
			prepStatement.setString(2, search);
			ResultSet result = prepStatement.executeQuery();
			
			while (result.next()) {
				Song song = new Song(
						result.getInt("songId"),
						result.getInt("albumId"),
						result.getString("title"),
						result.getString("artist")
						);
				songsMatching.add(song);
			}
		} catch (SQLException e) {
			System.out.println("Problem with DataBase in searchForSong! - " + e.getMessage());
		}
		if(songsMatching.size() == 0){
			System.out.println("There is no such artist or song! ");
			return null;
		}
		return Collections.unmodifiableList(songsMatching);
	}
	

	//for visualisation
	public List<Song> getAllSongsFromAlbum(int albumId) {
		List<Song> songsInAlbum = new ArrayList<Song>();
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(SElECT_ALL_SONGS);
			ps.setInt(1, albumId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Song song = new Song(rs.getInt("songId"), 
										rs.getInt("albumId"), 
										rs.getString("title"),
										rs.getString("artist"));
				
				songsInAlbum.add(song);
				System.out.println(albumId);
				System.out.println("Songs " + songsInAlbum);
			}
		} catch (SQLException e) {
			System.out.println("DB problem with selcting all songs." + e.getMessage());
		}
		return Collections.unmodifiableList(songsInAlbum);
	}
	
	
	public void increaseTimesPlayed(int song_id){
		String sql = "update spotify.songs set times_played=times_played+1 where songId = ?";	
		PreparedStatement ps=null;
			try {
				ps = connection.prepareStatement(sql);
				ps.setInt(1, song_id);
				int rowsAff = ps.executeUpdate();
				if(rowsAff>0){
					System.out.println("success");
				}
			} catch (SQLException e) {
				System.out.println("DB problem");
			}
		
	}

}
