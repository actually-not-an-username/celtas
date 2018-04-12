package com.fupadeb.celtas.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the train_group_hours database table.
 * 
 */
@Entity
@Table(name = "train_group_hours")
@NamedQuery(name = "TrainGroupHour.findAll", query = "SELECT t FROM TrainGroupHour t")
public class TrainGroupHour implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TrainGroupHourPK id;

	@Column(nullable = false)
	private Boolean h0;

	@Column(nullable = false)
	private Boolean h1;

	@Column(nullable = false)
	private Boolean h10;

	@Column(nullable = false)
	private Boolean h11;

	@Column(nullable = false)
	private Boolean h12;

	@Column(nullable = false)
	private Boolean h13;

	@Column(nullable = false)
	private Boolean h14;

	@Column(nullable = false)
	private Boolean h15;

	@Column(nullable = false)
	private Boolean h16;

	@Column(nullable = false)
	private Boolean h17;

	@Column(nullable = false)
	private Boolean h18;

	@Column(nullable = false)
	private Boolean h19;

	@Column(nullable = false)
	private Boolean h2;

	@Column(nullable = false)
	private Boolean h20;

	@Column(nullable = false)
	private Boolean h21;

	@Column(nullable = false)
	private Boolean h22;

	@Column(nullable = false)
	private Boolean h23;

	@Column(nullable = false)
	private Boolean h3;

	@Column(nullable = false)
	private Boolean h4;

	@Column(nullable = false)
	private Boolean h5;

	@Column(nullable = false)
	private Boolean h6;

	@Column(nullable = false)
	private Boolean h7;

	@Column(nullable = false)
	private Boolean h8;

	@Column(nullable = false)
	private Boolean h9;

	// bi-directional many-to-one association to TrainGroup
	@ManyToOne
	@JoinColumn(name = "id_group", nullable = false, insertable = false, updatable = false)
	private TrainGroup trainGroup;

	public TrainGroupHour() {
	}

	public TrainGroupHourPK getId() {
		return this.id;
	}

	public void setId(TrainGroupHourPK id) {
		this.id = id;
	}

	public Boolean getH0() {
		return this.h0;
	}

	public void setH0(Boolean h0) {
		this.h0 = h0;
	}

	public Boolean getH1() {
		return this.h1;
	}

	public void setH1(Boolean h1) {
		this.h1 = h1;
	}

	public Boolean getH10() {
		return this.h10;
	}

	public void setH10(Boolean h10) {
		this.h10 = h10;
	}

	public Boolean getH11() {
		return this.h11;
	}

	public void setH11(Boolean h11) {
		this.h11 = h11;
	}

	public Boolean getH12() {
		return this.h12;
	}

	public void setH12(Boolean h12) {
		this.h12 = h12;
	}

	public Boolean getH13() {
		return this.h13;
	}

	public void setH13(Boolean h13) {
		this.h13 = h13;
	}

	public Boolean getH14() {
		return this.h14;
	}

	public void setH14(Boolean h14) {
		this.h14 = h14;
	}

	public Boolean getH15() {
		return this.h15;
	}

	public void setH15(Boolean h15) {
		this.h15 = h15;
	}

	public Boolean getH16() {
		return this.h16;
	}

	public void setH16(Boolean h16) {
		this.h16 = h16;
	}

	public Boolean getH17() {
		return this.h17;
	}

	public void setH17(Boolean h17) {
		this.h17 = h17;
	}

	public Boolean getH18() {
		return this.h18;
	}

	public void setH18(Boolean h18) {
		this.h18 = h18;
	}

	public Boolean getH19() {
		return this.h19;
	}

	public void setH19(Boolean h19) {
		this.h19 = h19;
	}

	public Boolean getH2() {
		return this.h2;
	}

	public void setH2(Boolean h2) {
		this.h2 = h2;
	}

	public Boolean getH20() {
		return this.h20;
	}

	public void setH20(Boolean h20) {
		this.h20 = h20;
	}

	public Boolean getH21() {
		return this.h21;
	}

	public void setH21(Boolean h21) {
		this.h21 = h21;
	}

	public Boolean getH22() {
		return this.h22;
	}

	public void setH22(Boolean h22) {
		this.h22 = h22;
	}

	public Boolean getH23() {
		return this.h23;
	}

	public void setH23(Boolean h23) {
		this.h23 = h23;
	}

	public Boolean getH3() {
		return this.h3;
	}

	public void setH3(Boolean h3) {
		this.h3 = h3;
	}

	public Boolean getH4() {
		return this.h4;
	}

	public void setH4(Boolean h4) {
		this.h4 = h4;
	}

	public Boolean getH5() {
		return this.h5;
	}

	public void setH5(Boolean h5) {
		this.h5 = h5;
	}

	public Boolean getH6() {
		return this.h6;
	}

	public void setH6(Boolean h6) {
		this.h6 = h6;
	}

	public Boolean getH7() {
		return this.h7;
	}

	public void setH7(Boolean h7) {
		this.h7 = h7;
	}

	public Boolean getH8() {
		return this.h8;
	}

	public void setH8(Boolean h8) {
		this.h8 = h8;
	}

	public Boolean getH9() {
		return this.h9;
	}

	public void setH9(Boolean h9) {
		this.h9 = h9;
	}

	public TrainGroup getTrainGroup() {
		return this.trainGroup;
	}

	public void setTrainGroup(TrainGroup trainGroup) {
		this.trainGroup = trainGroup;
	}

}