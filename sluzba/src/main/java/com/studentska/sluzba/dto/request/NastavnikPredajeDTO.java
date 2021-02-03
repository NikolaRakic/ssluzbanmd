package com.studentska.sluzba.dto.request;

public class NastavnikPredajeDTO {
	private int idNastavnikPredaje;
	private int idNastavnik;
	private int idPredmet;
	
	
	public NastavnikPredajeDTO(int idNastavnikPredaje, int idNastavnik, int idPredmet) {
		super();
		this.idNastavnikPredaje = idNastavnikPredaje;
		this.idNastavnik = idNastavnik;
		this.idPredmet = idPredmet;
	}
	public int getIdNastavnikPredaje() {
		return idNastavnikPredaje;
	}
	public void setIdNastavnikPredaje(int idNastavnikPredaje) {
		this.idNastavnikPredaje = idNastavnikPredaje;
	}
	public int getIdNastavnik() {
		return idNastavnik;
	}
	public void setIdNastavnik(int idNastavnik) {
		this.idNastavnik = idNastavnik;
	}
	public int getIdPredmet() {
		return idPredmet;
	}
	public void setIdPredmet(int idPredmet) {
		this.idPredmet = idPredmet;
	}
	
	

}
