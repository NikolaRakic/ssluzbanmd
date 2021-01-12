package com.studentska.sluzba.dto.response;

public class StudentiDTO {
	private int id;
	private String korIme;
	private String email;
	private String ime;
	private String prezime;
	private int idStudenta;
	private String brojIndeksa;
	private String nazivSmera;
	
	
	public StudentiDTO(int id, String korIme, String email, String ime, String prezime, int idStudenta,
			String brojIndeksa, String nazivSmera) {
		super();
		this.id = id;
		this.korIme = korIme;
		this.email = email;
		this.ime = ime;
		this.prezime = prezime;
		this.idStudenta = idStudenta;
		this.brojIndeksa = brojIndeksa;
		this.nazivSmera = nazivSmera;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getKorIme() {
		return korIme;
	}


	public void setKorIme(String korIme) {
		this.korIme = korIme;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getIme() {
		return ime;
	}


	public void setIme(String ime) {
		this.ime = ime;
	}


	public String getPrezime() {
		return prezime;
	}


	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}


	public int getIdStudenta() {
		return idStudenta;
	}


	public void setIdStudenta(int idStudenta) {
		this.idStudenta = idStudenta;
	}


	public String getBrojIndeksa() {
		return brojIndeksa;
	}


	public void setBrojIndeksa(String brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}
	
	
	public String getNazivSmera() {
		return nazivSmera;
	}


	public void setNazivSmera(String nazivSmera) {
		this.nazivSmera = nazivSmera;
	}

}
