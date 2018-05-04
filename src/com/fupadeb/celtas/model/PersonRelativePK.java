package com.fupadeb.celtas.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the person_relatives database table.
 * 
 */
@Embeddable
public class PersonRelativePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_relative", insertable=false, updatable=false)
	private Integer idRelative;

	@Column(name="id_person", insertable=false, updatable=false)
	private Integer idPerson;

	@Column(name="id_parent_type", insertable=false, updatable=false)
	private Integer idParentType;

	public PersonRelativePK() {
	}
	public Integer getIdRelative() {
		return this.idRelative;
	}
	public void setIdRelative(Integer idRelative) {
		this.idRelative = idRelative;
	}
	public Integer getIdPerson() {
		return this.idPerson;
	}
	public void setIdPerson(Integer idPerson) {
		this.idPerson = idPerson;
	}
	public Integer getIdParentType() {
		return this.idParentType;
	}
	public void setIdParentType(Integer idParentType) {
		this.idParentType = idParentType;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PersonRelativePK)) {
			return false;
		}
		PersonRelativePK castOther = (PersonRelativePK)other;
		return 
			this.idRelative.equals(castOther.idRelative)
			&& this.idPerson.equals(castOther.idPerson)
			&& this.idParentType.equals(castOther.idParentType);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idRelative.hashCode();
		hash = hash * prime + this.idPerson.hashCode();
		hash = hash * prime + this.idParentType.hashCode();
		
		return hash;
	}
}