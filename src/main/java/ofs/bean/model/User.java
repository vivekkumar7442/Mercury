package ofs.bean.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1660539156826435329L;
	@Id
	@Column(name = "user_id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "user_email")
	private String userEmail;
	
	@Column
	private String userPassword;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserExpenseDetails> userExpenseDetails;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Set<UserExpenseDetails> getUserExpenseDetails() {

		if (userExpenseDetails == null) {
			userExpenseDetails = new HashSet<>();
		}
		return userExpenseDetails;
	}

	public void setUserExpenseDetails(Set<UserExpenseDetails> userExpenseDetails) {
		this.userExpenseDetails = userExpenseDetails;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	
}
