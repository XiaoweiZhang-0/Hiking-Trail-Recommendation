package com.hiking.servlet;

import com.hiking.dal.*;
import com.hiking.model.*;

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


/**
 * FindUsers is the primary entry point into the application.
 * <p>
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /findusers
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 * <p>
 * To run:
 * 1. Run the SQL script to recreate your database schema: http://goo.gl/86a11H.
 * 2. Insert test data. You can do this by running blog.tools.Inserter (right click,
 * Run As > JavaApplication.
 * Notice that this is similar to Runner.java in our JDBC example.
 * 3. Run the Tomcat server at localhost.
 * 4. Point your browser to http://localhost:8080/BlogApplication/findusers.
 */
@WebServlet("/findusers")
public class FindUsers extends HttpServlet {

    protected UsersDao usersDao;

    @Override
    public void init() throws ServletException {
        usersDao = UsersDao.getInstance();
    }

//  @Override
//  public void doGet(HttpServletRequest req, HttpServletResponse resp)
//      throws ServletException, IOException {
//    // Map for storing messages.
//    Map<String, String> messages = new HashMap<String, String>();
//    req.setAttribute("messages", messages);
//
//    List<BlogUsers> blogUsers = new ArrayList<BlogUsers>();
//
//    // Retrieve and validate name.
//    // firstname is retrieved from the URL query string.
//    String firstName = req.getParameter("firstname");
//    if (firstName == null || firstName.trim().isEmpty()) {
//      messages.put("success", "Please enter a valid name.");
//    } else {
//      // Retrieve BlogUsers, and store as a message.
//      try {
//        blogUsers = blogUsersDao.getBlogUsersFromFirstName(firstName);
//      } catch (SQLException e) {
//        e.printStackTrace();
//        throw new IOException(e);
//      }
//      messages.put("success", "Displaying results for " + firstName);
//      // Save the previous search term, so it can be used as the default
//      // in the input box when rendering FindUsers.jsp.
//      messages.put("previousFirstName", firstName);
//    }
//    req.setAttribute("blogUsers", blogUsers);
//
//    req.getRequestDispatcher("/FindUsers.jsp").forward(req, resp);
//  }

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        List<Users> users = new ArrayList<Users>();
        // Map for storing messages.
        String userId = httpServletRequest.getParameter("userId");
        int userIdInt = 0;
        if (userId != null) {
            userIdInt = Integer.parseInt(userId);
        }
        Users user = null;

        try {
            user = usersDao.getUserById(userIdInt);
            users.add(user);
        } catch (SQLException throwables) {
            // todo
        }

        httpServletRequest.setAttribute("users", users);
        httpServletRequest.getRequestDispatcher("/FindUsers.jsp").forward(httpServletRequest, httpServletResponse);
    }
}

