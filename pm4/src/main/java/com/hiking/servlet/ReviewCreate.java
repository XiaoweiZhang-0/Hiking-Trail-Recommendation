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


@WebServlet("/reviewcreate")
public class ReviewCreate extends HttpServlet {

  protected ReviewsDao reviewsDao;

  @Override
  public void init() throws ServletException {
      reviewsDao = ReviewsDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    //Just render the JSP.
    req.getRequestDispatcher("/ReviewCreate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
      throws ServletException, IOException {
    // Map for storing messages.
    String userId = httpServletRequest.getParameter("userId");
    String trailId = httpServletRequest.getParameter("trailId");
    Date date = new Date();
    String reviewContent = httpServletRequest.getParameter("review");
    String rating = httpServletRequest.getParameter("rating");
    
    int userIdInt = 0;
    if (userId != null) {
        userIdInt = Integer.parseInt(userId);
    }
    int trailIdInt = 0;
    if (trailId != null) {
        trailIdInt = Integer.parseInt(trailId);
    }
    double ratingDouble = 0;
    if (rating != null) {
        ratingDouble = Double.parseDouble(rating);
    }
    
    UsersDao usersDao = UsersDao.getInstance();
    HikingTrailsDao hikingTrailsDao = HikingTrailsDao.getInstance();
    
    Users user = null;
    HikingTrails hikingTrail = null;
    try {
        user = usersDao.getUserById(userIdInt);
        hikingTrail = hikingTrailsDao.getHikingTrailById(trailIdInt);
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    Reviews review = new Reviews(user, hikingTrail, date, reviewContent, ratingDouble);
    try {
      review = reviewsDao.create(review);
      httpServletRequest.setAttribute("review", review);

//      httpServletRequest.getRequestDispatcher("/index.jsp").forward(httpServletRequest, httpServletResponse);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    httpServletRequest.getRequestDispatcher("/FindUsers.jsp").forward(httpServletRequest, httpServletResponse);

  }
}

