/**
 * author Lilan Wu
 */

package com.hiking.dal;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hiking.model.HikingTrails;
import com.hiking.model.Reviews;
import com.hiking.model.Users;

public class ReviewsDao {
	protected ConnectionManager connectionManager;

	private static ReviewsDao instance = null;
	protected ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewsDao getInstance() {
		if(instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}

	public Reviews create(Reviews review) throws SQLException {
		String insertReview =
			"INSERT INTO Reviews(userId,trailId,createTime,review,rating) " +
			"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, review.getUser().getUserId());
			insertStmt.setInt(2, review.getHikingTrail().getTrailId());
			insertStmt.setTimestamp(3, new Timestamp(review.getCreatedTime().getTime()));
			insertStmt.setString(4, review.getReview());
			insertStmt.setFloat(5, review.getRating());

			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int reviewId = -1;
			if(resultKey.next()) {
				reviewId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setReviewId(reviewId);;
			return review;
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


	public Reviews delete(Reviews review) throws SQLException {
		String deleteReview = "DELETE FROM Reviews WHERE reviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, review.getReviewId());
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
	
	public Reviews updateReview(Reviews review, String newReview) throws SQLException {
		String updateReview = "UPDATE Reviews SET review=?,createTime=? WHERE reviewId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateReview);
			updateStmt.setString(1, newReview);
			// Sets the Created timestamp to the current time.
			Date newCreatedTimestamp = new Date();
			updateStmt.setTimestamp(2, new Timestamp(newCreatedTimestamp.getTime()));
			updateStmt.setInt(3, review.getReviewId());
			updateStmt.executeUpdate();

			review.setReview(newReview);
			review.setCreatedTime(newCreatedTimestamp);
			return review;
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
	


	public Reviews getReviewById(int reviewId) throws SQLException {
		String selectReview =
			"SELECT reviewId,userId,trailId,createTime,review,rating " +
			"FROM Reviews " +
			"WHERE reviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, reviewId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			HikingTrailsDao hikingTrailsDao = HikingTrailsDao.getInstance();
			if(results.next()) {
				int resultReviewId = results.getInt("reviewId");
				int userId = results.getInt("userId");
				int trailId = results.getInt("trailId");
				Date createTime =  new Date(results.getTimestamp("createTime").getTime());
				String reviewContent = results.getString("review");
				float rating = results.getFloat("rating");
				
				Users user = usersDao.getUserById(userId);
				HikingTrails hikingTrail = hikingTrailsDao.getHikingTrailById(trailId);
				Reviews review = new Reviews(resultReviewId, user, hikingTrail,createTime,reviewContent,rating);
				return review;
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
	 * Get the all the Reviews for a user.
	 */
	public List<Reviews> getReviewsByUserId(int userId) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReviews =
			"SELECT reviewId,userId,trailId,createTime,review,rating " +
			"FROM Reviews " + 
			"WHERE userId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			HikingTrailsDao hikingTrailsDao = HikingTrailsDao.getInstance();
			while(results.next()) {
				int reviewId = results.getInt("reviewId");
				int resultUserId = results.getInt("userId");
				int trailId = results.getInt("trailId");
				Date createTime =  new Date(results.getTimestamp("createTime").getTime());
				String reviewContent = results.getString("review");
				float rating = results.getFloat("rating");
				
				Users user = usersDao.getUserById(resultUserId);
				HikingTrails hikingTrail = hikingTrailsDao.getHikingTrailById(trailId);
				Reviews review = new Reviews(reviewId, user, hikingTrail,createTime,reviewContent,rating);
				reviews.add(review);
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
		return reviews;
	}
	
	public List<Reviews> getReviewsByTrailId(int trailId) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReviews =
			"SELECT reviewId,userId,trailId,createTime,review,rating " +
			"FROM Reviews " + 
			"WHERE trailId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1, trailId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			HikingTrailsDao hikingTrailsDao = HikingTrailsDao.getInstance();
			while(results.next()) {
				int reviewId = results.getInt("reviewId");
				int userId = results.getInt("userId");
				int resultTrailId = results.getInt("trailId");
				Date createTime =  new Date(results.getTimestamp("createTime").getTime());
				String reviewContent = results.getString("review");
				float rating = results.getFloat("rating");
				
				Users user = usersDao.getUserById(userId);
				HikingTrails hikingTrail = hikingTrailsDao.getHikingTrailById(resultTrailId);
				Reviews review = new Reviews(reviewId, user, hikingTrail,createTime,reviewContent,rating);
				reviews.add(review);
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
		return reviews;
	}

}
