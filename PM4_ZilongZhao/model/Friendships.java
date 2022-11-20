package blog.model;

public class Friendships {
	
	protected Users user1;
	protected Users user2;
	
	public Friendships(Users user1, Users user2) {
		super();
		this.user1 = user1;
		this.user2 = user2;
	}

	public Users getUser1() {
		return user1;
	}

	public void setUser1(Users user1) {
		this.user1 = user1;
	}

	public Users getUser2() {
		return user2;
	}

	public void setUser2(Users user2) {
		this.user2 = user2;
	}

}
