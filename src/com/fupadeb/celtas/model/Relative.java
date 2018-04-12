package com.fupadeb.celtas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the relatives database table.
 * 
 */
@Entity
@Table(name="relatives")
@NamedQuery(name="Relative.findAll", query="SELECT r FROM Relative r")
public class Relative implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_relative", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer idRelative;

	@Column(length=50)
	private String cellphone;

	@Column(nullable=false, length=200)
	private String fullname;

	@Column(name="identification_number")
	private Long identificationNumber;

	@Column(length=2147483647)
	private String ocupation;

	@Column(length=50)
	private String phone1;

	@Column(length=500)
	private String streetaddress1;

	//bi-directional many-to-one association to PersonRelative
	@OneToMany(mappedBy="relative")
	private List<PersonRelative> personRelatives;

	//bi-directional many-to-one association to IdentificationType
	@ManyToOne
	@JoinColumn(name="id_identification_type")
	private IdentificationType identificationType;

	public Relative() {
	}

	public Integer getIdRelative() {
		return this.idRelative;
	}

	public void setIdRelative(Integer idRelative) {
		this.idRelative = idRelative;
	}

	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Long getIdentificationNumber() {
		return this.identificationNumber;
	}

	public void setIdentificationNumber(Long identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getOcupation() {
		return this.ocupation;
	}

	public void setOcupation(String ocupation) {
		this.ocupation = ocupation;
	}

	public String getPhone1() {
		return this.phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getStreetaddress1() {
		return this.streetaddress1;
	}

	public void setStreetaddress1(String streetaddress1) {
		this.streetaddress1 = streetaddress1;
	}

	public List<PersonRelative> getPersonRelatives() {
		return this.personRelatives;
	}

	public void setPersonRelatives(List<PersonRelative> personRelatives) {
		this.personRelatives = personRelatives;
	}

	public PersonRelative addPersonRelative(PersonRelative personRelative) {
		getPersonRelatives().add(personRelative);
		personRelative.setRelative(this);

		return personRelative;
	}

	public PersonRelative removePersonRelative(PersonRelative personRelative) {
		getPersonRelatives().remove(personRelative);
		personRelative.setRelative(null);

		return personRelative;
	}

	public IdentificationType getIdentificationType() {
		return this.identificationType;
	}

	public void setIdentificationType(IdentificationType identificationType) {
		this.identificationType = identificationType;
	}

}