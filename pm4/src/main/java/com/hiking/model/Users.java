package com.hiking.model;

public class Users {
	protected int userId;
	protected String firstName;
	protected String lastName;
	protected String password;
	protected int age;
	protected double weight;
	protected double height;
	protected String address;
	protected String email;
	protected String phoneNumber;
	protected Gender gender;
	protected HikingLevel hikingLevel;
	
	public enum Gender {
		Male, Female, NotSpecified, NonBinary
	}
	
	public enum HikingLevel{
		Beginner, Intermediate, Advanced, Professional
	}

	public Users(int userId, String firstName, String lastName, String password, int age, double weight, double height,
				 String address, String email, String phoneNumber, Gender gender, HikingLevel hikingLevel) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.hikingLevel = hikingLevel;
	}

	public Users(int userId) {
		this.userId = userId;
	}

	public Users(String firstName, String lastName, String password, int age, double weight, double height,
				 String address, String email, String phoneNumber, Gender gender, HikingLevel hikingLevel) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.hikingLevel = hikingLevel;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public HikingLevel getHikingLevel() {
		return hikingLevel;
	}

	public void setHikingLevel(HikingLevel hikingLevel) {
		this.hikingLevel = hikingLevel;
	}
	
}
