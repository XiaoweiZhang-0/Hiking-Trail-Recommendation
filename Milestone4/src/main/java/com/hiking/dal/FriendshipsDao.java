package com.hiking.dal;


import com.hiking.dal.*;

import com.hiking.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class FriendshipsDao {

	protected ConnectionManager connectionManager;
	private static FriendshipsDao instance = null;

	protected FriendshipsDao( ) {
		connectionManager = new ConnectionManager();
	}

	public static FriendshipsDao getInstance() {
		if(instance == null) {
			instance = new FriendshipsDao();
		}
		return instance;
	}

	public Friendships create(Friendships friendship) throws SQLException {

		String insertFriendship =
				"INSERT INTO Friendship(userId1,userId2) "
				+ "VALUES(?,?);";

		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFriendship);
			insertStmt.setInt(1, friendship.getUser1().getUserId());
			insertStmt.setInt(2, friendship.getUser2().getUserId());
			insertStmt.executeUpdate();
			return friendship;

		}  catch (SQLException e) {
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

	public List<Friendships> getFriendshipsByUserId(int userId) throws SQLException {
		List <Friendships> friendships = new ArrayList<Friendships>();
		String selectFriendship =
				"SELECT userId1,userId2 "
				+ "FROM Friendship "
				+ "WHERE userId1=? OR userId2=?;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;


		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFriendship);

			selectStmt.setInt(1, userId);
			selectStmt.setInt(2, userId);

			results = selectStmt.executeQuery();
			UsersDao userDao = UsersDao.getInstance();

			while(results.next()) {
				int userId1 = results.getInt("userId1");
				int userId2 = results.getInt("userId2");
				Users user1 = userDao.getUserById(userId1);
				Users user2 = userDao.getUserById(userId2);
				Friendships friendship = new Friendships(user1, user2);

				friendships.add(friendship);
			}
			
			return friendships;
			
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
		
	}

	public Friendships delete(Friendships friendship) throws SQLException {
		String deleteFriendship =
				"DELETE "
				+ "FROM Friendship "
				+ "WHERE userId1=? OR userId2=?;";

		Connection connection = null;
		PreparedStatement deleteStmt = null;

		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFriendship);
			deleteStmt.setInt(1, friendship.getUser1().getUserId());
			deleteStmt.setInt(2, friendship.getUser2().getUserId());
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
	
	public Friendships updateUserId2(Friendships friendship, int newUserId2) throws SQLException {
		String updateRecommendation = "UPDATE Friendship "
				+ "SET userId2=? "
				+ "WHERE userId1=? AND userId2=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRecommendation);
			updateStmt.setInt(1, newUserId2);
			updateStmt.setInt(2, friendship.getUser1().getUserId());
			updateStmt.setInt(3, friendship.getUser2().getUserId());
			updateStmt.executeUpdate();

			UsersDao user2Dao = UsersDao.getInstance();
			Users newUser2 = user2Dao.getUserById(newUserId2);

			friendship.setUser2(newUser2);;
			return friendship;
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

}
