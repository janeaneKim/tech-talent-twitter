package com.tts.techtalenttwitter2.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //generates getters and setters and default toString, etc...
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_profile")
public class User {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;

	@Email(message = "Please provide a valid email")
	@NotEmpty(message="Please provide an email")
	private String email;
	
	@Length(min = 3, message = "Your username be between 3 - 15 characters")
	@Length(max=15, message= "Your username be between 3 - 15 characters")
	@Pattern(regexp="[^\\s]+", message = "Your username cannot contain spaces")
	private String username;
	
	@Length(min=5, message= "Your password must be at least 5 characters")
	private String password;
	
	@NotEmpty(message="Your first name cannot be empty")
	private String firstName;
	
	@NotEmpty(message="Your last name cannot be empty")
	private String lastName;
	private int active;
	
	@CreationTimestamp
	private Date createdAt;
	
	//Associate roles with a user
	//this is a collection type-- 
	//generally do not store multiple elements in a single field in a DB
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_follower", joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name= "follower_id"))
	private List <User> followers;
	
	@ManyToMany(mappedBy = "followers")
	private List<User> following;
	
}
