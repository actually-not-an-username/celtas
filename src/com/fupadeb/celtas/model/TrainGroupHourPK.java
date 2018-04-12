package com.fupadeb.celtas.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the train_group_hours database table.
 * 
 */
@Embeddable
public class TrainGroupHourPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_group", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer idGroup;

	@Column(unique=true, nullable=false)
	private Integer day;

	public TrainGroupHourPK() {
	}
	public Integer getIdGroup() {
		return this.idGroup;
	}
	public void setIdGroup(Integer idGroup) {
		this.idGroup = idGroup;
	}
	public Integer getDay() {
		return this.day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TrainGroupHourPK)) {
			return false;
		}
		TrainGroupHourPK castOther = (TrainGroupHourPK)other;
		return 
			this.idGroup.equals(castOther.idGroup)
			&& this.day.equals(castOther.day);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idGroup.hashCode();
		hash = hash * prime + this.day.hashCode();
		
		return hash;
	}
}