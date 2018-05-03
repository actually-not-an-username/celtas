package com.fupadeb.celtas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQueries({ @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r"),
	@NamedQuery(name = "Role.findById", query = "SELECT r FROM Role r WHERE r.roleId = :inputRole"),
	@NamedQuery(name = "Role.findByName", query = "SELECT r FROM Role r WHERE r.roleDescription = :inputRole") })
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id")
	private Integer roleId;

	@Column(name="role_description")
	private String roleDescription;

	//bi-directional many-to-one association to Person
	@OneToMany(mappedBy="role")
	private List<Person> persons;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="roles")
	private List<User> users;

	public Role() {
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public List<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public Person addPerson(Person person) {
		getPersons().add(person);
		person.setRole(this);

		return person;
	}

	public Person removePerson(Person person) {
		getPersons().remove(person);
		person.setRole(null);

		return person;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}