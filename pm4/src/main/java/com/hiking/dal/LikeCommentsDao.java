package com.hiking.dal;


import com.hiking.dal.*;
import com.hiking.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


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
				int userId = results.getInt("userId");
				int commentId = results.getInt("commentId");
				Users user = userDao.getUserById(userId);
				Comments comment = commentDao.getCommentById(commentId);
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
	
	public LikeComments updateCommentId(LikeComments likeComment, int newCommentId) throws SQLException {
		String updateRecommendation = "UPDATE LikeComments SET commentId=? WHERE likeCommentId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRecommendation);
			updateStmt.setInt(1, newCommentId);
			updateStmt.setInt(2, likeComment.getLikeCommentId());
			updateStmt.executeUpdate();
			
			CommentsDao commentDao = CommentsDao.getInstance();
			Comments newComment = commentDao.getCommentById(newCommentId);
					
			likeComment.setComment(newComment);;
			return likeComment;
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
	
	public List<LikeComments> getLikeCommentsByUserId(int userId) throws SQLException {
		List<LikeComments> likeComments = new ArrayList<LikeComments>();
		String selectLikeComments =
			"SELECT likeCommentId,userId,commentId " +
			"FROM LikeComments " +
			"WHERE userId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLikeComments);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			CommentsDao commentsDao = CommentsDao.getInstance();
			while(results.next()) {
				int likeCommentId = results.getInt("likeCommentId");
				int resultUserId = results.getInt("userId");
				int commentId = results.getInt("commentId");

				Users user = usersDao.getUserById(resultUserId);
				Comments comment = commentsDao.getCommentById(commentId);
				LikeComments likeComment = new LikeComments(likeCommentId, user, comment);
				likeComments.add(likeComment);
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
		return likeComments;
	}
	
}
