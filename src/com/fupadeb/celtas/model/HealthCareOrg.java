package com.fupadeb.celtas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the health_care_org database table.
 * 
 */
@Entity
@Table(name="health_care_org")
@NamedQuery(name="HealthCareOrg.findAll", query="SELECT h FROM HealthCareOrg h")
public class HealthCareOrg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_health_care_org")
	private Integer idHealthCareOrg;

	private String description;

	//bi-directional many-to-one association to PersonDetail
	@OneToMany(mappedBy="healthCareOrg")
	private List<PersonDetail> personDetails;

	public HealthCareOrg() {
	}

	public Integer getIdHealthCareOrg() {
		return this.idHealthCareOrg;
	}

	public void setIdHealthCareOrg(Integer idHealthCareOrg) {
		this.idHealthCareOrg = idHealthCareOrg;
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
		personDetail.setHealthCareOrg(this);

		return personDetail;
	}

	public PersonDetail removePersonDetail(PersonDetail personDetail) {
		getPersonDetails().remove(personDetail);
		personDetail.setHealthCareOrg(null);

		return personDetail;
	}

}