package blog.model;

import java.util.Date;


public class BlogComments {
	protected int commentId;
	protected String content;
	protected Date created;
	protected BlogPosts blogPost;
	protected BlogUsers blogUser;
	
	public BlogComments(int commentId, String content, Date created, BlogPosts blogPost,
			BlogUsers blogUser) {
		this.commentId = commentId;
		this.content = content;
		this.created = created;
		this.blogPost = blogPost;
		this.blogUser = blogUser;
	}
	
	public BlogComments(int commentId) {
		this.commentId = commentId;
	}
	
	public BlogComments(String content, Date created, BlogPosts blogPost, BlogUsers blogUser) {
		this.content = content;
		this.created = created;
		this.blogPost = blogPost;
		this.blogUser = blogUser;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public BlogPosts getBlogPost() {
		return blogPost;
	}

	public void setBlogPost(BlogPosts blogPost) {
		this.blogPost = blogPost;
	}

	public BlogUsers getBlogUser() {
		return blogUser;
	}

	public void setBlogUser(BlogUsers blogUser) {
		this.blogUser = blogUser;
	}
}
