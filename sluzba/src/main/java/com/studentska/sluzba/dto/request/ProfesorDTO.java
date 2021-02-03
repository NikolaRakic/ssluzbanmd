package com.studentska.sluzba.dto.request;

import java.util.List;

import com.studentska.sluzba.model.NastavnikPredaje;

public class ProfesorDTO {
	
	private int idKorisnik;
	private String adresa;
	private String email;
	private String ime;
	private String pass;
	private String prezime;
	private String rodjendan;
	private String uloga;
	private String username;
	private int idNastavnik;
	private String ulogaNastavnik;
	
	
	

	public ProfesorDTO(int idKorisnik, String uloga, String adresa, String email, String ime, String pass, String prezime,
			String rodjendan, String username, int idNastavnik, String ulogaNastavnik) {
		super();
		this.idKorisnik = idKorisnik;
		this.uloga = uloga;
		this.adresa = adresa;
		this.email = email;
		this.ime = ime;
		this.pass = pass;
		this.prezime = prezime;
		this.rodjendan = rodjendan;
		this.username = username;
		this.idNastavnik = idNastavnik;
		this.ulogaNastavnik = ulogaNastavnik;
	}




	public int getIdKorisnik() {
		return idKorisnik;
	}




	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}
	
	

	public String getUloga() {
		return uloga;
	}




	public void setUloga(String uloga) {
		this.uloga = uloga;
	}


	public String getAdresa() {
		return adresa;
	}




	public void setAdresa(String adresa) {
		this.adresa = adresa;
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




	public String getPass() {
		return pass;
	}




	public void setPass(String pass) {
		this.pass = pass;
	}




	public String getPrezime() {
		return prezime;
	}




	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}




	public String getRodjendan() {
		return rodjendan;
	}




	public void setRodjendan(String rodjendan) {
		this.rodjendan = rodjendan;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public int getIdNastavnik() {
		return idNastavnik;
	}




	public void setIdNastavnik(int idNastavnik) {
		this.idNastavnik = idNastavnik;
	}




	public String getUlogaNastavnik() {
		return ulogaNastavnik;
	}




	public void setUlogaNastavnik(String ulogaNastavnik) {
		this.ulogaNastavnik = ulogaNastavnik;
	}
	
	

}
