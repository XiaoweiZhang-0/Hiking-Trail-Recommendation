//package com.hiking.dal;
//
//import com.hiking.model.HikingTrails;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//
//public class HikingTrailsDao {
//
//    protected ConnectionManager connectionManager;
//
//	private static HikingTrailsDao instance = null;
//	protected HikingTrailsDao() {
//		connectionManager = new ConnectionManager();
//	}
//
//	public static HikingTrailsDao getInstance() {
//		if(instance == null) {
//			instance = new HikingTrailsDao();
//		}
//		return instance;
//	}
//
//
//	public HikingTrails create(HikingTrails hikingTrail) throws SQLException {
//		String insertHikingTrail =
//			"INSERT INTO HikingTrails(trailId,trailName,county,length,trailSystem,surface,canBicycle,canCarDrive) " +
//			"VALUES(?,?,?,?,?,?,?,?);";
//		Connection connection = null;
//		PreparedStatement insertStmt = null;
//		ResultSet resultKey = null;
//		try {
//			connection = connectionManager.getConnection();
//			// BlogPosts has an auto-generated key. So we want to retrieve that key.
//			insertStmt = connection.prepareStatement(insertHikingTrail,
//				Statement.RETURN_GENERATED_KEYS);
//			insertStmt.setInt(1, hikingTrail.getTrailId());
//			insertStmt.setString(2, hikingTrail.getTrailName());
//			insertStmt.setString(3, hikingTrail.getCounty());
//			insertStmt.setDouble(4, hikingTrail.getLength());
//			insertStmt.setString(5, hikingTrail.getTrailSystem());
//			insertStmt.setString(6, hikingTrail.getSurface());
//			insertStmt.setBoolean(7, hikingTrail.isCanBicycle());
//			insertStmt.setBoolean(8, hikingTrail.isCanCarDrive());
//			insertStmt.executeUpdate();
//
//			// Retrieve the auto-generated key and set it, so it can be used by the caller.
//			// For more details, see:
//			// http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-last-insert-id.html
//			resultKey = insertStmt.getGeneratedKeys();
//			int trailId = -1;
//			if(resultKey.next()) {
//				trailId = resultKey.getInt(1);
//			} else {
//				throw new SQLException("Unable to retrieve auto-generated key.");
//			}
//			hikingTrail.setTrailId(trailId);
//			return hikingTrail;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			if(connection != null) {
//				connection.close();
//			}
//			if(insertStmt != null) {
//				insertStmt.close();
//			}
//			if(resultKey != null) {
//				resultKey.close();
//			}
//		}
//	}
//
//	public HikingTrails delete(HikingTrails hikingTrail) throws SQLException {
//		String deleteUser = "DELETE FROM Users WHERE userId=?;";
//		Connection connection = null;
//		PreparedStatement deleteStmt = null;
//		try {
//			connection = connectionManager.getConnection();
//			deleteStmt = connection.prepareStatement(deleteUser);
//			deleteStmt.setInt(1, hikingTrail.getTrailId());
//			deleteStmt.executeUpdate();
//
//			// Return null so the caller can no longer operate on the Users instance.
//			return null;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			if(connection != null) {
//				connection.close();
//			}
//			if(deleteStmt != null) {
//				deleteStmt.close();
//			}
//		}
//	}
//
//	public HikingTrails updateTrailtName(HikingTrails hikingTrail, String newTrailtName) throws SQLException {
//		String updateTrailName = "UPDATE HikingTrails SET trailName=? WHERE trailId=?;";
//		Connection connection = null;
//		PreparedStatement updateStmt = null;
//		try {
//			connection = connectionManager.getConnection();
//			updateStmt = connection.prepareStatement(updateTrailName);
//			updateStmt.setString(1, newTrailtName);
//			updateStmt.setInt(2, hikingTrail.getTrailId());
//			updateStmt.executeUpdate();
//
//			// Update the user param before returning to the caller.
//			hikingTrail.setTrailName(newTrailtName);
//			return hikingTrail;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			if(connection != null) {
//				connection.close();
//			}
//			if(updateStmt != null) {
//				updateStmt.close();
//			}
//		}
//	}
//
//	public HikingTrails getHikingTrailById(int trailId) throws SQLException {
//		String selectUser =
//			"SELECT trailId,trailName,county,length,trailSystem,surface,canBicycle,canCarDrive" +
//			"FROM  HikingTrails " +
//			"WHERE trailId=?;";
//		Connection connection = null;
//		PreparedStatement selectStmt = null;
//		ResultSet results = null;
//		try {
//			connection = connectionManager.getConnection();
//			selectStmt = connection.prepareStatement(selectUser);
//			selectStmt.setInt(1, trailId);
//			results = selectStmt.executeQuery();
//
//			if(results.next()) {
//				int resultTrailId = results.getInt("trailId");
//				String trailName = results.getString("trailName");
//				String county = results.getString("county");
//				double length = results.getDouble("length");
//				String trailSystem = results.getString("trailSystem");
//				String surface = results.getString("surface");
//				boolean canBicycle = results.getBoolean("canBicycle");
//				boolean canCarDrive = results.getBoolean("canCarDrive");
//
//				HikingTrails hikingTrail = new HikingTrails(resultTrailId,trailName,county,length,trailSystem,surface,canBicycle,canCarDrive
//					);
//				return hikingTrail;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			if(connection != null) {
//				connection.close();
//			}
//			if(selectStmt != null) {
//				selectStmt.close();
//			}
//			if(results != null) {
//				results.close();
//			}
//		}
//		return null;
//	}
//
//
//}
