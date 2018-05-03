package com.fupadeb.celtas.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the person_relatives database table.
 * 
 */
@Entity
@Table(name="person_relatives")
@NamedQuery(name="PersonRelative.findAll", query="SELECT p FROM PersonRelative p")
public class PersonRelative implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonRelativePK id;

	//bi-directional many-to-one association to ParentType
	@ManyToOne
	@JoinColumn(name="id_parent_type")
	private ParentType parentType;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="id_person")
	private Person person;

	//bi-directional many-to-one association to Relative
	@ManyToOne
	@JoinColumn(name="id_relative")
	private Relative relative;

	public PersonRelative() {
	}

	public PersonRelativePK getId() {
		return this.id;
	}

	public void setId(PersonRelativePK id) {
		this.id = id;
	}

	public ParentType getParentType() {
		return this.parentType;
	}

	public void setParentType(ParentType parentType) {
		this.parentType = parentType;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Relative getRelative() {
		return this.relative;
	}

	public void setRelative(Relative relative) {
		this.relative = relative;
	}

}