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

import com.hiking.dal.*;
import com.hiking.model.*;

public class RecommendationsDao {
	protected ConnectionManager connectionManager;

	private static RecommendationsDao instance = null;
	protected RecommendationsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecommendationsDao getInstance() {
		if(instance == null) {
			instance = new RecommendationsDao();
		}
		return instance;
	}

	public Recommendations create(Recommendations recommendation) throws SQLException {
		String insertRecommendation =
			"INSERT INTO Recommendations(userId,trailId) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecommendation,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, recommendation.getUser().getUserId());
			insertStmt.setInt(2, recommendation.getHikingTrail().getTrailId());
			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int recommendationId = -1;
			if(resultKey.next()) {
				recommendationId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			recommendation.setRecommendationId(recommendationId);;
			return recommendation;
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


	public Recommendations delete(Recommendations recommendation) throws SQLException {
		String deleteRecommendation = "DELETE FROM Recommendations WHERE recommendationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRecommendation);
			deleteStmt.setInt(1, recommendation.getRecommendationId());
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

	public Recommendations updateTrailId(Recommendations recommendation, int newTrailId) throws SQLException {
		String updateRecommendation = "UPDATE Recommendations SET trailId=? WHERE recommendationId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRecommendation);
			updateStmt.setInt(1, newTrailId);
			updateStmt.setInt(2, recommendation.getRecommendationId());
			updateStmt.executeUpdate();
			HikingTrailsDao hikingTrailsDao = HikingTrailsDao.getInstance();
			HikingTrails newHikingTrail = hikingTrailsDao.getHikingTrailById(newTrailId);

			recommendation.setHikingTrail(newHikingTrail);;
			return recommendation;
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


	public Recommendations getRecommendationById(int recommendationId) throws SQLException {
		String selectRecommendation =
			"SELECT recommendationId,userId,trailId " +
			"FROM Recommendations " +
			"WHERE recommendationId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendation);
			selectStmt.setInt(1, recommendationId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			HikingTrailsDao hikingTrailsDao = HikingTrailsDao.getInstance();
			if(results.next()) {
				int resultRecommendationId = results.getInt("recommendationId");
				int userId = results.getInt("userId");
				int trailId = results.getInt("trailId");

				Users user = usersDao.getUserById(userId);
				HikingTrails hikingTrail = hikingTrailsDao.getHikingTrailById(trailId);
				Recommendations recommendation = new Recommendations(resultRecommendationId, user, hikingTrail);
				return recommendation;
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
	 * Get the all the Recommendations for a user.
	 */
	public List<Recommendations> getRecommendationsForUser(Users user) throws SQLException {
		List<Recommendations> recommendations = new ArrayList<Recommendations>();
		String selectRecommendations =
			"SELECT recommendationId,userId,trailId " +
			"FROM Recommendations " +
			"WHERE userId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendations);
			selectStmt.setInt(1, user.getUserId());
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			HikingTrailsDao hikingTrailsDao = HikingTrailsDao.getInstance();
			while(results.next()) {
				int recommendationId = results.getInt("recommendationId");
				int resultUserId = results.getInt("userId");
				int trailId = results.getInt("trailId");

				user = usersDao.getUserById(resultUserId);
				HikingTrails hikingTrail = hikingTrailsDao.getHikingTrailById(trailId);
				Recommendations recommendation = new Recommendations(recommendationId, user, hikingTrail);
				recommendations.add(recommendation);
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
		return recommendations;
	}
	
	/**
	 * Get the all the Recommendations for a user.
	 */
	public List<Recommendations> getRecommendationsByUserId(int userId) throws SQLException {
		List<Recommendations> recommendations = new ArrayList<Recommendations>();
		String selectRecommendations =
			"SELECT recommendationId,userId,trailId " +
			"FROM Recommendations " +
			"WHERE userId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendations);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			HikingTrailsDao hikingTrailsDao = HikingTrailsDao.getInstance();
			while(results.next()) {
				int recommendationId = results.getInt("recommendationId");
				int resultUserId = results.getInt("userId");
				int trailId = results.getInt("trailId");

				Users user = usersDao.getUserById(resultUserId);
				HikingTrails hikingTrail = hikingTrailsDao.getHikingTrailById(trailId);
				Recommendations recommendation = new Recommendations(recommendationId, user, hikingTrail);
				recommendations.add(recommendation);
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
		return recommendations;
	}

	public List<Recommendations> getRecommendationsByTrailId(int trailId) throws SQLException {
		List<Recommendations> recommendations = new ArrayList<Recommendations>();
		String selectRecommendations =
			"SELECT recommendationId,userId,trailId " +
			"FROM Recommendations " +
			"WHERE trailId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendations);
			selectStmt.setInt(1, trailId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			HikingTrailsDao hikingTrailsDao = HikingTrailsDao.getInstance();
			while(results.next()) {
				int resultRecommendationId = results.getInt("recommendationId");
				int userId = results.getInt("userId");
				int resultTrailId = results.getInt("trailId");

				Users user = usersDao.getUserById(userId);
				HikingTrails hikingTrail = hikingTrailsDao.getHikingTrailById(resultTrailId);
				Recommendations recommendation = new Recommendations(resultRecommendationId, user, hikingTrail);
				recommendations.add(recommendation);
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
		return recommendations;
	}

}
