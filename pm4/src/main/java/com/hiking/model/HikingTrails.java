package com.hiking.model;

public class HikingTrails {
	protected int trailId;
	protected String trailName;
	protected String county;
	protected double length;
	protected String trailSystem;
	protected String surface;
	protected boolean canBicycle;
	protected boolean canCarDrive;
	
	public HikingTrails(int trailId, String trailName, String county, double length, String trailSystem, String surface,
			boolean canBicycle, boolean canCarDrive) {
		this.trailId = trailId;
		this.trailName = trailName;
		this.county = county;
		this.length = length;
		this.trailSystem = trailSystem;
		this.surface = surface;
		this.canBicycle = canBicycle;
		this.canCarDrive = canCarDrive;
	}

	public HikingTrails(int trailId) {
		this.trailId = trailId;
	}

	public HikingTrails(String trailName, String county, double length, String trailSystem, String surface,
			boolean canBicycle, boolean canCarDrive) {
		this.trailName = trailName;
		this.county = county;
		this.length = length;
		this.trailSystem = trailSystem;
		this.surface = surface;
		this.canBicycle = canBicycle;
		this.canCarDrive = canCarDrive;
	}

	public String getTrailName() {
		return trailName;
	}

	public void setTrailName(String trailName) {
		this.trailName = trailName;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public String getTrailSystem() {
		return trailSystem;
	}

	public void setTrailSystem(String trailSystem) {
		this.trailSystem = trailSystem;
	}

	public String getSurface() {
		return surface;
	}

	public void setSurface(String surface) {
		this.surface = surface;
	}

	public boolean isCanBicycle() {
		return canBicycle;
	}

	public void setCanBicycle(boolean canBicycle) {
		this.canBicycle = canBicycle;
	}

	public int getTrailId() {
		return trailId;
	}

	public void setTrailId(int trailId) {
		this.trailId = trailId;
	}

	public boolean isCanCarDrive() {
		return canCarDrive;
	}

	public void setCanCarDrive(boolean canCarDrive) {
		this.canCarDrive = canCarDrive;
	}
	
	

}
