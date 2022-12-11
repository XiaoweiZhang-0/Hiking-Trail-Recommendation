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


@WebServlet("/reviews")
public class GetReviews extends HttpServlet {
    
    protected ReviewsDao reviewsDao;
    protected LikeReviewsDao likeReviewsDao;
    
    @Override
    public void init() throws ServletException {
        reviewsDao = ReviewsDao.getInstance();
        likeReviewsDao = LikeReviewsDao.getInstance();
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
//            messages.put("title", "Invalid username.");
            userId = "1";
        } else {
            messages.put("title", "Reviews for " + userId);
        }
        
        // Retrieve BlogUsers, and store in the request.
        Map<Reviews, Integer> reviewsToLikes = new HashMap<>();
        List<Reviews> reviews = new ArrayList<>();
        int userIdNum = Integer.parseInt(userId);
        try {
            reviews = reviewsDao.getReviewsByUserId(userIdNum);
            for (Reviews review : reviews) {
                int reviewId = review.getReviewId();
                System.out.println(likeReviewsDao.getLikeCountsByReviewId(reviewId));
                reviewsToLikes.put(review, likeReviewsDao.getLikeCountsByReviewId(reviewId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        req.setAttribute("reviews", reviews);
        req.setAttribute("reviewsToLikes", reviewsToLikes);
        req.getRequestDispatcher("/Reviews.jsp").forward(req, resp);
    }
}