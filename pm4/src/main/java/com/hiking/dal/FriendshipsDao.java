package com.hiking.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hiking.model.Friendships;
import com.hiking.model.Users;

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
			insertStmt.setInt(1, friendship.getUser1().getUserId);
			insertStmt.setInt(2, friendship.getUser2().getUserId);
			
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
	
	public List<Friendships> getFriendshipsByUserId(int userId) {
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
				Users user1 = userDao.getUserByUserId("userId1");
				Users user2 = userDao.getUserByUserId("userId2");
				Friendships friendship = new Friendships(user1, user2);
				
				friendships.add(friendship);
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
	
}
