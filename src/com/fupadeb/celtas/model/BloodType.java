package com.fupadeb.celtas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the blood_types database table.
 * 
 */
@Entity
@Table(name = "blood_types")
@NamedQueries({ @NamedQuery(name = "BloodType.findAll", query = "SELECT b FROM BloodType b"),
		@NamedQuery(name = "BloodType.findById", query = "SELECT b FROM BloodType b WHERE b.idBloodType = :inputBloodType"),
		@NamedQuery(name = "BloodType.findByDescription", query = "SELECT b FROM BloodType b WHERE b.description = :inputBloodType") })
public class BloodType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_blood_type", insertable = false, updatable = false, unique = true, nullable = false)
	private Integer idBloodType;

	@Column(insertable = false, updatable = false, nullable = false, length = 10)
	private String description;

	// bi-directional many-to-one association to PersonDetail
	@OneToMany(mappedBy = "bloodType")
	private List<PersonDetail> personDetails;

	public BloodType() {
	}

	public Integer getIdBloodType() {
		return this.idBloodType;
	}

	public void setIdBloodType(Integer idBloodType) {
		this.idBloodType = idBloodType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<PersonDetail> getPersonDetails() {
		return this.personDetails;
	}

	public void setPersonDetails(List<PersonDetail> personDetails) {
		this.personDetails = personDetails;
	}

	public PersonDetail addPersonDetail(PersonDetail personDetail) {
		getPersonDetails().add(personDetail);
		personDetail.setBloodType(this);

		return personDetail;
	}

	public PersonDetail removePersonDetail(PersonDetail personDetail) {
		getPersonDetails().remove(personDetail);
		personDetail.setBloodType(null);

		return personDetail;
	}

}