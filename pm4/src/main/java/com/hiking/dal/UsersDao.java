package com.hiking.dal;

import com.hiking.dal.ConnectionManager;


import com.hiking.model.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UsersDao {

    protected ConnectionManager connectionManager;

    private static UsersDao instance = null;

    protected UsersDao() {
        connectionManager = new ConnectionManager();
    }

    public static UsersDao getInstance() {
        if (instance == null) {
            instance = new UsersDao();
        }
        return instance;
    }

    public Users create(Users user) throws SQLException {
        String insertUser =
                "INSERT INTO Users(firstName,lastName, password, gender, age, weight, height, hikingLevel, address, phoneNumber, email) " +
                        "VALUES(?,?, ?,?, ?,?, ?,?, ?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            // Users has an auto-generated key. So we want to retrieve that key.
            insertStmt = connection.prepareStatement(insertUser,
                    Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, user.getFirstName());
            insertStmt.setString(2, user.getLastName());
            insertStmt.setString(3, user.getPassword());
            insertStmt.setString(4, user.getGender().name());
            insertStmt.setInt(5, user.getAge());
            insertStmt.setInt(6, user.getWeight());
            insertStmt.setDouble(7, user.getHeight());
            insertStmt.setString(8, user.getHikingLevel().name());
            insertStmt.setString(9, user.getAddress());
            insertStmt.setString(10, user.getPhoneNumber());
            insertStmt.setString(11, user.getEmail());


            insertStmt.executeUpdate();

            // Retrieve the auto-generated key and set it, so it can be used by the caller.
            // For more details, see:
            // http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-last-insert-id.html
            resultKey = insertStmt.getGeneratedKeys();
            int userId = -1;
            if (resultKey.next()) {
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
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
            if (resultKey != null) {
                resultKey.close();
            }
        }
    }

    public Users updateLastName(Users user, String newLastName) throws SQLException {
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
            if (connection != null) {
                connection.close();
            }
            if (updateStmt != null) {
                updateStmt.close();
            }
        }
    }

    public Users delete(Users user) throws SQLException {
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
            if (connection != null) {
                connection.close();
            }
            if (deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }

    public Users getUserById(int userId) throws SQLException {
        String selectUser =
                "SELECT  userId, firstName,lastName,password, gender, age, weight, height, hikingLevel, address, phoneNumber, email " +
                        "FROM Users " +
                        "WHERE userId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectUser);
            selectStmt.setInt(1, userId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int resultUserId = results.getInt("userId");
                String firstName = results.getString("firstName");
                String lastName = results.getString("lastName");
                String password = results.getString("password");
//                Users.Gender gender = Users.Gender.valueOf(results.getString("gender"));
                Users.Gender gender = null;

                int age = results.getInt("age");
                int weight = results.getInt("weight");
                double height = results.getDouble("height");
                Users.HikingLevel hikingLevel = null;
                if(results.getString("hikingLevel") != null){
                        hikingLevel = Users.HikingLevel.valueOf(
                        results.getString("hikingLevel"));
                }
                String address = results.getString("address");
                String phoneNumber = results.getString("phoneNumber");
                String email = results.getString("email");

                Users user = new Users(resultUserId, firstName, lastName, password, gender, age, weight, height, hikingLevel, address, phoneNumber, email);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return null;
    }

//	public Users getUserById(int userId) throws SQLException {
//		String selectUser =
//			"SELECT userId,firstName,lastName, password, gender, age, weight, height, hikingLevel, address, phoneNumber, email" +
//			"FROM Users" +
//			"WHERE userId=?;";
//		Connection connection = null;
//		PreparedStatement selectStmt = null;
//		ResultSet results = null;
//		try {
//			connection = connectionManager.getConnection();
//			selectStmt = connection.prepareStatement(selectUser);
//			selectStmt.setInt(1, userId);
//			results = selectStmt.executeQuery();
//
//			if(results.next()) {
//				int resultUserId = results.getInt("userId");
//				String firstName = results.getString("firstName");
//				String lastName = results.getString("lastName");
//				String password = results.getString("password");
//				int age = results.getInt("age");
//				int weight = results.getInt("weight");
//				double height= results.getDouble("height");
//				String address = results.getString("address");
//				String email = results.getString("email");
//				String phoneNumber = results.getString("phoneNumber");
//				Users.Gender gender = Users.Gender.valueOf(results.getString("gender"));
//				Users.HikingLevel hikingLevel= Users.HikingLevel.valueOf(results.getString("hikingLevel"));
//				Users user = new Users(resultUserId,firstName,lastName, password, gender, age, weight, height, hikingLevel, address, phoneNumber, email);
//				return user;
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
}