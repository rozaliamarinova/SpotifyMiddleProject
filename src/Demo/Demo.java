package Demo;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import DB.AlbumDAO;
import DB.DatabaseManager;
import DB.SongDAO;

public class Demo {

	public static void main(String[] args) {
		
		
//		Connection connection = null;
//		try {
//			connection = DatabaseManager.getInstance().getConnection();
//			
//			System.out.println("Working");
//		}
//		finally {
//				try {
//					connection.close();
//				} catch (SQLException e) {
//					System.out.println(e.getClass().getName());
//				}
//		}
		
//		AlbumDAO.getInstance().addAlbum("Starboy", "The Weeknd", "Starboy is the third studio album by Canadian singer and songwriter The Weeknd.", "Pop", 4);
//		AlbumDAO.getInstance().addAlbum("Anti", "Rihanna", " 'ANTI' is the long-awaited eighth album from global superstar and icon Rihanna.", "RnB", 6);
//		AlbumDAO.getInstance().addAlbum("AM", "Arctic Monkeys", "AM has been recognised as one of the bestselling vinyl albums of the decade, selling 27,000 units.","Indie",1);
//		AlbumDAO.getInstance().addAlbum("Ultraviolence", "Lana Del Rey", "Ultraviolence is the third studio album and second major-label record by Lana Del Rey","Indie",1);
//		insert into playlists (playlistId,userId,title) values (4,10,'Playlist1');

//		SongDAO.getInstance().addSong("Arabella", "Arctic Monkeys", "Indie", 9);
//		SongDAO.getInstance().addSong("R U Mine?", "Arctic Monkeys", "Indie", 9);
//		SongDAO.getInstance().addSong("One for the Road", "Arctic Monkeys", "Indie", 9);
//		SongDAO.getInstance().addSong("Starboy", "The Weeknd", "Pop", 7);
//		SongDAO.getInstance().addSong("Party Monster", "The Weeknd", "Pop", 7);
		
//		System.out.println(AlbumDAO.getInstance().getGenreAlbums(1));

	}

}
