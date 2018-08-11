package ofs.bean;

import java.io.Serializable;

public class UserVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7915142489638606930L;

	private long userId;

	private String userEmail;

	private String firstName;

	private String lastName;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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
