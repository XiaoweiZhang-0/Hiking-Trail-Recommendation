package com.hiking.model;

import java.util.Date;

public class Reviews {
	protected int reviewId;
	protected Users user;
	protected HikingTrails hikingTrail;
	protected Date createdTime;
	protected String review;
	protected double rating;
	public Reviews(int reviewId, Users user, HikingTrails hikingTrail, Date createdTime, String review, double rating) {
		super();
		this.reviewId = reviewId;
		this.user = user;
		this.hikingTrail = hikingTrail;
		this.createdTime = createdTime;
		this.review = review;
		this.rating = rating;
	}
	public Reviews(Users user, HikingTrails hikingTrail, Date createdTime, String review, double rating) {
		super();
		this.user = user;
		this.hikingTrail = hikingTrail;
		this.createdTime = createdTime;
		this.review = review;
		this.rating = rating;
	}
	public Reviews(int reviewId) {
		super();
		this.reviewId = reviewId;
	}
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public HikingTrails getHikingTrail() {
		return hikingTrail;
	}
	public void setHikingTrail(HikingTrails hikingTrail) {
		this.hikingTrail = hikingTrail;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}

	
	
	

}
