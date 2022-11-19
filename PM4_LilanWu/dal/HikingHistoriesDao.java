/**
 * author Lilan Wu
 */

package blog.dal;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import blog.model.*;

public class HikingHistoriesDao {
	protected ConnectionManager connectionManager;

	private static HikingHistoriesDao instance = null;
	protected HikingHistoriesDao() {
		connectionManager = new ConnectionManager();
	}
	public static HikingHistoriesDao getInstance() {
		if(instance == null) {
			instance = new HikingHistoriesDao();
		}
		return instance;
	}

	public HikingHistories create(HikingHistories hikingHistory) throws SQLException {
		String insertHikingHistory =
			"INSERT INTO HikingHistories(userId,trailId,travelTime) " +
			"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertHikingHistory,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, hikingHistory.getUser().getUserId());
			insertStmt.setInt(2, hikingHistory.getHikingTrail().getTrailId());
			insertStmt.setDate(3, (java.sql.Date) hikingHistory.getTravelTime());

			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int hikingHistoryId = -1;
			if(resultKey.next()) {
				hikingHistoryId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			hikingHistory.setHikingHistoryId(hikingHistoryId);;
			return hikingHistory;
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


	public HikingHistories delete(HikingHistories hikingHistory) throws SQLException {
		String deleteHikingHistory = "DELETE FROM HikingHistories WHERE hikingHistoryId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteHikingHistory);
			deleteStmt.setInt(1, hikingHistory.getHikingHistoryId());
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
	
	public HikingHistories updateHikingHistory(HikingHistories hikingHistory, int newTrailId, Date newTravelTime) throws SQLException {
		String updateHikingHistory = "UPDATE HikingHistories SET trailId=?, travelTime=? WHERE hikingHistoryId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateHikingHistory);
			updateStmt.setInt(1, newTrailId);
			// Sets the Created timestamp to the current time.
			updateStmt.setDate(2, (java.sql.Date) newTravelTime);
			updateStmt.setInt(3, hikingHistory.getHikingHistoryId());
			updateStmt.executeUpdate();
			HikingTrailsDao hikingTrailsDao = HikingTrailsDao.getInstance();
			HikingTrails newHikingTrail = hikingTrailsDao.getHikingTrailById(newTrailId);

			hikingHistory.setHikingTrail(newHikingTrail);;
			hikingHistory.setTravelTime(newTravelTime);;
			return hikingHistory;
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
	


	public HikingHistories getHikingHistoryById(int hikingHistoryId) throws SQLException {
		String selectHikingHistory =
			"SELECT hikingHistoryId,userId,trailId,travelTime " +
			"FROM HikingHistories " +
			"WHERE hikingHistoryId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHikingHistory);
			selectStmt.setInt(1, hikingHistoryId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			HikingTrailsDao hikingTrailsDao = HikingTrailsDao.getInstance();
			if(results.next()) {
				int resultHikingHistoryId = results.getInt("hikingHistoryId");
				int userId = results.getInt("userId");
				int trailId = results.getInt("trailId");
				Date travelTime =  results.getDate("travelTime");
				
				
				Users user = usersDao.getUserById(userId);
				HikingTrails hikingTrail = hikingTrailsDao.getHikingTrailById(trailId);
				HikingHistories hikingHistory = new HikingHistories(resultHikingHistoryId, user, hikingTrail,travelTime);
				return hikingHistory;
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
	 * Get the all the HikingHistories for a user.
	 */
	public List<HikingHistories> getHikingHistoriesByUserId(int userId) throws SQLException {
		List<HikingHistories> hikingHistorys = new ArrayList<HikingHistories>();
		String selectHikingHistories =
			"SELECT hikingHistoryId,userId,trailId,travelTime " +
			"FROM HikingHistories " + 
			"WHERE userId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHikingHistories);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			HikingTrailsDao hikingTrailsDao = HikingTrailsDao.getInstance();
			while(results.next()) {
				int hikingHistoryId = results.getInt("hikingHistoryId");
				int resultUserId = results.getInt("userId");
				int trailId = results.getInt("trailId");
				Date travelTime =  results.getDate("travelTime");
				
				
				Users user = usersDao.getUserById(resultUserId);
				HikingTrails hikingTrail = hikingTrailsDao.getHikingTrailById(trailId);
				HikingHistories hikingHistory = new HikingHistories(hikingHistoryId, user, hikingTrail,travelTime);
				hikingHistorys.add(hikingHistory);
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
		return hikingHistorys;
	}
	
	public List<HikingHistories> getHikingHistoriesByTrailId(int trailId) throws SQLException {
		List<HikingHistories> hikingHistorys = new ArrayList<HikingHistories>();
		String selectHikingHistories =
			"SELECT hikingHistoryId,userId,trailId,travelTime  " +
			"FROM HikingHistories " + 
			"WHERE trailId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHikingHistories);
			selectStmt.setInt(1, trailId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			HikingTrailsDao hikingTrailsDao = HikingTrailsDao.getInstance();
			while(results.next()) {
				int hikingHistoryId = results.getInt("hikingHistoryId");
				int userId = results.getInt("userId");
				int resultTrailId = results.getInt("trailId");
				Date travelTime =  results.getDate("travelTime");
				
				
				Users user = usersDao.getUserById(userId);
				HikingTrails hikingTrail = hikingTrailsDao.getHikingTrailById(resultTrailId);
				HikingHistories hikingHistory = new HikingHistories(hikingHistoryId, user, hikingTrail,travelTime);
				hikingHistorys.add(hikingHistory);
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
		return hikingHistorys;
	}

}
