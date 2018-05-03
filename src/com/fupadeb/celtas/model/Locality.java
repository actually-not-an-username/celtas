package com.fupadeb.celtas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the localities database table.
 * 
 */
@Entity
@Table(name="localities")
@NamedQuery(name="Locality.findAll", query="SELECT l FROM Locality l")
public class Locality implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_locality")
	private Integer idLocality;

	private String description;

	//bi-directional many-to-one association to PersonDetail
	@OneToMany(mappedBy="locality")
	private List<PersonDetail> personDetails;

	public Locality() {
	}

	public Integer getIdLocality() {
		return this.idLocality;
	}

	public void setIdLocality(Integer idLocality) {
		this.idLocality = idLocality;
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
		personDetail.setLocality(this);

		return personDetail;
	}

	public PersonDetail removePersonDetail(PersonDetail personDetail) {
		getPersonDetails().remove(personDetail);
		personDetail.setLocality(null);

		return personDetail;
	}

}