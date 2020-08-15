package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dokument database table.
 * 
 */
@Entity
@NamedQuery(name="Dokument.findAll", query="SELECT d FROM Dokument d")
public class Dokument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_dokument")
	private int idDokument;

	@Lob
	@Column(name="file_2")
	private byte[] file2;

	private String naziv;
	
	private boolean obrisan;

	//bi-directional many-to-one association to Student
	@ManyToOne
	private Student student;

	public Dokument() {
	}

	public int getIdDokument() {
		return this.idDokument;
	}

	public void setIdDokument(int idDokument) {
		this.idDokument = idDokument;
	}

	public byte[] getFile2() {
		return this.file2;
	}

	public void setFile2(byte[] file2) {
		this.file2 = file2;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public boolean isObrisan() {
		return this.obrisan;
	}
	
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}