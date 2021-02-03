package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the tip_ispita database table.
 * 
 */
@Entity
@Table(name="tip_ispita")
@NamedQuery(name="TipIspita.findAll", query="SELECT t FROM TipIspita t")
public class TipIspita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tip_ispita")
	private int idTipIspita;

	@Column(name="naziv_tipa")
	private String nazivTipa;

	//bi-directional many-to-one association to Ispit
	@JsonIgnore
	@OneToMany(mappedBy="tipIspita")
	private List<Ispit> ispits;

	public TipIspita() {
	}

	public int getIdTipIspita() {
		return this.idTipIspita;
	}

	public void setIdTipIspita(int idTipIspita) {
		this.idTipIspita = idTipIspita;
	}

	public String getNazivTipa() {
		return this.nazivTipa;
	}

	public void setNazivTipa(String nazivTipa) {
		this.nazivTipa = nazivTipa;
	}

	public List<Ispit> getIspits() {
		return this.ispits;
	}

	public void setIspits(List<Ispit> ispits) {
		this.ispits = ispits;
	}

	public Ispit addIspit(Ispit ispit) {
		getIspits().add(ispit);
		ispit.setTipIspita(this);

		return ispit;
	}

	public Ispit removeIspit(Ispit ispit) {
		getIspits().remove(ispit);
		ispit.setTipIspita(null);

		return ispit;
	}

}