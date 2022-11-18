package blog.dal;

import blog.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BlogCommentsDao {
	protected ConnectionManager connectionManager;

	private static BlogCommentsDao instance = null;
	protected BlogCommentsDao() {
		connectionManager = new ConnectionManager();
	}
	public static BlogCommentsDao getInstance() {
		if(instance == null) {
			instance = new BlogCommentsDao();
		}
		return instance;
	}

	public BlogComments create(BlogComments blogComment) throws SQLException {
		String insertBlogComment =
			"INSERT INTO BlogComments(Content,Created,UserName,PostId) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertBlogComment,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, blogComment.getContent());
			insertStmt.setTimestamp(2, new Timestamp(blogComment.getCreated().getTime()));
			insertStmt.setString(3, blogComment.getBlogUser().getUserName());
			insertStmt.setInt(4, blogComment.getBlogPost().getPostId());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int commentId = -1;
			if(resultKey.next()) {
				commentId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			blogComment.setCommentId(commentId);
			return blogComment;
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
	 * Update the content of the BlogComments instance.
	 * This runs a UPDATE statement.
	 */
	public BlogComments updateContent(BlogComments blogComment, String newContent) throws SQLException {
		String updateBlogComment = "UPDATE BlogComments SET Content=?,Created=? WHERE CommentId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateBlogComment);
			updateStmt.setString(1, newContent);
			Date newCreatedTimestamp = new Date();
			updateStmt.setTimestamp(2, new Timestamp(newCreatedTimestamp.getTime()));
			updateStmt.setInt(3, blogComment.getCommentId());
			updateStmt.executeUpdate();

			// Update the blogComment param before returning to the caller.
			blogComment.setContent(newContent);
			blogComment.setCreated(newCreatedTimestamp);
			return blogComment;
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

	/**
	 * Delete the BlogComments instance.
	 * This runs a DELETE statement.
	 */
	public BlogComments delete(BlogComments blogComment) throws SQLException {
		String deleteBlogComment = "DELETE FROM BlogComments WHERE CommentId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBlogComment);
			deleteStmt.setInt(1, blogComment.getCommentId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the BlogComments instance.
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
	 * Get the BlogComments record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single BlogComments instance.
	 * Note that we use BlogPostsDao and BlogUsersDao to retrieve the referenced
	 * BlogPosts and BlogUsers instances.
	 * One alternative (possibly more efficient) is using a single SELECT statement
	 * to join the BlogComments, BlogPosts, BlogUsers tables and then build each object.
	 */
	public BlogComments getBlogCommentById(int commentId) throws SQLException {
		String selectBlogComment =
			"SELECT CommentId,Content,Created,UserName,PostId " +
			"FROM BlogComments " +
			"WHERE CommentId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBlogComment);
			selectStmt.setInt(1, commentId);
			results = selectStmt.executeQuery();
			BlogPostsDao blogPostsDao = BlogPostsDao.getInstance();
			BlogUsersDao blogUsersDao = BlogUsersDao.getInstance();
			if(results.next()) {
				int resultCommentId = results.getInt("CommentId");
				String content = results.getString("Content");
				Date created =  new Date(results.getTimestamp("Created").getTime());
				int postId = results.getInt("PostId");
				String userName = results.getString("UserName");
				
				BlogPosts blogPost = blogPostsDao.getBlogPostById(postId);
				BlogUsers blogUser = blogUsersDao.getBlogUserFromUserName(userName);
				BlogComments blogComment = new BlogComments(resultCommentId, content,
					created, blogPost, blogUser);
				return blogComment;
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
	 * Get the all the BlogComments for a user.
	 */
	public List<BlogComments> getBlogCommentsForUser(BlogUsers blogUser) throws SQLException {
		List<BlogComments> blogComments = new ArrayList<BlogComments>();
		String selectBlogComments =
			"SELECT CommentId,Content,Created,UserName,PostId " +
			"FROM BlogComments " +
			"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBlogComments);
			selectStmt.setString(1, blogUser.getUserName());
			results = selectStmt.executeQuery();
			BlogPostsDao blogPostsDao = BlogPostsDao.getInstance();
			while(results.next()) {
				int commentId = results.getInt("CommentId");
				String content = results.getString("Content");
				Date created =  new Date(results.getTimestamp("Created").getTime());
				int postId = results.getInt("PostId");

				BlogPosts blogPost = blogPostsDao.getBlogPostById(postId);
				BlogComments blogComment = new BlogComments(commentId, content, created,
					blogPost, blogUser);
				blogComments.add(blogComment);
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
		return blogComments;
	}
	
	/**
	 * Get the all the BlogComments for a post.
	 */
	public List<BlogComments> getBlogCommentsForPost(BlogPosts blogPost) throws SQLException {
		List<BlogComments> blogComments = new ArrayList<BlogComments>();
		String selectBlogPosts =
			"SELECT CommentId,Content,Created,UserName,PostId " +
			"FROM BlogComments " +
			"WHERE PostId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBlogPosts);
			selectStmt.setInt(1, blogPost.getPostId());
			results = selectStmt.executeQuery();
			BlogUsersDao blogUsersDao = BlogUsersDao.getInstance();
			while(results.next()) {
				int commentId = results.getInt("CommentId");
				String content = results.getString("Content");
				Date created =  new Date(results.getTimestamp("Created").getTime());
				String userName = results.getString("UserName");

				BlogUsers blogUser = blogUsersDao.getBlogUserFromUserName(userName);
				BlogComments blogComment = new BlogComments(commentId, content, created,
					blogPost, blogUser);
				blogComments.add(blogComment);
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
		return blogComments;
	}
}
