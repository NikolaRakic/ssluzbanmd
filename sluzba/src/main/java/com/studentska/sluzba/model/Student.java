package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the student database table.
 * 
 */
@Entity
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_student")
	private int idStudent;

	@Column(name="broj_indeksa")
	private String brojIndeksa;

	@Column(name="godina_studija")
	private int godinaStudija;
	
	private boolean obrisan;

	//bi-directional many-to-one association to Dokument
	@OneToMany(mappedBy="student")
	private List<Dokument> dokuments;

	//bi-directional many-to-one association to Ispit
	@OneToMany(mappedBy="student")
	private List<Ispit> ispits;

	//bi-directional many-to-one association to Korisnik
	@OneToMany(mappedBy="student")
	private List<Korisnik> korisniks;

	//bi-directional many-to-one association to Smer
	@ManyToOne
	private Smer smer;

	//bi-directional many-to-one association to Uplata
	@OneToMany(mappedBy="student")
	private List<Uplata> uplatas;

	public Student() {
	}

	public int getIdStudent() {
		return this.idStudent;
	}

	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}

	public String getBrojIndeksa() {
		return this.brojIndeksa;
	}

	public void setBrojIndeksa(String brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}

	public int getGodinaStudija() {
		return this.godinaStudija;
	}

	public void setGodinaStudija(int godinaStudija) {
		this.godinaStudija = godinaStudija;
	}
	
	public boolean isObrisan() {
		return this.obrisan;
	}
	
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	public List<Dokument> getDokuments() {
		return this.dokuments;
	}

	public void setDokuments(List<Dokument> dokuments) {
		this.dokuments = dokuments;
	}

	public Dokument addDokument(Dokument dokument) {
		getDokuments().add(dokument);
		dokument.setStudent(this);

		return dokument;
	}

	public Dokument removeDokument(Dokument dokument) {
		getDokuments().remove(dokument);
		dokument.setStudent(null);

		return dokument;
	}

	public List<Ispit> getIspits() {
		return this.ispits;
	}

	public void setIspits(List<Ispit> ispits) {
		this.ispits = ispits;
	}

	public Ispit addIspit(Ispit ispit) {
		getIspits().add(ispit);
		ispit.setStudent(this);

		return ispit;
	}

	public Ispit removeIspit(Ispit ispit) {
		getIspits().remove(ispit);
		ispit.setStudent(null);

		return ispit;
	}

	public List<Korisnik> getKorisniks() {
		return this.korisniks;
	}

	public void setKorisniks(List<Korisnik> korisniks) {
		this.korisniks = korisniks;
	}

	public Korisnik addKorisnik(Korisnik korisnik) {
		getKorisniks().add(korisnik);
		korisnik.setStudent(this);

		return korisnik;
	}

	public Korisnik removeKorisnik(Korisnik korisnik) {
		getKorisniks().remove(korisnik);
		korisnik.setStudent(null);

		return korisnik;
	}

	public Smer getSmer() {
		return this.smer;
	}

	public void setSmer(Smer smer) {
		this.smer = smer;
	}

	public List<Uplata> getUplatas() {
		return this.uplatas;
	}

	public void setUplatas(List<Uplata> uplatas) {
		this.uplatas = uplatas;
	}

	public Uplata addUplata(Uplata uplata) {
		getUplatas().add(uplata);
		uplata.setStudent(this);

		return uplata;
	}

	public Uplata removeUplata(Uplata uplata) {
		getUplatas().remove(uplata);
		uplata.setStudent(null);

		return uplata;
	}

}