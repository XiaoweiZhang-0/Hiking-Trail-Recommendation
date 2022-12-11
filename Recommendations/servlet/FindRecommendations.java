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


@WebServlet("/findRecommendations")
public class FindRecommendations extends HttpServlet {

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

    List<Recommendations> recommendations = new ArrayList<Recommendations>();
    
    String recommendationId = req.getParameter("recommendationId");
    int recommendationIdInt = 0;
    if (recommendationId != null) {
    	recommendationIdInt = Integer.parseInt(recommendationId);
    }
    Recommendations recommendation = null;
    if (recommendationId == null || recommendationId.trim().isEmpty()) {
    	messages.put("success", "Please enter a valid trailId.");
    } else {
    	try {
    		recommendation = recommendationsDao.getRecommendationById(recommendationIdInt);
    		recommendations.add(recommendation);
    	} catch (SQLException e) {
    		e.printStackTrace();
    		throw new IOException(e);
    	}
    	messages.put("success", "Displaying results for " + recommendationId);
    	messages.put("previousRecommendationId", recommendationId);
    }
    req.setAttribute("recommendations", recommendations);
    
    req.getRequestDispatcher("/FindRecommendations.jsp").forward(req, resp);
	}  

	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
	    Map<String, String> messages = new HashMap<String, String>();
	    req.setAttribute("messages", messages);

	    List<Recommendations> recommendations = new ArrayList<Recommendations>();
	    
	    String recommendationId = req.getParameter("recommendationId");
	    int recommendationIdInt = 0;
	    if (recommendationId != null) {
	    	recommendationIdInt = Integer.parseInt(recommendationId);
	    }
	    Recommendations recommendation = null;
	    if (recommendationId == null || recommendationId.trim().isEmpty()) {
	    	messages.put("success", "Please enter a valid recommendationId.");
	    } else {
	    	try {
	    		recommendation = recommendationsDao.getRecommendationById(recommendationIdInt);
	    		recommendations.add(recommendation);
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    		throw new IOException(e);
	    	}
	    	messages.put("success", "Displaying results for " + recommendationId);
	    	messages.put("previousRecommendationId", recommendationId);
	    }
	    req.setAttribute("recommendations", recommendations);
	    
	    req.getRequestDispatcher("/FindRecommendations.jsp").forward(req, resp);
		}  
}

