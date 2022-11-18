package blog.dal;

import blog.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ResharesDao {
	protected ConnectionManager connectionManager;

	private static ResharesDao instance = null;
	protected ResharesDao() {
		connectionManager = new ConnectionManager();
	}
	public static ResharesDao getInstance() {
		if(instance == null) {
			instance = new ResharesDao();
		}
		return instance;
	}

	public Reshares create(Reshares reshare) throws SQLException {
		String insertReshare =
			"INSERT INTO Reshares(UserName,PostId) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReshare,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, reshare.getBlogUser().getUserName());
			insertStmt.setInt(2, reshare.getBlogPost().getPostId());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int reshareId = -1;
			if(resultKey.next()) {
				reshareId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			reshare.setReshareId(reshareId);
			return reshare;
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

	/**
	 * Delete the Reshares instance.
	 * This runs a DELETE statement.
	 */
	public Reshares delete(Reshares reshare) throws SQLException {
		String deleteReshare = "DELETE FROM Reshares WHERE ReshareId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReshare);
			deleteStmt.setInt(1, reshare.getReshareId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
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

	/**
	 * Get the Reshares record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Reshares instance.
	 * Note that we use BlogPostsDao and BlogUsersDao to retrieve the referenced
	 * BlogPosts and BlogUsers instances.
	 * One alternative (possibly more efficient) is using a single SELECT statement
	 * to join the Reshares, BlogPosts, BlogUsers tables and then build each object.
	 */
	public Reshares getReshareById(int reshareId) throws SQLException {
		String selectReshare =
			"SELECT ReshareId,UserName,PostId " +
			"FROM Reshares " +
			"WHERE ReshareId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReshare);
			selectStmt.setInt(1, reshareId);
			results = selectStmt.executeQuery();
			BlogUsersDao blogUsersDao = BlogUsersDao.getInstance();
			BlogPostsDao blogPostsDao = BlogPostsDao.getInstance();
			if(results.next()) {
				int resultReshareId = results.getInt("ReshareId");
				String userName = results.getString("UserName");
				int postId = results.getInt("PostId");
				
				BlogUsers blogUser = blogUsersDao.getBlogUserFromUserName(userName);
				BlogPosts blogPost = blogPostsDao.getBlogPostById(postId);
				Reshares reshare = new Reshares(resultReshareId, blogUser, blogPost);
				return reshare;
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
	 * Get the all the Reshares for a user.
	 */
	public List<Reshares> getResharesForUser(BlogUsers blogUser) throws SQLException {
		List<Reshares> reshares = new ArrayList<Reshares>();
		String selectReshares =
			"SELECT ReshareId,UserName,PostId " +
			"FROM Reshares " + 
			"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReshares);
			selectStmt.setString(1, blogUser.getUserName());
			results = selectStmt.executeQuery();
			BlogPostsDao blogPostsDao = BlogPostsDao.getInstance();
			while(results.next()) {
				int reshareId = results.getInt("ReshareId");
				int postId = results.getInt("PostId");
				BlogPosts blogPost = blogPostsDao.getBlogPostById(postId);
				Reshares reshare = new Reshares(reshareId, blogUser, blogPost);
				reshares.add(reshare);
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
		return reshares;
	}
}
