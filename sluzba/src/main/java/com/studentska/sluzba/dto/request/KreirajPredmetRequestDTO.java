package com.studentska.sluzba.dto.request;

public class KreirajPredmetRequestDTO {
	private int idPredmet;
	private String naziv;
	
	
	public KreirajPredmetRequestDTO(int idPredmet, String naziv) {
		super();
		this.idPredmet = idPredmet;
		this.naziv = naziv;
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
