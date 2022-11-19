package hiking.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hiking.model.User;

public class UsersDao {
	protected ConnectionManager connectionManager;
	
	private static UsersDao instance = null;
	protected UsersDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}

	public User createUser(User user) throws SQLException {
		String insertUser =
			"INSERT INTO Users (userId,firstName,lastName,password,age,weight,height,address,email,phoneNumber,Gender,HikingLevel) " +
			"VALUES(?,?,?,?,?,?,?,?,?,?,?.?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// Users has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertUser,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, user.getUserId());
			insertStmt.setString(2, user.getFirstName());
			insertStmt.setString(3, user.getLastName());
			insertStmt.setString(4, user.getPassword());
			insertStmt.setInt(5, user.getAge());
			insertStmt.setDouble(6,user.getWeight());
			insertStmt.setDouble(7, user.getHeight());
			insertStmt.setString(8,user.getAddress());
			insertStmt.setString(9,user.getEmail());
			insertStmt.setString(10,user.getPhoneNumber());
			insertStmt.setString(11,user.getGender().name());
			insertStmt.setString(12,user.getHikingLevel().name());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			// For more details, see:
			// http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-last-insert-id.html
			resultKey = insertStmt.getGeneratedKeys();
			int userId = -1;
			if(resultKey.next()) {
				userId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			user.setUserId(userId);
			return user;
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
	
	public User updateLastName(User user, String newLastName) throws SQLException {
		String updateUser = "UPDATE Users SET lastName=? WHERE userId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUser);
			updateStmt.setString(1, newLastName);
			updateStmt.setInt(2, user.getUserId());
			updateStmt.executeUpdate();
			
			// Update the user param before returning to the caller.
			user.setLastName(newLastName);
			return user;
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
	
	public User delete(User user) throws SQLException {
		String deleteUser = "DELETE FROM Users WHERE userId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1, user.getUserId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Users instance.
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
	

	public User getUserById(int userId) throws SQLException {
		String selectUser =
			"SELECT userId,firstName,lastName,password,age,weight,height,address,email,phoneNumber,gender,hikingLevel" +
			"FROM  Users " +
			"WHERE userId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultUserId = results.getInt("userId");
				String firstName = results.getString("firstName");
				String lastName = results.getString("lastName");
				String password = results.getString("password");
				int age = results.getInt("age");
				double weight = results.getDouble("weight");
				double height= results.getDouble("height");
				String address = results.getString("address");
				String email = results.getString("email");
				String phoneNumber = results.getString("phoneNumber");
				User.Gender gender = User.Gender.valueOf(results.getString("gender"));
				User.HikingLevel hikingLevel= User.HikingLevel.valueOf(results.getString("hikingLevel"));
				User user = new User(resultUserId,firstName,lastName,password,age,weight,height,address, email, phoneNumber,
					gender,hikingLevel);
				return user;
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
}




