package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the predmet_na_smeru database table.
 * 
 */
@Entity
@Table(name="predmet_na_smeru")
@NamedQuery(name="PredmetNaSmeru.findAll", query="SELECT p FROM PredmetNaSmeru p")
public class PredmetNaSmeru implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_predmet_na_smeru")
	private int idPredmetNaSmeru;

	@Column(name="godina_pojavljivanja")
	private int godinaPojavljivanja;
	
	private boolean obrisan;

	//bi-directional many-to-one association to Predmet
	@ManyToOne
	private Predmet predmet;

	//bi-directional many-to-one association to Smer
	@ManyToOne
	private Smer smer;

	public PredmetNaSmeru() {
	}

	public int getIdPredmetNaSmeru() {
		return this.idPredmetNaSmeru;
	}

	public void setIdPredmetNaSmeru(int idPredmetNaSmeru) {
		this.idPredmetNaSmeru = idPredmetNaSmeru;
	}

	public int getGodinaPojavljivanja() {
		return this.godinaPojavljivanja;
	}

	public void setGodinaPojavljivanja(int godinaPojavljivanja) {
		this.godinaPojavljivanja = godinaPojavljivanja;
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

	public Smer getSmer() {
		return this.smer;
	}

	public void setSmer(Smer smer) {
		this.smer = smer;
	}

}