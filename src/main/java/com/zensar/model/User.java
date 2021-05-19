package com.zensar.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.pl.REGON;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Size(min = 3, max = 50)
//	@Pattern(regexp = "^[a-zA-Z][ ]*$")
	private String firstName;

//	@Pattern(regexp = "^[a-zA-Z][ ]*$")
	private String lastName;

	@NotBlank(message = "Username is required")
//	@Size(min = 3, max = 12, message = "Username must be between 3 and 12 characters long")
	private String username;

	@Email(message = "Enter a valid email address.")
	private String email;

	@NotBlank
	private String currentCity;

	@NotBlank
	private String currentState;

	@NotBlank
	private String currentCountry;

//	@Pattern(regexp = "^[1-9]{1}[0-9]{2}\\\\s{0, 1}[0-9]{3}$")
	private int currentPinCode;

	@NotBlank
	private String permanentCity;

	@NotEmpty
	private String permanentState;

	@NotEmpty
	private String permanentCountry;

//	@Pattern(regexp = "^[1-9]{1}[0-9]{2}\\\\s{0, 1}[0-9]{3}$")
	private int permanentPinCode;

	
	private long aadharNumber;

//	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}")
	private String panNumber;

	
	private long mobileNumber;

	@NotEmpty
//	@Size(min = 6, message = "Sorry, but the given password is too short. Passwords must be at least 6 characters long.")
	private String password;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "savings_id")
	private SavingsAccount savingsAccount;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "salary_id")
	private SalaryAccount salaryAccount;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") })
	private List<Role> roles;

	public User() {
		super();
	}

	public User(int id, String firstName, String lastName, String username, String email, String currentCity,
			String currentState, String currentCountry, int currentPinCode, String permanentCity, String permanentState,
			String permanentCountry, int permanentPinCode, long aadharNumber, String panNumber, long mobileNumber,
			String password, com.zensar.model.SavingsAccount savingsAccount,
			com.zensar.model.SalaryAccount salaryAccount, List<Role> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.currentCity = currentCity;
		this.currentState = currentState;
		this.currentCountry = currentCountry;
		this.currentPinCode = currentPinCode;
		this.permanentCity = permanentCity;
		this.permanentState = permanentState;
		this.permanentCountry = permanentCountry;
		this.permanentPinCode = permanentPinCode;
		this.aadharNumber = aadharNumber;
		this.panNumber = panNumber;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.savingsAccount = savingsAccount;
		this.salaryAccount = salaryAccount;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public String getCurrentCountry() {
		return currentCountry;
	}

	public void setCurrentCountry(String currentCountry) {
		this.currentCountry = currentCountry;
	}

	public int getCurrentPinCode() {
		return currentPinCode;
	}

	public void setCurrentPinCode(int currentPinCode) {
		this.currentPinCode = currentPinCode;
	}

	public String getPermanentCity() {
		return permanentCity;
	}

	public void setPermanentCity(String permanentCity) {
		this.permanentCity = permanentCity;
	}

	public String getPermanentState() {
		return permanentState;
	}

	public void setPermanentState(String permanentState) {
		this.permanentState = permanentState;
	}

	public String getPermanentCountry() {
		return permanentCountry;
	}

	public void setPermanentCountry(String permanentCountry) {
		this.permanentCountry = permanentCountry;
	}

	public int getPermanentPinCode() {
		return permanentPinCode;
	}

	public void setPermanentPinCode(int permanentPinCode) {
		this.permanentPinCode = permanentPinCode;
	}

	public long getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SavingsAccount getSavingsAccount() {
		return savingsAccount;
	}

	public void setSavingsAccount(SavingsAccount savingsAccount) {
		this.savingsAccount = savingsAccount;
	}

	public SalaryAccount getSalaryAccount() {
		return salaryAccount;
	}

	public void setSalaryAccount(SalaryAccount salaryAccount) {
		this.salaryAccount = salaryAccount;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
