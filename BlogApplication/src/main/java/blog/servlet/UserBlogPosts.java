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


@WebServlet("/userblogposts")
public class UserBlogPosts extends HttpServlet {
	
	protected BlogPostsDao blogPostsDao;
	
	@Override
	public void init() throws ServletException {
		blogPostsDao = BlogPostsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		// Retrieve and validate UserName.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("title", "Invalid username.");
        } else {
        	messages.put("title", "BlogPosts for " + userName);
        }
        
        // Retrieve BlogUsers, and store in the request.
        List<BlogPosts> blogPosts = new ArrayList<BlogPosts>();
        try {
        	BlogUsers blogUser = new BlogUsers(userName);
        	blogPosts = blogPostsDao.getBlogPostsForUser(blogUser);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("blogPosts", blogPosts);
        req.getRequestDispatcher("/UserBlogPosts.jsp").forward(req, resp);
	}
}
