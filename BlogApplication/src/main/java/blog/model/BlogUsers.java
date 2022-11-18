package blog.model;

import java.util.Date;

/**
 * BlogUsers is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Persons}).
 */
public class BlogUsers extends Persons {
	protected Date dob;
	protected StatusLevel statusLevel;
	
	public enum StatusLevel {
		novice, intermediate, advanced
	}
	
	public BlogUsers(String userName, String firstName, String lastName, Date dob, StatusLevel statusLevel) {
		super(userName, firstName, lastName);
		this.dob = dob;
		this.statusLevel = statusLevel;
	}
	
	public BlogUsers(String userName) {
		super(userName);
	}

	/** Getters and setters. */
	
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public StatusLevel getStatusLevel() {
		return statusLevel;
	}

	public void setStatusLevel(StatusLevel statusLevel) {
		this.statusLevel = statusLevel;
	}
}
