package blog.model;

public class Friendships {
	
	protected Users user1;
	protected USers user2;
	
	public Friendships(Users user1, USers user2) {
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

	public USers getUser2() {
		return user2;
	}

	public void setUser2(USers user2) {
		this.user2 = user2;
	}

}
