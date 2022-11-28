package com.hiking.model;

public class Comments {
	protected int commentId;
	protected Users user;
	protected Reviews review;
	protected String comment;
	public Comments(int commentId, Users user, Reviews review, String comment) {
		super();
		this.commentId = commentId;
		this.user = user;
		this.review = review;
		this.comment = comment;
	}
	public Comments(int commentId) {
		super();
		this.commentId = commentId;
	}
	public Comments(Users user, Reviews review, String comment) {
		super();
		this.user = user;
		this.review = review;
		this.comment = comment;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Reviews getReview() {
		return review;
	}
	public void setReview(Reviews review) {
		this.review = review;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	

}
