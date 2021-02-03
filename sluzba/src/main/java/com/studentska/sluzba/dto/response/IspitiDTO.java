package com.studentska.sluzba.dto.response;

public class IspitiDTO {
	
	private String brojIndeksa;
	private String nazivPredmeta;
	private String nazivRoka;
	private String tipIspita;
	private String datum;
	private int ocena;
	private int bodovi;
	
	public IspitiDTO(String brojIndeksa, String nazivPredmeta, String nazivRoka, String tipIspita, String datum,
			int ocena, int bodovi) {
		super();
		this.brojIndeksa = brojIndeksa;
		this.nazivPredmeta = nazivPredmeta;
		this.nazivRoka = nazivRoka;
		this.tipIspita = tipIspita;
		this.datum = datum;
		this.ocena = ocena;
		this.bodovi = bodovi;
	}

	public String getBrojIndeksa() {
		return brojIndeksa;
	}

	public void setBrojIndeksa(String brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}

	public String getNazivPredmeta() {
		return nazivPredmeta;
	}

	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}

	public String getNazivRoka() {
		return nazivRoka;
	}

	public void setNazivRoka(String nazivRoka) {
		this.nazivRoka = nazivRoka;
	}

	public String getTipIspita() {
		return tipIspita;
	}

	public void setTipIspita(String tipIspita) {
		this.tipIspita = tipIspita;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public int getBodovi() {
		return bodovi;
	}

	public void setBodovi(int bodovi) {
		this.bodovi = bodovi;
	}
	
	

}
