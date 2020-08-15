package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the predmet database table.
 * 
 */
@Entity
@NamedQuery(name="Predmet.findAll", query="SELECT p FROM Predmet p")
public class Predmet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_predmet")
	private int idPredmet;

	private String naziv;
	
	private boolean obrisan;

	//bi-directional many-to-one association to Ispit
	@OneToMany(mappedBy="predmet")
	private List<Ispit> ispits;

	//bi-directional many-to-one association to NastavnikPredaje
	@OneToMany(mappedBy="predmet")
	private List<NastavnikPredaje> nastavnikPredajes;

	//bi-directional many-to-one association to PredmetNaSmeru
	@OneToMany(mappedBy="predmet")
	private List<PredmetNaSmeru> predmetNaSmerus;

	public Predmet() {
	}

	public int getIdPredmet() {
		return this.idPredmet;
	}

	public void setIdPredmet(int idPredmet) {
		this.idPredmet = idPredmet;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String string) {
		this.naziv = string;
	}
	
	public boolean isObrisan() {
		return this.obrisan;
	}
	
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	public List<Ispit> getIspits() {
		return this.ispits;
	}

	public void setIspits(List<Ispit> ispits) {
		this.ispits = ispits;
	}

	public Ispit addIspit(Ispit ispit) {
		getIspits().add(ispit);
		ispit.setPredmet(this);

		return ispit;
	}

	public Ispit removeIspit(Ispit ispit) {
		getIspits().remove(ispit);
		ispit.setPredmet(null);

		return ispit;
	}

	public List<NastavnikPredaje> getNastavnikPredajes() {
		return this.nastavnikPredajes;
	}

	public void setNastavnikPredajes(List<NastavnikPredaje> nastavnikPredajes) {
		this.nastavnikPredajes = nastavnikPredajes;
	}

	public NastavnikPredaje addNastavnikPredaje(NastavnikPredaje nastavnikPredaje) {
		getNastavnikPredajes().add(nastavnikPredaje);
		nastavnikPredaje.setPredmet(this);

		return nastavnikPredaje;
	}

	public NastavnikPredaje removeNastavnikPredaje(NastavnikPredaje nastavnikPredaje) {
		getNastavnikPredajes().remove(nastavnikPredaje);
		nastavnikPredaje.setPredmet(null);

		return nastavnikPredaje;
	}

	public List<PredmetNaSmeru> getPredmetNaSmerus() {
		return this.predmetNaSmerus;
	}

	public void setPredmetNaSmerus(List<PredmetNaSmeru> predmetNaSmerus) {
		this.predmetNaSmerus = predmetNaSmerus;
	}

	public PredmetNaSmeru addPredmetNaSmerus(PredmetNaSmeru predmetNaSmerus) {
		getPredmetNaSmerus().add(predmetNaSmerus);
		predmetNaSmerus.setPredmet(this);

		return predmetNaSmerus;
	}

	public PredmetNaSmeru removePredmetNaSmerus(PredmetNaSmeru predmetNaSmerus) {
		getPredmetNaSmerus().remove(predmetNaSmerus);
		predmetNaSmerus.setPredmet(null);

		return predmetNaSmerus;
	}

}