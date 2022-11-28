package com.hiking.servlet;

import com.hiking.dal.*;
import com.hiking.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/usercreate")
public class UserCreate extends HttpServlet {

  protected UsersDao usersDao;

  @Override
  public void init() throws ServletException {
    usersDao = UsersDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    //Just render the JSP.
    req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
      throws ServletException, IOException {
    // Map for storing messages.
    String firstName = httpServletRequest.getParameter("firstName");
    String lastName = httpServletRequest.getParameter("lastName");
    String password = httpServletRequest.getParameter("password");
    String gender = httpServletRequest.getParameter("gender");
    String age = httpServletRequest.getParameter("age");
    String weight = httpServletRequest.getParameter("weight");
    String height = httpServletRequest.getParameter("height");
    String hikingLevel = httpServletRequest.getParameter("hikingLevel");
    String address = httpServletRequest.getParameter("address");
    String phoneNumber = httpServletRequest.getParameter("phoneNumber");
    String email = httpServletRequest.getParameter("email");

    Users.Gender genderObj = null;
    if (gender != null) {
      genderObj = Users.Gender.valueOf(gender);
    }
    int ageInt = 0;
    if (age != null) {
      ageInt = Integer.parseInt(age);
    }
    int weightInt = 0;
    if (weight != null) {
      weightInt = Integer.parseInt(weight);
    }
    int heightInt = 0;
    if (height != null) {
      heightInt = Integer.parseInt(height);
    }
    Users.HikingLevel hikingLevelObj = null;
    if (hikingLevel != null) {
      hikingLevelObj = Users.HikingLevel.valueOf(hikingLevel);
    }

    Users user = new Users(firstName, lastName, password, genderObj, ageInt, weightInt, heightInt, hikingLevelObj, address, phoneNumber, email);
    try {
      user = usersDao.create(user);
      httpServletRequest.setAttribute("user", user);

//      httpServletRequest.getRequestDispatcher("/index.jsp").forward(httpServletRequest, httpServletResponse);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    httpServletRequest.getRequestDispatcher("/UserCreate.jsp").forward(httpServletRequest, httpServletResponse);

  }
}

