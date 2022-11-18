package blog.dal;

import blog.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying BlogUsers table in your
 * MySQL instance. This is used to store {@link BlogUsers} into your MySQL instance and 
 * retrieve {@link BlogUsers} from MySQL instance.
 */
public class BlogUsersDao extends PersonsDao {
	// Single pattern: instantiation is limited to one object.
	private static BlogUsersDao instance = null;
	protected BlogUsersDao() {
		super();
	}
	public static BlogUsersDao getInstance() {
		if(instance == null) {
			instance = new BlogUsersDao();
		}
		return instance;
	}

	public BlogUsers create(BlogUsers blogUsers) throws SQLException {
		// Insert into the superclass table first.
		create(new Persons(blogUsers.getUserName(), blogUsers.getFirstName(),
			blogUsers.getLastName()));

		String insertBlogUser = "INSERT INTO BlogUsers(UserName,DoB,StatusLevel) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertBlogUser);
			insertStmt.setString(1, blogUsers.getUserName());
			insertStmt.setTimestamp(2, new Timestamp(blogUsers.getDob().getTime()));
			insertStmt.setString(3, blogUsers.getStatusLevel().name());
			insertStmt.executeUpdate();
			return blogUsers;
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
		}
	}

	/**
	 * Update the LastName of the BlogUsers instance.
	 * This runs a UPDATE statement.
	 */
	public BlogUsers updateLastName(BlogUsers blogUser, String newLastName) throws SQLException {
		// The field to update only exists in the superclass table, so we can
		// just call the superclass method.
		super.updateLastName(blogUser, newLastName);
		return blogUser;
	}

	/**
	 * Delete the BlogUsers instance.
	 * This runs a DELETE statement.
	 */
	public BlogUsers delete(BlogUsers blogUser) throws SQLException {
		String deleteBlogUser = "DELETE FROM BlogUsers WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBlogUser);
			deleteStmt.setString(1, blogUser.getUserName());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for UserName=" + blogUser.getUserName());
			}

			// Then also delete from the superclass.
			// Notes:
			// 1. Due to the fk constraint (ON DELETE CASCADE), we could simply call
			//    super.delete() without even needing to delete from Administrators first.
			// 2. BlogPosts has a fk constraint on BlogUsers with the reference option
			//    ON DELETE SET NULL. If the BlogPosts fk reference option was instead
			//    ON DELETE RESTRICT, then the caller would need to delete the referencing
			//    BlogPosts before this BlogUser can be deleted.
			//    Example to delete the referencing BlogPosts:
			//    List<BlogPosts> posts = BlogPostsDao.getBlogPostsForUser(blogUser.getUserName());
			//    for(BlogPosts p : posts) BlogPostsDao.delete(p);
			super.delete(blogUser);

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

	public BlogUsers getBlogUserFromUserName(String userName) throws SQLException {
		// To build an BlogUser object, we need the Persons record, too.
		String selectBlogUser =
			"SELECT BlogUsers.UserName AS UserName, FirstName, LastName, DoB, StatusLevel " +
			"FROM BlogUsers INNER JOIN Persons " +
			"  ON BlogUsers.UserName = Persons.UserName " +
			"WHERE BlogUsers.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBlogUser);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				Date dob = new Date(results.getTimestamp("DoB").getTime());
				BlogUsers.StatusLevel statusLevel = BlogUsers.StatusLevel.valueOf(
						results.getString("StatusLevel"));
				BlogUsers blogUser = new BlogUsers(resultUserName, firstName, lastName, dob, statusLevel);
				return blogUser;
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

	public List<BlogUsers> getBlogUsersFromFirstName(String firstName)
			throws SQLException {
		List<BlogUsers> blogUsers = new ArrayList<BlogUsers>();
		String selectBlogUsers =
			"SELECT BlogUsers.UserName AS UserName, FirstName, LastName, DoB, StatusLevel " +
			"FROM BlogUsers INNER JOIN Persons " +
			"  ON BlogUsers.UserName = Persons.UserName " +
			"WHERE Persons.FirstName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBlogUsers);
			selectStmt.setString(1, firstName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String userName = results.getString("UserName");
				String resultFirstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				Date dob = new Date(results.getTimestamp("DoB").getTime());
				BlogUsers.StatusLevel statusLevel = BlogUsers.StatusLevel.valueOf(
						results.getString("StatusLevel"));
				BlogUsers blogUser = new BlogUsers(userName, resultFirstName, lastName, dob, statusLevel);
				blogUsers.add(blogUser);
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
		return blogUsers;
	}
}
