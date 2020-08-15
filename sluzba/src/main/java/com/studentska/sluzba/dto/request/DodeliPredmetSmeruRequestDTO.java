package com.studentska.sluzba.dto.request;

public class DodeliPredmetSmeruRequestDTO {
	private int idPredmet;
	private int idSmer;
	private int godinaPojavljivanja;
	public int getIdPredmet() {
		return idPredmet;
	}
	public void setIdPredmet(int idPredmet) {
		this.idPredmet = idPredmet;
	}
	public int getIdSmer() {
		return idSmer;
	}
	public void setIdSmer(int idSmer) {
		this.idSmer = idSmer;
	}
	public int getGodinaPojavljivanja() {
		return godinaPojavljivanja;
	}
	public void setGodinaPojavljivanja(int godinaPojavljivanja) {
		this.godinaPojavljivanja = godinaPojavljivanja;
	}
	
	
}
