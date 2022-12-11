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


@WebServlet("/reviewdelete")
public class ReviewDelete extends HttpServlet {

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
    // Provide a title and render the JSP.
    messages.put("title", "Delete Reviews");
    req.getRequestDispatcher("/ReviewDelete.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    String reviewId =req.getParameter("reviewId");
    int reviewIdInt = 0;
    if (reviewId != null) {
        reviewIdInt = Integer.parseInt(reviewId);
    }
    Reviews review = new Reviews(reviewIdInt);
    try {
      reviewsDao.delete(review);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    req.getRequestDispatcher("/FindUsers.jsp").forward(req, resp);
  }
}
