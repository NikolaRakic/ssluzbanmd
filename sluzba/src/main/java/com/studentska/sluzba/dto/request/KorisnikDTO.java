package com.studentska.sluzba.dto.request;

import java.util.Date;

public class KorisnikDTO {
	private int idKorisnik;
	private String adresa;
	private String email;
	private String ime;
	private String pass;
	private String prezime;
	private String rodjendan;
	private String username;
	private String uloga;
	private String brojIndeksa;
	private int godinaStudija;
	private String ulogaNastavnik;
	private String smerStudenta;
	
	public KorisnikDTO(int idKorisnik, String adresa, String email, String ime, String pass,
			String prezime, String rodjendan, String username, String uloga, String brojIndeksa, int godinaStudija, String ulogaNastavnik, String smerStudenta) {
		super();
		this.idKorisnik = idKorisnik;
		this.adresa = adresa;
		this.email = email;
		this.ime = ime;
		this.pass = pass;
		this.prezime = prezime;
		this.rodjendan = rodjendan;
		this.username = username;
		this.uloga = uloga;
		this.brojIndeksa = brojIndeksa;
		this.godinaStudija = godinaStudija;
		this.ulogaNastavnik = ulogaNastavnik;
		this.smerStudenta = smerStudenta;
	}
	public int getIdKorisnik() {
		return idKorisnik;
	}
	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
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
	public String getUloga() {
		return uloga;
	}
	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	public String getBrojIndeksa() {
		return brojIndeksa;
	}
	public void setBrojIndeksa(String brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}
	public int getGodinaStudija() {
		return godinaStudija;
	}
	public void setGodinaStudija(int godinaStudija) {
		this.godinaStudija = godinaStudija;
	}
	public String getUlogaNastavnik() {
		return ulogaNastavnik;
	}
	public void setUlogaNastavnik(String ulogaNastavnik) {
		this.ulogaNastavnik = ulogaNastavnik;
	}
	public String getSmerStudenta() {
		return smerStudenta;
	}
	public void setSmerStudenta(String smerStudenta) {
		this.smerStudenta = smerStudenta;
	}
	
	
}
