package com.shopme.common.entity;

import java.beans.Transient;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@ToString
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@SequenceGenerator(name = "user_seq", allocationSize = 1, initialValue = 1)
	private Long id;

	@Column(name = "password", length = 50, nullable = false)
	private String password;

	@Column(name = "first_name", length = 30, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 30, nullable = false)
	private String lastName;

	@Column(name = "email_address", length = 40, nullable = false, unique = true)
	private String email;

	@Column(name = "user_photo", length = 64)
	private String photos;

	@Column(name = "user_enabled")
	private boolean enabled;

	@ManyToMany
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	public Set<Role> roles = new HashSet<>();

	public void addRole(Role role) {
		roles.add(role);
	}

	public User(String password, String firstName, String lastName, String email) {
		super();
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	
	

	@Transient
	public String getPhotosImagePath() {

		if (id == null || photos == null)
			return "images/default-user.png";

		return "/user-photo/" + this.id + "/" + this.photos;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", photos=" + photos + ", enabled=" + enabled + ", roles=" + roles + "]";
	}
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	

}
