package com.fupadeb.celtas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the persons database table.
 * 
 */
@Entity
@Table(name = "persons")
@NamedQueries({ @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
		@NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.idPerson = :inputIDNumber"),
		@NamedQuery(name = "Person.genericNameSearch", query = "SELECT p FROM Person p WHERE p.name LIKE concat('%', :name, '%') OR p.surname LIKE concat('%', :name, '%')"),
		@NamedQuery(name = "Person.findByActivityStatus", query = "SELECT p FROM Person p WHERE p.activePerson = :inputIsActive"),
		@NamedQuery(name = "Person.findByRole", query = "SELECT p FROM Person p WHERE p.idRole = :inputIDRole") })
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_person", insertable = false, updatable = false, unique = true, nullable = false)
	private Integer idPerson;

	@Column(name = "active_person", nullable = false)
	private Boolean activePerson;

	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date", updatable = false)
	private Date birthDate;

	@Column(name = "id_role")
	private Integer idRole;

	@Column(name = "identification_number", updatable = false)
	private Long identificationNumber;

	@Temporal(TemporalType.DATE)
	@Column(name = "join_date", updatable = false)
	private Date joinDate;

	@Column(length = 255)
	private String name;

	@Column(length = 255)
	private String surname;

	// bi-directional many-to-one association to PersonDetail
	@OneToMany(mappedBy = "person")
	private List<PersonDetail> personDetails;

	// bi-directional many-to-one association to PersonRelative
	@OneToMany(mappedBy = "person")
	private List<PersonRelative> personRelatives;

	// bi-directional many-to-many association to TrainGroup
	@ManyToMany(mappedBy = "persons")
	private List<TrainGroup> trainGroups;

	// bi-directional many-to-one association to IdentificationType
	@ManyToOne
	@JoinColumn(name = "id_identification_type")
	private IdentificationType identificationType;

	public Person() {
	}

	public Integer getIdPerson() {
		return this.idPerson;
	}

	public void setIdPerson(Integer idPerson) {
		this.idPerson = idPerson;
	}

	public Boolean getActivePerson() {
		return this.activePerson;
	}

	public void setActivePerson(Boolean activePerson) {
		this.activePerson = activePerson;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getIdRole() {
		return this.idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public Long getIdentificationNumber() {
		return this.identificationNumber;
	}

	public void setIdentificationNumber(Long identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public Date getJoinDate() {
		return this.joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<PersonDetail> getPersonDetails() {
		return this.personDetails;
	}

	public void setPersonDetails(List<PersonDetail> personDetails) {
		this.personDetails = personDetails;
	}

	public PersonDetail addPersonDetail(PersonDetail personDetail) {
		getPersonDetails().add(personDetail);
		personDetail.setPerson(this);

		return personDetail;
	}

	public PersonDetail removePersonDetail(PersonDetail personDetail) {
		getPersonDetails().remove(personDetail);
		personDetail.setPerson(null);

		return personDetail;
	}

	public List<PersonRelative> getPersonRelatives() {
		return this.personRelatives;
	}

	public void setPersonRelatives(List<PersonRelative> personRelatives) {
		this.personRelatives = personRelatives;
	}

	public PersonRelative addPersonRelative(PersonRelative personRelative) {
		getPersonRelatives().add(personRelative);
		personRelative.setPerson(this);

		return personRelative;
	}

	public PersonRelative removePersonRelative(PersonRelative personRelative) {
		getPersonRelatives().remove(personRelative);
		personRelative.setPerson(null);

		return personRelative;
	}

	public List<TrainGroup> getTrainGroups() {
		return this.trainGroups;
	}

	public void setTrainGroups(List<TrainGroup> trainGroups) {
		this.trainGroups = trainGroups;
	}

	public IdentificationType getIdentificationType() {
		return this.identificationType;
	}

	public void setIdentificationType(IdentificationType identificationType) {
		this.identificationType = identificationType;
	}

}