package blog.model;

public class Reshares {
	protected int reshareId;
	protected BlogUsers blogUser;
	protected BlogPosts blogPost;
	
	public Reshares(int reshareId, BlogUsers blogUser, BlogPosts blogPost) {
		this.reshareId = reshareId;
		this.blogUser = blogUser;
		this.blogPost = blogPost;
	}
	
	public Reshares(BlogUsers blogUser, BlogPosts blogPost) {
		this.blogUser = blogUser;
		this.blogPost = blogPost;
	}
	
	public Reshares(int reshareId) {
		this.reshareId = reshareId;
	}

	public int getReshareId() {
		return reshareId;
	}

	public void setReshareId(int reshareId) {
		this.reshareId = reshareId;
	}

	public BlogUsers getBlogUser() {
		return blogUser;
	}

	public void setBlogUser(BlogUsers blogUser) {
		this.blogUser = blogUser;
	}

	public BlogPosts getBlogPost() {
		return blogPost;
	}

	public void setBlogPost(BlogPosts blogPost) {
		this.blogPost = blogPost;
	}
}
