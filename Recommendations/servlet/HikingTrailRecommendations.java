package com.hiking.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiking.dal.*;
import com.hiking.model.*;

@WebServlet("/hikingTrailrecommendations")
public class HikingTrailRecommendations extends HttpServlet {
	
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
		
		// Retrieve and validate UserName.
        String trailId = req.getParameter("trailId");
        if (trailId == null || trailId.trim().isEmpty()) {
            messages.put("title", "Invalid trailId.");
        } else {
        	messages.put("title", "Recommendations for " + trailId);
        }
	    int trailIdInt = 0;
	    if (trailId != null) {
	    	trailIdInt = Integer.parseInt(trailId);
	    }
        // Retrieve BlogUsers, and store in the request.
        List<Recommendations> recommendations = new ArrayList<Recommendations>();

        try {
        	recommendations = recommendationsDao.getRecommendationsByTrailId(trailIdInt);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("recommendations", recommendations);
        req.getRequestDispatcher("/HikingTrailRecommendations.jsp").forward(req, resp);
	}
}