package com.studentska.sluzba.dto.request;

import com.studentska.sluzba.model.Predmet;

public class PredmetDTO {
	private int idPredmet;
	private String naziv;
	
	
	public PredmetDTO(int idPredmet, String naziv) {
		super();
		this.idPredmet = idPredmet;
		this.naziv = naziv;
	}
	
	
	public PredmetDTO(Predmet predmet) {
		this(predmet.getIdPredmet(), predmet.getNaziv());
	}


	public int getIdPredmet() {
		return idPredmet;
	}
	public void setIdPredmet(int idPredmet) {
		this.idPredmet = idPredmet;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	

}
