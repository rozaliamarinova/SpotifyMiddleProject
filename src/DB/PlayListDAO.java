package DB;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Model.Playlist;
import Model.Song;


public class PlayListDAO {
	
	Connection connection = DatabaseManager.getInstance().getConnection();
	private static PlayListDAO instance;
	
	private PlayListDAO(){
		
		
	}
	
	public static synchronized PlayListDAO getInstance(){
		if(instance == null){
			instance = new PlayListDAO();
		}
		return instance;
	}

	// add playList with title for user_id, return int za da zapishem id-to na pleilista?
	public int addPlayList(int user_id, String playListTitle) throws SQLException{
		String sqlQuery = "INSERT INTO spotify.playlists (userId, title) VALUES (?,?) ";
		int playListId = 0;
		PreparedStatement prepStatement = null;
		try{
			prepStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			prepStatement.setInt(1, user_id);
			prepStatement.setString(2, playListTitle);
			prepStatement.executeUpdate();
			ResultSet resultSet = prepStatement.getGeneratedKeys();
			if(resultSet.next()){
				playListId = resultSet.getInt(1);
			}
		}
		finally{
			prepStatement.close();
		}
		return playListId;
	}
	
	
	// add a song to playList
	public void addSongInPlayList(int playList_id, int song_id) throws SQLException{
		String sqlQuery = "INSERT INTO spotify.playlists_with_songs (playlistId, songId) VALUES (?,?) ";
		PreparedStatement prepStatement = null;
		try{
			prepStatement = connection.prepareStatement(sqlQuery);
			prepStatement.setInt(1, playList_id);
			prepStatement.setInt(2, song_id);
			prepStatement.executeUpdate();
		}
		finally{
			prepStatement.close();
		}
	}
	
	// samo da gi vidim 
	public List<Playlist> getAllPlayLists() throws SQLException{
		String sqlQuery = "SELECT playlistId, title, userId FROM spotify.playlists";
		List<Playlist> allPlayLists = new ArrayList<>();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			while(resultSet.next()){
				allPlayLists.add(new Playlist(resultSet.getInt("playlistId"), resultSet.getInt("userId"), resultSet.getString("title")));
			}
		}
		finally{
			statement.close();
		}
		return Collections.unmodifiableList(allPlayLists);
	}
	
	
	public List<Playlist> getUserPlaylists(int userId) throws SQLException{
		String sqlQuery = "SELECT p.playlistId, p.title, p.userId FROM spotify.playlists p "
				+ "join spotify.users u using(userId) where u.userId = ?;";
		
		List<Playlist> allPlayLists = new ArrayList<>();
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, userId);
			
			ResultSet resultSet = ps.executeQuery();
			
			while(resultSet.next()){
				allPlayLists.add(new Playlist(resultSet.getInt("p.playlistId"),
						resultSet.getInt("p.userId"), 
						resultSet.getString("p.title")));
			}
			System.out.println(allPlayLists);
		}
		
		finally{
			ps.close();
		}
		return Collections.unmodifiableList(allPlayLists);
	}
	

	
	public int getPlaylistId(String name){
		String sql = "select playlistId from spotify.playlists where title = ?;";
		PreparedStatement ps = null;
		int id = 0;
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				id = rs.getInt("playlistId");
			}
		}
		catch(SQLException e)
		{
			System.out.println("DB problem selecting the playlist.");
		}
		finally{
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println("ops");
			}
		}
		return id;
	}
	
	
	public boolean existsInPlaylist(int song_id,int playlist_id){
		String sql = "SELECT playlistId,songId FROM spotify.playlists_with_songs WHERE playlistId = ? AND songId = ?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, playlist_id);
			ps.setInt(2, song_id);
			ResultSet rs = ps.executeQuery();
		
			if(rs.next()){
				return false;
			}
			
			} catch (SQLException e) {
				System.out.println("ops");
			}
			finally{
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("ops");
				}
			}
			return true;
			
		}
	
	
	public List<Song> getAllSongsFromPlaylist(int playlistId,int userId) {
		
		String sql = "select ss.songId,ss.albumId,ss.title,ss.artist"
				+ " from spotify.playlists_with_songs s join spotify.playlists"
				+ " p on p.playlistId = s.playlistId join spotify.songs ss on ss.songId = s.songId where p.playlistId = ? and p.userId =?;"; 


		List<Song> songsInPlaylist = new ArrayList<Song>();
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, playlistId);
			ps.setInt(2, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Song song = new Song(rs.getInt("ss.songId"), 
										rs.getInt("ss.albumId"), 
										rs.getString("ss.title"),
										rs.getString("ss.artist"));
				
				songsInPlaylist.add(song);
		
			}
			System.out.println("OK " + songsInPlaylist);
		} catch (SQLException e) {
			System.out.println("DB problem with selcting all songs." + e.getMessage());
		}
		return Collections.unmodifiableList(songsInPlaylist);
	}
		

		
}

