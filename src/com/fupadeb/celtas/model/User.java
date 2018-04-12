package com.fupadeb.celtas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name = "users")
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
	@NamedQuery(name = "User.findEmail", query = "SELECT u FROM User u WHERE u.email LIKE concat('%', :email, '%')"),
	@NamedQuery(name = "User.genericNameSearch", query = "SELECT u FROM User u WHERE u.name LIKE concat('%', :name, '%') OR u.surName LIKE concat('%', :name, '%')") 
	})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(insertable = false, updatable = false, unique = true, nullable = false)
	private Integer uid;

	@Column(nullable = false)
	private Boolean active;

	@Column(nullable = false, length = 255)
	private String email;

	@Column(nullable = false, length = 255)
	private String name;

	@Column(nullable = false, length = 2147483647)
	private String password;

	@Column(name = "sur_name", nullable = false, length = 255)
	private String surName;

	// bi-directional many-to-many association to Role
	@ManyToMany
	@JoinTable(name = "user_roles", joinColumns = {
			@JoinColumn(name = "uid", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", nullable = false) })
	private List<Role> roles;

	public User() {
	}

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurName() {
		return this.surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}