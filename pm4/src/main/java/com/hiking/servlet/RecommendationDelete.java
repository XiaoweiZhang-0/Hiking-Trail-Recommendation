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


@WebServlet("/recommendationdelete")
public class RecommendationDelete extends HttpServlet {

  protected RecommendationsDao recommendationsDao;

  @Override
  public void init() throws ServletException {
    recommendationsDao = RecommendationsDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    // Provide a title and render the JSP.
    messages.put("title", "Delete Recommendation");
    req.getRequestDispatcher("/RecommendationDelete.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    String recommendationId =req.getParameter("recommendationId");
    int recommendationIdInt = 0;
    if (recommendationId != null) {
      recommendationIdInt = Integer.parseInt(recommendationId);
    }
    Recommendations recommendation = new Recommendations(recommendationIdInt);
    try {
      recommendationsDao.delete(recommendation);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    req.getRequestDispatcher("/RecommendationDelete.jsp").forward(req, resp);
  }
}
