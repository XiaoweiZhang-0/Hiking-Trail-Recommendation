package blog.model;

public class LikeReviews {
	
	protected int likeReviewId;
	protected Users user;
	protected Reviews review;
	
	public LikeReviews(int likeReviewId) {
		super();
		this.likeReviewId = likeReviewId;
	}

	public LikeReviews(Users user, Reviews review) {
		super();
		this.user = user;
		this.review = review;
	}

	public LikeReviews(int likeReviewId, Users user, Reviews review) {
		super();
		this.likeReviewId = likeReviewId;
		this.user = user;
		this.review = review;
	}

	public int getLikeReviewId() {
		return likeReviewId;
	}

	public void setLikeReviewId(int likeReviewId) {
		this.likeReviewId = likeReviewId;
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
	
	

}
