package com.fupadeb.celtas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the train_groups database table.
 * 
 */
@Entity
@Table(name="train_groups")
@NamedQuery(name="TrainGroup.findAll", query="SELECT t FROM TrainGroup t")
public class TrainGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_group")
	private Integer idGroup;

	private String description;

	//bi-directional many-to-many association to Person
	@ManyToMany
	@JoinTable(
		name="person_train_groups"
		, joinColumns={
			@JoinColumn(name="id_group")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_person")
			}
		)
	private List<Person> persons;

	//bi-directional many-to-one association to TrainGroupHour
	@OneToMany(mappedBy="trainGroup")
	private List<TrainGroupHour> trainGroupHours;

	public TrainGroup() {
	}

	public Integer getIdGroup() {
		return this.idGroup;
	}

	public void setIdGroup(Integer idGroup) {
		this.idGroup = idGroup;
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

	public List<TrainGroupHour> getTrainGroupHours() {
		return this.trainGroupHours;
	}

	public void setTrainGroupHours(List<TrainGroupHour> trainGroupHours) {
		this.trainGroupHours = trainGroupHours;
	}

	public TrainGroupHour addTrainGroupHour(TrainGroupHour trainGroupHour) {
		getTrainGroupHours().add(trainGroupHour);
		trainGroupHour.setTrainGroup(this);

		return trainGroupHour;
	}

	public TrainGroupHour removeTrainGroupHour(TrainGroupHour trainGroupHour) {
		getTrainGroupHours().remove(trainGroupHour);
		trainGroupHour.setTrainGroup(null);

		return trainGroupHour;
	}

}