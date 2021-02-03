package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;


/**
 * The persistent class for the rok database table.
 * 
 */
@Entity
@NamedQuery(name="Rok.findAll", query="SELECT r FROM Rok r")
public class Rok implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_rok")
	private int idRok;

	@Column(name="naziv_roka")
	private String nazivRoka;
	
	@Column(name="aktivan")
	private boolean aktivan;

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	//bi-directional many-to-one association to Ispit
	@JsonIgnore
	@OneToMany(mappedBy="rok")
	private List<Ispit> ispits;

	public Rok() {
	}

	public int getIdRok() {
		return this.idRok;
	}

	public void setIdRok(int idRok) {
		this.idRok = idRok;
	}

	public String getNazivRoka() {
		return this.nazivRoka;
	}

	public void setNazivRoka(String nazivRoka) {
		this.nazivRoka = nazivRoka;
	}

	public List<Ispit> getIspits() {
		return this.ispits;
	}

	public void setIspits(List<Ispit> ispits) {
		this.ispits = ispits;
	}

	public Ispit addIspit(Ispit ispit) {
		getIspits().add(ispit);
		ispit.setRok(this);

		return ispit;
	}

	public Ispit removeIspit(Ispit ispit) {
		getIspits().remove(ispit);
		ispit.setRok(null);

		return ispit;
	}

}