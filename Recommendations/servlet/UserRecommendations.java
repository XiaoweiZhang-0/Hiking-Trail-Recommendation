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

@WebServlet("/userrecommendations")
public class UserRecommendations extends HttpServlet {
	
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
        String userId = req.getParameter("userId");
        if (userId == null || userId.trim().isEmpty()) {
            messages.put("title", "Invalid userId.");
        } else {
        	messages.put("title", "Recommendations for " + userId);
        }
	    int userIdInt = 0;
	    if (userId != null) {
	    	userIdInt = Integer.parseInt(userId);
	    }
        // Retrieve BlogUsers, and store in the request.
        List<Recommendations> recommendations = new ArrayList<Recommendations>();

        try {
        	recommendations = recommendationsDao.getRecommendationsByUserId(userIdInt);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("recommendations", recommendations);
        req.getRequestDispatcher("/UserRecommendations.jsp").forward(req, resp);
	}
}