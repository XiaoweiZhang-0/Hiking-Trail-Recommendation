package com.hiking.model;

import java.sql.Date;

public class HikingHistories {
	protected int hikingHistoryId;
	protected Users user;
	protected HikingTrails hikingTrail;
	protected Date travelTime;
	public HikingHistories(int hikingHistoryId, Users user, HikingTrails hikingTrail, Date travelTime) {
		super();
		this.hikingHistoryId = hikingHistoryId;
		this.user = user;
		this.hikingTrail = hikingTrail;
		this.travelTime = travelTime;
	}
	public HikingHistories(int hikingHistoryId) {
		super();
		this.hikingHistoryId = hikingHistoryId;
	}
	public HikingHistories(Users user, HikingTrails hikingTrail, Date travelTime) {
		super();
		this.user = user;
		this.hikingTrail = hikingTrail;
		this.travelTime = travelTime;
	}
	public int getHikingHistoryId() {
		return hikingHistoryId;
	}
	public void setHikingHistoryId(int hikingHistoryId) {
		this.hikingHistoryId = hikingHistoryId;
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
	public Date getTravelTime() {
		return travelTime;
	}
	public void setTravelTime(Date travelTime) {
		this.travelTime = travelTime;
	}
	
	

}
