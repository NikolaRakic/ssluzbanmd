package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the nastavnik database table.
 * 
 */
@Entity
@NamedQuery(name="Nastavnik.findAll", query="SELECT n FROM Nastavnik n")
public class Nastavnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_nastavnik")
	private int idNastavnik;
	
	private boolean obrisan;

	//bi-directional many-to-one association to Korisnik
	@OneToMany(mappedBy="nastavnik")
	private List<Korisnik> korisniks;

	//bi-directional many-to-one association to UlogaNastavnik
	@ManyToOne
	@JoinColumn(name="uloga_nastavnik_id_uloga_nastavnik")
	private UlogaNastavnik ulogaNastavnik;

	//bi-directional many-to-one association to NastavnikPredaje
	@OneToMany(mappedBy="nastavnik")
	private List<NastavnikPredaje> nastavnikPredajes;

	public Nastavnik() {
	}

	public int getIdNastavnik() {
		return this.idNastavnik;
	}

	public void setIdNastavnik(int idNastavnik) {
		this.idNastavnik = idNastavnik;
	}
	
	public boolean isObrisan() {
		return this.obrisan;
	}
	
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	

	public List<Korisnik> getKorisniks() {
		return this.korisniks;
	}

	public void setKorisniks(List<Korisnik> korisniks) {
		this.korisniks = korisniks;
	}

	public Korisnik addKorisnik(Korisnik korisnik) {
		getKorisniks().add(korisnik);
		korisnik.setNastavnik(this);

		return korisnik;
	}

	public Korisnik removeKorisnik(Korisnik korisnik) {
		getKorisniks().remove(korisnik);
		korisnik.setNastavnik(null);

		return korisnik;
	}

	public UlogaNastavnik getUlogaNastavnik() {
		return this.ulogaNastavnik;
	}

	public void setUlogaNastavnik(UlogaNastavnik ulogaNastavnik) {
		this.ulogaNastavnik = ulogaNastavnik;
	}

	public List<NastavnikPredaje> getNastavnikPredajes() {
		return this.nastavnikPredajes;
	}

	public void setNastavnikPredajes(List<NastavnikPredaje> nastavnikPredajes) {
		this.nastavnikPredajes = nastavnikPredajes;
	}

	public NastavnikPredaje addNastavnikPredaje(NastavnikPredaje nastavnikPredaje) {
		getNastavnikPredajes().add(nastavnikPredaje);
		nastavnikPredaje.setNastavnik(this);

		return nastavnikPredaje;
	}

	public NastavnikPredaje removeNastavnikPredaje(NastavnikPredaje nastavnikPredaje) {
		getNastavnikPredajes().remove(nastavnikPredaje);
		nastavnikPredaje.setNastavnik(null);

		return nastavnikPredaje;
	}

}