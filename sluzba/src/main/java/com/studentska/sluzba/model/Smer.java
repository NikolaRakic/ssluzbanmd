package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the smer database table.
 * 
 */
@Entity
@NamedQuery(name="Smer.findAll", query="SELECT s FROM Smer s")
public class Smer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_smer")
	private int idSmer;

	private String naziv;

	private int trajanje;
	
	private boolean obrisan;

	//bi-directional many-to-one association to PredmetNaSmeru
	@OneToMany(mappedBy="smer")
	private List<PredmetNaSmeru> predmetNaSmerus;

	//bi-directional many-to-one association to Student
	@OneToMany(mappedBy="smer")
	private List<Student> students;

	public Smer() {
	}

	public int getIdSmer() {
		return this.idSmer;
	}

	public void setIdSmer(int idSmer) {
		this.idSmer = idSmer;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getTrajanje() {
		return this.trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}
	
	public boolean isObrisan() {
		return this.obrisan;
	}
	
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	public List<PredmetNaSmeru> getPredmetNaSmerus() {
		return this.predmetNaSmerus;
	}

	public void setPredmetNaSmerus(List<PredmetNaSmeru> predmetNaSmerus) {
		this.predmetNaSmerus = predmetNaSmerus;
	}

	public PredmetNaSmeru addPredmetNaSmerus(PredmetNaSmeru predmetNaSmerus) {
		getPredmetNaSmerus().add(predmetNaSmerus);
		predmetNaSmerus.setSmer(this);

		return predmetNaSmerus;
	}

	public PredmetNaSmeru removePredmetNaSmerus(PredmetNaSmeru predmetNaSmerus) {
		getPredmetNaSmerus().remove(predmetNaSmerus);
		predmetNaSmerus.setSmer(null);

		return predmetNaSmerus;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setSmer(this);

		return student;
	}

	public Student removeStudent(Student student) {
		getStudents().remove(student);
		student.setSmer(null);

		return student;
	}

}