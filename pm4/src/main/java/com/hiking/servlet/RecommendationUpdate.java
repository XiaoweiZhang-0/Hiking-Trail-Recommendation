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


@WebServlet("/recommendationupdate")
public class RecommendationUpdate extends HttpServlet {

  protected RecommendationsDao recommendationsDao;

  @Override
  public void init() throws ServletException {
    recommendationsDao = RecommendationsDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
//    Map<String, String> messages = new HashMap<String, String>();
//    req.setAttribute("messages", messages);
//
//    // Retrieve user and validate.
    String recommendationId = req.getParameter("recommendationId");
//    if (userName == null || userName.trim().isEmpty()) {
//      messages.put("success", "Please enter a valid UserName.");
//    } else {
//      try {
//        Users user = UsersDao.getBlogUserFromUserName(userName);
//        if(blogUser == null) {
//          messages.put("success", "UserName does not exist.");
//        }
//        req.setAttribute("blogUser", blogUser);
//      } catch (SQLException e) {
//        e.printStackTrace();
//        throw new IOException(e);
//      }
//    }

    req.getRequestDispatcher("/RecommendationUpdate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    String recommendationId = req.getParameter("recommendationId");
    String trailId = req.getParameter("trailId");
    int recommendationIdInt = 0;
    if (recommendationId != null) {
      recommendationIdInt = Integer.parseInt(recommendationId);
    }
    int trailIdInt = 0;
    if (trailId != null) {
      trailIdInt = Integer.parseInt(trailId);
    }

    Recommendations recommendation = null;
	try {
		recommendation = recommendationsDao.getRecommendationById(recommendationIdInt);
		HikingTrailsDao hikingTrailsDao = HikingTrailsDao.getInstance();
	    HikingTrails hikingTrail = hikingTrailsDao.getHikingTrailById(trailIdInt);
	    recommendation.setHikingTrail(hikingTrail);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    try {
      recommendationsDao.updateTrailId(recommendation, trailIdInt);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    req.getRequestDispatcher("/RecommendationUpdate.jsp").forward(req, resp);
  }
}

