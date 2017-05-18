package Model;
import java.time.LocalDate;

public class Comment { //comment may has likes
	
	private int commentId;
	private int postId;
	private int userId;
	private String username;
	private LocalDate postedDate;
	private String content;
	
	
	public Comment(int commentId,int postId,int userId,User user, LocalDate postedDate, String content) {
		this.commentId = commentId;
		this.postId = postId;
		this.userId = userId;
		this.username = user.getUsername();
		this.postedDate = postedDate;
		this.content = content;
	}
	

}
