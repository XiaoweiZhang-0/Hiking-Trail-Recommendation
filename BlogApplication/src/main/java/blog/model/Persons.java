package blog.model;

/**
 * Persons is a simple, plain old java objects (POJO).
 * 
 * Persons/PersonsDao is the superclass for Administrators/AdministratorsDao and
 * BlogUsers/BlogUsersDao. Our implementation of Persons is a concrete class. This allows 
 * us to create records in the Persons MySQL table without having the associated records
 * in the Administrators or BlogUsers MySQL tables. Alternatively, Persons could be an
 * interface or an abstract class, which would force a Persons record to be created only
 * if an Administrators or BlogUsers record is created, too.
 */
public class Persons {
	protected String userName;
	protected String firstName;
	protected String lastName;
	
	public Persons(String userName, String firstName, String lastName) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Persons(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
