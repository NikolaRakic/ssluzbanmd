package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the nastavnik_predaje database table.
 * 
 */
@Entity
@Table(name="nastavnik_predaje")
@NamedQuery(name="NastavnikPredaje.findAll", query="SELECT n FROM NastavnikPredaje n")
public class NastavnikPredaje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_nastavnik_predaje")
	private int idNastavnikPredaje;
	
	private boolean obrisan;

	//bi-directional many-to-one association to Nastavnik
	@ManyToOne
	private Nastavnik nastavnik;

	//bi-directional many-to-one association to Predmet
	@ManyToOne
	private Predmet predmet;

	public NastavnikPredaje() {
	}

	public int getIdNastavnikPredaje() {
		return this.idNastavnikPredaje;
	}

	public void setIdNastavnikPredaje(int idNastavnikPredaje) {
		this.idNastavnikPredaje = idNastavnikPredaje;
	}

	public Nastavnik getNastavnik() {
		return this.nastavnik;
	}

	public void setNastavnik(Nastavnik nastavnik) {
		this.nastavnik = nastavnik;
	}
	
	
	public boolean isObrisan() {
		return this.obrisan;
	}
	
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	

	public Predmet getPredmet() {
		return this.predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

}