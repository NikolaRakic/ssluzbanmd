package com.studentska.sluzba.dto.request;

public class KreirajSmerRequestDTO {
	
	private int id;
	private String naziv;
	private int trajanje;
	
	
	public KreirajSmerRequestDTO(int id, String naziv, int trajanje) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.trajanje = trajanje;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public int getTrajanje() {
		return trajanje;
	}


	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}
	
	

}
