package blog.servlet;

import blog.dal.*;
import blog.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/blogcomments")
public class UserPostBlogComments extends HttpServlet {
	
	protected BlogCommentsDao blogCommentsDao;
	
	@Override
	public void init() throws ServletException {
		blogCommentsDao = BlogCommentsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        List<BlogComments> blogComments = new ArrayList<BlogComments>();
        
		// Retrieve BlogComments depending on valid PostId or UserName.
        String postId = req.getParameter("postid");
        String userName = req.getParameter("username");
        
        try {
	        if (postId != null && !postId.trim().isEmpty()) {
	        	// If the postid param is provided then ignore the username param.
	        	BlogPosts blogPost = new BlogPosts(Integer.parseInt(postId));
	        	blogComments = blogCommentsDao.getBlogCommentsForPost(blogPost);
	        	messages.put("title", "BlogComments for PostId " + postId);
	        } else if (userName != null && !userName.trim().isEmpty()) {
	        	// If postid is invalid, then use the username param.
	        	BlogUsers blogUser = new BlogUsers(userName);
	        	blogComments = blogCommentsDao.getBlogCommentsForUser(blogUser);
	        	messages.put("title", "BlogComments for UserName " + userName);
	        } else {
	        	messages.put("title", "Invalid PostId and UserName.");
	        }
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        req.setAttribute("blogComments", blogComments);
        req.getRequestDispatcher("/UserPostBlogComments.jsp").forward(req, resp);
	}
}
