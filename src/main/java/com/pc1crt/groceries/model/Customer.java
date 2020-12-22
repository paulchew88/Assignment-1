package com.pc1crt.groceries.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pc1crt.groceries.model.Address;

/**
 * @author paul_
 *
 */
@Entity
@Embeddable
public class Customer implements UserDetails, Serializable {
	@Id
	@GeneratedValue
	private Integer customerId;
	@Column(name = "email", unique = true)
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Must be a valid email")
	private String email;
	@Column(name = "password")
	@NotBlank(message = "password is mandatory")
	private String password;
	@Embedded
	@Column(name = "billing_Address")
	private Address billingAddress;
	@Column(name = "phonenumber")
	@NotBlank(message = "number must not be left blank")
	@Pattern(regexp = "[\\d]{11}", message = "must be a valid phone number including area code")
	private String phoneNumber;
	private Boolean active;	
	private String roles = "";
	private String permissions = "";

	public Customer() {
	}

	/**
	 * @param customerId
	 * @param email
	 * @param password
	 * @param billingAddress
	 * @param phoneNumber
	 * @param active
	 * @param roles
	 * @param permissions
	 */
	public Customer(Integer customerId,
			@NotBlank(message = "Email is mandatory") @Email(message = "Must be a valid email") String email,
			@NotBlank(message = "password is mandetory") String password, Address billingAddress,
			@NotBlank(message = "number must not be left blank") @Pattern(regexp = "[\\d]{11}", message = "must be a valid phone number including area code") String phoneNumber,
			Boolean active, @NotBlank String roles, String permissions) {
		this.customerId = customerId;
		this.email = email;
		this.password = password;
		this.billingAddress = billingAddress;
		this.phoneNumber = phoneNumber;
		this.active = active;
		this.roles = roles;
		this.permissions = permissions;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the id
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * @param id the id to set
	 */
	public void setCustomweId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the billingAddress
	 */
	public Address getBillingAddress() {
		return billingAddress;
	}

	/**
	 * @param billingAddress the billingAddress to set
	 */
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<String> getRoleList() {
		if (this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<>();
	}

	public List<String> getPermissionList() {
		if (this.permissions.length() > 0) {
			return Arrays.asList(this.permissions.split(","));
		}
		return new ArrayList<>();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Customer [customerId=").append(customerId).append(", email=").append(email)
				.append(", password=").append(password).append(", billingAddress=").append(billingAddress)
				.append(", phoneNumber=").append(phoneNumber).append(", active=").append(active).append(", roles=")
				.append(roles).append(", permissions=").append(permissions).append("]");
		return builder.toString();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
