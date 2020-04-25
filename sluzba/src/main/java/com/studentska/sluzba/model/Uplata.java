package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the uplata database table.
 * 
 */
@Entity
@NamedQuery(name="Uplata.findAll", query="SELECT u FROM Uplata u")
public class Uplata implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_uplata")
	private int idUplata;

	private int iznos;

	private String opis;

	//bi-directional many-to-one association to Student
	@ManyToOne
	private Student student;

	public Uplata() {
	}

	public int getIdUplata() {
		return this.idUplata;
	}

	public void setIdUplata(int idUplata) {
		this.idUplata = idUplata;
	}

	public int getIznos() {
		return this.iznos;
	}

	public void setIznos(int iznos) {
		this.iznos = iznos;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}