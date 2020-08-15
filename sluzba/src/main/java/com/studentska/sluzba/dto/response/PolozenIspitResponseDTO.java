package com.studentska.sluzba.dto.response;

public class PolozenIspitResponseDTO {
	private String nazivPredmeta;
	private int ocena;
	private String nazivRoka;
	
	public PolozenIspitResponseDTO(String nazivPredmeta, int ocena, String nazivRoka) {
		super();
		this.nazivPredmeta = nazivPredmeta;
		this.ocena = ocena;
		this.nazivRoka = nazivRoka;
	}
	public String getNazivPredmeta() {
		return nazivPredmeta;
	}
	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}
	public int getOcena() {
		return ocena;
	}
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	public String getNazivRoka() {
		return nazivRoka;
	}
	public void setNazivRoka(String nazivRoka) {
		this.nazivRoka = nazivRoka;
	}
	
	
}
