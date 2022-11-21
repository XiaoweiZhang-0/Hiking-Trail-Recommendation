package com.hiking.dal;
/**
 * author Lilan Wu
 */

import com.hiking.dal.ConnectionManager;
import com.hiking.dal.ReviewsDao;
import com.hiking.dal.UsersDao;
import com.hiking.model.Comments;
import com.hiking.model.Reviews;
import com.hiking.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CommentsDao {
	protected ConnectionManager connectionManager;

	private static CommentsDao instance = null;
	protected CommentsDao() {
		connectionManager = new ConnectionManager();
	}
	public static CommentsDao getInstance() {
		if(instance == null) {
			instance = new CommentsDao();
		}
		return instance;
	}

	public Comments create(Comments comment) throws SQLException {
		String insertComment =
			"INSERT INTO Comments(userId,reviewId) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertComment,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, comment.getUser().getUserId());
			insertStmt.setInt(2, comment.getHikingTrail().getReviewId());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int commentId = -1;
			if(resultKey.next()) {
				commentId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			comment.setCommentId(commentId);;
			return comment;
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


	public Comments delete(Comments comment) throws SQLException {
		String deleteComment = "DELETE FROM Comments WHERE commentId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteComment);
			deleteStmt.setInt(1, comment.getCommentId());
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
	
	public Comments updateReviewId(Comments comment, int newReviewId) throws SQLException {
		String updateComment = "UPDATE Comments SET reviewId=? WHERE commentId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateComment);
			updateStmt.setInt(1, newReviewId);
			updateStmt.setInt(2, comment.getCommentId());
			updateStmt.executeUpdate();
			ReviewsDao reviewsDao = ReviewsDao.getInstance();
			Reviews newReview = reviewsDao.getReviewById(newReviewId);

			comment.setReview(newReview);
			return comment;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}


	public Comments getCommentById(int commentId) throws SQLException {
		String selectComment =
			"SELECT commentId,userId,reviewId " +
			"FROM Comments " +
			"WHERE commentId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectComment);
			selectStmt.setInt(1, commentId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			ReviewsDao reviewsDao = ReviewsDao.getInstance();
			if(results.next()) {
				int resultCommentId = results.getInt("commentId");
				int userId = results.getInt("userId");
				int reviewId = results.getInt("reviewId");
				
				Users user = usersDao.getUserByUserName(userId);
				Reviews review = reviewsDao.getReviewById(reviewId);
				Comments comment = new Comments(resultCommentId, user, review);
				return comment;
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

	/**
	 * Get the all the Comments for a user.
	 */
	public List<Comments> getCommentsByUserId(int userId) throws SQLException {
		List<Comments> comments = new ArrayList<Comments>();
		String selectComments =
			"SELECT commentId,userId,reviewId " +
			"FROM Comments " + 
			"WHERE userId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectComments);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			ReviewsDao reviewsDao = ReviewsDao.getInstance();
			while(results.next()) {
				int commentId = results.getInt("commentId");
				int resultUserId = results.getInt("userId");
				int reviewId = results.getInt("reviewId");
				
				Users user = usersDao.getUserByUserName(resultUserId);
				Reviews review = reviewsDao.getReviewById(reviewId);
				Comments comment = new Comments(commentId, user, review);
				comments.add(comment);
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
		return comments;
	}
	
	public List<Comments> getCommentsByReviewId(int reviewId) throws SQLException {
		List<Comments> comments = new ArrayList<Comments>();
		String selectComments =
			"SELECT commentId,userId,reviewId " +
			"FROM Comments " + 
			"WHERE reviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectComments);
			selectStmt.setInt(1, reviewId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			ReviewsDao reviewsDao = ReviewsDao.getInstance();
			while(results.next()) {
				int commentId = results.getInt("commentId");
				int userId = results.getInt("userId");
				int resultReviewId = results.getInt("reviewId");
				
				Users user = usersDao.getUserByUserName(userId);
				Reviews review = reviewsDao.getReviewById(resultReviewId);
				Comments comment = new Comments(commentId, user, review);
				comments.add(comment);
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
		return comments;
	}

}
