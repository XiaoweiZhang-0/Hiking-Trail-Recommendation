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
public class RecommendationCreate extends HttpServlet {

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
    //Just render the JSP.
    req.getRequestDispatcher("/RecommendationCreate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
      throws ServletException, IOException {
    // Map for storing messages.
    String userId = httpServletRequest.getParameter("userId");
    String trailId = httpServletRequest.getParameter("trailId");

    int userIdInt = 0;
    if (userId != null) {
    	userIdInt = Integer.parseInt(userId);
    }
    int trailIdInt = 0;
    if (trailId != null) {
    	trailIdInt = Integer.parseInt(trailId);
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
    
    
    Recommendations recommendation = new Recommendations(user, hikingTrail);
    try {
      recommendation = recommendationsDao.create(recommendation);
      httpServletRequest.setAttribute("recommendation", recommendation);

//      httpServletRequest.getRequestDispatcher("/index.jsp").forward(httpServletRequest, httpServletResponse);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    httpServletRequest.getRequestDispatcher("/RecommendationCreate.jsp").forward(httpServletRequest, httpServletResponse);

  }
}

