package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ispit database table.
 * 
 */
@Entity
@NamedQuery(name="Ispit.findAll", query="SELECT i FROM Ispit i")
public class Ispit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ispit")
	private int idIspit;

	private int bodovi;

	@Temporal(TemporalType.DATE)
	private Date datum;

	private int ocena;

	//bi-directional many-to-one association to Predmet
	@ManyToOne
	private Predmet predmet;

	//bi-directional many-to-one association to Rok
	@ManyToOne
	private Rok rok;

	//bi-directional many-to-one association to Student
	@ManyToOne
	private Student student;

	//bi-directional many-to-one association to TipIspita
	@ManyToOne
	@JoinColumn(name="tip_ispita_id_tip_ispita")
	private TipIspita tipIspita;

	public Ispit() {
	}

	public int getIdIspit() {
		return this.idIspit;
	}

	public void setIdIspit(int idIspit) {
		this.idIspit = idIspit;
	}

	public int getBodovi() {
		return this.bodovi;
	}

	public void setBodovi(int bodovi) {
		this.bodovi = bodovi;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getOcena() {
		return this.ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public Predmet getPredmet() {
		return this.predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

	public Rok getRok() {
		return this.rok;
	}

	public void setRok(Rok rok) {
		this.rok = rok;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public TipIspita getTipIspita() {
		return this.tipIspita;
	}

	public void setTipIspita(TipIspita tipIspita) {
		this.tipIspita = tipIspita;
	}

}