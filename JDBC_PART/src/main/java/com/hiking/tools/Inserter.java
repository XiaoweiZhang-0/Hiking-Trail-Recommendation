


package com.hiking.tools;


import com.hiking.dal.*;

import com.hiking.model.*;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;



import java.util.List;



public class Inserter {
	public static void main(String[] args) throws SQLException, ParseException {
		
		// DAO instances.
		UsersDao usersDao = UsersDao.getInstance();
		HikingTrailsDao hikingTrailsDao = HikingTrailsDao.getInstance();
		ReviewsDao reviewsDao = ReviewsDao.getInstance();
		CommentsDao commentsDao = CommentsDao.getInstance();
		RecommendationsDao recommendationsDao = RecommendationsDao.getInstance();
		HikingHistoriesDao hikingHistoriesDao = HikingHistoriesDao.getInstance();
		LikeReviewsDao likeReviewsDao = LikeReviewsDao.getInstance();
		LikeCommentsDao likeCommentsDao = LikeCommentsDao.getInstance();
		FriendshipsDao friendshipsDao = FriendshipsDao.getInstance();
		
		// INSERT objects from our model.
		Users user1 = new Users("A", "a", "password", Users.Gender.Male,18, 45, 1.88, Users.HikingLevel.Beginner, "address", "email", "123" );
		user1 = usersDao.create(user1);
		Users user2 = new Users("B", "b", "password", Users.Gender.Male,18, 45, 1.88, Users.HikingLevel.Beginner, "address", "email", "123" );
		user2 = usersDao.create(user2);
		Users user3 = new Users("C", "c", "password", Users.Gender.Female,18, 45, 1.88, Users.HikingLevel.Beginner, "address3", "email3", "1233" );
		user3 = usersDao.create(user3);
		HikingTrails hikingTrails1 = new HikingTrails("trailName", "county", 300.0, "trailSystem", "surface", true, true);
		hikingTrails1 = hikingTrailsDao.create(hikingTrails1);
		HikingTrails hikingTrails2 = new HikingTrails("trailName2", "county2", 3200.0, "trailSystem2", "surface2", true, true);
		hikingTrails2 = hikingTrailsDao.create(hikingTrails2);
		Date date = new Date();
		Reviews review = new Reviews(user1, hikingTrails1, date, "review", 4.5);
		review = reviewsDao.create(review);
		Reviews review2 = new Reviews(user1, hikingTrails2, date, "review2", 4.1);
		review2 = reviewsDao.create(review2);
		Comments comment1 = new Comments(user1, review, "comment");
		comment1 = commentsDao.create(comment1);
		Comments comment2 = new Comments(user1, review2, "comment2");
		comment2 = commentsDao.create(comment2);
		Recommendations recommendation1 = new Recommendations(user1, hikingTrails1);
		recommendation1 = recommendationsDao.create(recommendation1);
		java.sql.Date date2 = new java.sql.Date(0);
		HikingHistories hikingHistory = new HikingHistories(user1, hikingTrails1, date2);
		hikingHistory = hikingHistoriesDao.create(hikingHistory);
		LikeReviews likeReviews = new LikeReviews(user1, review);
		likeReviews = likeReviewsDao.create(likeReviews);
		LikeComments likeComment = new LikeComments(user1, comment1);
		likeComment = likeCommentsDao.create(likeComment);
		
		Friendships friendship = new Friendships(user1, user2);
		friendship = friendshipsDao.create(friendship);
		
		//READ
		Users u1 = usersDao.getUserById(1);
		System.out.format("Reading User: userId:%s firstName:%s lastName:%s password:%s gender:%s age:%s "
				+ "weight:%s height:%s hikingLevel:%s address:%s phoneNumber:%s email:%s \n", 
				u1.getUserId(), u1.getFirstName(), u1.getLastName(), u1.getPassword(), u1.getGender(), u1.getAge(), 
				u1.getWeight(), u1.getHeight(), u1.getHikingLevel(), u1.getAddress(), u1.getPhoneNumber(), u1.getEmail());
		
		HikingTrails ht = hikingTrailsDao.getHikingTrailById(1);
		System.out.format("Reading User: trailId:%s trailName:%s county:%s length:%s trailSystem:%s "
				+ "surface:%s canBicycle:%s canCarDrive:%s \n",
				ht.getTrailId(), ht.getTrailName(), ht.getCounty(), ht.getLength(), ht.getTrailSystem(), 
				ht.getSurface(), ht.isCanBicycle(), ht.isCanCarDrive());
		
		Reviews rev = reviewsDao.getReviewById(1);
		System.out.format("Reading User: reviewId:%s userId:%s trailId:%s createTime:%s review:%s rating:%s \n",
				rev.getReviewId(), rev.getUser().getUserId(), rev.getHikingTrail().getTrailId(), rev.getCreatedTime(), 
				rev.getReview(), rev.getRating());
		
		Comments com = commentsDao.getCommentById(1);
		System.out.format("Reading User: commentId:%s userId:%s reviewId:%s comment:%s \n", 
				com.getCommentId(), com.getUser().getUserId(), com.getReview().getReviewId(), com.getComment());
		Recommendations recom = recommendationsDao.getRecommendationById(1);
		System.out.format("Reading User: recommendationId:%s userId:%s trailId:%s \n", 
				recom.getRecommendationId(), recom.getUser().getUserId(), recom.getHikingTrail().getTrailId());
		
		HikingHistories hkhis = hikingHistoriesDao.getHikingHistoryById(1);
		System.out.format("Reading User: hikingHistoryId:%s userId:%s trailId:%s travelTime:%s \n", 
				hkhis.getHikingHistoryId(), hkhis.getUser().getUserId(), hkhis.getHikingTrail().getTrailId(), hkhis.getTravelTime());
		
		LikeReviews lr = likeReviewsDao.getLikeReviewsById(1);
		System.out.format("Reading User: likeReviewId:%s userId:%s reviewId:%s \n", 
				lr.getLikeReviewId(), lr.getUser().getUserId(), lr.getReview().getReviewId());
		
		LikeComments lc = likeCommentsDao.getLikeCommentById(1);
		System.out.format("Reading User: likeCommentId:%s userId:%s commentId:%s \n", 
				lc.getLikeCommentId(), lc.getUser().getUserId(), lc.getComment().getCommentId());
		
		List<Friendships> fList1 = friendshipsDao.getFriendshipsByUserId(1);
		for(Friendships f : fList1) {
			System.out.format("Looping Friendships: userId1:%s userId2:%s \n", 
					f.getUser1().getUserId(), f.getUser2().getUserId());
		}
		
		List<Comments> commlist = commentsDao.getCommentsByUserId(1);
		List<Recommendations> recomlist = recommendationsDao.getRecommendationsByUserId(1);
		List<Reviews> revlist = reviewsDao.getReviewsByUserId(1);
		List<LikeComments> lclist = likeCommentsDao.getLikeCommentsByUserId(1);
		List<LikeReviews> lrlist = likeReviewsDao.getLikeReviewsByUserId(1);
		
		
		//UPDATE
		user1 = usersDao.updateLastName(user1, "zhao");
		hikingTrails1 = hikingTrailsDao.updateTrailtName(hikingTrails1, "Rattlesnake Trail");
		review = reviewsDao.updateReview(review, "Good trail here. ");
		comment1 = commentsDao.updateReviewId(comment1, review2.getReviewId());
		recommendation1 = recommendationsDao.updateTrailId(recommendation1, hikingTrails2.getTrailId());
		hikingHistory = hikingHistoriesDao.updateHikingHistory(hikingHistory, hikingTrails2.getTrailId(), date2);
		likeReviews = likeReviewsDao.updateReviewId(likeReviews, review2.getReviewId());
		likeComment = likeCommentsDao.updateCommentId(likeComment, comment2.getCommentId());
		friendship = friendshipsDao.updateUserId2(friendship, user3.getUserId());
		
		//DELETE
		user1 = usersDao.delete(user1);
		hikingTrails1 = hikingTrailsDao.delete(hikingTrails1);
		review = reviewsDao.delete(review);
		comment1 = commentsDao.delete(comment1);
		recommendation1 = recommendationsDao.delete(recommendation1);
		hikingHistory = hikingHistoriesDao.delete(hikingHistory);
		likeReviews = likeReviewsDao.delete(likeReviews);
		likeComment = likeCommentsDao.delete(likeComment);
		friendship = friendshipsDao.delete(friendship);
	
	}

}
