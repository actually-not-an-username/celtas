package com.fupadeb.celtas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the identification_types database table.
 * 
 */
@Entity
@Table(name="identification_types")
@NamedQuery(name="IdentificationType.findAll", query="SELECT i FROM IdentificationType i")
public class IdentificationType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_identification_type", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer idIdentificationType;

	@Column(insertable=false, updatable=false, length=255)
	private String description;

	//bi-directional many-to-one association to Person
	@OneToMany(mappedBy="identificationType")
	private List<Person> persons;

	//bi-directional many-to-one association to Relative
	@OneToMany(mappedBy="identificationType")
	private List<Relative> relatives;

	public IdentificationType() {
	}

	public Integer getIdIdentificationType() {
		return this.idIdentificationType;
	}

	public void setIdIdentificationType(Integer idIdentificationType) {
		this.idIdentificationType = idIdentificationType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public Person addPerson(Person person) {
		getPersons().add(person);
		person.setIdentificationType(this);

		return person;
	}

	public Person removePerson(Person person) {
		getPersons().remove(person);
		person.setIdentificationType(null);

		return person;
	}

	public List<Relative> getRelatives() {
		return this.relatives;
	}

	public void setRelatives(List<Relative> relatives) {
		this.relatives = relatives;
	}

	public Relative addRelative(Relative relative) {
		getRelatives().add(relative);
		relative.setIdentificationType(this);

		return relative;
	}

	public Relative removeRelative(Relative relative) {
		getRelatives().remove(relative);
		relative.setIdentificationType(null);

		return relative;
	}

}