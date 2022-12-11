package com.hiking.servlet;

import com.hiking.dal.LikeReviewsDao;
import com.hiking.dal.ReviewsDao;
import com.hiking.model.LikeReviews;

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

@WebServlet("/likereview")
public class FindLikeReviews extends HttpServlet {

	protected LikeReviewsDao likeReviewsDao;
	protected ReviewsDao reviewsDao;
	
	@Override
	public void init() throws ServletException {
		likeReviewsDao = LikeReviewsDao.getInstance();
		reviewsDao = ReviewsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("message", messages);
		
		List<LikeReviews> likeReviews = new ArrayList<LikeReviews>();
        String reviewId = req.getParameter("reviewId");
        if (reviewId == null || reviewId.trim().isEmpty()) {
            messages.put("title", "Invalid likeReviewId.");
        } else {
        	
        	try {
        		int reviewIdInt = Integer.parseInt(reviewId);
        		likeReviews = likeReviewsDao.getLikeReviewsByReviewId(reviewIdInt);
        	} catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("title", "review " + reviewId);
        }
        
        req.setAttribute("likeReview", likeReviews);
        req.getRequestDispatcher("/FindLikeReviews.jsp").forward(req, resp);
		
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		
		List<LikeReviews> likeReviews = new ArrayList<LikeReviews>();
		String reviewId = req.getParameter("reviewId");
        if (reviewId == null || reviewId.trim().isEmpty()) {
            messages.put("title", "Invalid likeReviewId.");
        } else {
      
        	try {
        		int reviewIdInt = Integer.parseInt(reviewId);
        		likeReviews = likeReviewsDao.getLikeReviewsByReviewId(reviewIdInt);
        	} catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("title", "review " + likeReviews);
        	
        }
        
        req.setAttribute("likeReviews", likeReviews);
        req.getRequestDispatcher("/FindLikeReviews.jsp").forward(req, resp);
		
	}
    
    
}
