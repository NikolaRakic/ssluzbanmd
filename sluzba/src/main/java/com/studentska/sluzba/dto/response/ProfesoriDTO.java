package com.studentska.sluzba.dto.response;

public class ProfesoriDTO {
	
	private int idKorisnik;
	private String Adresa;
	private String email;
	private String ime;
	private String prezime;
	private int idNastavnik;
	private String uloga;
	
	public ProfesoriDTO(int idKorisnik, int idNastavnik, String ime, String prezime, String email, String uloga) {
		super();
		this.idKorisnik = idKorisnik;
		this.idNastavnik = idNastavnik;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.uloga = uloga;
	}

	public int getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public int getIdNastavnik() {
		return idNastavnik;
	}

	public void setIdNastavnik(int idNastavnik) {
		this.idNastavnik = idNastavnik;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	
	

}
