package blog.model;



public class Recommendations {
	protected int recommendationId;
	protected Users user;
	protected HikingTrails hikingTrail;
	public Recommendations(int recommendationId, Users user, HikingTrails hikingTrail) {
		super();
		this.recommendationId = recommendationId;
		this.user = user;
		this.hikingTrail = hikingTrail;
	}
	public Recommendations(int recommendationId) {
		super();
		this.recommendationId = recommendationId;
	}
	public Recommendations(Users user, HikingTrails hikingTrail) {
		super();
		this.user = user;
		this.hikingTrail = hikingTrail;
	}
	public int getRecommendationId() {
		return recommendationId;
	}
	public void setRecommendationId(int recommendationId) {
		this.recommendationId = recommendationId;
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
	

	
	
	
}
