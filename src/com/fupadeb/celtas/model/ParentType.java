package com.fupadeb.celtas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the parent_types database table.
 * 
 */
@Entity
@Table(name="parent_types")
@NamedQueries({ @NamedQuery(name="ParentType.findAll", query="SELECT p FROM ParentType p"),
	@NamedQuery(name = "ParentType.findById", query = "SELECT p FROM ParentType p WHERE p.idParentType = :inputIDNumber"),
	@NamedQuery(name = "ParentType.findByDescription", query = "SELECT p FROM ParentType p WHERE p.description = :inputDescription") })
public class ParentType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_parent_type", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer idParentType;

	@Column(nullable=false, length=255)
	private String description;

	//bi-directional many-to-one association to PersonRelative
	@OneToMany(mappedBy="parentType")
	private List<PersonRelative> personRelatives;

	public ParentType() {
	}

	public Integer getIdParentType() {
		return this.idParentType;
	}

	public void setIdParentType(Integer idParentType) {
		this.idParentType = idParentType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<PersonRelative> getPersonRelatives() {
		return this.personRelatives;
	}

	public void setPersonRelatives(List<PersonRelative> personRelatives) {
		this.personRelatives = personRelatives;
	}

	public PersonRelative addPersonRelative(PersonRelative personRelative) {
		getPersonRelatives().add(personRelative);
		personRelative.setParentType(this);

		return personRelative;
	}

	public PersonRelative removePersonRelative(PersonRelative personRelative) {
		getPersonRelatives().remove(personRelative);
		personRelative.setParentType(null);

		return personRelative;
	}

}