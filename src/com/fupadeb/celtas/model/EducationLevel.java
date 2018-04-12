package com.fupadeb.celtas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the education_level database table.
 * 
 */
@Entity
@Table(name="education_level")
@NamedQuery(name="EducationLevel.findAll", query="SELECT e FROM EducationLevel e")
public class EducationLevel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_education_level", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer idEducationLevel;

	@Column(insertable=false, updatable=false, nullable=false, length=255)
	private String description;

	//bi-directional many-to-one association to PersonDetail
	@OneToMany(mappedBy="educationLevel")
	private List<PersonDetail> personDetails;

	public EducationLevel() {
	}

	public Integer getIdEducationLevel() {
		return this.idEducationLevel;
	}

	public void setIdEducationLevel(Integer idEducationLevel) {
		this.idEducationLevel = idEducationLevel;
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
		personDetail.setEducationLevel(this);

		return personDetail;
	}

	public PersonDetail removePersonDetail(PersonDetail personDetail) {
		getPersonDetails().remove(personDetail);
		personDetail.setEducationLevel(null);

		return personDetail;
	}

}