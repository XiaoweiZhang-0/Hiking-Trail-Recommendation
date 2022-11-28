package com.hiking.dal;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hiking.dal.*;
import com.hiking.model.*;

public class LikeReviewsDao {

	protected ConnectionManager connectionManager;
	private static LikeReviewsDao instance = null;

	protected LikeReviewsDao( ) {
		connectionManager = new ConnectionManager();
	}

	public static LikeReviewsDao getInstance() {
		if(instance == null) {
			instance = new LikeReviewsDao();
		}
		return instance;
	}

	public LikeReviews create(LikeReviews likeReview) throws SQLException {
		String inserLikeReviews =
				"INSERT INTO LikeReviews(userId,reviewId) "
				+ "VALUES(?,?)";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(inserLikeReviews,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, likeReview.getUser().getUserId());
			insertStmt.setInt(2, likeReview.getReview().getReviewId());
			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int likeReviewId = -1;

			if (resultKey.next()) {
				likeReviewId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}

			likeReview.setLikeReviewId(likeReviewId);
			return likeReview;

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

	public LikeReviews getLikeReviewsById(int likeReviewId) throws SQLException {

		String selectLikeReview =
				"SELECT likeReviewId,userId,reviewId "
				+ "FROM LikeReviews "
				+ "WHERE likeReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLikeReview);
			selectStmt.setInt(1, likeReviewId);
			results = selectStmt.executeQuery();
			UsersDao userDao = UsersDao.getInstance();
			ReviewsDao reviewDao = ReviewsDao.getInstance();
			if(results.next()) {
				int userId = results.getInt("userId");
				int reviewId = results.getInt("reviewId");
				Users user = userDao.getUserById(userId);
				Reviews review = reviewDao.getReviewById(reviewId);
				LikeReviews likeReviews = new LikeReviews(likeReviewId, user, review);
				return likeReviews;
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

	public LikeReviews delete(LikeReviews likeReview) throws SQLException {

		String deleteLikeReview =
				"DELETE "
				+ "FROM LikeReviews "
				+ "WHERE likeReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;

		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLikeReview);
			deleteStmt.setInt(1, likeReview.getLikeReviewId());
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
	
	
	
	public LikeReviews updateReviewId(LikeReviews likeReview, int newReviewId) throws SQLException {
		String updateRecommendation = "UPDATE LikeReviews SET reviewId=? WHERE likeReviewId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRecommendation);
			updateStmt.setInt(1, newReviewId);
			updateStmt.setInt(2, likeReview.getLikeReviewId());
			updateStmt.executeUpdate();
			
			ReviewsDao reviewDao = ReviewsDao.getInstance();
			Reviews newReview = reviewDao.getReviewById(newReviewId);
					
			likeReview.setReview(newReview);;
			return likeReview;
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
	
	/**
	 * Get the all the Recommendations for a user.
	 */
	public List<LikeReviews> getLikeReviewsByUserId(int userId) throws SQLException {
		List<LikeReviews> likeReviews = new ArrayList<LikeReviews>();
		String selectLikeReviews =
			"SELECT likeReviewId,userId,reviewId " +
			"FROM LikeReviews " +
			"WHERE userId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLikeReviews);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			ReviewsDao reviewsDao = ReviewsDao.getInstance();
			while(results.next()) {
				int likeReviewId = results.getInt("likeReviewId");
				int resultUserId = results.getInt("userId");
				int reviewId = results.getInt("reviewId");

				Users user = usersDao.getUserById(resultUserId);
				Reviews review = reviewsDao.getReviewById(1);
				LikeReviews likeReview = new LikeReviews(likeReviewId, user, review);
				likeReviews.add(likeReview);
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
		return likeReviews;
	}
}
