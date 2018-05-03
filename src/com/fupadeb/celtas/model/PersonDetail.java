package com.fupadeb.celtas.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the person_details database table.
 * 
 */
@Entity
@Table(name="person_details")
@NamedQuery(name="PersonDetail.findAll", query="SELECT p FROM PersonDetail p")
public class PersonDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_person", insertable=false, updatable=false)
	private Integer idPerson;

	private Integer height;

	private String neighborhood;

	private String phone1;

	private String phone2;

	@Column(name="school_name")
	private String schoolName;

	private String streetaddress1;

	private String streetaddress2;

	private Integer weight;

	//bi-directional many-to-one association to BloodType
	@ManyToOne
	@JoinColumn(name="id_blood_type")
	private BloodType bloodType;

	//bi-directional many-to-one association to EducationLevel
	@ManyToOne
	@JoinColumn(name="id_education_level")
	private EducationLevel educationLevel;

	//bi-directional many-to-one association to HealthCareOrg
	@ManyToOne
	@JoinColumn(name="id_health_care_org")
	private HealthCareOrg healthCareOrg;

	//bi-directional many-to-one association to Locality
	@ManyToOne
	@JoinColumn(name="id_locality")
	private Locality locality;

	//bi-directional one-to-one association to Person
	@OneToOne
	@JoinColumn(name="id_person")
	private Person person;

	public PersonDetail() {
	}

	public Integer getIdPerson() {
		return this.idPerson;
	}

	public void setIdPerson(Integer idPerson) {
		this.idPerson = idPerson;
	}

	public Integer getHeight() {
		return this.height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getNeighborhood() {
		return this.neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getPhone1() {
		return this.phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return this.phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getSchoolName() {
		return this.schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getStreetaddress1() {
		return this.streetaddress1;
	}

	public void setStreetaddress1(String streetaddress1) {
		this.streetaddress1 = streetaddress1;
	}

	public String getStreetaddress2() {
		return this.streetaddress2;
	}

	public void setStreetaddress2(String streetaddress2) {
		this.streetaddress2 = streetaddress2;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public BloodType getBloodType() {
		return this.bloodType;
	}

	public void setBloodType(BloodType bloodType) {
		this.bloodType = bloodType;
	}

	public EducationLevel getEducationLevel() {
		return this.educationLevel;
	}

	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	public HealthCareOrg getHealthCareOrg() {
		return this.healthCareOrg;
	}

	public void setHealthCareOrg(HealthCareOrg healthCareOrg) {
		this.healthCareOrg = healthCareOrg;
	}

	public Locality getLocality() {
		return this.locality;
	}

	public void setLocality(Locality locality) {
		this.locality = locality;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}