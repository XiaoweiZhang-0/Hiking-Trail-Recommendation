package com.hiking.servlet;

import com.hiking.dal.*;
import com.hiking.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/userupdate")
public class UserUpdate extends HttpServlet {

  protected UsersDao usersDao;

  @Override
  public void init() throws ServletException {
    usersDao = UsersDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
//    Map<String, String> messages = new HashMap<String, String>();
//    req.setAttribute("messages", messages);
//
//    // Retrieve user and validate.
    String userId = req.getParameter("userId");

    req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    String userId = req.getParameter("userId");
    String lastName = req.getParameter("lastName");
    int userIdInt = 0;
    if (userId != null) {
      userIdInt = Integer.parseInt(userId);
    }

    Users user = new Users(userIdInt);
    user.setLastName(lastName);
    try {
      usersDao.updateLastName(user, lastName);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    req.getRequestDispatcher("/FindUsers.jsp").forward(req, resp);
  }
}

