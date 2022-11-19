package blog.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import blog.model.LikeComments;

public class LikeCommentsDao {
	
	protected ConnectionManager connectionManager;
	private static LikeCommentsDao instance = null;
	
	protected LikeCommentsDao( ) {
		connectionManager = new ConnectionManager();
	}
	
	public static LikeCommentsDao getInstance() {
		if(instance == null) {
			instance = new LikeCommentsDao();
		}
		return instance;
	}
	
	public LikeComments create(LikeComments likeComment) throws SQLException {
		String insertLikeComment = 
				"INSERT INTO LikeComments(userId,commentId) " 
				+ "VALUES(?,?)";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertLikeComment, 
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, likeComment.getUser().getUserId());
			insertStmt.setInt(2, likeComment.getComment().getCommentId());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int likeCommentId = -1;
			
			if (resultKey.next()) {
				likeCommentId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			
			likeComment.setLikeCommentId(likeCommentId);
			return likeComment;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
		
	}
	
	public LikeComments getLikeCommentById(int likeCommentId) throws SQLException {
		
		String selectLikeComment = 
				"SELECT likeCommentId,userId,commentId "
				+ "FROM LikeComments "
				+ "WHERE likeCommentId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLikeComment);
			selectStmt.setInt(1, likeCommentId);
			results = selectStmt.executeQuery();
			UsersDao userDao = UsersDao.getInstance();
			CommentsDao commentDao = CommentsDao.getInstance();
			if(results.next()) {
				Users user = userDao.getUserByUserId("userId");
				Comments comment = commentDao.getCommentByReviewId("commentId");
				LikeComments likeComment = new LikeComments(likeCommentId, user, comment);
				return likeComment;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		
		return null;
		
	}
	
	public LikeComments delete(LikeComments likeComment) throws SQLException {
		
		String deleteLikeComment = 
				"DELETE "
				+ "FROM LikeComments "
				+ "WHERE likeCommentId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLikeComment);
			deleteStmt.setInt(1, likeComment.getLikeCommentId());
			deleteStmt.executeUpdate();
			
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
		
	}
}
