package blog.model;

public class LikeComments {

	protected int likeCommentId;
	protected Users user;
	protected Comments comment;
	
	
	public LikeComments(int likeCommentId) {
		super();
		this.likeCommentId = likeCommentId;
	}


	public LikeComments(Users user, Comments comment) {
		super();
		this.user = user;
		this.comment = comment;
	}


	public LikeComments(int likeCommentId, Users user, Comments comment) {
		super();
		this.likeCommentId = likeCommentId;
		this.user = user;
		this.comment = comment;
	}


	public int getLikeCommentId() {
		return likeCommentId;
	}


	public void setLikeCommentId(int likeCommentId) {
		this.likeCommentId = likeCommentId;
	}


	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}


	public Comments getComment() {
		return comment;
	}


	public void setComment(Comments comment) {
		this.comment = comment;
	}
	
	
	
}
