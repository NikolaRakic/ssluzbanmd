package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the uloga_nastavnik database table.
 * 
 */
@Entity
@Table(name="uloga_nastavnik")
@NamedQuery(name="UlogaNastavnik.findAll", query="SELECT u FROM UlogaNastavnik u")
public class UlogaNastavnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_uloga_nastavnik")
	private int idUlogaNastavnik;

	private String naziv;

	//bi-directional many-to-one association to Nastavnik
	@OneToMany(mappedBy="ulogaNastavnik")
	private List<Nastavnik> nastavniks;

	public UlogaNastavnik() {
	}

	public int getIdUlogaNastavnik() {
		return this.idUlogaNastavnik;
	}

	public void setIdUlogaNastavnik(int idUlogaNastavnik) {
		this.idUlogaNastavnik = idUlogaNastavnik;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Nastavnik> getNastavniks() {
		return this.nastavniks;
	}

	public void setNastavniks(List<Nastavnik> nastavniks) {
		this.nastavniks = nastavniks;
	}

	public Nastavnik addNastavnik(Nastavnik nastavnik) {
		getNastavniks().add(nastavnik);
		nastavnik.setUlogaNastavnik(this);

		return nastavnik;
	}

	public Nastavnik removeNastavnik(Nastavnik nastavnik) {
		getNastavniks().remove(nastavnik);
		nastavnik.setUlogaNastavnik(null);

		return nastavnik;
	}

}